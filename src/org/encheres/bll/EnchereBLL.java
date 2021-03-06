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

/**
 * Classe en charge
 * @author maitbaha2019
 * @version EncheresCMM - V1.0
 * @date 24 janv. 2020 - 15:22:08
 */
public interface EnchereBLL {
	
	public void enregistrerMontantEnch√®re(Utilisateur utilisateur, int noArticle, LocalDate dateEnchere,
			int montantEnchere) throws BusinessException;

}
