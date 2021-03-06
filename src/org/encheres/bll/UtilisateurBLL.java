/**
 * 
 */
package org.encheres.bll;

import java.util.List;

import org.encheres.BusinessException;
import org.encheres.bo.Article;
import org.encheres.bo.Utilisateur;

/**
 * Classe en charge de
 * 
 * @author cmartin2
 * @versionEncheresCMM - V1.0
 * @date 23 janv. 2020 - 15:57:58.
 */
public interface UtilisateurBLL {
	
	public Article selectArticleById(String no_article);

	public void creerUtilisateur(String pseudo, String nom, String prenom,
			String email, String telephone, String rue, String cp, String ville, String motDePasse,
			String motDePasseConfirm) throws BusinessException;

	public Utilisateur testConnexion(String pseudo, String motDePasse) throws BusinessException;
	
	public Utilisateur selectUtilisateurById(int noUtilisateur) throws BusinessException;
	
	public List<Article> toutesLesEncheresOuvertes() throws BusinessException;
	
	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException;
	
	public List<Article> mesEncheresOuvertes(int idUtilisateur) throws BusinessException;
	
	public List<Article> mesEncheresRemportees (int idUtilisateur) throws BusinessException;
	
	public List<Article> affichageParCategorie(String libelleCategorie) throws BusinessException;
	
	public List<Article> affichageParMotsCles(String motCle) throws BusinessException;
	
	public List<Article> mesVentesNonDebutees(int noUtilisateur) throws BusinessException;
	
	public void modifierUtilisateur(int idUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String cp, String ville, String motDePasseActuel, int credit, String motDePasseNouveauConfirmation) throws BusinessException;
	
	public boolean selectUtilisateurByPseudo(String pseudo) throws BusinessException;
	
	public List<Article> affichageDesVentesEnCours(int no_utilisateur) throws BusinessException;
	
	public List<Article> affichageMesVentesTerminees(int no_utilisateur) throws BusinessException;
	
}
