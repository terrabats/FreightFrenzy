package teleutil.independent;

import elements.Ball;
import robot.RobotFramework;
import util.User;

import static global.General.bot;
import static global.General.log;
import static global.General.mainUser;

public class IndependentRunner {

    public void addIndependent(Independent independent){
        bot.drive.switchUser(User.BACK);
        RobotFramework.backgroundThread.setExecutionCode(() -> {
            bot.drive.checkAccess(User.BACK);
            independent.initAuto();
            independent.runAuto();
            bot.drive.switchUser(mainUser);
            cancelIndependent();
        });
    }

    public void cancelIndependent(){
        RobotFramework.backgroundThread.setExecutionCode(() -> {});
    }
}
