package debugging.display;


import static java.lang.Math.PI;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import autoutil.executors.MovementExecutor;
import geometry.circles.AngleType;
import geometry.position.Pose;

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
        movementExecutor = new MovementExecutor(0, 0, 0, AngleType.RADIANS);
        movementExecutor.addSetpoint(70, 30, 0, AngleType.RADIANS);
        movementExecutor.addSetpoint(-50, 30, PI, AngleType.RADIANS);
        movementExecutor.addSetpoint(-65, 30, PI, AngleType.RADIANS);
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
