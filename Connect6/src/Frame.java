import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
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
	private int[][] weightGraph = new int[19][19];
//	private int[][] weightBlack = {{-1,-1,-1},{-1,1111,-1},{-1,-1,-1}};
//	private int[][] weightWhite = {{1,1,1},{1,2222,1},{1,1,1}};
	private int arr[] = {-1,0,1};
	private ArrayList<Point> blackSearch = new ArrayList<Point>();
	private Robot AIRobot;
	private Point temp1 = new Point();
	private Point temp2 = new Point();
	private boolean firstCheckFour = false;
	private boolean firstCheckFive = false;
	private boolean attackCheckFour = false;
	private boolean attackCheckMiddle = false;
	private boolean fourDefenseCheck =false;
	private Point tempPoint = new Point();
	private Point DefensePoint1 = new Point();
	private Point DefensePoint2 = new Point();
	private Point attackPoint1 = new Point();
	private Point attackPoint2 = new Point();
	public Frame() {
		for(int i = 0 ; i < weightGraph.length; i++) {
			for(int j = 0; j < weightGraph[i].length; j++) {
				weightGraph[i][j] = 0;
			}
		}
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
						weightGraph[benPoint.y/37][benPoint.x/37] = 3333;
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
						System.out.println(blackPoint.x/37 + " " + blackPoint.y/37);
						
					    
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
//						System.out.println(check);
						
						if(check) {
							try {
								 AIRobot = new Robot();
							} catch (AWTException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							benSpot.setOpaque(false);
							black.setOpaque(true);
							selectedPoint[blackPoint.y/37][blackPoint.x/37] = 1;
							
							boolean middleAttack = middleAttack();
							boolean five = BlackBlockFive(blackPoint);
							boolean attackfour = attackFour();
							boolean fourDen = fourDefense();
							
							System.out.println("five " + five);
							System.out.println("attackfour " + attackfour);
							System.out.println("fourDen " + fourDen);
							if(middleAttack == false) blackSearch.clear();
							if(five == false )blackSearch.clear();
							if(attackfour == false) blackSearch.clear();
							
							
							System.out.println("middleAttack: " + middleAttack);
							
							if(middleAttack) {
								temp1 = attackPoint1;
								temp2 = attackPoint2;
								attackCheckMiddle = true;
							}else if(attackfour){
								temp1 = attackPoint1;
								temp2 = attackPoint2;
//								selectedPoint[temp1.y][temp1.x] = 2;
//								selectedPoint[temp2.y][temp2.x] = 2;
								attackCheckFour = true;
							}else {
//								System.out.println("FOURDEN "+fourDen);
								if(fourDen == true) {
									temp1 = DefensePoint1;
									temp2 = DefensePoint2;
//									System.out.println(blackSearch.size());
									System.out.println(temp1.x +" " + temp1.y + " " + temp2.x + " " + temp2.y);
									fourDefenseCheck = true;
								}else if(five == true) {
									temp1 = DefensePoint1;
									temp2 = DefensePoint2;
//									selectedPoint[temp1.y][temp1.x] = 2;
//									selectedPoint[temp2.y][temp2.x] = 2;
									firstCheckFive = true;
								}else {
									boolean four = BlackBlock(blackPoint);
									System.out.println(four);
									if(four == true) {
										temp1 = blackSearch.get(0);
										temp2 = blackSearch.get(1);
//										selectedPoint[temp1.y][temp1.x] = 2;
//										selectedPoint[temp2.y][temp2.x] = 2;
										firstCheckFour = true;
									}else {
										
									}
								}
							}
							
							
							
//							weightGraph[blackPoint.y/37-1][blackPoint.x/37-1] += -1;
//							weightGraph[blackPoint.y/37-1][blackPoint.x/37] += -1;
//							weightGraph[blackPoint.y/37-1][blackPoint.x/37+1] += -1;
//							weightGraph[blackPoint.y/37][blackPoint.x/37-1] += -1;
//							weightGraph[blackPoint.y/37][blackPoint.x/37] = 1111;
//							weightGraph[blackPoint.y/37][blackPoint.x/37+1] += -1;
//							weightGraph[blackPoint.y/37+1][blackPoint.x/37-1] += -1;
//							weightGraph[blackPoint.y/37+1][blackPoint.x/37] += -1;
//							weightGraph[blackPoint.y/37+1][blackPoint.x/37+1] += -1;
							
							
							for(int i = 0 ;i < 3; i ++) {
								for(int j = 0; j < 3; j++) {
									if(i == 1 && j == 1) {
										weightGraph[blackPoint.y/37][blackPoint.x/37] = 1111;
									}else { // 오류 수정 필요 (바깥라인)
										if((blackPoint.y/37 + arr[i] >= 0 && blackPoint.x/37 +arr[i]>= 0
												&& blackPoint.x/37 + arr[i]< 19 && blackPoint.y/37 + arr[i]< 19) &&
												(blackPoint.y/37 + arr[j] >= 0 && blackPoint.x/37 +arr[j]>= 0
												&& blackPoint.x/37 + arr[j]< 19 && blackPoint.y/37 + arr[j]< 19)) {
											weightGraph[blackPoint.y/37 + arr[i]][blackPoint.x/37+arr[j]] += -1;
										}
									}
								}
							}
//							
							for(int i =0; i < weightGraph.length; i++) {
								for(int j = 0 ; j<weightGraph[i].length; j++) {
									System.out.print(weightGraph[i][j] + "   ");
								}
								System.out.println();
							}
							System.out.println();
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
								
								int[][] first = {{blackPoint.x + 37, blackPoint.y}, {blackPoint.x, blackPoint.y+37},
										{blackPoint.x - 37, blackPoint.y}, {blackPoint.x, blackPoint.y - 37}};
								
								int x = blackPoint.x + 37;
								int y = blackPoint.y ;
								int x2 = blackPoint.x;
								int y2 = blackPoint.y- 37;
								
								for(int i = 0 ;i < pointList.size(); i++) {
									for(int j = 0; j < first.length ; j++) {
										if((pointList.get(i).x == x && pointList.get(i).y == y )|| (x == x2 && y == y2) ) {
											x = first[j][0];
											y = first[j][1];
										}else {
											break;
										}
									}
									
									for(int j = 0 ; j < first.length ;j++) {
										if(pointList.get(i).x == x2 && pointList.get(i).y == y2 || (x == x2 && y == y2))  {
											x2 = first[j][0];
											y2 = first[j][1];
										}else {
											break;
										}
									}
								}
								System.out.println(x + " " + y  +"  " + x2 + " " + y2);
								
								AIRobot.mouseMove( x + 37 * 4, y + 37 * 4);
								AIRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
								AIRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
								
								AIRobot.mouseMove( x2 + 37 * 4, y2 + 37 * 4);
								AIRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
								AIRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
								
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
//								for(int i = 0 ; i< selectedPoint.length; i++) {
//									for(int j = 0; j <selectedPoint[i].length; j++) {
//										System.out.print(selectedPoint[i][j] + " ");
//									}
//									System.out.println();
//								}
								if(firstCheckFour) {
									AIRobot.mouseMove( temp1.x * 37 + 37 * 4, temp1.y * 37 + 37 * 4 );
									AIRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AIRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									
									AIRobot.mouseMove( temp2.x * 37 + 37 * 4, temp2.y * 37 +37 * 4);
									AIRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AIRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									
									blackSearch.clear();
									firstCheckFour = false;
									firstCheckFive = false;
								}else if(firstCheckFive) {
									AIRobot.mouseMove( temp1.x * 37 + 37 * 4, temp1.y * 37 + 37 * 4 );
									AIRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AIRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									
									AIRobot.mouseMove( temp2.x * 37 + 37 * 4, temp2.y * 37 +37 * 4);
									AIRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AIRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									
									blackSearch.clear();
									firstCheckFour = false;
									firstCheckFive = false;
								
								}else if(attackCheckFour) {
									AIRobot.mouseMove( temp1.x * 37 + 37 * 4, temp1.y * 37 + 37 * 4 );
									AIRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AIRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									
									AIRobot.mouseMove( temp2.x * 37 + 37 * 4, temp2.y * 37 +37 * 4);
									AIRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AIRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									blackSearch.clear();
									attackCheckFour = false;

								}else if(attackCheckMiddle){
									AIRobot.mouseMove( temp1.x * 37 + 37 * 4, temp1.y * 37 + 37 * 4 );
									AIRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AIRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									
									AIRobot.mouseMove( temp2.x * 37 + 37 * 4, temp2.y * 37 +37 * 4);
									AIRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AIRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									blackSearch.clear();
									attackCheckFour = false;
								}else if(fourDen){
									AIRobot.mouseMove( temp1.x * 37 + 37 * 4, temp1.y * 37 + 37 * 4 );
									AIRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AIRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									
									AIRobot.mouseMove( temp2.x * 37 + 37 * 4, temp2.y * 37 +37 * 4);
									AIRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AIRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									blackSearch.clear();
									fourDefenseCheck = false;
								}else {
									
									int min = 999999;
									Point tempT1 = new Point();
									Point tempT2 = new Point();
									int a1 = 0;
									int b1 = 0;
									for(int i = 0 ; i < blackList.size(); i++) {
										for(int z = blackList.get(i).y/37 -2;  z < blackList.get(i).y/37 + 3 ; z++) {
											for(int zi = blackList.get(i).x/37 -2; zi < blackList.get(i).x/37 + 3 ; zi++ ) {
												if(blackList.get(i).y/37 - 2 >= 0 && blackList.get(i).y/37 +3 < 19 && blackList.get(i).x/37 >= 0 
														&& blackList.get(i).x/37 < 19 && min > weightGraph[z][zi]) {
													tempT1.x = zi;
													a1 = zi;
													b1 = z;
													tempT1.y = z;
													min = weightGraph[z][zi];
												}
											}
										}
									}
									System.out.println(min);
									System.out.println(tempT1.x + " " + tempT1.y + "TEMP1");
									AIRobot.mouseMove( a1 * 37 + 37 * 4 , b1 * 37 + 37 * 4 );
									AIRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AIRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									
//									for(int i = 0 ;i < 3; i ++) {
//										for(int j = 0; j < 3; j++) {
//											if(i == 1 && j == 1) {
//												weightGraph[tempT1.y/37][tempT1.x/37] = 2222;
//											}else {
//												if(tempT1.y/37 + arr[i] >= 0 && tempT1.x +arr[i]>= 0 &&
//														tempT1.x + arr[i]< 19 && tempT1.y +arr[i]< 19 &&
//														tempT1.y/37 + arr[j] >= 0 && tempT1.x +arr[j]>= 0 &&
//																tempT1.x + arr[j]< 19 && tempT1.y +arr[j]< 19) {
//													weightGraph[tempT1.y/37 + arr[i]][tempT1.x/37+arr[j]] += 1;
//												}
//											}
//										}
//									}
//										AIRobot.delay(3000);
									int max = 0;
									int a2 = 0;
									int b2 = 0;
									for(int i = 0 ; i < blackList.size(); i++) {
										for(int z = blackList.get(i).y/37 -2;  z < blackList.get(i).y/37 +3 ; z++) {
											for(int zi = blackList.get(i).x/37 -2; zi < blackList.get(i).x/37 + 3 ; zi++ ) {
												if(blackList.get(i).x/37 -2 >= 0 && blackList.get(i).x/37 + 3 < 19 && blackList.get(i).y/37 -2 >= 0 
														&& blackList.get(i).y/37 + 3 < 19 && (max < weightGraph[z][zi] && weightGraph[z][zi] < 1000)) {
													tempT2.x = zi;
													tempT2.y = z;
													a2 = zi;
													b2 = z;
													
													max = weightGraph[z][zi];
													
												}
											}
										}
									}
										
									System.out.println(max);
									System.out.println(tempT2.x + " " + tempT2.y + "TEMP2");
									AIRobot.mouseMove( a2 * 37 + 37 * 4 , b2 * 37 + 37 * 4   );
//									AIRobot.delay(3000);
									AIRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AIRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									
								}
								
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
							
//							weightGraph[whitePoint.y/37-1][whitePoint.x/37-1] += 1;
//							weightGraph[whitePoint.y/37-1][whitePoint.x/37] += 1;
//							weightGraph[whitePoint.y/37-1][whitePoint.x/37+1] += 1;
//							weightGraph[whitePoint.y/37][whitePoint.x/37-1] += 1;
//							weightGraph[whitePoint.y/37][whitePoint.x/37] = 2222;
//							weightGraph[whitePoint.y/37][whitePoint.x/37+1] += 1;
//							weightGraph[whitePoint.y/37+1][whitePoint.x/37-1] += 1;
//							weightGraph[whitePoint.y/37+1][whitePoint.x/37] += 1;
//							weightGraph[whitePoint.y/37+1][whitePoint.x/37+1] += 1;
							
							for(int i = 0 ;i < 3; i ++) {
								for(int j = 0; j < 3; j++) {
									if(i == 1 && j == 1) {
										weightGraph[whitePoint.y/37][whitePoint.x/37] = 2222;
									}else {
										if((whitePoint.y/37 + arr[i] >= 0 && whitePoint.x/37 +arr[i]>= 0 &&
												whitePoint.x/37 + arr[i]< 19 && whitePoint.y/37 +arr[i]< 19) &&
												(whitePoint.y/37 + arr[j] >= 0 && whitePoint.x/37 +arr[j]>= 0 &&
												whitePoint.x/37 + arr[j]< 19 && whitePoint.y/37 +arr[j]< 19)) {
											weightGraph[whitePoint.y/37 + arr[i]][whitePoint.x/37+arr[j]] += 1;
										}
									}
								}
							}
							
							for(int i = 0 ;i < weightGraph.length; i++) {
								for(int j = 0; j  < weightGraph[i].length; j++) {
									System.out.print(weightGraph[i][j] + "   ");
								}
								System.out.println();
							}
							System.out.println();
							
//							for(int i =0; i < weightGraph.length; i++) {
//								for(int j = 0 ; j<weightGraph[i].length; j++) {
//									System.out.print(weightGraph[i][j] + "   ");
//								}
//								System.out.println();
//							}
//							System.out.println();
							
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
				if(whitePoint.y/37 -5 >= 0) {
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
	public Point searchPoint() {
		Point search = new Point();
		
		return search; 
	}
	public boolean BlackBlock(Point t) { // 흰색기준 검정 찾기 
		// ArrayList
		
		if(selectedPoint[t.y/37][t.x/37] == 1) {
			// 가로
			int count = 0;
			int idx1 = 0 , idx2 = 0;
			if(blackPoint.x/37 + 3 < 19) {
				for(int k = 0 ; k < 4; k++) {
					if(selectedPoint[blackPoint.y/37][blackPoint.x/37 + k] == 1) { // 오른쪽 
						count++;
					}else {
						if(selectedPoint[blackPoint.y/37][blackPoint.x/37+k] == 0) {
							idx1 = k;
						}
						break;
				}
			}
			
			if(blackPoint.x/37 - 3 >= 0) {
				for(int k = 0 ; k < 4; k++) {
					if(selectedPoint[blackPoint.y/37][blackPoint.x/37 - k] == 1) { // 왼쪽 
						count++;
					}else {
						if(selectedPoint[blackPoint.y/37][blackPoint.x/37-k] == 0) {
							idx2 = k;
						}
						break;
					}
				}
			}
			
			if(count -1 == 4) {
				Point temp = new Point();
				temp.x = blackPoint.x/37+idx1;
				temp.y = blackPoint.y/37;
				Point temp2 = new Point();
				temp2.x = blackPoint.x/37 - idx2;
				temp2.y = blackPoint.y/37;
				
//				if(temp.x-1 == temp2.x && temp.y == temp2.y) {
					
					if(selectedPoint[temp2.y][temp2.x-1] == 1) {
						System.out.println("@@@@@@@@@@@@@@@@@@@@@");
						System.out.println("HELLO");
						temp2.x = temp.x-5;
						temp2.y = temp.y;
					}else if(selectedPoint[temp2.y][temp2.x+1] == 1) {
						System.out.println("@@@@@@@@@@@@@@@@@@@@!!!!!!!!!!");
						System.out.println("HELLO!");
						temp.x = temp2.x+5;
						temp.y = temp2.y;
					}
//				}
				blackSearch.add(temp); 
				blackSearch.add(temp2);
//				System.out.println("가로 4개 완성!");
//				System.out.println(temp.x + " " + temp.y + " " + selectedPoint[temp.y][temp.x]);
//				System.out.println(temp2.x + " " + temp2.y + " " + selectedPoint[temp2.y][temp2.x]);
				return true;
			}
			count = 0;
			idx1 = 0 ;
			idx2 = 0;
			if(blackPoint.y/37 + 3 < 19) { // 아래로 
				for(int k = 0 ; k < 4 ; k++) {
					if(selectedPoint[blackPoint.y/37 + k][blackPoint.x/37] == 1) {
//						System.out.println(blackPoint.y/37 + k + " " + blackPoint.x/37);
						count++;
					}else {
						idx1 = k;
						break;
					}
				}
			}
//			System.out.println(count + "1");
			if(blackPoint.y/37 -3 >= 0) { // 위로 
				for(int k = 0 ; k < 4 ;k++) {
					if(selectedPoint[blackPoint.y/37-k][blackPoint.x/37] == 1) {
//						System.out.println(blackPoint.y/37 - k + " " + blackPoint.x/37);
						count++;
					}else {
						idx2 = k;
						break;
					}
				}
			}
			if(count -1 == 4) {
				Point temp = new Point();
				temp.x = blackPoint.x/37 ;
				temp.y = blackPoint.y/37 + idx1;
				Point temp2 = new Point();
				temp2.x = blackPoint.x/37 ;
				temp2.y = blackPoint.y/37 - idx2;
				blackSearch.add(temp); // 수정 필요
				blackSearch.add(temp2);
//				System.out.println("세로 4개 완성!");
//				System.out.println(temp.x + " " + temp.y + " " + selectedPoint[temp.y][temp.x]);
//				System.out.println(temp2.x + " " + temp2.y + " " + selectedPoint[temp2.y][temp2.x]);
				return true;
			}
			count = 0;
			idx1 = 0;
			idx2 = 0;
			if(blackPoint.y/37 + 3 < 19 && blackPoint.x/37 + 3 < 19) { // 대각선 왼오 
				for(int k = 0 ; k < 4; k++) {
					if(selectedPoint[blackPoint.y/37+k][blackPoint.x/37+k] == 1) {
						count++;
					}else {
						idx1 = k;
						break;
						
					}
				}
				
			}
			if(blackPoint.y/37 - 3 >= 0 && blackPoint.x/37 -3 >= 0) {
				for(int k =0 ; k < 4 ; k++) {
					if(selectedPoint[blackPoint.y/37-k][blackPoint.x/37-k] == 1) {
						System.out.println("COME");
						count++;
					}else {
						idx2 = k;
						break;
					}
				}
			}
			System.out.println(count +"대각선1");
			if(count-1 == 4) {
				Point temp = new Point();
				temp.x = blackPoint.x/37 + idx1;
				temp.y = blackPoint.y/37 + idx1;
				Point temp2 = new Point();
				temp2.x = blackPoint.x/37 - idx2;
				temp2.y = blackPoint.y/37 - idx2;
				
				if(temp2.x-4 >= 0 && temp2.y-4 >= 0 && temp2.x < 19 && temp2.y < 19 && 
						temp.x -1 == temp2.x && temp.y -1 == temp2.y) {
					temp2.x -= 4;
					temp2.y -= 4;
				}
				blackSearch.add(temp); // 수정 필요
				blackSearch.add(temp2);
				System.out.println("대각선 4개 완성!");
				
			
				return true;
			}
			count = 0;
			idx1 = 0;
			idx2 = 0;
			if(blackPoint.y/37 + 3 < 19 && blackPoint.x/37 -3 >= 0) {
				for(int k =0 ; k < 4 ; k++) {
					if(selectedPoint[blackPoint.y/37+k][blackPoint.x/37-k] == 1) {
						count++;
					}else {
						idx1 = k;
						break;
					}
				}
			}
			
			if(blackPoint.y/37 -3 >= 0&& blackPoint.x/37 +3 < 19) {
				for(int k =0 ; k < 4 ; k++) {
					if(selectedPoint[blackPoint.y/37-k][blackPoint.x/37+k] == 1) {
						count++;
					}else {
						idx2 = k;
						break;
					}
				}
			}
			System.out.println(count +  "대각선2" );
			if(count -1 == 4) {
				Point temp = new Point();
				temp.x = blackPoint.x/37 - idx1 ;
				temp.y = blackPoint.y/37 + idx1;
				Point temp2 = new Point();
				temp2.x = blackPoint.x/37 + idx2;
				temp2.y = blackPoint.y/37 - idx2;
				if(temp2.x +4 < 19 && temp2.y >= 0 && temp.x +1 == temp2.x && temp.y-1 == temp2.y) {
					temp2.x += 4;
					temp2.y -= 4;
				}
				blackSearch.add(temp); // 수정 필요
				blackSearch.add(temp2);
//				System.out.println("대각선 4개 완성!");
				System.out.println(temp.x + " " + temp.y + " " + selectedPoint[temp.y][temp.x]);
				System.out.println(temp2.x + " " + temp2.y + " " + selectedPoint[temp2.y][temp2.x]);
				return true;
			}
			count = 0;
			idx1 = 0;
			idx2 = 0;
			}
		}
		return false;
	}
	
	
	public boolean BlackBlockFive(Point t) { // 흰색기준 검정 찾기 
		// ArrayList
		
		if(selectedPoint[t.y/37][t.x/37] == 1) {
			// 가로
			int count = 0;
			int idx1 = 0 , idx2 = 0;
			boolean check1 = false, check2 = false;
			if(blackPoint.x/37 + 4 < 19 ) {
				for(int k = 0 ; k < 5; k++) {
					if(selectedPoint[blackPoint.y/37][blackPoint.x/37 + k] == 1) {
						count++;
						check1 = true;
					}else {
						if(selectedPoint[blackPoint.y/37][blackPoint.x/37+k] == 0) {
							idx1 = k;
						}
						break;
					}
			}
			
			if(blackPoint.x/37 - 4 >= 0) {
				for(int k = 0 ; k < 5; k++) {
					if(selectedPoint[blackPoint.y/37][blackPoint.x/37 - k] == 1) {
						count++;
						check2 = true;
					}else {
						if(selectedPoint[blackPoint.y/37][blackPoint.x/37-k] == 0) {
							idx2 = k;
						}
						break;
					}
				}
			}
			System.out.println(count + " 5줄 가로 갯수");
			if(count -1 == 5) {
				System.out.println("가로 5개 완성" );
				Point temp = new Point();
				temp.x = blackPoint.x/37+idx1;
				temp.y = blackPoint.y/37;
				Point temp2 = new Point();
				temp2.x = blackPoint.x/37 - idx2;
				temp2.y = blackPoint.y/37;
				DefensePoint1 = temp1;
				DefensePoint2 = temp2;
				blackSearch.add(temp2);
//				System.out.println("가로 4개 완성!");
//				System.out.println(temp.x + " " + temp.y + " " + selectedPoint[temp.y][temp.x]);
//				System.out.println(temp2.x + " " + temp2.y + " " + selectedPoint[temp2.y][temp2.x]);
				return true;
			}
			count = 0;
			idx1 = 0 ;
			idx2 = 0;
			if(blackPoint.y/37 + 4 < 19) { // 아래로 
				for(int k = 0 ; k < 5 ; k++) {
					if(selectedPoint[blackPoint.y/37 + k][blackPoint.x/37] == 1) {
//						System.out.println(blackPoint.y/37 + k + " " + blackPoint.x/37);
						count++;
						check1 = true;
					}else {
						idx1 = k;
						break;
					}
				}
			}
//			System.out.println(count + "1");
			if(blackPoint.y/37 -4 >= 0) { // 위로 
				for(int k = 0 ; k < 5 ;k++) {
					if(selectedPoint[blackPoint.y/37-k][blackPoint.x/37] == 1) {
//						System.out.println(blackPoint.y/37 - k + " " + blackPoint.x/37);
						count++;
						check2 = true;
					}else {
						idx2 = k;
						break;
					}
				}
			}
			System.out.println(count + " 5줄 세로 갯수");
			if(count -1 == 5) {
				System.out.println("5개 세로 성공");
				Point temp = new Point();
				temp.x = blackPoint.x/37 ;
				temp.y = blackPoint.y/37 + idx1;
				Point temp2 = new Point();
				temp2.x = blackPoint.x/37 ;
				temp2.y = blackPoint.y/37 - idx2;
				DefensePoint1 = temp1;
				DefensePoint2 = temp2;
				
//				System.out.println(temp.x + " " + temp.y + " " + selectedPoint[temp.y][temp.x]);
//				System.out.println(temp2.x + " " + temp2.y + " " + selectedPoint[temp2.y][temp2.x]);
				return true;
			}
			count = 0;
			idx1 = 0;
			idx2 = 0;
			if(blackPoint.y/37 + 4 < 19 && blackPoint.x/37 + 4 < 19) { // 대각선 왼오 
				for(int k = 0 ; k < 5; k++) {
					if(selectedPoint[blackPoint.y/37+k][blackPoint.x/37+k] == 1) {
						count++;
						check1 = true;
					}else {
						idx1 = k;
						break;
						
					}
				}
				
			}
			if(blackPoint.y/37 - 4 >= 0 && blackPoint.x/37 -4 >= 0) {
				for(int k =0 ; k < 4 ; k++) {
					if(selectedPoint[blackPoint.y/37-k][blackPoint.x/37-k] == 1) {
						System.out.println("COME");
						count++;
						check2 = true;
					}else {
						idx2 = k;
						break;
					}
				}
			}
//			System.out.println(count + "5개 대각선1 갯수");
			
			if(count -1 == 5) {
				System.out.println("5개 대각선 성공");
				Point temp = new Point();
				temp.x = blackPoint.x/37 + idx1;
				temp.y = blackPoint.y/37 + idx1;
				Point temp2 = new Point();
				temp2.x = blackPoint.x/37 - idx2;
				temp2.y = blackPoint.y/37 - idx2;
				if(temp2.x-5 >= 0 && temp2.y-5 >= 0 && temp2.x < 19 && temp2.y < 19 && 
						temp.x -1 == temp2.x && temp.y -1 == temp2.y) {
					temp2.x -= 5;
					temp2.y -= 5;
				}
				DefensePoint1 = temp1;
				DefensePoint2 = temp2;
				
//				System.out.println(temp.x + " " + temp.y + " " + selectedPoint[temp.y][temp.x]);
//				System.out.println(temp2.x + " " + temp2.y + " " + selectedPoint[temp2.y][temp2.x]);
				return true;
			}
			count = 0;
			idx1 = 0;
			idx2 = 0;
			if(blackPoint.y/37 + 4 < 19 && blackPoint.x/37 -4>= 0) {
				for(int k =0 ; k < 5 ; k++) {
					if(selectedPoint[blackPoint.y/37+k][blackPoint.x/37-k] == 1) {
						count++;
						check1 = true;
					}else {
						idx1 = k;
						break;
					}
				}
			}
			
			if(blackPoint.y/37 -4 >= 0&& blackPoint.x/37 +4 < 19) {
				for(int k =0 ; k < 5 ; k++) {
					if(selectedPoint[blackPoint.y/37-k][blackPoint.x/37+k] == 1) {
						count++;
						check2 = true;
					}else {
						idx2 = k;
						break;
					}
				}
			}
			System.out.println(count +  "5개 대각선2 갯수" );
			if(count -1 == 5) {
				System.out.println("5개 대각선2 성공 !");
				Point temp = new Point();
				temp.x = blackPoint.x/37 - idx1 ;
				temp.y = blackPoint.y/37 + idx1;
				Point temp2 = new Point();
				temp2.x = blackPoint.x/37 + idx2;
				temp2.y = blackPoint.y/37 - idx2;
				if(temp2.x +5 < 19 && temp2.y -5 >= 0 && temp.x +1 == temp2.x && temp.y-1 == temp2.y) {
					temp2.x += 1;
					temp2.y -= 1;
				}
				DefensePoint1 = temp1;
				DefensePoint2 = temp2;
				
//				System.out.println("대각선 4개 완성!");
//				System.out.println(temp.x + " " + temp.y + " " + selectedPoint[temp.y][temp.x]);
//				System.out.println(temp2.x + " " + temp2.y + " " + selectedPoint[temp2.y][temp2.x]);
				return true;
			}
			count = 0;
			idx1 = 0;
			idx2 = 0;
			}
		}
		return false;
	}
	
	public boolean attackFour() { // 4 개  
		
		for(int i = 0 ; i < whiteList.size(); i++) {
			Point tmp = whiteList.get(i);
			int count = 0 ;
			int idx1 = 0, idx2 = 0;
			
			if(tmp.x/37 + 3 < 19) {
				for(int k = 0 ; k < 4; k++) {
					if(selectedPoint[tmp.y/37][tmp.x/37 + k] == 2) { // 으른쪽 
						count++;
					}else {
						if(selectedPoint[tmp.y/37][tmp.x/37+k] == 0) {
							idx1 = k;
						}
						break;
					}
			}
			
			if(blackPoint.x/37 - 3 >= 0) {
				for(int k = 0 ; k < 4; k++) {
					if(selectedPoint[tmp.y/37][tmp.x/37 - k] == 2) { // 왼쪽 
						count++;
					}else {
						if(selectedPoint[tmp.y/37][tmp.x/37-k] == 0) {
							idx2 = k;
						}
						break;
					}
				}
			}
			
			if(count -1 == 4) {
				Point temp = new Point();
				temp.x = tmp.x/37+idx1;
				temp.y = tmp.y/37;
				Point temp2 = new Point();
				temp2.x = tmp.x/37 - idx2;
				temp2.y = tmp.y/37;
				
				if(selectedPoint[temp.y][temp.x] == 1) {
					if(selectedPoint[temp2.y][temp2.x + 1] == 0) {
						temp.x = temp2.x + 1;
						temp.y = temp2.y ;
					}else { // 가중치에 따라 행동 
						int min = 999999;
						for(int z = tmp.y/37 -2;  z < tmp.y/37 + 3 ; z++) {
							for(int zi = tmp.x/37 -2; zi < tmp.x/37 + 3 ; zi++ ) {
								if(min > weightGraph[z][zi]) {
									temp.x = zi;
									temp.y = z;
								}
							}
						}
					}
				}else if(selectedPoint[temp2.y][temp2.x] == 1) {
					if(selectedPoint[temp.y][temp.x -1] == 0) {
						temp2.x = temp.x -1;
						temp2.y = temp.y;
					}else {
						int min = 999999;
						for(int z = tmp.y/37 -2;  z < tmp.y/37 + 3 ; z++) {
							for(int zi = tmp.x/37 -2; zi < tmp.x/37 + 3 ; zi++ ) {
								if(min > weightGraph[z][zi]) {
									temp2.x = zi;
									temp2.y = z;
									min = weightGraph[z][zi];
								}
							}
						}
					}
				}
				attackPoint1 = temp1;
				attackPoint2 = temp2;
//				System.out.println("가로 4개 완성!");
//				System.out.println(temp.x + " " + temp.y + " " + selectedPoint[temp.y][temp.x]);
//				System.out.println(temp2.x + " " + temp2.y + " " + selectedPoint[temp2.y][temp2.x]);
				return true;
			}
			count = 0;
			idx1 = 0 ;
			idx2 = 0;
			if(blackPoint.y/37 + 3 < 19) { // 아래로 
				for(int k = 0 ; k < 4 ; k++) {
					if(selectedPoint[tmp.y/37 + k][tmp.x/37] == 2) {
//						System.out.println(blackPoint.y/37 + k + " " + blackPoint.x/37);
						count++;
					}else {
						idx1 = k;
						break;
					}
				}
			}
//			System.out.println(count + "1");
			if(blackPoint.y/37 -3 >= 0) { // 위로 
				for(int k = 0 ; k < 4 ;k++) {
					if(selectedPoint[tmp.y/37-k][tmp.x/37] == 2) {
//						System.out.println(blackPoint.y/37 - k + " " + blackPoint.x/37);
						count++;
					}else {
						idx2 = k;
						break;
					}
				}
			}
			if(count -1 == 4) {
				Point temp = new Point();
				temp.x = tmp.x/37 ;
				temp.y = tmp.y/37 + idx1;
				Point temp2 = new Point();
				temp2.x = tmp.x/37 ;
				temp2.y = tmp.y/37 - idx2;
				
				if(selectedPoint[temp.y][temp.x] == 1) { //아래에 검은돌 존재할 경우 
					if(selectedPoint[temp2.y -1][temp2.x] == 0) {
						temp.x = temp2.x ;
						temp.y = temp2.y -1 ;
					}else { // 가중치에 따라 행동 
						int min = 999999;
						for(int z = tmp.y/37 -2;  z < tmp.y/37 + 3 ; z++) {
							for(int zi = tmp.x/37 -2; zi < tmp.x/37 + 3 ; zi++ ) {
								if(min > weightGraph[z][zi]) {
									temp.x = zi;
									temp.y = z;
								}
							}
						}
					}
				}else if(selectedPoint[temp2.y][temp2.x] == 1) { // 위에 검은돌 존재할 경우 
					if(selectedPoint[temp.y + 1][temp.x] == 0) {
						temp2.x = temp.x ;
						temp2.y = temp.y + 1;
					}else {
						int min = 999999;
						for(int z = tmp.y/37 -2;  z < tmp.y/37 + 3 ; z++) {
							for(int zi = tmp.x/37 -2; zi < tmp.x/37 + 3 ; zi++ ) {
								if(min > weightGraph[z][zi]) {
									temp2.x = zi;
									temp2.y = z;
								}
							}
						}
					}
				}
				
				
				attackPoint1 = temp1;
				attackPoint2 = temp2;
//				System.out.println("세로 4개 완성!");
//				System.out.println(temp.x + " " + temp.y + " " + selectedPoint[temp.y][temp.x]);
//				System.out.println(temp2.x + " " + temp2.y + " " + selectedPoint[temp2.y][temp2.x]);
				return true;
			}
			count = 0;
			idx1 = 0;
			idx2 = 0;
			if(tmp.y/37 + 3 < 19 && tmp.x/37 + 3 < 19) { // 대각선 왼오 
				for(int k = 0 ; k < 4; k++) {
					if(selectedPoint[tmp.y/37+k][tmp.x/37+k] == 2) {
						count++;
					}else {
						idx1 = k;
						break;
						
					}
				}
				
			}
			if(tmp.y/37 - 3 >= 0 && tmp.x/37 -3 >= 0) {
				for(int k =0 ; k < 4 ; k++) {
					if(selectedPoint[tmp.y/37-k][tmp.x/37-k] == 2) {
						count++;
					}else {
						idx2 = k;
						break;
					}
				}
			}
			if(count -1 == 4) {
				Point temp = new Point();
				temp.x = tmp.x/37 + idx1;
				temp.y = tmp.y/37 + idx1;
				Point temp2 = new Point();
				temp2.x = tmp.x/37 - idx2;
				temp2.y = tmp.y/37 - idx2;
				
				if(selectedPoint[temp.y][temp.x] == 1) {
					if(selectedPoint[temp2.y -1][temp2.x -1] == 0) {
						temp.x = temp2.x -1;
						temp.y = temp2.y -1 ;
					}else { // 가중치에 따라 행동 
						int min = 999999;
						for(int z = tmp.y/37 -2;  z < tmp.y/37 + 3 ; z++) {
							for(int zi = tmp.x/37 -2; zi < tmp.x/37 + 3 ; zi++ ) {
								if(min > weightGraph[z][zi]) {
									temp.x = zi;
									temp.y = z;
								}
							}
						}
					}
				}else if(selectedPoint[temp2.y][temp2.x] == 1) {
					if(selectedPoint[temp.y + 1][temp.x +1] == 0) {
						temp2.x = temp.x +1;
						temp2.y = temp.y +1;
					}else {
						int min = 999999;
						for(int z = tmp.y/37 -2;  z < tmp.y/37 + 3 ; z++) {
							for(int zi = tmp.x/37 -2; zi < tmp.x/37 + 3 ; zi++ ) {
								if(min > weightGraph[z][zi]) {
									temp2.x = zi;
									temp2.y = z;
								}
							}
						}
					}
				}
				
				attackPoint1 = temp;
				attackPoint2 = temp2;
//				System.out.println("대각선 4개 완성!");
//				System.out.println(temp.x + " " + temp.y + " " + selectedPoint[temp.y][temp.x]);
//				System.out.println(temp2.x + " " + temp2.y + " " + selectedPoint[temp2.y][temp2.x]);
				return true;
			}
			count = 0;
			idx1 = 0;
			idx2 = 0;
			if(tmp.y/37 + 3 < 19 && tmp.x/37 -3 >= 0) {
				for(int k =0 ; k < 4 ; k++) {
					if(selectedPoint[tmp.y/37+k][tmp.x/37-k] == 2) {
						count++;
					}else {
						idx1 = k;
						break;
					}
				}
			}
			
			if(tmp.y/37 -3 >= 0&& tmp.x/37 +3 < 19) {
				for(int k =0 ; k < 4 ; k++) {
					if(selectedPoint[tmp.y/37-k][tmp.x/37+k] == 2) {
						count++;
					}else {
						idx2 = k;
						break;
					}
				}
			}
			System.out.println(count +  "대각선2" );
			if(count -1 == 4) {
				Point temp = new Point();
				temp.x = tmp.x/37 - idx1 ;
				temp.y = tmp.y/37 + idx1;
				Point temp2 = new Point();
				temp2.x = tmp.x/37 + idx2;
				temp2.y = tmp.y/37 - idx2;
				
				if(selectedPoint[temp.y][temp.x] == 1) {
					if(selectedPoint[temp2.y+1][temp2.x -1] == 0) {
						temp.x = temp2.x + 1;
						temp.y = temp2.y - 1;
					}else { // 가중치에 따라 행동 
						int min = 999999;
						for(int z = tmp.y/37 -2;  z < tmp.y/37 + 3 ; z++) {
							for(int zi = tmp.x/37 -2; zi < tmp.x/37 + 3 ; zi++ ) {
								if(min > weightGraph[z][zi]) {
									temp.x = zi;
									temp.y = z;
								}
							}
						}
					}
				}else if(selectedPoint[temp2.y][temp2.x] == 1) {
					if(selectedPoint[temp.y-1][temp.x +1] == 0) {
						temp2.x = temp.x -1;
						temp2.y = temp.y + 1;
					}else {
						int min = 999999;
						for(int z = tmp.y/37 -2;  z < tmp.y/37 + 3 ; z++) {
							for(int zi = tmp.x/37 -2; zi < tmp.x/37 + 3 ; zi++ ) {
								if(min > weightGraph[z][zi]) {
									temp2.x = zi;
									temp2.y = z;
								}
							}
						}
					}
				}
				attackPoint1 = temp;
				attackPoint2 = temp2;
//				System.out.println("대각선 4개 완성!");
//				System.out.println(temp.x + " " + temp.y + " " + selectedPoint[temp.y][temp.x]);
//				System.out.println(temp2.x + " " + temp2.y + " " + selectedPoint[temp2.y][temp2.x]);
				return true;
			}
			count = 0;
			idx1 = 0;
			idx2 = 0;
			}
		}
		return false;
	}
	
	public boolean middleAttack() {
		boolean check = false;
		Point t1 = new Point();
		Point t2 = new Point();
		
		attack:
		for(int i = 0 ; i < selectedPoint.length; i++) { // 가로 
			ArrayList<Integer> zeroIndex = new ArrayList<Integer>();
			
			for(int j = 0; j < selectedPoint[i].length; j++) {
				int count =0;
				int count2 = 0;
				if(selectedPoint[i][j] == 2 ) {
					for(int z = j; j+5 < 19 && z < j + 6 ; z++) {
						if(selectedPoint[i][z] == 2) {
							count++;
						}else if(selectedPoint[i][z] == 0) {
							count2++;
							zeroIndex.add(z);
						}
					}
					
					if(count == 4 && count2 == 2) {
						t1.x = zeroIndex.get(0);
						t1.y = i;
						attackPoint1 = t1;
						t2.x = zeroIndex.get(1);
						t2.y = i;
						attackPoint2 = t2;
						check =true;
						return check;
					}
				}
			}
		}
		
		attack2:
		for(int i = 0 ; i < selectedPoint.length; i++) {
			ArrayList<Integer> zeroIndex = new ArrayList<Integer>();
			for(int j = 0;  j< selectedPoint[i].length; j++) {
				int count = 0;
				int count2 = 0;
				if(selectedPoint[j][i] == 2) {
					for(int z = j; j+5 < 19 && z < j + 6; z++) {
						if(selectedPoint[z][i] == 2) {
							count++;
						}else if(selectedPoint[z][i] == 0) {
							count2++;
							zeroIndex.add(z);
						}
					}
					
					if(count == 4 && count2 == 2) {
						t1.x = i;
						t1.y = zeroIndex.get(0);
						attackPoint1 = t1;
						t2.x = i;
						t2.y = zeroIndex.get(1);
						attackPoint2 = t2;
						check = true;
						return check;
					}
				}
			}
		}
		
		attack3:
		for(int i = 0; i <selectedPoint.length; i++) {
			ArrayList<Integer> zeroIndex = new ArrayList<Integer>();
			for(int j = 0 ;j < selectedPoint[i].length; j++) {
				int count =0; 
				int count2 = 0;
				
				if(selectedPoint[i][j] == 2) {
					
					for(int z = 0; i+z < 19 && j+z < 19 && z < 6; z++) {
						if( selectedPoint[i+z][j+z] == 2) {
							count++;
						}else if(selectedPoint[i+z][j+z] == 0) {
							count2++;
							zeroIndex.add(z);
						}
					}
					if(count == 4 && count2 == 2) {
						
						t1.x = j + zeroIndex.get(0);
						t1.y = i + zeroIndex.get(0);
						t2.x = j + zeroIndex.get(1);
						t2.y = i + zeroIndex.get(1);
						attackPoint1 = t1;
						attackPoint2 = t2;
						check = true;
						return check;
					}
				}
			}
		}
		
		attack4:
		for(int i = 0; i < selectedPoint.length; i++) {
			ArrayList<Integer> zeroIndex = new ArrayList<Integer>();
			for(int j = 0 ; j < selectedPoint[i].length; j++) {
				int count =0 ;
				int count2 = 0;
				if(selectedPoint[i][j] == 2) {
					for(int z = 0 ; i+z < 19 && j-z >= 0 && z < 6 ; z++) {
						if(selectedPoint[i+z][j-z] == 2) {
							count++;
						}else if(selectedPoint[i+z][j-z] == 0) {
							count2++;
							zeroIndex.add(z);
						}
					}
					System.out.println(count + " " + count2 + "HELLO");
					if(count == 4 && count2 ==2 ) {
						t1.x = j -zeroIndex.get(0);
						t1.y = i + zeroIndex.get(0);
						
						t2.x = j - zeroIndex.get(1);
						t2.y = i + zeroIndex.get(1);
						attackPoint1 = t1;
						attackPoint2 = t2;
						check = true;
						return check;
					}
				}
			}
		}
		return check;
		
		
	}
	
	public boolean fourDefense() {
	      boolean check = false;
	      Point t1 = new Point();
	      Point t2 = new Point();
	      for(int di = 0; di < selectedPoint.length; di++) {
	         for(int dj = 0; dj < selectedPoint[di].length; dj++) {
	            System.out.print(selectedPoint[di][dj]);
	         }
	         System.out.println();
	      }
	      for(int i = 0; i < selectedPoint.length; i++) {
				ArrayList<Integer> zeroIndex = new ArrayList<Integer>();
				for(int j = 0; j < selectedPoint[i].length; j++) {
					int count = 0; 
					int idx1 = 0 , idx2 = 0;
					if(selectedPoint[i][j] == 1) {
						if(selectedPoint[i][j-1] == 0) {
							idx1 = j-1;
							for(int z =j ; z < 19 &&  z < j +5 ; z++) {
								if(selectedPoint[i][z] == 1) {
									count++;
									if(count == 5) {
										idx2 = z+1;
										zeroIndex.add(idx2);
									}
										
								}else {
									if(selectedPoint[i][z] == 2 || selectedPoint[i][z] == 3) {
										break;
									}else if(selectedPoint[i][z] == 0) {
										idx2 = z;
										zeroIndex.add(idx2);
									}
								}
							}
							
							System.out.println("CASE 1 " + count);
							if(idx1 != 0 && idx2 != 0 && (count == 4 )) {
								System.out.println("CASE 1");
								t1.x = idx1;
								t1.y = i;
								System.out.println(t1.x + " " + t1.y);
								DefensePoint1 = t1;
//								blackSearch.add(t1);
								t2.x = zeroIndex.get(0);
								t2.y = i;
								DefensePoint2 = t2;
//								blackSearch.add(t2);
								check = true;
								return check;
							}else if(idx1 != 0 && idx2 != 0 && count == 5){
								System.out.println("CASE 2");
								t1.x = idx1;
								t1.y = i;
								System.out.println(t1.x + " " + t1.y);
								DefensePoint1 = t1;
//								blackSearch.add(t1);
								t2.x = zeroIndex.get(0);
								t2.y = i;
//								blackSearch.add(t2);
								DefensePoint2 = t2;
								check = true;
								return check;
							}else if(idx1 != 0 && idx2 == 0 && (count == 4)) {
								System.out.println("CASE 3");
								t1.x = idx1;
								t1.y = i;
								
								DefensePoint1 = t1;
//								blackSearch.add(t1);
								int min = 999999;
								for(int z = t1.y -2;  z < t1.y + 3 ; z++) {
									for(int zi = t1.x -2; zi < t1.x + 3 ; zi++ ) {
										if(z >=0 && zi-2>=0 && min > weightGraph[z][zi]) {
											t2.x = zi;
											t2.y = z;
											min = weightGraph[z][zi];
										}
									}
								}
								System.out.println(t2.x + " " + t2.y);
								DefensePoint2 = t2;
//								blackSearch.add(t2);
								check = true;
								return check;
							}else if(idx1 != 0 && idx2 == 0 &&  count == 5) {
								System.out.println("CASE 4");
								t1.x = idx1;
								t1.y = i;
								DefensePoint1 = t1;
//								blackSearch.add(t1);
								System.out.println(t1.x + " " + t1.y);
								
								int min = 999999;
								for(int z = t1.y -2;  z < t1.y + 3 ; z++) {
									for(int zi = t1.x -2; zi < t1.x + 3 ; zi++ ) {
										if(t1.y-2 >=0 && t1.x-2>=0 && min > weightGraph[z][zi]) {
											t2.x = zi;
											t2.y = z;
											min = weightGraph[z][zi];
										}
									}
								}
//								blackSearch.add(t2);
								DefensePoint2 = t2;
//								System.out.println(blackSearch.size());
								check = true;
								return check;
							}
						}
					}
				}
	      }
	      
	      
	      for(int i = 0; i < selectedPoint.length; i++) {
	         ArrayList<Integer> zeroIndex = new ArrayList<Integer>();
	         for(int j = 0; j < selectedPoint[i].length; j++) {
	            int count = 0; 
	            int idx1 = 0 , idx2 = 0;
	            if(selectedPoint[j][i] == 1) {
	               if(selectedPoint[j-1][i] == 0) {
	                  idx1 = j-1;
	                  for(int z =j ; z < 19 &&  z < j + 5 ; z++) {
	                     if(selectedPoint[z][i] == 1) {
	                        count++;
	                        if(count == 5) {
								idx2 = z + 1;
								zeroIndex.add(idx2);
							}
	                     }else {
	                        if(selectedPoint[z][i] == 2 || selectedPoint[z][i] == 3) {
	                           break;
	                        }else if(selectedPoint[z][i] == 0) {
	                           idx2 = z;
	                           zeroIndex.add(idx2);
	                        }
	                     }
	                  }
	                  
	                  System.out.println("CASE 1 " + count);
	                  if(idx1 != 0 && idx2 != 0 && (count == 4 )) {
	                     System.out.println("CASE 1");
	                     t1.y = idx1;
	                     t1.x = i;
	                     System.out.println(t1.x + " " + t1.y);
	                     DefensePoint1 = t1;
//	                     blackSearch.add(t1);
	                     t2.y = zeroIndex.get(0);
	                     t2.x = i;
	                     DefensePoint2 = t2;
//	                     blackSearch.add(t2);
	                     check = true;
	                     return check;
	                  }else if(idx1 != 0 && idx2 != 0 && count == 5){
	                     System.out.println("CASE 2");
	                     t1.y = idx1;
	                     t1.x = i;
	                     System.out.println(t1.x + " " + t1.y);
	                     DefensePoint1 = t1;
//	                     blackSearch.add(t1);
	                     t2.y = zeroIndex.get(0);
	                     t2.x = i;
//	                     blackSearch.add(t2);
	                     DefensePoint2 = t2;
	                     check = true;
	                     return check;
	                  }else if(idx1 != 0 && idx2 == 0 && (count == 4)) {
	                     System.out.println("CASE 3");
	                     t1.y = idx1;
	                     t1.x = i;
	                     
	                     DefensePoint1 = t1;
//	                     blackSearch.add(t1);
	                     int min = 999999;
	                     for(int z = t1.y -2;  z < t1.y + 3 ; z++) {
	                        for(int zi = t1.x -2; zi < t1.x + 3 ; zi++ ) {
	                           if(z >=0 && zi-2>=0 && min > weightGraph[z][zi]) {
	                              t2.x = zi;
	                              t2.y = z;
	                              min = weightGraph[z][zi];
	                           }
	                        }
	                     }
	                     System.out.println(t2.x + " " + t2.y);
	                     DefensePoint2 = t2;
//	                     blackSearch.add(t2);
	                     check = true;
	                     return check;
	                  }else if(idx1 != 0 && idx2 == 0 &&  count == 5) {
	                     System.out.println("CASE 4");
	                     t1.y = idx1;
	                     t1.x = i;
	                     DefensePoint1 = t1;
//	                     blackSearch.add(t1);
	                     System.out.println(t1.x + " " + t1.y);
	                     
	                     int min = 999999;
	                     for(int z = t1.y -2;  z < t1.y + 3 ; z++) {
	                        for(int zi = t1.x -2; zi < t1.x + 3 ; zi++ ) {
	                           if(t1.y-2 >=0 && t1.x-2>=0 && min > weightGraph[z][zi]) {
	                              t2.x = zi;
	                              t2.y = z;
	                              min = weightGraph[z][zi];
	                           }
	                        }
	                     }
//	                     blackSearch.add(t2);
	                     DefensePoint2 = t2;
//	                     System.out.println(blackSearch.size());
	                     check = true;
	                     return check;
	                  }
	               }else if(j-1 >= 0 && selectedPoint[j-1][i] == 2 || j-1 >= 0 && selectedPoint[j-1][i] == 3) {
	                  for(int z = j ; z < j+5 ; z++) {
	                     if(selectedPoint[i][z] == 1) {
	                        count++;
	                        if(count == 5) {
	                        	idx2 = z + 1;
	                        }
	                     }else {
	                        if(selectedPoint[i][z] == 2 || selectedPoint[i][z] == 3) {
	                           break;
	                        }else if(selectedPoint[i][z] == 0) {
	                           idx2 = z;
	                           zeroIndex.add(idx2);
	                        }
	                     }
	                  }
	                  if(idx2 != 0 && ( count == 4 || count == 5 )) {
	                     System.out.println("CASE 3");
	                     t2.y = zeroIndex.get(0);
	                     t2.x = i;
//	                     blackSearch.add(t2);
	                     DefensePoint2 = t2;
	                     int min = 999999;
	                     for(int z = t2.y -2;  z < t2.y + 3 ; z++) {
	                        for(int zi = t2.x -2; zi < t2.x + 3 ; zi++ ) {
	                           if(t2.y-2 >=0 && t2.x-2>=0 && min > weightGraph[z][zi]) {
	                              t1.x = zi;
	                              t1.y = z;
	                              min = weightGraph[z][zi];
	                           }
	                        }
	                     }
	                     DefensePoint1 = t1;
	                     check = true;
	                     return check;
	                  }else if(idx2 == 0 && ( count == 4 || count == 5 )) {
	                     return false;
	                  }
	               }
	            }
	         }
	      }
	      
	      
	      
	      
	      return false;
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





