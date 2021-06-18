package org.encheres.dal;

import java.util.List;

import org.encheres.BusinessException;
import org.encheres.bo.Categorie;

public interface CategorieDAO {
	public List<Categorie> getCategories() throws BusinessException;
}
