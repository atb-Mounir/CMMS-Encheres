/**
 * 
 */
package org.encheres.bo;

import java.time.LocalDate;

import org.apache.tomcat.jni.Local;
import org.encheres.bo.enums.EtatVente;

/**
 * Classe en charge créer des articles
 * @author maitbaha2019
 * @version EncheresCMM - V1.0
 * @date 21 janv. 2020 - 14:47:18
 */
public class Article {
	

	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private EtatVente etatVente;
	private Categorie categorie;
	
	private Retrait retrait;
	private Enchere enchere;
	private Utilisateur utilisateur;
	

	
	/**
	 * Constructeur
	 */
	public Article() {
		}
	
	public Article(String nomArticle) {
		this();
		this.nomArticle = nomArticle;
	}
	/**
	 * Constructeur
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param categorie
	 * @param retrait
	 * @param enchere
	 */
	public Article(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, EtatVente etatVente, Categorie categorie,
			Retrait retrait, Enchere enchere) {
		this(nomArticle);
		this.noArticle = noArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.categorie = categorie;
		this.retrait = retrait;
		this.enchere = enchere;
	}
	

	public Article(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, Categorie categorie,
			Enchere enchere, Utilisateur utilisateur) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.categorie = categorie;
		this.enchere = enchere;
		this.utilisateur = utilisateur;
	}

	public Article(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, Categorie categorie,
			Retrait retrait, Utilisateur utilisateur) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.categorie = categorie;
		this.retrait = retrait;
		this.utilisateur=utilisateur;
	}

	public Article(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, Categorie categorie, Retrait retrait,
			Enchere enchere, Utilisateur utilisateur) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.categorie = categorie;
		this.retrait = retrait;
		this.enchere = enchere;
		this.utilisateur = utilisateur;
	}

	/**
	 *Getter pour noArticle
	 * @return the noArticle
	 */
	public int getNoArticle() {
		return noArticle;
	}

	/**
	 * Setter pour noArticle
	 * @param noArticle the noArticle to set
	 */
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	/**
	 *Getter pour nomArticle
	 * @return the nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}

	/**
	 * Setter pour nomArticle
	 * @param nomArticle the nomArticle to set
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	/**
	 *Getter pour description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter pour description
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 *Getter pour dateDebutEncheres
	 * @return the dateDebutEncheres
	 */
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	/**
	 * Setter pour dateDebutEncheres
	 * @param dateDebutEncheres the dateDebutEncheres to set
	 */
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	/**
	 *Getter pour dateFinEncheres
	 * @return the dateFinEncheres
	 */
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	/**
	 * Setter pour dateFinEncheres
	 * @param dateFinEncheres the dateFinEncheres to set
	 */
	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	/**
	 *Getter pour miseAPrix
	 * @return the miseAPrix
	 */
	public int getMiseAPrix() {
		return miseAPrix;
	}

	/**
	 * Setter pour miseAPrix
	 * @param miseAPrix the miseAPrix to set
	 */
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	/**
	 *Getter pour prixVente
	 * @return the prixVente
	 */
	public int getPrixVente() {
		return prixVente;
	}

	/**
	 * Setter pour prixVente
	 * @param prixVente the prixVente to set
	 */
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	/**
	 *Getter pour etatVente
	 * @return the etatVente
	 */
	public EtatVente getEtatVente() {
		return etatVente;
	}

	/**
	 * Setter pour etatVente
	 * @param etatVente the etatVente to set
	 */
	public void setEtatVente(EtatVente etatVente) {
		this.etatVente = etatVente;
	}

	/**
	 *Getter pour categorie
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * Setter pour categorie
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 *Getter pour retrait
	 * @return the retrait
	 */
	public Retrait getRetrait() {
		return retrait;
	}

	/**
	 * Setter pour retrait
	 * @param retrait the retrait to set
	 */
	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	/**
	 *Getter pour enchere
	 * @return the enchere
	 */
	public Enchere getEnchere() {
		return enchere;
	}

	/**
	 * Setter pour enchere
	 * @param enchere the enchere to set
	 */
	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}

	

	/*
	 * @{inherite code}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Article [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + "]";
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

	
}
