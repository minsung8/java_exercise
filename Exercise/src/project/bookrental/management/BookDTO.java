package project.bookrental.management;

import java.io.Serializable;
import java.util.ArrayList;

public class BookDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4736681013339970391L;
	
	String isbn;
	String category;
	String bookname;
	String author;
	String publisher;
	String price; 
	
	public BookDTO() {}
	public BookDTO(String isbn, String category, String bookname, String author, String publisher, String price) {
		this.isbn = isbn;
		this.category = category;
		this.bookname = bookname;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}