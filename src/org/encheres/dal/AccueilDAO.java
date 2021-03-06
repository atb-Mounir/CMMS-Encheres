/**
 * 
 */
package org.encheres.dal;

import java.util.List;

import org.encheres.BusinessException;
import org.encheres.bo.Article;


public interface AccueilDAO {
	public List<Article> toutesLesEncheresOuvertes() throws BusinessException;
	public List<Article> mesEncheresOuvertes(int idUtilisateur) throws BusinessException;
	public List<Article> mesEncheresRemportees(int idUtilisateur) throws BusinessException;
	public List<Article> affichageParCategorie(String libelleCategorie) throws BusinessException;
	public List<Article> affichageParMotsCles(String motCle) throws BusinessException;
	public List<Article> mesVentesNonDebutees(int noUtilisateur) throws BusinessException;
	/**
	 * Methode en charge de 
	 * @return
	 * @throws BusinessException
	 */
	/**
	 * @param no_utilisateur
	 * @return
	 * @throws BusinessException
	 */
	List<Article> mesVentesEnCours(int no_utilisateur) throws BusinessException;
	List<Article> mesVentesTerminees(int no_utilisateur) throws BusinessException;
}
