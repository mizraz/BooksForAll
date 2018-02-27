package example.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Like.
 */
public class Like {

	/** The email. */
	private String email;
	
	/** The book id. */
	private String bookId;
	
	/** The user nickname. */
	private String userNickname;
	
	/** The is liked. */
	private String isLiked;

	
	/**
	 * Gets the checks if is liked.
	 *
	 * @return the checks if is liked
	 */
	public String getIsLiked() {
		return isLiked;
	}

	/**
	 * Instantiates a new like.
	 */
	public Like() {
		
	}
	
	/**
	 * Instantiates a new like.
	 *
	 * @param email the email
	 * @param bookId the book id
	 * @param userNickname the user nickname
	 * @param isLiked the is liked
	 */
	public Like (String email, String bookId, String userNickname, String isLiked) {
		this.email = email;
		this.bookId = bookId;
		this.userNickname = userNickname;
		this.isLiked = isLiked;

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
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userNickname;
	}
	

	
	
}
