package test.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import test.dao.FriendsDao;
import test.dto.FriendsDto;


public class FriendsFrame extends JFrame implements ActionListener, PropertyChangeListener{
	// 필드
	JTextField inputName, inputPhone;
	JDatePickerImpl datePicker;
	JTable table;
	DefaultTableModel model2;

	// 생성자
	public FriendsFrame() {
		// 레이아웃 매니저 설정
		setLayout(new BorderLayout());
		
		JLabel label1 = new JLabel("이름");
		inputName = new JTextField(10);
		
		JLabel label2 = new JLabel("전화번호");
		inputPhone = new JTextField(20);
		
		JLabel label3 = new JLabel("생일");
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		
		JButton saveBtn = new JButton("저장");
		saveBtn.setActionCommand("save");
		
		JButton deleteBtn = new JButton("삭제");
		deleteBtn.setActionCommand("delete");
		
		// 패널에 UI를 배치
		JPanel panel = new JPanel();
		panel.add(label1);
		panel.add(inputName);
		panel.add(label2);
		panel.add(inputPhone);
		panel.add(label3);
		panel.add(datePicker);
		panel.add(saveBtn);
		panel.add(deleteBtn);
		// 프레임에 패널을 북쪽에 배치
		add(panel, BorderLayout.NORTH);
		
		// 표 형식으로 정보를 출력하기 위한 JTable
		table = new JTable();
		// 컬럼명을 String[]에 순서대로 준비
		String[] colNames = {"번호", "이름", "전화번호", "생일"};
		// 테이블에 연결할 모델 객체(테이블에 출력할 데이터를 가지고 있는 객체)
		model2 = new DefaultTableModel(colNames,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// 0번째 컬럼은 수정 불가능 하도록 false 리턴
				if(column == 0) {
					return false;
				}else { // 나머지 컬럼은 수정 가능하도록 true 리턴
					return true;
				}
			}
		};
		
		// 모델을 테이블에 연결
		table.setModel(model2);
		// 스크롤이 가능하도록 테이블을 JScrollPane에 감쌈
		JScrollPane scroll = new JScrollPane(table);
		// JScrollPane을 프레임의 가운데에 배치하기
		add(scroll, BorderLayout.CENTER);
		
		// 버튼에 리스너 등록
		saveBtn.addActionListener(this);
		deleteBtn.addActionListener(this);

		displayFriend();
		
	    table.addPropertyChangeListener(this);

	}
	
	// 테이블에 친구 목록을 출력하는 메소드
	public void displayFriend() {
		// 테이블에 출력된 데이터 reset하기
		model2.setRowCount(0);
		
		List<FriendsDto> list = new FriendsDao().getList();	
		
		// 반복문 돌면서 순서대로 FriendsDto 객체를 참조해서
		for(FriendsDto tmp : list) {
			// Object 배열로 만든 다음
			Object[] row = {tmp.getNum(), tmp.getName(), tmp.getPhone(), tmp.getBirth()};
			// 모델에 추가
			model2.addRow(row);
		}
	}
	
	
	public static void main(String[] args) {
		FriendsFrame f = new FriendsFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(500,500,900,500);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("save")) {
			// 입력한 이름과 주소를 읽어와서
			String name = inputName.getText();
			String phone = inputPhone.getText();
			Date date =  (Date) datePicker.getModel().getValue();
			
			// java.util.Date를 java.sql.Date로 변경
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = simpleDateFormat.format(date);		
			java.sql.Date date1 = java.sql.Date.valueOf(formattedDate);
			// FriendsDto 객체에 담고
			FriendsDto dto = new FriendsDto();
			dto.setName(name);
			dto.setPhone(phone);
			dto.setBirth(date1);
			// FriendsDao 객체를 이용해서 DB에 저장
			boolean isSuccess = new FriendsDao().insert(dto);
			if(isSuccess) {
				JOptionPane.showMessageDialog(this, name+"의 정보를 추가했습니다.");
				displayFriend();

			}else {
				JOptionPane.showMessageDialog(this, "실패했습니다.");
			}
		}
		else if(command.equals("delete")) {
			// JTable의 선택된 row의 인덱스를 읽어와서
			int rowIndex = table.getSelectedRow();
			if(rowIndex == -1) {
				 JOptionPane.showMessageDialog(this, "삭제할 row를 선택하세요!");
	        	 return;
			}
			
			int result = JOptionPane.showConfirmDialog(this, "선택된 row를 삭제하시겠습니까?");
			// 만일 예를 눌렀다면
			if(result == JOptionPane.YES_OPTION) {
				// DefaultTableModel 에서 해당 인덱스의 table row 에서 삭제할 친구의 번호를 읽어와서
				int num = (int) model2.getValueAt(rowIndex, 0);
				// FriendsDao 객체를 이용해서 DB 에서 삭제하고
				new FriendsDao().delete(num);
				// 새로고침하기
				displayFriend();
			}
		}
	}

	// table에 특정 이벤트가 발생했을 때 호출되는 메소드
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// 만일 테이블의 수정 사항을 DB에 수정 반영할 적당한 시점이 되면
		if(evt.getPropertyName().equals("tableCellEditor") && !table.isEditing()) {
			// 현재 선택된 row의 정보를 DB에 수정 반영함.
			// 변환된 값을 읽어와서 DB에 반영함.
			// 수정된 컬럼에 있는 row 전체의 값을 읽어옴.
			int Index = table.getSelectedRow();
			int colIndex = table.getSelectedColumn();
			int num = (int) model2.getValueAt(Index, 0);
			String name = (String) model2.getValueAt(Index, 1);
			String phone = (String) model2.getValueAt(Index, 2);
			java.sql.Date date1 = null;
			if(colIndex == 3) {
				String date = (String) model2.getValueAt(Index, 3);
				
				try {
					// 문자열로 저장된 날짜를 java.util.Date로 변경
					SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
					Date birth = format.parse(date);
					// java.util.Date를 java.sql.Date로 변경
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String formattedDate = simpleDateFormat.format(birth);		
					date1 = java.sql.Date.valueOf(formattedDate);

				} catch (ParseException e) {
					
				}
				
			}else {
				date1 =  (java.sql.Date)model2.getValueAt(Index, 3);
			}
			// 수정할 친구의 정보를 FriendsDto 객체에 담고
			FriendsDto dto = new FriendsDto(num, name, phone, date1);
			// DB에 저장
			new FriendsDao().update(dto);
			// 선택된 row clear
			table.clearSelection();
		}
	}
}
