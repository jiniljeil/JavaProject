package application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import application.Frame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import application.Board;

public class Frame extends JFrame{
	
	private static Board boardPanel;
	private static JButton undo;
	private static JButton redo;
	private static JButton pen;
	private static JButton eraser; 
	private static JButton copy;
	private static JButton home;
	private static JButton screen;
	private static JButton Oscreen; // original Screen
	private static JButton refresh;
	private static JButton rotation; // 
	private static JButton resize; //
	// 드래그 , 크기 변환 
	private Color color = Color.black;
	private static JButton colorSet; //
	private static JComboBox<String> bolderPen; //
	private static JComboBox<String> bolderEraser;
	private static JLabel bp;
	private static JLabel be;
	private static JButton colorFillObject; // 보류
	private static String[] bold = { "2", "4", "6", "8", "10","12", "14", "16" ,"18", "20"};
	private static String[] boldEraser = { "10","12", "14", "16" ,"18", "20", "22", "24", "26"};
	@SuppressWarnings("deprecation")
	public Frame() {
		setTitle("Graphic Editor");
		setSize(1300,900);
		FlowLayout Layout = new FlowLayout();
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		setLocation(300,100);
		setPreferredSize(new Dimension(1300,900));
//		frame.setLocation(300,100);
//		frame.setPreferredSize(new Dimension(1300,900));
		
	
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(25,0,45,0));// 위 옆 아래 오른쪽
		panel.setBackground(new Color(243,243,243));
		panel.setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
//		ButtonHandler handler = new ButtonHandler();
		
		undo = new JButton(new ImageIcon(Main.class.getResource("back.jpg")));
		redo = new JButton(new ImageIcon(Main.class.getResource("front.png")));
		pen = new JButton(new ImageIcon(Main.class.getResource("pen.jpg")));
		eraser = new JButton(new ImageIcon(Main.class.getResource("eraser.png")));
		colorSet = new JButton(new ImageIcon(Main.class.getResource("color.jpg")));
		bolderPen = new JComboBox<String>(bold);
		bp = new JLabel("펜 굵기");
		bolderEraser = new JComboBox<String>(boldEraser);
		be = new JLabel("지우개 굵기");
		colorFillObject = new JButton(new ImageIcon(Main.class.getResource("")));
		
		undo.setToolTipText("뒤로 가기");
		redo.setToolTipText("앞으로 가기");
		pen.setToolTipText("펜");
		eraser.setToolTipText("지우개");
		colorSet.setToolTipText("색깔");
		bolderPen.setToolTipText("펜 굵기");
		bolderEraser.setToolTipText("지우개 굵기");
		colorFillObject.setToolTipText("채우기");
		add(panel);

	    boardPanel = new Board();
		ButtonHandler handler = new ButtonHandler();
		add(undo);
		add(redo);
		add(pen);
		add(eraser);
		add(colorSet);
		add(bolderPen);
		add(bp); // 선 굵기
		add(bolderEraser);
		add(be); // 지우개 굵기 
		add(colorFillObject); // 미완 
		
		undo.setBounds(10,10,50,50);
		redo.setBounds(70,10,50,50);
		pen.setBounds(130,10,50,50);
		eraser.setBounds(190,10,50,50);
		colorSet.setBounds(250,10,50,50); 
		bp.setBounds(390,10,60,20);
		be.setBounds(390,40,80,20);
		bolderPen.setBounds(310,10,70,20); // JList -> change
		bolderEraser.setBounds(310, 40 ,70 ,20);
//		colorFillObject.setBounds(310,40,20,20);
		
		add(boardPanel);
		contentPane.add(panel, BorderLayout.NORTH);
		
		///////////////////////// MENU ///////////////////////
		JMenu menu = new JMenu(" File ");
		JMenu shape = new JMenu(" Shape ");
		JMenu line = new JMenu(" Line ");
		copy = new JButton("Copy");
		home = new JButton("Home");
		screen = new JButton("Full Screen");
		Oscreen = new JButton("Original Screen");
		refresh = new JButton("Clear");
		menu.setMnemonic('F');
		shape.setMnemonic('S');
		line.setMnemonic('L');
		
		JMenuBar menuBar = new JMenuBar();
		JMenuItem load = new JMenuItem("Load");
		JMenuItem save = new JMenuItem("Save");
		menu.add(load);
		menu.add(save);
		JMenuItem rect = new JMenuItem("Rectangle");
		JMenuItem tri = new JMenuItem("Triangle");
		JMenuItem cir = new JMenuItem("Circle");
		JMenuItem Frect = new JMenuItem("Fill Rectangle");
		JMenuItem Ftri = new JMenuItem("Fill Triangle");
		JMenuItem Fcir = new JMenuItem("Fill Circle");
		shape.add(rect);
		shape.add(tri);
		shape.add(cir);
		shape.add(Frect);
		shape.add(Ftri);
		shape.add(Fcir);
		JMenuItem Lline = new JMenuItem("Linear Line");
		JMenuItem Dline = new JMenuItem("Dotted Line");
		line.add(Lline);
		line.add(Dline);
		menuBar.add(menu);
		menuBar.add(shape);
		menuBar.add(line);
		
	    // screen
		copy.setBorderPainted(false);
		copy.setBackground(new Color(243,243,243));
		home.setBorderPainted(false);
		home.setBackground(new Color(243,243,243));
		screen.setBorderPainted(false);
		screen.setBackground(Color.LIGHT_GRAY);
		Oscreen.setBorderPainted(false);
		Oscreen.setBackground(Color.LIGHT_GRAY);
		refresh.setBorderPainted(false);
		refresh.setBackground(Color.LIGHT_GRAY);
		menuBar.add(copy);
		menuBar.add(home);
		menuBar.add(screen);
		menuBar.add(Oscreen);
		menuBar.add(refresh);
		copy.setMnemonic('C');
		home.setMnemonic('H');
		screen.setMnemonic('F');
		Oscreen.setMnemonic('O');
		refresh.setMnemonic('C');
		
		pen.addActionListener(handler);
		eraser.addActionListener(handler);
		colorSet.addActionListener(handler);
		bolderPen.addActionListener(handler);
		bolderEraser.addActionListener(handler);
		copy.addActionListener(handler);
		home.addActionListener(handler);
		screen.addActionListener(handler);
		Oscreen.addActionListener(handler);
		refresh.addActionListener(handler);
		rect.addActionListener(handler);
		tri.addActionListener(handler);
		cir.addActionListener(handler);
		Frect.addActionListener(handler);
		Ftri.addActionListener(handler);
		Fcir.addActionListener(handler);
		Lline.addActionListener(handler);
		Dline.addActionListener(handler);
		setJMenuBar(menuBar);
		undo.addActionListener(handler);
		redo.addActionListener(handler);
		load.addActionListener(handler);
		save.addActionListener(handler);
		setVisible(true);
		
		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.pack();
//		frame.setVisible(true);
	}
	
	public static Window getFrame() {
//		Main.getFrame().dispose();
		return Main.getFrame();
	}
	
	public class ButtonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			if(event.getSource() == undo) {
				boardPanel.undo();
			}else if(event.getSource() == redo) {
				boardPanel.redo();
			}else if(event.getSource() == home) {
				boardPanel.home();
			}else if(event.getSource() == copy) {
				boardPanel.copy();
			}else if(event.getSource() == screen) {
				boardPanel.fullScreen();
			}else if(event.getSource() == Oscreen) {
				boardPanel.OriginalScreen();
			}else if(event.getSource() == refresh) {
				boardPanel.Clear();
			}else if(event.getSource() == pen) {
				boardPanel.pen();
			}else if(event.getSource() == eraser) {
				boardPanel.eraser();
			}else if(event.getSource() == colorSet) {
				color = JColorChooser.showDialog(null, "Color", color);
				if(color != null) {
					boardPanel.setColor(color);
				}
			}else if(event.getSource() == bolderPen) {
				int idx = bolderPen.getSelectedIndex();
				boardPanel.setBolderPen(2 * idx);
			}else if(event.getSource() == bolderEraser) {
				int idx = bolderEraser.getSelectedIndex();
				boardPanel.setBolderEraser(10 + 2*idx);
			}else if(event.getActionCommand() == "Rectangle") {
				boardPanel.paintRect();
			}else if(event.getActionCommand() == "Triangle") {
				boardPanel.paintTri();
			}else if(event.getActionCommand() == "Circle") {
				boardPanel.paintCir();
			}else if(event.getActionCommand() == "Fill Rectangle") {
				boardPanel.paintFRect();
			}else if(event.getActionCommand() == "Fill Triangle") {
				boardPanel.paintFTri();
			}else if(event.getActionCommand() == "Fill Circle") {
				boardPanel.paintTCir();
			}else if(event.getActionCommand() == "Linear Line") {
				boardPanel.linearLine();
			}else if(event.getActionCommand() == "Dotted Line") {
				boardPanel.dottedLine();
			}else if(event.getActionCommand() == "Save") {
				try {
					boardPanel.save();
				}catch(Exception e ) {
					e.printStackTrace();
				}
			}else if(event.getActionCommand() == "Load") {
				try {
					boardPanel.load();
				}catch(Exception e ) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}


