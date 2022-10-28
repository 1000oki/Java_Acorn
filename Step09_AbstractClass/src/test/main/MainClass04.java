package test.main;

import test.mypac.Weapon;

public class MainClass04 {
	
	// 내부 클래스
	static class YourWeapon extends Weapon{
		@Override
		public void attack() {
			System.err.println("공중 공격으로 해요!");
		}
	}
	
	public static void main(String[] args) {
		Weapon w = new YourWeapon();
		useWeapon(w);
		
		// Local Inner Class
		class OurWeapon extends Weapon{
			@Override
			public void attack() {
				 System.out.println("지겹다 이제 아무나 공격하자!");
			}
		}
		Weapon w2 = new OurWeapon();
		useWeapon(w2);
	}
	
	public static void useWeapon(Weapon w) {
		w.prepare();
		w.attack();
	}
}
