package robotparts.electronics;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.Base64;

import global.Constants;
import static global.General.*;
import robotparts.Electronic;

public class PMotor extends Electronic {
    /**
     * DcMotor object and related parameters
     */
    private final DcMotor motor;
    private final DcMotorSimple.Direction direction;
    private final DcMotor.ZeroPowerBehavior zeroPowerBehavior;
    private final IEncoder motorEncoder;

    /**
     * Constructor to create a pmotor
     * @param m
     * @param dir
     * @param zpb
     * @param mode
     */
    public PMotor(DcMotor m, DcMotor.Direction dir, DcMotor.ZeroPowerBehavior zpb, DcMotor.RunMode mode){
        motor = m;
        direction = dir;
        zeroPowerBehavior = zpb;

        motor.setDirection(direction);
        motor.setZeroPowerBehavior(zeroPowerBehavior);
        motor.setMode(mode);

        motor.setPower(0);

        motorEncoder = new IEncoder(motor, IEncoder.Type.MOTOR);

        motorEncoder.reset();
    }

    /**
     * Set the power of the pmotor
     * @param p
     */
    public void setPower(double p){
        if(access.isAllowed()){ motor.setPower(p); }
    }

    /**
     * Set the position in ticks to move to
     * @param pos
     */
    public void setPosition(double pos){
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setTargetPosition((int)(pos));
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * Get the target position in ticks
     * @return target
     */
    public double getTarget() {
        return motor.getTargetPosition();
    }

    /**
     * Has the motor reached the target
     * @return if the motor is busy
     */
    public boolean hasReachedPosition(){
        return !motor.isBusy();
    }

    /**
     * Get the position of the motor
     * @return ticks
     */
    public double getPosition(){
        return motorEncoder.getPos();
    }

    /**
     * Gets the logical direction of the pmotor
     * @return direction
     */
    public DcMotorSimple.Direction getDirection(){return direction;}

    /**
     * Stop and reset the mode of the pmotor
     */
    public void stopAndReset(){
        setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Reset the position of the pmotor
     */
    public void resetPosition(){
        motorEncoder.reset();
    }

    /**
     * Sets the power of the motor to 0
     * NOTE: This should only be called in a thread that has access to use the robot
     */
    public void halt(){ setPower(0); }
}
