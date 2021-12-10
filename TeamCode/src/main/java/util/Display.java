package util;


import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import autoutil.Generator;
import autoutil.paths.Path;
import autoutil.paths.PathSegment;
import geometry.Pose;

import static java.lang.Math.*;


public class Display extends JPanel {
    private final int height = 700;
    private final int width = 700;
    private final int xScale = 3;
    private final int yScale = 3;
    private Path pathToDisplay;

    public void genTestPlane(){
        Generator generator = new Generator();
        generator.addNewAbsPos(0, 0, PI/2);
        generator.addNewAbsPos(-30, 70, PI/4);

        pathToDisplay = generator.done();


//        plane.add(new Circle(new Point(0,0), 20));
//        plane.add(new Line(new Point(0,0), new Point(10,10)));
    }

    @Override
    public void paintComponent(Graphics g) {
        for (PathSegment ps : pathToDisplay.segments) {
//            System.out.println(ps.points.size());
            for (Pose p : ps.points) {
//                g.drawLine(pX(p.p.x) + 300, pY(p.p.y) + 300, pX(p.p.x) + 300, pY(p.p.y) + 300);
                g.fillOval(pX(p.p.x) + width/2, pY(p.p.y) + height/2, 5, 5);
            }
        }
//        for(Line l: plane.getLines()){
//            g.drawLine(pX(l.p1.x), pY(l.p1.y), pX(l.p2.x), pY(l.p2.y));
//        }
    }

    private int pX(double x){
        return (int)(x*xScale);
    }
    private int pY(double y){
        return (int)(y*yScale);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Coordinate Frame 1");
        Display display = new Display();
        display.genTestPlane();
        window.add(display);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(display.height, display.width);
        window.setVisible(true);

        System.out.println("-- " + display.pathToDisplay.segments.size());
    }
}
