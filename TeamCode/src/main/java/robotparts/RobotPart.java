package robotparts;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import java.util.Map.*;
import java.util.Objects;
import java.util.TreeMap;

import robot.RobotFramework;
import robotparts.custom.Encoder;
import util.condition.Status;
import util.Timer;

import static global.General.*;
import robotparts.custom.Encoder.Type;

public class RobotPart {

    public TreeMap<String, DcMotor> motors = new TreeMap<>();
    public TreeMap<String, Servo> servos = new TreeMap<>();
    public TreeMap<String, CRServo> crservos = new TreeMap<>();
    public TreeMap<String, BNO055IMU> gyrosensors = new TreeMap<>();
    public TreeMap<String, DistanceSensor> distancesensors = new TreeMap<>();
    public TreeMap<String, ColorSensor> colorsensors = new TreeMap<>();
    public TreeMap<String, TouchSensor> touchsensors = new TreeMap<>();
    public TreeMap<String, Encoder> encoders = new TreeMap<>();

    private Status currentStatus = Status.INACTIVE;

    public Timer timer = new Timer();

    public RobotPart(){
       RobotFramework.allRobotParts.add(this);
    }

    public void init(){}
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

    protected Servo createServo(String name, Servo.Direction dir, double startpos, double endpos){
        Servo servo = hardwareMap.get(Servo.class, name);
        servo.setDirection(dir);
        servo.scaleRange(startpos, endpos);
        servo.setPosition(0);
        servos.put(name, servo);
        return servo;
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
}
