package application;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.Color;
//import com.sun.prism.paint.Color;

public class Shape implements Serializable {
	Point start = new Point();
	Point end = new Point();
	ArrayList<ArrayList<Point>> penP = new ArrayList<ArrayList<Point>>();
	ArrayList<ArrayList<Point>> eraserP = new ArrayList<ArrayList<Point>>();
	boolean drag = false;
	boolean realDrag = false;
	int type = 0; // 1: rectangle 2: triangle 3: circle 4~6 fill 7: linear line 8: dotted line
	int thicknessShape = 2 ;
	Color color = Color.BLACK;
	Shape(ArrayList<Point> point, int type, int thickness, Color color){
		penP.add(point);
		this.type = type;
		this.thicknessShape = thickness;
		this.color =color; 
	}
	Shape(int startX, int startY, int endX, int endY, int type, int thickness, Color color){
		start.x = startX;
		start.y = startY;
		end.x = endX;
		end.y = endY;
		this.type = type;
		this.thicknessShape = thickness;
		this.color = color;
	}
	public void setPen(ArrayList<Point> S, int idx) {
		penP.set(idx, S);
	}
//	public void setPen(int x ,int y, int xdx, int ydx) {
//		penP.get(xdx).get(ydx).x = x;
//		penP.get(xdx).get(ydx).y = y;
//	}
	public void setStartPoint(Point t) {
		start = t;
	}
	public void setEndPoint(Point t) {
		end = t;
	}
	public void setDDrag(boolean t) {
		realDrag = t;
	}
	public boolean getDDrag() {
		return realDrag;
	}
	public void setDrag(boolean t) {
		drag = t;
	}
	public boolean getDrag() {
		return drag;
	}
	public int startGetX() {
		return start.x;
	}
	public int endGetX() {
		return end.x;
	}
	public int startGetY() {
		return start.y;
	}
	public int endGetY() {
		return end.y;
	}
	public Point start() {
		return start;
	}
	public Point end() {
		return end;
	}
	public int getType() {
		return type;
	}
	public int getThickness() {
		return thicknessShape;
	}
	public Color getColor() {
		return color;
	}
}