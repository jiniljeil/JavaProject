import java.sql.*;
import java.util.ArrayList;

import javax.swing.JFrame;
public class Login {
	
	private static Connection connect;
	private static Statement st;
	private static ResultSet rs;
	private static Administrator ad;
	private static client clientPage;
	public static String tempID;
	public Login() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","id","password");
			System.out.println(connect);
//			connect.close();
			st = connect.createStatement();
		}catch(Exception e) {
			System.out.println("Database Connection Fail..." );
			System.out.println(e.getMessage());
		}finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//int num, String id, String password, String phoneNumber , String email
	public boolean LoginChecker(String id, String password) {
		boolean check = false;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wlsdlf159!");
			st = connect.createStatement();
			
			String SQL = "select * from information";
			rs = st.executeQuery(SQL);
			
			while(rs.next()) {
				if(rs.getString("id").equals(id) && rs.getString("password").equals(password)) {
					System.out.println("Client Login Success!");
					if(id.equals("root") && password.equals("root")) {
					    ad = new Administrator();
						ad.setVisible(true);
						Main.getHomepage().setVisible(false);
					}else { // client 로그인 
						clientPage = new client();
						tempID = new String(rs.getString("num"));
						clientPage.setLoginData(id,password);
						clientPage.setVisible(true);
//						Main.getHomepage().setVisible(false);
					}
					check = true;
					break;
				}
//				System.out.println(rs.getString("id"));
//				System.out.println(rs.getString("password"));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("검색 오류");
		}finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check;
	}
	
	public void Signup(String sql) throws SQLException {
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wlsdlf159!");
		st = connect.createStatement();
		PreparedStatement ps = connect.prepareStatement(sql);
		int r = ps.executeUpdate();
		if(r == 1) {
			return;
		}
		connect.close();
		return ;
	}
	
	public int countList() {
		int count = 0 ;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wlsdlf159!");
			st = connect.createStatement();
			String SQL = "select * from information";
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				count++;
			}
		}catch(Exception e) {
			e.getMessage();
		}finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public ArrayList<ArrayList<String>> getList(String sql) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wlsdlf159!");
			st = connect.createStatement();
			String SQL = sql;
			rs = st.executeQuery(SQL); // select * from information
			int i = 0 ;
			
			while(rs.next()) {
				ArrayList<String> tmp = new ArrayList<String>();
				tmp.add(String.valueOf(rs.getString(1)));
				for(int j = 2; j <= 5; j++) {
					tmp.add(rs.getString(j));
				}
				data.add(tmp);
			}
		}catch(Exception e) {
			e.getMessage();
		}finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		for(int i = 0 ; i < data.size(); i++) {
//			for(int j = 0 ; j <data.get(i).size(); j++) {
//				System.out.println(data.get(i).get(j));
//			}
//		}
		return data;
	}
	
	public static void deleteUser(String sql) {
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wlsdlf159!");
			st = connect.createStatement();
			String SQL = sql;
			PreparedStatement ps = connect.prepareStatement(sql);
			int r = ps.executeUpdate();
			if(r == 1) {
				System.out.println("삭제되었습니다.");
			}
		}catch(Exception e) {
			e.getMessage();
		}finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void modifyNum(String sql) {
		try{
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wlsdlf159!");
			st = connect.createStatement();
			PreparedStatement ps = connect.prepareStatement(sql);
			int r = ps.executeUpdate();
			if(r == 1) {
				System.out.println("학번이 수정 되었습니다.");
			}
		}catch(Exception e) {
			e.getMessage();
		}finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void modifyID(String sql) {
		try{
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wlsdlf159!");
			st = connect.createStatement();
			PreparedStatement ps = connect.prepareStatement(sql);
			int r = ps.executeUpdate();
			if(r == 1) {
				System.out.println("아이디가 수정 되었습니다.");
			}
		}catch(Exception e) {
			e.getMessage();
		}finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void modifyPW(String sql) {
		try{
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wlsdlf159!");
			st = connect.createStatement();
			PreparedStatement ps = connect.prepareStatement(sql);
			int r = ps.executeUpdate();
			if(r == 1) {
				System.out.println("비밀번호가 수정 되었습니다.");
			}
		}catch(Exception e) {
			e.getMessage();
		}finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void modifyPH(String sql) {
		try{
			PreparedStatement ps = connect.prepareStatement(sql);
			int r = ps.executeUpdate();
			if(r == 1) {
				System.out.println("전화 번호가 수정 되었습니다.");
			}
		}catch(Exception e) {
			e.getMessage();
		}
	}
	public void modifyEmail(String sql) {
		try{
			PreparedStatement ps = connect.prepareStatement(sql);
			int r = ps.executeUpdate();
			if(r == 1) {
				System.out.println("이메일이 수정 되었습니다.");
			}
		}catch(Exception e) {
			e.getMessage();
		}
	}
	
	public static Administrator getADPage() {
		return ad;
	}
	public static ArrayList<String> getUserData(String id){
		
		ArrayList<String> userData = new ArrayList<String>();
		String SQL = String.format("select * from information where id='%s'", id);
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wlsdlf159!");
			st = connect.createStatement();
			rs = st.executeQuery(SQL);
			
			if(rs.next()) {
				userData.add(String.valueOf(rs.getString(1)));
				userData.add(rs.getString(2));
				userData.add(rs.getString(3));
				userData.add(rs.getString(4));
				userData.add(rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userData;
	}
	
	public static client getclientPage() {
		return clientPage;
	}
	
	public static void newAdminPage() {
		ad = new Administrator();
		ad.setVisible(true);
	}
	public static String getSID(String id) {
		
		String SQL = "select * from information";
		String s_id = null;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wlsdlf159!");
			st = connect.createStatement();
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				if(rs.getString(2).equals(id)) {
					s_id = rs.getString(1);
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s_id;
	}
	public Login getLogin() {
		return new Login();
	}
	public static boolean getOverLab(String id) {
		boolean check = false;
		String SQL = "select * from information";
		
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wlsdlf159!");
			st = connect.createStatement();
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				if(rs.getString(2).equals(id)) {
					check = true;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check;
	}
}
