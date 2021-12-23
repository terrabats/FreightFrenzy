package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import automodules.stage.Exit;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stop;
import elements.FieldSide;
import global.Constants;
import robotparts.RobotPart;
import robotparts.electronics.IEncoder;
import robotparts.electronics.PMotor;

import static global.General.bot;
import static global.General.fieldSide;

public class Turret extends RobotPart {
    private PMotor tr;
    private IEncoder trEnc;

    @Override
    public void init() {
        tr = createPMotor("tr", DcMotorSimple.Direction.FORWARD);
        trEnc = createEncoder("tr", "trEnc", IEncoder.Type.MOTOR);
        resetEncoder();
    }

    public void move(double power) {
        tr.setPower(power);
    }

    public double getTurretPos(){
        return trEnc.getPos();
    }


    public void resetEncoder(){trEnc.reset();}

    public void setTarget(double angle){
        tr.setPosition(angle*Constants.TURRET_ANGLE_DEG_TO_TICKS);
    }

    public boolean hasReachedTarget(){
        return tr.hasReachedPosition();
    }

    public double getTurretTargetPos(){
        if(fieldSide != null) {
            if (fieldSide.equals(FieldSide.BLUE)) {
                return Constants.BLUE_SIDE_TURRET_ANGLE;
            } else if (fieldSide.equals(FieldSide.RED)) {
                return Constants.RED_SIDE_TURRET_ANGLE;
            }
        }
        return 0;
    }

    public void stopAndResetMode() {
        tr.stopAndReset();
    }

    public Initial initialSetTarget(double angle){return new Initial(() -> setTarget(angle));}
    public Initial initialSetFieldSideTarget(){return new Initial(() -> setTarget(getTurretTargetPos()));}

    public Main main(double power){return new Main(()-> move(power));}
    public Exit exitReachedTarget(){return new Exit(this::hasReachedTarget);}

    public Stop stop(){return new Stop(()-> bot.turret.move(0));}

    public Stop stopEncoder(){return new Stop(this::stopAndResetMode);}




}
