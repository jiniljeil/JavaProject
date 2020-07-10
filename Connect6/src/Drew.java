import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Drew extends JFrame{
	private String sen ;
	private static int drewUser = 0;
	private finish f = null;
	private static drewFrame df = null;
	public Drew() {
		setLocation(500, 400);
		setSize(380,300);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.black);
		
		JButton checkButton = new JButton("���");
		checkButton.setBackground(Color.black);
		checkButton.setForeground(Color.WHITE);
		checkButton.setBounds(70, 163, 97, 34);
		checkButton.setFocusPainted(false);
		getContentPane().add(checkButton);
		checkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Frame.getDrew().dispose();
				df = new drewFrame();
				df.setVisible(true);
			}
		});
		JButton backButton = new JButton("�ڷ� ����");
		backButton.setFocusPainted(false);
		backButton.setForeground(Color.WHITE);
		backButton.setBounds(213, 163, 97, 34);
		backButton.setBackground(Color.black);
		getContentPane().add(backButton);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Frame.getDrew().dispose();
			}
			
		});
		
		if(drewUser == 1) {
			sen = "�浹 ������ ���ºθ� ��û";
		}else if(drewUser == 2) {
			sen = "�� ������ ���ºθ� ��û";
		}
		JLabel sentence = new JLabel(sen);
		sentence.setForeground(Color.WHITE);
		sentence.setHorizontalAlignment(SwingConstants.CENTER);
		sentence.setFont(new Font("����", Font.PLAIN, 20));
		sentence.setBounds(58, 47, 264, 69);
		getContentPane().add(sentence);
	}
	
	public static void setDrewUser(int mode) {
		drewUser = mode;
	}
	public static drewFrame getDrewFrame() {
		return df;
	}
}
