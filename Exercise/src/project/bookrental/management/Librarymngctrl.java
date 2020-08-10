package project.bookrental.management;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import project.employee.management.EmpMngSerializable;

public class Librarymngctrl implements InterLibrarymngctrl{
	
	private final String LIBRARIANLIST = "C:\\iotestdata\\project\\library\\librarianlist.dat";
	private final String BOOKINFOLIST = "C:\\iotestdata\\project\\library\\bookinfolist.dat";
	private final String USERLIST = "C:\\iotestdata\\project\\library\\userlist.dat";
	private final String SEPERATEBOOKLIST = "C:\\iotestdata\\project\\library\\seperatebooklist.dat";
	private final String RENTALTASKLIST = "C:\\iotestdata\\project\\library\\rentaltasklist.dat";
	private final String COUNTDTOLIST = "C:\\iotestdata\\project\\library\\countdtolist.dat";
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
			System.out.println("6. 도서대여해주기 	7. 대여중인도서조회 		8. 도서반납해주기  	9. 나가기		10. 모든 책들의 대여 횟수 ");
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
			} else if (temp.equals("10")) {
				showCount(sc);
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
					System.out.println("▶암호 : ");
					pwd = sc.nextLine();
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
		
		ArrayList<RentalTaskDTO> rentalList = new ArrayList<RentalTaskDTO>();
		Object rentalListObj = serial2.getObjectFromFile(RENTALTASKLIST);
		rentalList = (ArrayList<RentalTaskDTO>) rentalListObj;
		File file = new File(RENTALTASKLIST);
		if (!file.exists()) {
			rentalList =  new ArrayList<RentalTaskDTO>();
		}

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
			for (int j=0; j<rentalList.size(); j++) {
				if (rentalList.get(j).getUserDTO().getId().equals(id)) {
					Calendar time = Calendar.getInstance();
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
					
					
					try {
						Date to = Date.valueOf(rentalList.get(j).getReturnDay());
						Date current = new Date(System.currentTimeMillis());
						
						long calDate = to.getTime() - current.getTime();
				        long calDateDays = calDate / ( 24*60*60*1000); 
				        calDateDays = Math.abs(calDateDays);
				        if (calDateDays > 0) {
				        	System.out.println("~~~~~ 반납예정일을 넘긴 미반납된 도서가 존재하므로 도서대여가 불가능합니다.!!!");
				        	return;
				        }
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if (!id.trim().isEmpty() && list != null) {
				for (int i=0; i<list.size(); i++) {
					if (list.get(i).getId().equals(id)) {
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
		
		File file_ConutDTO = new File(COUNTDTOLIST); 
		ArrayList<CountDTO> countDTOList = new ArrayList<CountDTO>();
		
		ArrayList<SeperateBookDTO> list2 = new ArrayList<SeperateBookDTO>();
		Object libListObj2 = serial2.getObjectFromFile(SEPERATEBOOKLIST);
		list2 = (ArrayList<SeperateBookDTO>) libListObj2;
		CountDTO countDTO = new CountDTO();

		// 파일이 없다면
		if (!file_ConutDTO.exists()) {
			countDTOList = new ArrayList<CountDTO>();
			for (int i=0; i<list2.size(); i++) {
				countDTO = new CountDTO("0", list2.get(i));
				countDTOList.add(countDTO);
			}
			// 파일이 존재한다면
		} else {
			Object libListObj3 = serial2.getObjectFromFile(COUNTDTOLIST);
			countDTOList = (ArrayList<CountDTO>) libListObj3;
		}
		
		System.out.println("▶총대여권수 :");
		String cnt = sc.nextLine(); 
		boolean confirm2 = false;
		String bookId = null;
		SeperateBookDTO seperateBookDTO = new SeperateBookDTO();
		for (int i=0; i<Integer.parseInt(cnt); i++) {
			while (true) {
				System.out.println("▶도서ID :");
				bookId = sc.nextLine();
				for (int j=0; j<list2.size(); j++) {
					if (list2.get(j).getBookid().equals(bookId)) {
						confirm2 = true;
						list2.get(i).setRent(true);
						RentalTaskDTO rentalTaskDTO = new RentalTaskDTO(list2.get(i), user);
						rentalTaskDTO.setRentalDay();
						rentalList.add(rentalTaskDTO);
						break;
					}
				}
				for (int k=0; k<countDTOList.size(); k++) {
					if (countDTOList.get(k).getSeperateBookDTO().getBookid().equals(bookId)) {
						String temp_cnt = countDTOList.get(k).getCount();
						countDTOList.remove(k);
						countDTO = new CountDTO(String.valueOf(Integer.parseInt(temp_cnt) + 1), list2.get(i));
						countDTOList.add(countDTO);
						break;
					}
				}
				if (confirm2 == false) System.out.println("~~~ 존재하지 않는 도서ID 입니다. 다시 입력하세요!! ~~~");
				else break;
			}
			
		}
		serial2.objectToFileSave(countDTOList, COUNTDTOLIST);
		int m = serial2.objectToFileSave(rentalList, RENTALTASKLIST);
		int n = serial2.objectToFileSave(list2, SEPERATEBOOKLIST);
		if (n == 1 && m == 1) {
			System.out.println(">>> 대여등록 성공!! <<<");
			System.out.println(">>> 대여도서 비치중에서 대여중으로 변경함 <<<");
		}
		
		
	}

	@Override
	public void lendingBookInfo(Scanner sc) {
		ArrayList<RentalTaskDTO> list = new ArrayList<RentalTaskDTO>();
		
		Object libListObj2 = serial2.getObjectFromFile(RENTALTASKLIST);
		list = (ArrayList<RentalTaskDTO>) libListObj2;
		System.out.println("=================================================================================================================");
		System.out.println("도서ID	ISBN		도서명	 	작가명		출판사	 	회원ID	연락처 	대여일자	 	반납예정일");
		System.out.println("=================================================================================================================");
		if (list.size() == 0) {
			System.out.println("~~~~ 대여해가신 도서가 없습니다. ~~~~");
		}
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i).getSeperateBookDTO().toString2());
			System.out.print("		" + list.get(i).getUserDTO().getId() + "	" +list.get(i).getUserDTO().getPhone());
			System.out.println("	" + list.get(i).getRentalDay() + "    " + list.get(i).getReturnDay());
			
		}
	}

	@Override
	public void returnBook(Scanner sc) {
		
		ArrayList<RentalTaskDTO> list = new ArrayList<RentalTaskDTO>();
		Object libListObj2 = serial2.getObjectFromFile(RENTALTASKLIST);
		list = (ArrayList<RentalTaskDTO>) libListObj2;
		
		ArrayList<SeperateBookDTO> list2 = new ArrayList<SeperateBookDTO>();
		Object libListObj3 = serial2.getObjectFromFile(SEPERATEBOOKLIST);
		list2 = (ArrayList<SeperateBookDTO>) libListObj3;
		
		int total = 0;
		System.out.println(">>> 도서반납하기 <<<");
		System.out.println("▶총반납권수 : ");
		String cnt = sc.nextLine();

		for (int i=0; i<Integer.parseInt(cnt); i++) {
			boolean confirm = false;
			while (true) {
				System.out.println("▶반납도서ID : ");
				String id = sc.nextLine();
				for (int j=0; j<list.size(); j++) {
					if (list.get(i).getSeperateBookDTO().getBookid().equals(id)) {
						confirm = true; 
						total += Integer.parseInt((list.get(i).getArrears(list.get(i).getReturnDay())));
						System.out.println("도서별 연체로 : " + (Integer.parseInt((list.get(i).getArrears(list.get(i).getReturnDay()))) * 200) + "원");
						list.remove(i);
						break;
					} 
				}
				for (int k=0; k<list2.size(); k++) {
					if (list2.get(i).getBookid().equals(id)) {
						list2.get(i).setRent(false);
					}
				}
				if (confirm == true) {
					break;
				}
			}
		}

		int n = serial2.objectToFileSave(list, RENTALTASKLIST);
		n = serial2.objectToFileSave(list2, SEPERATEBOOKLIST);
		System.out.println("▶연체료 총계: "+ (total * 200) + "원");
		
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
				if (login == false) {
					System.out.println("로그인을 해야 합니다");
				} else myRentalBookInfo(sc, user);
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
		
		ArrayList<SeperateBookDTO> answer_category = new ArrayList<SeperateBookDTO>();
		ArrayList<SeperateBookDTO> answer_bookname = new ArrayList<SeperateBookDTO>();
		ArrayList<SeperateBookDTO> answer_author = new ArrayList<SeperateBookDTO>();
		ArrayList<SeperateBookDTO> answer_publisher = new ArrayList<SeperateBookDTO>();
		
		ArrayList<SeperateBookDTO> list = new ArrayList<SeperateBookDTO>();
		
		Object libListObj2 = serial2.getObjectFromFile(SEPERATEBOOKLIST);
		list = (ArrayList<SeperateBookDTO>) libListObj2;
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		boolean flag4 = false;
		boolean flag5 = false;
		if (list != null) {
			if (!category.trim().isEmpty()) {
				for (int c=0; c<list.size(); c++) {
					if (list.get(c).getBookdto().getCategory().equalsIgnoreCase(category)) {
						answer_category.add(list.get(c));
					}
				} 
			} else {
				flag1 = true;
				answer_category = list;
			}
			if (!bookname.trim().isEmpty()) {
				for (int c=0; c<list.size(); c++) {
					if (list.get(c).getBookdto().getBookname().equalsIgnoreCase(bookname)) {
						answer_bookname.add(list.get(c));
					}
				}
			} else {
				flag2 = true;
				answer_bookname = list;
			}
			if (!author.trim().isEmpty()) {
				for (int c=0; c<list.size(); c++) {
					if (list.get(c).getBookdto().getAuthor().equalsIgnoreCase(author)) {
						answer_author.add(list.get(c));
					}
				}
			} else {
				flag3 = true;
				answer_author = list;
			}
			if (!publisher.trim().isEmpty()) {
				for (int c=0; c<list.size(); c++) {
					if (list.get(c).getBookdto().getPublisher().equalsIgnoreCase(publisher)) {
						answer_publisher.add(list.get(c));
					}
				}
			} else {
				flag4 = true;
				answer_publisher = list;
			}
		} 
		
		// 출력  
		System.out.println("=======================================================================================================");
		System.out.println("ISBN			도서아이디			도서명	 작가명	출판사	 가격		대여상태");
		System.out.println("=======================================================================================================");

		String temp = null;
		 
		for (int i=0; i<list.size(); i++) {
			if (answer_category.contains(list.get(i)) && answer_bookname.contains(list.get(i)) && answer_author.contains(list.get(i)) && answer_publisher.contains(list.get(i))) {
				flag5 = true;
				if (list.get(i).isRent == false) temp = "비치중";
				else temp = "대여중";
				System.out.print(list.get(i).toString()); 
				System.out.printf("        %,d", Integer.parseInt(list.get(i).getBookdto().getPrice()));
				System.out.printf("		%s\n", temp);
			}
		}
		
		if (!flag5) System.out.println("~~~ 검색에 일치하는 도서가 없습니다 ~~~");

	}

	@Override
	public void myRentalBookInfo(Scanner sc, UserDTO userDTO) {
		ArrayList<RentalTaskDTO> list = new ArrayList<RentalTaskDTO>();
		ArrayList<RentalTaskDTO> answer = new ArrayList<RentalTaskDTO>();
		
		Object libListObj2 = serial2.getObjectFromFile(RENTALTASKLIST);
		list = (ArrayList<RentalTaskDTO>) libListObj2;
		System.out.println("=================================================================================================================");
		System.out.println("도서ID	ISBN		도서명	 	작가명		출판사	 	회원ID	연락처 	대여일자	 	반납예정일");
		System.out.println("=================================================================================================================");
		if (list != null) {
			for (int i=0; i<list.size(); i++) {
				if (list.get(i).getUserDTO().getId().equals(userDTO.getId())) answer.add(list.get(i));
			}
		}
		if (answer.size() == 0 || list==null) System.out.println("~~~ 대여해가신 도서가 없습니다 ~~~");
		else {
			for (int i=0; i<answer.size(); i++) {
				System.out.print(answer.get(i).getSeperateBookDTO().toString());
				System.out.print("		" + answer.get(i).getUserDTO().getId() + "	" +answer.get(i).getUserDTO().getPhone());
				System.out.println("	" + answer.get(i).getRentalDay() + "    " + answer.get(i).getReturnDay());
			}
		}
	}

	@Override
	public void showCount(Scanner sc) {
		ArrayList<CountDTO> list = new ArrayList<CountDTO>();
		Object libListObj3 = serial2.getObjectFromFile(COUNTDTOLIST);
		list = (ArrayList<CountDTO>) libListObj3;
		
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
}