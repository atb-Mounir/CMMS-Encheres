/**
 * 
 */
package org.encheres.bll;
import org.encheres.bo.Article;

/**
 * Classe en charge
 * @author maitbaha2019
 * @version EncheresCMM - V1.0
 * @date 23 janv. 2020 - 09:46:42
 */
public interface ArticleBLL {

	public Article selectArticleById(int no_article);
}
