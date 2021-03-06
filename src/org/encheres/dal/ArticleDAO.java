/**
 * 
 */
package org.encheres.dal;

import org.encheres.bo.Article;
import org.encheres.bo.Enchere;
import org.encheres.bo.Retrait;
import org.encheres.bo.Utilisateur;

import java.util.List;

import org.encheres.BusinessException;

/**
 * Classe en charge de
 * @author mdelauna2
 * @version EncheresCMM - V1.0
 * @date 22 janv. 2020 - 12:21:58
 */
public interface ArticleDAO {
	public void insertRetrait (Retrait retrait) throws BusinessException;
	public Article selectArticleById(int no_article);
	public void insertNouvelleVente(Article newArticle) throws BusinessException;
	public void supprimerLaVente(Article article) throws BusinessException;


}
