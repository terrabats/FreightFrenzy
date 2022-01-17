package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import automodules.stage.Exit;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stage;
import automodules.stage.Stop;
import global.Constants;
import robotparts.RobotPart;
import robotparts.electronics.positional.PMotor;

import static global.General.bot;

public class Lift extends RobotPart {
    /**
     * Lift positional motor
     */
    private PMotor li;

    /**
     * Init method creates the lift motor and resets the encoder (done internally)
     */
    @Override
    public void init() {
        li = createPMotor("li", DcMotorSimple.Direction.FORWARD);
    }

    /**
     * Move the lift motor at a certain power
     * @param p
     */
    public void move(double p){
        li.setPower(p + Constants.LIFT_REST_POW);
    }

    /**
     * Gets the position of the lift (in ticks)
     * @return ticks
     */
    public double getLiftPos(){
        return li.getPosition();
    }

    /**
     * Gets the current power of the motor
     * @return power
     */
    public double getPower() { return li.getPower(); }

    /**
     * Resets the lift encoder
     */
    public void resetEncoder(){li.resetPosition();}

    /**
     * Sets the lift target in cm
     * @param h
     */
    public void setTarget(double h){
        li.setPosition(h*Constants.LIFT_CM_TO_TICKS);
    }

    /**
     * Gets the target of the lift (in cm)
     * @return
     */
    public double getTarget() {
        return li.getTarget()/Constants.LIFT_CM_TO_TICKS;
    }

    /**
     * Stops and resets the mode of the positional motor
     */
    public void stopAndResetMode() {
        li.stopAndReset();
    }

    /**
     * Has the positional motor reached the target position?
     * @return hasReachedTarget
     */
    public boolean hasReachedTarget(){
        return li.hasReachedPosition();
    }

    /**
     * Initial to set the target
     * @param height
     * @return
     */
    public Initial initialSetTarget(double height){return new Initial(() -> setTarget(height));}

    /**
     * Set the power of the lift in a main
     * @param power
     * @return
     */
    public Main main(double power){return new Main(() -> move(power));}

    /**
     * Exit when the lift is down
     * NOTE: Uses the touch sensor
     * @return
     */
    public Exit exitDown(){return new Exit(() -> bot.touchSensor.isOuttakePressingTouchSensor());}

    /**
     * Exit when the lift reached the target
     * @return
     */
    public Exit exitReachedTarget(){return new Exit(this::hasReachedTarget);}

    /**
     * Stop the lift motor
     * @return
     */
    public Stop stop(){return new Stop(() -> move(0));}

    /**
     * Stop the lift and reset the mode
     * @return
     */
    public Stop stopEncoder(){return new Stop(this::stopAndResetMode);}

    /**
     * Lift for a certain time
     * @param power
     * @param time
     * @return
     */
    public Stage liftTime(double power, double time){return new Stage(
            usePart(),
            main(power),
            exitTime(time),
            stop(),
            returnPart()
    );}

    /**
     * Lift to a certain position
     * @param power
     * @param height
     * @return
     */
    public Stage liftEncoder(double power, double height){return new Stage(
            usePart(),
            initialSetTarget(height),
            main(power),
            exitReachedTarget(),
            stopEncoder(),
            returnPart()
    );}

}
