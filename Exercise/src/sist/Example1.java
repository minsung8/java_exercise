package sist;

import java.util.Scanner;

class Employee{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	int getPay(){
		return 0;
	}
}

class Permanent extends Employee{
	private int pay;
	private int bonus;
	
	Permanent() {}
	
	Permanent(String name, int pay, int bonus) {
		super.setName(name);
		this.pay = pay;
		this.bonus = bonus;
	}
	
	@Override
	int getPay() {
		return pay + bonus;
	}
	
}

class Temporary extends Employee{
	int time;
	int pay;
	Temporary() {}
	
	Temporary(String name, int pay, int time) {
		super.setName(name);
		this.pay = pay;
		this.time = time;
	}
	
	@Override
	int getPay() {
		return pay * time;
	}
}

public class Example1 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("고용형태 정규직P, 비정규직T를 입력하세요");
		String temp = sc.next();
		
		if (temp.equals("P")){
			System.out.println("이름, 기본급, 보너스를 입력하세요");
			Permanent p = new Permanent(sc.next(), sc.nextInt(), sc.nextInt());
			System.out.println("정규직");
			System.out.println(p.getName());
			System.out.println(p.getPay());
		} else if (temp.equals("T")) {
			System.out.println("이름, 작업시간, 시간당 급여를 입력하세요");
			Temporary p = new Temporary(sc.next(), sc.nextInt(), sc.nextInt());
			System.out.println("비정규직");
			System.out.println(p.getName());
			System.out.println(p.getPay());
		}
	}
}