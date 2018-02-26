package example.model;

public class Transaction {
	
	private String bookId;
	private String email;
	private String price;
	private String datePurchased;
	
	public Transaction (String bookId, String email, String datePurchased, String price) {
		this.bookId = bookId;
		this.email = email;
		this.datePurchased = datePurchased;
		this.price = price;
		
	}
	
	public String getBookId() {
		return bookId;
	}
	public String getEmail() {
		return email;
	}
	public String getPrice() {
		return price;
	}
	public String getDatePurchased() {
		return datePurchased;
	}

}
