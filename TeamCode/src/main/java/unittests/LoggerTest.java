package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import util.condition.Status;

import static global.General.*;

@TeleOp(name = "LoggerTest")
public class LoggerTest extends OpMode implements Common {
    private double d = 0;
    private boolean b = false;
    private Status status = Status.ACTIVE;
    @Override
    public void init() {reference(this);}

    @Override
    public void loop() {
        d += 0.1;
        b = !b;
        log.display("log.display is working");
        log.monitor("Double", d);
        log.monitor("Boolean", b);
        log.status("status", status);
        update(true);
    }
    @Override
    public void stop(){
        end();
    }
}
