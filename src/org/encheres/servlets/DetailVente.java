package org.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.encheres.BusinessException;
import org.encheres.bll.ArticleManager;
import org.encheres.bll.BLLFactory;
import org.encheres.bll.EnchereManager;
import org.encheres.bo.Article;
import org.encheres.bo.Utilisateur;



/**
 * Servlet implementation class DetailVente
 */
@WebServlet(description = "servlet qui redirige vers la jsp detailVente.jsp", urlPatterns = { "/DetailVente" })
public class DetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
     public DetailVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("je suis dans DetailVente en doGet");
		/*
		 * test variable pour comparaison avec date du jour dans la jsp detailVente
		 */
		java.util.Date aujourdhui=new java.util.Date();  
		ArticleManager articleManager = BLLFactory.getArticleBLL();
		// je récupère le no_article envoyé par la jspAccueil qui veut afficher le
		// détail de l'article séléctionner
		int no_article = Integer.parseInt(request.getParameter("no_article"));
		Article article = articleManager.selectArticleById(no_article);

		request.setAttribute("article", article);
		request.setAttribute("aujourdhui", aujourdhui);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp");
		rd.forward(request, response);

		System.out.println("numero article récupérer par la Servlet : " + no_article);
		System.out.println(LocalDate.now());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recuperation du Numero Utilisateur par la session
		HttpSession session = request.getSession();
		Utilisateur utilisateur= (Utilisateur) session.getAttribute("newUtilisateur");
		int noUtilisateur=utilisateur.getNoUtilisateur();
		List<Integer> listeCodesErreur=new ArrayList<>();

		
		//Recuperation du numero de l'article envoyé par la JSP
		int noArticle =Integer.parseInt(request.getParameter("no_article"));
		
		//Recuperation du montantEnchere envoyé par la JSP
		Integer montantEnchere=null;
		try {
			montantEnchere = lireParametreMontantEnchere(utilisateur, request, listeCodesErreur);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (listeCodesErreur.size() > 0) {
			System.out.println("errr enregistrer" + listeCodesErreur.size());
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			doGet(request, response);
		}else {
		
		/*RECUPERATION DE LA DATE DU JOUR*/
		Date input = new Date();
		LocalDate dateEnchere = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		//SYSO pour tester la date
		System.out.println("date de la nouvelle enchere en LOCALdATE: "+dateEnchere);

		//Creation de EnchereManager
		EnchereManager enchereManager = BLLFactory.getEnchereBLL();
		
		if(session.getAttribute("etatConnexion")!= null) {
			System.out.println("info de la nouvelle enchère montant : "+montantEnchere
					+"date du jour : "+dateEnchere
					+" noUtilisateur : "+noUtilisateur
					+" noArticle : "+noArticle);
			try {
				enchereManager.enregistrerMontantEnchère(utilisateur, noArticle, dateEnchere, montantEnchere);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("NOUVELLE ENCHERE ENREGISTRE / "+noUtilisateur+" "+ noArticle+" "+ dateEnchere+" "+ montantEnchere);
		} else {
		System.out.println("personne n'est connecté");
		}
		
		request.setAttribute("no_article", noArticle);
		System.out.println("numero d'article envoyé vers le DOGet "+noArticle);
		response.sendRedirect("/EncheresCMM/DetailVente?no_article="+noArticle);
		}
		
	}
	
	
	private int lireParametreMontantEnchere(Utilisateur utilisateur, HttpServletRequest request, List<Integer> listeCodesErreur) throws BusinessException {
		int montantEnchere= Integer.parseInt(request.getParameter("montant_enchere"));
		int meilleureOffre=Integer.parseInt(request.getParameter("meilleureOffre"));
		int miseAPrix=Integer.parseInt(request.getParameter("miseAPrix"));
		System.out.println("meilleure offre : "+meilleureOffre);
		System.out.println("montant_enchere : "+montantEnchere);
		if(montantEnchere<=meilleureOffre)
		{
			listeCodesErreur.add(CodesResultatServlets.MONTANT_ENCHERE_INCCORECT);
		}
		
		else if(montantEnchere<=miseAPrix) {
			listeCodesErreur.add(CodesResultatServlets.MONTANT_ENCHERE_INF_MISE_A_PRIX);
		}
		
		if(montantEnchere > utilisateur.getCredit()) {
			listeCodesErreur.add(CodesResultatServlets.CREDIT_INSUFFISANT);
		}
		
		return montantEnchere;
	}
	
}

