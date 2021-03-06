package unittests;

import java.util.Map.*;

import robotparts.RobotPart;
import robotparts.electronics.CMotor;
import robotparts.electronics.CServo;
import robotparts.electronics.PServo;
import util.condition.Status;

import static global.General.*;

public class UnitTest {
    /**
     * Status of the unit test, starts at idle
     */
    protected Status status = Status.IDLE;

    /**
     * For init, start, loop, and stop see tele
     * @link Tele
     */
    public void init() {}
    protected void start() {}
    protected void loop() {}
    public void stop() {}

    /**
     * Reset the status to idle
     */
    public void reset(){
        status = Status.IDLE;
    }

    /**
     * Test by running start, setting the status to active, and then running loop
     * This ensures that start runs once and then loop runs over and over
     */
    public void test(){
        if(status.equals(Status.IDLE)){
            start();
            status = Status.ACTIVE;
        }else{
            loop();
        }
    }

    /**
     * Method to show the configuration of the motors and servos in a robotpart
     * @param part
     */
    protected void showConfig(RobotPart part){
        for (Entry<String, CMotor> entry: part.getElectronicsOfType(CMotor.class).entrySet()) { showConfig(entry.getKey(), entry.getValue()); }
        for (Entry<String, PServo> entry: part.getElectronicsOfType(PServo.class).entrySet()) { showConfig(entry.getKey(), entry.getValue()); }
        for (Entry<String, CServo> entry: part.getElectronicsOfType(CServo.class).entrySet()) { showConfig(entry.getKey(), entry.getValue()); }
    }

    /**
     * Methods to show the config for a specific motor or servo
     * @param name
     * @param motor
     */
    protected void showConfig(String name, CMotor motor){
        log.showAndRecord("DcMotor: "+ name, "Dir: "+ motor.getDirection());
    }
    protected void showConfig(String name, PServo pservo){
        log.showAndRecord("Servo: "+ name, "Dir: "+ pservo.getDirection() + "Lower: "+ pservo.getLower() + "Upper: " + pservo.getUpper());
    }
    protected void showConfig(String name, CServo crservo){
        log.showAndRecord("CRServo: "+ name, "Dir: "+ crservo.getDirection());
    }
}
