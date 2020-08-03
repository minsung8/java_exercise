package project.employee.management;

import java.util.Scanner;

public class EmployeeMngCtrl implements InterEmployeeMngCtrl {


	@Override
	public void registerDept(Scanner sc) {
		
		System.out.println("부서등록하기");
		System.out.println("부서번호 : ");
		String temp1;
		while (true) {
			temp1 = sc.next();
			
			try {
				int temp2 = Integer.parseInt(temp1);
				break;
			} catch (Exception e) {
				System.out.println("오류 : 부서번호는 숫자로만 입려하세요");
			}
		}
		System.out.println("부서명 : ");
		String temp3 = sc.next();
		System.out.println("부서위치 : ");
		String temp4 = sc.next();
		
		DeptDTO d1 = new DeptDTO(temp1, temp3, temp4);
	}	
}