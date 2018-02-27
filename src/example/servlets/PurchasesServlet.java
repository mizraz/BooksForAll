package example.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

import DB.DBConsts;
import DB.DBQueries;
import example.AppConstants;
import example.URIConsts;
import example.Utils;
import example.model.Purchase;
import example.model.Review;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class PurchasesServlet.
 */
@WebServlet(
		description = "Handles purchases info",
		urlPatterns = { 
				"/purchases/email/*",
				"/purchase/email/*",
				"/newPurchase"
		})
public class PurchasesServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new purchases servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public PurchasesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Get all purchases of user , get a single purchase of user by url params: email , bookId
	 *
	 * @param request the request
	 * @param response the response
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

			Collection<Purchase> purchasesResult = new ArrayList<Purchase>(); 
			String uri = request.getRequestURI();
			String email = "";
			String bookId = "";
			
			PreparedStatement stmt;
			try {
	    		if (uri.indexOf(URIConsts.BOOK_ID) != -1){
	    			email = uri.substring(uri.indexOf(URIConsts.EMAIL) + URIConsts.EMAIL.length() + 1 , 
							(uri.indexOf("/" + URIConsts.BOOK_ID)));
	    			bookId = uri.substring(uri.indexOf(URIConsts.BOOK_ID) + URIConsts.BOOK_ID.length() + 1);
					stmt = conn.prepareStatement(DBQueries.SELECT_PURCHASES_BY_EMAIL_AND_BOOK_ID);
					stmt.setString(1, email);
					stmt.setString(2, bookId);
	    		} else {
	    			email = uri.substring(uri.indexOf(URIConsts.EMAIL) + URIConsts.EMAIL.length() + 1 );
					stmt = conn.prepareStatement(DBQueries.SELECT_PURCHASES_BY_EMAIL);
					stmt.setString(1, email);
	    		}
	    		

				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()){
					purchasesResult.add(new Purchase(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6)));
					System.out.println("Purchase: email " + rs.getString(1) + " bookId " + rs.getString(2) + " time " + rs.getString(3)
										+ " scroll: " +  rs.getString(6));
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				getServletContext().log("Error while querying for purchases", e);
				response.sendError(500);//internal server error
			}
			for (Iterator iterator = purchasesResult.iterator(); iterator.hasNext();) {
				Purchase purchase = (Purchase) iterator.next();
				System.out.println("email: "+ purchase.getEmail() + " bookId: "+ purchase.getBookId() 
									+ "purchase time : "+ purchase.getDatePurchased());
			}



			conn.close();

			Gson gson = new Gson();
			//convert from customers collection to json
			String purchaseJsonResult = gson.toJson(purchasesResult, AppConstants.PURCHASE_COLLECTION);
			response.addHeader("Content-Type", "application/json");
			PrintWriter writer = response.getWriter();
			writer.println(purchaseJsonResult);
			writer.close();
		} catch (SQLException | NamingException e) {
			getServletContext().log("Error while closing connection", e);
			response.sendError(500);//internal server error
		}
		
		
		
	}

	/**
	 * A new purchase is made by user. update in USER_PURCHASES table that user purchased ebook.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {

			//obtain CustomerDB data source from Tomcat's context
			Context context = new InitialContext();
			BasicDataSource ds = (BasicDataSource)context.lookup(
					getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
			Connection conn = ds.getConnection();
			String data = Utils.getPostBody(request);
			    			System.out.println("!!!!!!!!!!!! " + data);
			Gson gson = new Gson();
			Purchase purchase = gson.fromJson(data, Purchase.class);
			System.out.println("in post purchase " + purchase.getBookId());

			try {
				
	    		String uri = request.getRequestURI();

	    		
				PreparedStatement pstmt = conn.prepareStatement(DBQueries.INSERT_PURCHASE);
				pstmt.setString(1, purchase.getEmail());
				pstmt.setString(2, purchase.getBookId());
				pstmt.setString(3, "0");
				pstmt.setString(4, purchase.getPrice());
				pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
				pstmt.setInt(6, 0);



				pstmt.executeUpdate();
				System.out.println("email, bookId :  " + purchase.getEmail() +
						purchase.getBookId() +"price: "+ purchase.getPrice());
	    		
				


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
