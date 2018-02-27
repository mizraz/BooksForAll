package example.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Transaction.
 */
public class Transaction {
	
	/** The book id. */
	private String bookId;
	
	/** The email. */
	private String email;
	
	/** The price. */
	private String price;
	
	/** The date purchased. */
	private String datePurchased;
	
	/**
	 * Instantiates a new transaction.
	 *
	 * @param bookId the book id
	 * @param email the email
	 * @param datePurchased the date purchased
	 * @param price the price
	 */
	public Transaction (String bookId, String email, String datePurchased, String price) {
		this.bookId = bookId;
		this.email = email;
		this.datePurchased = datePurchased;
		this.price = price;
		
	}
	
	/**
	 * Gets the book id.
	 *
	 * @return the book id
	 */
	public String getBookId() {
		return bookId;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	
	/**
	 * Gets the date purchased.
	 *
	 * @return the date purchased
	 */
	public String getDatePurchased() {
		return datePurchased;
	}

}
