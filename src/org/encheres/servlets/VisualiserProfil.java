package org.encheres.servlets;

import java.io.IOException;

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
import org.encheres.bll.BLLFactory;
import org.encheres.bll.UtilisateurBLL;
import org.encheres.bo.Utilisateur;

/**
 * Servlet implementation class VisualiserProfil
 */
@WebServlet(description = "Servlet qui redirige vers la jsp visualiserProfil.jsp", urlPatterns = { "/VisualiserProfil", "/VisualiserVendeur" })
public class VisualiserProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualiserProfil() {
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
		System.out.println("Je suis dans le DOGET de visualiser profil");
		int idUtilisateur=0;
		
		if(request.getServletPath().equals("/VisualiserProfil")) {
			HttpSession session = request.getSession();
			idUtilisateur=(int)session.getAttribute("etatConnexion");
		}
		
		
		if(request.getServletPath().equals("/VisualiserVendeur")) {
			System.out.println("no_vendeur="+request.getParameter("no_vendeur"));
			idUtilisateur=Integer.parseInt(request.getParameter("no_vendeur"));
		}
		
		UtilisateurBLL utilisateurBLL=BLLFactory.getUtilisateurBLL();
		try {
			Utilisateur utilisateur=null;
			utilisateur=utilisateurBLL.selectUtilisateurById(idUtilisateur);
			request.setAttribute("utilisateur", utilisateur);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/visualiserProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Je suis dans le DOPOST de visualiser profil");
		doGet(request, response);
	}

}
