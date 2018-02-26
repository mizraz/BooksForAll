package DB;

public class DBConsts {

	public enum SqlColumns{
		SCROLL("current_scroll"),
		REVIEW_DESCRIPTION("review_description"),
		REVIEW_IS_APPROVED("is_review_approved"),
		REVIEW_DATE("date_reviewed"),
		BOOK_ID("book_id"),
		TITLE("title"),
		AUTHOR("author"),
		BOOK_IMAGE_URL("book_image_url"),
		BOOK_DESCRIPTION("book_description"),
		EMAIL("email"),
		LIKED("is_liked"),
		PRICE("price"),
		
		PURCHASE_TIME("date_purchased"),
		USER_NAME("user_name"),
		USER_ADDRESS("address"),
		USER_NIECKNAME("user_nickname"),
		USER_DESCRIPTION("description"),
		USER_PWD("pwd"),
		USER_PHONE_NUM("phone_number"),
		USER_IMAGE("image_url")
		;
		
		String columnName;
		SqlColumns(String columnName){
			 this.columnName = columnName; 
		}
		public String getName(){
			return columnName;
		}

	};
	
	
	public enum SqlTables{
		REVIEWS("ALL_REVIEWS"),
		USERS_DETAILS("USER_DETAILS"),
		USER_PURCHASES("USER_PURCHASES"),
		EBOOKS("EBOOKS"),

		;
		
		String tableName;
		SqlTables(String tableName){
			 this.tableName = tableName; 
		}
		public String getName(){
			return tableName;
		}

	};
	
	
	
	
}
