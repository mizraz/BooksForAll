package example.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Ebook.
 */
public class Ebook {

	/**
	 * Instantiates a new ebook.
	 *
	 * @param bookId the book id
	 * @param title the title
	 * @param author the author
	 * @param price the price
	 * @param imageUrl the image url
	 * @param description the description
	 */
	public Ebook(String bookId, String title, String author, String price, String imageUrl, String description) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.price = price;
		this.imageUrl = imageUrl;
		this.description = description;		
		
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
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
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
	 * Gets the image url.
	 *
	 * @return the image url
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/** The book id. */
	private String bookId;
	
	/** The title. */
	private String title;
	
	/** The author. */
	private String author;
	
	/** The price. */
	private String price;
	
	/** The image url. */
	private String imageUrl;
	
	/** The description. */
	private String description;
	
}
