package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import DB.DBQueries;
import utils.AppConstants;
import utils.URIConsts;
import utils.Utils;
import model.Review;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ReviewHandler.
 */

@WebServlet(
		description = "Servlet to provide insert new Review", 
		urlPatterns = { 
				"/newReview",
				"/allReviewsNotApproved",
				"/reviewApprove",
				"/reviews/bookId/*"
		})
public class ReviewsServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new reviews servlet.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get: all reviews by book id url param , get all unapproved reviews.
	 *
	 * @param request the request
	 * @param response - a list of reviews
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

    		Collection<Review> reviewsResult = new ArrayList<Review>(); 
    		String uri = request.getRequestURI();
    		if (uri.indexOf(URIConsts.BOOK_ID) != -1){//filter customer by specific name
    			String bookId = uri.substring(uri.indexOf(URIConsts.BOOK_ID) + URIConsts.BOOK_ID.length() + 1);
    			PreparedStatement stmt;
    			try {
    				stmt = conn.prepareStatement(DBQueries.SELECT_REVIEWS_OF_BOOK_ID);
    				stmt.setString(1, bookId);
    				ResultSet rs = stmt.executeQuery();
    				while (rs.next()){
    					reviewsResult.add(new Review(rs.getString(1),rs.getString(2),rs.getString(3),
    							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
    					System.out.println("REVIEWer name: " + rs.getString(5));
    				}
    				rs.close();
    				stmt.close();
    			} catch (SQLException e) {
    				getServletContext().log("Error while querying for customers", e);
    	    		response.sendError(500);//internal server error
    			}
    		}else{
    			Statement stmt;
    			try {
    				stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(DBQueries.SELECT_ALL_REVIEWS_NOT_APPROVED);
    				while (rs.next()){
    					reviewsResult.add(new Review(rs.getString(1),rs.getString(2),rs.getString(3),
    							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7))); 
    				System.out.println("review of: " + rs.getString(1));
					System.out.println("REVIEWer name: " + rs.getString(5));

    				}
    				rs.close();
    				stmt.close();
    			} catch (SQLException e) {
    				getServletContext().log("Error while querying for customers", e);
    	    		response.sendError(500);//internal server error
    			}

    		}
			for (Iterator iterator = reviewsResult.iterator(); iterator.hasNext();) {
				Review review = (Review) iterator.next();
//				System.out.println("bookId: "+ review.getBookId() + "description "+ review.getDescription());
				
			}

    		
    		conn.close();
    		
    		Gson gson = new Gson();
        	//convert from customers collection to json
        	String reviewJsonResult = gson.toJson(reviewsResult, AppConstants.REVIEW_COLLECTION);
        	response.addHeader("Content-Type", "application/json");
        	PrintWriter writer = response.getWriter();
        	writer.println(reviewJsonResult);
        	writer.close();
    	} catch (SQLException | NamingException e) {
    		getServletContext().log("Error while closing connection", e);
    		response.sendError(500);//internal server error
    	}
	}

	/**
	 * store in DB in REVIEWS table a new review. for review approval - update DB in REVIEWS table the review is approved
	 *
	 * @param request for new review submission, approve a review.
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
			Review rev = gson.fromJson(data, Review.class);
			    			System.out.println("!!!!!!!!!!!! " + rev.getBookId());

			try {
				
	    		String uri = request.getRequestURI();
	    		if (uri.indexOf(URIConsts.REVIEW_APPROVE) == -1){//filter customer by specific name

				PreparedStatement pstmt = conn.prepareStatement(DBQueries.INSERT_REVIEW);
				pstmt.setString(1, rev.getEmail());
				pstmt.setString(2, rev.getBookId());
				pstmt.setString(3, rev.getDescription());
				pstmt.setString(4, rev.getIsApproved());
				pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));


				pstmt.executeUpdate();
				System.out.println("email: " + rev.getEmail() +"description: "+ rev.getDescription());
	    		} else {
					PreparedStatement pstmt = conn.prepareStatement(DBQueries.SET_REVIEW);
					pstmt.setString(1, rev.getEmail());
					pstmt.setString(2, rev.getBookId());
					pstmt.executeUpdate();
					System.out.println("review approved email: " + rev.getEmail() + " bookId: " + rev.getBookId()  );
	    		}
	    		

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
