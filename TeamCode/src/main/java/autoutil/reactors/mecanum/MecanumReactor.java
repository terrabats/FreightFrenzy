package autoutil.reactors.mecanum;

import autoutil.reactors.Reactor;

import static global.General.bot;

public abstract class MecanumReactor extends Reactor {
    @Override
    public double[] getPose() {
        return bot.odometry2.getPose();
    }
}
