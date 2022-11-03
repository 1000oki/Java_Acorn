package frame08;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import test.mypac.CountRunnable;
import test.mypac.CountThread;

public class MyFrame extends JFrame implements ActionListener{
	
	// 생성자
	public MyFrame(String title) {
		super(title);
		// 프레임의 초기 설정 작업하기
		setBounds(100, 100, 500, 500);
		// 이 프레임(MyFrame)의 x 버튼(close 버튼)을 눌렀을 때 프로세스도 같이 종료 되도록 설정
		// 이 설정 없으면 화면에 안보여도 프로세스는 살아있음.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 레이아웃 설정
		setLayout(new FlowLayout());
		
		JButton startBtn = new JButton("카운트 다운 시작");
		startBtn.addActionListener(this);
		// 프레임에 버튼 추가
		add(startBtn);
		
		JTextField tf = new JTextField(10);
		// 프레임에 JTextField추가
		add(tf);
		
		// 프레임을 화면상에 실제 보이게 하기(false하면 화면에 보이지 않는다)
		setVisible(true);
	
	}
	
	// run 했을 때 실행의 흐름이 시작되는 특별한 메소드
	public static void main(String[] args) {
		// MyFrame 객체 새성하기
		new MyFrame("나의 프레임7");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("카운트다운을 시작합니다.");
		new Thread(()->{
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
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				--count;
			}
		}).start();
	}
	
}
