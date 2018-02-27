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
import java.sql.Timestamp;
import java.util.Collection;

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
import model.Review;



// TODO: Auto-generated Javadoc
/**
 * An example listener that reads the customer json file and populates the data into a Derby database.
 */
@WebListener
public class ManageReviewsDBFromJsonFile implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ManageReviewsDBFromJsonFile() {
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
    			
    			
    			
//    			System.out.println(DBQueries.DROP_REVIEWS_TABLE_CONSTRAINT_PK);
//    			Statement stmt6 = conn.createStatement();
//    			stmt6.executeUpdate(DBQueries.DROP_REVIEWS_TABLE_CONSTRAINT_PK);    			
//
//    			Statement stmt4 = conn.createStatement();
//    			stmt4.executeUpdate(DBQueries.DROP_REVIEWS_TABLE_CONSTRAINT_EMAIL);
//    			Statement stmt5 = conn.createStatement();
//    			stmt5.executeUpdate(DBQueries.DROP_EBOOKS_TABLE_CONSTRAINT_EBOOK_ID);
    			
    			
    			
				try {
	    			Statement stmt3 = conn.createStatement();
	    			stmt3.executeUpdate(DBQueries.DROP_ALL_REVIEWS_TABLE);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    			

    			
    			//create Customers table
    			Statement stmt = conn.createStatement();
    			stmt.executeUpdate(DBQueries.CREATE_ALL_REVIEWS_TABLE);
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
    			Collection<Review> reviews = loadReviews(cntx.getResourceAsStream(File.separator +
    														   AppConstants.REVIEWS_FILE));
    			PreparedStatement pstmt = conn.prepareStatement(DBQueries.INSERT_REVIEW);
    			for (Review review : reviews){
    				System.out.println("insert to REVIEW TABLE " + review.getEmail() + "is Approved: " + review.getIsApproved());
    				pstmt.setString(1,review.getEmail());
    				pstmt.setString(2,review.getBookId());
    				pstmt.setString(3,review.getDescription());
    				pstmt.setString(4,review.getIsApproved());
					pstmt.setTimestamp(5,Timestamp.valueOf(review.getDateReview()));
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
	private Collection<Review> loadReviews(InputStream is) throws IOException{
		
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
		Type type = new TypeToken<Collection<Review>>(){}.getType();
		Collection<Review> reviews = gson.fromJson(jsonFileContent.toString(), type);
		//close
		br.close();	
		return reviews;

	}
	
}


