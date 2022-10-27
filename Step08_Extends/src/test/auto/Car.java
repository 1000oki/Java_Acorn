package test.auto;
/*
 *  - 접근 지정자 접근 범위
 *  
 *  public : 어디에서나 접근 가능
 *  protected : 동일한 package 혹은 상속관계에서 자식에서 접근 가능
 *  default : 동일한 package 안에서만 접근 가능
 *  private : 동일한 클래스 혹은 동일한 객체 안에서만 접근 가능
 *  
 *  - 접근 지정자를 붙이는 위치
 *  
 *  1. 클래스를 선언할 때
 *  2. 생성자
 *  3. 필드
 *  4. 메소드
 *  
 *  클래스는 default와 public 두가지의 접근 지정자만 지정가능함.
 *  접근 지정자를 생략한 것이 default 접근 지정자임.
 *  
 *  1. 클래스에 붙은 접근 지정자
 *     * import할 수 있는지 여부가 결정됨
 *  2. 생성자에 붙은 접근 지정자
 *     * 객체를 생성할 수 있는지 여부가 결정됨.
 *  3. 필드에 붙은 접근 지정자
 *     * 필드를 참조할 수 있는지 여부가 결정됨.
 *  4. 메소드에 붙은 접근 지정자
 *     * 메소드 호출 가능 여부가 결정됨.
 */
public class Car {
	// 필드
	protected Engine engine;
	
	// Engine 객체를 인자로 전달 받는 생성자
	// Car객체를 생성하기 위해서는 반드시 Engine 객체가 생성되어야 함.
	public Car(Engine engine) {
		//생성자로 전달 받은 Engine 객체의 참조값을 필드에 저장하기
		this.engine = engine;
	}
	// 메소드
	public void drive() {
		if(this.engine == null) {
			System.out.println("Engine 객체가 없어서 달릴 수가 없어요.");
			return;
		}
		System.out.println("달려요~");
	}
}
