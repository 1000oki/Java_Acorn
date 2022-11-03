package test.mypac;

public class CountThread extends Thread{
	
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
