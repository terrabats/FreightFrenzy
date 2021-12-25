package robotparts;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import java.util.Map;
import java.util.TreeMap;

import automodules.stage.Exit;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stage;
import automodules.stage.StageComponent;
import automodules.stage.Stop;
import robot.RobotFramework;
import robotparts.electronics.CMotor;
import robotparts.electronics.CServo;
import robotparts.electronics.IColor;
import robotparts.electronics.IDistance;
import robotparts.electronics.IEncoder;
import robotparts.electronics.IGyro;
import robotparts.electronics.OLed;
import robotparts.electronics.PMotor;
import robotparts.electronics.PServo;

import static global.General.*;
import robotparts.electronics.IEncoder.Type;
import robotparts.electronics.ITouch;
import util.User;
import util.codeseg.ParameterCodeSeg;

public class RobotPart extends Electronic {
    /**
     * Represents a part of the robot like the drivetrain or the intake
     * When making a new part of the robot part make sure to extend this class
     * then override the init method
     */

    /**
     * TreeMap to store the list of electronics
     */
    public TreeMap<String, Electronic> electronics = new TreeMap<>();
    /**
     * The currentUser of the robotPart, by default none
     */
    private volatile User currentUser = User.NONE;

    /**
     * Constructor to create the robot part
     * NOTE: This automatically adds itself to the robotparts list
     */
    public RobotPart(){
       RobotFramework.allRobotParts.add(this);
    }

    /**
     * Init method (to be overwritten)
     */
    public void init() {}

    /**
     * Create different robot parts from a set of parameters
     */
    protected CMotor createCMotor(String name, DcMotor.Direction dir){
        CMotor cmotor = new CMotor(hardwareMap.get(DcMotor.class, name), dir, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        electronics.put(name, cmotor);
        return cmotor;
    }

    protected CMotor createCMotor(String name, DcMotor.Direction dir, DcMotor.ZeroPowerBehavior zpb, DcMotor.RunMode mode){
        CMotor cmotor = new CMotor(hardwareMap.get(DcMotor.class, name), dir, zpb, mode);
        electronics.put(name, cmotor);
        return cmotor;
    }

    protected CServo createCServo(String name, CRServo.Direction dir){
        CServo cservo = new CServo(hardwareMap.get(CRServo.class, name), dir);
        electronics.put(name, cservo);
        return cservo;
    }

    protected PServo createPServo(String name, Servo.Direction dir, double startpos, double endpos){
        PServo pservo = new PServo(hardwareMap.get(Servo.class, name), dir, startpos, endpos);
        electronics.put(name, pservo);
        return pservo;
    }

    protected PMotor createPMotor(String name, DcMotor.Direction dir){
        PMotor pmotor = new PMotor(hardwareMap.get(DcMotor.class, name), dir, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        electronics.put(name, pmotor);
        return pmotor;
    }

    protected IGyro createGyro(String name){
        IGyro gyro = new IGyro(hardwareMap.get(BNO055IMU.class, name));
        electronics.put(name, gyro);
        return gyro;
    }

    protected IDistance createDistanceSensor(String name){
        IDistance distanceSensor = new IDistance(hardwareMap.get(ModernRoboticsI2cRangeSensor.class, name));
        electronics.put(name, distanceSensor);
        return distanceSensor;
    }

    protected IColor createColorSensor(String name){
        IColor colorSensor = new IColor(hardwareMap.get(ColorSensor.class, name));
        electronics.put(name, colorSensor);
        return colorSensor;
    }

    protected ITouch createTouchSensor(String name){
        ITouch touchSensor = new ITouch(hardwareMap.get(TouchSensor.class, name));
        electronics.put(name, touchSensor);
        return touchSensor;
    }

    protected IEncoder createEncoder(String motor, String name, Type type){
        IEncoder encoder = new IEncoder(hardwareMap.get(DcMotor.class, motor), type);
        electronics.put(name, encoder);
        return encoder;
    }

    protected OLed createLED(String name){
        OLed led = new OLed(hardwareMap.get(DigitalChannel.class,  "g" + name), hardwareMap.get(DigitalChannel.class,  "r" + name));
        electronics.put(name, led);
        return led;
    }

    /**
     * Gets treemap of specific electronics
     * @return Electronic TreeMap
     */
    @SuppressWarnings("unchecked")
    public <T> TreeMap<String, T> getObjects(Class<T> type) {
        TreeMap<String, T> ret = new TreeMap<>();
        for (Map.Entry<String, Electronic> o : electronics.entrySet()) {
            if (o.getClass() == type) {
                ret.put(o.getKey(), (T) o.getValue());
            }
        }
        return ret;
    }

    /**
     * Halt of the cmotors and cservos (i.e. set the power to 0)
     */
    public void halt(){
        for(CMotor mot: getObjects(CMotor.class).values()){
            mot.setPower(0);
        }
        for(CServo crs: getObjects(CServo.class).values()){
            crs.setPower(0);
        }
    }

    /**
     * Get the currentUser
     * @return currentUser
     */
    public User getUser(){
        return currentUser;
    }

    /**
     * Switch the user to the new user,
     * @param newUser
     */
    public void switchUser(User newUser){
        currentUser = newUser;
    }

    /**
     * Check the access of the user if they match the current user
     * This means that only the currentUser has access and all other users will be denied
     * In order to use a robotpart you must call switch user
     * @param potentialUser
     */
    public void checkAccess(User potentialUser){
        if(potentialUser.equals(currentUser)) {
            forAllElectronics(e -> e.access.allow());
        }else{
            forAllElectronics(e -> e.access.deny());
        }
    }

    /**
     * For all electonics run...
     * @param run
     */
    private void forAllElectronics(ParameterCodeSeg<Electronic> run){ for(Electronic e: electronics.values()){ run.run(e); } }

    public Exit exitTime(double s){return new Exit(() -> bot.rfsHandler.timer.seconds() > s);}
    public Exit exit(){return new Exit(() -> true);}

    public Initial usePart(){return new Initial(() -> switchUser(User.ROFU));}
    public Stop returnPart(){return new Stop(() -> switchUser(mainUser));}

    public Stage pause() {return new Stage(true);}
}
