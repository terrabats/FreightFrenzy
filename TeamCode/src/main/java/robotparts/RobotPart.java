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

import java.util.Map.*;
import java.util.Objects;
import java.util.TreeMap;

import robot.RobotFramework;
import robotparts.custom.Encoder;
import robotparts.custom.LED;
import robotparts.custom.PServo;
import util.condition.Status;
import util.Timer;

import static global.General.*;
import robotparts.custom.Encoder.Type;

public class RobotPart {

    public TreeMap<String, DcMotor> motors = new TreeMap<>();
    public TreeMap<String, PServo> pservos = new TreeMap<>();
    public TreeMap<String, CRServo> crservos = new TreeMap<>();
    public TreeMap<String, BNO055IMU> gyrosensors = new TreeMap<>();
    public TreeMap<String, DistanceSensor> distancesensors = new TreeMap<>();
    public TreeMap<String, ColorSensor> colorsensors = new TreeMap<>();
    public TreeMap<String, TouchSensor> touchsensors = new TreeMap<>();
    public TreeMap<String, Encoder> encoders = new TreeMap<>();
    public TreeMap<String, LED> leds = new TreeMap<>();

    private volatile Status currentStatus = Status.ACTIVE;

    public RobotPart(){
       RobotFramework.allRobotParts.add(this);
    }

    public void init() {}

    protected DcMotor createMotor(String name, DcMotor.Direction dir){
        DcMotor dcMotor = hardwareMap.get(DcMotor.class, name);
        dcMotor.setPower(0);
        dcMotor.setDirection(dir);
        dcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dcMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motors.put(name, dcMotor);
        return dcMotor;
    }
    protected DcMotor createMotor(String name, DcMotor.Direction dir, DcMotor.ZeroPowerBehavior zpb, DcMotor.RunMode mode){
        DcMotor dcMotor = hardwareMap.get(DcMotor.class, name);
        dcMotor.setPower(0);
        dcMotor.setDirection(dir);
        dcMotor.setZeroPowerBehavior(zpb);
        dcMotor.setMode(mode);
        motors.put(name, dcMotor);
        return dcMotor;
    }

    protected PServo createPServo(String name, Servo.Direction dir, double startpos, double endpos){
        PServo pservo = new PServo(hardwareMap.get(Servo.class, name), dir, startpos, endpos);
        pservos.put(name, pservo);
        return pservo;
    }

    protected CRServo createCRServo(String name, CRServo.Direction dir){
        CRServo crServo = hardwareMap.get(CRServo.class, name);
        crServo.setPower(0);
        crServo.setDirection(dir);
        crservos.put(name, crServo);
        return crServo;
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

    public void setStatus(Status status){
        currentStatus = status;
    }

    public Status getStatus(){
        return currentStatus;
    }

    public void halt(){
        for(DcMotor mot: motors.values()){
            mot.setPower(0);
        }
        for(CRServo crs: crservos.values()){
            crs.setPower(0);
        }
    }

    public boolean isActive(){
        return getStatus().equals(Status.ACTIVE);
    }
    public boolean isInactive(){
        return getStatus().equals(Status.INACTIVE);
    }
    public synchronized void activate(){
        setStatus(Status.ACTIVE);
    }
    public synchronized void deactivate(){
        setStatus(Status.INACTIVE);
    }

    protected void move(double val1){}
    protected void move(double val1, double val2){}
    protected void move(double val1, double val2, double val3){}
    protected void move(String val1){}

    protected void moveTele(double val1){ if(isInactive()){return;} move(val1); }
    protected void moveTele(double val1, double val2){ if(isInactive()){return;} move(val1, val2); }
    protected void moveTele(double val1, double val2, double val3){ if(isInactive()){return;} move(val1, val2, val3); }
    protected void moveTele(String val1){ if(isInactive()){return;} move(val1); }

    // TODO FIX
    // Fix this system of knowing when they are active?

}
