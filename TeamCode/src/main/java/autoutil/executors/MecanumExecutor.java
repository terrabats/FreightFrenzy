package autoutil.executors;

import static global.General.bot;

public class MecanumExecutor extends Executor {
    @Override
    public void move(double f, double t) {
        bot.mecanumDrive.move(f, 0, t);
    }
}
