/**
 * 
 */
package org.encheres.dal;


public class DAOFactory {
	
	public static EnchereDAO getEnchereDAO() {
		return  new EnchereDAOJdbcImpl();
	}
	
	
	public static ArticleDAO getArticleDAO()
	{
		System.out.println("je suis dans DAOFactory");
		return new ArticleDAOJdbcImpl();
	}
	
	
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static CategorieDAO getCategories()
	{
		return new CategorieDAOJdbcImpl();
	}
	
	public static AccueilDAO getAccueilDAO() {
		return new AccueilDAOJdbcImpl();
	}
	
	
}
