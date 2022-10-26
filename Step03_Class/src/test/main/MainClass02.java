package test.main;

import test.mypac.MyUtil;

public class MainClass02 {
	public static void main(String[] args) {
		// static 메소드는 클래스명에 . 찍어서 바로 호출할 수 있다.
		MyUtil.send();
		// 
		MyUtil.version = "1.0";
	}
}
