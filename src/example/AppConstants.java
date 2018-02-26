package example;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;

import example.model.Customer;
import example.model.Ebook;
import example.model.Like;
import example.model.Purchase;
import example.model.Review;
import example.model.ScrollObj;
import example.model.Transaction;
import example.model.User;

/**
 * A simple place to hold global application constants
 */
public interface AppConstants {

	public final String CUSTOMERS = "customers";
	public final String PURCHASES = "purchases";
	public final String EBOOKS = "ebooks";
	public final String REVIEWS = "reviews";
	public final String USER_DETAILS = "usersDetails";
	
	public final String CUSTOMERS_FILE = CUSTOMERS + ".json";
	public final String PURCHASES_FILE = PURCHASES + ".json";
	public final String EBOOKS_FILE = EBOOKS + ".json";
	public final String REVIEWS_FILE = REVIEWS + ".json";
	public final String USER_DETAILS_FILE = USER_DETAILS + ".json";

	

	public final Type CUSTOMER_COLLECTION = new TypeToken<Collection<Customer>>() {}.getType();
	public final Type REVIEW_COLLECTION = new TypeToken<Collection<Review>>() {}.getType();
	public final Type USER_COLLECTION = new TypeToken<Collection<User>>() {}.getType();
	public final Type TRANSACTION_COLLECTION = new TypeToken<Collection<Transaction>>() {}.getType();
	public final Type PURCHASE_COLLECTION = new TypeToken<Collection<Purchase>>() {}.getType();
	public final Type SCROLL_COLLECTION = new TypeToken<Collection<ScrollObj>>() {}.getType();
	public final Type EBOOKS_COLLECTION = new TypeToken<Collection<Ebook>>() {}.getType();
	public final Type PURCHASES_COLLECTION = new TypeToken<Collection<Purchase>>() {}.getType();
	public final Type LIKE_COLLECTION = new TypeToken<Collection<Like>>() {}.getType();
	public final Type USER_DETAILS_COLLECTION = new TypeToken<Collection<User>>() {}.getType();
	//derby constants
	public final String DB_NAME = "DB_NAME";
	public final String DB_DATASOURCE = "DB_DATASOURCE";
	public final String PROTOCOL = "jdbc:derby:"; 
	public final String OPEN = "Open";
	public final String SHUTDOWN = "Shutdown";
	
	//sql statements
	public final String CREATE_CUSTOMERS_TABLE = "CREATE TABLE CUSTOMER(Name varchar(100),"
			+ "City varchar(100),"
			+ "Country varchar(100))";
	public final String INSERT_CUSTOMER_STMT = "INSERT INTO CUSTOMER VALUES(?,?,?)";
	public final String SELECT_ALL_CUSTOMERS_STMT = "SELECT * FROM CUSTOMER";
	public final String SELECT_CUSTOMER_BY_NAME_STMT = 
			"SELECT * FROM CUSTOMER "
			+ "WHERE Name=?";
	public final String NAME = "name";
	
	
	
	
	
}
