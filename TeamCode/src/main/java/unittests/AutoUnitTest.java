package unittests;

import static global.General.log;

import java.util.Map;

import robotparts.RobotPart;
import robotparts.electronics.CMotor;
import robotparts.electronics.CServo;
import robotparts.electronics.PServo;
import util.condition.Status;

public class AutoUnitTest{
    /**
     * Status of the unit test, starts at idle
     */
    protected Status status = Status.IDLE;

    /**
     * For init, start, loop, and stop see tele
     * @link Tele
     */
    public void init() {

    }
    public void start() {}
    public void run() {}
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


    /**
     * Method to show the configuration of the motors and servos in a robotpart
     * @param part
     */
    protected void showConfig(RobotPart part){
        for (Map.Entry<String, CMotor> entry: part.getElectronicsOfType(CMotor.class).entrySet()) { showConfig(entry.getKey(), entry.getValue()); }
        for (Map.Entry<String, PServo> entry: part.getElectronicsOfType(PServo.class).entrySet()) { showConfig(entry.getKey(), entry.getValue()); }
        for (Map.Entry<String, CServo> entry: part.getElectronicsOfType(CServo.class).entrySet()) { showConfig(entry.getKey(), entry.getValue()); }
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
