package autoutil.executors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import autoutil.paths.PathAutoModule;
import autoutil.paths.PathLine;
import autoutil.paths.PathPose;
import autoutil.paths.PathSegment2;
import geometry.position.Pose;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;
import util.templates.Iterator;

import static global.General.bot;
import static global.General.log;

public class MecanumExecutor extends ExecutorNew implements Iterator {

    public MecanumExecutor(LinearOpMode opMode) {
        super(opMode);
    }

    public MecanumExecutor(ReturnCodeSeg<Boolean> active) {
        super(active);
    }

    @Override
    public void followPath() {
        reactor.init();
        for(PathSegment2 pathSegment: path.getSegments()){
            reactor.setPathSegment(pathSegment);
            if(pathSegment instanceof PathPose) {
                for (Pose pose : pathSegment.getPoses()) {
                    reactor.setTarget(pose.asArray());
                    whileActive(() -> !reactor.isAtTarget(), ()-> {
                        reactor.moveToTarget();
                    });
                    reactor.nextTarget();
                }
            }else if(pathSegment instanceof PathAutoModule){
                ((PathAutoModule) pathSegment).runAutoModule();
                if(!((PathAutoModule) pathSegment).isConcurrent()) {
                    bot.drive.move(0,0,0);
                    whileActive(() -> !((PathAutoModule) pathSegment).isDoneWithAutoModule(), () -> {});
                }
            }
        }
        bot.drive.move(0,0,0);
    }

    @Override
    public boolean condition() {
        return whileOpModeIsActive.run();
    }
}