package robotparts;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.TreeMap;

import robot.General;
import robot.RobotFramework;
import robot.TerraBot;
import util.Timer;

public class RobotPart implements General {

    public TreeMap<String, DcMotor> motors = new TreeMap<>();
    public TreeMap<String, Servo> servos = new TreeMap<>();
    public TreeMap<String, CRServo> crservos = new TreeMap<>();
    public TreeMap<String, BNO055IMU> gyrosensors = new TreeMap<>();
    public TreeMap<String, DistanceSensor> distancesensors = new TreeMap<>();

    private Status currentStatus = Status.INACTIVE;

    public Timer timer = new Timer();

    public RobotPart(){
       RobotFramework.allRobotParts.add(this);
    }

    public void init(){}

    protected DcMotor createMotor(String name, DcMotor.Direction dir, DcMotor.ZeroPowerBehavior zpb, DcMotor.RunMode mode){
        DcMotor dcMotor = TerraBot.hardwareMap.get(DcMotor.class, name);
        dcMotor.setPower(0);
        dcMotor.setDirection(dir);
        dcMotor.setZeroPowerBehavior(zpb);
        dcMotor.setMode(mode);
        motors.put(name, dcMotor);
        return dcMotor;
    }

    protected Servo createServo(String name, Servo.Direction dir, double startpos, double endpos){
        Servo servo = TerraBot.hardwareMap.get(Servo.class, name);
        servo.setDirection(dir);
        servo.scaleRange(startpos, endpos);
        servo.setPosition(0);
        servos.put(name, servo);
        return servo;
    }

    protected CRServo createCRServo(String name, CRServo.Direction dir){
        CRServo crServo = TerraBot.hardwareMap.get(CRServo.class, name);
        crServo.setPower(0);
        crServo.setDirection(dir);
        crservos.put(name, crServo);
        return crServo;
    }

    protected BNO055IMU createGyro(String name){
        BNO055IMU gyro = TerraBot.hardwareMap.get(BNO055IMU.class, name);
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        gyro.initialize(parameters);
        gyrosensors.put(name, gyro);
        return gyro;
    }

    protected DistanceSensor createDistanceSensor(String name){
        DistanceSensor distanceSensor = TerraBot.hardwareMap.get(ModernRoboticsI2cRangeSensor.class, name);
        distancesensors.put(name, distanceSensor);
        return distanceSensor;
    }

    public void setStatus(Status status){
        currentStatus = status;
    }

    public Status getStatus(){
        return currentStatus;
    }

    protected enum Status{
        IDLE, //RobotPart is waiting for commands and is ready to be used
        ACTIVE, //RobotPart is being used
        DISABLED, //RobotPart is not allowed to be used
        INACTIVE //RobotPart is not being used or off
    }
}
