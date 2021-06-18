/**
 * 
 */
package org.encheres.bo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe en  charge de
 * @author cmartin2
 * @versionEncheresCMM - V1.0
 * @date 21 janv. 2020 - 15:18:22.
 */
public class Enchere implements Serializable{
	
	private static final long serialVersionUID = -6916032171187434199L;
	private LocalDate dateEnchere;
	private int montantEnchere;
	private Article article;
	private Utilisateur noAcheteur;
	private Utilisateur utilisateur;

	
	/**
	 * Constructeur
	 */
	public Enchere() {

	}
	
	public Enchere(LocalDate dateEnchere,int montantEnchere,Article article) {
		this();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.article = article;
	}
	
	/**
	 * Constructeur
	 * @param dateEnchere
	 * @param montantEnchere
	 */
	public Enchere(LocalDate dateEnchere, int montantEnchere) {
		this();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	
	public Enchere(LocalDate dateEnchere,int montantEnchere,Utilisateur noAcheteur) {
		this();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noAcheteur = noAcheteur;
	}

	/**
	 * Constructeur
	 * @param dateEnchere2
	 * @param montantEnchere2
	 * @param noUtilisateur
	 * @param noArticle
	 */
	public Enchere(LocalDate dateEnchere, int montantEnchere, Utilisateur utilisateur, Article noArticle) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.utilisateur=utilisateur;
		this.article=noArticle;
	}

	/**
	 * Getter pour dateEnchere.
	 * @return the dateEnchere
	 */
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * Getter pour montantEnchere.
	 * @return the montantEnchere
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}

	/**
	 * Getter pour article.
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * Getter pour noAcheteur.
	 * @return the noAcheteur
	 */
	public Utilisateur getNoAcheteur() {
		return noAcheteur;
	}

	/**
	 * Setter de dateEnchere.
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/**
	 * Setter de montantEnchere.
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	/**
	 * Setter de article.
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

	/**
	 * Setter de noAcheteur.
	 * @param noAcheteur the noAcheteur to set
	 */
	public void setNoAcheteur(Utilisateur noAcheteur) {
		this.noAcheteur = noAcheteur;
	}

	/** 
	 * @{inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", article=" + article
				+ ", noAcheteur=" + noAcheteur + "]";
	}

	/**
	 *Getter pour utilisateur
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * Setter pour utilisateur
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * @return
	 */
	


}
