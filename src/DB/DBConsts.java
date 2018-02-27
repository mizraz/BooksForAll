package DB;

// TODO: Auto-generated Javadoc
/**
 * The Class DBConsts.
 */
public class DBConsts {

	/**
	 * The Enum SqlColumns.
	 */
	public enum SqlColumns{
		
		/** The scroll. */
		SCROLL("current_scroll"),
		
		/** The review description. */
		REVIEW_DESCRIPTION("review_description"),
		
		/** The review is approved. */
		REVIEW_IS_APPROVED("is_review_approved"),
		
		/** The review date. */
		REVIEW_DATE("date_reviewed"),
		
		/** The book id. */
		BOOK_ID("book_id"),
		
		/** The title. */
		TITLE("title"),
		
		/** The author. */
		AUTHOR("author"),
		
		/** The book image url. */
		BOOK_IMAGE_URL("book_image_url"),
		
		/** The book description. */
		BOOK_DESCRIPTION("book_description"),
		
		/** The email. */
		EMAIL("email"),
		
		/** The liked. */
		LIKED("is_liked"),
		
		/** The price. */
		PRICE("price"),
		
		/** The purchase time. */
		PURCHASE_TIME("date_purchased"),
		
		/** The user name. */
		USER_NAME("user_name"),
		
		/** The user address. */
		USER_ADDRESS("address"),
		
		/** The user nieckname. */
		USER_NIECKNAME("user_nickname"),
		
		/** The user description. */
		USER_DESCRIPTION("description"),
		
		/** The user pwd. */
		USER_PWD("pwd"),
		
		/** The user phone num. */
		USER_PHONE_NUM("phone_number"),
		
		/** The user image. */
		USER_IMAGE("image_url")
		;
		
		/** The column name. */
		String columnName;
		
		/**
		 * Instantiates a new sql columns.
		 *
		 * @param columnName the column name
		 */
		SqlColumns(String columnName){
			 this.columnName = columnName; 
		}
		
		/**
		 * Gets the name.
		 *
		 * @return the name
		 */
		public String getName(){
			return columnName;
		}

	};
	
	
	/**
	 * The Enum SqlTables.
	 */
	public enum SqlTables{
		
		/** The reviews. */
		REVIEWS("ALL_REVIEWS"),
		
		/** The users details. */
		USERS_DETAILS("USER_DETAILS"),
		
		/** The user purchases. */
		USER_PURCHASES("USER_PURCHASES"),
		
		/** The ebooks. */
		EBOOKS("EBOOKS"),

		;
		
		/** The table name. */
		String tableName;
		
		/**
		 * Instantiates a new sql tables.
		 *
		 * @param tableName the table name
		 */
		SqlTables(String tableName){
			 this.tableName = tableName; 
		}
		
		/**
		 * Gets the name.
		 *
		 * @return the name
		 */
		public String getName(){
			return tableName;
		}

	};
	
	
	
	
}
