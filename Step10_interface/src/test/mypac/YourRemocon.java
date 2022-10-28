package test.mypac;

public class YourRemocon implements Remocon{

	@Override
	public void up() {
		System.out.println("소리를 키우다.");
	}

	@Override
	public void down() {
		System.out.println("소리를 줄이다.");
	}
	
}
