package test.main;

import java.util.Scanner;

import test.mypac.Weapon;

public class MainClass05 {
	// 필드 : 공간을 만들고 값을 대입하는 정도까지만,,,
	// 필드를 선언하는 동시에 값 얻어내서 대입하는 코드
	int num = 999;
	String name = "kim";
	Scanner scan = new Scanner(System.in);
	
	// 필드는 선언만 하면 기본 값이 들어감.
	int weight; // 0
	boolean isRun; // false
	String msg; // null
	Scanner scan2; // null
	// static 영역에 올리고 싶은 필드는 static 예약어를 이용해서 만든다.
	static String greet="안녕";
	
	// class ? extends Weapon{public void attack(){}} 과 같음. 이름이 없는 클래스 
	static Weapon w1 = new Weapon() {

		@Override
		public void attack() {
			System.out.println("무엇인지 모르겠지만 아무거나 공격하자");
		}
		
	};
	
	public static void main(String[] args) {
		useWeapon(w1);
		// Anonymous Local Inner Class 를 이용해서 Weapon type의 참조값 얻어내기
		Weapon w2 = new Weapon() { 
			@Override
			public void attack() {
				System.out.println("공격하자.");
			}
		};
		useWeapon(w2);
		
		useWeapon(new Weapon() { 
			@Override
			public void attack() {
				System.out.println("신기하게 공격하기");
			}
		});
	}
	
	public static void useWeapon(Weapon w) {
		w.prepare();
		w.attack();
	}
}
