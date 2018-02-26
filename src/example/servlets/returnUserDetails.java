package example.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.Cookie;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import DB.DBQueries;
import example.AppConstants;
import example.model.Review;
import example.model.User;


/**
 * Servlet implementation class returnUserDetails
 */

@WebServlet(
		description = "Handles ebooks info",
		urlPatterns = { 
				"/returnUserDetails",
				"/returnUserDetails/*"
				
		})


public class returnUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public returnUserDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HELLO");
		System.out.println(request.getRequestURI());
		String uri1;  
		uri1 = new String (request.getRequestURI());
		System.out.println("HELLO1");
		System.out.println("this is uri1:" + uri1.split("/")[4]);
		
        try {
        	
    		
        	//obtain CustomerDB data source from Tomcat's context
    		Context context = new InitialContext();
    		BasicDataSource ds = (BasicDataSource)context.lookup(
    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
    		Connection conn = ds.getConnection();
    		Collection<User> userResult = new ArrayList<User>(); 
    		//Collection<User> userResult = new ArrayList<User>();
    		//BufferedReader reader = request.getReader();
    		
    		//line = reader.readLine();
    		String uri = request.getRequestURI();
    		
			
			String userName = "";
			String email = "";	
			String userNickname = "";
			String pwd = "";
			String address = "";
			String phoneNumber = "";
			String description = "";
			String userImageUrl = "";
    	
    		String[] segments = uri.split("/");
    		String param = segments[segments.length-1];
    		PreparedStatement stmt;
    		Cookie[] sessionCookie = null;
    		sessionCookie = request.getCookies();
    		String searchBy = "";
    		if(uri1.split("/")[4]!=null)
    		{
    			searchBy=uri1.split("/")[4];
    		}
    		else
    		{
    			searchBy=sessionCookie[0].getValue();
    		}
    		System.out.println(sessionCookie[0].getValue());
    			stmt = conn.prepareStatement("SELECT * FROM USER_DETAILS WHERE  email= '"+searchBy+"'");
    	 	
			try {		
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()){
					User usr = new User(rs.getString(1),rs.getString(2),rs.getString(6),rs.getString(5),rs.getString(3),rs.getString(8),rs.getString(4),rs.getString(7));
					System.out.println(usr);
					userResult.add(usr);
					//System.out.println("User: email " + rs.getString(1) + " email " + rs.getString(2) +  " nickname " + rs.getString(3) + " password " + rs.getString(4)
								//		+ " address: " +  rs.getString(5)+ " phoneNumber: " +  rs.getString(6)+ " description: " +  rs.getString(7)+ " photo: " +  rs.getString(8));
				}
				rs.close();
				stmt.close();
			}catch (SQLException e) {
				getServletContext().log("Error while querying for ebooks", e);
				response.sendError(500);//internal server error
			}
			for (Iterator iterator = userResult.iterator(); iterator.hasNext();) {
				User usr = (User) iterator.next();
				System.out.println("name : "+ usr.getUserName() + " nickname : "+ usr.getUserNickname() 
									+ "email : "+ usr.getEmail());
			}

			conn.close();

			Gson gsonRet = new Gson();
			//convert from customers collection to json
			String userRet = gsonRet.toJson(userResult, AppConstants.USER_COLLECTION);
			response.addHeader("Content-Type", "application/json");
			PrintWriter writer = response.getWriter();
			writer.println(userRet);
			writer.close();
			
		
        
     }
        catch (SQLException | NamingException e) {
    		getServletContext().log("Error while closing connection", e);
    		response.sendError(500);//internal server error
    	}
		
		
		
	}
	/**	try {	
			//obtain CustomerDB data source from Tomcat's context
			Context context = new InitialContext();
		
			BasicDataSource ds = (BasicDataSource)context.lookup(
					getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
			Connection conn = ds.getConnection();

			Collection<User> users = new ArrayList<User>(); 
			String uri = request.getRequestURI();
			
			String userName = "";
			String email = "";	
			String userNickname = "";
			String pwd = "";
			String address = "";
			String phoneNumber = "";
			String description = "";
			String userImageUrl = "";
			
			
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
					stmt = conn.prepareStatement("SELECT * FROM USER_DETAILS WHERE  email= '"+email+"' OR user_nickname = '"+userNickname +"'");
//	    		}
	    		
			try {		
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()){
					users.add(new User(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
					System.out.println("User: name " + rs.getString(1) + " email " + rs.getString(2) +  " nickname " + rs.getString(3) + " password " + rs.getString(4)
										+ " address: " +  rs.getString(5)+ " phoneNumber: " +  rs.getString(6)+ " description: " +  rs.getString(7)+ " photo: " +  rs.getString(8));
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				getServletContext().log("Error while querying for ebooks", e);
				response.sendError(500);//internal server error
			}
			for (Iterator iterator = users.iterator(); iterator.hasNext();) {
				User usr = (User) iterator.next();
				System.out.println("name : "+ usr.getUserName() + " nickname : "+ usr.getUserNickname() 
									+ "email : "+ usr.getEmail());
			}



			conn.close();

			Gson gsonRet = new Gson();
			//convert from customers collection to json
			String userRet = gsonRet.toJson(users, AppConstants.USER_COLLECTION);
			response.addHeader("Content-Type", "application/json");
			PrintWriter writer = response.getWriter();
			writer.println(userRet);
			writer.close();
			
		}	
	  
	catch (SQLException  | NamingException e) {
	   		getServletContext().log("Error while closing connection", e);
	    		response.sendError(500);//internal server error
	    	}
		} 

**/
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}



