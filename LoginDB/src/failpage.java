import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class failpage extends JFrame {
	private static boolean check = false;
	public void Loginfailpage() {
		setLocation(600, 400);
		setSize(200, 150);
		
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("로그인 실패");
		label.setBounds(58, 31, 72, 29);
		getContentPane().add(label);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.setBounds(43, 82, 97, 23);
		btnNewButton.setForeground(Color.white);
		btnNewButton.setBackground(new Color(51,84,156));
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			homepage.getFail().setVisible(false);
		}	
		});
	}
	public boolean deleteCheck() {
		
		setLocation(600,400);
		setSize(270,180);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("정말 삭제하시겠습니까?");
		
		label.setBounds(58, 31, 150, 29);
		getContentPane().add(label);
		
		JButton corr = new JButton("확인");
		corr.setBounds(43, 82, 67, 23);
		getContentPane().add(corr);
		corr.setForeground(Color.white);
		corr.setBackground(new Color(51,84,156));
		
		JButton not = new JButton("취소");
		not.setBounds(140, 82, 67, 23);
		getContentPane().add(not);
		not.setForeground(Color.white);
		not.setBackground(new Color(51,84,156));
		
		corr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				System.out.println("!");
				String SQL = String.format("delete from information where id='%s'", client.getID());
				Login.deleteUser(SQL);
				client.getDeleteCheck().dispose();
				Login.getclientPage().dispose();
			}
		});
		
		not.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				check = false;
				client.getDeleteCheck().setVisible(false);
			}
		});
		return check;
	}
	public void joinCheck() {
		setLocation(600,400);
		setSize(270,180);
		getContentPane().setLayout(null);
		JLabel label = new JLabel("형식에 맞게 작성해 주세요.");
		
		label.setBounds(58, 31, 180, 40);
		getContentPane().add(label);
		
		JButton corr = new JButton("확인");
		corr.setForeground(Color.white);
		corr.setBackground(new Color(51,84,156));
		corr.setBounds(93, 82, 67, 23);
		getContentPane().add(corr);
		corr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Signup.f().dispose();
			}
			
		});
	}
	public void sameCheck() {
		if(!Signup.getSameCheck()) {
			setLocation(600,400);
			setSize(270,180);
			getContentPane().setLayout(null);
			
			JLabel label = new JLabel("중복 체크 필수");
			
			label.setBounds(83, 31, 150, 40);
			getContentPane().add(label);
			
			JButton corr = new JButton("확인");
			corr.setForeground(Color.white);
			corr.setBackground(new Color(51,84,156));
			corr.setBounds(93, 82, 67, 23);
			getContentPane().add(corr);
			corr.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Signup.t().dispose();
				}
				
			});
		}
	}
	public static boolean getCheck() {
		return check;
	}
}
