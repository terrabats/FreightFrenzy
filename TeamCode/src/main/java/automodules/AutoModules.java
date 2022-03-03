package automodules;

import static global.General.bot;

public class AutoModules {

    public StageList DuckTele = new StageList(
            bot.carousel.spinOneDuck(2,0.4,0.7)
    );
}
