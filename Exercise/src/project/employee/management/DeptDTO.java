package project.employee.management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeptDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4243660468555022538L;
	
	String s1;
	String s2;
	String s3;
	
	DeptDTO() {}
	DeptDTO(String s1, String s2, String s3) {
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
	}
	
	public void registerDept() {
		
	}

}
