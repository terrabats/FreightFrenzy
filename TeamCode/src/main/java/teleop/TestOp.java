package teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import global.General;
import global.TestGeneral;

public class TestOp extends OpMode implements TestGeneral {
// TODO: Figure out a structure for this that makes it easy to code, maybe one teleop somehow
    @Override
    public void init() {
        bot.init(hardwareMap);


        General.init(hardwareMap);
    }

    @Override
    public void loop() {

    }
}
