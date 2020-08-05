package project.employee.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeMngMainExecution {

	public static void main(String[] args) {
		
		InterEmployeeMngCtrl ctrl = new EmployeeMngCtrl();
		List<DeptDTO> deptList = new ArrayList<DeptDTO>();

		Scanner sc = new Scanner(System.in);
		boolean flag = false;
		String EMPLISTFILENAME = "C:\\iotestdata\\project\\employeemng\\employeelist.dat";
		EmpMngSerializable serial = new EmpMngSerializable();
		Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME);
		EmployeeDTO temp_emp = new EmployeeDTO();
		
		while (true) {
			
			if (flag) {

				if (temp_emp != null) {
					System.out.println("["+temp_emp.getEmp_name() +"님 로그인중 ...]");
				}
			}
			
			System.out.println("1.부서등록" + " 2.사원등록" + " 3.로그인" + " 4.로그아웃" + " 5.프로그램 종료");
			System.out.println("메뉴번호선택 : ");
			
			String temp_temp = sc.next();
			int temp = Integer.parseInt(temp_temp);
			if (temp < 1 || temp > 5) {
				System.out.println("메뉴에 없는 번호입니다 다시 선택하세요!");
			} else if(temp == 5) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else if(temp == 4) {
				System.out.println("로그아웃 되었습니다");
				flag = false;
			} else if(temp == 1) {
				ctrl.registerDept(sc);
			} else if(temp == 2) {
				ctrl.registerEmployee(sc);
			} else if(temp == 3) {
				if (flag == true) {
					System.out.println("이미 로그인중입니다.");
				} else {
				temp_emp = ctrl.login(sc);
				ctrl.employeeMenu(sc, temp_emp);
				flag = true;
				}
			}
		}	
	}
}