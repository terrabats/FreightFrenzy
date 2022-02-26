package display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import geometry.circles.AngleType;
import geometry.position.Line;
import geometry.position.Point;
import geometry.position.Pose;
import geometry.position.Vector;

public class Drawer extends JPanel {
    protected Graphics2D g;
    protected final int pointSize = 5;
    protected final Color pointColor = Color.BLACK;
    protected final int lineWidth = 2;
    protected final Color lineColor = Color.GRAY;
    protected final int poseLength = 10;
    protected final Color poseColor = Color.DARK_GRAY;
    protected final int arcWidth = 2;
    protected final Color arcColor = new Color(122, 56, 3);
    protected final int circleWidth = 2;
    protected final Color circleColor = new Color(72, 76, 3);

    // TODO NEW
    // Finish this


    public void drawPoint(Point p){
        g.setColor(pointColor);
        g.fillOval((int) p.x, (int) p.y, pointSize, pointSize);
    }
    public void drawLine(Line l){
        g.setColor(lineColor);
        g.setStroke(new BasicStroke(lineWidth));
        g.drawLine((int) l.p1.x, (int) l.p1.y, (int) l.p2.x, (int) l.p2.y);
    }
    public void drawPose(Pose p){
        g.setColor(poseColor);
        g.setStroke(new BasicStroke(lineWidth));
        Vector poseLine = new Vector(poseLength,p.ang, AngleType.RADIANS);
        double offset = (lineWidth+pointSize)/2.0;
        g.drawLine((int) (p.p.x+offset), (int) (p.p.y+offset), (int) (p.p.x + poseLine.getX()+offset), (int) (p.p.y + poseLine.getY()+offset));
        drawPoint(p.p);
    }
    public void drawCircularArc(Point c, double r, double st, double en, AngleType angleType){
        g.setColor(arcColor);
        g.setStroke(new BasicStroke(arcWidth));
        Vector startLine = new Vector(r, st, angleType);
        Vector endLine = new Vector(r, en, angleType);
        g.drawArc((int) c.x, (int) c.y, (int) r,(int) r, (int) startLine.getAngle(AngleType.DEGREES), (int) endLine.getAngle(AngleType.DEGREES));
    }
    public void drawCircle(Point c, double r){
        g.setColor(circleColor);
        g.setStroke(new BasicStroke(circleWidth));
        g.drawOval((int) c.x, (int) c.y, (int) r,(int) r);
    }

    @Override
    public void paintComponent(Graphics g) {
        this.g = (Graphics2D) g;
        drawPoint(new Point(100,100));
        drawLine(new Line(new Point(0,0), new Point(60,60)));
        drawPose(new Pose(new Point(200,200),90, AngleType.DEGREES));
        drawCircularArc(new Point(100,200),40, 0,90, AngleType.DEGREES);
        drawCircle(new Point(200, 200), 100);
    }


    public static void drawWindow(int width, int height, String name){
        JFrame window = new JFrame(name);
        Drawer drawer = new Drawer();
        window.add(drawer);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(height, width);
        window.setVisible(true);
    }
}
