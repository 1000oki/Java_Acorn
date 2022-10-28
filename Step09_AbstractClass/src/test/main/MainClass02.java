package test.main;

import test.mypac.Gun;
import test.mypac.MyWeapon2;
import test.mypac.Weapon;

public class MainClass02 {
	
	// static 메소드 안에서 static만 호출할 수 있음
	public static void main(String[] args) {
		// 동일 클래스 안에 있는 static 메소드 호출가능
		test("안녕!!!");
		
		// 여러분이 직접 클래스를 만들고, 객체 생성해서 아래의 useWeapon()메소드를 호출해 보세요.
		Weapon weap = new MyWeapon2();
		useWeapon(weap);
		
		Weapon g1 = new Gun();
		useWeapon(g1);
	}
	
	// 동일한 static에 올라와야 main에서 사용 가능하기 때문에 static을 붙여줘야함.
	public static void test(String msg) {
		System.out.println(msg);
	}
	// Weapon type을 인자로 전달받아서 사용하는 static 메소드
	public static void useWeapon(Weapon w) {
		w.prepare();
		w.attack();
	}
}
