package org.encheres.bll;

import java.util.List;

import org.encheres.BusinessException;
import org.encheres.bo.Categorie;
import org.encheres.dal.CategorieDAOJdbcImpl;

public class CategorieManager extends CategorieBLL{

	private CategorieDAOJdbcImpl categorieDAO = new CategorieDAOJdbcImpl();
	
	public List<Categorie> listeCategories() throws BusinessException{
		return this.categorieDAO.getCategories();
	}
	
	
}
