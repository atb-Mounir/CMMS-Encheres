package org.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.encheres.BusinessException;
import org.encheres.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO 
{
	private static final String	SELECT_ALL = "SELECT no_categorie, libelle FROM categories";
	private List<Categorie> listeCategories;
	
	@Override
	public List<Categorie> getCategories() throws BusinessException {
		listeCategories = new ArrayList<Categorie>();
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt =cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			Categorie categorieLue = new Categorie();
			while(rs.next())
			{
				categorieLue = categorieBuilder(rs);
				listeCategories.add(categorieLue);
			}
		} catch (SQLException e) {
		e.printStackTrace();
		BusinessException businessException = new BusinessException();
		businessException.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIES_ECHEC);
		throw businessException;
		}
		System.out.println("j'affiche les catégories");
		return listeCategories;
	}
/*
 * Méthode qui construit l'objet Catégorie à partir de la BDD
 * @ ResultSet : qui recupère le no_categorie puis le libelle 
 */
	private Categorie categorieBuilder(ResultSet rs) throws SQLException{
		Categorie categorieLue = new Categorie();
		categorieLue.setNoCategorie(rs.getInt(1));
		categorieLue.setLibelleCategorie(rs.getString(2));
		return categorieLue;
				
	}
	
}
