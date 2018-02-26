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
import example.AppConstants;
import example.model.User;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet(urlPatterns = { 
		"/login"
})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
//		Cookie[] sessionCookie = null;
//		sessionCookie = request.getCookies();
//		if (sessionCookie == null) //if no session is open
//		{
//			response.sendRedirect("http://localhost:8080/ExampleServletv3/KREbooks/registerANDlogin/login.html");
//		}
//		//else
//		//{
//			response.sendRedirect("http://localhost:8080/ExampleServletv3/KREbooks/registerANDlogin/login.html");
//		//}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	try {
		Cookie cook = null;
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
		/*report an error*/
		}
		String data = jb.toString();
		Gson gson = new Gson();
		Type type = new TypeToken<User>(){}.getType();
		User users = gson.fromJson(data, type);
		System.out.println(users.getUserNickname());
		
		int checkUser= 0;
		
			
			Context context = new InitialContext();
			BasicDataSource ds = (BasicDataSource)context.lookup(
					getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
			
			Connection conn = ds.getConnection();
			Collection<User> userResult = new ArrayList<User>();
			PreparedStatement pstmt;
			
			pstmt = conn.prepareStatement("SELECT * FROM USER_DETAILS WHERE user_nickname = '"+users.getUserNickname()+"' AND pwd = '"+users.getPwd() +"'");
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				checkUser++;
				User usr = new User(rs.getString(1),rs.getString(2),rs.getString(6),rs.getString(5),rs.getString(3),rs.getString(8),rs.getString(4),rs.getString(7));
				System.out.println(usr);
				userResult.add(usr);
				if(checkUser == 1)
				{
					cook = new Cookie("email",usr.getEmail());
					cook.setMaxAge(60*60*24);
					response.addCookie(cook);
					System.out.println(cook.getValue());
				}
				
			}
			
			rs.close();	
			pstmt.close();	
		conn.close();
		System.out.println(checkUser);
		if (checkUser == 1)
		{
			
			Gson gsonRet = new Gson();
			//convert from customers collection to json
			String userRet = gsonRet.toJson(userResult, AppConstants.USER_COLLECTION);
			response.addHeader("Content-Type", "application/json");
			PrintWriter writer = response.getWriter();
			writer.println(userRet);
			writer.close();
		}
		else
			
		{
			
			PrintWriter writer = response.getWriter();
			writer.println("Failure");
			writer.close();
			
		}
	}
        catch (SQLException | NamingException e) {
    		getServletContext().log("Error while closing connection", e);
    		response.sendError(500);//internal server error
    	}

}
}
