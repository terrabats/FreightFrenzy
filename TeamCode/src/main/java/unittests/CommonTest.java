package unittests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import static global.General.*;

public class CommonTest extends UnitTest {
    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        log.display("Common is Working");
        update(true);
    }
    @Override
    public void stop(){
        //end(true);
    }
}
