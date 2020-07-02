import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class Administrator extends JFrame {
	private Login connection = new Login();
	private JTable table;
	private String[] list = {"학번","아이디","비밀번호","전화번호","이메일"};
	private ArrayList<ArrayList<String>> content; 
	private static int idx = 0 ; // select table index (row) 
	private static String[][] data;
	private static modify m;
	private static boolean modifyCheck = false;
	private boolean adminRefresh = false;
	private static Signup signup;
	private static boolean adminSign = false;
	@SuppressWarnings({ "unchecked", "unchecked" })
	public Administrator() {
		getList(connection.countList());
	
		setTitle("Administrator Page");
		setSize(700,600);
		setLocation(500,200);
		getContentPane().setBackground(new Color(51,84,156));
		getContentPane().setLayout(null);
		
		data = new String[content.size()][5];
		
		for(int i = 0; i < content.size(); i++) {
			for(int j = 0 ; j < content.get(i).size(); j++) {
				data[i][j] = content.get(i).get(j);
			}
		}
	
		table = new JTable(data,list);
		table.getTableHeader().setBackground(new Color(51,84,156));
		table.getTableHeader().setForeground(Color.white);
		table.setBackground(new Color(51,84,156));
		table.setForeground(Color.white);
		table.setBounds(24, 61, 406, 433);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				idx = table.getSelectedRow();
				System.out.println(idx);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		JScrollPane sp = new JScrollPane();
		sp.setBackground(new Color(51,84,156));
		sp.setLocation(51, 42);
		sp.setSize(452,453);
		sp.setViewportView(table);
		getContentPane().add(sp);
//		getContentPane().add(table);
		
//		super.add(new JScrollPane(table));
		
		JButton addUser = new JButton("추가");
		addUser.setForeground(Color.WHITE);
		addUser.setBackground(new Color(51,84,156));
		addUser.setBounds(545, 84, 97, 23);
		getContentPane().add(addUser);
		addUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				signup = new Signup();
				signup.setVisible(true);
				adminSign = true;
			}
			
		});
		
		
		JButton deleteUser = new JButton("삭제");
		deleteUser.setForeground(Color.WHITE);
		deleteUser.setBackground(new Color(51,84,156));
		deleteUser.setBounds(545, 143, 97, 23);
		getContentPane().add(deleteUser);
		deleteUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = data[idx][1];
				String SQL = String.format("delete from information where id='%"
						+ "s'",name);
				connection.deleteUser(SQL);
			}
			
		});
		
		JButton modifyUser = new JButton("수정/변경");
		modifyUser.setForeground(Color.WHITE);
		modifyUser.setBackground(new Color(51,84,156));
		modifyUser.setBounds(545, 202, 97, 23);
		getContentPane().add(modifyUser);
		modifyUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				modifyCheck = true;
				m = new modify();
				m.setVisible(true);
				m.setCheck(true);
			}
			
		});
		JButton refresh = new JButton("새로 고침");
		refresh.setForeground(Color.WHITE);
		refresh.setBackground(new Color(51,84,156));
		refresh.setBounds(545, 263, 97, 23);
		getContentPane().add(refresh);
		
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getList(connection.countList());
				adminRefresh = true;
				if(adminRefresh) {
					Login.getADPage().dispose();
					Login.newAdminPage();
				}
			}
			
		});
		JButton logoutUser = new JButton("로그아웃");
		logoutUser.setForeground(Color.WHITE);
		logoutUser.setBounds(545, 472, 97, 23);
		getContentPane().add(logoutUser);
		logoutUser.setBackground(new Color(51,84,156));
		logoutUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Login.getADPage().dispose();
				Main.getHomepage().setVisible(true);
			}
		});
	}
	public static boolean getModifyMode() {
		return modifyCheck;
	}
	public void getList (int count ) {
//		numList = new String[count];
		
		String SQL = "select * from information";
		content = connection.getList(SQL);
	}
	public static String[] getData (){
		return data[idx];
	}
	public static modify getModifyFrame() {
		return m;
	}
	public boolean getRefresh() {
		System.out.println(adminRefresh);
		return adminRefresh;
	}
	public static Signup getSingupPage() {
		return signup; // admin
	}
	public static boolean getSignCheck() {
		return adminSign;
	}
}

