package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import elements.FieldSide;
import global.Constants;
import robotparts.RobotPart;
import robotparts.custom.Encoder;

import static global.General.fault;
import static global.General.fieldSide;
import static java.lang.Math.*;

public class Turret extends RobotPart {
    private DcMotor tr;
    private Encoder trEnc;

    @Override
    public void init() {
        tr = createMotor("tr", DcMotorSimple.Direction.FORWARD);
        trEnc = createEncoder("tr", "trEnc", Encoder.Type.NORMAL);
    }

    @Override
    public void move(double power) {
        tr.setPower(power);
    }

    @Override
    public void moveTele(double power) { super.moveTele(power); }

    public double getTurretPos(){
        return trEnc.getPos();
    }


    public void resetEncoder(){trEnc.reset();}

    public void setTarget(double h){
        resetEncoder();
        tr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tr.setTargetPosition((int)(h* Constants.LIFT_CM_TO_TICKS));
    }

    public boolean hasReachedTarget(){
        return !tr.isBusy();
    }

    public double getTurretTargetPos(){
        if(fieldSide.equals(FieldSide.BLUE)){
            return Constants.BLUE_SIDE_TURRET_ANGLE;
        }else if(fieldSide.equals(FieldSide.RED)){
            return Constants.RED_SIDE_TURRET_ANGLE;
        }else{
            return 0;
        }
    }
}
