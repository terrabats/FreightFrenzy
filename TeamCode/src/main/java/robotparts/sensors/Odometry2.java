package robotparts.sensors;

import geometry.position.Vector;
import geometry.position.Vector2;
import robotparts.RobotPart;
import robotparts.electronics.input.IEncoder;
import util.codeseg.ExceptionCodeSeg;

import static global.General.bot;
import static robot.RobotFramework.odometryThread;

public class Odometry2 extends RobotPart {

    private IEncoder horizontalEncoder;
    private IEncoder verticalEncoder;


    private double lastHorizontalEncoderPos;
    private double lastVerticalEncoderPos;


    private final Vector2 positionOdometryCenter = new Vector2(0,0);
    private Vector2 positionRobotCenter = new Vector2(0,0);
    private final Vector2 localOdometryCenterOffset = new Vector2(10,10);
    private double heading = 0;

    private final ExceptionCodeSeg<RuntimeException> odometryUpdateCode = this::update;


    @Override
    public void init() {
        horizontalEncoder = createEncoder("bl", "cEnc", IEncoder.Type.NORMAL);
        verticalEncoder = createEncoder("bl", "rEnc", IEncoder.Type.NORMAL);
        lastHorizontalEncoderPos = horizontalEncoder.getPos();
        lastVerticalEncoderPos = verticalEncoder.getPos();
        odometryThread.setExecutionCode(odometryUpdateCode);
    }

    public double getHorizontalEncoderPosition(){
        return horizontalEncoder.getPos();
    }

    public double getVerticalEncoderPosition(){
        return verticalEncoder.getPos();
    }

    private double getLocalHorizontalDelta(){
        double delta = getHorizontalEncoderPosition() - lastHorizontalEncoderPos;
        lastHorizontalEncoderPos = getHorizontalEncoderPosition();
        return ticksToCm(delta);
    }

    private double getLocalVerticalDelta(){
        double delta = getVerticalEncoderPosition() - lastVerticalEncoderPos;
        lastVerticalEncoderPos = getVerticalEncoderPosition();
        return ticksToCm(delta);
    }

    public double ticksToCm(double ticks) {
        return ticks/8192 * 3.5 * Math.PI;
    }

    private void updateHeading(){
        heading = bot.gyroSensors.getLeftHeadingRad();
    }

    public void updatePosition(){
        Vector2 localDelta = new Vector2(getLocalHorizontalDelta(), getLocalVerticalDelta());
        Vector2 globalDelta = localDelta.rotate(-heading);
        positionOdometryCenter.add(globalDelta);
        Vector2 globalOdometryCenterOffset = localOdometryCenterOffset.rotate(heading);
        positionRobotCenter = positionOdometryCenter.add(globalOdometryCenterOffset);
    }

    public void update(){
        updateHeading();
        updatePosition();
    }

    public double getX(){
        return positionRobotCenter.getX();
    }
    public double getY(){
        return positionRobotCenter.getY();
    }
    public double getHeading(){
        return heading;
    }

    public double[] getPose(){
        return new double[]{getX(), getY(), getHeading()};
    }

}
