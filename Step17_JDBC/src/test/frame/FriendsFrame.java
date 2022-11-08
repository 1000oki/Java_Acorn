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
	JTextField inputName, inputPhone;
	JDatePickerImpl datePicker;
	JTable table;
	DefaultTableModel model2;

	public FriendsFrame() {
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
		
		JPanel panel = new JPanel();
		panel.add(label1);
		panel.add(inputName);
		panel.add(label2);
		panel.add(inputPhone);
		panel.add(label3);
		panel.add(datePicker);
		panel.add(saveBtn);
		panel.add(deleteBtn);

		add(panel, BorderLayout.NORTH);
		
		table = new JTable();
		String[] colNames = {"번호", "이름", "전화번호", "생일"};
		model2 = new DefaultTableModel(colNames,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 0) {
					return false;
				}else {
					return true;
				}
			}
		};
		
		table.setModel(model2);
		JScrollPane scroll = new JScrollPane(table);
		add(scroll, BorderLayout.CENTER);
		
		saveBtn.addActionListener(this);
		deleteBtn.addActionListener(this);

		displayFriend();
		
	    table.addPropertyChangeListener(this);

	}
	
	public void displayFriend() {
		model2.setRowCount(0);
		
		List<FriendsDto> list = new FriendsDao().getList();	
		
		for(FriendsDto tmp : list) {
			Object[] row = {tmp.getNum(), tmp.getName(), tmp.getPhone(), tmp.getBirth()};
			
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
			String name = inputName.getText();
			String phone = inputPhone.getText();
			Date date =  (Date) datePicker.getModel().getValue();
			
			// java.util.Date를 java.sql.Date로 변경
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = simpleDateFormat.format(date);		
			java.sql.Date date1 = java.sql.Date.valueOf(formattedDate);
			
			FriendsDto dto = new FriendsDto();
			
			dto.setName(name);
			dto.setPhone(phone);
			dto.setBirth(date1);
			boolean isSuccess = new FriendsDao().insert(dto);
			if(isSuccess) {
				JOptionPane.showMessageDialog(this, name+"의 정보를 추가했습니다.");
				displayFriend();

			}else {
				JOptionPane.showMessageDialog(this, "실패했습니다.");
			}
		}
		else if(command.equals("delete")) {
			int rowIndex = table.getSelectedRow();
			if(rowIndex == -1) {
				 JOptionPane.showMessageDialog(this, "삭제할 row를 선택하세요!");
	        	 return;
			}
			
			int result = JOptionPane.showConfirmDialog(this, "선택된 row를 삭제하시겠습니까?");
			if(result == JOptionPane.YES_OPTION) {
				int num = (int) model2.getValueAt(rowIndex, 0);
				new FriendsDao().delete(num);
				displayFriend();
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("tableCellEditor") && !table.isEditing()) {
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
			// sql문 update
			FriendsDto dto = new FriendsDto(num, name, phone, date1);
			new FriendsDao().update(dto);
			table.clearSelection();
		}
	}
}
