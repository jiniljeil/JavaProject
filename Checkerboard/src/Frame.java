import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Frame extends JFrame implements MouseListener{
	private int mode = 0; // 1: 나 , 2: 상대방
	private int first = 0; // 2 end 
	private Point blackPoint = new Point();
	private Point whitePoint = new Point();
	private JLabel black;
	private JLabel white; 
	private BufferedImage blackBI;
	private BufferedImage whiteBI;
	private BufferedImage benBI;
	private JPanel panel;
	private boolean firstCheck = false;
	private int blackCount = 1;
	private int whiteCount = 1;
	private ArrayList<Point> blackList = new ArrayList<Point>();
	private ArrayList<Point> whiteList = new ArrayList<Point>();
	private ArrayList<Point> pointList = new ArrayList<Point>();
	private int[][] selectedPoint = new int[19][19];
	private boolean gameStart = false;
	private int count = 1500;
	private Timer timer ;
	private JLabel benSpot = new JLabel();
	private Point benPoint = new Point();
	private static finish f = null;
	private static Drew d = null;
	private JLabel currentRock;
	
	public Frame() {
		
		try {
			blackBI = ImageIO.read(new File("C:\\Users\\김진일\\Desktop\\이미지\\blackRock.png"));
			whiteBI = ImageIO.read(new File("C:\\Users\\김진일\\Desktop\\이미지\\whiteRock.png"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		for(int i = 0; i <selectedPoint.length; i++) {
			for(int j = 0 ; j <selectedPoint[i].length; j++) {
				 selectedPoint[i][j] = 0;
			}
		}
		setSize(1150,850);
		setLocation(100,50);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.black);
		ImageIcon boardImage = new ImageIcon(Main.class.getResource("board.png"));
		
		JLabel board = new JLabel(boardImage);
		board.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
				if(mode == 0) {
					boolean benCheck = true;
					benPoint = e.getPoint();
					
					for(int i = 0 ; i < 19; i++ ) {
						if(benPoint.x >= 37 * i  && benPoint.x  <= 37 * (i+1)){
							benPoint.x = 37 * i;
						}
						if(benPoint.y >= 37 * i  && benPoint.y <= 37 * (i+1)) {
							benPoint.y = 37 * i;
						}
					}
					
					benSpot = new JLabel(new ImageIcon(Main.class.getResource("benmark.png")));
					benSpot.setBackground(new Color(220,179,92));
					benSpot.setBounds(benPoint.x, benPoint.y, 35,35);
					benSpot.setOpaque(true);
					
					for(int i = 0 ; i < pointList.size() ; i++) {
						if(pointList.get(i).x == benPoint.x && pointList.get(i).y == benPoint.y) {
							benCheck = false;
							break;
						}
					}
					if(benCheck) {
						selectedPoint[benPoint.y/37][benPoint.x/37] = 3;
						pointList.add(benPoint);
						System.out.println(pointList.size());
					}
					
					try {
						benBI = ImageIO.read(new File("C:\\Users\\김진일\\Desktop\\이미지\\benmark.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					getContentPane().add(benSpot);
					benSpot.setIcon(new ImageIcon(benBI.getScaledInstance(35, 35, BufferedImage.TYPE_INT_RGB)));
					panel.add(benSpot);
					
					
				}
				
				if(gameStart) {
					if(mode == 1) {
						blackPoint = e.getPoint();
//						System.out.println(blackPoint.x);
						
						for(int i = 0 ; i < 19; i++ ) {
							if(blackPoint.x >= 37 * i  && blackPoint.x  <= 37 * (i+1)){
//								blackPoint.x = 37 * i + 20  ;
								blackPoint.x = 37 * i;
							}
							if(blackPoint.y >= 37 * i  && blackPoint.y <= 37 * (i+1)) {
//								blackPoint.y = 37 * i + 20 ;
								blackPoint.y = 37 * i;
							}
						}
//						System.out.println(blackPoint.y);
						
					    
						black = new JLabel(new ImageIcon(Main.class.getResource("blackRock.png")));
//						
						black.setBackground(new Color(220,179,92));
						black.setBounds(blackPoint.x, blackPoint.y, 35, 35);
						
						try {
							blackBI = ImageIO.read(new File("C:\\Users\\김진일\\Desktop\\이미지\\blackRock.png"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						Graphics g = black.getGraphics();
						getContentPane().add(black);
						black.setIcon(new ImageIcon(blackBI.getScaledInstance(35, 35, BufferedImage.TYPE_INT_RGB)));
						
						panel.add(black);
						
						boolean check = true;
						for(int i = 0 ; i < pointList.size() ; i++) {
							if(pointList.get(i).x == blackPoint.x && pointList.get(i).y == blackPoint.y) {
								check = false;
								break;
							}
						}
//						paintComponent(g);
//						System.out.println(blackPoint.x + " " + blackPoint.y);
						
						
						if(check) {
							benSpot.setOpaque(false);
							black.setOpaque(true);
							selectedPoint[blackPoint.y/37][blackPoint.x/37] = 1;
							int vic = victory();
							if(vic == 1) {
								System.out.println("BLACK WIN !");
								finish.win1 += 1;
								finish.lose2 += 1;
								finish.setWinner(1);
								f = new finish();
								f.setVisible(true);
								gameStart = false;
							}else if(vic == 2) {
								finish.win2 += 1;
								finish.lose1 += 1;
								System.out.println("WHITE WIN !");
								finish.setWinner(2);
								f = new finish();
								f.setVisible(true);
								gameStart = false;
							}
							if(!firstCheck) {
								currentRock.setBackground(Color.black);
								currentRock.setOpaque(true);
								currentRock.setIcon(new ImageIcon(whiteBI.getScaledInstance(35, 35, BufferedImage.TYPE_INT_RGB)));
								count = 1500;
								mode = 2;
								blackList.add(blackPoint); 
								pointList.add(blackPoint);
								firstCheck = true;
							}else if(firstCheck && blackCount == 2) {
								currentRock.setBackground(Color.black);
								currentRock.setOpaque(true);
								currentRock.setIcon(new ImageIcon(whiteBI.getScaledInstance(35, 35, BufferedImage.TYPE_INT_RGB)));
								count = 1500;
								blackCount = 1;
								mode = 2;
//								System.out.println("BLACK");
								blackList.add(blackPoint); 
								pointList.add(blackPoint);
							}else {
								blackCount++;
								blackList.add(blackPoint); 
								pointList.add(blackPoint);
								
							}
						}
						
						
					
					}else if(mode == 2) {
						
						whitePoint = e.getPoint();
						white = new JLabel(new ImageIcon(Main.class.getResource("whiteRock.png")));
						
						white.setBackground(new Color(220,179,92));
						for(int i = 0 ; i < 19; i++ ) {
							if(whitePoint.x >= 37 * i  && whitePoint.x  <= 37 * (i+1)){
//								whitePoint.x = 37 * i + 20  ;
								whitePoint.x = 37 * i;
							}
							if(whitePoint.y >= 37 * i  && whitePoint.y <= 37 * (i+1)) {
//								whitePoint.y = 37 * i + 20 ;
								whitePoint.y = 37 * i;
							}
						}
						
						white.setBounds(whitePoint.x , whitePoint.y, 35, 35);
				
						try{
							whiteBI = ImageIO.read(new File("C:\\Users\\김진일\\Desktop\\이미지\\whiteRock.png"));
						}catch(Exception e1) {
							e1.printStackTrace();
						}
						getContentPane().add(white);
						white.setIcon(new ImageIcon(whiteBI.getScaledInstance(35, 35, BufferedImage.TYPE_INT_RGB)));
						panel.add(white);
						
						boolean check = true;
						for(int i = 0 ; i < pointList.size() ; i++) {
							if(pointList.get(i).x == whitePoint.x && pointList.get(i).y == whitePoint.y){
								check =false;
								break;
							}
						}
						
						if(check) {
							benSpot.setOpaque(false);
							white.setOpaque(true);
							selectedPoint[whitePoint.y/37][whitePoint.x/37] = 2;
							int vic = victory();
							if(vic == 1) {
								System.out.println("BLACK WIN !");
								finish.setWinner(1);
								f = new finish();
								f.setVisible(true);
								gameStart = false;
								finish.win1 += 1;
								finish.lose2 += 1;
							}else if(vic == 2) {
								System.out.println("WHITE WIN !");
								finish.setWinner(2);
								f = new finish();
								f.setVisible(true);
								gameStart = false;
								finish.win2 += 1;
								finish.lose1 += 1;
							}
							if(!firstCheck) {
								currentRock.setBackground(Color.black);
								currentRock.setOpaque(true);
								currentRock.setIcon(new ImageIcon(blackBI.getScaledInstance(35, 35, BufferedImage.TYPE_INT_RGB)));
								count = 1500;
								mode =1;
								whiteList.add(whitePoint);
								pointList.add(whitePoint);
							}else if(firstCheck && whiteCount == 2) {
								currentRock.setBackground(Color.black);
								currentRock.setOpaque(true);
								currentRock.setIcon(new ImageIcon(blackBI.getScaledInstance(35, 35, BufferedImage.TYPE_INT_RGB)));
								count = 1500;
								whiteCount = 1;
								mode = 1;
								whiteList.add(whitePoint);
								pointList.add(whitePoint);
							}else {
								whiteCount++;
								whiteList.add(whitePoint);
								pointList.add(whitePoint);
							}
						}
					}
					
					// 승 패 체크 
				}
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
		
		JButton startButton = new JButton("게임 시작");
		startButton.setFont(new Font("굴림", Font.BOLD, 20));
		startButton.setForeground(Color.WHITE);
		startButton.setBorderPainted(false);
		startButton.setFocusable(false);
		startButton.setOpaque(true);
		startButton.setBackground(Color.black);
		startButton.setBounds(797, 644, 168, 80);
		getContentPane().add(startButton);
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(gameStart) {
					for(int i = 0 ; i < selectedPoint.length; i++) {
						for(int j = 0 ; j < selectedPoint[i].length; j++) {
							selectedPoint[i][j] = 0;
						}
					}
					blackList.clear();
					whiteList.clear();
					pointList.clear();
					
					gameStart = false;
					mode = 0;
					timer.start();
					if(firstCheck) {
						Main.setFinishCheck(true);
					}
				}else {
					for(int i = 0 ; i < selectedPoint.length; i++) {
						for(int j = 0 ; j < selectedPoint[i].length; j++) {
							selectedPoint[i][j] = 0;
						}
					}
					blackList.clear();
					whiteList.clear();
					pointList.clear();
					
					gameStart = true;
					mode = 1;
					timer.start();
//					if(firstCheck) {
//						Main.setFinishCheck(true);
//					}
					
				}
			}
			
		});
		
		board.setBounds(31, 33, 700, 700);
		
		getContentPane().add(board);
		
		panel = new JPanel();
		panel.setBounds(31, 33, 700, 700);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton exitButton = new JButton(new ImageIcon(Main.class.getResource("exit.png")));
		exitButton.setBackground(Color.black);
		exitButton.setBorderPainted(false);
		exitButton.setBounds(996, 644, 85, 85);
		exitButton.setOpaque(true);
		getContentPane().add(exitButton);
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(1);
			}
			
		});
		
		
		
		JButton opponent = new JButton(new ImageIcon(Main.class.getResource("Profile.png"))); // 상대방
		opponent.setBounds(774, 52, 150, 150);
		getContentPane().add(opponent);
		opponent.setBorderPainted(false);
		opponent.setContentAreaFilled(false);
		opponent.setFocusPainted(false);
		
		JButton me = new JButton(new ImageIcon(Main.class.getResource("Profile.png"))); // 나
		me.setBounds(953, 52, 150, 150);
		getContentPane().add(me);
		me.setBorderPainted(false);
		me.setContentAreaFilled(false);
		me.setFocusPainted(false);
		
		JLabel lblNewLabel = new JLabel("User: Me");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(796, 209, 105, 58);
		getContentPane().add(lblNewLabel);
		
		JLabel lbll = new JLabel("User: Opponent");
		lbll.setForeground(Color.WHITE);
		lbll.setHorizontalAlignment(SwingConstants.CENTER);
		lbll.setBounds(985, 209, 89, 58);
		getContentPane().add(lbll);
		
		JLabel lblNewLabel_1 = new JLabel("\uC2DC\uAC04: ");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(832, 561, 48, 47);
		getContentPane().add(lblNewLabel_1);
		
		
		
		
		JLabel time = new JLabel();
		
		time.setFont(new Font("굴림", Font.BOLD, 20));
		time.setForeground(Color.WHITE);
		time.setBounds(892, 566, 70, 35);
		getContentPane().add(time);
		
		
		JButton stopButton = new JButton(new ImageIcon(Main.class.getResource("pause.png")));
		stopButton.setBounds(996, 555, 58, 58);
		getContentPane().add(stopButton);
		stopButton.setBorderPainted(false);
		
		JButton nexon = new JButton(new ImageIcon(Main.class.getResource("nexon.jpg")));
		nexon.setBounds(969, 744, 134, 40);
		getContentPane().add(nexon);
		nexon.setBorderPainted(false);
		
		JButton giveup = new JButton("기권");
		giveup.setBackground(Color.black);
		giveup.setForeground(Color.black);
		giveup.setFocusable(false);
//		giveup.setBorderPainted(false);
		giveup.setForeground(Color.WHITE);
		giveup.setFont(new Font("굴림", Font.PLAIN, 18));
		giveup.setBounds(797, 475, 127, 58);
		getContentPane().add(giveup);
		giveup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(mode == 1) {
					finish.setWinner(2);
					finish.win2 +=1;
					finish.lose1 += 1;
				}else if(mode == 2) {
					finish.setWinner(1);
					finish.win1 += 1;
					finish.lose2 += 2;
				}
				timer.stop();
				gameStart = false;
				f = new finish();
				f.setVisible(true);
				pointList.clear();
				blackList.clear();
				whiteList.clear();
			}
			
		});
		
		JButton drew = new JButton("무승부");
		drew.setBackground(Color.black);
		drew.setForeground(Color.black);
//		drew.setBorderPainted(false);
		drew.setFocusable(false);
		drew.setForeground(Color.WHITE);
		drew.setFont(new Font("굴림", Font.PLAIN, 18));
		drew.setBounds(953, 475, 127, 58);
		getContentPane().add(drew);
		
		JLabel currentOrder = new JLabel("현재 차례:") ;
		currentOrder.setForeground(Color.WHITE);
		currentOrder.setBounds(797, 337, 70, 40);
		getContentPane().add(currentOrder);
		
		currentRock = new JLabel(new ImageIcon(blackBI.getScaledInstance(35, 35, BufferedImage.TYPE_INT_RGB)));
		
		currentRock.setBounds(867, 337, 35, 35);
		getContentPane().add(currentRock);
		
		drew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Drew.setDrewUser(mode);
				d = new Drew();
				d.setVisible(true);
			}
			
		});
		
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(gameStart == false) {
					gameStart = true;
					timer.restart();
				}else { // true
					gameStart = false;
					timer.stop();
				}
			}
		});
		
		
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(gameStart) {
					count--;
					time.setText(String.valueOf(count));
					if(count == 0) {
						if(mode == 1 || blackCount == 2 ) {
							currentRock.setBackground(Color.black);
							currentRock.setOpaque(true);
							currentRock.setIcon(new ImageIcon(whiteBI.getScaledInstance(35, 35, BufferedImage.TYPE_INT_RGB)));
							mode = 2;
							count = 1500;
						}else if(mode == 2 || whiteCount == 2) {
							currentRock.setBackground(Color.black);
							currentRock.setOpaque(true);
							currentRock.setIcon(new ImageIcon(blackBI.getScaledInstance(35, 35, BufferedImage.TYPE_INT_RGB)));
							mode = 1;
							count = 1500;
							
						}
					}
				}
			}
		});
		
		
		
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
//		super.paintComponents(g);
		
		if(mode == 1) {
			g2.drawOval(blackPoint.x, blackPoint.y, 10, 10);
		}else if(mode == 2) {
			
		}
//		g2.drawImage(blackBI, blackPoint.x, blackPoint.y, null);
	}

	public int victory() {
		int victory = 0; // 1 : black , 2: white
		
//		for(int i =  0; i < selectedPoint.length; i++) {
//			for(int j = 0 ;j < selectedPoint[i].length; j++) {
//				System.out.print(selectedPoint[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		if(mode == 1) {
			if(selectedPoint[blackPoint.y/37][blackPoint.x/37] == 1) { // 가로 
				int count = 0;
				boolean check1 = false, check2 = false;
				if(blackPoint.x/37 + 5 < 19) {
					for(int k = 0 ; k < 6; k++) {
						if(selectedPoint[blackPoint.y/37][blackPoint.x/37 + k] == 1) {
							count++;
							check1 = true;
						}else {
							break;
						}
					}
				}
				
				if(blackPoint.x/37 - 5 >= 0) {
					for(int k = 0 ; k < 6; k++) {
						if(selectedPoint[blackPoint.y/37][blackPoint.x/37 - k] == 1) {
							count++;
							check2 = true;
						}else {
							break;
						}
					}
				}
				if(check1 && check2) {
					if(count-1 == 6) {
						return 1;
					}
				}else {
					if(count == 6) {
						return 1;
					}
				}
			}
			
			if(selectedPoint[blackPoint.y/37][blackPoint.x/37] == 1) { // 세로
				int count = 0;
				boolean check1 = false, check2 = false;
				if(blackPoint.y/37 + 5 < 19) { // 아래로 
					for(int k = 0 ; k < 6 ; k++) {
						if(selectedPoint[blackPoint.y/37 + k][blackPoint.x/37] == 1) {
//							System.out.println(blackPoint.y/37 + k + " " + blackPoint.x/37);
							count++;
							check1 = true;
						}else {
							break;
						}
					}
				}
//				System.out.println(count + "1");
				if(blackPoint.y/37 -5 >= 0) { // 위로 
					for(int k = 0 ; k < 6 ;k++) {
						if(selectedPoint[blackPoint.y/37-k][blackPoint.x/37] == 1) {
//							System.out.println(blackPoint.y/37 - k + " " + blackPoint.x/37);
							count++;
							check2 = true;
						}else {
							break;
						}
					}
				}
				if(check1 && check2) {
					if(count-1 == 6) {
						return 1;
					}
				}else {
					if(count == 6) {
						return 1;
					}
				}
			}
			if(selectedPoint[blackPoint.y/37][blackPoint.x/37] == 1) { // 오른쪽 대각선 아래
				int count = 0; 
				boolean check1 = false, check2 = false;
				if(blackPoint.y/37 + 5 < 19 && blackPoint.x/37 + 5 < 19) {
					for(int k = 0 ; k < 6 ; k++) {
						if(selectedPoint[blackPoint.y/37+k][blackPoint.x/37+k] == 1) {
							count++;
							check1 = true;
						}else {
							break;
						}
					}
					
				}
				if(blackPoint.y/37 - 5 >= 0 && blackPoint.x/37 -5 >= 0) {
					for(int k =0 ; k < 6 ; k++) {
						if(selectedPoint[blackPoint.y/37-k][blackPoint.x/37-k] == 1) {
							count++;
							check2 = true;
						}else {
							break;
						}
					}
				}
				if(check1 && check2) {
					if(count-1 == 6) {
						return 1;
					}
				}else {
					if(count == 6) {
						return 1;
					}
				}
			}
			
			
			if(selectedPoint[blackPoint.y/37][blackPoint.x/37] == 1) { // 왼쪽 대각선 아래 + 오른쪽 대각선 위 
				int count = 0;
				boolean check1 = false, check2 = false;
				
				if(blackPoint.y/37 + 5 < 19 && blackPoint.x/37 -5 >= 0) {
					for(int k =0 ; k < 6 ; k++) {
						if(selectedPoint[blackPoint.y/37+k][blackPoint.x/37-k] == 1) {
							count++;
							check1 = true;
						}else {
							break;
						}
					}
				}
				
				if(blackPoint.y/37 -5 >= 0&& blackPoint.x/37 +5 < 19) {
					for(int k =0 ; k < 6 ; k++) {
						if(selectedPoint[blackPoint.y/37-k][blackPoint.x/37+k] == 1) {
							count++;
							check2 = true;
						}else {
							break;
						}
					}
				}
				if(check1 && check2) {
					if(count-1 == 6) {
						return 1;
					}
				}else {
					if(count == 6) {
						return 1;
					}
				}
			}
		}else if(mode == 2) {
			if(selectedPoint[whitePoint.y/37][whitePoint.x/37] == 2) { // 가로 
				int count = 0;
				boolean check1 = false, check2 = false;
				if(whitePoint.x/37 + 5 < 19) {
					for(int k = 0 ; k < 6; k++) {
						if(selectedPoint[whitePoint.y/37][whitePoint.x/37 + k] == 2) {
							count++;
							check1 = true;
						}else {
							break;
						}
					}
				}
				
				if(whitePoint.x/37 - 5 >= 0) {
					for(int k = 0 ; k < 6; k++) {
						if(selectedPoint[whitePoint.y/37][whitePoint.x/37 - k] == 2) {
							count++;
							check2 = true;
						}else {
							break;
						}
					}
				}
				if(check1 && check2) {
					if(count-1 == 6) {
						return 2;
					}
				}else {
					if(count == 6) {
						return 2;
					}
				}
			}
			
			if(selectedPoint[whitePoint.y/37][whitePoint.x/37] == 2) { // 세로
				int count = 0;
				boolean check1 = false, check2 = false;
				if(whitePoint.y/37 + 5 < 19) {
					for(int k = 0 ; k < 6 ; k++) {
						if(selectedPoint[k+whitePoint.y/37][whitePoint.x/37] == 2) {
							count++;
							check1 = true;
						}else {
							break;
						}
					}
				}
//				System.out.println(count + "1");
				if(blackPoint.y/37 -5 >= 0) {
					for(int k = 0 ; k < 6 ;k++) {
						if(selectedPoint[whitePoint.y/37-k][whitePoint.x/37] == 2) {
							count++;
							check2 = true;
						}else {
							break;
						}
					}
				}
				if(check1 && check2) {
					if(count-1 == 6) {
						return 2;
					}
				}else {
					if(count == 6) {
						return 2;
					}
				}
			}
			if(selectedPoint[whitePoint.y/37][whitePoint.x/37] == 2) { // 오른쪽 대각선 아래
				int count = 0; 
				boolean check1 = false, check2 = false;
				if(whitePoint.y/37 + 5 < 19 && whitePoint.x/37 + 5 < 19) {
					for(int k = 0 ; k < 6 ; k++) {
						if(selectedPoint[whitePoint.y/37+k][whitePoint.x/37+k] == 2) {
							count++;
							check1 = true;
						}else {
							break;
						}
					}
				}
				if(whitePoint.y/37 - 5 >= 0 && whitePoint.x/37 -5 >= 0) {
					for(int k =0 ; k < 6 ; k++) {
						if(selectedPoint[whitePoint.y/37-k][whitePoint.x/37-k] == 2) {
							count++;
							check2 = true;
						}else {
							break;
						}
					}
				}
				if(check1 && check2) {
					if(count-1 == 6) {
						return 2;
					}
				}else {
					if(count == 6) {
						return 2;
					}
				}
			}
			
			
			if(selectedPoint[whitePoint.y/37][whitePoint.x/37] == 2) { // 왼쪽 대각선 아래 + 오른쪽 대각선 위 
				int count = 0;
				boolean check1 = false, check2 = false;
				if(blackPoint.y/37 + 5 < 19 && whitePoint.x/37 -5 >= 0) {
					for(int k =0 ; k < 6 ; k++) {
						if(selectedPoint[whitePoint.y/37+k][whitePoint.x/37-k] == 2) {
							count++;
							check1 = true;
						}else {
							break;
						}
					}
				}
				
				if(whitePoint.y/37 -5 >= 0&& whitePoint.x/37 +5 < 19) {
					for(int k =0 ; k < 6 ; k++) {
						if(selectedPoint[whitePoint.y/37-k][whitePoint.x/37+k] == 2) {
							count++;
							check2 = true;
						}else {
							break;
						}
					}
				}
				if(check1 && check2) {
					if(count-1 == 6) {
						return 2;
					}
				}else {
					if(count == 6) {
						return 2;
					}
				}
			}
		}
		return victory;
		
	}
	
	public static finish getFinish() {
		return f;
	}

	public static Drew getDrew() {
		return d;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
}
