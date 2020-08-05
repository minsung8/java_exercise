package project.employee.management;

import java.util.ArrayList;
import java.util.Scanner;

public interface InterEmployeeMngCtrl {

	void registerDept(Scanner sc);		// 부서등록
	
	void registerEmployee(Scanner sc); 		// 사원등록
	
	Boolean isUseID(String id);		// 아이디 중복
		
	EmployeeDTO login(Scanner sc);		// 로그인
	
	void employeeMenu(Scanner sc, EmployeeDTO loginEmp);
	
	void modify(Scanner sc, EmployeeDTO loginEmp);
	
	void allprn();
	
	void emp_search(Scanner sc, EmployeeDTO loginEmp);
	
	ArrayList<EmployeeDTO> search_name(Scanner sc, String name);
	ArrayList<EmployeeDTO> search_age(Scanner sc, String p_num);
	ArrayList<EmployeeDTO> search_birthdate(Scanner sc, String position);
	ArrayList<EmployeeDTO> search_salary(Scanner sc, int min_salary, int max_salary);
	ArrayList<EmployeeDTO> search_deptname(Scanner sc, String deptname);
		
}
