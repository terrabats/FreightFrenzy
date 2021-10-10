package teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.General;

@TeleOp(name = "TestOp")
public class TestOp extends OpMode implements General {
// TODO: Figure out a structure for this that makes it easy to code, maybe one teleop somehow
    @Override
    public void init() {
        bot.init(hardwareMap);
    }

    @Override
    public void loop() {
        bot.mechDrive.move(0.3,0,0);
    }
}
