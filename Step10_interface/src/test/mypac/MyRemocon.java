package test.mypac;

public class MyRemocon implements Remocon{
// 인터페이스에 명시된 미 완성된 메소드를 완성하는 게 강제됨.
	@Override
	public void up() {
		System.out.println("채널을 올려요.");
	}

	@Override
	public void down() {
		System.out.println("채널을 내려요");
	}
	
}
