package auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import util.Timer;

import static global.General.mainUser;

@Autonomous(name="AutoUserTest")
public class AutoUserTest extends Auto{

    @Override
    public void runOpMode() throws InterruptedException {
        reference(this);
        waitForStart();
        telemetry.addData("Current User", mainUser.toString());
        telemetry.update();
        Timer timer = new Timer();
        timer.reset();
        while (opModeIsActive() && timer.seconds() < 10){}

    }
}
