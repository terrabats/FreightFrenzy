package autoutil.display;


import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import autoutil.Generator;
import autoutil.paths.Path;
import autoutil.paths.PathSegment;
import geometry.position.Pose;

import static java.lang.Math.*;

/**
 * NOTE: Uncommented
 */

public class Display extends JPanel {

    // TOD3 NEW
    // Make this a actual frame work that lets you draw things

    private final int height = 700;
    private final int width = 700;
    private final int xScale = 5;
    private final int yScale = 5;
    private Path pathToDisplay;

    public void genTestPlane(){
        Generator generator = new Generator();
        generator.moveTo(0,0, 0);
        generator.moveTo(30, 0, PI);
        generator.moveTo(10,5,PI);
        generator.moveTo(30, 5, PI);
        generator.moveTo(30,30, PI/2);
        generator.moveTo(0, 0, -PI/2);
        generator.moveTo(25, 20, 3 * PI/2);

        //(10, 5, pi) , (30, 5, pi), (30, 30, pi/2), (0, 0, -pi/2) , (25, 20, 3pi/2)

        pathToDisplay = generator.done();

    }

    @Override
    public void paintComponent(Graphics g) {
        for (PathSegment ps : pathToDisplay.segments) {
            for (Pose p : ps.points) {
                g.fillOval(pX(p.p.x), pY(p.p.y), 5, 5);
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
        Display display = new Display();
        display.genTestPlane();
        window.add(display);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(display.height, display.width);
        window.setVisible(true);
    }
}
