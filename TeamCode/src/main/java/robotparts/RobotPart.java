package robotparts;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import java.util.ArrayList;
import java.util.TreeMap;

import robot.RobotFramework;
import robotparts.electronics.CMotor;
import robotparts.electronics.CServo;
import robotparts.electronics.Encoder;
import robotparts.electronics.LED;
import robotparts.electronics.PMotor;
import robotparts.electronics.PServo;

import static global.General.*;
import robotparts.electronics.Encoder.Type;
import util.User;
import util.codeseg.TypeParameterCodeSeg;

public class RobotPart extends Electronic {

    public TreeMap<String, CMotor> cmotors = new TreeMap<>();
    public TreeMap<String, CServo> cservos = new TreeMap<>();

    public TreeMap<String, PMotor> pmotors = new TreeMap<>();
    public TreeMap<String, PServo> pservos = new TreeMap<>();

    public TreeMap<String, BNO055IMU> gyrosensors = new TreeMap<>();
    public TreeMap<String, DistanceSensor> distancesensors = new TreeMap<>();
    public TreeMap<String, ColorSensor> colorsensors = new TreeMap<>();
    public TreeMap<String, TouchSensor> touchsensors = new TreeMap<>();
    public TreeMap<String, Encoder> encoders = new TreeMap<>();
    public TreeMap<String, LED> leds = new TreeMap<>();

    public ArrayList<Electronic> electronics = new ArrayList<>();

    private volatile User currentUser = User.NONE;

    public RobotPart(){
       RobotFramework.allRobotParts.add(this);
    }

    public void init() {}

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
    // TODO FIX
    // Make this proper
    // ALSO REMEMBER TO ADD THE OTHER ELECTRONICS TO electronics arraylist

    protected PMotor createPMotor(String name, DcMotor.Direction dir){
        PMotor pmotor = new PMotor(hardwareMap.get(DcMotor.class, name), dir, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pmotors.put(name, pmotor);
        electronics.add(pmotor);
        return pmotor;
    }

    protected BNO055IMU createGyro(String name){
        BNO055IMU gyro = hardwareMap.get(BNO055IMU.class, name);
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        gyro.initialize(parameters);
        gyrosensors.put(name, gyro);
        return gyro;
    }

    protected DistanceSensor createDistanceSensor(String name){
        DistanceSensor distanceSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, name);
        distancesensors.put(name, distanceSensor);
        return distanceSensor;
    }

    protected ColorSensor createColorSensor(String name){
        ColorSensor colorSensor = hardwareMap.get(ColorSensor.class, name);
        colorsensors.put(name, colorSensor);
        return colorSensor;
    }

    protected TouchSensor createTouchSensor(String name){
        TouchSensor touchSensor = hardwareMap.get(TouchSensor.class, name);
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

    public void halt(){
        for(CMotor mot: cmotors.values()){
            mot.setPower(0);
        }
        for(CServo crs: cservos.values()){
            crs.setPower(0);
        }
    }

    public User getUser(){
        return currentUser;
    }

    public void switchUser(User newUser){
        currentUser = newUser;
    }

    public void checkAccess(User potentialUser){
        if(potentialUser.equals(currentUser)) {
            forAllElectronics(e -> e.access.allow());
        }else{
            forAllElectronics(e -> e.access.deny());
        }
    }


    private void forAllElectronics(TypeParameterCodeSeg<Electronic> run){ for(Electronic e: electronics){ run.run(e); } }

}
