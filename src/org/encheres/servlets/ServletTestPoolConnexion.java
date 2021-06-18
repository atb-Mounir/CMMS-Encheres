package org.encheres.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ServletTestPoolConnexion
 */
@WebServlet(
		description = "Servlet qui permet de tester la connexion à la base de données.", 
		urlPatterns = { "/ServletTestPoolConnexion" })
public class ServletTestPoolConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTestPoolConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			Context context = new InitialContext();
			//Rechercher la DataSource
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
			//Demande une connexion. La méthode getConnection met la demande en attente 
			//tant qu'il n'y a pas de connexions disponibles dans le pool.
			Connection cnx = dataSource.getConnection();
			out.print("La connexion est "+(cnx.isClosed()? "fermée":"ouverte")+ ".");
			//Libérer la connexion lorsqu'on en a plus besoin:
			cnx.close();//la connexion n'est pas fermée mais remise dans le pool de connexion.
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.println("Une erreur est survenue lors de l'utilisation de la base de données : "+e.getMessage());
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

