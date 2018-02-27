package utils;
import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;

import model.Customer;
import model.Ebook;
import model.Like;
import model.Purchase;
import model.Review;
import model.ScrollObj;
import model.Transaction;
import model.User;

// TODO: Auto-generated Javadoc
/**
 * A simple place to hold global application constants.
 */
public interface AppConstants {

	/** The customers. */
	public final String CUSTOMERS = "customers";
	
	/** The purchases. */
	public final String PURCHASES = "purchases";
	
	/** The ebooks. */
	public final String EBOOKS = "ebooks";
	
	/** The reviews. */
	public final String REVIEWS = "reviews";
	
	/** The user details. */
	public final String USER_DETAILS = "usersDetails";
	
	/** The customers file. */
	public final String CUSTOMERS_FILE = CUSTOMERS + ".json";
	
	/** The purchases file. */
	public final String PURCHASES_FILE = PURCHASES + ".json";
	
	/** The ebooks file. */
	public final String EBOOKS_FILE = EBOOKS + ".json";
	
	/** The reviews file. */
	public final String REVIEWS_FILE = REVIEWS + ".json";
	
	/** The user details file. */
	public final String USER_DETAILS_FILE = USER_DETAILS + ".json";

	

	/** The customer collection. */
	public final Type CUSTOMER_COLLECTION = new TypeToken<Collection<Customer>>() {}.getType();
	
	/** The review collection. */
	public final Type REVIEW_COLLECTION = new TypeToken<Collection<Review>>() {}.getType();
	
	/** The user collection. */
	public final Type USER_COLLECTION = new TypeToken<Collection<User>>() {}.getType();
	
	/** The transaction collection. */
	public final Type TRANSACTION_COLLECTION = new TypeToken<Collection<Transaction>>() {}.getType();
	
	/** The purchase collection. */
	public final Type PURCHASE_COLLECTION = new TypeToken<Collection<Purchase>>() {}.getType();
	
	/** The scroll collection. */
	public final Type SCROLL_COLLECTION = new TypeToken<Collection<ScrollObj>>() {}.getType();
	
	/** The ebooks collection. */
	public final Type EBOOKS_COLLECTION = new TypeToken<Collection<Ebook>>() {}.getType();
	
	/** The purchases collection. */
	public final Type PURCHASES_COLLECTION = new TypeToken<Collection<Purchase>>() {}.getType();
	
	/** The like collection. */
	public final Type LIKE_COLLECTION = new TypeToken<Collection<Like>>() {}.getType();
	
	/** The user details collection. */
	public final Type USER_DETAILS_COLLECTION = new TypeToken<Collection<User>>() {}.getType();
	
	/** The db name. */
	//derby constants
	public final String DB_NAME = "DB_NAME";
	
	/** The db datasource. */
	public final String DB_DATASOURCE = "DB_DATASOURCE";
	
	/** The protocol. */
	public final String PROTOCOL = "jdbc:derby:"; 
	
	/** The open. */
	public final String OPEN = "Open";
	
	/** The shutdown. */
	public final String SHUTDOWN = "Shutdown";
	
	/** The create customers table. */
	//sql statements
	public final String CREATE_CUSTOMERS_TABLE = "CREATE TABLE CUSTOMER(Name varchar(100),"
			+ "City varchar(100),"
			+ "Country varchar(100))";
	
	/** The insert customer stmt. */
	public final String INSERT_CUSTOMER_STMT = "INSERT INTO CUSTOMER VALUES(?,?,?)";
	
	/** The select all customers stmt. */
	public final String SELECT_ALL_CUSTOMERS_STMT = "SELECT * FROM CUSTOMER";
	
	/** The select customer by name stmt. */
	public final String SELECT_CUSTOMER_BY_NAME_STMT = 
			"SELECT * FROM CUSTOMER "
			+ "WHERE Name=?";
	
	/** The name. */
	public final String NAME = "name";
	
	
	
	
	
}
