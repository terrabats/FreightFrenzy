package unittests;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Map.*;

import robotparts.RobotPart;
import robotparts.electronics.CMotor;
import robotparts.electronics.CServo;
import robotparts.electronics.PServo;
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
        for (Entry<String, CMotor> entry: part.cmotors.entrySet()) { showConfig(entry.getKey(), entry.getValue()); }
        for (Entry<String, PServo> entry: part.pservos.entrySet()) { showConfig(entry.getKey(), entry.getValue()); }
        for (Entry<String, CServo> entry: part.cservos.entrySet()) { showConfig(entry.getKey(), entry.getValue()); }
    }
    protected void showConfig(String name, CMotor motor){
        log.display("DcMotor: "+ name, "Dir: "+ motor.getDirection());
    }
    protected void showConfig(String name, PServo pservo){
        log.display("Servo: "+ name, "Dir: "+ pservo.getDirection() + "Lower: "+ pservo.getLower() + "Upper: " + pservo.getUpper());
    }
    protected void showConfig(String name, CServo crservo){
        log.display("CRServo: "+ name, "Dir: "+ crservo.getDirection());
    }
}
