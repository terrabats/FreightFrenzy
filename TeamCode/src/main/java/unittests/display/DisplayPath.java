package unittests.display;


import static java.lang.Math.PI;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import autoutil.Generator;
import autoutil.paths.Path;
import autoutil.paths.PathSegment;
import geometry.Pose;


public class DisplayPath extends JPanel {

    private final int height = 700;
    private final int width = 700;
    private final int xScale = 2;
    private final int yScale = 2;
    private Path pathToDisplay;

    public void genTestPlane(){
        Generator generator = new Generator();
//        generator.moveTo(0,0, PI/2);
        generator.moveTo(30, 20, 0);
        generator.moveTo(-20,30,-PI/2);
//        generator.moveTo(-100, 40, PI);

        //(10, 5, pi) , (30, 5, pi), (30, 30, pi/2), (0, 0, -pi/2) , (25, 20, 3pi/2)

        pathToDisplay = generator.done();

    }

    @Override
    public void paintComponent(Graphics g) {
        for (PathSegment ps : pathToDisplay.segments) {
//            for (int i = 0; i < 2 * ps.points.size()/3; i++) {
//                g.fillOval(pX(ps.points.get(i).p.x), pY(ps.points.get(i).p.y), 5, 5);
//            }
            for (Pose p : ps.points) {
                g.fillOval(pX(p.p.x), pY(p.p.y), 5, 5);
                System.out.println(p.p.x + " " + p.p.y + " " + p.ang);
            }
        }
    }

    private int pX(double x){
        return (int)(x*xScale) + width/2;
    }
    private int pY(double y){
        return height/2 - (int)(y*yScale);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Coordinate Frame 1");
        DisplayPath display = new DisplayPath();
        display.genTestPlane();
        window.add(display);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(display.height, display.width);
        window.setVisible(true);
    }
}
