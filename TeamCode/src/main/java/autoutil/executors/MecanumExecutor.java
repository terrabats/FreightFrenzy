package autoutil.executors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.Arrays;

import autoutil.paths.PathAutoModule;
import autoutil.paths.PathPose;
import autoutil.paths.PathSegment;
import autoutil.paths.PathSegment2;
import autoutil.reactors.Reactor;
import autoutil.reactors.mecanum.MecanumReactor;
import geometry.position.Pose;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;

import static global.General.bot;
import static global.General.log;
import static global.General.telemetry;

public class MecanumExecutor extends ExecutorReal{

    public MecanumExecutor(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void followPath() {
        reactor.nextTarget();
        for(PathSegment2 pathSegment: path.getSegments()){
            if(pathSegment instanceof PathPose) {
                for (Pose pose : pathSegment.getPoses()) {
                    reactor.setTarget(pose.asArray());
                    whileActive(() -> !reactor.isAtTarget(), ()-> reactor.moveToTarget());
                    reactor.nextTarget();
                }
            }else if(pathSegment instanceof PathAutoModule){
                ((PathAutoModule) pathSegment).runAutoModule();
                whileActive(() -> !((PathAutoModule) pathSegment).isDoneWithAutoModule(), ()-> {});
            }
        }
        bot.mecanumDrive.move(0,0,0);
    }

    protected void whileActive(ReturnCodeSeg<Boolean> active, CodeSeg code){
        log.setShouldUpdateOnShow(false);
        while (whileOpModeIsActive.run() && active.run()){
            code.run();
            log.showTelemetry();
        }
        log.setShouldUpdateOnShow(true);
    }
}
