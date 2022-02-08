package robotparts.hardware;

import static global.General.bot;
import static global.General.fault;
import static global.General.fieldSide;
import static global.General.mainUser;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import automodules.stage.Exit;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stage;
import automodules.stage.Stop;
import elements.FieldSide;
import global.Constants;
import robotparts.RobotPart;
import robotparts.electronics.positional.PMotor;

public class Lift extends RobotPart {
    /**
     * Turret positional motor
     */
    private PMotor li;

    /**
     * Create the turret motor and reset it (done internally)
     */
    @Override
    public void init() {
        li = createPMotor("li", DcMotorSimple.Direction.REVERSE);
    }

    /**
     * Move the motor at a power
     * @param power
     */
    public void move(double power) {
        li.setPower(power);
    }

    public void setToRestPow() {
        double liftPos = getLiftPos()/Constants.TURRET_ANGLE_DEG_TO_TICKS - 45;
        liftPos *= Math.PI/180;
        move(Math.cos(liftPos) * 0.1);
    }

    /**
     * Gets the current power of the motor
     * @return power
     */
    public double getPower() { return li.getPower(); }

    /**
     * Gets the target of the lift (in cm)
     * @return
     */
    public double getTarget() {
        return li.getTarget()/Constants.LIFT_CM_TO_TICKS;
    }

    /**
     * Stop the lift motor
     * @return
     */
    public Stop stop(){return new Stop(() -> move(0));}

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
     * Get the turret position
     * @return position
     */
    public double getLiftPos(){
        return li.getPosition();
    }

    /**
     * Reset the turret encoder (motor encoder)
     */
    public void resetEncoder(){li.resetPosition();}

    /**
     * Set the target angle for the turret
     * @param angle
     */
    public void setTarget(double angle){
        li.setPosition(angle * Constants.TURRET_ANGLE_DEG_TO_TICKS);
    }

    /**
     * Has the turret reached the target position
     * @return hasReached
     */
    public boolean hasReachedTarget(){
        return li.hasReachedPosition();
    }

    /**
     * Stop and reset the mode
     */
    public void stopAndResetMode() {
        li.stopAndReset();
    }

    /**
     * Automobile methods
     */
    public Initial initialSetTarget(double angle){return new Initial(() -> setTarget(angle));}

    public Main main(double power){return new Main(()-> move(power));}
    public Exit exitReachedTarget(){return new Exit(this::hasReachedTarget);}

    public Stop stopEncoder(){return new Stop(this::stopAndResetMode);}

    public Stage liftEncoder(double power, double angle){ return new Stage(
            usePart(),
            initialSetTarget(angle),
            main(power),
            exitReachedTarget(),
            stopEncoder(),
            returnPart()
    );}

    public Stage liftEncoderAndIntake(double power, double angle){ return new Stage(
            usePart(),
            bot.intake.usePart(),
            initialSetTarget(angle),
            bot.intake.main(1),
            main(power),
            exitReachedTarget(),
            stopEncoder(),
            bot.intake.stop(),
            bot.intake.returnPart(),
            returnPart()
    );}
}