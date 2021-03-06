/**
 * 
 */
package org.encheres.message;

/**
 * Classe en charge
 * @author maitbaha2019
 * @version EncheresCMM - V1.0
 * @date 22 janv. 2020 - 13:12:58
 */
public abstract class CodesResultat {
	
	/**
	 * Les codes disponibles sont entre 20000 et 29999
	 */
	
	/**
	 * Erreur lors que la lecture de la liste ne se fait pas
	 */
	public static int LECTURE_LISTES_ECHEC=2000;

	/**
	 * Erreur lors que la lecture de la liste ne se fait pas
	 */
	public static int LECTURE_LISTES_ECHEC_VENTES_REMPORTEES=2001;

	/**
	 * Erreur lors que la lecture de la liste ne se fait pas
	 */
	public static int LECTURE_LISTES_ECHEC_MES_ENCHERES_OUVERTES=2002;

	
	
	/**
	 * Constructeur
	 */
	public CodesResultat() {
	}

}
