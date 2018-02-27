package model;

// TODO: Auto-generated Javadoc
/**
 * The Class ScrollObj.
 */
public class ScrollObj {

	/**
	 * Instantiates a new scroll obj.
	 *
	 * @param email the email
	 * @param bookId the book id
	 * @param scroll the scroll
	 */
	public ScrollObj(String email, String bookId, String scroll) {
		this.currentScroll = scroll;
		this.bookId = bookId;
		this.email = email;
		
	}
	
	
	/** The current scroll. */
	private String currentScroll;
	
	/**
	 * Gets the scroll.
	 *
	 * @return the scroll
	 */
	public String getScroll() {
		return currentScroll;
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
	
	/** The book id. */
	private String bookId;
	
	/** The email. */
	private String email;
	
	
}
