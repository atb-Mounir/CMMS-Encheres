
package org.encheres.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.encheres.BusinessException;
import org.encheres.bo.Article;
import org.encheres.bo.Categorie;
import org.encheres.bo.Enchere;
import org.encheres.bo.Utilisateur;
import org.encheres.message.CodesResultat;


/**
 * Classe en charge de
 * @author mdelauna2
 * @version EncheresCMM - V1.0
 * @date 22 janv. 2020 - 12:22:37
 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT_UTILISATEUR="insert into UTILISATEURS VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	private static final String TEST_CONNEXION_UTILISATEUR="SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur  FROM UTILISATEURS WHERE pseudo=? AND mot_de_passe=?;";
	private static final String TEST_CONNEXION_UPDATE_UTILISATEUR="SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur  FROM UTILISATEURS WHERE no_utilisateur=? AND mot_de_passe=?;";

	private static final String MES_ENCHERES_REMPORTEES = "select MAX(montant_enchere),art.nom_article,art.prix_initial,art.date_fin_encheres,"+
			"art.no_vendeur FROM ENCHERES e JOIN ARTICLES_VENDUS art ON art.no_article=e.no_article"+
			"JOIN UTILISATEURS ut ON e.no_acheteur=ut.no_utilisateur WHERE date_fin_encheres < GETDATE()"+
			"AND e.no_acheteur=? GROUP BY art.nom_article,art.prix_initial,art.date_fin_encheres,art.no_vendeur;";
	private static final String MES_VENTES_EN_COURS ="SELECT a.no_article,a.nom_article,a.description,a.date_debut_encheres,a.date_fin_encheres,"+
			"a.prix_initial,a.prix_vente,a.no_vendeur,a.no_categorie,u.pseudo FROM ARTICLES_VENDUS as a JOIN UTILISATEURS u " +
			"ON u.no_utilisateur=a.no_vendeur WHERE no_vendeur=? AND (date_debut_encheres<=GETDATE() AND date_fin_encheres<=GETDATE());";
	private static final String MES_VENTES_NON_DEBUTEES = "SELECT a.no_article,a.nom_article,a.description,a.date_debut_encheres,a.date_fin_encheres,"+
			"a.prix_initial,a.prix_vente,a.no_vendeur,a.no_categorie,u.pseudo FROM ARTICLES_VENDUS as a"+
			"JOIN UTILISATEURS u  ON u.no_utilisateur=a.no_vendeur  WHERE no_vendeur=? AND date_debut_encheres>GETDATE();";
	private static final String MES_VENTES_TERMINEES = "SELECT a.no_article,a.nom_article,a.description,a.date_debut_encheres,a.date_fin_encheres,"+
			 "a.prix_initial,a.prix_vente,a.no_vendeur,a.no_categorie,u.pseudo FROM ARTICLES_VENDUS as a"+
			 "JOIN UTILISATEURS u  ON u.no_utilisateur=a.no_vendeur WHERE no_vendeur=? AND date_fin_encheres<GETDATE();";
	private static final String SELECT_UTILISATEUR_BY_ID="SELECT ut.no_utilisateur, ut.pseudo, ut.nom, ut.prenom,ut.email,ut.telephone,ut.rue,ut.code_postal,ut.ville,ut.mot_de_passe,ut.credit,ut.administrateur FROM UTILISATEURS ut WHERE ut.no_utilisateur=?;";
	private static final String SELECT_UTILISATEUR_BY_PSEUDO="SELECT ut.pseudo FROM UTILISATEURS ut WHERE ut.pseudo=?;";
	private static final String UPDATE_UTILISATEUR="UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, "
			+ "email=?, telephone=?, rue=?, code_postal=?,\r\n" + 
			"ville=?, mot_de_passe=?, credit=? WHERE no_utilisateur=?";
	private static final String DELETE_UTILISATEUR="DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
	private static final String DELETE_ENCHERES="DELETE FROM ENCHERES WHERE no_acheteur=?";
	
	
	@Override
	public void creerUtilisateur(Utilisateur utilisateur) throws BusinessException {
		System.out.println("Je suis dans creerUtilisateur");
		if(utilisateur==null)
		{
			System.out.println("Je suis dans creerUtilisateur et l'utilisateur est null");
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				System.out.println("J'ai fais la connexion");
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
				System.out.println("j'ai fais le pstmt");
				pstmt.setString(1, utilisateur.getPseudo());
				System.out.println(utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				System.out.println(utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				System.out.println(utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				System.out.println(utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				System.out.println(utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRueUtilisateur());
				System.out.println(utilisateur.getRueUtilisateur());
				pstmt.setString(7, utilisateur.getCpUtilisateur());
				System.out.println(utilisateur.getCpUtilisateur());
				pstmt.setString(8, utilisateur.getVilleUtilisateur());
				System.out.println(utilisateur.getVilleUtilisateur());
				pstmt.setString(9, utilisateur.getMotDePasse());
				System.out.println(utilisateur.getMotDePasse());
				pstmt.setInt(10, utilisateur.getCredit());
				System.out.println(utilisateur.getCredit());
				pstmt.setByte(11, (byte)(utilisateur.isAdministrateur()?1:0));
				System.out.println((byte)(utilisateur.isAdministrateur()?1:0));
				
				pstmt.executeUpdate();
				System.out.println("J'ai pass?? l'execute");
				rs = pstmt.getGeneratedKeys();
				System.out.println("J'ai pass?? le rs");
				if(rs.next())
				{
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
			throw businessException;
		}
		System.out.println("J'ai fini la m??thode creer utilisateur");
	}

	public Utilisateur testConnexion(String pseudo, String motDePasse) throws BusinessException{
		
		Utilisateur utilisateur=null;
		BusinessException businessException = new BusinessException();
		System.out.println(pseudo);
		System.out.println(motDePasse);
		System.out.println("Je suis dans le DAO test de connexion");
		if(pseudo==null  || motDePasse==null)
		{
			System.out.println("Je suis dans creerUtilisateur et le pseudo et ou le mot de passe est null");
			System.out.println(pseudo);
			System.out.println(motDePasse);
			
			businessException.ajouterErreur(CodesResultatDAL.TEST_CONNEXION_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(TEST_CONNEXION_UTILISATEUR);
				System.out.println("j'ai fais le pstmt");
				
				pstmt.setString(1, pseudo);
				System.out.println("j'ai fais un set");
				pstmt.setString(2, motDePasse);
				
				System.out.println("j'ai fais les set");
				pstmt.executeQuery();
				System.out.println("J'ai pass?? l'execute");
				rs = pstmt.executeQuery();
				System.out.println("J'ai pass?? le rs");
				if(rs.next())
				{
					System.out.println("Succ??s de connexion utilisateur");
					
					utilisateur=new Utilisateur();
					
					utilisateur.setNoUtilisateur(rs.getInt(1));
					utilisateur.setPseudo(rs.getString(2));
					utilisateur.setNom(rs.getString(3));
					utilisateur.setPrenom(rs.getString(4));
					utilisateur.setEmail(rs.getString(5));
					utilisateur.setTelephone(rs.getString(6));
					utilisateur.setRueUtilisateur(rs.getString(7));
					utilisateur.setCpUtilisateur(rs.getString(8));
					utilisateur.setVilleUtilisateur(rs.getString(9));
					utilisateur.setMotDePasse(rs.getString(10));
					utilisateur.setCredit(rs.getInt(11));
					utilisateur.setAdministrateur(rs.getByte(12)!=0);
				
				}
				else {
					System.out.println("Erreur de connexion utilisateur");
				}
				rs.close();
				pstmt.close();
				cnx.commit();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}
	
public Utilisateur testConnexionModifierUtilisateur(int noUtilisateur, String motDePasse) throws BusinessException{
		
		Utilisateur utilisateur=null;
		BusinessException businessException = new BusinessException();
		System.out.println("Je suis dans le DAO test de connexion");
		if(noUtilisateur==0  || motDePasse==null)
		{
			
			businessException.ajouterErreur(CodesResultatDAL.TEST_CONNEXION_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(TEST_CONNEXION_UPDATE_UTILISATEUR);

				pstmt.setInt(1, noUtilisateur);
				pstmt.setString(2, motDePasse);
				
				pstmt.executeQuery();
				rs = pstmt.executeQuery();
				if(rs.next())
				{
					System.out.println("Succ??s de connexion utilisateur");
					
					utilisateur=new Utilisateur();
					
					utilisateur.setNoUtilisateur(rs.getInt(1));
					utilisateur.setPseudo(rs.getString(2));
					utilisateur.setNom(rs.getString(3));
					utilisateur.setPrenom(rs.getString(4));
					utilisateur.setEmail(rs.getString(5));
					utilisateur.setTelephone(rs.getString(6));
					utilisateur.setRueUtilisateur(rs.getString(7));
					utilisateur.setCpUtilisateur(rs.getString(8));
					utilisateur.setVilleUtilisateur(rs.getString(9));
					utilisateur.setMotDePasse(rs.getString(10));
					utilisateur.setCredit(rs.getInt(11));
					utilisateur.setAdministrateur(rs.getByte(12)!=0);
				
				}
				else {
					System.out.println("Erreur de connexion utilisateur");
				}
				rs.close();
				pstmt.close();
				cnx.commit();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}
	/** 
	 * @{inheritDoc}
	 * @see org.encheres.dal.UtilisateurDAO#byNomArticle()
	 */
	@Override
	public List<Article> byNomArticle() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	 * @{inheritDoc}
	 * @see org.encheres.dal.UtilisateurDAO#byCategorie()
	 */
	@Override
	public List<Article> byCategorie() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	 * @{inheritDoc}
	 * @see org.encheres.dal.UtilisateurDAO#encheresOuvertes()
	 */
	
	/** 
	 * @{inheritDoc}
	 * @see org.encheres.dal.UtilisateurDAO#mesEncheresEnCours()
	 */
	@Override
	public List<Article> mesEncheresEnCours() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	 * @{inheritDoc}
	 * @see org.encheres.dal.UtilisateurDAO#mesEncheresRemportees()
	 */
	@Override
	public List<Article> mesEncheresRemportees() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	 * @{inheritDoc}
	 * @see org.encheres.dal.UtilisateurDAO#mesVentesEnCours()
	 */
	@Override
	public List<Article> mesVentesEnCours() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	 * @{inheritDoc}
	 * @see org.encheres.dal.UtilisateurDAO#mesVentesNonDebutees()
	 */
	@Override
	public List<Article> mesVentesNonDebutees() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	 * @{inheritDoc}
	 * @see org.encheres.dal.UtilisateurDAO#mesVentesTerminees()
	 */
	@Override
	public List<Article> mesVentesTerminees() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @{inherite code}
	 * @see org.encheres.dal.UtilisateurDAO#selectArticleById(java.lang.String)
	 */
	@Override
	public Article selectArticleById(String no_article) {
		// TODO Auto-generated method stub
		return null;
	}

	public Utilisateur selectUtilisateurById(int noUtilisateur) throws BusinessException {
		Utilisateur utilisateur=null;
		BusinessException businessException = new BusinessException();
		System.out.println("Je suis dans le DAO test de connexion");
		if(noUtilisateur==0)
		{
			System.out.println("Je suis dans selectUtilisateurById et mon id est null");
			
			businessException.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_BY_ID_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_ID);
				
				pstmt.setInt(1, noUtilisateur);
				
				pstmt.executeQuery();
				rs = pstmt.executeQuery();
				if(rs.next())
				{
					
					utilisateur=new Utilisateur();
					
					utilisateur.setNoUtilisateur(rs.getInt(1));
					utilisateur.setPseudo(rs.getString(2));
					utilisateur.setNom(rs.getString(3));
					utilisateur.setPrenom(rs.getString(4));
					utilisateur.setEmail(rs.getString(5));
					utilisateur.setTelephone(rs.getString(6));
					utilisateur.setRueUtilisateur(rs.getString(7));
					utilisateur.setCpUtilisateur(rs.getString(8));
					utilisateur.setVilleUtilisateur(rs.getString(9));
					utilisateur.setMotDePasse(rs.getString(10));
					utilisateur.setCredit(rs.getInt(11));
					utilisateur.setAdministrateur(rs.getByte(12)!=0);
				
				}
				else {
					System.out.println("Erreur de connexion utilisateur");
				}
				rs.close();
				pstmt.close();
				cnx.commit();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_BY_ID_NULL);
			throw businessException;
		}
		return utilisateur;
	}
	
	
	public boolean selectUtilisateurByPseudo(String pseudo) throws BusinessException {
		boolean pseudoExiste=false;
		BusinessException businessException = new BusinessException();
		System.out.println("Je suis dans le DAO test de connexion");
		if(pseudo==null)
		{
			System.out.println("Je suis dans selectUtilisateurById et mon id est null");
			
			businessException.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_BY_PSEUDO_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_PSEUDO);
				
				pstmt.setString(1, pseudo);
				
				pstmt.executeQuery();
				rs = pstmt.executeQuery();
				if(rs.next())
				{
					
					pseudoExiste=true;
				
				}
				else {
					System.out.println("Erreur de connexion utilisateur");
				}
				rs.close();
				pstmt.close();
				cnx.commit();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_BY_ID_NULL);
			throw businessException;
		}
		return pseudoExiste;
	}
	
	
	public void modifierUtilisateur(Utilisateur utilisateurAvecModification) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		Utilisateur utilisateurTestConnexion=null;
		/*V??rification si le mot de passe actuel rentr?? est correct*/

		
		System.out.println("Je suis dans le DAO test de connexion update");
//		if(utilisateurAvecModification==null  || motDePasseActuel==null || utilisateurTestConnexion==null)
//		{
//			System.out.println("Je suis dans modifierUtilisateur et l'utilisateur et ou le mot de passe est null");
//
//	
//			businessException.ajouterErreur(CodesResultatDAL.MODIFIER_UTILISATEUR_NULL);
//			throw businessException;
//		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
				
				
				pstmt.setString(1, utilisateurAvecModification.getPseudo());
				pstmt.setString(2, utilisateurAvecModification.getNom());
				pstmt.setString(3, utilisateurAvecModification.getPrenom());
				pstmt.setString(4, utilisateurAvecModification.getEmail());
				pstmt.setString(5, utilisateurAvecModification.getTelephone());
				pstmt.setString(6, utilisateurAvecModification.getRueUtilisateur());
				pstmt.setString(7, utilisateurAvecModification.getCpUtilisateur());
				pstmt.setString(8, utilisateurAvecModification.getVilleUtilisateur());
				pstmt.setString(9, utilisateurAvecModification.getMotDePasse());
				pstmt.setInt(10, utilisateurAvecModification.getCredit());
				pstmt.setInt(11, utilisateurAvecModification.getNoUtilisateur());
				
				System.out.println("J'ai pass?? le rs de update");


				pstmt.executeUpdate();

				pstmt.close();
				cnx.commit();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
			throw businessException;
		}
		
	}
	
public void supprimerUtilisateur(int noUtilisateur) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		
		System.out.println("Je suis dans le DAO supprimerUtilisateur");
		if(noUtilisateur<=0)
		{
			System.out.println("Je suis dans modifierUtilisateur et l'utilisateur et ou le mot de passe est null");

			
			businessException.ajouterErreur(CodesResultatDAL.DELETE_UTILISATEUR_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				deleteEncheres(noUtilisateur);
				pstmt = cnx.prepareStatement(DELETE_UTILISATEUR);
				
				
				pstmt.setInt(1, noUtilisateur);

				pstmt.executeUpdate();
				pstmt.close();
				cnx.commit();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_UTILISATEUR_ECHEC);
			throw businessException;
		}
		
	}

public void deleteEncheres(int noUtilisateur) throws BusinessException {
	
	BusinessException businessException = new BusinessException();
	
	System.out.println("Je suis dans le DAO supprimerUtilisateur");
	if(noUtilisateur<=0)
	{
		System.out.println("Je suis dans modifierUtilisateur et l'utilisateur et ou le mot de passe est null");

		
		businessException.ajouterErreur(CodesResultatDAL.DELETE_UTILISATEUR_NULL);
		throw businessException;
	}
	
	try(Connection cnx = ConnectionProvider.getConnection())
	{
		try
		{
			cnx.setAutoCommit(false);
			PreparedStatement pstmt;
			pstmt = cnx.prepareStatement(DELETE_ENCHERES);
			
			
			pstmt.setInt(1, noUtilisateur);

			pstmt.executeUpdate();
			pstmt.close();
			cnx.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			cnx.rollback();
			throw e;
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		businessException.ajouterErreur(CodesResultatDAL.DELETE_UTILISATEUR_ECHEC);
		throw businessException;
	}
	
}


}
