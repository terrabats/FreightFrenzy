package autoutil.paths;

import automodules.StageList;

import static global.General.bot;

public class PathAutoModule extends PathSegment2{
    private final StageList automodule;
    public PathAutoModule(StageList automodule){
        this.automodule = automodule;
    }
    public void runAutoModule(){
        bot.addAutoModule(automodule);
    }
    public boolean isDoneWithAutoModule(){
        return bot.rfsHandler.rfsQueue.isEmpty();
    }

}
