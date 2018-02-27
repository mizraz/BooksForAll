package example.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
import example.AppConstants;
import example.URIConsts;
import example.model.Ebook;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class EbookServlet.
 */
@WebServlet(
		description = "Handles ebooks info",
		urlPatterns = { 
				"/ebooks"
		})
public class EbookServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new ebook servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public EbookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * get all the ebooks the store can offer.
	 *
	 * @param request the request - for all ebooks the store can offer
	 * @param response with a list of all ebooks store can offer
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

			Collection<Ebook> ebooksResult = new ArrayList<Ebook>(); 
			String uri = request.getRequestURI();
			String email = "";
			String bookId = "";
			
			PreparedStatement stmt;
			try {
//	    		if (uri.indexOf(URIConsts.BOOK_ID) != -1){
//	    			email = uri.substring(uri.indexOf(URIConsts.EMAIL) + URIConsts.EMAIL.length() + 1 , 
//							(uri.indexOf("/" + URIConsts.BOOK_ID)));
//	    			bookId = uri.substring(uri.indexOf(URIConsts.BOOK_ID) + URIConsts.BOOK_ID.length() + 1);
//					stmt = conn.prepareStatement(DBQueries.SELECT_PURCHASES_BY_EMAIL_AND_BOOK_ID);
//					stmt.setString(1, email);
//					stmt.setString(2, bookId);
//	    		}
//	    		else
//	    		{
					stmt = conn.prepareStatement(DBQueries.SELECT_ALL_EBOOKS);
//	    		}
	    		

				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()){
					ebooksResult.add(new Ebook(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6)));
					System.out.println("Ebook: id " + rs.getString(1) + " title " + rs.getString(2) + " author " + rs.getString(3)
										+ " price: " +  rs.getString(4));
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				getServletContext().log("Error while querying for ebooks", e);
				response.sendError(500);//internal server error
			}
			for (Iterator iterator = ebooksResult.iterator(); iterator.hasNext();) {
				Ebook ebook = (Ebook) iterator.next();
				System.out.println("id : "+ ebook.getBookId() + " title : "+ ebook.getTitle() 
									+ "ebook price : "+ ebook.getPrice());
			}



			conn.close();

			Gson gson = new Gson();
			//convert from customers collection to json
			String ebookJsonResult = gson.toJson(ebooksResult, AppConstants.EBOOKS_COLLECTION);
			response.addHeader("Content-Type", "application/json");
			PrintWriter writer = response.getWriter();
			writer.println(ebookJsonResult);
			writer.close();
		} catch (SQLException | NamingException e) {
			getServletContext().log("Error while closing connection", e);
			response.sendError(500);//internal server error
		}	}

	/**
	 * Do post. - in this project - no need to edit ebooks functionallity.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
