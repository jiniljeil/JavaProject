import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;

public class Signup extends JFrame{

	private static boolean checker; // 중복 확인
	private JButton button;
	private JButton button_1;
	private boolean complete = false;
	private boolean back = false;
	private JTextField email;
	private JTextField phoneNumber;
	private JTextField password;
	private JTextField id;
	private JTextField num;
	private String existIDChecker = new String("중복 확인");
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private boolean check;
	private boolean[] join = {false,false,false,false,false};
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private static failpage f;
	private static failpage t;
	public Signup() {
		Login connection = new Login();
		setTitle("SignUp Page");
		setSize(422,500);
		setLocation(1000,200);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(51,84,156));
		JLabel label = new JLabel("학번:"); 
		label.setForeground(Color.WHITE);
		label.setFont(new Font("굴림", Font.BOLD, 13));
		label.setBounds(93, 110, 33, 29);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("아이디:");
		label_1.setFont(new Font("굴림", Font.BOLD, 13));
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(78, 166, 48, 26);
		getContentPane().add(label_1);
		
		JLabel lblNewLabel = new JLabel("비밀번호:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel.setBounds(65, 222, 61, 26);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("전화번호:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(70, 276, 56, 29);
		getContentPane().add(lblNewLabel_1);
		
		JLabel label_2 = new JLabel("이메일:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("굴림", Font.PLAIN, 13));
		label_2.setBounds(82, 327, 44, 29);
		
		getContentPane().add(label_2);
		
		
		email = new JTextField();
		
		email.setFont(new Font("굴림", Font.PLAIN, 14));
		email.setBounds(134, 326, 147, 29);
		getContentPane().add(email);
		email.setColumns(10);
		
		phoneNumber = new JTextField();
		phoneNumber.setFont(new Font("굴림", Font.PLAIN, 14));
		phoneNumber.setColumns(10);
		phoneNumber.setBounds(134, 275, 147, 29);
		getContentPane().add(phoneNumber);
		
		password = new JTextField();
		password.setFont(new Font("굴림", Font.PLAIN, 14));
		password.setColumns(10);
		password.setBounds(134, 220, 147, 29);
		getContentPane().add(password);
		
		id = new JTextField();
		id.setFont(new Font("굴림", Font.PLAIN, 14));
		id.setColumns(10);
		id.setBounds(134, 164, 147, 29);
		getContentPane().add(id);
		
		num = new JTextField();
		num.setFont(new Font("굴림", Font.PLAIN, 14));
		num.setColumns(10);
		num.setBounds(134, 110, 147, 29);
		getContentPane().add(num);
		
		lblNewLabel_2 = new JLabel("아이디가 존재합니다.");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(134, 195, 147, 15);
		getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		lblNewLabel_3 = new JLabel("사용 가능합니다.");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(134, 195, 147, 15);
		getContentPane().add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		
		button = new JButton(existIDChecker);
		button.setBackground(new Color(51,84,156));
		button.setForeground(Color.WHITE);
		button.setBounds(293, 165, 93, 28);
		getContentPane().add(button);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				check = Login.getOverLab(id.getText());
				if(check) {
					lblNewLabel_3.setVisible(false);
					System.out.println("이미 존재합니다.");
					lblNewLabel_2.setVisible(true);
					
				}else {
					checker = true;
					lblNewLabel_2.setVisible(false);
					System.out.println("사용 가능합니다.");
					lblNewLabel_3.setVisible(true);
				}
			}
			
		});
		
		button_1 = new JButton("가입완료");
		button_1.setBackground(new Color(51,84,156));
		button_1.setForeground(Color.WHITE);
		button_1.setBounds(196, 384, 91, 29);
		getContentPane().add(button_1);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tmp = num.getText();
				if(tmp.length() == 8) {
					join[0] = true;
				}
				int student_id = 0;
				if(!tmp.equals("")) {
					student_id = Integer.parseInt(tmp);
				}
				
				String ID = id.getText();
				if(!check) { // 중복 확인 눌러서 확인 받아야 가입 가능
					join[1] = true;
				}else {
					f = new failpage();
					f.sameCheck();
					f.setVisible(true);
				}
				String PW = password.getText();
				if(PW.length() >= 8) {
					join[2] = true;
				}
				String PN = phoneNumber.getText();
				if(PN.length() >= 13 && PN.charAt(3) == '-' && PN.charAt(8) == '-') {
					boolean c = true;
					for(int i = 0;  i < PN.length(); i++) {
						if(i != 3 && i != 8) {
							c = Character.isDigit(PN.charAt(i));
						}
					}
					if(c == true) {
						join[3] = true;
					}
				}else if(PN.length() >= 12 && PN.charAt(3) == '-' && PN.charAt(7) == '-') {
					boolean c = true;
					for(int i = 0;  i < PN.length(); i++) {
						if(i != 3 && i != 7) {
							c = Character.isDigit(PN.charAt(i));
						}
					}
					if(c == true) {
						join[3] = true;
					}
				}
				String E = email.getText();
				if(E.contains("@")) {
					join[4] = true;
				}
				
				for(int i = 0 ; i < join.length; i++) {
					System.out.println(join[i]);
				}
				if(join[0] && join[1] && join[2] && join[3] && join[4]) { // 가입 가능
					String SQL = String.format("INSERT INTO information values (%d,'%s','%s','%s','%s')",student_id,ID,PW,PN,E);
					try {
						connection.Signup(SQL);
						if(Administrator.getSignCheck()) {
							Administrator.getSingupPage().dispose();
						}else {
							homepage.getSignUpFrame().dispose();
						}
//						complete = true;
						
						System.out.println(complete);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.getMessage();
					}finally {
						if(homepage.getSignUpFrame() != null) {
							homepage.getSignUpFrame().dispose();
						}
					}
				}else {
					t = new failpage();
					t.joinCheck();
					t.setVisible(true);
				}
			}
		});
		
		
		
		JButton button_1_1 = new JButton("뒤로가기");
		button_1_1.setBackground(new Color(51,84,156));
		button_1_1.setForeground(Color.WHITE);
		button_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Administrator.getSignCheck()) {
					Administrator.getSingupPage().dispose();
				}else {
					homepage.getSignUpFrame().dispose();
				}
			}
		});
		button_1_1.setBounds(295, 384, 91, 29);
		getContentPane().add(button_1_1);
		
		JButton btnNewButton = new JButton(new ImageIcon(Main.class.getResource("facebook_s.png")));
		btnNewButton.setBounds(131, 29, 150, 44);
		getContentPane().add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		
		lblNewLabel_4 = new JLabel("8자리");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(293, 117, 57, 15);
		getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("8자리 이상");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(293, 228, 79, 15);
		getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("\'-\'포함");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(293, 283, 57, 15);
		getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("(@ 포함)");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(293, 334, 57, 15);
		getContentPane().add(lblNewLabel_7);
		
		
	}
	public Signup getSignFrame(boolean t) {
		return this;
	}
	public void setComplete() {
		complete = true;
	}
	public void setBack() {
		back =true;
	}
	public boolean getComplete() {
		return complete;
	}
	public boolean getBack() {
		return back;
	}
	public static boolean getSameCheck() {
		return checker;
	}
	public static failpage f() {
		return f;
	}
	public static failpage t() {
		return t;
	}
}
