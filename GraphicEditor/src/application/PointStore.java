package application;

import java.awt.Point;
import java.util.ArrayList;

public class PointStore {
	private ArrayList<ArrayList<Point>> point;
	PointStore(Point start, Point end){
		point = new ArrayList<ArrayList<Point>>();
		ArrayList<Point> tmp = new ArrayList<Point>();
		tmp.add(start);
		tmp.add(end);
		point.add(tmp);
	}
	
	public void addPoint(Point start, Point end) {
		ArrayList<Point> tmp = new ArrayList<Point>();
		tmp.add(start);
		tmp.add(end);
		point.add(tmp);
	}
	public ArrayList<Point> getPoint(){
		return point.get(point.size() -1);
	}
	public ArrayList<ArrayList<Point>> pointReturn(){
		return point;
	}
	public void printPoint() {
		for(int i = 0 ; i< point.size(); i++) {
			System.out.println(point.get(i).get(0) + "  " + point.get(i).get(1));
		}
	}
}
