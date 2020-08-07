package project.bookrental.management;

import java.util.Scanner;

public class Library_Main {

	public static void main(String[] args) {
		
		InterLibrarymngctrl lmc = new Librarymngctrl();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("===> 도서대여 프로그램 <===");
			System.out.println("1. 사서 전용메뉴 	2. 일반회원 전용메뉴		3. 프로그램 종료");
			System.out.println("=> 메뉴번호선택 : ");
			String temp = sc.nextLine();
			
			if(temp.equals("3")) {														// 프로그램 종료 실행
				break;
			} else if(temp.equals("1")) {												// 사서 전용메뉴 실행
				lmc.onlyLibrarian(sc);
			} else if(temp.equals("2")) {												// 일반회원 전용메뉴 실
				lmc.onlyUser(sc);
			}
		}
		
		
		
	}

}
