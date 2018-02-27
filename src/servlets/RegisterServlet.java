package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.servlet.http.Cookie;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DB.DBQueries;
import utils.AppConstants;
import model.User;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class UserRegisterServlet.
 */
@WebServlet(urlPatterns = { 
		"/UserRegisterServlet"
})
public class RegisterServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new register servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		StringBuffer buff = new StringBuffer();          //create buffer to read the request
		String line = null;
		try
		{   //read the request into buffer
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
			buff.append(line);
		}
		catch (Exception e)
		{
		/*error*/
		}
		//create new user from the data received
		String data = buff.toString();
		Gson gson = new Gson();
		Type type = new TypeToken<User>(){}.getType();
		User user = gson.fromJson(data, type);
		//check if user with nickname/email passed by data  already exists
		if (UsernameExist(user.getUserNickname(),user.getEmail(),response))
		{    //if true,respond with failure, the user on the other side will receive a message that the nickname/email is taken
			 response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
	else { 
			
		    //if the nickname/email is not taken create strings corresponding to the passed values
			final String name = user.getUserName();
			final String password =user.getPwd();
			final String nickname = user.getUserNickname();
			final String desc = user.getDescription();
			final String address = user.getAddress();
			final String phoneNumber = user.getPhoneNumber();
			final String email = user.getEmail();
			final String photo = user.getImageUrl();
			if (email != null && nickname != null &&
                !email.isEmpty() && !nickname.isEmpty() ) {
            response.setStatus(HttpServletResponse.SC_OK); //make sure the nickname/email is not empty
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
	try
		{
		//connect to database
		Context context = new InitialContext();
	
		BasicDataSource ds = (BasicDataSource)context.lookup(
				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
		Connection conn = ds.getConnection();
		    //insert new user into USERS_TABLE
			PreparedStatement pstmt = conn.prepareStatement(DBQueries.INSERT_USER_DETAILS);
			pstmt.setString(1,email);
			pstmt.setString(2,name);
			pstmt.setString(3,address);
			pstmt.setString(4,phoneNumber);
			pstmt.setString(5,password);
			pstmt.setString(6,nickname);
			pstmt.setString(7,desc);
			pstmt.setString(8,photo);
			
			pstmt.executeUpdate();
			//initialize cookie for further use
			Cookie cook = null;
			cook = new Cookie("email",email);
			cook.setMaxAge(120*3);
			response.addCookie(cook);
			
		}
		catch ( SQLException | NamingException e)
		{
		
			e.printStackTrace();
			
		}
		

    }
	
		
		return;
	}
	
	/**
	 * Username exist.
	 *
	 * @param username the username
	 * @param email the email
	 * @param response the response
	 * @return the boolean
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//function checks if user that have the nickname/email identical to the passed in data, already exists
	protected Boolean UsernameExist(String username,String email, HttpServletResponse response) throws ServletException, IOException
	{
		int check = 0;//flag if user exists
		try
		{   // connect to db
			Context context = new InitialContext();
			BasicDataSource ds = (BasicDataSource)context.lookup(
					getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
			Connection conn = ds.getConnection();
			//check if user that have the nickname/email identical to the passed in data, already exists in the USERS_TABLE 
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM USER_DETAILS WHERE user_nickname ='"+username.toString()+"' OR email='"+email.toString()+"'");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				//if user already exists change flag
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
		//return the flag
		if (check > 0)
			return true;
		else
			return false;
	}

}
