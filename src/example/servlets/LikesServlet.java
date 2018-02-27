package example.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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

import DB.DBConsts.SqlColumns;
import DB.DBQueries;
import example.AppConstants;
import example.URIConsts;
import example.Utils;
import example.model.Like;
import example.model.Review;

/**
 * Servlet implementation class LikeHandler
 */
@WebServlet(
		description = "Servlet to Like", 
		urlPatterns = { 
				"/newLike",
				"/likes/bookId/*"
		})
public class LikesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LikesServlet() {
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

			//			Statement stmt1 = conn.createStatement();
			//			stmt1.executeUpdate(AppConstants.CREATE_BOOK_READ_TABLE);


			Collection<Like> likeResult = new ArrayList<Like>(); 
			String uri = request.getRequestURI();
			String bookId = uri.substring(uri.indexOf(URIConsts.BOOK_ID) + URIConsts.BOOK_ID.length() + 1);
			PreparedStatement stmt;
			try {

				stmt = conn.prepareStatement(DBQueries.SELECT_LIKES_OF_BOOK_ID);
				stmt.setString(1, bookId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()){
					likeResult.add(new Like(rs.getString(1),rs.getString(2),rs.getString(3), Integer.toString(1)));
					System.out.println("LIKER: email " + rs.getString(1) + " bookId " + rs.getString(2) + " name " + rs.getString(3));
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				getServletContext().log("Error while querying for likes", e);
				response.sendError(500);//internal server error
			}
			for (Iterator iterator = likeResult.iterator(); iterator.hasNext();) {
				Like like = (Like) iterator.next();
				System.out.println("bookId: "+ like.getBookId() + "user name: "+ like.getUserName());

			}



			conn.close();

			Gson gson = new Gson();
			//convert from customers collection to json
			String likeJsonResult = gson.toJson(likeResult, AppConstants.LIKE_COLLECTION);
			response.addHeader("Content-Type", "application/json");
			PrintWriter writer = response.getWriter();
			writer.println(likeJsonResult);
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
			Context context = new InitialContext();
			BasicDataSource ds = (BasicDataSource)context.lookup(
					getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
			Connection conn = ds.getConnection();
			String data = Utils.getPostBody(request);
			System.out.println("!!!!!!!!!!!! " + data);
			Gson gson = new Gson();
			Like like = gson.fromJson(data, Like.class);
			System.out.println("!!!!!!!!!!!! " + like.getBookId());

			try {
				PreparedStatement pstmt = conn.prepareStatement(DBQueries.SET_LIKE);
				pstmt.setString(1, like.getIsLiked());
				pstmt.setString(2, like.getEmail());
				pstmt.setString(3, like.getBookId());
				pstmt.executeUpdate();
				System.out.println("email: " + like.getEmail() +"bookId: "+ like.getBookId() + " is Liked: "+ like.getIsLiked());


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
