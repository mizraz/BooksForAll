package example.servlets;

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

/**
 * Servlet Filter implementation class RolesFilter
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
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
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
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
