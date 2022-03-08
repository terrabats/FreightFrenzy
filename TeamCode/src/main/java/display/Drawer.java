package display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import geometry.circles.AngleType;
import geometry.polygons.Rect;
import geometry.polygons.Triangle;
import geometry.position.Line;
import geometry.position.Point;
import geometry.position.Pose;
import geometry.position.Vector;
import util.ExceptionCatcher;

public class Drawer extends JPanel {
    protected Graphics2D g;
    protected final int pointSize = 5;
    protected Color pointColor = Color.BLACK;
    protected final int lineWidth = 2;
    protected Color lineColor = Color.GRAY;
    protected final int poseLength = 10;
    protected final Color poseColor = Color.DARK_GRAY;
    protected final int arcWidth = 2;
    protected final Color arcColor = new Color(122, 56, 3);
    protected final int circleWidth = 2;
    protected final Color circleColor = new Color(72, 76, 3);

    // TOD4 NEW
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

    public void drawField(){
        final BufferedImage image;
//        System.out.println();
//        File f = new File("display/ff.png");
//        System.out.println(System.getProperty("user.dir")+ "\\TeamCode\\src\\main\\java\\display");
        File f = new File(System.getProperty("user.dir")+ "\\TeamCode\\src\\main\\java\\display\\ff2.png");
//        System.out.println("Heei");
//        if(f.exists()) {
//            System.out.println("Hiii");
//        }
        try {
//            File f2 = new File("ff.png");
//            if(f.exists() && !f.isDirectory()) {
//
//            }
            image = ImageIO.read(f);
            g.drawImage(image, 0, 0, 780, 770, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public void whatToDraw(){
//        Triangle triangle = new Triangle(new Point(200,240), new Point(300,180), new Point(260,160));
//        double area = triangle.area();
//        Rect bbox = triangle.boundingbox();
//        System.out.println(area);
//        System.out.println(bbox.toString());
//        Point p1 = triangle.points.get(0);
//        Point p2 = triangle.points.get(1);
//        Point p3 = triangle.points.get(2);
//        drawLine(new Line(p1, p2));
//        drawLine(new Line(p2, p3));
//        drawLine(new Line(p1, p3));
//        drawPoint(p1);
//        drawPoint(p2);
//        drawPoint(p3);
//        Point r1 = new Point(bbox.getX1(), bbox.getY1());
//        Point r2 = new Point(bbox.getX1(), bbox.getY2());
//        Point r3 = new Point(bbox.getX2(), bbox.getY2());
//        Point r4 = new Point(bbox.getX2(), bbox.getY1());
//        pointColor = Color.RED;
//        lineColor = Color.BLUE;
//        drawLine(new Line(r1, r2));
//        drawLine(new Line(r2, r3));
//        drawLine(new Line(r3, r4));
//        drawLine(new Line(r4, r1));
//        drawPoint(r1);
//        drawPoint(r2);
//        drawPoint(r3);
//        drawPoint(r4);
//    }


    public void define(){
        drawField();
//        System.out.println("Hi");
    }

    @Override
    public void paintComponent(Graphics g) {
        this.g = (Graphics2D) g;
//        System.out.println("Hi");
        define();
//        whatToDraw();
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
