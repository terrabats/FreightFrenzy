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
     * Warn that something has gone wrong with a message if test does not match the correct value
     * @param msg
     * @param test
     * @param correct
     */
    public void warn(String msg, boolean test, boolean correct){
        createFault(" Msg: " + msg, test, test != correct);
    }

    /**
     * Same as above except more detailed
     * @link warn
     * @param msg
     * @param e
     * @param m
     * @param test
     * @param correct
     */
    public void warn(String msg, Expectation e, Magnitude m, boolean test, boolean correct){
        createFault(" Msg: " + msg + " Exp: " + e.toString() + " Mag: " + m.toString(), test != correct, false);
    }

    /**
     * Same as warn except throws an exception (crashes the app)
     * NOTE: Only use when something major has gone wrong or is important
     * @param msg
     * @param test
     * @param correct
     */
    public void check(String msg, boolean test, boolean correct) {
        createFault(" Msg: " + msg, test != correct, true);
    }

    /**
     * Same as above with more details
     * @link check
     * @param msg
     * @param e
     * @param m
     * @param test
     * @param correct
     */
    public void check(String msg, Expectation e, Magnitude m, boolean test, boolean correct){
        createFault(" Msg: " + msg + " Exp: " + e.toString() + " Mag: " + m.toString(), test != correct, true);
    }

    /**
     * Create a fault using a message, display the faultNum if in unsafe mode
     * @param out
     * @param failed
     * @param createException
     */
    private void createFault(String out, boolean failed, boolean createException){
        if(failed){
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
