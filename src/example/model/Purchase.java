package example.model;


// TODO: Auto-generated Javadoc
/**
 * The Class Purchase.
 */
public class Purchase {

	/**
	 * Instantiates a new purchase.
	 *
	 * @param email the email
	 * @param bookId the book id
	 * @param isLiked the is liked
	 * @param price the price
	 * @param datePurchased the date purchased
	 * @param currentScroll the current scroll
	 */
	public Purchase(String email, String bookId, String isLiked, String price, String datePurchased, String currentScroll) {
		this.email = email;
		this.bookId = bookId;
		this.isLiked = isLiked;
		this.price = price;
		this.datePurchased = datePurchased;
		this.currentScroll = currentScroll;
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
	 * Gets the book id.
	 *
	 * @return the book id
	 */
	public String getBookId() {
		return bookId;
	}
	
	/**
	 * Gets the checks if is liked.
	 *
	 * @return the checks if is liked
	 */
	public String getIsLiked() {
		return isLiked;
	}
//	public double getPrice() {
//		return price;
//	}
//	public java.sql.Timestamp getDatePurchased() {
//		return datePurchased;
//	}
//	public int getCurrentScroll() {
//		return currentScroll;
/** The email. */
//	}
	private String email;
	
	/** The book id. */
	private String bookId;
	
	/** The is liked. */
	private String isLiked;
//	private double price;
//	private java.sql.Timestamp datePurchased;
/** The price. */
//	private int currentScroll;
	private String price;
	
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
	
	/**
	 * Gets the current scroll.
	 *
	 * @return the current scroll
	 */
	public String getCurrentScroll() {
		return currentScroll;
	}
	
	/** The date purchased. */
	private String datePurchased;
	
	/** The current scroll. */
	private String currentScroll;	

	
	
}
