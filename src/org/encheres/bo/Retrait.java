/**
 * 
 */
package org.encheres.bo;

import java.io.Serializable;

/**
 * Classe en  charge de
 * @author cmartin2
 * @versionEncheresCMM - V1.0
 * @date 21 janv. 2020 - 14:41:45.
 */
public class Retrait implements Serializable {
	
	private static final long serialVersionUID = 6568217870249747338L;
	private String rueRetrait;
	private String cpRetrait;
	private String villeRetrait;
	private Article article;

	/**
	 * Constructeur
	 */
	public Retrait() {
	}
	
	/**
	 * Constructeur
	 * @param string
	 * @param string2
	 * @param string3
	 */
	public Retrait(String rueRetrait, String cpRetrait, String villeRetrait) {
		this.rueRetrait = rueRetrait;
		this.cpRetrait = cpRetrait;
		this.villeRetrait = villeRetrait;
	}

	/**
	 * Constructeur
	 * @param string
	 * @param string2
	 * @param string3
	 */

	public void Retrait(String rueRetrait,String cpRetrait, String villeRetrait, Article article) {
		this.rueRetrait = rueRetrait;
		this.cpRetrait = cpRetrait;
		this.villeRetrait = villeRetrait;
		this.article = article;
		
	}
	
	public void Retrait(String rueRetrait,String cpRetrait, String villeRetrait) {
		
		
	}

	/**
	 * Getter pour rueRetrait.
	 * @return the rueRetrait
	 */
	public String getRueRetrait() {
		return rueRetrait;
	}

	/**
	 * Getter pour cpRetrait.
	 * @return the cpRetrait
	 */
	public String getCpRetrait() {
		return cpRetrait;
	}

	/**
	 * Getter pour villeRetrait.
	 * @return the villeRetrait
	 */
	public String getVilleRetrait() {
		return villeRetrait;
	}

	/**
	 * Getter pour article.
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * Setter de rueRetrait.
	 * @param rueRetrait the rueRetrait to set
	 */
	public void setRueRetrait(String rueRetrait) {
		this.rueRetrait = rueRetrait;
	}

	/**
	 * Setter de cpRetrait.
	 * @param cpRetrait the cpRetrait to set
	 */
	public void setCpRetrait(String cpRetrait) {
		this.cpRetrait = cpRetrait;
	}

	/**
	 * Setter de villeRetrait.
	 * @param villeRetrait the villeRetrait to set
	 */
	public void setVilleRetrait(String villeRetrait) {
		this.villeRetrait = villeRetrait;
	}

	/**
	 * Setter de article.
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

	/** 
	 * @{inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Retrait [rueRetrait=" + rueRetrait + ", cpRetrait=" + cpRetrait + ", villeRetrait=" + villeRetrait
				+ "]";
	}
	
	

}
