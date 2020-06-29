package application;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Board extends JPanel {
   private static int x1=111111; // 그리기 
   private static int y1=0;
   private static int x2=0;
   private static int y2=1111111;
   private int mode = 0, p = 0;
   private boolean hiding = false;
   private ArrayList<Shape> changeObject = new ArrayList<Shape>();
   private Point customCursor = new Point();
   private Point mouseStart = new Point();
   private Point mouseEnd = new Point(); // drag (Object checker)
   private Point dragStart = new Point();
   private Point dragEnd = new Point();
   private Point copyStart = new Point();
   private Point copyEnd = new Point();
   private Point resizeStart = new Point();
   private Point resizeEnd = new Point();
   private ArrayList<Boolean> cursorCheck = new ArrayList<Boolean>(6); // resize 작업 안함
   private ArrayList<Shape> stack = new ArrayList<Shape>();
   private ArrayList<Shape> pop = new ArrayList<Shape>();
   private ArrayList<Point> penS = new ArrayList<Point>();
   private int clear = 0;
   private Point start = new Point(), end = new Point();
   private int thicknessPen = 2;
   private int thicknessEraser = 10;
   public int thicknessShape = 2; // 추후 업데이트 
   private Color color = Color.black;
   public Color colorShape = Color.black; 
   private BufferedImage bi ;
   private Frame original = null;
   private ArrayList<Shape> shape = new ArrayList<Shape>();
   private ArrayList<PointStore> pointStore = new ArrayList<PointStore>();
   private ArrayList<PointStore> pointPop = new ArrayList<PointStore>();
   private ArrayList<Integer> eraserIdx = new ArrayList<Integer>();
   private Shape objDrag = null; 
   private ArrayList<ArrayList<Point>> pointS;
   private boolean undo = false, redo = false, objEraser;
   private int resizeMode = 0 ;
   private boolean drag =false, resize = false, copy = false;
   
//   boolean mouseClicked = false;
   public Board() {
      setBackground(Color.white);
      MouseHandler handler = new MouseHandler();
      super.addMouseListener(handler);
      super.addMouseMotionListener(handler);
   }
   public void home() {
      // TODO Auto-generated method stub
      mode = 0;
      copy = false;
   }

   public void copy() {
      // TODO Auto-generated method stub
      copy =true;
   }
   public void fullScreen() {
      mode = 1;
      if(mode == 1) {
         GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
         original = (Frame) Frame.getFrame();
         device.setFullScreenWindow(Frame.getFrame());
      }
   }
   public void OriginalScreen() {
      // TODO Auto-generated method stub
      if(original != null) {
         original.setLocation(300, 100);
         original.setSize(new Dimension(1300,900));
      }
   }
   public void Clear() {
      // TODO Auto-generated method stub
      clear = 1;
      if(clear == 1) {
         super.repaint();
         shape.clear();
         pointStore.clear();
      }
   }
   public void pen() { // 미완 
      // TODO Auto-generated method stub
      mode = 2;
   }
   public void eraser() {
      // TODO Auto-generated method stub
      mode = 3;
      
      if(objDrag != null && 
            objDrag.getDrag()) {
         pop.add(objDrag);
         eraserIdx.add(shape.indexOf(objDrag));
         shape.remove(shape.indexOf(objDrag));
//         mode = 0;
         repaint();
      }else {
         mode = 0;
      }
     
   }
   public void paintRect() { // 
      // TODO Auto-generated method stub
      mode = 4; 
   }
   public void paintTri() {
      // TODO Auto-generated method stub
      mode = 5;
   }
   public void paintCir() {
      // TODO Auto-generated method stub
      mode = 6;
   }
   public void linearLine() {
      // TODO Auto-generated method stub
      mode = 7;
   }
   public void dottedLine() {
      // TODO Auto-generated method stub
      mode = 8;
   }
   public void paintFRect() {
      // TODO Auto-generated method stub
      mode = 9;
   }
   public void paintFTri() {
      // TODO Auto-generated method stub
      mode = 10;
   }

   public void paintTCir() {
      // TODO Auto-generated method stub
      mode = 11;
   }

   
   public void setBolderPen(int idx) {
      if(mode == 2) {
         thicknessPen = idx;
      }else {
         thicknessShape = idx;
      }
   }
   public void setBolderEraser(int idx) {
      thicknessEraser = idx;
   }
   
   public void setColor(Color color) {
      this.color = color;
      this.colorShape = color;
   }
   public void undo() {
      // TODO Auto-generated method stub
      Shape tmp = null;
      undo = true;
//      for(int i = pointStore.size()-1 ; i >= 0 ; i--) {
//         pointS = pointStore.get(i).pointReturn();
//         if(pointS.size() > 1) { // 드래그 된 경우 
////            for(int j = 0; j < pointS.size(); j++) { // 여러개의 좌표가 존재할 경우 
//            
//            int k = pointS.size()-1;
//               if(pointS.get(k).get(0).getX() == shape.get(shape.size()-1).start().getX() && pointS.get(k).get(0).getY() == shape.get(shape.size()-1).start().getY()
//                     && pointS.get(k).get(1).getX() == shape.get(shape.size()-1).end().getX() && pointS.get(k).get(1).getY() == shape.get(shape.size()-1).end().getY()) {
//                  tmp = shape.get(shape.size()-1);
//                  k--;
//                  System.out.println(k);
//                  tmp.setStartPoint(pointS.get(k).get(0));
//                    tmp.setEndPoint(pointS.get(k).get(1));
//                  pointS.remove(pointS.size()-1);
//                  System.out.println(pointS.size());
//                  shape.set(shape.indexOf(shape.get(shape.size()-1)),tmp );
//                    
//                  pointStore.remove(pointStore.size()-1);
//                    break;            
//               }
////            }
//         }else {
//            pop.add(shape.get(shape.size()-1));
//            shape.remove(shape.size()-1);
//            pointStore.remove(pointStore.size()-1);
//            break;
//         }
//      }
      if(mode == 3) {
         shape.add(eraserIdx.get(eraserIdx.size()-1), pop.get(pop.size()-1));
         pop.remove(pop.get(pop.size()-1));
         mode = 0;
      }else {
         pop.add(shape.get(shape.size()-1));
         shape.remove(shape.size()-1);
      }
      
//      pointStore.remove(pointStore.size()-1);
      repaint();
//      stack.add(shape.get(shape.size()-1));   
//      shape.remove(shape.size()-1);
//      repaint();
   }
   public void redo() {
      // TODO Auto-generated method stub
      redo = true;
      shape.add(pop.get(pop.size()-1));
      pop.remove(pop.size()-1);
      repaint();
   }
   public void save() throws IOException{
      // TODO Auto-generated method stub
//      JFileChooser file = new JFileChooser();
      FileDialog f = new FileDialog(Main.getFrame(), "파일 열기", FileDialog.LOAD);
      f.setVisible(true);
      
      String directory = f.getDirectory();
      String file = f.getFile();
      try {
         ObjectOutputStream objLoad = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(directory, file))));
         objLoad.writeObject(shape);
         objLoad.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
      
   }
   public void load() throws IOException{
      FileDialog f = new FileDialog(Main.getFrame(), "파일 열기", FileDialog.LOAD);
      f.setVisible(true);
         
      String directory = f.getDirectory();
      String file = f.getFile();
      
      try {
         ObjectInputStream objLoad = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(directory, file))));
         shape = (ArrayList)objLoad.readObject();
         objLoad.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
      undo();
      redo();
   }
   
   @Override
   public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      super.paintComponent(g);
      g2.setColor(Color.black);
      if(clear == 1) {
         bi = null;
         clear = 0;
         shape.clear();
      }
      if(bi == null) {
         bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
      }else {
         g2.drawImage(bi, 0, 0, null);
      }   
      if(mode == 0 ) {
         if(hiding == false) {
               if(!drag) { // 드래그 (Object 찾기) (Clear)
                  Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                  g2.setStroke(dashed);
                  g2.drawRect(Math.min(mouseStart.x, mouseEnd.x),Math.min(mouseStart.y, mouseEnd.y),
                        Math.max(mouseStart.x-mouseEnd.x, mouseEnd.x-mouseStart.x), Math.max(mouseStart.y-mouseEnd.y, mouseEnd.y-mouseStart.y));
               }
         }else {
            hiding = false;
         }
      }
      
      for(Shape s : shape) { // 범위 안에 포함된 도형들 표시 (Clear)
         if(mode == 0 || undo || redo ) { 
            if( (Math.min(mouseEnd.x, mouseStart.x) <= Math.min(s.startGetX(),s.endGetX()) && (Math.min(mouseStart.y, mouseEnd.y) <= Math.min(s.startGetY(),s.endGetY()) ))
                  && Math.max(mouseStart.x, mouseEnd.x) >= Math.max(s.startGetX(), s.endGetX()) && Math.max(mouseStart.y,mouseEnd.y) >= Math.max(s.endGetY(), s.startGetY())) {
                  Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                    g2.setStroke(dashed);
                    g2.drawRect(Math.min(s.startGetX(), s.endGetX()),Math.min(s.startGetY(), s.endGetY()),
                        Math.max(s.startGetX()-s.endGetX(), s.endGetX()-s.startGetX()), Math.max(s.endGetY()
                              -s.startGetY(),s.startGetY()-s.endGetY()));
                    s.setDrag(true); // possible Object ( drag )
            }
            if(s.type == 9) {
               
               
               for(int i = 0; i < s.penP.size(); i++) {
                  for(int j = 0 ; j <s.penP.get(i).size(); j++) {
                     if(x1 > s.penP.get(i).get(j).x) { // 가장 작은 x
                        x1 = s.penP.get(i).get(j).x;
                     }
                     if(y1 < s.penP.get(i).get(j).y) { // 가장 큰 y
                        y1 = s.penP.get(i).get(j).y;
                     }
                     if(x2 < s.penP.get(i).get(j).x) { // 가장 큰 x
                        x2 = s.penP.get(i).get(j).x;
                     }
                     if(y2 > s.penP.get(i).get(j).y) {
                        y2 = s.penP.get(i).get(j).y; // y 가장 작은 값 맨위 
                     }
                  }
               }
               System.out.println(x1+ " " + y2 + " " + x2 +" " +y1);
                
               if( (Math.min(mouseStart.x, mouseEnd.x) <= x1) && (Math.min(mouseStart.y, mouseEnd.y) <= y2) && 
                     Math.max(mouseStart.x, mouseEnd.x) >= x2 && Math.max(mouseStart.y, mouseEnd.y) >= y1) {
                  
                  Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                    g2.setStroke(dashed);
                    
                    g2.drawRect(x1,y2,(x2-x1),(y1-y2));
                    s.setDrag(true);
               }
               
            }
            
            if(objDrag != null && resize ) {
               
            }
            
            if(objDrag != null && drag && s.getDrag() && !copy) { // 평행 이동 
               g2.setStroke(new BasicStroke(thicknessShape));
               if(objDrag.type == 1) {
                  g2.drawRect(Math.min(objDrag.startGetX(), objDrag.endGetX()) + (dragEnd.x - dragStart.x),
                        Math.min(objDrag.startGetY(), objDrag.endGetY()) + (dragEnd.y - dragStart.y),
                        Math.max(objDrag.startGetX() - objDrag.endGetX(), objDrag.endGetX()-objDrag.startGetX()),
                        Math.max(objDrag.startGetY() - objDrag.endGetY(), objDrag.endGetY()-objDrag.startGetY()));
               }else if(objDrag.type == 2) {
                  g2.drawPolygon(new int[] {objDrag.startGetX() + (dragEnd.x - dragStart.x), (objDrag.startGetX()+objDrag.endGetX())/2 + (dragEnd.x - dragStart.x),
                        objDrag.endGetX() + (dragEnd.x - dragStart.x)},new int[] {objDrag.endGetY() + (dragEnd.y - dragStart.y), objDrag.startGetY() + (dragEnd.y - dragStart.y),
                        objDrag.endGetY() + (dragEnd.y - dragStart.y)}, 3);
               }else if(objDrag.type == 3) {
                  g2.drawOval(Math.min(objDrag.startGetX(), objDrag.endGetX()) + (dragEnd.x - dragStart.x), Math.min(objDrag.startGetY(), objDrag.endGetY()) + (dragEnd.y - dragStart.y),
                        Math.max(objDrag.endGetX()-objDrag.startGetX(), objDrag.startGetX()-objDrag.endGetX()), Math.max(objDrag.endGetY()-objDrag.startGetY(), objDrag.startGetY()-objDrag.endGetY()));
               }else if(objDrag.type == 4) {
                  g2.fillRect(Math.min(objDrag.startGetX(), objDrag.endGetX()) + (dragEnd.x - dragStart.x),
                        Math.min(objDrag.startGetY(), objDrag.endGetY()) + (dragEnd.y - dragStart.y),
                        Math.max(objDrag.startGetX() - objDrag.endGetX(), objDrag.endGetX()-objDrag.startGetX()),
                        Math.max(objDrag.startGetY() - objDrag.endGetY(), objDrag.endGetY()-objDrag.startGetY()));
               }else if(objDrag.type == 5) {
                  g2.fillPolygon(new int[] {objDrag.startGetX() + (dragEnd.x - dragStart.x), (objDrag.startGetX()+objDrag.endGetX())/2 + (dragEnd.x - dragStart.x),
                        objDrag.endGetX() + (dragEnd.x - dragStart.x)},new int[] {objDrag.endGetY() + (dragEnd.y - dragStart.y), objDrag.startGetY() + (dragEnd.y - dragStart.y),
                              objDrag.endGetY() + (dragEnd.y - dragStart.y)}, 3);
               }else if(objDrag.type == 6) {
                  g2.fillOval(Math.min(objDrag.startGetX(), objDrag.endGetX()) + (dragEnd.x - dragStart.x),
                        Math.min(objDrag.startGetY(), objDrag.endGetY()) + (dragEnd.y - dragStart.y),
                        Math.max(objDrag.startGetX() - objDrag.endGetX(), objDrag.endGetX()-objDrag.startGetX()),
                        Math.max(objDrag.startGetY() - objDrag.endGetY(), objDrag.endGetY()-objDrag.startGetY()));
               }else if(objDrag.type == 7) {
                  g2.drawLine(objDrag.startGetX() + (dragEnd.x - dragStart.x), objDrag.startGetY()+ (dragEnd.y - dragStart.y),
                        objDrag.endGetX() + (dragEnd.x - dragStart.x), objDrag.endGetY() + (dragEnd.y - dragStart.y));
               }else if(objDrag.type == 8) {
                  Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                    g2.setStroke(dashed);
                  g2.drawLine(objDrag.startGetX() + (dragEnd.x - dragStart.x), objDrag.startGetY()+ (dragEnd.y - dragStart.y),
                        objDrag.endGetX() + (dragEnd.x - dragStart.x), objDrag.endGetY() + (dragEnd.y - dragStart.y));
               }else if(objDrag.type == 9) {
                  Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                   g2.setStroke(dashed);
                   for(int i = 0 ; i < s.penP.size(); i++) {
                      for(int j = 0 ; j < s.penP.get(i).size() -1 ; j++) {
                         g2.drawLine(s.penP.get(i).get(j).x + (dragEnd.x - dragStart.x), s.penP.get(i).get(j).y + (dragEnd.y - dragStart.y),
                               s.penP.get(i).get(j+1).x + (dragEnd.x - dragStart.x), s.penP.get(i).get(j+1).y  + (dragEnd.y - dragStart.y));
                      }
                   }
               }
               
            }
            
            undo = false;
            redo = false;
            objEraser = false;
         }else {
            drag = false;
//            objDrag = null;
         }
         g2.setStroke(new BasicStroke(s.getThickness()));
         g2.setColor(s.getColor());
//         if(!drag) {
            if(s.type == 1) { // rectangle
               g2.drawRect(Math.min(s.startGetX(), s.endGetX()),Math.min(s.startGetY(), s.endGetY()),
                     Math.max(s.startGetX()-s.endGetX(), s.endGetX()-s.startGetX()), Math.max(s.endGetY()
                           -s.startGetY(),s.startGetY()-s.endGetY()));
            }else if(s.type == 2) { // triangle
               g2.drawPolygon(new int[]{s.startGetX(),((s.startGetX()+s.endGetX())/2),s.endGetX()},
                     new int[] {s.endGetY(),s.startGetY(),s.endGetY()}, 3);
            }else if(s.type == 3) { // circle
               g2.drawOval(Math.min(s.startGetX(), s.endGetX()), Math.min(s.startGetY(), s.endGetY()),
                     Math.max(s.endGetX()-s.startGetX(), s.startGetX()-s.endGetX()) , Math.max(s.endGetY()-s.startGetY(), s.startGetY()-s.endGetY()));
            }else if(s.type == 4) { // fill rectangle
               g2.fillRect(Math.min(s.startGetX(), s.endGetX()),Math.min(s.startGetY(), s.endGetY()),
                     Math.max(s.startGetX()-s.endGetX(), s.endGetX()-s.startGetX()), Math.max(s.endGetY()
                           -s.startGetY(),s.startGetY()-s.endGetY()));
            }else if(s.type == 5) { // fill triangle
               g2.fillPolygon(new int[]{s.startGetX(),((s.startGetX()+s.endGetX())/2),s.endGetX()},
                     new int[] {s.endGetY(),s.startGetY(),s.endGetY()}, 3);
            }else if(s.type == 6) { // fill circle
               g2.fillOval(Math.min(s.startGetX(), s.endGetX()), Math.min(s.startGetY(), s.endGetY()),
                     Math.max(s.endGetX()-s.startGetX(), s.startGetX()-s.endGetX()) , Math.max(s.endGetY()-s.startGetY(), s.startGetY()-s.endGetY()));
            }else if(s.type == 7) { //linear line
               g2.drawLine(s.startGetX(), s.startGetY(), s.endGetX(), s.endGetY());
            }else if(s.type == 8) { //dotted line
               Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                 g2.setStroke(dashed);
               g2.drawLine(s.startGetX(), s.startGetY(), s.endGetX(), s.endGetY());
               g2.setStroke(new BasicStroke());
            }else if(s.type == 9) {
               for(int i = 0 ; i < s.penP.size(); i++) {
                  for(int j = 0 ; j < s.penP.get(i).size() -1 ; j++) {
                     g2.drawLine(s.penP.get(i).get(j).x, s.penP.get(i).get(j).y, s.penP.get(i).get(j+1).x, s.penP.get(i).get(j+1).y);
                  }
               }
            }
//         }
      }
      g2.setColor(colorShape);
      g2.setStroke(new BasicStroke(thicknessShape));
      if(mode == 2) {
//         int[] x = new int[penX.size()];
//         int[] y = new int[penY.size()];
//         
//         for(int i = 0 ; i < penX.size(); i++) {
//            x[i] = penX.get(i);
//            y[i] = penY.get(i);
//         }
//         g2.drawPolygon(x,y,penX.size());
         for(int i = 0; i <penS.size()-1; i++) {
            g2.drawLine(penS.get(i).x, penS.get(i).y, penS.get(i+1).x, penS.get(i+1).y);
         }
         
      }else if(mode == 4) {
         g2.drawRect(Math.min(start.x, end.x), Math.min(start.y, end.y),Math.max(start.x-end.x, end.x-start.x), Math.max(end.y-start.y,start.y-end.y) );
      }else if(mode == 5) {
         g2.drawPolygon(new int[] {start.x,(int)((start.x+end.x)/2), end.x },new int[] {end.y, start.y, end.y} , 3);
      }else if(mode == 6) {
         g2.drawOval(Math.min(start.x, end.x), Math.min(start.y, end.y), Math.max(start.x-end.x, end.x-start.x), Math.max(start.y-end.y, end.y-start.y));
      }else if(mode == 7) { // linear line
         g2.drawLine(start.x, start.y, end.x, end.y);
      }else if(mode == 8) {
         Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
           g2.setStroke(dashed);
           g2.drawLine(start.x, start.y, end.x, end.y);
           // ++
      }else if(mode == 9) {
         g2.fillRect(Math.min(start.x, end.x), Math.min(start.y, end.y),Math.max(start.x-end.x, end.x-start.x), Math.max(end.y-start.y,start.y-end.y) );
      }else if(mode == 10) {
         g2.fillPolygon(new int[] {start.x,(int)((start.x+end.x)/2), end.x },new int[] {end.y, start.y, end.y} , 3);
      }else if(mode == 11) {
         g2.fillOval(Math.min(start.x, end.x), Math.min(start.y, end.y), Math.max(start.x-end.x, end.x-start.x), Math.max(start.y-end.y, end.y-start.y));
      }
      
   }

//   void drawR(Point p1, Point p2) {
//      Graphics2D g = bi.createGraphics();
//      if(mode == 4) {
//         g.drawRect(p1.x,p2.y,p2.x-p1.x,p2.y-p1.y);
//      }
//      repaint();
//   }
    class MouseHandler implements MouseListener, MouseMotionListener{
       private Point old_point;
      @Override
      public void mouseDragged(MouseEvent e) {
         // TODO Auto-generated method stub
         if(mode == 0) {
            mouseEnd = new Point(e.getX(),e.getY());
            if(objDrag != null) {
               dragEnd = e.getPoint();
            }
            if(resize == true) {
               resizeEnd = e.getPoint();
            }
         }
         if(mode == 2) { // pen 
//            Point new_point = new Point(e.getX(),e.getY());
//            drawLine(old_point, new_point);
//            old_point = new_point;
            penS.add(e.getPoint());
         }else if(mode == 3) { // eraser
            Point new_point = new Point(e.getX(),e.getY());
            drawLine(old_point, new_point);
            old_point = new_point;
         }else if(mode == 4) { // rect
            end = new Point(e.getX(), e.getY());
         }else if(mode == 5) { // tri
            end = new Point(e.getX(), e.getY());
         }else if(mode == 6) { // tri
            end = new Point(e.getX(), e.getY());
         }else if(mode == 7) {
            end = new Point(e.getX(), e.getY());
         }else if(mode == 8) {
            end = new Point(e.getX(), e.getY());
         }else if(mode == 9) {
            end = new Point(e.getX(), e.getY());
         }else if(mode == 10) {
            end = new Point(e.getX(), e.getY());
         }else if(mode == 11) {
            end = new Point(e.getX(), e.getY());
         }
         repaint();
      }
      
      
      private void drawLine(Point p1, Point p2) {
         // TODO Auto-generated method stub
         
         Graphics2D g = bi.createGraphics();
         if(mode == 2){
            g.setColor(color);
            g.setStroke(new BasicStroke(thicknessPen));
         }else if(mode == 3){
            g.setColor(color.white);
            g.setStroke(new BasicStroke(thicknessEraser));
         }
         g.drawLine(p1.x, p1.y, p2.x, p2.y);
         repaint(); // 없애면 안그려짐 
      }

      @Override
      public void mouseMoved(MouseEvent e) {
         // TODO Auto-generated method stub
         customCursor = e.getPoint();
//         System.out.println(customCursor.getX() + " " + customCursor.getY());
         
         // 미완성 Resize
         if(objDrag != null) {
            if(Math.min(objDrag.startGetX(), objDrag.endGetX()) == customCursor.getX() && Math.min(objDrag.startGetY(), objDrag.endGetY()) == customCursor.getY()){
               setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
               resizeMode = 1;
               resize = true;
               // 왼쪽 위 대각선 // 오른쪽 아래 대각선
            }else if(Math.max(objDrag.startGetX(), objDrag.endGetX()) == customCursor.getX() && Math.max(objDrag.startGetY(), objDrag.endGetY()) == customCursor.getY()){
               resizeMode = 2;
               setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
               resize = true;
            }else if(Math.max(objDrag.startGetX(), objDrag.endGetX()) == customCursor.getX() && Math.min(objDrag.startGetY(), objDrag.endGetY()) == customCursor.getY()){
               setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
               resizeMode = 3;
               resize = true;
               // 오른쪽 위 대각선 // 왼쪽 아래 대각선
            }else if(Math.min(objDrag.startGetX(), objDrag.endGetX()) == customCursor.getX() && Math.max(objDrag.startGetY(), objDrag.endGetY()) == customCursor.getY()){
               resizeMode = 4;
               setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
               resize = true;
            }else if(((objDrag.startGetY() == customCursor.getY() || objDrag.endGetY() == (int)customCursor.getY()) || 
                 (objDrag.startGetY() == customCursor.getY()+1 || objDrag.endGetY() == (int)customCursor.getY()-1)) &&
                 (int)Math.min(objDrag.startGetX(), objDrag.endGetX()) <= (int)customCursor.getX() &&
                 (int)Math.max(objDrag.startGetX(), objDrag.endGetX()) >= (int)customCursor.getX()) { 
                resizeMode = 5;
                resize = true;
                 setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                // 위 아래 
            }else if(((objDrag.startGetX() == customCursor.getX() || objDrag.endGetX() == customCursor.getX()) ||
                 (objDrag.startGetX() == customCursor.getX()+1 || objDrag.endGetX() == customCursor.getX()-1)) &&
                 Math.min(objDrag.startGetY(), objDrag.endGetY()) <= customCursor.getY() && 
                 Math.max(objDrag.startGetY(),  objDrag.endGetY()) >= customCursor.getY()) {
               resizeMode = 6;
               setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                 resize = true;
                // 왼쪽 오른쪽
            }else {
               setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
         }else {
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
         }
      }

      @Override
      public void mouseClicked(MouseEvent e) {
         // TODO Auto-generated method stub

      }

      @Override
      public void mousePressed(MouseEvent e) {
         // TODO Auto-generated method stub
         if(mode == 0) {
            mouseStart = e.getPoint();
            if(objDrag != null) {
               dragStart = e.getPoint();
//               System.out.println(objDrag.type);
               if(objDrag.getDrag() && ((Math.min(objDrag.startGetX(), objDrag.endGetX()) <= e.getPoint().x) && (Math.max(objDrag.startGetX(), objDrag.endGetX()) >= e.getPoint().x))){
                  drag = true;
                  objDrag.setDDrag(true);
               }
//               if(objDrag.getDrag() && objDrag.getType() == 9) {
//                  System.out.println(x1+" "+ y2 +" "+ x2 +" "+y1);
//                  
//               }
               if(objDrag.getDrag() && objDrag.getType() == 9 && x1 <= e.getPoint().x && x2 >= e.getPoint().x && y1 >= e.getPoint().y && y2 <= e.getPoint().y) {
                 drag = true;
                  objDrag.setDDrag(true);
               }
//                     && (Math.min(s.startGetY(), s.endGetY()) <= e.getPoint().y) && (Math.max(s.startGetY(), s.endGetY()) >= e.getPoint().y))
            }
            if(copy == true) {
               copyStart = e.getPoint();
            }
            
            if(resize == true) {
                 resizeStart = e.getPoint();
            }
         }
         
         if(mode == 2) {//pen
//            old_point = new Point(e.getX(),e.getY());
            penS.add(e.getPoint());
         }else if(mode == 3) {
            old_point = new Point(e.getX(),e.getY());
         }else if(mode == 4) {
            start = e.getPoint();
         }else if(mode == 5) {
            start = e.getPoint();
         }else if(mode == 6) { // tri
            start = e.getPoint();
         }else if(mode == 7) {
            start = e.getPoint();
         }else if(mode == 8) {
            start = e.getPoint();
         }else if(mode == 9) {
            start = e.getPoint();
         }else if(mode == 10) {
            start = e.getPoint();
         }else if(mode == 11) {
            start = e.getPoint();
         }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
         // TODO Auto-generated method stub
         if(mode == 0) {
            end = e.getPoint();
            hiding = true;
            
            
            if(drag == false) {
               System.out.println(drag);
                for(Shape s : shape) {
                   if(s.type == 9 && Math.min(mouseStart.x, mouseEnd.x) <= x1 && Math.min(mouseStart.y, mouseEnd.y) <= y2 &&
                         Math.max(mouseStart.x, mouseEnd.x) >= x2 && Math.max(mouseStart.y, mouseEnd.y) >= y1) {
//                      System.out.println("1");
                      if(s.getDrag()) {
                         objDrag = s;
                         objDrag.setDrag(true);
                      }
                   }else if(Math.min(mouseStart.x, mouseEnd.x) <= Math.min(s.startGetX(), s.endGetX()) && 
                           Math.max(mouseStart.y, mouseEnd.y) >= Math.max(s.startGetY(), s.endGetY())) {
                        if(s.getDrag() ) {
                           objDrag = s;
                           System.out.println(objDrag);
                             objDrag.setDrag(true); // 선택된 도형 드래그 가능 상태
//                             objDrag.setDDrag(true);
                        }
                     }
                 
                  }
            }
//            if(copy == true) {
//               for(Shape s : shape) {
//                    if(Math.min(mouseStart.x, mouseEnd.x) <= Math.min(s.startGetX(), s.endGetX()) && 
//                          Math.max(mouseStart.y, mouseEnd.y) >= Math.max(s.startGetY(), s.endGetY())) {
//                       if(s.getDrag() ) {
//                          objDrag = s;
//                            objDrag.setDrag(true); // 선택된 도형 드래그 가능 상태
////                            objDrag.setDDrag(true);
//                       }
//                    }
            
//                
//                 }
//            }
            // copy object
            if(copy == true && objDrag != null && shape.size() != 0) {
               copyEnd = e.getPoint();
               
               shape.add(new Shape(Math.min(objDrag.startGetX(), objDrag.endGetX()) + (copyEnd.x - copyStart.x),
                        Math.min(objDrag.startGetY(), objDrag.endGetY()) + (copyEnd.y - copyStart.y), Math.max(objDrag.startGetX(), objDrag.endGetX()) + (copyEnd.x - copyStart.x),
                        Math.max(objDrag.startGetY(), objDrag.endGetY()) + (copyEnd.y - copyStart.y), objDrag.getType(), objDrag.getThickness(), objDrag.getColor()));
           
              repaint();
               objDrag = null;
              drag = false;
              copy =false;
            }
            
            //resize
            if(resize == true && objDrag != null && shape.size() != 0) {
               resizeEnd = e.getPoint();
               if(resizeMode == 1) {
                  
               }else if(resizeMode == 2) {
                  
               }else if(resizeMode == 3) {
                  
               }else if(resizeMode == 4) {
                  
               }else if(resizeMode == 5) { // 위 아래 
//                  Shape tmp = new Shape(Math.min(objDrag.startGetX(), objDrag.endGetX()), Math.min(objDrag.startGetY(), objDrag.endGetY()) + (resizeEnd.y - resizeStart.y), 
//                        Math.max(objDrag.startGetX(), objDrag.endGetX()),Math.max(objDrag.startGetY(), objDrag.endGetY()) + (resizeEnd.y - resizeStart.y),
//                        objDrag.getType(), objDrag.getThickness(), objDrag.getColor());
//                  shape.set(shape.indexOf(objDrag), tmp);
                  
               }else if(resizeMode == 6) { // 가로 세로
                  
               }
            }
            
            // Select 되었을 때
            if(objDrag != null && drag == true && shape.size() != 0 && !copy && !objEraser) {
               dragEnd = e.getPoint();
               if(objDrag.getDDrag()) { // 위치 이동 되었을 때
                  if(objDrag.getType() == 9) {
                    ArrayList<Point> new_point = new ArrayList<Point>();
                   for(int i = 0 ; i < shape.size(); i++ ) {
                      for(int j = 0 ; j < objDrag.penP.size(); j++) {
                        if(shape.get(i).penP.get(j) == objDrag.penP.get(j)) {
                           for(int k = 0 ; k < objDrag.penP.get(j).size()-1; k++) {
//                               new_point.add(objDrag.penP.get(j).get(k));
                              int x = (int) objDrag.penP.get(j).get(k).getX();
                              int y = (int) objDrag.penP.get(j).get(k).getY();
                              x += (dragEnd.x - dragStart.x);
                              y += (dragEnd.y - dragStart.y); 
                              Point temp = new Point();
                              temp.x = x;
                              temp.y = y;
                              new_point.add(temp);
                              
//                               objDrag.setPen(objDrag.penP.get(j).get(k).x, objDrag.penP.get(j).get(k).y, j , k);
                            }
                           objDrag.setPen(new_point, j);
                        }
                      }
                   }
                   Board.x1=111111;
                   Board.y1=0;
                   Board.x2=0;
                   Board.y2=1111111; // 그리기 
//                   objDrag.setDDrag(false);
                   objDrag.setDrag(false);
//                   objDrag = null;
                   
                  }else {
                     Shape tmp = new Shape(Math.min(objDrag.startGetX(), objDrag.endGetX()) + (dragEnd.x - dragStart.x),
                               Math.min(objDrag.startGetY(), objDrag.endGetY()) + (dragEnd.y - dragStart.y), Math.max(objDrag.startGetX(), objDrag.endGetX()) + (dragEnd.x - dragStart.x),
                               Math.max(objDrag.startGetY(), objDrag.endGetY()) + (dragEnd.y - dragStart.y), objDrag.getType(), objDrag.getThickness(), objDrag.getColor());
                      for(int i = 0 ; i < pointStore.size(); i++) {
                          if(pointStore.get(i).getPoint().get(0).x == objDrag.start().x && pointStore.get(i).getPoint().get(0).y == objDrag.start().y
                                && pointStore.get(i).getPoint().get(1).x == objDrag.end().x && pointStore.get(i).getPoint().get(1).y == objDrag.end().y) {
                             pointStore.get(i).addPoint(tmp.start(), tmp.end());
                             pointStore.get(i).printPoint();
                          }
                       } 
//                      changeObject.add(tmp); // 변경된 Object 담기 
                      stack.add(tmp);
                      shape.set(shape.indexOf(objDrag),tmp);
                       objDrag.setDrag(false);
                  }
                  
               }
               
               // 저장 
               drag = false; //  보류 
//             
            }
            repaint();
         }
         if(mode == 2) {
            penS.add(e.getPoint());
            shape.add(new Shape(new ArrayList<Point>(penS), 9, thicknessShape, colorShape));
            penS.clear();
         }else if(mode == 4) { // rect
            end = e.getPoint();
            shape.add(new Shape(start.x, start.y, end.x, end.y, 1 ,thicknessShape, colorShape));
         }else if(mode == 5){ // tri
            end = e.getPoint();
            shape.add(new Shape(start.x, start.y, end.x, end.y,2,thicknessShape, colorShape));
         }else if(mode == 6){ // circle
            end = e.getPoint();
            shape.add(new Shape(start.x, start.y, end.x, end.y,3,thicknessShape, colorShape));
         }else if(mode == 7) { // linear line
            end = e.getPoint();
            shape.add(new Shape(start.x, start.y, end.x, end.y, 7,thicknessShape, colorShape));
         }else if(mode == 8) { // dotted line
            end = e.getPoint();
            shape.add(new Shape(start.x, start.y, end.x, end.y, 8,thicknessShape, colorShape));
         }else if(mode == 9) {
            end = e.getPoint();
            shape.add(new Shape(start.x, start.y, end.x, end.y, 4,thicknessShape, colorShape));
         }else if(mode == 10) {
            end = e.getPoint();
            shape.add(new Shape(start.x, start.y, end.x, end.y,5,thicknessShape, colorShape));
         }else if(mode == 11) {
            end = e.getPoint();
            shape.add(new Shape(start.x, start.y, end.x, end.y,6,thicknessShape, colorShape));
         }
         if(mode == 4 || mode == 5 || mode == 6 || mode == 7 || mode == 8 || mode == 9 || mode == 10 || mode == 11) {
            pointStore.add(new PointStore(shape.get(shape.size()-1).start(),shape.get(shape.size()-1).end()));
            stack.add(shape.get(shape.size() -1 ));
         }
      }

      @Override
      public void mouseEntered(MouseEvent e) {
         // TODO Auto-generated method stub
         
         if(mode == 0 || mode == 4 || mode == 5 || mode == 6 || mode == 7 || mode == 8 || mode == 9
               || mode == 10|| mode == 11) {
            if(objDrag == null) {
//              setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }else {
            		
            }
         }
//         setCursor(Cursor.getPredefinedCursor(Cursor.CUSTOM_CURSOR)); 추가 기능
      }

      @Override
      public void mouseExited(MouseEvent e) {
         // TODO Auto-generated method stub
         
      }
      
   }

   
}