package unittests.display;


import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import autoutil.Generator;
import autoutil.paths.Path;
import autoutil.paths.PathSegment;
import geometry.Pose;

import static java.lang.Math.*;


public class Display extends JPanel {

    // TODO NEW
    // Make this a actual frame work that lets you draw things

    private final int height = 700;
    private final int width = 700;
    private final int xScale = 3;
    private final int yScale = 3;
    private Path pathToDisplay;

    public void genTestPlane(){
        Generator generator = new Generator();
        generator.addNewAbsPos(0,0,PI/2);
        generator.addNewAbsPos(-80, 40, PI/8);

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
