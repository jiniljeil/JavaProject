package src;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JSlider;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class frame extends JFrame{
	private static JLabel label;
	private Point mosaicPoint = new Point();
	private int mosaic = 0 ;
	private int mode = 0;
	private int minx = 111111 ,miny = 111111;
	private static  int originalWidth = 643;
	private static int originalHeight = 422;
	private int width;
	private int height;
	private static BufferedImage bi ;
	private static BufferedImage changebi;
	private Graphics g ;
	private int[][] pixelRed;
	private int[][] pixelGreen;
	private int[][] pixelBlue;
	private boolean con = false;
	private Image resizeImage;
	private int contrastValue = 100;
	private String path;
	private boolean zoomCheck = false;
	private int zoomValue = 0;
	private Point ePoint; 
	private JLabel imageLabel;
	private BufferedImage pbi ;
	private BufferedImage panelBI;
	private int[][] addImagePixel;
	private Point imagePoint;
	private Point CropStartPoint;
	private Point CropEndPoint;
	private boolean addImagePress = false;
	private BufferedImage tc;
	private int[][] mosaicPixel;
	//int p = img.getRGB(x, y);
	//Getting the A R G B values from the pixel value
	//	int a = (p>>24)&0xff;
	//	int r = (p>>16)&0xff;
	//	int g = (p>>8)&0xff;
	//	int b = p&0xff;
	public frame() {
//		panel handler = new panel();
//		pixelRed = new int[height][width];
//		pixelGreen = new int[height][width];
//		pixelBlue = new int[height][width];
//		JPanel panel = new JPanel();
//		super.add(panel);
//		
//		panel.setLocation(30, 130);
//		panel.setBackground(new Color(35,38,44));
//		panel.setSize(originalWidth, originalHeight);
		
//		add(panel);
		getContentPane().setLayout(null);
		setLocation(600,200);
		setSize(1000,700);
		
		JMenuBar jb = new JMenuBar();
		JMenu file = new JMenu("파일");
		JMenuItem load = new JMenuItem("불러 오기");
		JMenuItem save = new JMenuItem("저장하기");
		file.add(load);
		file.add(save);
		getContentPane().add(jb);
		getContentPane().setBackground(new Color(35,38,	44));
		
		JButton contrast = new JButton(new ImageIcon(Main.class.getResource("../image/Contrast.png")));
		contrast.setBounds(686, 329, 45, 43);
		getContentPane().add(contrast);
		contrast.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				con = true;
				for(int i = 0 ; i < originalHeight; i++) {
					for(int j = 0; j < originalWidth; j++) {
						Color t = new Color(bi.getRGB(j, i));
						
//						int a = (t >> 24) & 0xff;
						int red = (int) (t.getRed() * 0.2126) ;
						int green = (int) (t.getGreen() * 0.7152);
						int blue = (int) (t.getBlue() * 0.0722);
						
						Color color = new Color(red+green+blue,red+green+blue, red+green+blue  );
					
						changebi.setRGB(j, i, color.getRGB());
					}
				}
				label.setIcon(new ImageIcon(changebi.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH)));
			}
			
		});
		contrast.setBorderPainted(false);
		contrast.setContentAreaFilled(false);
//		contrast.setFocusPainted(false);
		
		JButton saturation = new JButton(new ImageIcon(Main.class.getResource("../image/Saturation.png")));
		saturation.setBounds(686, 395, 43, 43);
		getContentPane().add(saturation);
		saturation.setBorderPainted(false);
		saturation.setContentAreaFilled(false);
		saturation.setFocusPainted(false);
		
		JButton ImageColor = new JButton(new ImageIcon(Main.class.getResource("../image/Colorimage.png")));
		ImageColor.setBounds(686, 461, 43, 43);
		getContentPane().add(ImageColor);
		ImageColor.setBorderPainted(false);
		ImageColor.setContentAreaFilled(false);
		ImageColor.setFocusPainted(false);
		
		JButton Photo = new JButton(new ImageIcon(Main.class.getResource("../image/gallery.png")));
		Photo.setBorderPainted(false);
		Photo.setBounds(149, 585, 53, 64);
		getContentPane().add(Photo);
		Photo.setBorderPainted(false);
		Photo.setContentAreaFilled(false);
		Photo.setFocusPainted(false);
		Photo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String path = loadFile();
				try {
					label.setIcon(resizeImage(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		JButton zoom = new JButton(new ImageIcon(Main.class.getResource("../image/Zoom.png")));
		zoom.setBorderPainted(false);
		zoom.setBounds(686, 202, 43, 43);
		getContentPane().add(zoom);
		zoom.setBorderPainted(false);
		zoom.setContentAreaFilled(false);
//		zoom.setFocusPainted(false);
		
		
		
		JButton brightness = new JButton(new ImageIcon(Main.class.getResource("../image/bright.png")));
		brightness.setBorderPainted(false);
		brightness.setBounds(686, 265, 43, 43);
		getContentPane().add(brightness);
		brightness.setBorderPainted(false);
		brightness.setContentAreaFilled(false);
		brightness.setFocusPainted(false);
		brightness.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changebi = bi;
			}
			
		});
		
		JSlider saturationSlider = new JSlider(0,100,0);
		saturationSlider.setBackground(new Color(35,38,44));
		saturationSlider.setBounds(819, 406, 141, 26);
		getContentPane().add(saturationSlider);
		saturationSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stubs
				int value = saturationSlider.getValue();
				SaturationImage(value);
				label.setIcon(new ImageIcon(changebi.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH)));
				repaint();
			}
			
		});
		
		JSlider contrastSlider = new JSlider(JSlider.HORIZONTAL,-20,20,0);
		
		contrastSlider.setBackground(new Color(35,38,44));
		contrastSlider.setBounds(819, 339, 141, 26);
		getContentPane().add(contrastSlider);
		contrastSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int value = contrastSlider.getValue();
				
				for(int i = 0 ; i < originalHeight; i++) {
					for(int j = 0; j < originalWidth; j++) {
						Color t = new Color(bi.getRGB(j, i));
						
//						int a = (t >> 24) & 0xff;
						int red = (int) (t.getRed() * 0.2126) ;
						int green = (int) (t.getGreen() * 0.7152);
						int blue = (int) (t.getBlue() * 0.0722);
						
						int sum = red + green + blue;
						if(sum + contrastSlider.getValue() < 0) {
							sum = 0;
						}else if(sum + contrastSlider.getValue() > 255) {
							sum = 255;
						}else {
							contrastValue = ((JSlider)e.getSource()).getValue();
							
							Color color = new Color(red+green+blue +(int)(contrastSlider.getValue() ),
									red+green+blue + (int)(contrastSlider.getValue() ),
									red+green+blue + (int)(contrastSlider.getValue() ) );
							
							changebi.setRGB(j, i, color.getRGB());
						}
						
					}
				}
				
				label.setIcon(new ImageIcon(changebi.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH)));
//				g.drawImage(bi,0,0,null);
////				bi = new BufferedImage(width , height, BufferedImage.TYPE_BYTE_GRAY);
//				Graphics2D g2 = (Graphics2D) bi.getGraphics();
//				g2.drawImage(bi,0,0,null);
				repaint();
			}
		
		});
		
		JSlider brightSlider = new JSlider(0, 100, 1);
		brightSlider.setBackground(new Color(35,38,44));
		brightSlider.setBounds(819, 275, 141, 26);
		getContentPane().add(brightSlider);
		brightSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int value = brightSlider.getValue();
				changebi = deepCopy(brightImage(value));
				
//				System.out.println(value);
				label.setIcon(new ImageIcon(changebi.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH)));
				label.revalidate();
				repaint();
			}
			
		});
		
		JSlider zoomSlider = new JSlider(0,100,0);  // 0 200 400
		zoomSlider.setBackground(new Color(35,38,44));
		zoomSlider.setBounds(819, 212, 141, 26);
		getContentPane().add(zoomSlider);
		zoomSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				mosaic = zoomSlider.getValue();
				zoomValue = zoomSlider.getValue();
				if(zoomValue == 1) { // 200
					zoomCheck = true;
					mode = 4 ;
				}
//				changebi = imresize(changebi, 1/4);
			}
		});
		
		
		JSlider colorSlider = new JSlider(-1,1,0);
		colorSlider.setBackground(new Color(35,38,44));
		colorSlider.setBounds(819, 466, 141, 26);
		getContentPane().add(colorSlider);
		colorSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int choice = colorSlider.getValue();
				if(choice == -1) {
					getRed();
				}else if(choice == 0) {
					getGreen();
				}else if(choice == 1) {
					getBlue();
				}
//				System.out.println(choice);
				label.setIcon(new ImageIcon(changebi.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH)));
//				label.revalidate();
				repaint();
				changebi = deepCopy(bi);
			}
			
		});
		
		JLabel lblNewLabel_1 = new JLabel("Brightness");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(743, 275, 69, 26);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Contrast");
		lblNewLabel_1_2.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setBounds(743, 339, 57, 26);
		getContentPane().add(lblNewLabel_1_2);
		
		
		JLabel lblNewLabel_1_3 = new JLabel("Saturation");
		lblNewLabel_1_3.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setBounds(743, 406, 69, 26);
		getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Color");
		lblNewLabel_1_4.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setBounds(743, 466, 57, 26);
		getContentPane().add(lblNewLabel_1_4);
		
		JLabel programName = new JLabel("Photo Editor");
		programName.setHorizontalAlignment(SwingConstants.CENTER);
		programName.setFont(new Font("굴림", Font.BOLD, 24));
		programName.setForeground(Color.WHITE);
		programName.setBounds(775, 35, 148, 56);
		getContentPane().add(programName);
		
//		JPanel panel = new JPanel();
//		handler.setBounds(31, 142, 643, 422);
//		getContentPane().add(handler);
		//		super.setVisible(true);
				
		label = new JLabel();
		label.setLocation(30, 130);
		label.setSize(627, 422);
//		panel.add(label);
//		panel.addMouseListener(this);
	    super.add(label);
	    label.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(mode == 4) {
					
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(mode == 1) {
					ePoint = e.getPoint();
//					System.out.println("11");
//					 ePoint = event.getPoint();
					repaint();
				}else if(mode == 2) {
					imagePoint = e.getPoint();
				    
					addImagePress = true;
					paintComponent(g);
				}else if(mode == 3) {
//					System.out.println("1");
					mosaicPoint = e.getPoint();
					mosaicPixel = new int[11][11];
					int k1 = 0, k2 = 0;
					for(int i = 0; i < originalHeight; i++) {
						for(int j = 0 ;j < originalWidth; j++) {
							if((((mosaicPoint.x - 10/2) >= j) && (mosaicPoint.x + 10/2) <= j) && (mosaicPoint.y -10/2) >= i && (mosaicPoint.y + 10/2) <= i) {
								mosaicPixel[k1][k2++] = tc.getRGB(j, i);
								if(k2 > 10) {
									k2 = 0;
									k1++;
								}
							}
							
						}
					}
					paintComponent(g);
					
//					for(int i = 0 ; i <mosaicPixel.length; i++) {
//						for(int j = 0 ; j <mosaicPixel[i].length; j++) {
//							System.out.println(mosaicPixel[i][j]);
//						}
//					}
				}
			}
		
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if(mode == 3) {
					CropEndPoint = e.getPoint();
				}
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
	    
	    label.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				if(mode == 3) {
//					System.out.println("!");
					CropEndPoint = e.getPoint();
					paintComponent(g);
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    super.setBackground(new Color(35,38,44));
//	    super.add(handler);
	    
		JButton refresh = new JButton(new ImageIcon(Main.class.getResource("../image/refresh.jpg")));
		refresh.setFocusPainted(false);
		refresh.setContentAreaFilled(false);
		refresh.setBorderPainted(false);
		refresh.setBounds(226, 585, 46, 46);
		getContentPane().add(refresh);
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File file_img=new File(path);
				BufferedImage image_BI = null;
				try {
					image_BI = ImageIO.read(file_img);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				resizeImage = image_BI.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH);

				bi = new BufferedImage(originalWidth,originalHeight, BufferedImage.TYPE_INT_RGB);
//				bi.getGraphics().drawImage(resizeImage, 0, 0, null);
				g = bi.getGraphics();
				g.drawImage(resizeImage, 0, 0, null);
//				g.dispose();
				changebi = deepCopy(bi);
				ImageIcon image = new ImageIcon(resizeImage);
				label.setIcon(image);
			}
			
		});
		
		
		JLabel lblNewLabel = new JLabel("Refresh");
		lblNewLabel.setForeground(new Color(233,233,233));
		lblNewLabel.setBounds(227, 631, 45, 15);
		getContentPane().add(lblNewLabel);
		
		JButton MosaicButton = new JButton("Mosaic");
		MosaicButton.setBorderPainted(false);
		MosaicButton.setHorizontalAlignment(SwingConstants.LEADING);
		MosaicButton.setBackground(new Color(35,38,44));
		MosaicButton.setForeground(Color.WHITE);
		MosaicButton.setFont(new Font("굴림", Font.PLAIN, 14));
		MosaicButton.setBounds(727, 202, 85, 36);
		MosaicButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mode = 3;
				
				Image ig = bi.getScaledInstance(mosaic, mosaic, Image.SCALE_SMOOTH);
				BufferedImage t = new BufferedImage(mosaic,mosaic,BufferedImage.TYPE_INT_RGB);
				Graphics gc = t.getGraphics();
				gc.drawImage(ig, 0, 0, null);
				gc.dispose();
				
				Image igc = t.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH);
				tc = new BufferedImage(originalWidth,originalHeight,BufferedImage.TYPE_INT_RGB);
				Graphics gcs = tc.getGraphics();
				gcs.drawImage(igc, 0, 0, null);
				gcs.dispose();
				
				label.setIcon(new ImageIcon(tc.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH)));
				//paintComponent(g);
//				g.drawImage(t,0,0,originalWidth, originalHeight, null);
			}
		});
		
		getContentPane().add(MosaicButton);
		
		JButton addPhoto = new JButton((Icon) null);
		
		addPhoto.setFocusPainted(false);
		addPhoto.setContentAreaFilled(false);
//		addPhoto.setBorderPainted(false);
		addPhoto.setBounds(294, 585, 46, 46);
		getContentPane().add(addPhoto);
		addPhoto.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mode = 2;
				
//				JFileChooser file = new JFileChooser();
//				file.setCurrentDirectory(new File(System.getProperty("user.home")));
//				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","jpeg","gif","png");
//				file.addChoosableFileFilter(filter);
//				int result = file.showSaveDialog(null);
				String path = loadFile();;
				
				
					File file_img = new File(path);
					BufferedImage image_BI = null;
					try {
						image_BI = ImageIO.read(file_img);
//						System.out.println(image_BI.getHeight());
						addphoto(image_BI);
//						Graphics2D g2 = (Graphics2D) g;
						paintComponent(g);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					addphoto(image_BI);
					
				
			}
		});
		
		
		
		JLabel lblAddImage = new JLabel("Add");
		lblAddImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddImage.setForeground(Color.WHITE);
		lblAddImage.setBounds(288, 631, 57, 15);
		getContentPane().add(lblAddImage);
		
		JButton saveButton = new JButton((Icon) null);
		saveButton.setFocusPainted(false);
		saveButton.setContentAreaFilled(false);
		saveButton.setBounds(361, 585, 46, 46);
		getContentPane().add(saveButton);
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveFile();
			}
			
		});
		
		JLabel saveLabel = new JLabel("Save");
		saveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		saveLabel.setForeground(Color.WHITE);
		saveLabel.setBounds(357, 631, 57, 15);
		getContentPane().add(saveLabel);
		
		
	}
	public BufferedImage brightImage(int value) {
		
		BufferedImage t = deepCopy(bi);
		for(int i = 0 ; i < originalHeight; i++) {
			for(int j = 0 ; j < originalWidth; j++) {
				Color color = new Color(bi.getRGB(j, i));
				int red = (int) (color.getRed() + value);
				int green = (int) (color.getGreen() + value);
				int blue = (int) (color.getBlue() + value);
				
				red = Math.max(0, red);
				green = Math.max(0, green);
				blue = Math.max(0, blue);
				
				red = Math.min(255, red);
				green = Math.min(255, green);
				blue = Math.min(255, blue);
				
				Color c = new Color(red, green, blue);
				t.setRGB(j, i, c.getRGB());
			}
		}
		
		RescaleOp brightFilter = new RescaleOp((float)(value* 0.05),0,null);
		
		return brightFilter.filter(bi,t);
	}
	
//	public BufferedImage saturationImage() {
//		
//	}
	
	public ImageIcon resizeImage(String path) throws IOException {
//		System.out.println(path);
//		ImageIcon my = new ImageIcon(path);
//		Image img = my.getImage();
		this.path = path;
		File file_img=new File(path);
		BufferedImage image_BI = ImageIO.read(file_img);
		resizeImage = image_BI.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH);
		
//		System.out.println(originalWidth + " " + originalHeight);
		
		bi = new BufferedImage(originalWidth,originalHeight, BufferedImage.TYPE_INT_RGB);
//		bi.getGraphics().drawImage(resizeImage, 0, 0, null);
		g = bi.getGraphics();
		g.drawImage(resizeImage, 0, 0, null);
//		g.dispose();
		changebi = deepCopy(bi);
		ImageIcon image = new ImageIcon(resizeImage);
		
		System.out.println(new Color((bi.getRGB(100, 100))));
		
		
		
		return image;
	}
	
	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel model = bi.getColorModel();
		WritableRaster wr = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
		return new BufferedImage(model, wr, model.isAlphaPremultiplied(), null);
	}
	
	public static BufferedImage getBI() {
		return bi;
	}
	
	
	public void addphoto (BufferedImage t) { // t 추가된 이미지 
		mode = 2;
		pbi = frame.deepCopy(t); // 추가된 이미지 
		
//		System.out.println(originalWidth + " " + originalHeight);
		
//		bi = new BufferedImage(originalWidth,originalHeight, BufferedImage.TYPE_INT_RGB);
////		bi.getGraphics().drawImage(resizeImage, 0, 0, null);
//		g = bi.getGraphics();
//		g.drawImage(resizeImage, 0, 0, null);
		panelBI = frame.deepCopy(bi);
		
		addImagePixel = new int[t.getHeight()][t.getWidth()];
//		addRed = new int[pbi.getHeight()][pbi.getWidth()];
//		addGreen = new int[pbi.getHeight()][pbi.getWidth()];
//		addBlue = new int[pbi.getHeight()][pbi.getWidth()];
		
		Color backgroundColor = new Color(t.getRGB(1, 1));
		
		for(int i = 0 ; i < t.getHeight(); i++) {
			for(int j = 0 ; j < t.getWidth();j++){
				if(!backgroundColor.equals(new Color(t.getRGB(j, i)))) {
					if(miny > i) {
						miny = i;
						minx = j;
					}
					addImagePixel[i][j] = t.getRGB(j,i);
				}else {
					addImagePixel[i][j] = -1;
				}
			}
		}
		
//		for(int i = 0; i < t.getHeight(); i++) {
//			for(int j = 0; j < t.getWidth(); j++) {
//				System.out.print(new Color(addImagePixel[i][j]).getRGB());
//			}
//			System.out.println("");
//		}
		
	}
	
	public void SaturationImage(int value) {
		for(int i = 0; i < originalHeight; i++) {
			for(int j = 0;  j < originalWidth ; j++) {
				Color color = new Color(bi.getRGB(j, i));
				float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
				float hue = hsb[0];
				float saturation = hsb[1];
				float brightness = hsb[2];
				
				if((saturation + value * 0.001)  > 1) {
					saturation = 1;
				}else if((saturation + value * 0.001) < 0){
					saturation = 0;
				}else {
					saturation += value * 0.001;
				}
				
				int rgb = Color.HSBtoRGB(hue, saturation, brightness);
				changebi.setRGB(j, i, rgb);
			}
		}
	}
	
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g ;
		super.paintComponents(g);
		if(mode == 1) {
//			int[][] originImage = 
			bi = frame.getBI();
			Image resize = bi.getScaledInstance(40, 40, BufferedImage.TYPE_INT_RGB);
			g2.drawImage(resize, ePoint.x-30, ePoint.y-30, null);
//			imageLabel.setIcon(new ImageIcon(resize));
			
			
//			g2.setColor(Color.white);
//			g2.drawRect(ePoint.x-30, ePoint.y-30, 30, 30);
		}else if(mode == 2) {
			
			if(addImagePress) {
				System.out.println("1");
				for(int i = 0 ; i < pbi.getHeight(); i++) {
					for(int j = 0; j < pbi.getWidth(); j++) {
						if(addImagePixel[i][j] != -1) {
							changebi.setRGB(j + imagePoint.x - minx, i + imagePoint.y -miny, addImagePixel[i][j]);
						}
					}
				}
//				System.out.printf("%d %d", pbi.getWidth(), pbi.getHeight());
				label.setIcon(new ImageIcon(changebi.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH)));
				mode = 0;
				addImagePress = false;
				
				
			}
			
//			setBufferedImage(panelBI);
//			System.out.println("!!!!!!!!!");
//			changebi = deepCopy(panelBI);
			
			
//			repaint();
		}else if(mode == 3) {
			int k1 = 0 , k2 = 0 ;
//			System.out.println("1");
			for(int i = 0 ; i < originalHeight; i++) {
				for(int j = 0 ; j < originalWidth; j++) {
					if((((mosaicPoint.x - 5) >= j) && (mosaicPoint.x + 5) <= j) && ((mosaicPoint.y - 5) >= i && (mosaicPoint.y + 5 ) <= i)) {
						System.out.println(i + " " + j);
						mosaicPixel[k1][k2] = tc.getRGB(j, i);
						
						changebi.setRGB(mosaicPoint.x, mosaicPoint.y, mosaicPixel[k1][k2]);
						k2++;
						if(k2 > 10) {
							k2 = 0;
							k1++;
						}
					}
//					if()
				}
			}
			
			label.setIcon(new ImageIcon(changebi.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH)));

			
			
		}
//		Image resize = changebi.getScaledInstance(40, 40, BufferedImage.TYPE_INT_RGB);
//		g2.drawImage(resize,);
	}
	
	public void getRed() {
		int white = new Color(255,255,255).getRGB();
		int black = new Color(0,0,0).getRGB();
		for(int i = 0 ; i < originalHeight; i++) {
			for(int j = 0; j < originalWidth; j++) {
				if(new Color(bi.getRGB(j, i)).getGreen() > 170) {
					changebi.setRGB(j,i, white);
				}else if(new Color(bi.getRGB(j, i)).getBlue() > 170) {
					changebi.setRGB(j, i, white);
				}else if(bi.getRGB(j, i) == new Color(85,170,170).getRGB()) {
					changebi.setRGB(j, i, white);
				}else if(changebi.getRGB(j, i) != white) {
					changebi.setRGB(j, i, black);
				}
			}
		}
	}
	
	public void getGreen() {
		int white = new Color(255,255,255).getRGB();
		int black = new Color(0,0,0).getRGB();
		for(int i = 0 ; i < originalHeight; i++) {
			for(int j = 0; j < originalWidth; j++) {
				if(new Color(bi.getRGB(j, i)).getRed() > 170) {
					changebi.setRGB(j,i, white);
				}else if(new Color(bi.getRGB(j, i)).getBlue() > 170) {
					changebi.setRGB(j, i, white);
				}else if(bi.getRGB(j, i) == new Color(170,85,170).getRGB()) {
					changebi.setRGB(j, i, white);
				}else if(changebi.getRGB(j, i) != white) {
					changebi.setRGB(j, i, black);
				}
			}
		}
	}
	
	public void getBlue() {
		int white = new Color(255,255,255).getRGB();
		int black = new Color(0,0,0).getRGB();
		for(int i = 0 ; i < originalHeight; i++) {
			for(int j = 0; j < originalWidth; j++) {
				if(new Color(bi.getRGB(j, i)).getRed() > 170) {
					changebi.setRGB(j,i, white);
				}else if(new Color(bi.getRGB(j, i)).getGreen() > 170) {
					changebi.setRGB(j, i, white);
				}else if(bi.getRGB(j, i) == new Color(170,170,85).getRGB()) {
					changebi.setRGB(j, i, white);
				}else if(changebi.getRGB(j, i) != white) {
					changebi.setRGB(j, i, black);
				}
			}
		}
	}
	
	public void saveFile() {
		FileDialog f = new FileDialog(this, "파일 저장", FileDialog.SAVE);
	    f.setVisible(true);
	    File t = new File(f.getDirectory()+f.getFile());
	    try {
			ImageIO.write(changebi,"png",t);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
//		int result = file.showSaveDialog(null);
//		
//		if(result == JFileChooser.APPROVE_OPTION) {
////			ImageIO.write(changebi,"png",)
//		}
		
	}
	
	public String loadFile() {
		FileDialog f = new FileDialog(this, "파일 열기", FileDialog.LOAD);
	    f.setVisible(true);
	    
	    File t = new File(f.getDirectory()+f.getFile());
	    try {
	    	bi = ImageIO.read(t);
	    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    return f.getDirectory()+f.getFile();
	}
}
