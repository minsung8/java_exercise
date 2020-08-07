package project.bookrental.management;

import java.io.Serializable;

public class LibrarianDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5094777288429697218L;
	/**
	 * 
	 */
	String id;
	String pwd;
	
	public LibrarianDTO() {} 
	
	public LibrarianDTO(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	

}
