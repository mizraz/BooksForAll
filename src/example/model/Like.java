package example.model;

public class Like {

	private String email;
	private String bookId;
	private String userNickname;
	private String isLiked;

	
	public String getIsLiked() {
		return isLiked;
	}

	public Like() {
		
	}
	
	public Like (String email, String bookId, String userNickname, String isLiked) {
		this.email = email;
		this.bookId = bookId;
		this.userNickname = userNickname;
		this.isLiked = isLiked;

	}
	
	public String getBookId() {
		return bookId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getUserName() {
		return userNickname;
	}
	

	
	
}
