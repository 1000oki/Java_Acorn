package test.mypac;

public class SmartPhone extends HandPhone{
	// 생성자
	public SmartPhone() {
		System.out.println("SmartPhone 생성자 호출됨");
	}
	
	// 메소드
	public void doInternet() {
		System.out.println("인터넷을 해요");
	}
	
	@Override // 재정의 메소드라는 것을 표시
	public void takePicture() {
		// TODO Auto-generated method stub
		super.takePicture();
		System.out.println("1000만 화소의 사진을 찍어요!");
	}
}
