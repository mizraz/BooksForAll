package servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// TODO: Auto-generated Javadoc
/**
 * Servlet Filter implementation class RolesFilter.
 */
@WebFilter(
		urlPatterns = { 
				"/allReviewsNotApproved",
				"/reviewApprove",
				"/transactions",
				"/transactions/*",
				"/usersList"

		})
public class RolesFilterServlet implements Filter {

    /**
     * Default constructor. 
     */
    public RolesFilterServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * Destroy.
	 *
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * filter unauthorized users try to make admin's activities. checks that session is exist by comparing hash came from request
	 * that appears in SessionActiveTable. 
	 *
	 * @param request a request to perform: get: allReviewsNotApproved /  transactions / usersList / reviewApprove
	 * @param response - chain if authorized, otherwise drop
	 * @param chain the chain
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		String attrHas = (String) session.getAttribute("hashForSessionId");
		String attrIsAdmin = (String) session.getAttribute("isAdmin");

		
		if(SessionsActiveTable.setOfActiveSessions.contains(attrHas)) {
			System.out.println("the session is active: " + attrHas);
			if (attrIsAdmin.compareTo("1") == 0) {
				chain.doFilter(request, response);
			}
		}
	}

	/**
	 * Inits the.
	 *
	 * @param fConfig the f config
	 * @throws ServletException the servlet exception
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
