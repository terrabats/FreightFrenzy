package debugging;

import util.Timer;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

public class Fault {
    private int faultNum = 0;
    private final boolean unsafeMode = false;

    public void warn(String msg, boolean test){
        createFault(" Msg: " + msg, test, false);
    }
    public void warn(String msg, Expectation e, Magnitude m, boolean test){
        createFault(" Msg: " + msg + " Exp: " + e.toString() + " Mag: " + m.toString(), test, false);
    }
    public void check(String msg, boolean test) {
        createFault(" Msg: " + msg, test, true);
    }
    public void check(String msg, Expectation e, Magnitude m, boolean test){
        createFault(" Msg: " + msg + " Exp: " + e.toString() + " Mag: " + m.toString(), test, true);
    }

    private void createFault(String out, boolean test, boolean createException){
        if(!test){
            faultNum++;
            if(unsafeMode){
                log.display( "Fault: " + faultNum + out);
            }else{
                log.display( "Fault: " + out);
                if(createException) {
                    throw new RuntimeException(out);
                }
            }
        }
    }
}
