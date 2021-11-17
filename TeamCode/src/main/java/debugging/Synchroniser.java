package debugging;

import util.Timer;

import static global.General.log;

public class Synchroniser {
    private int numUpdates = 0;
    private final Timer lagTimer = new Timer();
    public void resetDelay(){
        numUpdates = 0;
        lagTimer.reset();
    }
    public void logDelay(){
        log.save("Delay (ms)", (1000*lagTimer.seconds())/numUpdates);
    }
}
