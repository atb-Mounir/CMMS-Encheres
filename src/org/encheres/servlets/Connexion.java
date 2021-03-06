package org.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.encheres.BusinessException;
import org.encheres.bll.BLLFactory;
import org.encheres.bll.UtilisateurManager;
import org.encheres.bo.Utilisateur;


/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
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
		
		UtilisateurManager utilisateurManager=BLLFactory.getUtilisateurBLL();
		Utilisateur utilisateur=null;
		Cookie[] cookies = request.getCookies();
		Cookie cookieUtilisateurPseudo = null;
		Cookie cookieUtilisateurMotDePasse = null;
		String pseudo=null;
		String motDePasse=null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				if ("utilisateurPseudo".equals(c.getName())) {
					pseudo=c.getValue();	
				}	
				if ("utilisateurMotDePasse".equals(c.getName())) {
					motDePasse=c.getValue();
				}
				
			}
		}
		if(pseudo!=null && motDePasse!=null) {
			try {
				utilisateur = utilisateurManager.testConnexion(pseudo, motDePasse);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(utilisateur!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("etatConnexion", utilisateur.getNoUtilisateur());
			session.setAttribute("newUtilisateur", utilisateur);//Ajout?? pour r??cup??rer l'objet Utilisateur qui est dans la session
			response.sendRedirect("/EncheresCMM/Accueil");
			}
			
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
			rd.forward(request, response);
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> listeCodesErreur=new ArrayList<>();
		
		
		String pseudo=lireParametrePseudo(request, listeCodesErreur);
		String motDePasse=lireParametreMotDePasse(request, listeCodesErreur);
		
		
		UtilisateurManager utilisateurManager=BLLFactory.getUtilisateurBLL();
		Utilisateur utilisateur=null;
		
		if(listeCodesErreur.size()>0)
		{
			System.out.println("errr" + listeCodesErreur.size());
			request.setAttribute("listeCodesErreur",listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
			rd.forward(request, response);
		}
		else {
			try {
				utilisateur = utilisateurManager.testConnexion(pseudo, motDePasse);
				HttpSession session = request.getSession();
				
				
				if(utilisateur!=null)
				{
					session.setAttribute("etatConnexion", utilisateur.getNoUtilisateur());
					session.setAttribute("newUtilisateur", utilisateur);//Ajout?? pour r??cup??rer l'objet Utilisateur qui est dans la session

					if (request.getParameter("seSouvenirDeMoi") != null) {

						Cookie[] cookies = request.getCookies();
						Cookie cookieUtilisateurPseudo = null;
						Cookie cookieUtilisateurMotDePasse = null;
						if (cookies != null) {
							for (Cookie c : cookies) {
								if ("utilisateurPseudo".equals(c.getName())) {
									cookieUtilisateurPseudo = c;
									cookieUtilisateurPseudo.setValue(utilisateur.getPseudo());
									break;
								}

								if ("utilisateurMotDePasse".equals(c.getName())) {
									cookieUtilisateurMotDePasse = c;
									cookieUtilisateurMotDePasse.setValue(utilisateur.getMotDePasse());
									break;
								}
							}
						}
						// Je cr??e le cookie et c'est surement la 1er fois que j'arrive sur ma page.
						if (cookieUtilisateurPseudo == null) {
							cookieUtilisateurPseudo = new Cookie("utilisateurPseudo", utilisateur.getPseudo());
						}
						if (cookieUtilisateurMotDePasse == null) {
							cookieUtilisateurMotDePasse = new Cookie("utilisateurMotDePasse", utilisateur.getMotDePasse());
						}
						// Dur??e de vie du Cookie.
						cookieUtilisateurPseudo.setMaxAge(600);
						cookieUtilisateurMotDePasse.setMaxAge(600);
						session.setAttribute("cookieUtilisateurPseudo", cookieUtilisateurPseudo);
						session.setAttribute("cookieUtilisateurMotDePasse", cookieUtilisateurMotDePasse);
						// Ajout du cookie sur la r??ponse.
						response.addCookie(cookieUtilisateurPseudo);
						response.addCookie(cookieUtilisateurMotDePasse);
						response.sendRedirect("/EncheresCMM/Accueil");
						
					}

				}
						
					
				
				else {
					listeCodesErreur.add(CodesResultatServlets.MOT_DE_PASSE_IDENTIFIANT_INCORRECT);
					request.setAttribute("listeCodesErreur",listeCodesErreur);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
					rd.forward(request, response);
				}
				
				
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	private String lireParametrePseudo(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String pseudo;
		pseudo = request.getParameter("pseudo");
		if(pseudo==null || pseudo.trim().equals(""))
		{
			listeCodesErreur.add(CodesResultatServlets.PSEUDO_OBLIGATOIRE);
		}
		return pseudo;
	}
	
	private String lireParametreMotDePasse(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String motDePasse;
		motDePasse = request.getParameter("motDePasse");
		if(motDePasse==null || motDePasse.trim().equals(""))
		{
			listeCodesErreur.add(CodesResultatServlets.MOT_DE_PASSE_OBLIGATOIRE);
		}
		return motDePasse;
	}

}
