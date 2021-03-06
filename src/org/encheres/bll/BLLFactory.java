/**
 * 
 */
package org.encheres.bll;

/**
 * Classe en  charge de récupérer les informations des différents manager
 * @author cmartin2
 * @versionEncheresCMM - V1.0
 * @date 22 janv. 2020 - 14:22:30.
 */
public class BLLFactory {
	

	public static EnchereManager getEnchereBLL() {
		return new EnchereManager();
	}
	
	public static ArticleManager getArticleBLL()
	{
		System.out.println("je suis dans BLLFactory");
		return new ArticleManager();
	}
	
	public static ArticleManager getArticleMBLL()
	{
		return new ArticleManager();
	}
	
	public static UtilisateurManager getUtilisateurBLL()
	{
		return new UtilisateurManager();
	}

	public static CategorieManager getCategoriesLL() {
		return new CategorieManager();
	}
	
	
}
