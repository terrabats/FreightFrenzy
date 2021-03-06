package debugging;

import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

public class Fault {
    /**
     * Is the fault in unsafe mode (i.e. the fault will not throw exceptions even in check)
     * NOTE: this should only be set to true when the robot is being run in the competition
     */
    private final boolean unsafeMode = false;

    /**
     * Warn that something went wrong with a message
     * @param msg
     */
    public void warn(String msg){
        createFault(getName(msg), true, false);
    }

    /**
     * Warn with more details
     * @param msg
     * @param e
     * @param m
     */
    public void warn(String msg, Expectation e, Magnitude m){
        createFault(getName(msg, e, m), true, false);
    }

    /**
     * Warn that something has gone wrong with a message if test does not match the correct value
     * @param msg
     * @param test
     * @param correct
     */
    public void warn(String msg, boolean test, boolean correct){
        createFault(getName(msg), test != correct, false);
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
        createFault(getName(msg, e, m), test != correct, false);
    }

    /**
     * Same as warn except throws exception
     * NOTE: Only use when something major has gone wrong or is important
     * @param msg
     */
    public void check(String msg) {
        createFault(getName(msg), true, true);
    }

    /**
     * Same as above with more details
     * @param msg
     * @param e
     * @param m
     */
    public void check(String msg, Expectation e, Magnitude m) {
        createFault(getName(msg, e, m), true, true);
    }

    /**
     * Same as warn except throws an exception if test does not match the correct value
     * @param msg
     * @param test
     * @param correct
     */
    public void check(String msg, boolean test, boolean correct) {
        createFault(getName(msg), test != correct, true);
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
        createFault(getName(msg, e, m), test != correct, true);
    }

    /**
     * Gets the name given a message
     * @param msg
     * @return
     */
    private String getName(String msg){
        return "Msg: " + msg;
    }

    /**
     * Gets the name given a message and expectation and magnitude
     * @param msg
     * @param e
     * @param m
     * @return
     */
    private String getName(String msg, Expectation e, Magnitude m){
        return getName(msg) + " Exp: " + e.toString() + " Mag: " + m.toString();
    }

    /**
     * Create a fault using a message, display the faultNum if in unsafe mode
     * @param out
     * @param failed
     * @param createException
     */
    private void createFault(String out, boolean failed, boolean createException){
        if(failed){
            log.showAndRecord(out,  "Please check your code for logical errors");
            if(!unsafeMode && createException){
                RuntimeException exception = new RuntimeException(" "+out);
                exception.printStackTrace();
                throw exception;
            }
        }
    }
}
