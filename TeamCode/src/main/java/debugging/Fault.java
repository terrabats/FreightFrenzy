package debugging;

import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

public class Fault {
    /**
     * The number of faults that occurred since the start
     */
    private int faultNum = 0;
    /**
     * Is the fault in unsafe mode (i.e. the fault will not throw exceptions even in check)
     * NOTE: this should only be set to true when the robot is being run in the competition
     */
    private final boolean unsafeMode = false;

    /**
     * Warn that something has gone wrong with a message if test is true
     * @param msg
     * @param test
     */
    public void warn(String msg, boolean test){
        createFault(" Msg: " + msg, test, false);
    }

    /**
     * Same as above except more detailed
     * @link warn
     * @param msg
     * @param e
     * @param m
     * @param test
     */
    public void warn(String msg, Expectation e, Magnitude m, boolean test){
        createFault(" Msg: " + msg + " Exp: " + e.toString() + " Mag: " + m.toString(), test, false);
    }

    /**
     * Same as warn except throws an exception (crashes the app)
     * NOTE: Only use when something major has gone wrong or is important
     * @param msg
     * @param test
     */
    // TODO NEW
    // add third parameter of what the boolean SHOULD be
    public void check(String msg, boolean test) {
        createFault(" Msg: " + msg, test, true);
    }

    /**
     * Same as above with more details
     * @link check
     * @param msg
     * @param e
     * @param m
     * @param test
     */
    public void check(String msg, Expectation e, Magnitude m, boolean test){
        createFault(" Msg: " + msg + " Exp: " + e.toString() + " Mag: " + m.toString(), test, true);
    }

    /**
     * Create a fault using a message, display the faultNum if in unsafe mode
     * @param out
     * @param test
     * @param createException
     */
    private void createFault(String out, boolean test, boolean createException){
        if(!test){
            faultNum++;
            if(unsafeMode){
                log.showAndRecord( "Fault: " ,  faultNum + out);
            }else{
                log.showAndRecord( "Fault: " ,  out);
                if(createException) {
                    RuntimeException exception = new RuntimeException(out);
                    exception.printStackTrace();
                    throw exception;
                }
            }
        }
    }
}
