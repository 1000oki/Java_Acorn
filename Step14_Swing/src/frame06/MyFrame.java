package frame06;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame implements ActionListener{
	JButton sendBtn;
	JButton delBtn;
	// 생성자
	public MyFrame(String title) {
		super(title);
		
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		sendBtn = new JButton("전송");
		add(sendBtn);
		
		delBtn = new JButton("삭제");
		add(delBtn);
		
		sendBtn.addActionListener(this);
		
	}
	
	public static void main(String[] args) {
		MyFrame frame = new MyFrame("나의 프레임6");
		frame.setVisible(true);
	}
	/*
	 * 전송버튼, 삭제버튼을 누르면 호출되는 메소드
	 * 메소드 안에서 어떤 버튼을 눌렀는지 확인해서 분기 해야 한다.
	 * 메소드에 선언된 매개변수 ActionEvent e에 눌러진 버튼에 대한 정보가 담겨있다.
	 * 해당 객체를 활용해서 분기를 하면 된다.
	 */

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 눌러진 버튼의 참조값 얻어내기
		Object which = e.getSource();
		JOptionPane.showMessageDialog(this, "전송합니다.");
		
	}
}