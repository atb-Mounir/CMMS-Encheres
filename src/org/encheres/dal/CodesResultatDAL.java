package org.encheres.dal;

/**
 * Codes de gestion des erreurs de la DAL compris entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	/**
	 * Echec général quand tentative d'insérer un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	public static final int INSERT_UTILISATEUR_NULL=10001;
	public static final int INSERT_UTILISATEUR_ECHEC=10002;
	public static final int TEST_CONNEXION_NULL=10003;
	public static final int SELECT_UTILISATEUR_BY_ID_NULL=10004;
	public static final int MODIFIER_UTILISATEUR_NULL=10005;
	public static final int DELETE_UTILISATEUR_NULL=10006;
	public static final int DELETE_UTILISATEUR_ECHEC=10007;
	public static final int SELECT_UTILISATEUR_BY_PSEUDO_NULL=10008;
	public static final int MES_VENTES_NON_DEBUTEES=10009;
	public static final int INSERT_ENCHER_NULL=10010;
	public static final int DELETE_NOUVELLE_VENTE=10011;


	/**
	 * Echec de lecture depuis la BDD de la liste des Catégories 
	 */
	public static final int LECTURE_CATEGORIES_ECHEC=10001;

}
