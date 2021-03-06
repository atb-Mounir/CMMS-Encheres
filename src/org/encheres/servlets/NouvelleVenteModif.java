package org.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.encheres.bll.ArticleBLL;
import org.encheres.bll.ArticleManager;
import org.encheres.bll.BLLFactory;
import org.encheres.bll.UtilisateurBLL;
import org.encheres.bo.Article;
import org.encheres.bo.Categorie;
import org.encheres.bo.Utilisateur;
import org.encheres.BusinessException;
import org.encheres.bll.BLLFactory;


import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/NouvelleVenteModif")
public class NouvelleVenteModif extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouvelleVenteModif() {
        super();
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
	    		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
				rd.forward(request, response);
			}
    		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
			rd.forward(request, response);
    }
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("je suis dans NouvelleVenteModif en doGet");
		
		
		
		
		
		//COPIE DE DETAIL VENTE.JAVA
		ArticleManager articleManager = BLLFactory.getArticleBLL();
		Integer no_article = Integer.parseInt(request.getParameter("no_article"));
		Article article = articleManager.selectArticleById(no_article);
		request.setAttribute("article", article);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
		rd.forward(request, response);
		System.out.println("numero article récupérer par la Servlet NouvelleVente : " + no_article);
		//Récupération du numéro de l'article afin de le supprimer
//			Article article = null;		
//			String no_article = request.getParameter("no_article");
//				System.out.println(no_article);
//				if(request.getParameter("no_article")!=null) {
//				Integer numArticle = Integer.parseInt(no_article);
//				//Remplir les champs avec le bon article
//				ArticleManager articleManager = BLLFactory.getArticleBLL();
//				article = articleManager.selectArticleById(numArticle);
//				request.setAttribute("article",article);
//				}
//				
				//ACTION DU BOUTON ANNULER LA VENTE	
				System.out.println("je suis dans la servlet NouvelleVenteModif en get : ANNULER LA VENTE");
					try {
						ArticleManager artManager = BLLFactory.getArticleBLL();
						artManager.SupprimerCetArticle(article);
					}catch (Exception e) {
						e.printStackTrace();
						System.out.println("erreur de suppression de l'article");
					}
			redirection(request,response);
		}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("je suis dans la Servlet NouvelleVenteModif en doPost");
		//ACTION DU BOUTON ENREGISTRER
		if (request.getParameter("enregistrer")!= null) {
			System.out.println("je suis dans la Servlet NouvelleVente en Post");
		//Création d'une liste de code d'erreur
			List<Integer> listeCodesErreur = new ArrayList<>();
			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			System.out.println("je suis avant la récupération de catégorie");
			String categorieString = request.getParameter("categorie");
			System.out.println("la categorie choisie est : "+categorieString);
			System.out.println("je suis avant la la récupération du prix initial");
			String miseAPrixString = request.getParameter("miseAPrix");
			String PrixFinInitString = miseAPrixString;
			String dateDebutEnchereString = request.getParameter("dateDebutEnchere");
			String dateFinEnchereString = request.getParameter("dateFinEnchere");
			String retraitRue = request.getParameter("retraitRue");
			String retraitCP = request.getParameter("retraitCP");
			String retraitVille = request.getParameter("retraitVille");
			System.out.println("j'ai fini les getParameter dans la Servlet");
		//RECUPERATION DE l'ID DE L'UTILISATEUR DE SESSION grâce à la Servlet MonProfil
			int idUtilisateur = ((Utilisateur) request.getSession().getAttribute("newUtilisateur")).getNoUtilisateur();
			System.out.println("tous les paramètres sont enregistrés dans des variables et parsées");
			System.out.println("j'utilise la BLLFactory pour aller sur l'article Manager");
			ArticleManager articleManager = BLLFactory.getArticleMBLL();
		try {
			articleManager.EnregistrementNouvelleVente(nomArticle, description, categorieString, miseAPrixString, 
					dateDebutEnchereString, dateFinEnchereString, retraitRue, retraitCP, retraitVille, idUtilisateur);
		}catch (Exception e) {
			e.printStackTrace();
			listeCodesErreur.add((CodesResultatServlets.ENVOI_NOUVELLE_VENTE_A_ARTICLE_MANAGER_ERREUR));
		}
		RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
		rd.forward(request, response);
		}
			
		//ACTION DU BOUTON ANNULER
		if (request.getParameter("annuler")!= null) {
			RequestDispatcher rd = request.getRequestDispatcher("/NouvelleVente");
			rd.forward(request, response);
			}
		
		//COPIE DETAIL VENTE EN POST
		//Recuperation du Numero Utilisateur par la session
				ArticleManager artManager = BLLFactory.getArticleBLL();
				HttpSession session = request.getSession();
				Utilisateur utilisateur= (Utilisateur) session.getAttribute("newUtilisateur");
				int noUtilisateur=utilisateur.getNoUtilisateur();
				//Recuperation du numero de l'article envoyé par la JSP
				Integer noArticle =Integer.parseInt(request.getParameter("no_article"));
				//Création du Manager
				//ACTION DU BOUTON ANNULER LA VENTE	
				
				if(request.getParameter("annulerVente")!=null) {
				System.out.println("je suis dans la servlet en post : ANNULER LA VENTE");
					try {
						Article article = artManager.selectArticleById(noArticle);
						request.setAttribute("article",article);
						artManager.SupprimerCetArticle(article);
					}catch (Exception e) {
						e.printStackTrace();
						System.out.println("erreur de suppression de l'article");
					}
				}		
		

		
		
		
		
//		//REDIRECTION DEPUIS LA PAGE ACCUEIL POUR MODIFIER LA VENTE : affichage de l'article déja mis en vente avec des champs remplis.
//		System.out.println("je suis en doPost");
//		//Récupération du numéro de l'article afin de le supprimer
//		Article article = null;		
//		String no_article = request.getParameter("no_article");
//			System.out.println(no_article);
//			if(request.getParameter("no_article")!=null) {
//			Integer numArticle = Integer.parseInt(no_article);
//			//Remplir les champs avec le bon article
//			ArticleManager articleManager = BLLFactory.getArticleBLL();
//			article = articleManager.selectArticleById(numArticle);
//			request.setAttribute("article",article);
//			}
//			//ACTION DU BOUTON ANNULER LA VENTE	
//			if(request.getParameter("annulerVente")!=null) {
//			System.out.println("je suis dans la servlet en post : ANNULER LA VENTE");
//				try {
//					ArticleManager artManager = BLLFactory.getArticleBLL();
//					artManager.SupprimerCetArticle(article);
//				}catch (Exception e) {
//					e.printStackTrace();
//					System.out.println("erreur de suppression de l'article");
//				}
//			}							
				//A LA FIN : REDIRECTION VERS L'ACCUEIL		
		RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
		rd.forward(request, response);
		
				
		
	}
}	
		

