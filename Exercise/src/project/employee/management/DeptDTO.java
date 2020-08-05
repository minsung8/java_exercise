package project.employee.management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeptDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4243660468555022538L;
	
	private int s1;
	private String s2;
	private String s3;
	
	DeptDTO() {}
	DeptDTO(int s1, String s2, String s3) {
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
	}
	public int getS1() {
		return s1;
	}
	public void setS1(int s1) {
		this.s1 = s1;
	}
	public String getS2() {
		return s2;
	}
	public void setS2(String s2) {
		this.s2 = s2;
	}
	public String getS3() {
		return s3;
	}
	public void setS3(String s3) {
		this.s3 = s3;
	}
	
}