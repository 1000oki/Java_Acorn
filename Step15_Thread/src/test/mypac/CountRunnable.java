package test.mypac;
/*
 *  새로운 스레드 만드는 방법2
 *  
 *  1. Runnable 인터페이스를 구현한 클래스를 정의함
 *  2. run() 메소드를 강제 오버라이드 함.
 *  3. Thread 클래스로 객체를 생성하면서 해당 클래스로 만든 객체를 생성자의 인자로 전달함.
 *  4. Thread 클래스로 만든 객체의 start() 메소드를 호출해서 스레드를 시작시킴.
 *  new Thread(new CountRunnable()).start()이렇게 사용해야함..
 */
public class CountRunnable implements Runnable{
	@Override
	public void run() {
		// 카운트 값을 저장할 지역번수 만들고 초기 값 대입
		int count = 10;
		while(true) {
			System.out.println("현재 카운트: "+ count);
			// 만일 count가 0이면
			if(count == 0 ) {
				// 반복문 탈출( 반복문 탈출하면 run() 메소드가 리턴되기 때문에 스레드가 종료됨.)
				break;
			}
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			--count;
		}
	}
}
