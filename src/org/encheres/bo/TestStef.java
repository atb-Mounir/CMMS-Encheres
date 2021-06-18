package org.encheres.bo;

public class TestStef {

	private String nomArticle;

	public TestStef() {
		
	}
	
	public TestStef(String nomArticle) {
		super();
		this.nomArticle = nomArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	@Override
	public String toString() {
		return "TestStef [nomArticle=" + nomArticle + "]";
	}
	
	
}
