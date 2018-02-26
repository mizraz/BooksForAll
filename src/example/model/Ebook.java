package example.model;

public class Ebook {

	public Ebook(String bookId, String title, String author, String price, String imageUrl, String description) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.price = price;
		this.imageUrl = imageUrl;
		this.description = description;		
		
	}
	
	public String getBookId() {
		return bookId;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getPrice() {
		return price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public String getDescription() {
		return description;
	}
	private String bookId;
	private String title;
	private String author;
	private String price;
	private String imageUrl;
	private String description;
	
}
