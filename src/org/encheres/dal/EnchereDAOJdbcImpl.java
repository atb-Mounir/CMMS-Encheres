 /**
 * 
 */
package org.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.encheres.BusinessException;
import org.encheres.bll.UtilisateurManager;
import org.encheres.bo.Enchere;

/**
 * Classe en charge
 * @author maitbaha2019
 * @version EncheresCMM - V1.0
 * @date 24 janv. 2020 - 15:37:43
 */
public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private static final String INSERT_ENCHERE = "insert into ENCHERES (no_acheteur,no_article,date_enchere,montant_enchere) values (?,?,?,?)";
	private static final String SELECT_ENCHERE = "SELECT no_acheteur, no_article, date_enchere, montant_enchere "
												+ "FROM ENCHERES WHERE no_acheteur=? AND no_article=?";
	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET date_enchere=?, montant_enchere=? "
												+ "WHERE no_acheteur=? AND no_article=?";

	/*
	 * @{inherite code}
	 * @see org.encheres.dal.EnchereDAO#insertEnchere(org.encheres.bo.Utilisateur, org.encheres.bo.Article, org.encheres.bo.Enchere, org.encheres.bo.Enchere)
	 */
	@Override
	public void insertEnchere(Enchere enchere){
		boolean enchereExiste;
		System.out.println("Je suis dans InsererEncher");

		enchereExiste=selectEnchere(enchere);
		/* Si l'enchere n'existe pas, on le crée */
		if (!enchereExiste) {
			System.out.println("l'enchere n'existe pas");
			creationEnchere(enchere);
		}
		/* Sinon on fait un update */
		else {

			miseAJourEnchere(enchere);
		}
		
		/*Soustraction de l'enchere dans le credit et mise à jour dans la BDD*/
		System.out.println("montant enchere :"+enchere.getMontantEnchere() + "credit utilisateur : " + enchere.getUtilisateur().getCredit());
		enchere.getUtilisateur().setCredit(enchere.getUtilisateur().getCredit()-enchere.getMontantEnchere());
		System.out.println("credit deduit"+enchere.getUtilisateur().getCredit());
		UtilisateurDAO utilisateurDAO=DAOFactory.getUtilisateurDAO();
		try {
			utilisateurDAO.modifierUtilisateur(enchere.getUtilisateur());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public boolean selectEnchere(Enchere enchere) {
		boolean enchereExiste = false;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				
				
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(SELECT_ENCHERE);
				pstmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
				pstmt.setInt(2, enchere.getArticle().getNoArticle());

				pstmt.executeQuery();
				rs = pstmt.executeQuery();

				if (rs.next()) {
					enchereExiste = true;
				}

				rs.close();
				pstmt.close();
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return enchereExiste;
	}
	
	
	public boolean creationEnchere(Enchere enchere) {
		boolean enchereExiste = false;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
				pstmt.setInt(2, enchere.getArticle().getNoArticle());
				pstmt.setDate(3,java.sql.Date.valueOf(enchere.getDateEnchere()) );
				pstmt.setInt(4,enchere.getMontantEnchere());

				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();

				if (rs.next()) {
					enchereExiste = true;
				}

				rs.close();
				pstmt.close();
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return enchereExiste;
	}
	
	public void miseAJourEnchere(Enchere enchere) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;

				pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
				pstmt.setDate(1,java.sql.Date.valueOf(enchere.getDateEnchere()));
				pstmt.setInt(2,enchere.getMontantEnchere());
				pstmt.setInt(3, enchere.getUtilisateur().getNoUtilisateur());
				pstmt.setInt(4, enchere.getArticle().getNoArticle());
				

				pstmt.executeUpdate();
				pstmt.close();
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}



