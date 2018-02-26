package example.servlets;

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
import DB.DBConsts.SqlColumns;
import example.AppConstants;
import example.URIConsts;
import example.Utils;
import example.model.Review;
import example.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(
		description = "Servlet to provide users", 
		urlPatterns = { 
				"/usersList",
				"/deleteUser"
		})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
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

			Collection<User> usersResult = new ArrayList<User>(); 
			String uri = request.getRequestURI();
			Statement stmt;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(DBQueries.SELECT_ALL_USERS);
				while (rs.next()){

					usersResult.add(new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7))); 
					System.out.println("name of: " + rs.getString(2));
					System.out.println("email : " + rs.getString(1));
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				getServletContext().log("Error while querying for customers", e);
				response.sendError(500);//internal server error
			}

			for (Iterator iterator = usersResult.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				//				System.out.println("bookId: "+ review.getBookId() + "description "+ review.getDescription());

			}
			conn.close();

			Gson gson = new Gson();
			//convert from customers collection to json
			String userJsonResult = gson.toJson(usersResult, AppConstants.USER_COLLECTION);
			response.addHeader("Content-Type", "application/json");
			PrintWriter writer = response.getWriter();
			writer.println(userJsonResult);
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
			User user = gson.fromJson(data, User.class);
			    			System.out.println("!!!!!!!!!!!! " + user.getEmail());

			try {
//TODO: need to insert timestamp
				
	    		String uri = request.getRequestURI();
	    		if (uri.indexOf(URIConsts.DELETE_USER) != -1){//filter customer by specific name

				PreparedStatement pstmt = conn.prepareStatement(DBQueries.DELETE_USER_BY_EMAIL);
				pstmt.setString(1, user.getEmail());
				pstmt.executeUpdate();
				System.out.println("in delete user: email: " + user.getEmail());
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
