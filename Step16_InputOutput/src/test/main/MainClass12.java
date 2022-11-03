package test.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * c:/acorn202210/myFolder/memo.txt 파일에 기록된 문자열을 읽어서 
 * 콘솔창에 출력해 보세요.
 * 
 *  -hint
 *  FileReader 객체를 잘 활용해 보세요.
 */

public class MainClass12 {
	public static void main(String[] args) {
		// File 객체
		File memoFile = new File("c:/acorn202210/myFolder/memo.txt");
		try {
			// 파일에서 문자열을 읽어들일 수 있는 객체 생성
			FileReader fr = new FileReader(memoFile);
			while(true) {
				// 한글자씩 읽어들인다.
				int code = fr.read();
				// 만일 더이상 읽을 문자가 없으면
				if(code == -1) {
					break;
				}
				// 코드값을 문자로 변환해서
				char ch = (char)code;
				// 개행 기호없이 읽은 내용만 출력
				System.out.print(ch);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
