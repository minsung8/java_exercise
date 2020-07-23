package sist;

import java.util.Scanner;



public class Example3 {
	
	
	static String search(String productName, String[][] proTable) {
		
		if (productName.length() == 0) {
			productName = null;
		}
		try {
			for (int i=0; i<3; i++) {
				System.out.println(productName.length());

				if (proTable[i][0].equals(productName)) {
					System.out.println((proTable[i][0] + " " + proTable[i][1]));
					return "";
				}
			}
		} catch(Exception e) {
			System.out.println("해당 상품이 없습니다. 널값");
			return "";
		}
		System.out.println("해당 상품이 없습니다. 테이블에 정보가 없습니다");
		return "";
		
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String[][] proTable = new String[3][2];
		
		for (int i=0; i<3; i++) {
			proTable[i][0] = sc.next();
			proTable[i][1] = sc.next();
		}
		System.out.println("검색하실 상품명을 입력하세요");
		sc.nextLine();
		search(sc.nextLine(), proTable);

	}

}
