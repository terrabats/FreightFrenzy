package robotparts;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.TreeMap;

import global.General;
import global.TerraBot;
import global.General;

public class RobotPart implements General {

    // TODO: Finish this class

    public TreeMap<String, DcMotor> motors = new TreeMap<>();
    public TreeMap<String, Servo> servos = new TreeMap<>();
    public TreeMap<String, CRServo> crservos = new TreeMap<>();
    public TreeMap<String, BNO055IMU> gyrosensors = new TreeMap<>();
    public TreeMap<String, DistanceSensor> distancesensors = new TreeMap<>();


    private Status currentStatus;

    public RobotPart () { }

    public void init () {}

    public void setStatus(Status status){
        currentStatus = status;
    }

    public Status getStatus(){
        return currentStatus;
    }

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

    // TODO: FILL THIS OUT
    protected void createCRServo(){}

    protected BNO055IMU createGyro(String name){
        BNO055IMU gyro = TerraBot.hardwareMap.get(BNO055IMU.class, name);
        gyrosensors.put(name, gyro);
        return gyro;
    }

    // TODO: FILL THIS OUT
    protected void createDistanceSensor(){}

    protected enum Status{
        IDLE,
        ACTIVE,
        DISABLED
    }
}
