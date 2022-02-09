package autoutil.reactors.mecanum;

import autoutil.reactors.Reactor;

import static global.General.bot;

public abstract class MecanumReactor extends Reactor {
    @Override
    public double[] getPose() {
        return new double[]{bot.odometry2.getCurX(), bot.odometry2.getCurY(), bot.odometry2.getCurThetaRad()};
    }
}
