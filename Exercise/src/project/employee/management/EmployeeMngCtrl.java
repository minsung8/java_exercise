package project.employee.management;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class EmployeeMngCtrl implements InterEmployeeMngCtrl {

	private final String DEPTLISTFILENAME = "C:\\iotestdata\\project\\employeemng\\deptlist.dat";
	private final String EMPLISTFILENAME = "C:\\iotestdata\\project\\employeemng\\employeelist.dat";

	private EmpMngSerializable serial = new EmpMngSerializable();
	private Object empListObj;
	
	@Override
	public void registerDept(Scanner sc) {
		
		System.out.println("부서등록하기");
		
		System.out.println("부서번호 : ");
		int temp1;
		while (true) {
			try {
				String temp = sc.next();
				temp1 = Integer.parseInt(temp);
				break;
			} catch (Exception e) {
				System.out.println("오류 : 부서번호는 숫자로만 입려하세요");
				sc.nextLine();
			}
		}
		System.out.println("부서명 : ");
		String temp3 = sc.next();
		System.out.println("부서위치 : ");
		String temp4 = sc.next();
		System.out.println();
		
		DeptDTO d1 = new DeptDTO(temp1, temp3, temp4);
		
		File file = new File(DEPTLISTFILENAME);					// 파일 객체 생성
		
		Map<String, DeptDTO> deptMap = null;
		int n = 0;
		
		if (!file.exists()) {
			deptMap = new HashMap<>();
			deptMap.put(String.valueOf(temp1), d1);	
			n = serial.objectToFileSave(deptMap, DEPTLISTFILENAME);
		} else {
			Object deptMapObj = serial.getObjectFromFile(DEPTLISTFILENAME);
			deptMap = (HashMap<String, DeptDTO>) deptMapObj;
			deptMap.put(String.valueOf(temp1), d1);
		}
		if (n==1) {
			System.out.println("부서등록 성공!!");
		} else {
			System.out.println("부서등록 실패!!");
		}
	}	

	@Override
	public void registerEmployee(Scanner sc) {
		
		sc.nextLine();
		String id = null;
		do {
			System.out.println("아이디를 입력하세요");
			id = sc.nextLine();
			
			if (id == null || id.trim().isEmpty()) {
				continue;
			} else if (!isUseID(id)) {
					System.out.println("이미 있는 아이디입니다.");
					continue;
			} else {
				break;
			}
		} while (true);
		
		System.out.println("패스워드 :");
		int n1;
		while (true) {
			try {
				String temp = sc.next();
				n1 = Integer.parseInt(temp);
				break;
			} catch (Exception e) {
				System.out.println("오류 : 패스워드는 숫자로만 입려하세요");
				sc.nextLine();
			}
		}
		System.out.println("사원명 :");
		String s2 = sc.next();
		while (true) {
			if (s2.trim().isEmpty()) {
				s2 = sc.next();
				System.out.println("다시 입력하세요");
			} else {
				break;
			}
		}
		System.out.println("생년월일 :");
		String s3 = sc.next();
		while (true) {
			if (s3.trim().isEmpty()) {
				s3 = sc.next();
				System.out.println("다시 입력하세요");
			} else {
				break;
			}
		}
		System.out.println("주소 :");
		String s4 = sc.next();
		while (true) {
			if (s4.trim().isEmpty()) {
				s4 = sc.next();
				System.out.println("다시 입력하세요");
			} else {
				break;
			}
		}
		System.out.println("직급 :");
		String s5 = sc.next();
		while (true) {
			if (s5.trim().isEmpty()) {
				System.out.println("다시 입력하세요");
			} else {
				break;
			}
		}
		System.out.println("급여 :");
		int n2;
		while (true) {
			try {
				String temp = sc.next();
				n2 = Integer.parseInt(temp);
				break;
			} catch (Exception e) {
				System.out.println("오류 : 급여 숫자로만 입려하세요");
				sc.nextLine();
			}
		}
		String sDeptno = null;
		do {
			System.out.println("부서번호 : ");
			sDeptno = sc.next();
			
			Object deptMapObj = serial.getObjectFromFile(DEPTLISTFILENAME);
			Map<String, DeptDTO> deptMap = (Map<String, DeptDTO>) deptMapObj;
			Set<String> keyset = deptMap.keySet();
			boolean flag = false;
			for(String key : keyset) {
				if (key.equals(sDeptno)) {
					flag = true;
					break;
				}
			}
			
			if (!flag) {
				System.out.println("입력하신 부서번호는 없는 부서번호입니다.");
				continue;
			}
			try {
				int deptno = Integer.parseInt(sDeptno);
				break;
			}catch (Exception e) {
				System.out.println("숫자를 입력해주세요");
			}
			
		} while (true);
		Object deptMapObj = serial.getObjectFromFile(DEPTLISTFILENAME);
		Map<String, DeptDTO> deptMap = (Map<String, DeptDTO>) deptMapObj;
		Set<String> keyset = deptMap.keySet();
		String answer = null;
		for(String key : keyset) {
			if (Integer.parseInt(key) == Integer.parseInt(sDeptno)) {
				answer = key;
			}
		}
		System.out.println(deptMap.get(answer));
		EmployeeDTO employee = new EmployeeDTO(id, n1, s2, s3, s4, s5, n2, Integer.parseInt(sDeptno), deptMap.get(answer));

		File file = new File(EMPLISTFILENAME);
		
		List<EmployeeDTO> empList = null;
		
		int n = 0;
		
		if (!file.exists()) {
			empList = new ArrayList<>();
			empList.add(employee);
		} else {
			Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME);
			empList = (ArrayList)empListObj;
			empList.add(employee);
		}
		n = serial.objectToFileSave(empList, EMPLISTFILENAME);

		if (n==1) {
			System.out.println("사원등록 성공!!");
		} else {
			System.out.println("사원등록 실패!!");
		}
	}

	@Override
	public Boolean isUseID(String id) {
		
		boolean isUse = true;
		
		Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME);
		if (empListObj != null) {
			ArrayList<EmployeeDTO> temp2_list = new ArrayList<EmployeeDTO>();
			temp2_list = (ArrayList<EmployeeDTO>) empListObj;
			for (int i=0; i<temp2_list.size(); i++) {
				String temp3 = temp2_list.get(i).getId();
				if (temp3.equals(id)) {
					isUse = false;
					return isUse;
				}
			}
		}
		return isUse;
	}
	

	@Override
	public EmployeeDTO login(Scanner sc) {
		Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME);

		if (empListObj != null) {
			ArrayList<EmployeeDTO> temp2_list = new ArrayList<EmployeeDTO>();
			temp2_list = (ArrayList<EmployeeDTO>) empListObj;

			System.out.println("아이디를 입력하세요 :");
			String temp_id = sc.next();
			System.out.println("패스워드를 입력하세요 :");
			int temp_pwd = sc.nextInt();
			for (int i=0; i<temp2_list.size(); i++) {
				if (temp2_list.get(i).getPwd() == temp_pwd && !isUseID(temp_id)) {
					System.out.println("로그인에 성공");
					return temp2_list.get(i);
				}
			}
		}		
		
		return null;
	}

	@Override
	public void employeeMenu(Scanner sc, EmployeeDTO loginEmp) {

		while (true) {
			System.out.println("[" + loginEmp.getEmp_name() + " 님 로그인중...]");
			System.out.println("1. 내정보보기" + " 2. 내정보변경하기" + " 3. 모든사원정보보기" + " 4. 사원검색하기" + " 5. 사원사직시키기" + " 6. 나가기" );
			System.out.println("메뉴번호선택 : ");
			
			String temp_temp = sc.next();
			int temp = Integer.parseInt(temp_temp);
			if (temp < 1 || temp > 6) {
				System.out.println("존재하지 않는 번호입니다 다시 선택하세요!");
			} else if(temp == 6) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else if(temp == 4) {
				emp_search(sc, loginEmp);
			} else if(temp == 1) {
				System.out.println("=== 내정보 ===");
				System.out.println(loginEmp.toString());
				System.out.println("부서명 :" + loginEmp.getDeptdto().getS2());
				System.out.println("부서위치 :" + loginEmp.getDeptdto().getS3());
			} else if(temp == 2) {
				modify(sc, loginEmp);
			} else if(temp == 3) {
				allprn();
			}
		}
	}

	@Override
	public void modify(Scanner sc, EmployeeDTO loginEmp) {

		System.out.println("== 내 정보 변경하기 ==");
		System.out.println(loginEmp.toString());
		System.out.println("부서명 :" + loginEmp.getDeptdto().getS2());
		System.out.println("부서위치 :" + loginEmp.getDeptdto().getS3());
		sc.nextLine();
		System.out.println("공백이나 엔터입력시 그대로 사용");
		System.out.println("암호변경 :");
		String pwd = sc.nextLine();
		System.out.println(pwd);
		if ( !pwd.trim().isEmpty()) {
			System.out.println("변경하시겠습니까? y/n");
			String temp = sc.next();
			if (temp.equals("y")) {
				loginEmp.setPwd(Integer.parseInt(pwd));
				sc.nextLine();
			} else {
				System.out.println("내정보 변경을 취소하였습니다");
			}
		}
		System.out.println("주소변경 :");
		String addr = sc.nextLine();
		if ( !addr.trim().isEmpty()) {
			System.out.println("변경하시겠습니까? y/n");
			String temp = sc.next();
			if (temp.equals("y")) {
				loginEmp.setAddr(addr);
				sc.nextLine();
			} else {
				System.out.println("내정보 변경을 취소하였습니다.");
			}
		}
		System.out.println("급여변경 :");
		String salary = sc.nextLine();
		int salary2;
		if ( !salary.trim().isEmpty()) {
			System.out.println("변경하시겠습니까? y/n");
			String temp = sc.next();
			if (temp.equals("y")) {
				while (true) {
					try {
						
						salary2 = Integer.parseInt(salary);
						break;
					} catch(Exception e) {
						System.out.println("급여는 숫자로만 입력하세요");
					}
					sc.nextLine();
					salary = sc.nextLine();
				}
				loginEmp.setSalary(salary2);
			} else {
				System.out.println("내정보 변경을 취소하였습니다");
			}
		}
		int n = 0;
		ArrayList empList = new ArrayList();
		Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME);
		empList = (ArrayList)empListObj;
		empList.remove(loginEmp);
		empList.add(loginEmp);
	
		n = serial.objectToFileSave(empList, EMPLISTFILENAME);
		if (n == 1) {
			System.out.println("내 정보 변경 성공!");
		}
		
	}

	@Override
	public void allprn() {
		ArrayList<EmployeeDTO> empList = new ArrayList();
		Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME);
		empList = (ArrayList<EmployeeDTO>)empListObj;
		System.out.println(">>> 모든 사원 정보 출력 <<<");
		System.out.println("=========================================================================");
		System.out.println("아이디   암호   사원명   생년월일   나이    주소     직급      급여    	부서번호	   부서명       부서위치");
		System.out.println("=========================================================================");

		for (int i=0; i<empList.size(); i++) {
			String answer = String.valueOf(empList.get(i).getPwd());
			if (answer.length() > 3) {
				String temp = "*";
				String repeat = new String(new char[answer.length() - 3]).replace("\0", "*");
				answer = answer.substring(0, 3);
				answer = answer.concat(repeat);
			} 
			System.out.println(empList.get(i).getId() + " " + answer + " " + empList.get(i).getP_num() + " " + (2020 - Integer.parseInt(empList.get(i).getP_num())) + " " + empList.get(i).getAddr() + " " + empList.get(i).getdeptno() + " " + empList.get(i).getSalary() + " " + empList.get(i).getDeptno() + " " + empList.get(i).getDeptdto().getS2() + " " + empList.get(i).getDeptdto().getS3());
		}
	
	}

	@Override
	public void emp_search(Scanner sc, EmployeeDTO loginEmp) {
		sc.nextLine();

		
		while (true) {
			System.out.println(">>> 사원검색 menu [" + loginEmp.getEmp_name() + "님 로그인중 ...]<<<");
			System.out.println("1. 사원명 검색   2. 연령대 검색   3. 직급 검색   4. 급여범위 검색   5. 부서명 검색   6. 나가기");
			System.out.println("=> 메뉴번호 선택 : ");
			String temp = sc.nextLine();
			
			if (temp.equals("6")) {
				break;
			} else if (temp.equals("1")){
				System.out.println("검색할 사원명 : ");
				String emp_name = sc.nextLine();
				ArrayList<EmployeeDTO> answer = search_name(sc, emp_name);
				if (answer != null) {
					System.out.println("=========================================================================");
					System.out.println("아이디   암호   사원명   생년월일   나이    주소     직급      급여    	부서번호	   부서명       부서위치");
					System.out.println("=========================================================================");
					System.out.println(answer.get(0).toString2());
				} else {
					System.out.println("검색하신 " + emp_name + "는(은) 존재하지 않습니다.");
				}
			} else if (temp.equals("2")) {
				System.out.println("검색할 연령대 : ");
				String age = sc.nextLine();
				ArrayList<EmployeeDTO> answer = search_age(sc, age);
				if (answer != null) {
					System.out.println("=========================================================================");
					System.out.println("아이디   암호   사원명   생년월일   나이    주소     직급      급여    	부서번호	   부서명       부서위치");
					System.out.println("=========================================================================");
					for (int i=0; i<answer.size(); i++) {
						System.out.println(answer.get(i).toString2());
					}
				} else {
					System.out.println("검색하신 연령대" + age + "대 는(은) 존재하지 않습니다.");
				}
			} else if (temp.equals("3")) {
				System.out.println("검색할 직급명 : ");
				String position = sc.nextLine();
				if (!position.trim().isEmpty()) {
					ArrayList<EmployeeDTO> answer = search_birthdate(sc, position);
					if (answer != null) {
						System.out.println("=========================================================================");
						System.out.println("아이디   암호   사원명   생년월일   나이    주소     직급      급여    	부서번호	   부서명       부서위치");
						System.out.println("=========================================================================");
						for (int i=0; i<answer.size(); i++) {
							System.out.println(answer.get(i).toString2());
						}
					} else {
						System.out.println("검색하신 직급" + position + "는(은) 존재하지 않습니다.");
					}
				}
			} else if (temp.equals("5")) {
				System.out.println("검색할 부서명 : ");
				String deptname = sc.nextLine();
				if (!deptname.trim().isEmpty()) {
					ArrayList<EmployeeDTO> answer = search_deptname(sc, deptname);
					if (answer != null) {
						System.out.println("=========================================================================");
						System.out.println("아이디   암호   사원명   생년월일   나이    주소     직급      급여    	부서번호	   부서명       부서위치");
						System.out.println("=========================================================================");
						for (int i=0; i<answer.size(); i++) {
							System.out.println(answer.get(i).toString2());
						}
					} else {
						System.out.println("검색하신 부서명" + deptname + "는(은) 존재하지 않습니다.");
					}
				}
			}else if (temp.equals("4")) {
				System.out.println("검색할 급여 최소값 : ");
				String min_salary = sc.nextLine();
				System.out.println("검색할 급여 최대값 : ");
				String max_salary = sc.nextLine();
				if (!min_salary.trim().isEmpty() && !max_salary.trim().isEmpty()) {
					ArrayList<EmployeeDTO> answer = search_salary(sc, Integer.parseInt(min_salary), Integer.parseInt(max_salary));
					if (answer != null) {
						System.out.println("=========================================================================");
						System.out.println("아이디   암호   사원명   생년월일   나이    주소     직급      급여    	부서번호	   부서명       부서위치");
						System.out.println("=========================================================================");
						for (int i=0; i<answer.size(); i++) {
							System.out.println(answer.get(i).toString2());
						}
					} else {
						System.out.println("검색하신 급여범위에 해당하는 사원은 존재하지 않습니다.");
					}
				}
			}
		}
	}

	@Override
	public ArrayList<EmployeeDTO> search_name(Scanner sc, String name) {
		ArrayList<EmployeeDTO> empList = new ArrayList();
		Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME);
		empList = (ArrayList<EmployeeDTO>)empListObj;
		boolean flag = false;
		ArrayList<EmployeeDTO> answerList = new ArrayList();
		for (int i=0; i<empList.size(); i++) {
			if (empList.get(i).getEmp_name().equals(name)) {
				answerList.add(empList.get(i));
				flag = true;
			}
		}
		if (flag == false) {
			return null;
		}
		return answerList;
	}

	@Override
	public ArrayList<EmployeeDTO> search_age(Scanner sc, String age) {
		ArrayList<EmployeeDTO> empList = new ArrayList();
		Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME);
		empList = (ArrayList<EmployeeDTO>)empListObj;
		ArrayList<EmployeeDTO> answerList = new ArrayList();
		for (int i=0; i<empList.size(); i++) {

			int answer = 2020 - Integer.parseInt(empList.get(i).getP_num());
			if (Integer.parseInt(age) <= answer && Integer.parseInt(age) + 9 >= answer) {
				answerList.add(empList.get(i));
			}
		}
		return answerList;
	}

	@Override
	public ArrayList<EmployeeDTO> search_birthdate(Scanner sc, String position) {
		ArrayList<EmployeeDTO> empList = new ArrayList();
		Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME);
		empList = (ArrayList<EmployeeDTO>)empListObj;
		ArrayList<EmployeeDTO> answerList = new ArrayList();
		for (int i=0; i<empList.size(); i++) {
			if (empList.get(i).getBir_date().equals(position)) {
				answerList.add(empList.get(i));
			}
		}
		return answerList;
	}

	@Override
	public ArrayList<EmployeeDTO> search_salary(Scanner sc, int min_salary, int max_salary) {
		System.out.println(">>> 급여범위 검색 [" + min_salary + "원 ~" + max_salary + "원] <<<");

		ArrayList<EmployeeDTO> empList = new ArrayList();
		Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME);
		empList = (ArrayList<EmployeeDTO>)empListObj;
		ArrayList<EmployeeDTO> answerList = new ArrayList();
		for (int i=0; i<empList.size(); i++) {
			if (min_salary <= empList.get(i).getSalary() && max_salary >= empList.get(i).getSalary()) {
				answerList.add(empList.get(i));
			}
		}
		return answerList;
	}

	@Override
	public ArrayList<EmployeeDTO> search_deptname(Scanner sc, String deptname) {
		ArrayList<EmployeeDTO> empList = new ArrayList();
		Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME);
		empList = (ArrayList<EmployeeDTO>)empListObj;
		ArrayList<EmployeeDTO> answerList = new ArrayList();
		for (int i=0; i<empList.size(); i++) {
			if (empList.get(i).getDeptdto().getS2().equals(deptname)) {
				answerList.add(empList.get(i));
			}
		}
		return answerList;
	}

}