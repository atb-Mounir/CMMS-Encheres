/**
 * 
 */
package org.encheres.bll;


import java.time.LocalDate;
import java.util.List;


import java.util.List;

import org.encheres.bo.Article;

import org.encheres.BusinessException;
import org.encheres.bo.Article;
import org.encheres.bo.Categorie;
import org.encheres.bo.Enchere;
import org.encheres.bo.Retrait;
import org.encheres.bo.Utilisateur;
import org.encheres.bo.enums.EtatVente;
import org.encheres.dal.ArticleDAO;
import org.encheres.dal.DAOFactory;

import com.sun.org.apache.bcel.internal.generic.CASTORE;

/**
 * Classe en  charge de
 * @author cmartin2
 * @versionEncheresCMM - V1.0
 * @date 22 janv. 2020 - 14:24:09.
 */
public class ArticleManager implements ArticleBLL {

	
	//Déclaration des attributs
	private ArticleDAO articleDAO;
	
	//Constructeur de l'ArticleManager
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	
	@Override
	public Article selectArticleById(int no_article) {
		System.out.println("je suis dans ArticleManager");	
		return this.articleDAO.selectArticleById(no_article);
	}
	


	private void validerNomArticle(String nomArticle, BusinessException businessException) {
		if(nomArticle==null || nomArticle.trim().length()>30)
		{
			businessException.ajouterErreur(CodeResultatBLL.ARTICLE_NOM_ARTICLE_ERREUR);
			System.out.println("J'ai une exception nom de l'article");
		}
	}
		private void validerDescritptionArticle(String descriptionArticle, BusinessException businessException) {
		if(descriptionArticle==null || descriptionArticle.trim().length()>300)
		{
			businessException.ajouterErreur(CodeResultatBLL.ARTICLE_DESCRIPTION_ARTICLE_ERREUR);
			System.out.println("J'ai une exception description de l'article");
		}
	}
	
		private void validerDatesEnchere(LocalDate dateDebut, LocalDate dateFin, BusinessException businessException) {
		if(dateDebut==null || dateDebut.isEqual(dateFin) || dateFin.isBefore(dateDebut))
		{
			businessException.ajouterErreur(CodeResultatBLL.ARTICLE_DATES_ARTICLE_ERREUR);
			System.out.println("J'ai une exception de date sur l'enchère de nouvelle vente");
		}
	}
		private void validerPrixInitial(int prixInitial, BusinessException businessException) {
		if(prixInitial<=0)
		{
			businessException.ajouterErreur(CodeResultatBLL.ARTICLE_PRIX_ARTICLE_ERREUR);
			System.out.println("J'ai une exception sur le prix de l'enchère de nouvelle vente");
		}
	}		
		private void validerRetraitRue(String retraitRue, BusinessException businessException) {
		if(retraitRue==null ||  retraitRue.trim().length()>30)
		{
			businessException.ajouterErreur(CodeResultatBLL.RETRAIT_RUE_ERREUR);
			System.out.println("J'ai une exception sur le lieu de retrait : rue");
		}
	}				
		private void validerRetraitCP(String codePostal, BusinessException businessException) {
		if(codePostal==null ||  codePostal.trim().length()>15)
		{
			businessException.ajouterErreur(CodeResultatBLL.RETRAIT_CP_ERREUR);
			System.out.println("J'ai une exception sur le lieu de retrait : Code Postal");
		}
	}				
		private void validerRetraitVille(String ville, BusinessException businessException) {
		if(ville==null || ville.trim().length()>30)
		{
			businessException.ajouterErreur(CodeResultatBLL.RETRAIT_VILLE_ERREUR);
			System.out.println("J'ai une exception sur le lieu de retrait : Ville");
		}

	}


		public void EnregistrementNouvelleVente(String nomArticle, String description, String noCategorie, String miseAPrixString,
			String dateDebutEnchereString, String dateFinEnchereString, String retraitRue, String retraitCP,
			String retraitVille, int idUtilisateur) {
			// TODO Auto-generated method stub
			BusinessException businessException = new BusinessException();
			this.validerNomArticle(nomArticle, businessException);
			this.validerDescritptionArticle(description, businessException);
			int miseAPrix = Integer.parseInt(miseAPrixString);
			this.validerPrixInitial(miseAPrix, businessException);
			LocalDate dateDebut = LocalDate.parse(dateDebutEnchereString);
			LocalDate dateFin = LocalDate.parse(dateFinEnchereString);
			this.validerDatesEnchere(dateDebut, dateFin, businessException);
			this.validerRetraitRue(retraitRue, businessException);
			this.validerRetraitCP(retraitCP, businessException);
			this.validerRetraitVille(retraitVille, businessException);
					
			System.out.println("Je suis dans l'article Manager les tests d'erreur sont passés");
			//Instanciation des nouveaux objets à insérer
			Article newArticle = new Article();
			newArticle.setCategorie(new Categorie());
			
			newArticle.setUtilisateur(new Utilisateur());
			
			newArticle.setRetrait(new Retrait());
			//attribution des paramètres obtenus de la JSP aux nouveaux objets
			newArticle.setNomArticle(nomArticle);
			newArticle.setDescription(description);
			newArticle.getCategorie().setNoCategorie(Integer.parseInt(noCategorie));
			newArticle.setMiseAPrix(miseAPrix);
			newArticle.setDateDebutEncheres(dateDebut);
			newArticle.setDateFinEncheres(dateFin);
			newArticle.getRetrait().setRueRetrait(retraitRue);
			newArticle.getRetrait().setCpRetrait(retraitCP);
			newArticle.getRetrait().setVilleRetrait(retraitVille);
			newArticle.getUtilisateur().setNoUtilisateur(idUtilisateur);

			try {
				System.out.println("je suis dans le try et j'insert l'objet dans la BDD");
				articleDAO.insertNouvelleVente(newArticle);
				System.out.println("Dans le Test Manager on appelle la méthode de Article DAO");
				
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}

		public void SupprimerCetArticle(Article article) {
				BusinessException businessException = new BusinessException();
				System.out.println("Je suis dans l'article Manager méthode SupprimerCetArticle");
								
				try {
					System.out.println("je suis dans le try de l'article Manager");
					System.out.println("Dans le Test Manager on appelle la méthode de Article DAO");
					articleDAO.supprimerLaVente(article);
					
					
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		

	
}
