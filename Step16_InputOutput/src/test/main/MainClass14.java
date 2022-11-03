package test.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
 * FileReader type 지역변수 fr를 try블럭 위에서 만들고 
 * try 블럭 안에서 객체를 생성해서 대입할 수는 없을까?
 */

public class MainClass14 {
	public static void main(String[] args) {
		// File 객체
		File memoFile = new File("c:/acorn202210/myFolder/memo.txt");
		
		// 필요한 객체를 담을 지역변수를 미리 만들어 줌.
		FileReader fr = null;
		BufferedReader br = null;
		try {
			// 미리 만들어둔 지역 변수에 참조 값 대입하기
			fr = new FileReader(memoFile);
			br = new BufferedReader(fr);
			
			// 반복문 돌면서
			while(true) {
				// 개행기호를 기준으로 한 줄씩 읽어오기 때문에 개행기호는 읽어오지 않음.
				String line = br.readLine();
				if(line == null) {
					break;
				}
				// 읽어낸 문자열 콘솔에 출력하기
				System.out.println(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 마무리 작업을 해줘야 되는데...
			try {
				// 닫는 작업은 열린 순서의 역순으로 하면 좋음.
				if(br != null)br.close();
				if(fr != null)fr.close();	
			}catch(Exception e) {}
		}
	}
}
