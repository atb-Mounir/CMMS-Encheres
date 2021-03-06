package org.encheres.servlets;

/**
 * Les codes disponibles sont entre 30000 et 39999
 */

public abstract class CodesResultatServlets {
	/**
	 * Format NouvelleVente.date incorrect
	 */
	public static final int FORMAT_NOUVELLE_VENTE_DATE_ERREUR=30000;
	
	/**
	 * Format NouvelleVente.categorie incorrect
	 */
	public static final int FORMAT_NOUVELLE_VENTE_CATEGORIE_ERREUR=30001;
	
	/**
	 * Format NouvelleVente.mise à prix incorrect
	 */
	public static final int FORMAT_NOUVELLE_VENTE_MISE_A_PRIX_ERREUR=30002;
		
	/**
	 * Problème d'envoi des données de la Nouvelle vente à la BLL (articleManager)
	 */
	public static final int ENVOI_NOUVELLE_VENTE_A_ARTICLE_MANAGER_ERREUR=30002;
	
	public static final int PSEUDO_OBLIGATOIRE=30003;
	
	public static final int MOT_DE_PASSE_OBLIGATOIRE=30004;
	
	public static final int MOT_DE_PASSE_IDENTIFIANT_INCORRECT=30005;
	
	public static final int MOT_DE_PASSE_OBLIGATOIRE_CREATION=30006;
	
	public static final int MOT_DE_PASSE__CONFIRMATION_NON_IDENTIQUE_CREATION=30007;
	
	public static final int PSEUDO_DEJA_EXISTANT=30008;
	
	public static final int MONTANT_ENCHERE_INCCORECT=30009;
	
	public static final int CREDIT_INSUFFISANT=300010;
	
	public static final int MONTANT_ENCHERE_INF_MISE_A_PRIX=300011;
	
	
	
	
	
}
