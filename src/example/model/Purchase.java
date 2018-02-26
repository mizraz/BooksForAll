package example.model;


public class Purchase {

	public Purchase(String email, String bookId, String isLiked, String price, String datePurchased, String currentScroll) {
		this.email = email;
		this.bookId = bookId;
		this.isLiked = isLiked;
		this.price = price;
		this.datePurchased = datePurchased;
		this.currentScroll = currentScroll;
	}
	
	
	public String getEmail() {
		return email;
	}
	public String getBookId() {
		return bookId;
	}
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
//	}
	private String email;
	private String bookId;
	private String isLiked;
//	private double price;
//	private java.sql.Timestamp datePurchased;
//	private int currentScroll;
	private String price;
	public String getPrice() {
		return price;
	}
	public String getDatePurchased() {
		return datePurchased;
	}
	public String getCurrentScroll() {
		return currentScroll;
	}
	private String datePurchased;
	private String currentScroll;	

	
	
}
