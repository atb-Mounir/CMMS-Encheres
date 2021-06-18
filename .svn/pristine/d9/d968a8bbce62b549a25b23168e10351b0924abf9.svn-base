/**
 * 
 */
package org.encheres.bll;

import java.sql.Date;
import java.time.LocalDate;

import org.encheres.BusinessException;
import org.encheres.bo.Article;
import org.encheres.bo.Enchere;
import org.encheres.bo.Utilisateur;
import org.encheres.dal.DAOFactory;
import org.encheres.dal.EnchereDAO;

/**
 * Classe en charge
 * @author maitbaha2019
 * @version EncheresCMM - V1.0
 * @date 24 janv. 2020 - 15:21:09
 */
public class EnchereManager implements EnchereBLL {
	
	private EnchereDAO enchereDAO;

	/**
	 * Constructeur
	 */
	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}

	/*
	 * @{inherite code}
	 * @see org.encheres.bll.EnchereBLL#insertEnchere(org.encheres.bo.Utilisateur, org.encheres.bo.Article, org.encheres.bo.Enchere, org.encheres.bo.Enchere)
	 */
	@Override
	public void enregistrerMontantEnchère(Utilisateur utilisateur, int noArticle, LocalDate dateEnchere,
			int montantEnchere) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		this.validerMontantEnchere(montantEnchere, businessException);
		
		
		Article article = new Article ();
		article.setNoArticle(noArticle);
		
		Enchere enchere = new Enchere(dateEnchere,montantEnchere,utilisateur,article);
		this.enchereDAO.insertEnchere(enchere);
		System.out.println("Je suis dans la methode enrigistrerMontantEnchere de EnchereManager");
		}
		
		
	
	
	private void validerMontantEnchere(int montantEnchere, BusinessException businessException) {
		if(montantEnchere==0) {
			businessException.ajouterErreur(CodeResultatBLL.ENCHERE_MONTANT_ERREUR);
			System.out.println("J'ai une exception Montant_Enchere");
			
		}
	}

	/**
	 * @param montantEnchere
	 * @return
	 */
	
}
