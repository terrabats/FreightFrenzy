package robotparts.sensors;

import geometry.position.Vector2;
import robotparts.electronics.input.IEncoder;

import static global.General.bot;
import static robot.RobotFramework.odometryThread;

public class TwoOdometry extends Odometry {

    private IEncoder horizontalEncoder;
    private IEncoder verticalEncoder;


    private double lastHorizontalEncoderPos;
    private double lastVerticalEncoderPos;


    private final Vector2 positionOdometryCenter = new Vector2(0,0);
    private Vector2 positionRobotCenter = new Vector2(0,0);
    private final Vector2 localOdometryCenterOffset; // = new Vector2(3,-0.5);
    private double heading = 0;

    public TwoOdometry(double odo1_to_center_x, double odo2_to_center_y) {
        super(odo1_to_center_x);
        localOdometryCenterOffset = new Vector2(odo1_to_center_x, odo2_to_center_y);
    }


    @Override
    public void init() {
        horizontalEncoder = createEncoder("br", "cEnc", IEncoder.EncoderType.NORMAL);
        verticalEncoder = createEncoder("bl", "rEnc", IEncoder.EncoderType.NORMAL);
        lastHorizontalEncoderPos = horizontalEncoder.getPos();
        lastVerticalEncoderPos = verticalEncoder.getPos();
        odometryThread.setExecutionCode(this::update);
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
        heading = bot.gyroSensors.getRightHeadingRad();
    }

    public void updatePosition(){
        Vector2 localDelta = new Vector2(getLocalHorizontalDelta(), getLocalVerticalDelta());
        Vector2 globalDelta = localDelta.getRotated(-heading);
        positionOdometryCenter.add(globalDelta);
        Vector2 globalOdometryCenterOffset = localOdometryCenterOffset.getRotated(heading);
        positionRobotCenter = positionOdometryCenter.getAdded(globalOdometryCenterOffset).getAdded(localOdometryCenterOffset.getNegative());
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

    @Override
    public double[] getPose(){
        return new double[]{getX(), getY(), getHeading()};
    }

}