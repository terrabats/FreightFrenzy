package automodules;

import static global.General.bot;

public class AutoModules {

    public StageList DuckTele = new StageList(bot.carousel.spinOneDuck(2,0.4,0.7));

    public StageList UpLift = new StageList(bot.lift.liftEncoder(0.2, 20));
    public StageList ResetLift = new StageList(bot.lift.liftEncoder(-0.2, 0));
}
