package teleutil.independent;

import elements.Ball;
import robot.RobotFramework;
import util.User;

import static global.General.bot;

public class IndependentRunner {


    public void addIndependent(Independent independent){
        bot.drive.switchUser(User.BACK);
        RobotFramework.backgroundThread.setExecutionCode(() -> {
            independent.runAuto();
            bot.drive.returnPart();
        });
    }
}
