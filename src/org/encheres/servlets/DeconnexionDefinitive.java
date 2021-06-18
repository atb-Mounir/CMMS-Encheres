package org.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeconnexionDefinitive
 */
@WebServlet("/DeconnexionDefinitive")
public class DeconnexionDefinitive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeconnexionDefinitive() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Cookie cookieUtilisateurPseudo = null;
		Cookie cookieUtilisateurMotDePasse = null;
		
		HttpSession session = request.getSession();
		session.setAttribute("etatConnexion", null);
		session.setAttribute("cookieUtilisateurPseudo", null);
		session.setAttribute("cookieUtilisateurMotDePasse", null);
		
		if (cookies != null) {
			for (Cookie c : cookies) {
				if ("utilisateurPseudo".equals(c.getName())) {
					cookieUtilisateurPseudo = c;
					cookieUtilisateurPseudo.setMaxAge(0);
					response.addCookie(cookieUtilisateurPseudo);
				}

				if ("utilisateurMotDePasse".equals(c.getName())) {
					cookieUtilisateurMotDePasse = c;
					cookieUtilisateurMotDePasse.setMaxAge(0);
					response.addCookie(cookieUtilisateurMotDePasse);
				}
			}
		}


		
		

		RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
