package project.bookrental.management;

import java.util.Scanner;

public interface InterLibrarymngctrl {
	
	void onlyLibrarian(Scanner sc);				// 사서메뉴 인터페이스
	void registerLibrarian(Scanner sc);
	LibrarianDTO loginLibrarian(Scanner sc);
	void registerBookInfo(Scanner sc);
	void registerSeperateBook(Scanner sc);
	void lendBook(Scanner sc);
	void lendingBookInfo(Scanner sc);
	void returnBook(Scanner sc);
	
	void onlyUser(Scanner sc);				// 유저메뉴 인터페이스
	void registerUser(Scanner sc);
	UserDTO loginUser(Scanner sc);
	void searchBook(Scanner sc);
	void myRentalBookInfo(Scanner sc, UserDTO userDTO);
	
	void showCount(Scanner sc);
	
}
