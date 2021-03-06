package org.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.encheres.BusinessException;
import org.encheres.bll.BLLFactory;
import org.encheres.bll.UtilisateurBLL;
import org.encheres.bo.Article;

/**
 * Servlet implementation class accueil
 */
@WebServlet(description = "page d'accueil du site d'encheres", urlPatterns = { "/Accueil" })
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int idUtilisateur;
	private String titre = "ENI-Enchères";  
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Méthode pour rediriger vers la servlet d'accueil
     * Methode en charge de récupérer la liste des énumérations pour l'utiliser dans la jsp
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */    
    public void redirection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    		try {
				request.setAttribute("categories", BLLFactory.getCategoriesLL().listeCategories());
			} catch (BusinessException e) {
	    		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
				rd.forward(request, response);
			}
    		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
			rd.forward(request, response);
    }
    
    public void recuperationListes (HttpServletRequest request, HttpServletResponse response) throws BusinessException {
		
    	HttpSession session = request.getSession();
    	if(session.getAttribute("etatConnexion")!= null) {
    		idUtilisateur=(int)session.getAttribute("etatConnexion");
    	}
		
    	request.setAttribute("listeAccueil", BLLFactory.getUtilisateurBLL().toutesLesEncheresOuvertes());

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("je suis dans le doGet de la servelt accueil");
		System.out.println("1");
		try {
			System.out.println("2");
			recuperationListes(request,response);
			System.out.println("3");
			System.out.println("la methode de recuperation de listes est bien utilisée");
		} catch (BusinessException e) {
			System.out.println("erreur d'affichage liste d'articles");
			e.printStackTrace();
		}
		UtilisateurBLL utilisateurBLL=BLLFactory.getUtilisateurBLL();
		List<Article> listeArticles=null;
		
		redirection(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("je suis dans le doPost de la servelt accueil");
		List <Article> listeAccueil=new ArrayList<>();
		List<Article> listeArticleAAjouter=new ArrayList<>();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("etatConnexion")!=null) {
		int idUtilisateur=(int)session.getAttribute("etatConnexion");
		}
		try {
			/*Si categorie est selectionné*/
			if(request.getParameter("categorie")!=null) {
				System.out.println("categorie");
				listeArticleAAjouter.clear();
				/*Récupération de toute la liste par categorie*/
				List<Article> listeCategorie=affichageParCategorie(request.getParameter("categorie"), request, response);
				System.out.println(request.getParameter("categorie"));
				if(listeAccueil.size()==0) {
				/*Si il n'y a pas d'autres champs selectionnés, on affiche tout*/
				listeAccueil.addAll(listeCategorie);
				}
				else {
					for(Article articleAccueil : listeAccueil) {
						for(Article articleCategorie : listeCategorie) {
							/*Si plusieurs champs sont selectionnés, on affiche seulement ceux en commun*/
							if(articleAccueil.getNoArticle()==articleCategorie.getNoArticle()) {
								listeArticleAAjouter.add(articleCategorie);
							}
						}
					}
					listeAccueil.addAll(listeArticleAAjouter);
				}
			}
			
			if(request.getParameter("motsCles")!=null && request.getParameter("motsCles").length()>0) {
				System.out.println("motsCles");
				System.out.println("nombre :"+request.getParameter("motsCles").length());
				System.out.println(request.getParameter("motsCles"));
				listeArticleAAjouter.clear();
				List<Article> listeMotsCles=affichageParMotsCles(request.getParameter("motsCles"), request, response);
				if(listeAccueil.size()==0) {
				listeAccueil.addAll(listeMotsCles);
				}
				else {
					for(Article articleAccueil : listeAccueil) {
						for(Article articleMotsCles : listeMotsCles) {
							if(articleAccueil.getNoArticle()==articleMotsCles.getNoArticle()) {
								
								listeArticleAAjouter.add(articleMotsCles);
							}
						}
					}
					listeAccueil.clear();
					listeAccueil.addAll(listeArticleAAjouter);
				}
			}
			
			if(request.getParameter("encheresOuverts")!=null) {
				System.out.println("encheresOuverts");
				listeArticleAAjouter.clear();
				List<Article> listeToutesLesEncheresOuvertes=toutesLesEncheresOuvertes(request, response);
				if(listeAccueil.size()==0) {
				listeAccueil.addAll(listeToutesLesEncheresOuvertes);
				}
				else {
					for(Article articleAccueil : listeAccueil) {
						for(Article articleVentesNonDebutees : listeToutesLesEncheresOuvertes) {
							if(articleAccueil.getNoArticle()==articleVentesNonDebutees.getNoArticle()) {
								
								listeArticleAAjouter.add(articleVentesNonDebutees);
							}
						}
					}
					listeAccueil.clear();
					listeAccueil.addAll(listeArticleAAjouter);
				}
				
			}
			
			if(request.getParameter("mesEncheresEnCours")!=null) {
				System.out.println("mesEncheresEnCours");
				listeArticleAAjouter.clear();
				List<Article> listeMesEncheresEnCours=mesEncheresEnCours(idUtilisateur, request, response);
				if(listeAccueil.size()==0) {
				listeAccueil.addAll(listeMesEncheresEnCours);
				}
				else {
					for(Article articleAccueil : listeAccueil) {
						for(Article articleVentesNonDebutees : listeMesEncheresEnCours) {
							if(articleAccueil.getNoArticle()==articleVentesNonDebutees.getNoArticle()) {
								
								listeArticleAAjouter.add(articleVentesNonDebutees);
							}
						}
					}
					listeAccueil.clear();
					listeAccueil.addAll(listeArticleAAjouter);
				}
				
			}
			
			if(request.getParameter("mesEncheresRemportes")!=null) {
				System.out.println("mesEncheresRemportes");
				listeArticleAAjouter.clear();
				List<Article> listeMesEncheresRemportees=mesEncheresRemportees(idUtilisateur, request, response);
				if(listeAccueil.size()==0) {
				listeAccueil.addAll(listeMesEncheresRemportees);
				}
				else {
					for(Article articleAccueil : listeAccueil) {
						for(Article articleVentesNonDebutees : listeMesEncheresRemportees) {
							if(articleAccueil.getNoArticle()==articleVentesNonDebutees.getNoArticle()) {
								
								listeArticleAAjouter.add(articleVentesNonDebutees);
							}
						}
					}
					listeAccueil.clear();
					listeAccueil.addAll(listeArticleAAjouter);
				}
				
			}
			
			
			
			
			if(request.getParameter("mesVentesEnCours")!=null) {
				System.out.println("mesVentesEnCours");
				listeArticleAAjouter.clear();
				List<Article> listeMesVentesEnCours=mesVentesEnCours(idUtilisateur, request, response);
				if(listeAccueil.size()==0) {
				listeAccueil.addAll(listeMesVentesEnCours);
				}
				else {
					for(Article articleAccueil : listeAccueil) {
						for(Article articleVentesNonDebutees : listeMesVentesEnCours) {
							if(articleAccueil.getNoArticle()==articleVentesNonDebutees.getNoArticle()) {
								
								listeArticleAAjouter.add(articleVentesNonDebutees);
							}
						}
					}
					listeAccueil.clear();
					listeAccueil.addAll(listeArticleAAjouter);
				}
				
			}
			
			if(request.getParameter("mesVentesNonDebutees")!=null) {
				System.out.println("mesVentesNonDebutees");
				listeArticleAAjouter.clear();
				List<Article> listeVentesNonDebutees=mesVentesNonDebutees(idUtilisateur, request, response);
				if(listeAccueil.size()==0) {
				listeAccueil.addAll(listeVentesNonDebutees);
				}
				else {
					for(Article articleAccueil : listeAccueil) {
						for(Article articleVentesNonDebutees : listeVentesNonDebutees) {
							if(articleAccueil.getNoArticle()==articleVentesNonDebutees.getNoArticle()) {
								
								listeArticleAAjouter.add(articleVentesNonDebutees);
							}
						}
					}
					listeAccueil.clear();
					listeAccueil.addAll(listeArticleAAjouter);
				}
				
			}
			
			
			
			if(request.getParameter("mesVentesTerminees")!=null) {
				System.out.println("mesVentesTerminees");
				listeArticleAAjouter.clear();
				List<Article> listeMesVentesTerminees=mesVentesTerminees(idUtilisateur, request, response);
				if(listeAccueil.size()==0) {
				listeAccueil.addAll(listeMesVentesTerminees);
				}
				else {
					for(Article articleAccueil : listeAccueil) {
						for(Article articleVentesNonDebutees : listeMesVentesTerminees) {
							if(articleAccueil.getNoArticle()==articleVentesNonDebutees.getNoArticle()) {
								
								listeArticleAAjouter.add(articleVentesNonDebutees);
							}
						}
					}
					listeAccueil.clear();
					listeAccueil.addAll(listeArticleAAjouter);
				}
				
			}
			
			} catch (BusinessException e) {
			
			System.out.println("une erreur s'est produite dans le choix du formulaire");
			e.printStackTrace();
		}
		request.setAttribute("listeAccueil", listeAccueil);
		redirection(request,response);
	}


	private List<Article> affichageParMotsCles(String motsCles, HttpServletRequest request, HttpServletResponse response) throws BusinessException, ServletException, IOException {
		UtilisateurBLL utilisateurBLL=BLLFactory.getUtilisateurBLL();
		return utilisateurBLL.affichageParMotsCles(motsCles);
	}


	private List<Article> affichageParCategorie(String libelleCategorie, HttpServletRequest request, HttpServletResponse response) throws BusinessException, 
	ServletException, IOException {
		UtilisateurBLL utilisateurBLL=BLLFactory.getUtilisateurBLL();
		return utilisateurBLL.affichageParCategorie(libelleCategorie);

	}
	
	private List<Article> mesVentesNonDebutees(int noUtilisateur, HttpServletRequest request, HttpServletResponse response) throws BusinessException, 
	ServletException, IOException {
		UtilisateurBLL utilisateurBLL=BLLFactory.getUtilisateurBLL();
		return utilisateurBLL.mesVentesNonDebutees(noUtilisateur);

	}
	
	private List<Article> mesVentesEnCours(int noUtilisateur, HttpServletRequest request, HttpServletResponse response) throws BusinessException, 
	ServletException, IOException {
		UtilisateurBLL utilisateurBLL=BLLFactory.getUtilisateurBLL();
		return utilisateurBLL.affichageDesVentesEnCours(noUtilisateur);

	}
	
	private List<Article> mesVentesTerminees(int noUtilisateur, HttpServletRequest request, HttpServletResponse response) throws BusinessException, 
	ServletException, IOException {
		UtilisateurBLL utilisateurBLL=BLLFactory.getUtilisateurBLL();
		return utilisateurBLL.affichageMesVentesTerminees(noUtilisateur);

	}
	
	private List<Article> mesEncheresEnCours(int noUtilisateur, HttpServletRequest request, HttpServletResponse response) throws BusinessException, 
	ServletException, IOException {
		UtilisateurBLL utilisateurBLL=BLLFactory.getUtilisateurBLL();
		return utilisateurBLL.mesEncheresOuvertes(noUtilisateur);

	}
	
	private List<Article> mesEncheresRemportees(int noUtilisateur, HttpServletRequest request, HttpServletResponse response) throws BusinessException, 
	ServletException, IOException {
		UtilisateurBLL utilisateurBLL=BLLFactory.getUtilisateurBLL();
		return utilisateurBLL.mesEncheresRemportees(noUtilisateur);

	}

	
	private List<Article> toutesLesEncheresOuvertes (HttpServletRequest request, HttpServletResponse response) 
			throws BusinessException, ServletException, IOException {
		UtilisateurBLL utilisateurBLL=BLLFactory.getUtilisateurBLL();
		return utilisateurBLL.toutesLesEncheresOuvertes();
	}

	

}
