package example.model;

public class ScrollObj {

	public ScrollObj(String email, String bookId, String scroll) {
		this.currentScroll = scroll;
		this.bookId = bookId;
		this.email = email;
		
	}
	
	
	private String currentScroll;
	public String getScroll() {
		return currentScroll;
	}
	public String getBookId() {
		return bookId;
	}
	public String getEmail() {
		return email;
	}
	private String bookId;
	private String email;
	
	
}
