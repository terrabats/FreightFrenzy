package debugging;

import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

public class Fault {
    private static int faultNum = 0;

    public Fault() { faultNum = 0; }

    public void check(String msg, Expectation e, Magnitude m, boolean test){
        faultNum++;
        if(!test){
            String out = "Fault: " + faultNum + " Msg: " + msg + " Exp: " + e.toString() + " Mag: " + m.toString();
            log.display(out);
            throw new RuntimeException();
        }
    }

}
