package model;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User {
	
	/**
	 * Instantiates a new user.
	 *
	 * @param email the email
	 * @param name the name
	 * @param nickname the nickname
	 * @param address the address
	 * @param userImageUrl the user image url
	 * @param phoneNumber the phone number
	 * @param description the description
	 */
	public User(String email, String name, String nickname, String address, String userImageUrl,  String phoneNumber, String description) {
		this.userName = name;
		this.email = email;
		this.userNickname = nickname;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.description = description;
		this.userImageUrl = userImageUrl;
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param email the email
	 * @param name the name
	 * @param nickname the nickname
	 * @param pwd the pwd
	 * @param address the address
	 * @param userImageUrl the user image url
	 * @param phoneNumber the phone number
	 * @param description the description
	 */
	public User(String email, String name, String nickname,String pwd, String address, String userImageUrl,  String phoneNumber, String description) {
		this.userName = name;
		this.email = email;
		this.userNickname = nickname;
		this.pwd = pwd;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.description = description;
		this.userImageUrl = userImageUrl;
	}
	
	/**
	 * Sets the user name.
	 *
	 * @param name the new user name
	 */
	public void setUserName(String name) {
		this.userName = name;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Sets the user nick name.
	 *
	 * @param nickname the new user nick name
	 */
	public void setUserNickName(String nickname) {
		this.userNickname = nickname;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param pass the new password
	 */
	public void setPassword(String pass) {
		this.pwd = pass;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param addr the new address
	 */
	public void setAddress(String addr) {
		this.address = addr;
	}
	
	/**
	 * Sets the phone number.
	 *
	 * @param phone the new phone number
	 */
	public void setPhoneNumber(String phone) {
		this.phoneNumber = phone;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param desc the new description
	 */
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	/**
	 * Sets the photo.
	 *
	 * @param photo the new photo
	 */
	public void setPhoto(String photo) {
		this.userImageUrl = photo;
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
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
	 * Gets the user nickname.
	 *
	 * @return the user nickname
	 */
	public String getUserNickname() {
		return userNickname;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
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
	 * Gets the pwd.
	 *
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	
	/**
	 * Gets the image url.
	 *
	 * @return the image url
	 */
	public String getImageUrl() {
		return userImageUrl;
	}
	
	/**
	 * Prints the user.
	 */
	public void printUser() {
		System.out.println("userName: "+ userName + "img: "+ userImageUrl);
	}
	
	/** The user name. */
	private String userName;
	
	/** The email. */
	private String email;
	
	/** The user nickname. */
	private String userNickname;
	
	/** The address. */
	private String address;
	
	/** The phone number. */
	private String phoneNumber;
	
	/** The description. */
	private String description;
	
	/** The pwd. */
	private String pwd;
	
	/** The user image url. */
	private String userImageUrl;
	

}
