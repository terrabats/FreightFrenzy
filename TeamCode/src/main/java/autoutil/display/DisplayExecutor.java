package autoutil.display;


import static java.lang.Math.PI;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import autoutil.MovementExecutor;
import geometry.AngleType;
import geometry.Pose;

/**
 * NOTE: Uncommented
 */

public class DisplayExecutor extends JPanel {

    private final int height = 1000;
    private final int width = 1000;
    private final int xScale = 5;
    private final int yScale = 5;

    private MovementExecutor movementExecutor;

    public void genTestPlane(){
        movementExecutor = new MovementExecutor(0, 0, PI/2, AngleType.RADIANS);
        movementExecutor.addSetpoint(60, 60, PI/2, AngleType.RADIANS);
        movementExecutor.addSetpoint(0, 0, 0, AngleType.RADIANS);
        movementExecutor.complete();
    }

    @Override
    public void paintComponent(Graphics g) {
        for (ArrayList<Pose> poses : movementExecutor.paths) {
            for (Pose p : poses) {
                g.fillOval(pX(p.p.x), pY(p.p.y), 5, 5);
                System.out.println(p.p.x + " " + p.p.y + " " + p.ang);
            }
            System.out.println("---");
        }
        System.out.println("---");
        System.out.println("---");
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
