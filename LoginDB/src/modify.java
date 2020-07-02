import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.Font;

public class modify extends JFrame{
	private JTextField num;
	private JTextField id;
	private JTextField password;
	private JTextField phoneNumber;
	private JTextField email;
	private JButton modifyButton;
	private String[] data;
	private boolean adminCheck;
	private boolean clientCheck;
	public modify() {
		Login connection = new Login();
		adminCheck = Administrator.getModifyMode();
		if(Administrator.getModifyMode()) {
			data = Administrator.getData(); // 특정 개인 데이터 
		}else {
			System.out.println("1");
			data = client.getClientData();
			clientCheck= true;
		}
		
		setTitle("Modification Page");
		setSize(325,400);
		setLocation(550,350);
		getContentPane().setLayout(null);
		setBackground(new Color(51,84,156));
		
		JLabel numButton = new JLabel("학번");
		
		numButton.setBounds(65, 90, 30, 15);
		getContentPane().add(numButton);
		
		num = new JTextField();
		num.setBounds(105, 87, 116, 21);
		getContentPane().add(num);
		num.setColumns(10);
		
		JLabel idButton = new JLabel("아이디");
		idButton.setBounds(53, 130, 39, 15);
		getContentPane().add(idButton);
		
		id = new JTextField();
		id.setBounds(105, 127, 116, 21);
		getContentPane().add(id);
		id.setColumns(10);
		
		JLabel pwButton = new JLabel("비밀번호");
		pwButton.setBounds(40, 172, 55, 15);
		getContentPane().add(pwButton);
		
		password = new JTextField();
		password.setBounds(105, 169, 116, 21);
		getContentPane().add(password);
		password.setColumns(10);
		
		JLabel pn = new JLabel("전화번호");
		pn.setBounds(40, 211, 55, 15);
		getContentPane().add(pn);
		
		JLabel Email = new JLabel("이메일");
		Email.setBounds(53, 250, 39, 15);
		getContentPane().add(Email);
		
		phoneNumber = new JTextField();
		phoneNumber.setBounds(105, 208, 116, 21);
		getContentPane().add(phoneNumber);
		phoneNumber.setColumns(10);
		
		email = new JTextField();
		email.setBounds(105, 247, 116, 21);
		getContentPane().add(email);
		email.setColumns(10);
		
		modifyButton = new JButton("수정");
		modifyButton.setForeground(Color.white);
		modifyButton.setBackground(new Color(51,84,156));
		modifyButton.setBounds(115, 311, 97, 23);
		getContentPane().add(modifyButton);
		
		JLabel lblNewLabel = new JLabel("My Account");
		lblNewLabel.setForeground(new Color(51,84,156));
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(105, 26, 116, 36);
		getContentPane().add(lblNewLabel);
		modifyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!num.getText().equals("")) {
					String SQL = String.format("update information set num=%d where id='%s'",Integer.parseInt(num.getText()),data[1]);
					connection.modifyNum(SQL);
				}
				if(!id.getText().equals("")) {
					String SQL = String.format("update information set id='%s' where id='%s'",id.getText(),data[1]);
					connection.modifyID(SQL);
				}
				if(!password.getText().equals("")) {
					String SQL = String.format("update information set password='%s' where id='%s'",password.getText(),data[1]);
					connection.modifyPW(SQL);
				}
				if(!phoneNumber.getText().equals("")) {
					String SQL = String.format("update information set phoneNumber='%s' where id='%s'",phoneNumber.getText(),data[1]);
					connection.modifyPH(SQL);
				}
				if(!email.getText().equals("")) {
					String SQL = String.format("update information set email='%s' where id='%s'",email.getText(),data[1]);
					connection.modifyEmail(SQL);
				}
				if(adminCheck) {
					Administrator.getModifyFrame().dispose();
				}else if(clientCheck){
					client.getRevisePage().dispose();
				}
			}
		});
	}
	public void setCheck(boolean t) {
		adminCheck = t;
	}
}
