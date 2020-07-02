import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import javax.swing.Icon;

public class client extends JFrame{
	private static boolean clientMode = false;
	private static String id;
	private static String password;
	private static ArrayList<String> data;
	private static modify m;
	private static failpage f;
	private boolean check;
	private boolean openPage = false;
	private int like = 12;
	/**
	 * @wbp.nonvisual location=-200,469
	 */
	public client() {
		
		setTitle("Client Page");
		setSize(298,380);
		setLocation(600,300);
		setBackground(Color.white);
		
		getContentPane().setLayout(null);
//		Login connection = new Login();
		
		JButton btnNewButton = new JButton("·Î±×¾Æ¿ô");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(90, 224, 97, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.setBackground(new Color(51,84,156));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Login.getclientPage().dispose();
				Main.getHomepage().setVisible(true);
			}
		});
		
		JButton userInfo = new JButton("Á¤º¸º¯°æ");
		userInfo.setForeground(Color.WHITE);
		userInfo.setBackground(new Color(51,84,156));
		userInfo.setBounds(90, 257, 97, 23); // 
		getContentPane().add(userInfo);
		userInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientMode = true;
				data = Login.getUserData(id);
			    m = new modify();
				m.setVisible(true);
			}
			
		});
		
		JButton removeUser = new JButton("È¸¿øÅ»Åð");
		removeUser.setBackground(new Color(51,84,156));
		removeUser.setForeground(Color.WHITE);
		removeUser.setBounds(90, 290, 97, 23);
		getContentPane().add(removeUser);
		
		
//		String s_id = Login.getSID(Login.tempID);
		
		JLabel SID = new JLabel(Login.tempID);
		
		SID.setFont(new Font("±¼¸²", Font.BOLD, 15));
		SID.setHorizontalAlignment(SwingConstants.CENTER);
		SID.setBounds(58, 188, 159, 23);
		getContentPane().add(SID);
		
		JButton btnNewButton_1 = new JButton(new ImageIcon(Main.class.getResource("Profile.png")));
		btnNewButton_1.setBounds(61, 23, 159, 155);
		getContentPane().add(btnNewButton_1);
		
		JButton image = new JButton(new ImageIcon(Main.class.getResource("image.jpg")));
		image.setBounds(239, 287, 31, 29);
		getContentPane().add(image);
		image.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				like++;
			}
		});
		
		JButton message = new JButton(new ImageIcon(Main.class.getResource("message.png")));
		message.setBounds(239, 245, 31, 31);
		getContentPane().add(message);
		
		JButton instagram = new JButton(new ImageIcon(Main.class.getResource("instagram.png")));
		instagram.setBounds(239, 204, 31, 31);
		getContentPane().add(instagram);
		
		JLabel Like = new JLabel(String.valueOf(like));
		Like.setFont(new Font("±¼¸²", Font.BOLD, 14));
		Like.setBounds(215, 290, 21, 23);
		getContentPane().add(Like);
		
		
		instagram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("https://www.instagram.com/?hl=ko").toURI());
						
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		message.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Desktop.getDesktop().browse(new URL("https://download.cnet.com/Facebook-Desktop-Messenger/3000-2150_4-76359343.html").toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		removeUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			    f = new failpage();
			    f.deleteCheck();
				f.setVisible(true);
			}
		});
	}
	
	public static void setLoginData(String ID, String pw) {
		id = new String(ID);
		password = new String(pw);
	}
	public static boolean getClientMode() {
		return clientMode;
	}
	public static String[] getClientData(){
		String[] userData = new String[data.size()];
		for(int i = 0 ; i < data.size(); i++) {
			userData[i] = data.get(i);
		}
		
		return userData;
	}
	public static modify getRevisePage() {
		return m;
	}
	public static failpage getDeleteCheck() {
		return f;
	}
	public static String getID() {
		return id;
	}
}
