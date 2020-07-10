import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class finish extends JFrame{
	private static int Winner = 0;
	private String sen ;
	static int win1 = 0;
	static int drew1 = 0;
	static int lose1 = 0;
	static int win2 = 0;
	static int drew2 = 0;
	static int lose2 = 0;
	public finish() {
		setLocation(500, 400);
		setSize(380,300);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.black);
		
		JButton checkButton = new JButton("È®ÀÎ");
		checkButton.setBackground(Color.black);
		checkButton.setForeground(Color.WHITE);
		checkButton.setBounds(70, 163, 97, 34);
		checkButton.setFocusPainted(false);
		getContentPane().add(checkButton);
		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Frame.getFinish().dispose();
			}
		});
		JButton backButton = new JButton("µÚ·Î °¡±â");
		backButton.setFocusPainted(false);
		backButton.setForeground(Color.WHITE);
		backButton.setBounds(213, 163, 97, 34);
		backButton.setBackground(Color.black);
		getContentPane().add(backButton);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Frame.getFinish().dispose();
			}
			
		});
		if(Winner == 1) {
			sen = "Èæµ¹ ½Â¸® !";
		}else if(Winner == 2) {
			sen = "Èòµ¹ ½Â¸® !";
		}
		JLabel sentence = new JLabel(sen);
		sentence.setForeground(Color.WHITE);
		sentence.setHorizontalAlignment(SwingConstants.CENTER);
		sentence.setFont(new Font("±¼¸²", Font.PLAIN, 28));
		sentence.setBounds(80, 45, 208, 79);
		getContentPane().add(sentence);
	}
	
	public static void setWinner(int w ) {
		Winner = w;
	}
}
