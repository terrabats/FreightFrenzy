package unittests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import geometry.*;
import robot.TerraBot;

import static global.General.*;

// TO BE TESTED

@TeleOp(name = "CoordinatePlaneTest")
public class CoordinatePlaneTest extends OpMode {
    public CoordinatePlane coordinatePlane = new CoordinatePlane();

    @Override
    public void init() {
        bot = new TerraBot();
        bot.init(hardwareMap, telemetry, gamepad1, gamepad2);

        coordinatePlane.add(new Vector(0, 0, AngleType.RADIANS));
        coordinatePlane.add(new Line(new Point(1, 10), new Point(5, 10)));

        telemetry.addData("Status", "Ready");
        telemetry.update();
    }

    @Override
    public void loop() {
        coordinatePlane.setOrientation(0, AngleType.DEGREES);
        telemetry.addData("Vector original", coordinatePlane.getVectors().get(0));
        telemetry.addData("Line original", coordinatePlane.getLines().get(0));
        coordinatePlane.setOrientation(90, AngleType.DEGREES);
        telemetry.addData("Vector final", coordinatePlane.getVectors().get(0));
        telemetry.addData("Line final", coordinatePlane.getLines().get(0));
        telemetry.update();
    }

    @Override
    public void stop() {
        bot.stop();
    }
}
