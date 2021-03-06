package org.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
import org.encheres.bll.UtilisateurManager;
import org.encheres.bo.Utilisateur;

/**
 * Servlet implementation class MonProfil
 */
@WebServlet("/MonProfil")
public class MonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonProfil() {
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
		
		/*Début test si utilisateur connecté pour lui afficher les informations de profil et qu'il les modifie*/
		HttpSession session = request.getSession();
		
		if(session.getAttribute("etatConnexion")!=null) {
		int idUtilisateur=(int)session.getAttribute("etatConnexion");
		Utilisateur utilisateur=null;

		UtilisateurBLL utilisateurBLL=BLLFactory.getUtilisateurBLL();
		try {
			utilisateur=utilisateurBLL.selectUtilisateurById(idUtilisateur);
			request.setAttribute("utilisateur", utilisateur);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		/*Fin test*/
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/monProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Integer> listeCodesErreur=new ArrayList<>();
		
		String pseudo=request.getParameter("pseudo");
		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		String email=request.getParameter("email");
		String telephone=request.getParameter("telephone");
		String rue=request.getParameter("rue");
		String cp=request.getParameter("cp");
		String ville=request.getParameter("ville");
		
		
		System.out.println("Je suis dans la méthode post de mon profil");
		
		UtilisateurManager utilisateurManager=BLLFactory.getUtilisateurBLL();
		
		if(request.getParameter("creer")!=null) {
		/*J'ai cliqué sur créer mon profil en mode déconnecté*/
		/*Verification si le pseudo existe déjà*/
		try {
			lireParametrePseudo(pseudo, request, listeCodesErreur);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String motDePasse=lireParametreMotDePasseCreation(request, listeCodesErreur);
		String motDePasseConfirm=lireParametreMotDePasseConfirmation(motDePasse, request, listeCodesErreur);
		
			if (listeCodesErreur.size() > 0) {
				System.out.println("errr" + listeCodesErreur.size());
				request.setAttribute("listeCodesErreur", listeCodesErreur);
				doGet(request, response);
			} 
			else {
				try {
					utilisateurManager.creerUtilisateur(pseudo, nom, prenom, email, telephone, rue, cp, ville,
							motDePasse, motDePasseConfirm);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect("/EncheresCMM/Accueil");
			}
			
		}

		if (request.getParameter("enregistrer") != null) {
			/* J'ai cliqué sur enregistré mon profil en mode connecté pour le modifier */
			HttpSession session = request.getSession();
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("newUtilisateur");
			try {
				lireParametrePseudo(utilisateur.getPseudo(), pseudo, request, listeCodesErreur);
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int idUtilisateur = (int) session.getAttribute("etatConnexion");

			String motDePasseActuel = null;
			try {
				motDePasseActuel = lireParametreMotDePasseActuel(utilisateur, request, listeCodesErreur);
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String motDePasseNouveau = lireParametreMotDePasseNouveau(request, listeCodesErreur);
			String motDePasseNouveauConfirmation = lireParametreMotDePasseNouveauConfirmation(motDePasseNouveau,
					request, listeCodesErreur);

			if (listeCodesErreur.size() > 0) {
				System.out.println("errr enregistrer" + listeCodesErreur.size());
				request.setAttribute("listeCodesErreur", listeCodesErreur);
				doGet(request, response);
			} else {
				try {
					utilisateurManager.modifierUtilisateur(idUtilisateur, pseudo, nom, prenom, email, telephone, rue,
							cp, ville, motDePasseActuel, utilisateur.getCredit(), motDePasseNouveauConfirmation);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect("/EncheresCMM/Accueil");
			}
		}
		
		/*J'ai cliqué sur supprimer mon profil en mode connecté*/
		if(request.getParameter("supprimer")!=null) {
			HttpSession session = request.getSession();
			int idUtilisateur=(int)session.getAttribute("etatConnexion");
			try {
				utilisateurManager.supprimerUtilisateur(idUtilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/Deconnexion");
			rd.forward(request, response);
		}
		
	}
	
	
	private String lireParametreMotDePasseActuel(Utilisateur utilisateur, HttpServletRequest request, List<Integer> listeCodesErreur) throws BusinessException {
		UtilisateurManager utilisateurManager=BLLFactory.getUtilisateurBLL();
		String motDePasseActuel;
		motDePasseActuel = request.getParameter("motDePasseActuel");
		utilisateur=utilisateurManager.testConnexion(utilisateur.getPseudo(), motDePasseActuel);
		if(utilisateur==null)
		{
			listeCodesErreur.add(CodesResultatServlets.MOT_DE_PASSE_IDENTIFIANT_INCORRECT);
		}
		return motDePasseActuel;
	}
	
	
	private String lireParametreMotDePasseCreation(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String motDePasseCreation;
		motDePasseCreation = request.getParameter("motDePasseCreation");
		if(motDePasseCreation==null || motDePasseCreation.trim().equals("") || motDePasseCreation.trim().length()<2)
		{
			listeCodesErreur.add(CodesResultatServlets.MOT_DE_PASSE_OBLIGATOIRE_CREATION);
		}
		return motDePasseCreation;
	}
	
	private String lireParametreMotDePasseNouveau(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String motDePasseCreation;
		motDePasseCreation = request.getParameter("motDePasseNouveau");
		if(motDePasseCreation.trim().length()<2 && motDePasseCreation.trim().length()>0)
		{
			listeCodesErreur.add(CodesResultatServlets.MOT_DE_PASSE_OBLIGATOIRE_CREATION);
		}
		return motDePasseCreation.trim();
	}
	
	private String lireParametreMotDePasseConfirmation(String motDePasse, HttpServletRequest request, List<Integer> listeCodesErreur) {
		String motDePasseConfirmation;
		motDePasseConfirmation = request.getParameter("motDePasseCreationConfirm");
		if(!motDePasse.equals(motDePasseConfirmation)) {
			listeCodesErreur.add(CodesResultatServlets.MOT_DE_PASSE__CONFIRMATION_NON_IDENTIQUE_CREATION);
		}
		return motDePasseConfirmation;
	}
	
	private String lireParametreMotDePasseNouveauConfirmation(String motDePasse, HttpServletRequest request, List<Integer> listeCodesErreur) {
		String motDePasseConfirmation;
		motDePasseConfirmation = request.getParameter("motDePasseNouveauConfirmation");

		if(!motDePasse.equals(motDePasseConfirmation)) {
			listeCodesErreur.add(CodesResultatServlets.MOT_DE_PASSE__CONFIRMATION_NON_IDENTIQUE_CREATION);
		}
		return motDePasseConfirmation;
	}
	
	private void lireParametrePseudo(String pseudo, HttpServletRequest request, List<Integer> listeCodesErreur) throws BusinessException {
		UtilisateurManager utilisateurManager=BLLFactory.getUtilisateurBLL();
		
		if(utilisateurManager.selectUtilisateurByPseudo(pseudo)) {
			listeCodesErreur.add(CodesResultatServlets.PSEUDO_DEJA_EXISTANT);

		}
	}
	
	private void lireParametrePseudo(String pseudoActuel, String pseudo, HttpServletRequest request, List<Integer> listeCodesErreur) throws BusinessException {
		UtilisateurManager utilisateurManager=BLLFactory.getUtilisateurBLL();
		
		/*Si le pseudo est modifié, verification que le nouveau pseudo n'existe pas déjà*/
		if (!pseudoActuel.equals(pseudo)) {
			if (utilisateurManager.selectUtilisateurByPseudo(pseudo)) {
				listeCodesErreur.add(CodesResultatServlets.PSEUDO_DEJA_EXISTANT);

			}
		}
	}
	

}
