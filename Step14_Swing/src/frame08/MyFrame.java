package frame08;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * JTextField 객체의 메소드를 잘 활용해서
 * 
 * 메세지를 입력하고 눌러보셈 버튼을 누르면 
 * 
 * 입력한 메세지가 알리망에 출력되도록 프로그래밍 해 보세요.
 */

public class MyFrame extends JFrame{
	JTextField inputMsg;
	// 생성자
	public MyFrame(String title) {
		super(title);
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 흐르듯이 배치하는 레이아웃
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		// 문자열을 한 줄 입력할 수 있는 JTextField
		inputMsg = new JTextField(10);
		JButton sendBtn = new JButton("전송");
		
		add(inputMsg);
		add(sendBtn);
		
		// 버튼을 눌렀을 때 실행할 메소드를 가지고 있는 객체를 등록하는 메소드
		sendBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 입력한 문자열 읽어오기
				String msg = inputMsg.getText();
				// 알림으로 띄우기
				JOptionPane.showMessageDialog(MyFrame.this, msg);
			}
		});
		/*
		 * ActionListener listener = null;
		 * sendBtn.addActionListener(listener);
		 * 인터페이스가 들어가야되기 때문에 저게 들어가야 함. 
		 * 인터페이스는 오버라이드해줘야하기 때문에 익명의 클래스를 만들어서 넣어준 것 뿐!
		 */
	}
	
	public static void main(String[] args) {
		MyFrame f = new MyFrame("나의 프레임8");
		f.setVisible(true);
	}

}
