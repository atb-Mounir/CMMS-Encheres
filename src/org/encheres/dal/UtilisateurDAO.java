/**
 * 
 */
package org.encheres.dal;

import java.util.List;

import org.encheres.BusinessException;
import org.encheres.bo.Article;
import org.encheres.bo.Utilisateur;

/**
 * Classe en charge de
 * 
 * @author mdelauna2
 * @version EncheresCMM - V1.0
 * @date 22 janv. 2020 - 12:22:09
 */
public interface UtilisateurDAO {

	public void creerUtilisateur(Utilisateur utilisateur) throws BusinessException;

	public List<Article> byNomArticle() throws BusinessException;

	public List<Article> byCategorie() throws BusinessException;

	public List<Article> mesEncheresEnCours() throws BusinessException;

	public List<Article> mesEncheresRemportees() throws BusinessException;

	public List<Article> mesVentesEnCours() throws BusinessException;

	public List<Article> mesVentesNonDebutees() throws BusinessException;

	public List<Article> mesVentesTerminees() throws BusinessException;
	
	public Utilisateur testConnexion(String pseudo, String motDePasse) throws BusinessException;
	
	public Utilisateur selectUtilisateurById(int noUtilisateur) throws BusinessException;
	
	public boolean selectUtilisateurByPseudo(String pseudo) throws BusinessException;
	
	public void modifierUtilisateur(Utilisateur utilisateurString) throws BusinessException;
	
	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException;

	/**
	 * @param no_article
	 * @return
	 */
	public Article selectArticleById(String no_article);

}
