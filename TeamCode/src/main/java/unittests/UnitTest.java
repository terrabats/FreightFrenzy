package unittests;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Map;
import java.util.Map.*;

import robotparts.RobotPart;
import robotparts.custom.PServo;
import util.condition.Status;

import static global.General.*;

public class UnitTest {
    protected Status status = Status.IDLE;
    public void init() {}
    public void test(){
        if(status.equals(Status.IDLE)){
            run();
            status = Status.ACTIVE;
        }else{
            loop();
        }
    }
    protected void run() {}
    protected void loop() {}
    public void stop() {bot.halt();}
    public void start() {}


    protected void showConfig(RobotPart part){
        for (Entry<String, DcMotor> entry: part.motors.entrySet()) { showConfig(entry.getKey(), entry.getValue()); }
        for (Entry<String, PServo> entry: part.pservos.entrySet()) { showConfig(entry.getKey(), entry.getValue()); }
        for (Entry<String, CRServo> entry: part.crservos.entrySet()) { showConfig(entry.getKey(), (CRServo) entry.getValue()); }
    }
    protected void showConfig(String name, DcMotor motor){
        log.display("DcMotor: "+ name, "Dir: "+ motor.getDirection());
    }
    protected void showConfig(String name, PServo pservo){
        log.display("Servo: "+ name, "Dir: "+ pservo.getDirection() + "Lower: "+ pservo.getLower() + "Upper: " + pservo.getUpper());
    }
    protected void showConfig(String name, CRServo crservo){
        log.display("CRServo: "+ name, "Dir: "+ crservo.getDirection());
    }
}
