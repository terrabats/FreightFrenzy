package autoutil.executors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import autoutil.paths.PathAutoModule;
import autoutil.paths.PathPose;
import autoutil.paths.PathSegment2;
import geometry.position.Pose;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;

import static global.General.bot;
import static global.General.log;

public class MecanumExecutorPID extends ExecutorPID {

    public MecanumExecutorPID(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void followPath() {
        reactor.init();
        for(PathSegment2 pathSegment: path.getSegments()){
            if(pathSegment instanceof PathPose) {
                for (Pose pose : pathSegment.getPoses()) {
                    reactor.setTarget(pose.asArray());
                    whileActive(() -> !reactor.isAtTarget(), ()-> reactor.moveToTarget());
                    reactor.nextTarget();
                }
            }else if(pathSegment instanceof PathAutoModule){
                bot.mecanumDrive.move(0,0,0);
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