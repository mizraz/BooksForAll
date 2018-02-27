package DB;

import DB.DBConsts.SqlColumns;
import DB.DBConsts.SqlTables;

// TODO: Auto-generated Javadoc
/**
 * The Class DBQueries.
 */
public class DBQueries {
	
	
		/** The Constant CREATE_USER_PURCHASES_TABLE. */
		public static final String CREATE_USER_PURCHASES_TABLE =
				"CREATE TABLE " + DBConsts.SqlTables.USER_PURCHASES.getName() + "(" +
				DBConsts.SqlColumns.EMAIL.getName() + " varchar(100), "+
				DBConsts.SqlColumns.BOOK_ID.getName() + " varchar(100), "+	
				DBConsts.SqlColumns.LIKED.getName() + " varchar(1), "+	
				DBConsts.SqlColumns.PRICE.getName() + " double, "+
				DBConsts.SqlColumns.PURCHASE_TIME.getName() + " timestamp, "+	
				DBConsts.SqlColumns.SCROLL.getName() + " int "+	//", " +
//				" PRIMARY KEY (" + SqlColumns.BOOK_ID.getName() + "," + SqlColumns.EMAIL.getName() + ") " + ", " +
//				" FOREIGN KEY (" + SqlColumns.BOOK_ID.getName() + ") REFERENCES " + SqlTables.EBOOKS.getName() + "(" + SqlColumns.BOOK_ID.getName() + ") , " + 
//				" FOREIGN KEY (" + SqlColumns.EMAIL.getName() + ") REFERENCES " + SqlTables.USERS_DETAILS.getName() + "(" + SqlColumns.EMAIL.getName() + ") " +
				")";
		
		/** The Constant CREATE_ALL_REVIEWS_TABLE. */
		public static final String CREATE_ALL_REVIEWS_TABLE =
				"CREATE TABLE " + DBConsts.SqlTables.REVIEWS.getName() + "(" +
				DBConsts.SqlColumns.EMAIL.getName() + " varchar(100), "+
				DBConsts.SqlColumns.BOOK_ID.getName() + " varchar(100), "+
				DBConsts.SqlColumns.REVIEW_DESCRIPTION.getName() + " varchar(5000), "+
				DBConsts.SqlColumns.REVIEW_IS_APPROVED.getName() + " varchar(1), "+
				DBConsts.SqlColumns.REVIEW_DATE.getName() + " timestamp " + //" , " +	
//				" PRIMARY KEY (" + SqlColumns.BOOK_ID.getName() + "," + SqlColumns.EMAIL.getName() + ") " + ", " +
//				" FOREIGN KEY (" + SqlColumns.BOOK_ID.getName() + ") REFERENCES " + SqlTables.EBOOKS.getName() + "(" + SqlColumns.BOOK_ID.getName() + ") , " + 
//				" FOREIGN KEY (" + SqlColumns.EMAIL.getName() + ") REFERENCES " + SqlTables.USERS_DETAILS.getName() + "(" + SqlColumns.EMAIL.getName() + ")  " +
				")";
		
		/** The Constant UPDATE_USER_DETAILS. */
		public static final String UPDATE_USER_DETAILS =
				"UPDATE "+  DBConsts.SqlTables.USERS_DETAILS.getName() +
				" SET " +DBConsts.SqlColumns.EMAIL.getName() + " = ? " + " , " +
				  DBConsts.SqlColumns.USER_NAME.getName() + " = ? " +" , " +
				  DBConsts.SqlColumns.USER_ADDRESS.getName() + " = ?" + " , "+
				  DBConsts.SqlColumns.USER_PHONE_NUM.getName() + " = ?" + " , " +
				  DBConsts.SqlColumns.USER_PWD.getName() + " = ?" + " , " +
				  DBConsts.SqlColumns.USER_NIECKNAME.getName() + " =?"+ " , " +
				  DBConsts.SqlColumns.USER_DESCRIPTION.getName() + " = ?" + ", "+
				  DBConsts.SqlColumns.USER_IMAGE.getName() + " = ?  "+
				" WHERE " + DBConsts.SqlColumns.EMAIL.getName() + " = ? ";
		
		/** The Constant DROP_EBOOKS_TABLE_CONSTRAINT_PK. */
		public static final String DROP_EBOOKS_TABLE_CONSTRAINT_PK = 
				"ALTER TABLE " + SqlTables.EBOOKS.getName() + " " + 
				" DROP CONSTRAINT " + SqlTables.EBOOKS.getName() + "_PK";
		
		
		/** The Constant DROP_REVIEWS_TABLE_CONSTRAINT_PK. */
		public static final String DROP_REVIEWS_TABLE_CONSTRAINT_PK = 
				"ALTER TABLE " + SqlTables.REVIEWS.getName() + " " + 
				" DROP CONSTRAINT " + SqlTables.REVIEWS.getName() + "_PK";
		
		/** The Constant DROP_PURCHASES_TABLE_CONSTRAINT_PK. */
		public static final String DROP_PURCHASES_TABLE_CONSTRAINT_PK = 
				"ALTER TABLE " + SqlTables.USER_PURCHASES.getName() + " " + 
				" DROP CONSTRAINT " + SqlTables.USER_PURCHASES.getName() + "_PK";
		
		
		
		/** The Constant DROP_REVIEWS_TABLE_CONSTRAINT_EMAIL. */
		public static final String DROP_REVIEWS_TABLE_CONSTRAINT_EMAIL = 
				"ALTER TABLE " + SqlTables.REVIEWS.getName() + " " + 
				" DROP CONSTRAINT " + SqlColumns.BOOK_ID.getName() ;
		
		/** The Constant DROP_EBOOKS_TABLE_CONSTRAINT_EBOOK_ID. */
		public static final String DROP_EBOOKS_TABLE_CONSTRAINT_EBOOK_ID = 
				"ALTER TABLE " + SqlTables.REVIEWS.getName() + " " + 
				" DROP CONSTRAINT " + SqlColumns.EMAIL.getName() ;
		
		
		/** The Constant DROP_PURCHASES_TABLE_CONSTRAINT_EMAIL. */
		public static final String DROP_PURCHASES_TABLE_CONSTRAINT_EMAIL = 
				"ALTER TABLE " + SqlTables.USER_PURCHASES.getName() + " " + 
				" DROP CONSTRAINT " + SqlColumns.BOOK_ID.getName() ;
		
		/** The Constant DROP_PURCHASES_TABLE_CONSTRAINT_EBOOK_ID. */
		public static final String DROP_PURCHASES_TABLE_CONSTRAINT_EBOOK_ID = 
				"ALTER TABLE " + SqlTables.USER_PURCHASES.getName() + " " + 
				" DROP CONSTRAINT " + SqlColumns.EMAIL.getName() ;
		
		
		
		
		
		
		/** The Constant CREATE_USER_DETAILS_TABLE. */
		public static final String CREATE_USER_DETAILS_TABLE =
				"CREATE TABLE " + DBConsts.SqlTables.USERS_DETAILS.getName() + "(" +
				DBConsts.SqlColumns.EMAIL.getName() + " varchar(100), "+
				DBConsts.SqlColumns.USER_NAME.getName() + " varchar(100), "+
				DBConsts.SqlColumns.USER_ADDRESS.getName() + " varchar(100), "+
				DBConsts.SqlColumns.USER_PHONE_NUM.getName() + " varchar(100), "+
				DBConsts.SqlColumns.USER_PWD.getName() + " varchar(100), "+
				DBConsts.SqlColumns.USER_NIECKNAME.getName() + " varchar(100), "+
				DBConsts.SqlColumns.USER_DESCRIPTION.getName() + " varchar(5000), "+
				DBConsts.SqlColumns.USER_IMAGE.getName() + " varchar(1000) "+ //", " +
//				"PRIMARY KEY (" + SqlColumns.EMAIL.getName() + ") " +
				")";
		
		/** The Constant CREATE_EBOOKS_TABLE. */
		public static final String CREATE_EBOOKS_TABLE =
				"CREATE TABLE " + DBConsts.SqlTables.EBOOKS.getName() + "(" +
				DBConsts.SqlColumns.BOOK_ID.getName() + " varchar(100), "+
				DBConsts.SqlColumns.TITLE.getName() + " varchar(100), "+
				DBConsts.SqlColumns.AUTHOR.getName() + " varchar(100), "+
				DBConsts.SqlColumns.PRICE.getName() + " double, "+
				DBConsts.SqlColumns.BOOK_IMAGE_URL.getName() + " varchar(100), "+
				DBConsts.SqlColumns.BOOK_DESCRIPTION.getName() + " varchar(1000) "+ //", " +
//				"PRIMARY KEY (" + SqlColumns.BOOK_ID.getName() + ") " +
				")";

		
		/** The Constant INSERT_EBOOK. */
		public static final String INSERT_EBOOK =
				"INSERT INTO "+  DBConsts.SqlTables.EBOOKS.getName() + "(" +
				DBConsts.SqlColumns.BOOK_ID.getName() + ", "+
				DBConsts.SqlColumns.TITLE.getName() + ", "+
				DBConsts.SqlColumns.AUTHOR.getName() + " , "+
				DBConsts.SqlColumns.PRICE.getName() + " , "+
				DBConsts.SqlColumns.BOOK_IMAGE_URL.getName() + " , "+
				DBConsts.SqlColumns.BOOK_DESCRIPTION.getName() + "  "+
				")" +
				" VALUES(?,?,?,?,?,?)";
		
		
		/** The Constant INSERT_USER_DETAILS. */
		public static final String INSERT_USER_DETAILS =
				"INSERT INTO "+  DBConsts.SqlTables.USERS_DETAILS.getName() + "(" +
				DBConsts.SqlColumns.EMAIL.getName() + ", "+
				DBConsts.SqlColumns.USER_NAME.getName() + ", "+
				DBConsts.SqlColumns.USER_ADDRESS.getName() + " , "+
				DBConsts.SqlColumns.USER_PHONE_NUM.getName() + " , "+
				DBConsts.SqlColumns.USER_PWD.getName() + " , "+
				DBConsts.SqlColumns.USER_NIECKNAME.getName() + " , "+
				DBConsts.SqlColumns.USER_DESCRIPTION.getName() + " , "+
				DBConsts.SqlColumns.USER_IMAGE.getName() + "  "+
				")" +
				" VALUES(?,?,?,?,?,?,?,?)";
		
		//TODO: delete this
//		public final String CREATE_BOOK_READ_TABLE =
//				"CREATE TABLE BOOK_READ (" +
//				"email varchar(100), "+
//				"book_id varchar(255), " +
//				"like varchar(1)" +			
//				")";
		
		/** The Constant DROP_ALL_REVIEWS_TABLE. */
		public static final String DROP_ALL_REVIEWS_TABLE = 
				"DROP TABLE  " + DBConsts.SqlTables.REVIEWS.getName() ;//+ 
//				"  cascade constraints; ";

		/** The Constant DROP_USERS_TABLE. */
public static final String DROP_USERS_TABLE = 
				"DROP TABLE  " + DBConsts.SqlTables.USERS_DETAILS.getName();// + 
//				"  cascade constraints; ";
		
		/** The Constant DROP_USER_PURCHASES_TABLE. */
public static final String DROP_USER_PURCHASES_TABLE = 
				"DROP TABLE  " + DBConsts.SqlTables.USER_PURCHASES.getName(); //+ 
//				"  cascade constraints;";
		
		/** The Constant DROP_EBOOKS_TABLE. */
public static final String DROP_EBOOKS_TABLE = 
				"DROP TABLE  " + DBConsts.SqlTables.EBOOKS.getName() ;//+
//				" cascade constraints; ";		
		

		
		
//		public final String SELECT_ALL_REVIEWS_NOT_APPROVED =
//				"SELECT * " +
//				" FROM ALL_REVIEWS " +
//				" WHERE is_review_approved = '0'";
		
		/** The Constant SELECT_ALL_REVIEWS_NOT_APPROVED. */
public static final String SELECT_ALL_REVIEWS_NOT_APPROVED = 
				" SELECT " +
				SqlTables.REVIEWS.getName() + "." + SqlColumns.EMAIL.getName() + "," +
				SqlTables.REVIEWS.getName() + "." + SqlColumns.BOOK_ID.getName() + "," +
				SqlTables.REVIEWS.getName() + "." + SqlColumns.REVIEW_DESCRIPTION.getName() + "," +
				SqlTables.REVIEWS.getName() + "." + SqlColumns.REVIEW_IS_APPROVED.getName() + "," +
				SqlTables.USERS_DETAILS.getName() + "." + SqlColumns.USER_NAME.getName() + "," +
				SqlTables.USERS_DETAILS.getName() + "." + SqlColumns.USER_IMAGE.getName() + "," +
				SqlTables.REVIEWS.getName() + "." + SqlColumns.REVIEW_DATE.getName()  +
				" FROM " + SqlTables.REVIEWS.getName() +
				" INNER JOIN " + SqlTables.USERS_DETAILS.getName() + " "
						+ " ON " + SqlTables.USERS_DETAILS.getName() +"."+ SqlColumns.EMAIL.getName() + " = " +
								    	SqlTables.REVIEWS.getName() + "." + SqlColumns.EMAIL.getName() + " "  + 
				" WHERE " + SqlColumns.REVIEW_IS_APPROVED.getName() + " = '0' or  " +
							SqlColumns.REVIEW_IS_APPROVED.getName() + " = '1'" ; //TODO: delete the 1 thing
		
		/** The Constant SELECT_ALL_USERS. */
		public static final String SELECT_ALL_USERS = 
				" SELECT  " +
						SqlColumns.EMAIL.getName() + ", " +
						SqlColumns.USER_NAME.getName() + ", " +
						SqlColumns.USER_NIECKNAME.getName() + ", " +
						SqlColumns.USER_ADDRESS.getName() + ", " +
						SqlColumns.USER_IMAGE.getName() + ", " +
						SqlColumns.USER_PHONE_NUM.getName() + ", " +
						SqlColumns.USER_DESCRIPTION.getName() + " " +
				" FROM  " +SqlTables.USERS_DETAILS.getName() ;
		
		
		/** The Constant SELECT_REVIEWS_OF_BOOK_ID. */
		public static final String SELECT_REVIEWS_OF_BOOK_ID = 
				" SELECT " +
				SqlTables.REVIEWS.getName() +"." + SqlColumns.EMAIL.getName() + "," +
				SqlTables.REVIEWS.getName() +"." + SqlColumns.BOOK_ID.getName() + "," +
				SqlTables.REVIEWS.getName() +"." + SqlColumns.REVIEW_DESCRIPTION.getName() + "," +
				SqlTables.REVIEWS.getName() +"." + SqlColumns.REVIEW_IS_APPROVED.getName() + "," +
				SqlTables.USERS_DETAILS.getName() +"." + SqlColumns.USER_NAME.getName() + "," +
				SqlTables.USERS_DETAILS.getName() +"." + SqlColumns.USER_IMAGE.getName() + "," +
				SqlTables.REVIEWS.getName() +"." + SqlColumns.REVIEW_DATE.getName() + " " +
				" FROM " + SqlTables.REVIEWS.getName()  +
				" INNER JOIN " + SqlTables.USERS_DETAILS.getName() +
				" ON " + SqlTables.USERS_DETAILS.getName() + "." +SqlColumns.EMAIL.getName() + " = " 
						+ SqlTables.REVIEWS.getName() + "." +SqlColumns.EMAIL.getName() + 
				" WHERE " +//TODO: need to add to where : ' and ALL_REVIEWS.is_review_approved = 1'
						SqlTables.REVIEWS.getName() + "." +SqlColumns.BOOK_ID.getName() + " = ? and " +
						SqlTables.REVIEWS.getName() + "." + SqlColumns.REVIEW_IS_APPROVED.getName() + " = '1'";
		
		/** The Constant INSERT_REVIEW. */
		public static final String INSERT_REVIEW =
				" INSERT INTO " + SqlTables.REVIEWS.getName() + " (" +
					DBConsts.SqlColumns.EMAIL.getName() + ", "+
					DBConsts.SqlColumns.BOOK_ID.getName() + ", "+
					DBConsts.SqlColumns.REVIEW_DESCRIPTION.getName() + ", "+
					DBConsts.SqlColumns.REVIEW_IS_APPROVED.getName() + ", "+
					DBConsts.SqlColumns.REVIEW_DATE.getName() + " "+
				") VALUES (?,?,?,?,?)";
		
		
		

		/** The Constant SET_REVIEW. */
		public static final String SET_REVIEW =
				"UPDATE " + SqlTables.REVIEWS.getName() + " " +
				"SET " + SqlColumns.REVIEW_IS_APPROVED.getName() + " = '1' " +
				"WHERE " + SqlColumns.EMAIL.getName() + " = ? and " +
							SqlColumns.BOOK_ID.getName() + " = ? " ;
		
		
		/** The Constant SET_LIKE. */
		public static final String SET_LIKE =
				"UPDATE " + SqlTables.USER_PURCHASES.getName() + " " +
				"SET " + SqlColumns.LIKED.getName() + " = ? " +
				"WHERE " + SqlColumns.EMAIL.getName() + " = ? and " +
							SqlColumns.BOOK_ID.getName() + " = ? " ;
		

		/** The Constant SET_SCROLL. */
		public static final String SET_SCROLL =
				"UPDATE " + SqlTables.USER_PURCHASES.getName() + " " +
				"SET " + SqlColumns.SCROLL.getName() + " = ? " +
				"WHERE " + SqlColumns.EMAIL.getName() + " = ? and " +
							SqlColumns.BOOK_ID.getName() + " = ? " ;
		
		
		
		/** The Constant INSERT_PURCHASE. */
		public static final String INSERT_PURCHASE =
				" INSERT INTO " + SqlTables.USER_PURCHASES.getName() + " (" +
					DBConsts.SqlColumns.EMAIL.getName() + ", "+
					DBConsts.SqlColumns.BOOK_ID.getName() + ", "+
					DBConsts.SqlColumns.LIKED.getName() + ", "+
					DBConsts.SqlColumns.PRICE.getName() + ", "+
					DBConsts.SqlColumns.PURCHASE_TIME.getName() + ", "+
					DBConsts.SqlColumns.SCROLL.getName() + " "+
				") VALUES (?,?,?,?,?,?)";
		
		
		/** The Constant SELECT_LIKES_OF_BOOK_ID. */
		public static final String SELECT_LIKES_OF_BOOK_ID =
				"SELECT " + SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.EMAIL.getName() + ", " +
							SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.BOOK_ID.getName() + ", " +
							SqlTables.USERS_DETAILS.getName() + "." + SqlColumns.USER_NIECKNAME.getName() + " " +
				" FROM " + SqlTables.USER_PURCHASES.getName() + 
				" INNER JOIN " +SqlTables.USERS_DETAILS.getName() +
				" ON " + SqlTables.USER_PURCHASES.getName() + "." +SqlColumns.EMAIL.getName() + " = " 
						+ SqlTables.USERS_DETAILS.getName() + "." +SqlColumns.EMAIL.getName() + 
				" WHERE " + SqlColumns.BOOK_ID.getName() + " = ? AND " +
							SqlColumns.LIKED.getName() + " = '1' " ;
		
		
		/** The Constant SELECT_TRANSACTIONS_BETWEEN_2_DATES. */
		public static final String SELECT_TRANSACTIONS_BETWEEN_2_DATES =
				"SELECT " + SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.EMAIL.getName() + ", " +
							SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.BOOK_ID.getName() + ", " +
							SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.PURCHASE_TIME.getName() + ", " +
							SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.PRICE.getName() + /*", "
//							SqlTables.USERS_DETAILS.getName() + "." + SqlColumns.USER_NAME.getName() + " " + */
				" FROM " + SqlTables.USER_PURCHASES.getName() + 
//				" INNER JOIN " +SqlTables.USERS_DETAILS.getName() +
//				" ON " + SqlTables.USER_PURCHASES.getName() + "." +SqlColumns.EMAIL.getName() + " = " 
//						+ SqlTables.USERS_DETAILS.getName() + "." +SqlColumns.EMAIL.getName() + 
				" WHERE " + SqlColumns.PURCHASE_TIME.getName() +
					" BETWEEN ? AND  ? " ;
		
		
		/** The Constant SELECT_PURCHASES_BY_EMAIL. */
		public static final String SELECT_PURCHASES_BY_EMAIL =
				"SELECT " + SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.EMAIL.getName() + ", " +
							SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.BOOK_ID.getName() + ", " +
							SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.LIKED.getName() + ", " +
							SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.PRICE.getName() + ", " +
							SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.PURCHASE_TIME.getName() + ", " +
							SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.SCROLL.getName() + /*", "
//							SqlTables.USERS_DETAILS.getName() + "." + SqlColumns.USER_NAME.getName() + " " + */
				" FROM " + SqlTables.USER_PURCHASES.getName() + 
//				" INNER JOIN " +SqlTables.USERS_DETAILS.getName() +
//				" ON " + SqlTables.USER_PURCHASES.getName() + "." +SqlColumns.EMAIL.getName() + " = " 
//						+ SqlTables.USERS_DETAILS.getName() + "." +SqlColumns.EMAIL.getName() + 
				" WHERE " + SqlColumns.EMAIL.getName() +
					" = ? " ;
		
		
		/** The Constant SELECT_ALL_EBOOKS. */
		public static final String SELECT_ALL_EBOOKS =
				"SELECT " + SqlTables.EBOOKS.getName() + "." + SqlColumns.BOOK_ID.getName() + ", " +
							SqlTables.EBOOKS.getName() + "." + SqlColumns.TITLE.getName() + ", " +
							SqlTables.EBOOKS.getName() + "." + SqlColumns.AUTHOR.getName() + ", " +
							SqlTables.EBOOKS.getName() + "." + SqlColumns.PRICE.getName() + ", " +
							SqlTables.EBOOKS.getName() + "." + SqlColumns.BOOK_IMAGE_URL.getName() + ", " +
							SqlTables.EBOOKS.getName() + "." + SqlColumns.BOOK_DESCRIPTION.getName() + /*", "
//							SqlTables.USERS_DETAILS.getName() + "." + SqlColumns.USER_NAME.getName() + " " + */
				" FROM " + SqlTables.EBOOKS.getName() // +
//				" INNER JOIN " +SqlTables.USERS_DETAILS.getName() +
//				" ON " + SqlTables.USER_PURCHASES.getName() + "." +SqlColumns.EMAIL.getName() + " = " 
//						+ SqlTables.USERS_DETAILS.getName() + "." +SqlColumns.EMAIL.getName() + 
					;
		
		
		
		/** The Constant SELECT_PURCHASES_BY_EMAIL_AND_BOOK_ID. */
		public static final String SELECT_PURCHASES_BY_EMAIL_AND_BOOK_ID =
				"SELECT " + SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.EMAIL.getName() + ", " +
							SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.BOOK_ID.getName() + ", " +
							SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.LIKED.getName() + ", " +
							SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.PRICE.getName() + ", " +
							SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.PURCHASE_TIME.getName() + ", " +
							SqlTables.USER_PURCHASES.getName() + "." + SqlColumns.SCROLL.getName() + /*", "
//							SqlTables.USERS_DETAILS.getName() + "." + SqlColumns.USER_NAME.getName() + " " + */
				" FROM " + SqlTables.USER_PURCHASES.getName() + 
//				" INNER JOIN " +SqlTables.USERS_DETAILS.getName() +
//				" ON " + SqlTables.USER_PURCHASES.getName() + "." +SqlColumns.EMAIL.getName() + " = " 
//						+ SqlTables.USERS_DETAILS.getName() + "." +SqlColumns.EMAIL.getName() + 
				" WHERE " + SqlColumns.EMAIL.getName() + " = ? AND " +
					SqlColumns.BOOK_ID.getName() + " = ? ";
		
		
		/** The Constant SELECT_SCROLL_BY_BOOK_ID_AND_EMAIL. */
		public static final String SELECT_SCROLL_BY_BOOK_ID_AND_EMAIL = 
				" SELECT " + SqlColumns.SCROLL.getName() + " " +
				" FROM " + SqlTables.USER_PURCHASES.getName() + " " +
				" WHERE " + SqlColumns.BOOK_ID.getName() + " = ? " +
					" and " + SqlColumns.EMAIL.getName() + " = ? " ;
		

		/** The Constant SELECT_LIKES_OF_BOOK_ID_JUST_THE_EMAIL_AND_BOOK_ID. */
		public static final String SELECT_LIKES_OF_BOOK_ID_JUST_THE_EMAIL_AND_BOOK_ID =
				"SELECT " +SqlColumns.EMAIL.getName() + ", " +
							SqlColumns.BOOK_ID.getName() + " " +
				" FROM " + SqlTables.USER_PURCHASES.getName() + 
				" WHERE " + SqlColumns.BOOK_ID.getName() + " = ? ";// AND " +
//							SqlColumns.LIKED.getName() + " = '1' " ;
		
		
		/** The Constant UPDATE_USER_LIKE_BOOK. */
public static final String UPDATE_USER_LIKE_BOOK = 
				" UPDATE  " + DBConsts.SqlTables.USER_PURCHASES.getName() + 
				" SET " + SqlColumns.LIKED.getName() + " = ? " +
				" WHERE " + SqlColumns.EMAIL.getName() + " = ?";
		
		
		/** The Constant DELETE_USER_BY_EMAIL. */
		public static final String DELETE_USER_BY_EMAIL = 
				"DELETE FROM " + DBConsts.SqlTables.USERS_DETAILS.getName() + " " +
				" WHERE " + DBConsts.SqlColumns.EMAIL.getName() + " = ? ";


}
