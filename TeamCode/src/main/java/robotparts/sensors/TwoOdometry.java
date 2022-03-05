package robotparts.sensors;

import geometry.position.Vector2;
import robotparts.electronics.input.IEncoder;
import robotparts.unused.TankOdometry;

import static global.General.bot;
import static robot.RobotFramework.odometryThread;

public class TwoOdometry extends TankOdometry {

    private IEncoder horizontalEncoder;
    private IEncoder verticalEncoder;


    private double lastHorizontalEncoderPos;
    private double lastVerticalEncoderPos;


    private final Vector2 positionOdometryCenter = new Vector2(0,0);
    private Vector2 positionRobotCenter = new Vector2(0,0);
    private final Vector2 localOdometryCenterOffset; // = new Vector2(3,-0.5);
    private double heading = 0;

    public TwoOdometry() {
        super(3.0);
        localOdometryCenterOffset = new Vector2(3.0, -0.5);
    }


    @Override
    public void init() {
        horizontalEncoder = createEncoder("bl", "cEnc", IEncoder.EncoderType.NORMAL);
        verticalEncoder = createEncoder("fl", "rEnc", IEncoder.EncoderType.NORMAL);
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
        heading = bot.gyro.getRightHeadingRad();
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

    public double getCurX(){
        return positionRobotCenter.getX();
    }
    public double getCurY(){
        return positionRobotCenter.getY();
    }
    public double getCurThetaRad(){
        return heading;
    }

    @Override
    public double[] getPose(){
        return new double[]{getCurX(), getCurY(), getCurThetaRad()};
    }

}