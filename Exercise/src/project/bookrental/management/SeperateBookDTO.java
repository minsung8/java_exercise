package project.bookrental.management;

import java.io.Serializable;

public class SeperateBookDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6724111105404635400L;
	String bookid;
	boolean isRent;
	BookDTO bookdto;
	UserDTO userdto;
	
	public SeperateBookDTO () {}
	
	public SeperateBookDTO (String bookid, BookDTO bookdto) {
		this.bookid = bookid;
		this.bookdto = bookdto;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public boolean isRent() {
		return isRent;
	}
	public void setRent(boolean isRent) {
		this.isRent = isRent;
	} 
	public BookDTO getBookdto() {
		return bookdto;
	}
	public void setBookdto(BookDTO bookdto) {
		this.bookdto = bookdto;
	}

	@Override
	public String toString() {
		return bookdto.getIsbn() + "   " + bookid + "      " + bookdto.getBookname() + "	" + bookdto.getAuthor() + "		" + bookdto.getPublisher();		
	}

	public UserDTO getUserdto() {
		return userdto;
	}

	public void setUserdto(UserDTO userdto) {
		this.userdto = userdto;
	}
	
	
}
