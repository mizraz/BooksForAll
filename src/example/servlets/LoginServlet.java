package example.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javax.servlet.http.HttpSession;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import DB.DBQueries;
import example.AppConstants;
import example.model.User;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class loginServlet.
 */
@WebServlet(urlPatterns = { 
		"/login"
})
public class LoginServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new login servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        
    }

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	try {
		Cookie cook = null;                       //create cookie for further use
		StringBuffer buff = new StringBuffer();   //create buffer to read the request into
		String line = null;
		try
		{
			BufferedReader reader = request.getReader(); //read the request to the buffer 
			while ((line = reader.readLine()) != null)
			buff.append(line);
		}
		catch (Exception e)
		{
		/*error*/
		}
		//create user fields from the data received, to check if user exists
		String data = buff.toString();
		Gson gson = new Gson();
		Type type = new TypeToken<User>(){}.getType();
		User users = gson.fromJson(data, type);
		
		//flag if such user exists
		int checkUser= 0;
		
			//connection to the DB
			Context context = new InitialContext();
			BasicDataSource ds = (BasicDataSource)context.lookup(
					getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
			
			Connection conn = ds.getConnection();
			Collection<User> userResult = new ArrayList<User>();
			PreparedStatement pstmt;
			//check if user with nickname and password passed in data exists
			pstmt = conn.prepareStatement("SELECT * FROM USER_DETAILS WHERE user_nickname = '"+users.getUserNickname()+"' AND pwd = '"+users.getPwd() +"'");
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{   //if user exists change the flag. Create new user to response with
				checkUser++;
				User usr = new User(rs.getString(1),rs.getString(2),rs.getString(6),rs.getString(5),rs.getString(3),rs.getString(8),rs.getString(4),rs.getString(7));
				System.out.println(usr);
				userResult.add(usr);
				if(checkUser == 1)
				{   //initialize cookie for further use
					cook = new Cookie("email",usr.getEmail());
					cook.setMaxAge(60*60*24);
					response.addCookie(cook);
					System.out.println(cook.getValue());
				}
				
			}
			
			rs.close();	
			pstmt.close();	
		conn.close();
		
		if (checkUser == 1)
		{
			//check if admin loged in and set the session attribute accordingly
			HttpSession session = request.getSession();
			if (users.getUserNickname().compareTo("admin") == 0) 
			{
				session.setAttribute("isAdmin", "1");
			} else {
				session.setAttribute("isAdmin", "0");

			}
			
			String hashOfNicknameAndTime = users.getUserNickname() + Long.toString(System.currentTimeMillis());
			byte[] bytesOfMessage = hashOfNicknameAndTime.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			String stringOfHashOfNicknameAndTime = new String(thedigest);

			session.setAttribute("hashForSessionId", stringOfHashOfNicknameAndTime);
			SessionsActiveTable.setOfActiveSessions.add(stringOfHashOfNicknameAndTime);
			
			
			
			//return user if user who logged in was found via json
			Gson gsonRet = new Gson();
			//convert from users collection to json
			String userRet = gsonRet.toJson(userResult, AppConstants.USER_COLLECTION);
			response.addHeader("Content-Type", "application/json");
			PrintWriter writer = response.getWriter();
			writer.println(userRet);
			writer.close();	
		}
		else
			
		{
		    //return "Failure" if user who logged in was not found	
			PrintWriter writer = response.getWriter();
			writer.println("Failure");
			writer.close();
			
		}
	}
        catch (SQLException | NamingException e) {
    		getServletContext().log("Error while closing connection", e);
    		response.sendError(500);//internal server error
    	} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}
