package project.bookrental.management;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import project.employee.management.DeptDTO;
import project.employee.management.EmpMngSerializable;

public class Librarymngctrl implements InterLibrarymngctrl{
	
	private final String LIBRARIANLIST = "C:\\iotestdata\\project\\library\\librarianlist.dat";
	private final String BOOKINFOLIST = "C:\\iotestdata\\project\\library\\bookinfolist.dat";
	private final String USERLIST = "C:\\iotestdata\\project\\library\\userlist.dat";
	private final String SEPERATEBOOKLIST = "C:\\iotestdata\\project\\library\\seperatebooklist.dat";
	private EmpMngSerializable serial2 = new EmpMngSerializable();
	private Object libListObj;

	@Override
	public void onlyLibrarian(Scanner sc) {
		
		boolean login = false;
		LibrarianDTO obj = null;
		
		while (true) {
			if (login == true) {
				System.out.println(">>>> 사서 전용 메뉴 [" + obj.getId() + " 로그인중 ..]<<<<");
			} else {
				System.out.println(">>>> 사서 전용 메뉴 <<<<");
			}
			System.out.println("1. 사서가입 	2. 로그인 	3. 로그아웃  	4. 도서정보등록 	5. 개별도서등록");
			System.out.println("6. 도서대여해주기 	7. 대여중인도서조회 		8. 도서반납해주기  	9. 나가기");
			System.out.println("=> 메뉴번호선택 : ");
			String temp = sc.nextLine();
			
			if (temp.equals("1")){
				System.out.println("== 사서가입하기 == ");
				registerLibrarian(sc);
			} else if (temp.equals("2")) {
				obj = loginLibrarian(sc);
				login = true;
			} else if (temp.equals("3")) {
				login = false;
				System.out.println("로그아웃 되었습니다.");
			} else if (temp.equals("4")) {
				registerBookInfo(sc);
			} else if (temp.equals("5")) {
				registerSeperateBook(sc);
			} else if (temp.equals("6")) {
				lendBook(sc);
			} else if (temp.equals("7")) {
				lendingBookInfo(sc);
			} else if (temp.equals("8")) {
				returnBook(sc);
			} else if (temp.equals("9")) {
				break;
			} else {
				System.out.println("잘못입력되었습니다. 다시 입력해주세요!");
			}
		}
	}

	@Override
	public void registerLibrarian(Scanner sc) {
		System.out.println("▶사서ID : ");
		String id = sc.nextLine();
		System.out.println("▶암호 : ");
		String pwd = sc.nextLine();
		
		LibrarianDTO lib1 = new LibrarianDTO(id, pwd);
		
		File file = new File(LIBRARIANLIST);
		
		ArrayList<LibrarianDTO> list = null;
		
		int n = 0;
		boolean flag = false;

		if (!file.exists()) {
			list = new ArrayList<LibrarianDTO>();
			list.add(lib1);
		} else {
			while (true) {
				Object libListObj = serial2.getObjectFromFile(LIBRARIANLIST);
				list = (ArrayList<LibrarianDTO>) libListObj;
				for (int i=0; i<list.size(); i++) {
					if (list.get(i).getId().equals(id)) {
						flag = true;
						break;
					}
				}
				if (flag == true) {
					System.out.println(id + "는 이미 존재하므로 다른 회원ID를 입력하세요!!");
					System.out.println("▶사서ID : ");
					id = sc.nextLine();
					flag = false;
					continue;
				} else {
					list.add(lib1);
					break;
				}
			}
		}
		if (flag == false) { 
		n = serial2.objectToFileSave(list, LIBRARIANLIST);
		if (n==1) {
			System.out.println(">> 사서등록 성공! <<");
			}
		}
	}

	@Override
	public LibrarianDTO loginLibrarian(Scanner sc) {
		Object libListObj = serial2.getObjectFromFile(LIBRARIANLIST);
		ArrayList<LibrarianDTO> list = new ArrayList<LibrarianDTO>();
		list = (ArrayList<LibrarianDTO>) libListObj;
		LibrarianDTO answer = null;
		boolean login = false;
		System.out.println("== 로그인 하기 ==");
		while (true) {
			System.out.println("▶사서ID : ");
			String id = sc.nextLine();
			System.out.println("▶암호 : ");
			String pwd = sc.nextLine();
			for (int i=0; i<list.size(); i++) {
				if (list.get(i).getId().equals(id) && list.get(i).getPwd().equals(pwd)) {
					System.out.println(">>> 로그인 성공!!! <<<");
					answer = list.get(i);
					login = true;
					break;
				}
			}
			if (login == true) {
				break;
			} else {
				System.out.println("아이디나 암호가 잘못되었습니다. 다시입력하세요");
			}
		}
		return answer;
	}

	@Override
	public void registerBookInfo(Scanner sc) {
		System.out.println("== 도서정보 등록하기 ==");
		String isbn = null;
		while (true) {
			System.out.println("▶국제표준도서번호(ISBN) : ");
			isbn = sc.nextLine();
			if (!isbn.trim().isEmpty()) {
				break;
			}
			System.out.println("~~~ 국제표준도서번호(ISBN)을 입력하세요!!");
		}
		System.out.println("▶도서분류카테고리 : ");
		String category = sc.nextLine();
		System.out.println("▶도서명 : ");
		String bookname = sc.nextLine();
		System.out.println("▶작가명 : ");
		String author = sc.nextLine();
		System.out.println("▶출판사 : ");
		String publisher = sc.nextLine();
		int real_price;
		while(true) {
			System.out.println("▶가격 : ");
			String price = sc.nextLine();
			try {
				real_price = Integer.parseInt(price);
				break;
			} catch(Exception e) {
				System.out.println("가격은 숫자로만 입력하세요!!!");
			}
		}
		BookDTO book1 = new BookDTO(isbn, category, bookname, author, publisher, String.valueOf(real_price));
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		File file = new File(BOOKINFOLIST);
		if (!file.exists()) {
			list = new ArrayList<BookDTO>();
			list.add(book1);
		} else {
			Object libListObj = serial2.getObjectFromFile(BOOKINFOLIST);
			list = (ArrayList<BookDTO>) libListObj;
			list.add(book1);
		}
		int n = 0;
		n = serial2.objectToFileSave(list, BOOKINFOLIST);
		if (n == 1) {
			System.out.println(">>> 도서정보등록 성공!! <<<");
		}
	}

	@Override
	public void registerSeperateBook(Scanner sc) {

		System.out.println("== 개별도서 등록하기 ==");
		System.out.println("▶국제표준도서번호(ISBN) : ");
		String isbn = sc.nextLine();
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		Object libListObj = serial2.getObjectFromFile(BOOKINFOLIST);
		list = (ArrayList<BookDTO>) libListObj;
		boolean confirm = false;
		BookDTO temp_book = null;
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getIsbn().equals(isbn)) {
				temp_book = list.get(i);
				confirm = true;
				break;
			}
		}
		if (confirm == false) {
			System.out.println(">>> 등록된 ISBN이 아닙니다. 도서등록 실패!! <<<");
			return;
		}
		ArrayList<SeperateBookDTO> list2 = new ArrayList<SeperateBookDTO>();
		Object libListObj2 = serial2.getObjectFromFile(SEPERATEBOOKLIST);
		list2 = (ArrayList<SeperateBookDTO>) libListObj2;
		System.out.println("▶도서아이디 : ");
		boolean confirm_book = false;
		String bookId = null;
		
		while (true) {
			bookId = sc.nextLine();
			if (bookId.trim().isEmpty()) {
				System.out.println("~~~ 도서아이디를 입력하세요!!");
				continue;
			} else if (list2 != null) {
				for (int i=0; i<list2.size(); i++) {
					if (list2.get(i).getBookid().equals(bookId)) {
						confirm_book = true;
						break;
					}
				}
				if (confirm_book == true) {
					System.out.println(bookId + " 는 이미 존재하므로 다른 도서아이디를 입력하세요!!");
					confirm_book = false;
					continue;
				} else {
					break;
				}
			} else {
				list2 = new ArrayList<SeperateBookDTO>();
				break;
			}
		}
		File file = new File(SEPERATEBOOKLIST);

		SeperateBookDTO sbook = new SeperateBookDTO(bookId, temp_book);
		list2.add(sbook);
		int n = serial2.objectToFileSave(list2, SEPERATEBOOKLIST);
		
		if (n == 1) System.out.println(">>> 도서등록 성공!! <<<");		

	}

	@Override
	public void lendBook(Scanner sc) {
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		Object libListObj = serial2.getObjectFromFile(USERLIST);
		list = (ArrayList<UserDTO>) libListObj;
		boolean confirm = false;
		System.out.println(">>> 도서대여하기 <<<");
		String id = null;
		UserDTO user = new UserDTO();
		while (true) {
			System.out.println("▶회원ID : ");
			id = sc.nextLine();
			if (!id.trim().isEmpty() && list != null) {
				for (int i=0; i<list.size(); i++) {
					if (list.get(i).equals(id)) {
						confirm = true;
						user = list.get(i);
						break;
					}
				}
			} else if (id.trim().isEmpty() || confirm == false){
				System.out.println("~~~ 등록된 회원ID가 아닙니다 ~~~");
				continue;
			}
			break;	
		}
		ArrayList<SeperateBookDTO> list2 = new ArrayList<SeperateBookDTO>();
		Object libListObj2 = serial2.getObjectFromFile(SEPERATEBOOKLIST);
		list2 = (ArrayList<SeperateBookDTO>) libListObj2;
		System.out.println("▶총대여권수 :");
		String cnt = sc.nextLine(); 
		boolean confirm2 = false;
		ArrayList<SeperateBookDTO> book_list = new ArrayList<SeperateBookDTO>();
		String bookId = null;
		SeperateBookDTO temp_book = new SeperateBookDTO();
		for (int i=0; i<Integer.parseInt(cnt); i++) {
			while (true) {
				System.out.println("▶도서ID :");
				bookId = sc.nextLine();
				for (int j=0; j<list2.size(); j++) {
					if (list2.get(j).getBookid().equals(bookId)) {
						confirm2 = true;
						list2.get(i).setUserdto(user);
						list2.get(i).setRent(true);
						temp_book = list2.get(i);
						break;
					}
				}
				if (confirm2 == false) System.out.println("~~~ 존재하지 않는 도서ID 입니다. 다시 입력하세요!! ~~~");
				else break;
				}
			}
		book_list.add(temp_book);
		int n = serial2.objectToFileSave(list2, SEPERATEBOOKLIST);
		System.out.println(">>> 대여등록 성공!! <<<");
		System.out.println(">>> 대여도서 비치중에서 대여중으로 변경함 <<<");
	}

	@Override
	public void lendingBookInfo(Scanner sc) {
		ArrayList<SeperateBookDTO> list = new ArrayList<SeperateBookDTO>();
		
		Object libListObj2 = serial2.getObjectFromFile(SEPERATEBOOKLIST);
		list = (ArrayList<SeperateBookDTO>) libListObj2;
		System.out.println("=======================================================================================================");
		System.out.println("ISBN		도서아이디		도서명	 	작가명		출판사	 	가격		대여상태");
		System.out.println("=======================================================================================================");
		if (list.size() == 0) {
			System.out.println("~~~~ 대여해가신 도서가 없습니다. ~~~~");
		}
		for (int i=0; i<list.size(); i++) {
			if(list.get(i).isRent == true) {
				System.out.print(list.get(i).toString());
				System.out.println(" " + list.get(i).userdto.getId());
			}
		}
	}

	@Override
	public void returnBook(Scanner sc) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void onlyUser(Scanner sc) {
		
		UserDTO user = null;
		boolean login = false;
		
		while (true) {
			if (login == true) System.out.println("===> 일반회원 전용 Menu [" +user.name + "...] <===");
			else System.out.println("===> 일반회원 전용 Menu <===");
			System.out.println("1. 일반회원가입 	2. 로그인  	3. 로그아웃	4. 도서검색하기 	5. 나의대여현황보기  	6. 나가기");
			System.out.println("=> 메뉴번호선택 :");
			String temp = sc.nextLine();
			if (temp.equals("1")) {
				registerUser(sc);
			} else if (temp.equals("2")) {
				user = loginUser(sc);
				login = true;
			}else if (temp.equals("3")) {
				login = false;
			}else if (temp.equals("4")) {
				searchBook(sc);
			}else if (temp.equals("5")) {
				myRentalBookInfo(sc);
			}else if (temp.equals("6")) {
				break;
			}
		}	
	}

	@Override
	public void registerUser(Scanner sc) {
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		Object libListObj = serial2.getObjectFromFile(USERLIST);
		list = (ArrayList<UserDTO>) libListObj;
		System.out.println("== 일반회원 가입하기 ==");
		String id = null;
		boolean confirm = true;
		while (confirm) {
			System.out.println("▶회원ID : ");
			id = sc.nextLine();
			if (list != null) {
				for (int i=0; i<list.size(); i++) {
					if (list.get(i).getId().equals(id)) {
						System.out.println(id + " 는 이미 존재하므로 다른 회원ID를 입력하세요!!");
						break;
					}
					confirm = false;
				}
			} else {
				confirm = false;
			}
		}
		System.out.println("▶암호 : ");
		String pwd = sc.nextLine();
		System.out.println("▶성명 : ");
		String name = sc.nextLine();
		System.out.println("▶연락처 : ");
		String phone = sc.nextLine();

		UserDTO user = new UserDTO(id, pwd, name, phone);
		if (list == null) {
			list = new ArrayList<UserDTO>();
		}
		list.add(user);
		int n = 0;
		n = serial2.objectToFileSave(list, USERLIST);
		if (n == 1) System.out.println(">>> 회원등록 성공!! <<<");
	}

	@Override
	public UserDTO loginUser(Scanner sc) {
		Object libListObj = serial2.getObjectFromFile(USERLIST);
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		list = (ArrayList<UserDTO>) libListObj;
		UserDTO answer = null;
		boolean login = false;
		System.out.println("== 로그인 하기 ==");
		while (true) {
			System.out.println("▶회원아이디 : ");
			String id = sc.nextLine();
			System.out.println("▶암호 : ");
			String pwd = sc.nextLine();
			for (int i=0; i<list.size(); i++) {
				if (list.get(i).getId().equals(id) && list.get(i).getPwd().equals(pwd)) {
					System.out.println(">>> 로그인 성공!!! <<<");
					answer = list.get(i);
					login = true;
					break;
				}
			}
			if (login == true) {
				break;
			} else {
				System.out.println("아이디나 암호가 잘못되었습니다. 다시입력하세요");
			}
		}
		return answer;	
	}

	@Override
	public void searchBook(Scanner sc) {
		System.out.println(" >>> 도서검색하기 <<<");
		System.out.println("[주의사항] 검색어를 입력치 않고 엔터를 하면 검색대상에서 제외됩니다.");
		
		System.out.println("▶도서분류카테고리 (Programming, Database 등) :" );
		String category = sc.nextLine();
		System.out.println("▶도서명 :");
		String bookname = sc.nextLine();
		System.out.println("▶작가명 :");
		String author = sc.nextLine();
		System.out.println("▶출판사명 :");
		String publisher = sc.nextLine();
		
		ArrayList<SeperateBookDTO> answer = new ArrayList<SeperateBookDTO>();
		
		ArrayList<SeperateBookDTO> list = new ArrayList<SeperateBookDTO>();
		
		Object libListObj2 = serial2.getObjectFromFile(SEPERATEBOOKLIST);
		list = (ArrayList<SeperateBookDTO>) libListObj2;
		if (list != null) {
			System.out.println(list.size());
			if (!category.trim().isEmpty()) {
				for (int c=0; c<list.size(); c++) {
					if (list.get(c).getBookdto().getCategory().equals(category)) {
						answer.add(list.get(c));
					}
				} 
			}
			if (!bookname.trim().isEmpty()) {
				for (int c=0; c<list.size(); c++) {
					if (list.get(c).getBookdto().getBookname().equals(bookname)) answer.add(list.get(c));
				}
			}
			if (!author.trim().isEmpty()) {
				for (int c=0; c<list.size(); c++) {
					if (list.get(c).getBookdto().getAuthor().equals(author)) answer.add(list.get(c));
				}
			}
			if (!publisher.trim().isEmpty()) {
				for (int c=0; c<list.size(); c++) {
					if (list.get(c).getBookdto().getPublisher().equals(publisher)) answer.add(list.get(c));
				}
			}
		} 
		// 출력  
		System.out.println("=======================================================================================================");
		System.out.println("ISBN		도서아이디		도서명	 	작가명		출판사	 	가격		대여상태");
		System.out.println("=======================================================================================================");
		if (answer.size() == 0) {
			System.out.println("~~~~ 검색에 일치하는 도서가 없습니다 ~~~~");
		}
		String temp = null;
		for (int i=0; i<answer.size(); i++) {
			if (answer.get(i).isRent == false) temp = "비치중";
			else temp = "대여중";
			System.out.print(answer.get(i).toString()); 
			System.out.printf("		%,d", Integer.parseInt(answer.get(i).getBookdto().getPrice()));
			System.out.printf("		%s\n", temp);
		}
	}

	@Override
	public void myRentalBookInfo(Scanner sc) {
		
	}
}