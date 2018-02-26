package example.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import example.AppConstants;
import example.URIConsts;
import example.model.Transaction;

/**
 * Servlet implementation class TransactionsServlet
 */
@WebServlet(
		description = "Handles transactions info",
		urlPatterns = { 
				"/transactions",
				"/transactions/*" /*will have 2 params: first, second date*/
		})
public class TransactionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionsServlet() {
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

			Collection<Transaction> transactionResult = new ArrayList<Transaction>(); 
			String uri = request.getRequestURI();
			String firstDateAsString = Long.toString(new Long(0));
			String secondDateAsString = Long.toString(new Long(System.currentTimeMillis())); 
    		if (uri.indexOf(URIConsts.FIRST_DATE) != -1 && uri.indexOf(URIConsts.SECOND_DATE) != -1 ) {
			firstDateAsString = uri.substring(uri.indexOf(URIConsts.FIRST_DATE) + URIConsts.FIRST_DATE.length() + 1, 
					uri.indexOf("/" + URIConsts.SECOND_DATE));
			secondDateAsString = uri.substring(uri.indexOf(URIConsts.SECOND_DATE) + URIConsts.SECOND_DATE.length() + 1);
    		}
			PreparedStatement stmt;
			try {
				Long firstDateLong = Long.parseLong(firstDateAsString);
				Long secondDateLong = Long.parseLong(secondDateAsString);
				stmt = conn.prepareStatement(DBQueries.SELECT_TRANSACTIONS_BETWEEN_2_DATES);
				stmt.setTimestamp(1,new Timestamp(firstDateLong));
				stmt.setTimestamp(2,new Timestamp(secondDateLong));
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()){
					transactionResult.add(new Transaction(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4)));
					System.out.println("Transaction: email " + rs.getString(1) + " bookId " + rs.getString(2) + " time " + rs.getString(3)
					+ " price: "+ rs.getString(4));
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				getServletContext().log("Error while querying for transactions", e);
				response.sendError(500);//internal server error
			}
			for (Iterator iterator = transactionResult.iterator(); iterator.hasNext();) {
				Transaction transaction = (Transaction) iterator.next();
				System.out.println("email: "+ transaction.getEmail() + " bookId: "+ transaction.getBookId() 
									+ "transaction time : "+ transaction.getDatePurchased());
			}



			conn.close();

			Gson gson = new Gson();
			//convert from customers collection to json
			String transactionJsonResult = gson.toJson(transactionResult, AppConstants.TRANSACTION_COLLECTION);
			response.addHeader("Content-Type", "application/json");
			PrintWriter writer = response.getWriter();
			writer.println(transactionJsonResult);
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
		doGet(request, response);
	}

}
