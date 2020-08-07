package project.bookrental.management;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2104786937406929481L;
	
	String id;
	String pwd;
	String name;
	String phone; 
	ArrayList<SeperateBookDTO> rentBookList;

	public UserDTO () {}
	public UserDTO (String id, String pwd, String name, String phone) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ArrayList<SeperateBookDTO> getRentBookList() {
		return rentBookList;
	}
	public void setRentBookList(ArrayList<SeperateBookDTO> rentBookList) {
		this.rentBookList = rentBookList;
	}

	

}
