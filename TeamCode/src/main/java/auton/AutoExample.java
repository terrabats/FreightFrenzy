package auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import autoutil.executors.MecanumExecutorPID;
import autoutil.generators.PoseToPoseGenerator;
import autoutil.reactors.mecanum.MecanumPIDReactor;
import util.Timer;

import static global.General.autoModules;
import static global.General.bot;

@Disabled
@Autonomous(name = "AutoExample")
public class AutoExample extends Auto {


    PoseToPoseGenerator g1;
    MecanumPIDReactor r1;
    MecanumExecutorPID e1;


    PoseToPoseGenerator g2;
    MecanumPIDReactor r2;
    MecanumExecutorPID e2;

    PoseToPoseGenerator g3;
    MecanumPIDReactor r3;
    MecanumExecutorPID e3;

    PoseToPoseGenerator g4;
    MecanumPIDReactor r4;
    MecanumExecutorPID e4;

    Timer timer = new Timer();

    @Override
    public void initAuto() {
        g1 = new PoseToPoseGenerator();
        r1 = new MecanumPIDReactor();
        e1 = new MecanumExecutorPID(this);

        g2 = new PoseToPoseGenerator();
        r2 = new MecanumPIDReactor();
        e2 = new MecanumExecutorPID(this);

        g3 = new PoseToPoseGenerator();
        r3 = new MecanumPIDReactor();
        e3 = new MecanumExecutorPID(this);

        g4 = new PoseToPoseGenerator();
        r4 = new MecanumPIDReactor();
        e4 = new MecanumExecutorPID(this);


        g1.addPose(0,25,90);
//        g1.addAutoModule(autoModules.IntakeRiseAuto);
        g1.addPose(40, 25, 60);

//        g2.addAutoModule(autoModules.DuckRiseTele);
        g2.addPose(-0, 50, 90);
        g2.addPose(-100, 35, 90);
        g2.addPose(-110,25,-135);
        g2.addPose(-100,50,-135);
//        g2.addAutoModule(autoModules.ForwardRiseTele);

        g2.addPose(-110,-5,-90);

//        g3.addAutoModule(autoModules.IntakeRiseAuto);

        g4.addPose(-100,50,-135);
//        g4.addAutoModule(autoModules.ForwardRiseTele);
        g4.addPose(-120,30,-90);
        g4.addPose(-122,-5,-90);





        e1.setPath(g1.getPath());
        e1.setReactor(r1);

        e2.setPath(g2.getPath());
        e2.setReactor(r2);

        e3.setPath(g3.getPath());
        e3.setReactor(r3);

        e4.setPath(g4.getPath());
        e4.setReactor(r4);
    }

    @Override
    public void runAuto() {
        e1.followPath();

        moveTime(0.5, 0.0, 0.4, 0.0);

        e2.followPath();

        moveTime(0.5, 0, -0.7, -0.5);

        moveTime(0.9, 1, -0.4, 0.0);

        e3.followPath();

        moveTime(0.6, -1, -0.4, 0.0);

        moveTime(0.3, 0, 0.4, 0.0);

        e4.followPath();

        moveTime(0.5, 0, -0.7, -0.5);

        moveTime(1, 1, -0.4, 0.0);

    }


    public void moveTime(double time, double f, double s, double t){
        timer.reset();
        while (opModeIsActive() && timer.seconds() < time) {
            bot.mecanumDrive.moveSmooth(f, s, t);
        }
    }
}