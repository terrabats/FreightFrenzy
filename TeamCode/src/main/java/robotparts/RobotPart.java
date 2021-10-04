package robotparts;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;

import java.util.ArrayList;

public class RobotPart {
    public ArrayList<DcMotor> motors = new ArrayList<>();
    public ArrayList<Servo> servos = new ArrayList<>();
    public ArrayList<CRServo> crservos = new ArrayList<>();
    public ArrayList<BNO055IMU> gyrosensors = new ArrayList<>();
    public ArrayList<DistanceSensor> distancesensors = new ArrayList<>();
    public ElapsedTime timer = new ElapsedTime();
    private Status currentStatus;
    private HardwareMap hardwareMap;
    public void init(HardwareMap hwMap){
        hardwareMap = hwMap;
    }
    public void setStatus(Status status){
        currentStatus = status;
    }
    public Status getStatus(){
        return currentStatus;
    }
    protected DcMotor createMotor(String name, DcMotor.Direction dir, DcMotor.ZeroPowerBehavior zpb, DcMotor.RunMode mode){
        DcMotor dcMotor = hardwareMap.get(DcMotor.class, name);
        dcMotor.setPower(0);
        dcMotor.setDirection(dir);
        dcMotor.setZeroPowerBehavior(zpb);
        dcMotor.setMode(mode);
        motors.add(dcMotor);
        return dcMotor;
    }
    protected Servo createServo(String name, Servo.Direction dir, double startpos){
        Servo servo = hardwareMap.get(Servo.class, name);
        servo.setDirection(dir);
        servo.setPosition(startpos);
        return servo;
    }
    protected void createCRServo(){}
    protected void createGyro(){}
    protected void createDistanceSensor(){}
    protected enum Status{
        IDLE,
        ACTIVE,
        DISABLED
    }
}
