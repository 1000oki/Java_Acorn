package test.mypac;
/*
 *  Class의 역할
 *  
 *  1. 객체의 설계도 역할
 *  
 *  2. data type 역할
 *  
 *  3. static 필드와 메소드를 포함하는 역할
 */

public class Car {
	// 저장소(field)
	public String name; // 지역변수는 선언만하면 변수가 생성안되지만, 필드는 생성됨.
	
	// 달리는 기능(method)
	public void drive() {
		/*
		 * 이 클래스로 객체가 생성이 된다면 바로 그 객체의 참조값을 가리키는 예약어가 this임.
		 */
		System.out.println(this.name + " 이(가) 달려요!");
	}	
	
	// 멈추는 기능(method)
	public void stop() {
		System.out.println("멈춰요!");
	}
}
