package project.employee.management;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EmpMngSerializable {
	
	// 직렬화하는 메서드 생성하기(메모리상에 올라온 객체를 하드디스크 파일에 저장) <-> 역직렬화
	public void objectToFileSave(Object obj, String saveFilename) {
		
		// 객체 obj를 saveFilename로 저장
		try {
			// 출력 노드 스트림, 파일이름(saveFilename)을 이용해서 FileOutputStream 객체 생성
			FileOutputStream fost = new FileOutputStream(saveFilename, false);		// true => 파일에 추가 / false => 덮어쓰기 (기존내용 삭제)
			
			// 필터 스트림(속도 향상)
			BufferedOutputStream bufOst = new BufferedOutputStream(fost, 1024);
			
			ObjectOutputStream objOst = new ObjectOutputStream(bufOst);		// 객체 obj를 파일명 saveFilename에 기록하는 스트림 객체 생성
			
			objOst.writeObject(obj);		// 객체 obj를 파일명 saveFilename에 기록하는 것
			
			objOst.close();
			bufOst.close();
			fost.close();			// 사용된 객체를 메모리공간에서 제거
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public Object getObjectFromFile(String fileName) {
		
		try {
			// 입력노드스트림
			FileInputStream finst = new FileInputStream(fileName); 
			
			// 필터스트림(속력 향상)
			BufferedInputStream bufInst = new BufferedInputStream(finst, 1024);
			
			// 파일명 fileName을 읽어서 객체로 만들어주는 스트림 객체 생성
			ObjectInputStream objInst = new ObjectInputStream(bufInst);
			
			Object obj = objInst.readObject();
			
			objInst.close();
			bufInst.close();
			finst.close();
			
			return obj;
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		// 예외처리일 경우
	}
}