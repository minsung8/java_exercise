package sist;

import java.util.Scanner;

interface shape{
	
	float findArea();
}

class Circle implements shape{
	
	private int r;
	
	Circle(){}
	Circle(int r){
		this.r = r;
	}
	
	@Override
	public float findArea() {
		return (int)((r * r) * 3.14);
	}
}

class Ractangle implements shape{
	
	private int x;
	private int y;
	
	Ractangle(){}
	Ractangle(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public float findArea() {
		return x * y;
	}
	
}

public class Example2 {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		if (num == 1) {
			
			Circle c = new Circle(sc.nextInt());
			System.out.println(c.findArea());
		} else if (num == 2) {
			Ractangle r = new Ractangle(sc.nextInt(), sc.nextInt());
			System.out.println(r.findArea());
		}
	}
}