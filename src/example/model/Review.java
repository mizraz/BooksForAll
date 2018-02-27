package example.model;

import org.apache.derby.iapi.types.SQLTimestamp;

// TODO: Auto-generated Javadoc
/**
 * The Class Review.
 */
public class Review {
	
	/** The email. */
	private String email;
	
	/** The book id. */
	private String bookId;
	
	/** The description. */
	private String description;
	
	/** The is approved. */
	private String isApproved;
	
	/** The user nickname. */
	private String userNickname;
	
	/** The user image url. */
	private String userImageUrl;
	
	/** The date review. */
	private String dateReview;
	
	/**
	 * Instantiates a new review.
	 */
	public Review () {
		
	}
	
	/**
	 * Instantiates a new review.
	 *
	 * @param email the email
	 * @param bookId the book id
	 * @param description the description
	 * @param isApproved the is approved
	 * @param userNickname the user nickname
	 * @param userImageUrl the user image url
	 * @param dateReview the date review
	 */
	public Review (String email, String bookId, String description, String isApproved, String userNickname, String userImageUrl, String dateReview) {
		this.email = email;
		this.bookId = bookId;
		this.description = description;
		this.isApproved = isApproved;
		this.userNickname = userNickname;
		this.userImageUrl = userImageUrl;
		this.dateReview = dateReview;
	}
	
	/**
	 * Gets the date review.
	 *
	 * @return the date review
	 */
	public String getDateReview() {
		return dateReview;
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Gets the user nickname.
	 *
	 * @return the user nickname
	 */
	public String getUserNickname() {
		return userNickname;
	}
	
	/**
	 * Gets the user image url.
	 *
	 * @return the user image url
	 */
	public String getUserImageUrl() {
		return userImageUrl;
	}
	
	/**
	 * Gets the checks if is approved.
	 *
	 * @return the checks if is approved
	 */
	public String getIsApproved() {
		return isApproved;
	}
	


}
