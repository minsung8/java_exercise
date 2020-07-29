package sist;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

class Mythread1 extends Thread{
	
	int n;
	int answer;
	
	public Mythread1() {}
	public Mythread1(int n) {
		this.n = n;
	}
	
	@Override
	public void run() {
		for (int i=1; i<=n; i++) {
			answer += i;
		}
		System.out.println(answer);
	}
	
}

class Mythread2 extends Thread{

	@Override
	public void run() {
		
		for (int i=0; i<5; i++) {
			try {
				
				SimpleDateFormat sdf = new SimpleDateFormat("HH시mm분ss초");
				String s = sdf.format(System.currentTimeMillis());
				System.out.println(s);
				Thread.sleep(3000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

public class Example5 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("숫자를 입력하세요");
		Mythread1 t1 = new Mythread1(sc.nextInt());
		
		Mythread2 t2 = new Mythread2();
		t1.start(); t2.start();
	
	}
}