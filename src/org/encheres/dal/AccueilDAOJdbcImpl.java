/**
 * 
 */
package org.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.encheres.BusinessException;
import org.encheres.bo.Article;
import org.encheres.bo.Categorie;
import org.encheres.bo.Enchere;
import org.encheres.bo.Utilisateur;
import org.encheres.message.CodesResultat;
import org.encheres.servlets.Connexion;

/**
 * Classe en charge de selectionner les artiches en fonction du choix de l'utilisateur dans la page d'accueil
 * @author mdelauna2
 * @version EncheresCMM - V1.0
 * @date 24 janv. 2020 - 13:01:34
 */
public class AccueilDAOJdbcImpl implements AccueilDAO {

	private static final String TOUTES_LES_ENCHERES="SELECT ut.no_utilisateur, ut.pseudo, ut.nom, ut.prenom, ut.email,ut.telephone,ut.rue, "
			+"ut.code_postal,ut.ville,ut.mot_de_passe,ut.credit,ut.administrateur, " + 
			"e.date_enchere, e.montant_enchere, cat.no_categorie, cat.libelle, art.no_article, art.nom_article," + 
			"art.description, art.date_debut_encheres," + 
			"art.date_fin_encheres, art.prix_initial, art.prix_vente, art.no_vendeur, art.no_categorie,e.no_acheteur " + 
			"FROM ARTICLES_VENDUS art " + 
			"LEFT JOIN ENCHERES e ON art.no_article=e.no_article " + 
			"JOIN UTILISATEURS ut ON ut.no_utilisateur=art.no_vendeur " + 
			"JOIN CATEGORIES cat ON art.no_categorie=cat.no_categorie ";
			

	



	/*DEBUT MODELE POUR LES REQUETES*/
	@Override
	public List<Article> toutesLesEncheresOuvertes() throws BusinessException {
		List<Article> toutesLesEncheresOuvertes = new ArrayList<Article>();
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			/*Ajouter à TOUTES_LES_ENCHERES la restriction de la requête spécifique*/
			PreparedStatement pstmt = cnx.prepareStatement(TOUTES_LES_ENCHERES+" WHERE date_fin_encheres > GETDATE() AND date_debut_encheres< GETDATE() ORDER BY e.montant_enchere DESC;");
			
			ResultSet rs = pstmt.executeQuery();
			
			return toutesLesEncheresOuvertes=creationArticle(rs);
				
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			/*Créer un code erreur*/
			businessException.ajouterErreur(CodesResultat.LECTURE_LISTES_ECHEC);
			throw businessException;
		}
		
	}
	
	
	
	@Override
	public List<Article> mesVentesEnCours(int no_utilisateur) throws BusinessException {
		List<Article> mesVentesEnCours = new ArrayList<Article>();
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(TOUTES_LES_ENCHERES+"WHERE (art.date_debut_encheres<GETDATE() and art.date_fin_encheres>GETDATE()) and (ut.no_utilisateur like ?);");
			
			pstmt.setInt(1, no_utilisateur);
			
			ResultSet rs = pstmt.executeQuery();
			
			return mesVentesEnCours=creationArticle(rs);
		}
		catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			/*Créer un code erreur*/
			businessException.ajouterErreur(CodesResultat.LECTURE_LISTES_ECHEC);
			throw businessException;
		}
	}
	
	


	private List<Article> creationArticle(ResultSet rs) throws SQLException {
		List<Article> toutesLesEncheresOuvertes = new ArrayList<Article>();
		int noArticleDejaPresent;
		while(rs.next())
		{
		String libelleCategorie=rs.getString(16);
		int noCategorie=rs.getInt(15);
		
		Categorie categorie=new Categorie(noCategorie, libelleCategorie);
		
		int noUtilisateur=rs.getInt(1);
		String pseudo=rs.getString(2);
		String nom=rs.getString(3);
		String prenom=rs.getString(4);
		String email=rs.getString(5);
		String telephone=rs.getString(6);
		String rue=rs.getString(7);
		String cp=rs.getString(8);
		String ville=rs.getString(9);
		String motDePasse=rs.getString(10);
		int credit=rs.getInt(11);
		boolean administrateur=(rs.getByte(12)!=0);
		Utilisateur utilisateur=new Utilisateur(noUtilisateur,pseudo, nom, prenom, email, telephone, rue,
				cp, ville, motDePasse, credit, administrateur); 
		int noAcheteur=rs.getInt(26);
		Utilisateur acheteur=new Utilisateur(noAcheteur,pseudo, nom, prenom, email, telephone, rue,
				cp, ville, motDePasse, credit, administrateur); 
		
		int montantEnchere=rs.getInt(14);
		LocalDate dateEnchere;
		if(rs.getDate(13)!=null) {
			dateEnchere=rs.getDate(13).toLocalDate();
		}
		else {
			dateEnchere=LocalDate.now();
		}
		Enchere enchere=new Enchere(dateEnchere,montantEnchere,acheteur);
		
		int noArticle=rs.getInt(17);
		String nomArticle=rs.getString(18);
		String description=rs.getString(19);
		LocalDate dateDebutEncheres=rs.getDate(20).toLocalDate();
		LocalDate dateFinEncheres=rs.getDate(21).toLocalDate();
		int prixInitial=rs.getInt(22);
		int prixVente=rs.getInt(23);
		int noVendeur=rs.getInt(24);
		int noCategorieArt=rs.getInt(25);
		
		Article article= new Article(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres,
				prixInitial, prixVente, categorie, enchere, utilisateur);
		System.out.println("Nom des artciles requetes : "+article.getNomArticle());
		noArticleDejaPresent=0;
		for(Article art : toutesLesEncheresOuvertes) {
			if(art.getNoArticle()==article.getNoArticle()) {
				noArticleDejaPresent++;
			}
		}
		/*Si  l'article n'existe pas, on l'ajoute*/
		if(noArticleDejaPresent==0) {
		toutesLesEncheresOuvertes.add(article);
		}
		
	}
		return toutesLesEncheresOuvertes;
	}

	/** 
	 * @{inheritDoc}
	 * @see org.encheres.dal.AccueilDAO#mesEncheresOuvertes()
	 */
	
	public List<Article> mesEncheresOuvertes(int idUtilisateur) throws BusinessException {
		System.out.println("Debut enchères ouvertes");
		BusinessException businessException = new BusinessException();
		List<Article> mesEncheresOuvertes = new ArrayList<Article>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{

			/*Ajouter à TOUTES_LES_ENCHERES la restriction de la requête spécifique*/
			PreparedStatement pstmt = cnx.prepareStatement(TOUTES_LES_ENCHERES+" WHERE date_fin_encheres > GETDATE() AND e.no_acheteur=?;");
			pstmt.setInt(1, idUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			// Création et instanciation de tous les objets de la BO
			return mesEncheresOuvertes=creationArticle(rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			/*Créer un code erreur*/
			businessException.ajouterErreur(CodesResultat.LECTURE_LISTES_ECHEC_MES_ENCHERES_OUVERTES);
			throw businessException;
		}

	}

	/**
	 *@throws BusinessException 
	 * @(inheritDoc) 
	 * @see org.encheres.dal.AccueilDAO#affichageParCategorie(java.lang.String)
	 */
	@Override
	public List<Article> affichageParCategorie(String libelleCategorie) throws BusinessException{
		System.out.println("Debut recherche");
		BusinessException businessException = new BusinessException();
		List<Article> toutesLesEncheresOuvertes = new ArrayList<Article>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(TOUTES_LES_ENCHERES+" WHERE cat.libelle=? ORDER BY e.montant_enchere DESC");
				
				pstmt.setString(1, libelleCategorie);
				
				pstmt.executeQuery();
				rs = pstmt.executeQuery();
				
				toutesLesEncheresOuvertes=creationArticle(rs);
				
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
		System.out.println("fin recherche");
		return toutesLesEncheresOuvertes;
		
	}
	
	public List<Article> affichageParMotsCles(String motCle) throws BusinessException{
		System.out.println("Debut recherche mot cle");
		System.out.println(motCle);
		BusinessException businessException = new BusinessException();
		List<Article> toutesLesEncheres = new ArrayList<Article>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				System.out.println("avant requete");
				pstmt = cnx.prepareStatement(TOUTES_LES_ENCHERES+" WHERE art.nom_article like ? ORDER BY e.montant_enchere DESC;");
				System.out.println("apres pstmt");
				pstmt.setString(1, "%"+motCle+"%");
				
				pstmt.executeQuery();
				rs = pstmt.executeQuery();
				System.out.println("apres execute");
				toutesLesEncheres=creationArticle(rs);
				
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
		System.out.println("fin recherche");
		return toutesLesEncheres;
		
	}
	
	public List<Article> mesVentesNonDebutees(int noUtilisateur) throws BusinessException{
		System.out.println("Debut recherche mesventesnondebutees");
		BusinessException businessException = new BusinessException();
		List<Article> toutesLesEncheres = new ArrayList<Article>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				System.out.println("avant requete");
				pstmt = cnx.prepareStatement(TOUTES_LES_ENCHERES+" WHERE no_vendeur=? AND date_debut_encheres>GETDATE() ORDER BY e.montant_enchere DESC;");
				System.out.println("apres pstmt");
				pstmt.setInt(1, noUtilisateur);
				
				pstmt.executeQuery();
				rs = pstmt.executeQuery();
				System.out.println("apres execute");
				toutesLesEncheres=creationArticle(rs);
				
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
			businessException.ajouterErreur(CodesResultatDAL.MES_VENTES_NON_DEBUTEES);
			throw businessException;
		}
		System.out.println("fin recherche");
		return toutesLesEncheres;
		
	}



	@Override
	public List<Article> mesEncheresRemportees(int idUtilisateur) throws BusinessException {
		System.out.println("Accueil DAOJdbcImpl : mesEncheresRemportees");
		List<Article> toutesMesEncheresRemporteesTemp = new ArrayList<Article>();
		List<Article> toutesMesEncheresRemportees = new ArrayList<Article>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			/*Ajouter à TOUTES_LES_ENCHERES la restriction de la requête spécifique*/
			PreparedStatement pstmt = cnx.prepareStatement(TOUTES_LES_ENCHERES+					
			" WHERE date_fin_encheres < GETDATE() ORDER BY e.montant_enchere DESC;");
			ResultSet rs = pstmt.executeQuery();
			// Création et instanciation de tous les objets de la BO
			toutesMesEncheresRemporteesTemp=creationArticle(rs);
			
			for(Article article : toutesMesEncheresRemporteesTemp) {
				
				if(article.getEnchere().getNoAcheteur().getNoUtilisateur()==idUtilisateur) {
				toutesMesEncheresRemportees.add(article);
				}
			}
			
			return toutesMesEncheresRemportees;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			/*Créer un code erreur*/
			businessException.ajouterErreur(CodesResultat.LECTURE_LISTES_ECHEC_VENTES_REMPORTEES);
			throw businessException;
		}
		
	
	}



	@Override
	public List<Article> mesVentesTerminees(int no_utilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		List<Article>mesVentesTerminees = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(TOUTES_LES_ENCHERES + "WHERE  (date_fin_encheres<GETDATE()) AND (no_vendeur=?);");
			pstmt.setInt(1,no_utilisateur);
			ResultSet rs = pstmt.executeQuery();
			
			return mesVentesTerminees=creationArticle(rs);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			/*Créer un code erreur*/
			businessException.ajouterErreur(CodesResultat.LECTURE_LISTES_ECHEC);
			throw businessException;
		}
	}
}
