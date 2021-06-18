package org.encheres.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Local;
import org.encheres.BusinessException;
import org.encheres.bll.ArticleManager;
import org.encheres.bll.BLLFactory;
import org.encheres.bo.Article;
import org.encheres.bo.Utilisateur;

/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/NouvelleVente")
public class NouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouvelleVente() {
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
				
				//DEPUIS L'ACCUEIL ON CLIQUE SUR NOUVELLE VENTE
				if (request.getParameter("no_article")==null) {
					System.out.println("je suis dans NouvelleVente en doGet");
					// récupération de mon profil utilisateur et attribution de l'adresse de l'utilisateur dans les champs RETRAIT 
					String nomRue = ((Utilisateur) request.getSession().getAttribute("newUtilisateur")).getRueUtilisateur();
					request.setAttribute("retraitRue", nomRue);
					String codePostal = ((Utilisateur) request.getSession().getAttribute("newUtilisateur")).getCpUtilisateur();
					request.setAttribute("retraitCP", codePostal);
					String nomVille = ((Utilisateur) request.getSession().getAttribute("newUtilisateur")).getVilleUtilisateur();
					request.setAttribute("retraitVille", nomVille);
					// récupération de la date du jour pour la indiquer la date de l'enchère à la date du jour minimum
					SimpleDateFormat formater = null;
					Date today = new Date();
					formater = new SimpleDateFormat("yyyy-MM-dd");
				    System.out.println("la date du jour est : "+formater.format(today));
				   // request.setAttribute("dateDebutEnchere", today);
					System.out.println("Les attributs de l'adresse de l'utilisateur remplissent les champs RETRAIT");
				}
				
				//DEPUIS L'ACCUEIL ON CLIQUE SUR MODIFIER UN ARTICLE				
				if (request.getParameter("no_article")!=null) {
					String noArticle = request.getParameter("no_article");				
					System.out.println("le paramètre String récupéré de l'accueil est l'article numéro :" + noArticle);
					int no_article = Integer.parseInt(noArticle);
					System.out.println("le paramètre Integer parsé récupéré de l'accueil est l'article numéro :" + noArticle);
					ArticleManager articleManager = BLLFactory.getArticleBLL();
					//Récupération de l'article grâce à la BLL
					Article article = articleManager.selectArticleById(no_article);
					//request.setAttribute(arg0, arg1);article.getPrixVente()
					request.setAttribute("article",article);
					request.setAttribute("description",article);
					request.setAttribute("categorie",article);
					request.setAttribute("dateDebutEnchere",article);
					request.setAttribute("categorie",article.getCategorie().getLibelleCategorie());
					String categorie = article.getCategorie().getLibelleCategorie();
				
					System.out.println("le libellé de la categorie est : "+ categorie);
					String rue = article.getRetrait().getRueRetrait();
					System.out.println(rue);
					System.out.println(article);
				}
			redirection(request,response);
		}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("je suis dans la Servlet NouvelleVente en doPost");
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
		
		//ACTION DU BOUTON ANNULER LA VENTE	
		if (request.getParameter("annulerVente")!= null) {
			System.out.println("je suis dans la servlet en post : ANNULER LA VENTE");
//			String noArticle = request.getParameter("no_article");				
//			System.out.println("le paramètre String récupéré de l'accueil en Post est l'article numéro :" + noArticle);
//			int no_article = Integer.parseInt(noArticle);
//					
//			ArticleManager articleManager = BLLFactory.getArticleBLL();
//			//Récupération de l'article grâce à la BLL
//			Article article = articleManager.selectArticleById(no_article);
//			try {
//				ArticleManager artManager = BLLFactory.getArticleBLL();
//				artManager.SupprimerCetArticle(article);
//			}catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("erreur de suppression de l'article");
//			}
			RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
			rd.forward(request, response);
		}
	}
}	
		

