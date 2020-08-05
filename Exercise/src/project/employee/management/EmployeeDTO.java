package project.employee.management;

import java.io.Serializable;

public class EmployeeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2534699851589679551L;
	private String id;
	private int pwd;
	private String emp_name;
	private String p_num;
	private String addr;
	private String bir_date;
	private int salary;
	private int deptno;
	private DeptDTO deptdto;
	
	public EmployeeDTO() {}
	public EmployeeDTO(String id, int pwd, String emp_name, String p_num, String addr, String bir_date, int salary, int deptno, DeptDTO deptdto) {
		this.id = id;
		this.pwd = pwd;
		this.emp_name = emp_name;
		this.p_num = p_num;
		this.addr = addr;
		this.bir_date = bir_date;
		this.salary = salary;
		this.deptno = deptno;
		this.deptdto = deptdto;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPwd() {
		return pwd;
	}
	public void setPwd(int pwd) {
		this.pwd = pwd;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getP_num() {
		return p_num;
	}
	public void setP_num(String p_num) {
		this.p_num = p_num;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getBir_date() {
		return bir_date;
	}
	public void setBir_date(String bir_date) {
		this.bir_date = bir_date;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getdeptno() {
		return deptno;
	}
	public void getdeptno(int deptno) {
		this.deptno = deptno;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "아이디:" + id + "\n 비밀번호:" + pwd + "\n 사원명:" + emp_name + "\n 나이:" + (2020 - Integer.parseInt(p_num)) + "\n 주소:"
				+ addr + "\n 생년월일:" + bir_date + "\n 급여:" + salary + "\n 부서번호:" + deptno;
	}
	
	public String toString2() {
		return id + "  " + pwd + " " + emp_name + " 	" + (2020 - Integer.parseInt(p_num)) + " 	"
				+ addr + " 	" + bir_date + "	" + salary + "	" + deptno + " " + deptdto.getS2() + " " + deptdto.getS3(); 
 	}
	public DeptDTO getDeptdto() {
		return deptdto;
	}
	public void setDeptdto(DeptDTO deptdto) {
		this.deptdto = deptdto;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
}