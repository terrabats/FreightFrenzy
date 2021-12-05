package util;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import geometry.Circle;
import geometry.CoordinatePlane;
import geometry.GeometryObject;
import geometry.Line;
import geometry.Point;


public class Display extends JPanel {
    private final int height = 800;
    private final int width = 800;
    private final int xScale = 4;
    private final int yScale = 4;
    private CoordinatePlane plane = new CoordinatePlane();

    public void genTestPlane(){
        plane.add(new Circle(new Point(0,0), 20));
        plane.add(new Line(new Point(0,0), new Point(10,10)));
    }

    @Override
    public void paintComponent(Graphics g) {
        for(Line l: plane.getLines()){
            g.drawLine(pX(l.p1.x), pY(l.p1.y), pX(l.p2.x), pY(l.p2.y));
        }
    }

    private int pX(double x){
        return (int)(x*xScale);
    }
    private int pY(double y){
        return (int)(y*yScale);
    }

//    public static void main(String[] args) {
//        JFrame window = new JFrame("Coordinate Frame 1");
//        Display display = new Display();
//        display.genTestPlane();
//        window.add(display);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setSize(display.height, display.width);
//        window.setVisible(true);
//    }
}
