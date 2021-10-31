package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import static global.General.*;

@Disabled
@TeleOp(name = "ExampleTest", group = "UnitTests")
public class ExampleTest extends OpMode implements Common {

    @Override
    public void init() {reference(this);}

    @Override
    public void loop() {

    }
    @Override
    public void stop(){end(true);}
}
