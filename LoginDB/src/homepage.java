import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class homepage extends JFrame{
	private JTextField id;
	private JPasswordField password;
	private static Signup signup = null;
	private static failpage fp;
	
	public homepage() {
		Login connection = new Login();
		
//		System.out.println("«œ¿’"+ connection.isAdmin());
		
		setTitle("Login Page");
		setSize(600,500);
		setLocation(500,200);
		
		JButton fb = new JButton(new ImageIcon(Main.class.getResource("facebook.png")));
		fb.setBorderPainted(false);
		fb.setContentAreaFilled(false);
		fb.setFocusPainted(false);
		fb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
//		fb.setSize(200,75);
		fb.setBounds(196,109,200,61);
		super.add(fb);
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(51,84,156));
		
//		JLabel lblNewLabel = new JLabel("Login");
//		lblNewLabel.setFont(new Font("±º∏≤", Font.PLAIN, 40));
//		lblNewLabel.setBounds(262, 76, 109, 78);
//		getContentPane().add(lblNewLabel);
		
		JLabel lblLoginId = new JLabel("Login ID:");
		lblLoginId.setForeground(Color.WHITE);
		lblLoginId.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblLoginId.setBounds(173, 233, 58, 21);
		getContentPane().add(lblLoginId);
		
		id = new JTextField();
		id.setBounds(239, 234, 116, 21);
		getContentPane().add(id);
		id.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblPassword.setBounds(164, 283, 67, 15);
		getContentPane().add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(239, 281, 116, 21);
		getContentPane().add(password);
		password.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("±º∏≤", Font.PLAIN, 12));
		btnLogin.setBounds(246, 350, 97, 23);
		getContentPane().add(btnLogin);
		btnLogin.setBackground(new Color(51,84,156));
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setBounds(246, 393, 97, 23);
		getContentPane().add(btnSignUp);
		btnSignUp.setBackground(new Color(51,84,156));
		
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ID = id.getText();
				char[] PW = password.getPassword();
				String pw = String.valueOf(PW);
				boolean check = connection.LoginChecker(ID,pw);
				if(check == false) {
					fp = new failpage();
					fp.Loginfailpage();
					fp.setVisible(true);
				}
			}
			
		});
		
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				signup = new Signup();
				signup.setVisible(true);
				
				if(signup.getComplete() || signup.getBack()) {
					signup.dispose();
				}
			}
		});
		
		
	}
	
	public static Signup getSignUpFrame() {
		return signup;
	}
	
	public static failpage getFail() {
		return fp;
	}
}
