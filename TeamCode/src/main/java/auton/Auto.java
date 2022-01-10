package auton;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import global.Common;
import static global.General.*;

public abstract class Auto extends LinearOpMode implements Common {
    // Base class for autonomous classes
    // Uses the generator to create the path
    // Uses the executor to move the robot along the path (and any decisions that need to be made while the robot is moving)
}
