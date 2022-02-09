package autoutil.executors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import autoutil.paths.PathSegment;
import autoutil.paths.PathSegment2;
import autoutil.reactors.Reactor;
import autoutil.reactors.mecanum.MecanumReactor;
import geometry.position.Pose;

import static global.General.bot;

public class MecanumExecutor extends ExecutorReal{

    public MecanumExecutor(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void followPath() {
        for(PathSegment2 pathSegment: path.getSegments()){
            for(Pose pose: pathSegment.getPoses()){
                reactor.setTarget(pose.asArray());
                while (whileOpModeIsActive.run() && !reactor.isAtTarget()){
                    reactor.moveToTarget();
                }
                reactor.nextTarget();
            }
        }
        bot.mecanumDrive.move(0,0,0);
    }
}
