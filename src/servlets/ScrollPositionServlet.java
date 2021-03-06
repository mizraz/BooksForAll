package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import com.google.gson.Gson;
import DB.DBQueries;
import utils.AppConstants;
import utils.URIConsts;
import utils.Utils;
import model.ScrollObj;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ScrollServlet.
 */
@WebServlet(
		description = "Servlet to SCroll", 
		urlPatterns = { 
				"/scroll",
				"/scroll/email/*",

		})
public class ScrollPositionServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new scroll position servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public ScrollPositionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Get the last scroll position user is in book id by url param.
	 *
	 * @param request the for the last scroll position
	 * @param response ScrollObj , contains user last scroll position
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	
	
		try {
			//obtain CustomerDB data source from Tomcat's context
			Context context = new InitialContext();
			BasicDataSource ds = (BasicDataSource)context.lookup(
					getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
			Connection conn = ds.getConnection();

			Collection<ScrollObj> scrollPositionResult = new ArrayList<ScrollObj>(); 
			String uri = request.getRequestURI();
			String email = "";
			String bookId = "";
			
			PreparedStatement stmt;
			try {
//	    		if (uri.indexOf(URIConsts.BOOK_ID) != -1){
	    			email = uri.substring(uri.indexOf(URIConsts.EMAIL) + URIConsts.EMAIL.length() + 1 , 
							(uri.indexOf("/" + URIConsts.BOOK_ID)));
	    			bookId = uri.substring(uri.indexOf(URIConsts.BOOK_ID) + URIConsts.BOOK_ID.length() + 1);
					stmt = conn.prepareStatement(DBQueries.SELECT_SCROLL_BY_BOOK_ID_AND_EMAIL);
					stmt.setString(1, bookId);
					stmt.setString(2, email);
//	    		} else {
//	    			email = uri.substring(uri.indexOf(URIConsts.EMAIL) + URIConsts.EMAIL.length() + 1 );
//					stmt = conn.prepareStatement(DBQueries.SELECT_PURCHASES_BY_EMAIL);
//					stmt.setString(1, email);
//	    		}
	    		

				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()){
					scrollPositionResult.add(new ScrollObj(email, bookId , rs.getString(1)));
					System.out.println("get  scroll: " +  rs.getString(1));
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				getServletContext().log("Error while querying for scroll", e);
				response.sendError(500);//internal server error
			}


			conn.close();

			Gson gson = new Gson();
			//convert from customers collection to json
			String scrollPositionJsonResult = gson.toJson(scrollPositionResult, AppConstants.SCROLL_COLLECTION);
			response.addHeader("Content-Type", "application/json");
			PrintWriter writer = response.getWriter();
			writer.println(scrollPositionJsonResult);
			writer.close();
		} catch (SQLException | NamingException e) {
			getServletContext().log("Error while closing connection", e);
			response.sendError(500);//internal server error
		}
		
	}

	/**
	 * Update the last scroll position of user in book in DB in USER_PURCHASES table.
	 *
	 * @param request for update the last scroll position of user in book by id and email.
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			//obtain CustomerDB data source from Tomcat's context
			Context context = new InitialContext();
			BasicDataSource ds = (BasicDataSource)context.lookup(
					getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
			Connection conn = ds.getConnection();
			String data = Utils.getPostBody(request);
			    			System.out.println("!!!!!!!!!!!! " + data);
			Gson gson = new Gson();
			ScrollObj scroll = gson.fromJson(data, ScrollObj.class);
			    			System.out.println("!!!!!!!!!!!! " + scroll.getBookId());

			try {
    			

				PreparedStatement pstmt = conn.prepareStatement(DBQueries.SET_SCROLL);
				pstmt.setString(1, scroll.getScroll());
				pstmt.setString(2, scroll.getEmail());
				pstmt.setString(3, scroll.getBookId());
				pstmt.executeUpdate();
				System.out.println("email: " + scroll.getEmail() +"bookId: "+ scroll.getBookId() +" scroll: "+ scroll.getScroll());

			} catch (SQLException e) {
				getServletContext().log("Error while querying for customers", e);
				response.sendError(500);//internal server error
			}
			conn.close();

		} catch (SQLException | NamingException e) {
			getServletContext().log("Error while closing connection", e);
			response.sendError(500);//internal server error
		}
	}

}
