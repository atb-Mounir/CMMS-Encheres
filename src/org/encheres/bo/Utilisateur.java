/**
 * 
 */
package org.encheres.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe en charge de créer des utilisateurs, acheteurs et vendeurs
 * @author mdelauna2
 * @version EncheresCMM - V1.0
 * @date 21 janv. 2020 - 14:40:43
 */
public class Utilisateur implements Serializable{

	private static final long serialVersionUID = -2449897927176644006L;
	
	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone=null;
	private String rueUtilisateur;
	private String cpUtilisateur;
	private String villeUtilisateur;
	private String motDePasse;
	private int credit;
	private boolean administrateur;
	
	private List<Article> listeArticlesAchat;
	private List<Article> listeArticleVente;
	private List<Enchere> listeEncheres;
	
	
	public Utilisateur() {
		super();
	}
	
	/*Constructeur sans le numéro de téléphone */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String rueUtilisateur,
			String cpUtilisateur, String villeUtilisateur, String motDePasse, int credit, boolean administrateur) {
		super();
		
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.rueUtilisateur = rueUtilisateur;
		this.cpUtilisateur = cpUtilisateur;
		this.villeUtilisateur = villeUtilisateur;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.listeArticlesAchat = new ArrayList<>();
		this.listeArticleVente = new ArrayList<>();
		this.listeEncheres = new ArrayList<>();
	}
	
	/*Constructeur avec tous les paramètres */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rueUtilisateur, String cpUtilisateur, String villeUtilisateur, String motDePasse, int credit,
			boolean administrateur) {
		
		this(pseudo, nom, prenom, email, rueUtilisateur, cpUtilisateur, villeUtilisateur, motDePasse, 
				credit, administrateur);
		this.telephone = telephone;
		
	}
	/*Constructeur avec juste le noUtilisateur*/
	public Utilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	/*Constructeur avec tous les paramètres et l'id utilisateur*/
	public Utilisateur(int noUtilisateur,String pseudo, String nom, String prenom, String email, String telephone,
			String rueUtilisateur, String cpUtilisateur, String villeUtilisateur, String motDePasse, int credit,
			boolean administrateur) {
		
		this(pseudo, nom, prenom, email, rueUtilisateur, cpUtilisateur, villeUtilisateur, motDePasse, 
				credit, administrateur);
		this.telephone = telephone;
		this.noUtilisateur = noUtilisateur;
	}


	/**
	 * Constructeur
	 * @param string
	 * @param string2
	 */
	public Utilisateur(String pseudo, String telephone,int credit) {
		super();
		this.pseudo = pseudo;
		this.telephone = telephone;
		this.credit= credit;
	}

	/**
	 * Constructeur
	 * @param string
	 * @param string2
	 * @param int1
	 */
	

	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getRueUtilisateur() {
		return rueUtilisateur;
	}
	public void setRueUtilisateur(String rueUtilisateur) {
		this.rueUtilisateur = rueUtilisateur;
	}
	
	public String getCpUtilisateur() {
		return cpUtilisateur;
	}
	public void setCpUtilisateur(String cpUtilisateur) {
		this.cpUtilisateur = cpUtilisateur;
	}
	public String getVilleUtilisateur() {
		return villeUtilisateur;
	}
	public void setVilleUtilisateur(String villeUtilisateur) {
		this.villeUtilisateur = villeUtilisateur;
	}
	
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public boolean isAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}
	
	public List<Article> getListeArticlesAchat() {
		return listeArticlesAchat;
	}
	public void setListeArticlesAchat(List<Article> listeArticlesAchat) {
		this.listeArticlesAchat = listeArticlesAchat;
	}
	
	public List<Article> getListeArticleVente() {
		return listeArticleVente;
	}
	public void setListeArticleVente(List<Article> listeArticleVente) {
		this.listeArticleVente = listeArticleVente;
	}
	
	public List<Enchere> getListeEncheres() {
		return listeEncheres;
	}
	public void setListeEncheres(List<Enchere> listeEncheres) {
		this.listeEncheres = listeEncheres;
	}
	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rueUtilisateur=" + rueUtilisateur
				+ ", cpUtilisateur=" + cpUtilisateur + ", villeUtilisateur=" + villeUtilisateur + ", motDePasse="
				+ motDePasse + ", credit=" + credit + ", administrateur=" + administrateur + "]";
	}
	
	
	
}
