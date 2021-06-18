/**
 * 
 */
package org.encheres.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe en  charge de créer des catégories pour répertorier les articles par catégories
 * @author cmartin2
 * @versionEncheresCMM - V1.0
 * @date 21 janv. 2020 - 14:51:12.
 */
public class Categorie {
	
	private int noCategorie;
	private String libelleCategorie;
	private List<String>listeCategories;

	/**
	 * Constructeur
	 */
	public Categorie() {
		listeCategories=new ArrayList<String>();
	}
	
	public Categorie(String libelleCategorie) {
		this();
		this.libelleCategorie = libelleCategorie;
	}
	
	public Categorie(int noCategorie, String libelleCategorie) {
		this();
		this.noCategorie = noCategorie;
	}
	
	
	/**
	 * Méthode pour générer la liste de catégories
	 */
	public List ListCategories(String libelleCategorie) {
		listeCategories.add(libelleCategorie);
		return listeCategories;
	}
	
	/**
	 * Getter pour libelleCategorie.
	 * @return the libelleCategorie
	 */
	public String getLibelleCategorie() {
		return libelleCategorie;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	/**
	 * Setter de libelleCategorie.
	 * @param libelleCategorie the libelleCategorie to set
	 */
	public void setLibelleCategorie(String libelleCategorie) {
		this.libelleCategorie = libelleCategorie;
	}

	/** 
	 * @{inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelleCategorie=" + libelleCategorie + ", listeCategories="
				+ listeCategories + "]";
	}
	
	
	
}
