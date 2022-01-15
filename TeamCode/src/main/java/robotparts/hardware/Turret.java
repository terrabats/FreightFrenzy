package robotparts.hardware;

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

import static global.General.bot;
import static global.General.fieldSide;

public class Turret extends RobotPart {
    /**
     * Turret positional motor
     */
    private PMotor tr;

    /**
     * Create the turret motor and reset it (done internally)
     */
    @Override
    public void init() {
        tr = createPMotor("tr", DcMotorSimple.Direction.FORWARD);
    }

    /**
     * Move the motor at a power
     * @param power
     */
    public void move(double power) {
        tr.setPower(power);
    }

    /**
     * Get the turret position
     * @return position
     */
    public double getTurretPos(){
        return tr.getPosition();
    }

    /**
     * Reset the turret encoder (motor encoder)
     */
    public void resetEncoder(){tr.resetPosition();}

    /**
     * Set the target angle for the turret
     * @param angle
     */
    public void setTarget(double angle){
        tr.setPosition(angle*Constants.TURRET_ANGLE_DEG_TO_TICKS);
    }

    /**
     * Has the turret reached the target position
     * @return hasReached
     */
    public boolean hasReachedTarget(){
        return tr.hasReachedPosition();
    }

    /**
     * Get the target position based on the field side
     * @return
     */
    public double getTargetPos(){
        if(fieldSide != null) {
            if (fieldSide.equals(FieldSide.BLUE)) {
                return Constants.BLUE_SIDE_TURRET_ANGLE;
            } else if (fieldSide.equals(FieldSide.RED)) {
                return Constants.RED_SIDE_TURRET_ANGLE;
            }
        }
        return 0;
    }

    /**
     * Stop and reset the mode
     */
    public void stopAndResetMode() {
        tr.stopAndReset();
    }

    /**
     * Automobile methods
     */
    public Initial initialSetTarget(double angle){return new Initial(() -> setTarget(angle));}
    public Initial initialSetFieldSideTarget(){return new Initial(() -> setTarget(getTargetPos()));}

    public Main main(double power){return new Main(()-> move(power));}
    public Exit exitReachedTarget(){return new Exit(this::hasReachedTarget);}

    public Stop stop(){return new Stop(()-> bot.turret.move(0));}

    public Stop stopEncoder(){return new Stop(this::stopAndResetMode);}

    public Stage turretEncoder(double power, double angle){return new Stage(
            usePart(),
            initialSetTarget(angle),
            main(power),
            exitReachedTarget(),
            stopEncoder(),
            returnPart()
    );}

    public Stage turretEncoderTarget(double power){return new Stage(
            usePart(),
            initialSetFieldSideTarget(),
            main(power),
            exitReachedTarget(),
            stopEncoder(),
            returnPart()
    );}


}
