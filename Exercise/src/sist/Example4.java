package sist;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ProductSearchData{
	
	Map<String, String> map = new HashMap<String, String>();
	
	public ProductSearchData() {
		map.put("a", "b");
		map.put("c", "d");
		map.put("e", "f");
	}
	
	public void search(String name) {
		
		if (map.containsKey(name)) {
			System.out.println(map.get(name));
		} else {
			System.out.println("해당상품이 없습니다");
		}	
	}
}

public class Example4 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ProductSearchData psd = new ProductSearchData();
		psd.search(sc.next());
		sc.close();	
	}
}