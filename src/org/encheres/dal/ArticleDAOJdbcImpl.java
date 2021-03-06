/**
 * 
 */
package org.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.encheres.bo.Article;
import org.encheres.bo.Categorie;
import org.encheres.bo.Enchere;
import org.encheres.bo.Retrait;
import org.encheres.bo.Utilisateur;

import com.sun.corba.se.impl.orb.PrefixParserData;

import org.encheres.BusinessException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe en charge de
 * 
 * @author mdelauna2
 * @version EncheresCMM - V1.0
 * @date 22 janv. 2020 - 12:22:20
 */
public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String INSERT_ARTICLE="INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_vendeur,no_categorie) "
				+ "VALUES (?,?,?,?,?,?,?)";
	
	private static final String INSERT_RETRAIT="INSERT INTO RETRAITS (no_article,rue,code_postal,ville) "
			+ "VALUES (?,?,?,?)"; 
	
	private static final String DETAIL_DE_VENTE = "SELECT a.no_article,nom_article,description,c.no_categorie, c.libelle, e.montant_enchere, e.date_enchere, e.no_acheteur,\r\n" + 
			"			date_fin_encheres, date_debut_encheres, prix_initial, r.rue, r.code_postal, r.ville, vendeur.pseudo as'vendeur.pseudo', vendeur.nom as 'vendeur.nom',\r\n" + 
			"			vendeur.prenom as 'vendeur.prenom', vendeur.email as 'vendeur.email', vendeur.telephone as 'vendeur.telephone', vendeur.rue as 'vendeur.rue',\r\n" + 
			"			vendeur.code_postal as 'vendeur.code_postal', vendeur.ville as 'vendeur.ville', vendeur.mot_de_passe as 'vendeur.mot_de_passe', vendeur.no_utilisateur as 'vendeur.no_utilisateur',\r\n" + 
			"			vendeur.credit as'vendeur.credit', vendeur.administrateur as 'vendeur.administrateur',\r\n" + 
			"			acheteur.no_utilisateur as 'acheteur.no_utilisateur', acheteur.pseudo as 'acheteur.pseudo', \r\n" + 
			"			acheteur.nom as 'acheteur.nom', acheteur.prenom as 'acheteur.prenom', acheteur.email as 'acheteur.email', \r\n" + 
			"			acheteur.telephone as 'acheteur.telephone', acheteur.rue as 'acheteur.rue',\r\n" + 
			"			acheteur.code_postal 'acheteur.code_postal', acheteur.ville as 'acheteur.ville', \r\n" + 
			"			acheteur.mot_de_passe as 'acheteur.mot_de_passe', acheteur.credit as 'acheteur.credit', acheteur.administrateur as 'acheteur.administrateur'\r\n" + 
			"			FROM ARTICLES_VENDUS a \r\n" + 
			"		 LEFT JOIN RETRAITS r ON a.no_article= r.no_article\r\n" + 
			"			LEFT JOIN UTILISATEURS vendeur ON a.no_vendeur=vendeur.no_utilisateur\r\n" + 
			"			LEFT JOIN ENCHERES e ON a.no_article = e.no_article\r\n" + 
			"			LEFT JOIN UTILISATEURS acheteur ON acheteur.no_utilisateur=e.no_acheteur\r\n" + 
			"			JOIN CATEGORIES c ON a.no_categorie=c.no_categorie \r\n" + 
			"			WHERE a.no_article =?\r\n" + 
			"			ORDER BY e.montant_enchere DESC";
	
	private static final String DELETE_RETRAIT="DELETE FROM RETRAITS WHERE no_article=?";
	
	private static final String DELETE_ARTICLE="DELETE FROM ARTICLES_VENDUS WHERE no_article=?";
	


	public void insertRetrait(Retrait retrait) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Article selectArticleById(int no_article) {
		Article art = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement rqt = cnx.prepareStatement(DETAIL_DE_VENTE);) {

			rqt.setInt(1, no_article);
			try (ResultSet rs = rqt.executeQuery()) {

				Categorie cat = null;
				Retrait retr = null;
				if (rs.next()) {
					// je cr??e les nouveaux objets pour pouvoir les ins??ser dans l'objet Article
					cat = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
					
					System.out.println(rs.getString("libelle"));
					retr = new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
					
					Utilisateur acheteur = new Utilisateur(rs.getString("acheteur.pseudo"), 
							rs.getString("acheteur.nom"), 
							rs.getString("acheteur.prenom"),
							rs.getString("acheteur.email"),
							rs.getString("acheteur.telephone"),
							rs.getString("acheteur.rue"),
							rs.getString("acheteur.code_postal"),
							rs.getString("acheteur.ville"),
							rs.getString("acheteur.mot_de_passe"),
							rs.getInt("acheteur.credit"),
							rs.getBoolean("acheteur.administrateur"));
					acheteur.setNoUtilisateur(rs.getInt("acheteur.no_utilisateur"));
					
					
					Enchere enchere=new Enchere();
					if(rs.getDate("date_enchere")!=null) {
						enchere=new Enchere(rs.getDate("date_enchere").toLocalDate(), rs.getInt("montant_enchere"), acheteur);
					}
					
					
					Utilisateur vendeur = new Utilisateur(rs.getString("vendeur.pseudo"), 
							rs.getString("vendeur.nom"), 
							rs.getString("vendeur.prenom"),
							rs.getString("vendeur.email"),
							rs.getString("vendeur.telephone"),
							rs.getString("vendeur.rue"),
							rs.getString("vendeur.code_postal"),
							rs.getString("vendeur.ville"),
							rs.getString("vendeur.mot_de_passe"),
							rs.getInt("vendeur.credit"),
							rs.getBoolean("vendeur.administrateur"));
					vendeur.setNoUtilisateur(rs.getInt("vendeur.no_utilisateur"));
					
					art = new Article(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
							rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
							rs.getInt("montant_enchere"), cat, retr, enchere, vendeur);
					
					System.out.println("rue retrait"+retr.getRueRetrait());
					System.out.println("Je suis dans ***");
					System.out.println("numeroArticle dans la jdbc" + art.getNoArticle());
				}
			}

			return art;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return art;
	}



	@Override
	public void insertNouvelleVente(Article newArticle) throws BusinessException{
		if (newArticle==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			cnx.setAutoCommit(false);
			try
			{
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE,PreparedStatement.RETURN_GENERATED_KEYS);
				System.out.println("je fais un pstmt INSERT ARTICLE");
				pstmt.setString(1,newArticle.getNomArticle());
				pstmt.setString(2,newArticle.getDescription());
				pstmt.setDate(3,java.sql.Date.valueOf(newArticle.getDateDebutEncheres()));
				pstmt.setDate(4,java.sql.Date.valueOf(newArticle.getDateFinEncheres()));
				pstmt.setInt(5, newArticle.getMiseAPrix());
				pstmt.setInt(6, newArticle.getUtilisateur().getNoUtilisateur());
				System.out.println("Dans le pstmt INSERT ARTICLE j'enregistre le no_Cat??gorie ?? partir de la liste");
				pstmt.setInt(7, newArticle.getCategorie().getNoCategorie());
				pstmt.executeUpdate();
				System.out.println("Dans le pstmt INSERT ARTICLE r??cup??ration de la PK");
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next())
				{
					newArticle.setNoArticle(rs.getInt(1));
				}
				System.out.println("R??cup??ration de la PK");
				rs.close();
				pstmt.close();
					pstmt = cnx.prepareStatement(INSERT_RETRAIT);
					System.out.println("transmission de la PK article pour le no-retrait");
					pstmt.setInt(1, newArticle.getNoArticle());
					pstmt.setString(2, newArticle.getRetrait().getRueRetrait());
					pstmt.setString(3, newArticle.getRetrait().getCpRetrait());
					pstmt.setString(4, newArticle.getRetrait().getVilleRetrait());
					pstmt.executeUpdate();
					System.out.println("Mise ?? jour dans la BDD de la nouvelle vente");
					pstmt.close();
					cnx.commit();
			}
			catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
		}
	
		
	}

	@Override
	public void supprimerLaVente(Article article) throws BusinessException {
		System.out.println("m??thode suppprimer la vente : le retrait");
		try(Connection cnx = ConnectionProvider.getConnection();PreparedStatement pstmt = cnx.prepareStatement(DELETE_RETRAIT);)
		{
			cnx.setAutoCommit(false);
			try
			{
				pstmt.setInt(1, article.getNoArticle());
			}
			catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
			cnx.commit();
			System.out.println("m??thode suppprimer la vente : l'article");				
			try(PreparedStatement psmt = cnx.prepareStatement(DELETE_ARTICLE);)
			{
				cnx.setAutoCommit(false);
				try
				{
					pstmt.setInt(1, article.getNoArticle());
				}
				catch (Exception e) {
					e.printStackTrace();
					cnx.rollback();
					throw e;
				}
				cnx.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_NOUVELLE_VENTE);
		}
	}



}
