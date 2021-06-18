/**
 * 
 */
package org.encheres.bll;

import java.util.List;

import org.encheres.BusinessException;
import org.encheres.bo.Article;
import org.encheres.bo.Utilisateur;
import org.encheres.dal.AccueilDAO;
import org.encheres.dal.DAOFactory;
import org.encheres.dal.UtilisateurDAO;



/**
 * Classe en  charge de
 * @author cmartin2
 * @versionEncheresCMM - V1.0
 * @date 22 janv. 2020 - 14:24:22.
 */
public class UtilisateurManager implements UtilisateurBLL {
	
	private UtilisateurDAO utilisateurDAO;
	private AccueilDAO accueilDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
		this.accueilDAO = DAOFactory.getAccueilDAO();
	}

	public Article selectArticleById(String no_article) {
		System.out.println("je suis dans ArticleManager");	
		return this.utilisateurDAO.selectArticleById(no_article);
	}

	public void creerUtilisateur(String pseudo, String nom, String prenom,
			String email, String telephone, String rue, String cp, String ville, String motDePasse,
			String motDePasseConfirm) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		
		this.validerPseudo(pseudo, businessException);
		this.validerNom(nom, businessException);
		this.validerPrenom(prenom, businessException);
		this.validerEmail(email, businessException);
		this.validerTelephone(telephone, businessException);
		this.validerRue(rue, businessException);
		this.validerCp(cp, businessException);
		this.validerVille(ville, businessException);
		this.validerMotDePasse(motDePasse, motDePasseConfirm, businessException);
		
		Utilisateur utilisateur=null;
		System.out.println("Je suis dans l'utilisateur Manager sans test erreur");
		if(!businessException.hasErreurs())
		{
			System.out.println("Je suis dans l'utilisateur Manager avec test erreur");
			if(telephone==null) {
				utilisateur=new Utilisateur(pseudo, nom, prenom, email, rue, cp, ville, motDePasse, 100, false);
			}
			
			else {
				System.out.println("Je crée utilisateur avec tel non null");
				utilisateur=new Utilisateur(pseudo, nom, prenom, email, telephone, rue, cp, ville, motDePasse, 100, false);

			}
			System.out.println("Je crée l'instance DAO");
			
			/*Si le pseudo n'existe pas, on peut créer l'utilisateur*/
			if(!this.utilisateurDAO.selectUtilisateurByPseudo(pseudo)) {
			this.utilisateurDAO.creerUtilisateur(utilisateur);
			/*Article article = new Article(nomArticle.trim());
			listeCourse.getArticles().add(article);
			this.listeCourseDAO.insert(listeCourse);*/
			}
		}
		else
		{
			System.out.println("Je suis dans l'exception");
			throw businessException;
		}
		
	}
	
	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException{
		BusinessException businessException = new BusinessException();
		this.utilisateurDAO.supprimerUtilisateur(noUtilisateur);
		
	}

	public void modifierUtilisateur(int idUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String cp, String ville, String motDePasseActuel, int credit, 
			String motDePasseNouveauConfirmation) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		String motDePasse=null;
		
		this.validerPseudo(pseudo, businessException);
		this.validerNom(nom, businessException);
		this.validerPrenom(prenom, businessException);
		this.validerEmail(email, businessException);
		this.validerTelephone(telephone, businessException);
		this.validerRue(rue, businessException);
		this.validerCp(cp, businessException);
		this.validerVille(ville, businessException);
		
		/*Si le nouveau mot de passe est supérieur alors il est à mettre à jout */
		if(motDePasseNouveauConfirmation.length()>0) {
			motDePasse=motDePasseNouveauConfirmation;
		}
		/*Sinon on reprend le mot de passe habituel*/
		else {
			motDePasse=motDePasseActuel;
		}

		
		Utilisateur utilisateur=null;
		
		if(!businessException.hasErreurs())
		{
			System.out.println("Je suis dans l'utilisateur Manager avec test erreur");
			if(telephone==null) {
				utilisateur=new Utilisateur(pseudo, nom, prenom, email, rue, cp, ville, motDePasse, credit, false);
				utilisateur.setNoUtilisateur(idUtilisateur);
			}
			
			else {
				System.out.println("Je crée utilisateur avec tel non null");
				utilisateur=new Utilisateur(pseudo, nom, prenom, email, telephone, rue, cp, ville, motDePasse, credit, false);
				utilisateur.setNoUtilisateur(idUtilisateur);

			}
			System.out.println("Je crée l'instance DAO");
			/*Si le pseudo n'existe pas, on peut créer l'utilisateur*/
				this.utilisateurDAO.modifierUtilisateur(utilisateur);
				/*Article article = new Article(nomArticle.trim());
				listeCourse.getArticles().add(article);
				this.listeCourseDAO.insert(listeCourse);*/
			
			
		}
		else
		{
			System.out.println("Je suis dans l'exception");
			throw businessException;
		}
		
	}
	
	public boolean selectUtilisateurByPseudo(String pseudo) throws BusinessException{
		return this.utilisateurDAO.selectUtilisateurByPseudo(pseudo);
	}
	
	public List<Article> toutesLesEncheresOuvertes() throws BusinessException{
		return this.accueilDAO.toutesLesEncheresOuvertes();
	}
	
	@Override
	public List<Article> affichageParCategorie(String libelleCategorie) throws BusinessException {
		BusinessException businessException = new BusinessException();

		return this.accueilDAO.affichageParCategorie(libelleCategorie);
	}
	
	public List<Article> mesVentesNonDebutees(int noUtilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();

		return this.accueilDAO.mesVentesNonDebutees(noUtilisateur);
	}
	
	public List<Article> affichageParMotsCles(String motCle) throws BusinessException {
		BusinessException businessException = new BusinessException();

		return this.accueilDAO.affichageParMotsCles(motCle);
	}

	private void validerPseudo(String pseudo, BusinessException businessException) {
		if(pseudo==null || pseudo.trim().length()>30)
		{
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_PSEUDO_ERREUR);
			System.out.println("J'ai une exception pseudo");
		}
		
	}
	private void validerNom(String nom, BusinessException businessException) {
		if(nom==null || nom.trim().length()>30)
		{
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_NOM_ERREUR);
			System.out.println("J'ai une exception nom");
		}
	}
	private void validerPrenom(String prenom, BusinessException businessException) {
		if(prenom==null || prenom.trim().length()>30)
		{
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_PRENOM_ERREUR);
			System.out.println("J'ai une exception prenom");
		}
	}
	private void validerEmail(String email, BusinessException businessException) {
		if(email==null || email.trim().length()>50)
		{
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_EMAIL_ERREUR);
			System.out.println("J'ai une exception email");
		}
	}
	private void validerTelephone(String telephone, BusinessException businessException) {
		/*Le téléphone n'est pas obligatoire, il peut être null*/
		if(telephone.trim().length()>15)
		{
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_TELEPHONE_ERREUR);
			System.out.println("J'ai une exception tel");
		}
	}
	private void validerRue(String rue, BusinessException businessException) {
		if(rue==null || rue.trim().length()>30)
		{
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_RUE_ERREUR);
			System.out.println("J'ai une exception rue");
		}
	}
	private void validerCp(String cp, BusinessException businessException) {
		if(cp==null || cp.trim().length()>10)
		{
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_CP_ERREUR);
			System.out.println("J'ai une exception cp");
		}
	}
	private void validerVille(String ville, BusinessException businessException) {
		if(ville==null || ville.trim().length()>30)
		{
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_VILLE_ERREUR);
			System.out.println("J'ai une exception ville");
		}
	}
	private void validerMotDePasse(String motDePasse, String motDePasseConfirm, BusinessException businessException) {
		/*Test si le mdp et le mdp de confirmation sont identiques*/
		if(!motDePasse.equals(motDePasseConfirm))
		{
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_MOT_DE_PASSE_PAS_IDENTIQUE_ERREUR);
			System.out.println("J'ai une exception mdp identique");
		}
		if(motDePasse==null || motDePasse.trim().length()>30)
		{
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_MOT_DE_PASSE_ERREUR);
			System.out.println("J'ai une exception mdp");
		}
		
	}
	
	public Utilisateur testConnexion(String pseudo, String motDePasse) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.validerPseudo(pseudo, businessException);
		return this.utilisateurDAO.testConnexion(pseudo, motDePasse);
	}
	
	public Utilisateur selectUtilisateurById(int noUtilisateur) throws BusinessException{
		BusinessException businessException = new BusinessException();
		return this.utilisateurDAO.selectUtilisateurById(noUtilisateur);
	}

	/** 
	 * @{inheritDoc}
	 * @see org.encheres.bll.UtilisateurBLL#mesEncheresOuvertes(int)
	 */
	@Override
	public List<Article> mesEncheresOuvertes(int idUtilisateur) throws BusinessException {
		return this.accueilDAO.mesEncheresOuvertes(idUtilisateur);
	}

	@Override
	public List<Article> mesEncheresRemportees(int idUtilisateur) throws BusinessException {
		return this.accueilDAO.mesEncheresRemportees(idUtilisateur);
	}

	/*
	 * @{inherite code}
	 * @see org.encheres.bll.UtilisateurBLL#affichageDesVentesEnCours(int)
	 */
	@Override
	public List<Article> affichageDesVentesEnCours(int no_utilisateur) throws BusinessException {
		return this.accueilDAO.mesVentesEnCours(no_utilisateur);
		
	}

	@Override
	public List<Article> affichageMesVentesTerminees(int no_utilisateur) throws BusinessException {
		return this.accueilDAO.mesVentesTerminees(no_utilisateur);
	}

	/**
	 *@(inheritDoc) 
	 * @see org.encheres.bll.UtilisateurBLL#afficherParCategorie(java.lang.String)
	 */
	
	
	
	
}
