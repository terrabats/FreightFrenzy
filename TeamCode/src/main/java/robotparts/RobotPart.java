package robotparts;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import java.util.ArrayList;
import java.util.TreeMap;

import robot.RobotFramework;
import robotparts.electronics.CMotor;
import robotparts.electronics.CServo;
import robotparts.electronics.SColor;
import robotparts.electronics.SDistance;
import robotparts.electronics.Encoder;
import robotparts.electronics.Gyro;
import robotparts.electronics.LED;
import robotparts.electronics.PMotor;
import robotparts.electronics.PServo;

import static global.General.*;
import robotparts.electronics.Encoder.Type;
import robotparts.electronics.Touch;
import util.User;
import util.codeseg.TypeParameterCodeSeg;

public class RobotPart extends Electronic {
    /**
     * Represents a part of the robot like the drivetrain or the intake
     * When making a new part of the robot part make sure to extend this class
     * then override the init method
     */
    // TODO FIX
    // Remove all of the specific treemaps and make a method to get all based on certain class
    // TODO FIX
    // ALSO FIX NAMING CONVENTIONS
    /**
     * TreeMaps to store the list of electronics
     */
    public TreeMap<String, CMotor> cmotors = new TreeMap<>();
    public TreeMap<String, CServo> cservos = new TreeMap<>();
    public TreeMap<String, PMotor> pmotors = new TreeMap<>();
    public TreeMap<String, PServo> pservos = new TreeMap<>();
    public TreeMap<String, Gyro> gyrosensors = new TreeMap<>();
    public TreeMap<String, SDistance> distancesensors = new TreeMap<>();
    public TreeMap<String, SColor> colorsensors = new TreeMap<>();
    public TreeMap<String, Touch> touchsensors = new TreeMap<>();
    public TreeMap<String, Encoder> encoders = new TreeMap<>();
    public TreeMap<String, LED> leds = new TreeMap<>();
    /**
     * TreeMaps to store the list of electronics
     */
    public ArrayList<Electronic> electronics = new ArrayList<>();
    /**
     * The currentUser of the robotPart, by default none
     */
    private volatile User currentUser = User.NONE;

    /**
     * Constructor to create the robot part
     * NOTE: This automaticallty adds itself to the robotparts list
     */
    public RobotPart(){
       RobotFramework.allRobotParts.add(this);
    }

    /**
     * Init method (to be overriden
     */
    public void init() {}

    // TODO FIX
    // Make all of thse literally one method or smt, if its possible
    /**
     * Create different robot parts from a set of parameterys
     */
    protected CMotor createCMotor(String name, DcMotor.Direction dir){
        CMotor cmotor = new CMotor(hardwareMap.get(DcMotor.class, name), dir, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        cmotors.put(name, cmotor);
        electronics.add(cmotor);
        return cmotor;
    }

    protected CMotor createCMotor(String name, DcMotor.Direction dir, DcMotor.ZeroPowerBehavior zpb, DcMotor.RunMode mode){
        CMotor cmotor = new CMotor(hardwareMap.get(DcMotor.class, name), dir, zpb, mode);
        cmotors.put(name, cmotor);
        electronics.add(cmotor);
        return cmotor;
    }

    protected CServo createCServo(String name, CRServo.Direction dir){
        CServo cservo = new CServo(hardwareMap.get(CRServo.class, name), dir);
        cservos.put(name, cservo);
        electronics.add(cservo);
        return cservo;
    }

    protected PServo createPServo(String name, Servo.Direction dir, double startpos, double endpos){
        PServo pservo = new PServo(hardwareMap.get(Servo.class, name), dir, startpos, endpos);
        pservos.put(name, pservo);
        electronics.add(pservo);
        return pservo;
    }

    protected PMotor createPMotor(String name, DcMotor.Direction dir){
        PMotor pmotor = new PMotor(hardwareMap.get(DcMotor.class, name), dir, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pmotors.put(name, pmotor);
        electronics.add(pmotor);
        return pmotor;
    }

    protected Gyro createGyro(String name){
        Gyro gyro = new Gyro(hardwareMap.get(BNO055IMU.class, name));
        gyrosensors.put(name, gyro);
        electronics.add(gyro);
        return gyro;
    }

    protected SDistance createDistanceSensor(String name){
        SDistance distanceSensor = new SDistance(hardwareMap.get(ModernRoboticsI2cRangeSensor.class, name));
        distancesensors.put(name, distanceSensor);
        electronics.add(distanceSensor);
        return distanceSensor;
    }

    protected SColor createColorSensor(String name){
        SColor colorSensor = new SColor(hardwareMap.get(ColorSensor.class, name));
        colorsensors.put(name, colorSensor);
        electronics.add(colorSensor);

        return colorSensor;
    }

    protected Touch createTouchSensor(String name){
        Touch touchSensor = new Touch(hardwareMap.get(TouchSensor.class, name));
        touchsensors.put(name, touchSensor);
        return touchSensor;
    }

    protected Encoder createEncoder(String motor, String name, Type type){
        Encoder encoder = new Encoder(hardwareMap.get(DcMotor.class, motor), type);
        encoders.put(name, encoder);
        return encoder;
    }

    protected LED createLED(String name){
        LED led = new LED(hardwareMap.get(DigitalChannel.class,  "g" + name), hardwareMap.get(DigitalChannel.class,  "r" + name));
        leds.put(name, led);
        return led;
    }

    /**
     * Halt of the cmotors and cservos (i.e. set the power to 0)
     */
    public void halt(){
        for(CMotor mot: cmotors.values()){
            mot.setPower(0);
        }
        for(CServo crs: cservos.values()){
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
    private void forAllElectronics(TypeParameterCodeSeg<Electronic> run){ for(Electronic e: electronics){ run.run(e); } }

}
