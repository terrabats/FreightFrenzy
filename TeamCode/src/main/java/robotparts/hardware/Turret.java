package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import elements.FieldSide;
import global.Constants;
import robotparts.RobotPart;
import robotparts.electronics.CMotor;
import robotparts.electronics.Encoder;
import robotparts.electronics.PMotor;

import static global.General.fieldSide;

public class Turret extends RobotPart {
    private PMotor tr;
    private Encoder trEnc;

    @Override
    public void init() {
        tr = createPMotor("tr", DcMotorSimple.Direction.FORWARD);
        trEnc = createEncoder("tr", "trEnc", Encoder.Type.MOTOR);
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
        tr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tr.setTargetPosition((int)(angle*Constants.TURRET_ANGLE_DEG_TO_TICKS));
        tr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public boolean hasReachedTarget(){
        return !tr.isBusy();
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
        tr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        tr.setPower(0);
    }
}
