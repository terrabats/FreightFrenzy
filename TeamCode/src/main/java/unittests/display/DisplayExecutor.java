package unittests.display;


import static java.lang.Math.PI;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import autoutil.Executor;
import autoutil.Generator;
import autoutil.paths.Path;
import autoutil.paths.PathSegment;
import geometry.AngleType;
import geometry.Pose;


public class DisplayExecutor extends JPanel {

    private final int height = 1000;
    private final int width = 1000;
    private final int xScale = 10;
    private final int yScale = 10;

    private Executor executor;

    public void genTestPlane(){
        executor = new Executor(0, 0, PI/2, AngleType.RADIANS);
        executor.addWaypoint(10, 10, PI, AngleType.RADIANS);
        executor.addWaypoint(0, 20, 0, AngleType.RADIANS);
        executor.addSetpoint(20, 7, 45, AngleType.DEGREES);
        executor.addWaypoint(0, 0, PI/4, AngleType.RADIANS);
        executor.addSetpoint(-10, 0, -PI/8, AngleType.RADIANS);
        executor.complete();
    }

    @Override
    public void paintComponent(Graphics g) {
        for (ArrayList<Pose> poses : executor.paths) {
            for (Pose p : poses) {
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
        DisplayExecutor display = new DisplayExecutor();
        display.genTestPlane();
        window.add(display);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(display.height, display.width);
        window.setVisible(true);
    }
}
