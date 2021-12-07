package unittests.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Map.*;

import robotparts.RobotPart;
import robotparts.custom.PServo;
import unittests.UnitTest;

import static global.General.log;

public class HardwareTest extends UnitTest {
    @Override
    public Type getType() {
        return Type.HARDWARE;
    }

    public void showConfig(RobotPart part){
        for (Entry entry: part.motors.entrySet()) {
            showConfig((String) entry.getKey(), (DcMotor) entry.getValue());
        }
        for (Entry entry: part.pservos.entrySet()) {
            showConfig((String) entry.getKey(), (PServo) entry.getValue());
        }
        for (Entry entry: part.crservos.entrySet()) {
            showConfig((String) entry.getKey(), (CRServo) entry.getValue());
        }
    }
    private void showConfig(String name, DcMotor motor){
        log.display("DcMotor: "+ name, "Dir: "+ motor.getDirection());
    }
    private void showConfig(String name, PServo pservo){
        log.display("Servo: "+ name, "Dir: "+ pservo.getDirection() + "Lower: "+ pservo.getLower() + "Upper: " + pservo.getUpper());
    }
    private void showConfig(String name, CRServo crservo){
        log.display("CRServo: "+ name, "Dir: "+ crservo.getDirection());
    }
}
