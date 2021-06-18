package org.encheres.filters;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionAttributeListener;

/**
 * Servlet Filter implementation class FilterRequestRedirectAccueil
 */
@WebFilter(
		dispatcherTypes = {DispatcherType.ERROR}
					, 
		urlPatterns = { 
				"/bla" 
				//,"http://localhost:8080/EncheresCMM/*"
		})
public class FilterRequestRedirectAccueil implements Filter {

    /**
     * Default constructor. 
     */
    public FilterRequestRedirectAccueil() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Je suis le filtre de redirection vers la page d'accueil si modif de l'URL");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accesRefuse.jsp");
		rd.forward(request, response);

		
		
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
