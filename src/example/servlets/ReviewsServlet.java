package example.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
import example.Utils;
import example.model.Review;

/**
 * Servlet implementation class ReviewHandler
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
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
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

//			
//			PreparedStatement ps7 = (PreparedStatement) conn.prepareStatement(AppConstants.SELECT_REVIEWS_OF_BOOK_ID);
//			ps7.setString(1, "56254");
//			ResultSet rs7 = ps7.executeQuery();	
//			
//			ArrayList<Map<String, Object>> resultList7 = new ArrayList<Map<String, Object>>(); 
//			Map<String, Object> row7 = null;
//			ResultSetMetaData metaData7 = (ResultSetMetaData) rs7.getMetaData();
//			Integer columnCount7 = metaData7.getColumnCount();
//			while (rs7.next()) {
//				row7 = new HashMap<String, Object>();
//				for (int i = 1; i <= columnCount7; i++) {
//					row7.put(metaData7.getColumnName(i), rs7.getObject(i));
//				}
//				resultList7.add(row7);
//			}
//			Utils.printAllInResultSet(resultList7);
			
    		
    		
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
	    		
				
//				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(DBQueries.SELECT_ALL_REVIEWS_NOT_APPROVED);
//				ResultSet rs = ps.executeQuery();
//				
////				PreparedStatement pstmt4 = (PreparedStatement) conn.prepareStatement(AppConstants.SELECT_REVIEWS_OF_BOOK_ID);
////				pstmt4.setString(1, "1");
////				ResultSet rs4 = pstmt4.executeQuery();
////
////				ArrayList<Map<String, Object>> resultList4 = new ArrayList<Map<String, Object>>(); 
////				Map<String, Object> row4 = null;
////				ResultSetMetaData metaData4 = (ResultSetMetaData) rs4.getMetaData();
////				Integer columnCount4 = metaData4.getColumnCount();
////				while (rs4.next()) {
////					row4 = new HashMap<String, Object>();
////					for (int i = 1; i <= columnCount4; i++) {
////						row4.put(metaData4.getColumnName(i), rs4.getObject(i));
////					}
////					resultList4.add(row4);
////				}
////				Utils.printAllInResultSet(resultList4);
//				
//				
//
//				ArrayList<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(); 
//				Map<String, Object> row1 = null;
//				ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
//				Integer columnCount = metaData.getColumnCount();
//				while (rs.next()) {
//					row1 = new HashMap<String, Object>();
//					for (int i = 1; i <= columnCount; i++) {
//						row1.put(metaData.getColumnName(i), rs.getObject(i));
//					}
//					resultList.add(row1);
//				}
////				Utils.printAllInResultSet(resultList);

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
