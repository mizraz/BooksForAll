package example.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
				"/deleteUser",
				"/returnUserDetails",
				"/returnUserDetails/*",
				"/updateUserDetails"
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

		
		String uri1;  
		uri1 = new String (request.getRequestURI());
	

		
        try {
        	
    		
        	//obtain CustomerDB data source from Tomcat's context
    		Context context = new InitialContext();
    		BasicDataSource ds = (BasicDataSource)context.lookup(
    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
    		Connection conn = ds.getConnection();
    		Collection<User> userResult = new ArrayList<User>(); 
    		PreparedStatement stmt;
    		Cookie[] sessionCookie = null;
    		sessionCookie = request.getCookies();
    		String searchBy = "";
    		String element2 = "";
    		
    		element2 = uri1.split("/")[2];
    		
    	if(element2.equals( "returnUserDetails")) {	
    		
    		if(uri1.split("/")[4]!=null)
    		{
    			searchBy=uri1.split("/")[4];
    		}
    		else if (uri1.split("/")[2] == "returnUserDetails")
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
			
    	}//if
    	
     if(element2.equals("usersList"))
    	{
    		
    
			Collection<User> usersResult = new ArrayList<User>(); 
			
			Statement stmt2;
			try {
				stmt2 = conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(DBQueries.SELECT_ALL_USERS);
				while (rs2.next()){

					usersResult.add(new User(rs2.getString(1),rs2.getString(2),rs2.getString(3),rs2.getString(4),
							rs2.getString(5), rs2.getString(6), rs2.getString(7))); 
					System.out.println("name of: " + rs2.getString(2));
					System.out.println("email : " + rs2.getString(1));
				}
				rs2.close();
				stmt2.close();
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
    	}
       
     }
        catch (SQLException | NamingException e) {
    		getServletContext().log("Error while closing connection", e);
    		response.sendError(500);//internal server error
    	}
	
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri1;  
		uri1 = new String (request.getRequestURI());
		String element2;
	    element2 = uri1.split("/")[2];
	    
	   
		
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
	    		if(element2.equals("updateUserDetails")){
	    			System.out.println("Hello");
	    		try {	
	    			StringBuffer jb = new StringBuffer();
	    			String line = null;
	    			try
	    			{
	    				BufferedReader reader = request.getReader();
	    				while ((line = reader.readLine()) != null)
	    				jb.append(line);
	    			}
	    			catch (Exception e)
	    			{
	    			System.out.println(e);
	    			}
	    			
	    			Cookie[] sessionCookie = null;
	    			sessionCookie = request.getCookies();
	    			System.out.println(sessionCookie[0].getValue());
	    			System.out.println(user.getUserNickname());
	    			System.out.println(response);
	    			if (UsernameExist(user.getUserNickname(),response,sessionCookie[0].getValue())) //&& (user.getEmail() != sessionCookie[0].getValue()))
	    			{
	    				
	    				 response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	    				return;
	    			}
	    		else {	
	    			final String name = user.getUserName();
	    			final String password =user.getPwd();
	    			final String nickname = user.getUserNickname();
	    			final String desc = user.getDescription();
	    	        final String address = user.getAddress();
	    	        final String phoneNumber = user.getPhoneNumber();
	    	        final String email = user.getEmail();
	    	        final String photo = user.getImageUrl();
	    	        if (email != null && name != null &&
	    	                !email.isEmpty() && !name.isEmpty() ) {
	    	            response.setStatus(HttpServletResponse.SC_OK);
	    	        } else {
	    	            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	    	        }
	    			PreparedStatement pstmt = conn.prepareStatement(DBQueries.UPDATE_USER_DETAILS);
	    			pstmt.setString(1,email);
	    			pstmt.setString(2,name);
	    			pstmt.setString(3,address);
	    			pstmt.setString(4,phoneNumber);
	    			pstmt.setString(5,password);
	    			pstmt.setString(6,nickname);
	    			pstmt.setString(7,desc);
	    			pstmt.setString(8,photo);
	    			pstmt.setString(9, email);
	    			System.out.println(pstmt);
	    			pstmt.executeUpdate();
	    			
	    		}
	    			
	    	        
	    		}catch ( SQLException  e)
	    			{
	    			
	    				//log error 
	    				//cntx.log("Error during database initialization",e);
	    				e.printStackTrace();
	    			}

	    		
	    			
	    		}
	    		
				


			} catch (SQLException e) {
				getServletContext().log("Error while querying for customers", e);
				response.sendError(500);//internal server error
			}
			conn.close();
			
			

		} //try1
		catch (SQLException | NamingException e) {
			getServletContext().log("Error while closing connection", e);
			response.sendError(500);//internal server error
		}//catch1


	}//post
	protected Boolean UsernameExist(String username, HttpServletResponse response,String cookEmail) throws ServletException, IOException
	{
		System.out.println("baaaaa");
		int check = 0;
		try
		{
			Context context = new InitialContext();
			BasicDataSource ds = (BasicDataSource)context.lookup(
					getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM USER_DETAILS WHERE user_nickname ='"+username.toString()+"' AND email !='"+cookEmail+"'" );
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				check++;
	 		}
		 	rs.close();
		 	pstmt.close();
		 	conn.close();
		 	context.close();
		}
		catch(SQLException | NamingException e)
		{
			getServletContext().log("Error: Connection to DB or SELECT command are not good", e);
			response.sendError(500);
		}
		System.out.println(check);
		if (check > 0)
			return true;
		else
			return false;
	}

}
