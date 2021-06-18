/**
 * 
 */
package org.encheres;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe en charge
 * @author maitbaha2019
 * @version EncheresCMM - V1.0
 * @date 22 janv. 2020 - 12:28:27
 */
public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;

	private List<Integer> listeCodesErreur;
	/**
	 * Constructeur
	 */
	
	public BusinessException() {
		super();
		this.listeCodesErreur=new ArrayList<>();
		
	}
	
	public void ajouterErreur(int code) {
		if(!this.listeCodesErreur.contains(code)) {
			this.listeCodesErreur.add(code);
		}
	}
	
	public boolean hasErreurs() {
		return this.listeCodesErreur.size()>0;
	}
	
	public List<Integer> getLiseCodesErreur()
	{
		return this.listeCodesErreur;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
