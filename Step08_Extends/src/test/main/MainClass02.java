package test.main;

import test.mypac.HandPhone;
import test.mypac.Phone;

/*
 *  지역변수나 필드 앞에 선언한 data type은 (참조 데이터 type)
 *  
 *  그 안에 들어 있는 참조값의 사용 설명서라고 생각하면 된다.
 *  
 *  그렇기 때문에 그 지역변수나 필드에 . 을 찍으면 사용 설명서에 명시된 기능만 사용할 수 있다.
 *  
 *  java 의 모든 객체는 다형성을 가질 수 있다.
 *  
 *  다형성은 type이 여러개라는 의미이다.
 */
public class MainClass02 {
	public static void main(String[] args) {
		// 다형성 확인!!
		HandPhone p1 = new HandPhone();
		// 어떤 객체의 참조받을 부모 type으로 받을 수 있다.
		Phone p2 = new HandPhone();
		Object p3 = new HandPhone();
		// 자식 객체의 참조값은 부모 type 변수나 필드에 자연스럽게 담긴다.
	
		// Hanphone은 HandPhone type, Phone type, Object type이기도 하다.
		// p2, p3는 핸드폰객체를 생성해서 부모 타입으로 받음. => 프로그래밍이 좀 유연해진다. (넣을 수 있는 가지 수가 많아진다.)
		// p3는 핸드폰이라는 기능은 가지고 있지만, Obejct로 썼기 때문에 Object형태로 써야한다.
	}
}
