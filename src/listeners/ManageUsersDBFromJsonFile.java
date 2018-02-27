package listeners;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DB.DBQueries;
import utils.AppConstants;
import model.User;



// TODO: Auto-generated Javadoc
/**
 * An example listener that reads the customer json file and populates the data into a Derby database.
 */
@WebListener
public class ManageUsersDBFromJsonFile implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ManageUsersDBFromJsonFile() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Table already exists.
     *
     * @param e the e
     * @return true, if successful
     */
    //utility that checks whether the customer tables already exists
    private boolean tableAlreadyExists(SQLException e) {
        boolean exists;
        if(e.getSQLState().equals("X0Y32")) {
            exists = true;
        } else {
            exists = false;
        }
        return exists;
    }

	/**
	 * Context initialized.
	 *
	 * @param event the event
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
    public void contextInitialized(ServletContextEvent event)  { 
    	ServletContext cntx = event.getServletContext();
    	
    	try{
    		
    		//obtain CustomerDB data source from Tomcat's context
    		Context context = new InitialContext();
    		BasicDataSource ds = (BasicDataSource)context.lookup(
    				cntx.getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
    		Connection conn = ds.getConnection();
    		
    		boolean created = false;
    		try{
				
    			
    			try {
	    			Statement stmt3 = conn.createStatement();
	    			stmt3.executeUpdate(DBQueries.DROP_USERS_TABLE);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    			
    			

    			
    			//create Customers table
    			Statement stmt = conn.createStatement();
    			stmt.executeUpdate(DBQueries.CREATE_USER_DETAILS_TABLE);
    			//commit update
        		conn.commit();
        		stmt.close();
    		}catch (SQLException e){
    			//check if exception thrown since table was already created (so we created the database already 
    			//in the past
    			created = tableAlreadyExists(e);
    			if (!created){
    				throw e;//re-throw the exception so it will be caught in the
    				//external try..catch and recorded as error in the log
    			}
    		}
    		
    		//if no database exist in the past - further populate its records in the table
    		if (!created){
    			//populate customers table with customer data from json file
    			Collection<User> users = loadusers(cntx.getResourceAsStream(File.separator +
    														   AppConstants.USER_DETAILS_FILE));
    			PreparedStatement pstmt = conn.prepareStatement(DBQueries.INSERT_USER_DETAILS);
    			for (User user : users){
    				System.out.println("insert to USER TABLE " + user.getEmail());
    				pstmt.setString(1,user.getEmail());
    				pstmt.setString(2,user.getUserName());
    				pstmt.setString(3,user.getAddress());
    				pstmt.setString(4,user.getPhoneNumber());
    				pstmt.setString(5,user.getPwd());
    				pstmt.setString(6,user.getUserNickname());
    				pstmt.setString(7,user.getDescription());
    				pstmt.setString(8,user.getImageUrl());
    				pstmt.executeUpdate();
    			}

    			//commit update
    			conn.commit();
    			//close statements
    			pstmt.close();
    		}
    		

    		//close connection
    		conn.close();

    	} catch (IOException | SQLException | NamingException e) {
    		//log error 
    		cntx.log("Error during database initialization",e);
    	}
    }

	/**
	 * Context destroyed.
	 *
	 * @param event the event
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
    public void contextDestroyed(ServletContextEvent event)  { 
    	 ServletContext cntx = event.getServletContext();
    	 
         //shut down database
    	 try {
     		//obtain CustomerDB data source from Tomcat's context and shutdown
     		Context context = new InitialContext();
     		BasicDataSource ds = (BasicDataSource)context.lookup(
     				cntx.getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.SHUTDOWN);
     		ds.getConnection();
     		ds = null;
		} catch (SQLException | NamingException e) {
			cntx.log("Error shutting down database",e);
		}

    }
    
    
    /**
     * Loads customers data from json file that is read from the input stream into 
     * a collection of Customer objects.
     *
     * @param is input stream to json file
     * @return collection of customers
     * @throws IOException Signals that an I/O exception has occurred.
     */
	private Collection<User> loadusers(InputStream is) throws IOException{
		
		//wrap input stream with a buffered reader to allow reading the file line by line
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder jsonFileContent = new StringBuilder();
		//read line by line from file
		String nextLine = null;
		while ((nextLine = br.readLine()) != null){
			jsonFileContent.append(nextLine);
		}

		Gson gson = new Gson();
		//this is a require type definition by the Gson utility so Gson will 
		//understand what kind of object representation should the json file match
		Type type = new TypeToken<Collection<User>>(){}.getType();
		Collection<User> users = gson.fromJson(jsonFileContent.toString(), type);
		
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			user.printUser();
			
		}
		
		//close
		br.close();	
		return users;

	}
	
}


