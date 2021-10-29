package developing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import global.General;

import static global.General.*;

public class InitOp extends OpMode {
    public InitOp(){
        General.hardwareMap = hardwareMap;
        General.telemetry = telemetry;
    }

    public void initObjects(){
        General.hardwareMap = hardwareMap;
        General.telemetry = telemetry;
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
