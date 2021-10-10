package util;

import robot.TerraBot;

public class Fault {
    public static int faultNum = 0;
    public static boolean debugging = true;

    public void check(String msg, Expectation e, Magnitude m, boolean test){
        faultNum++;
        if(!test){
            String out = " Msg: " + msg + " Exp: " + e.toString() + " Mag: " + m.toString();
            TerraBot.telemetry.addData("Fault: " + Integer.toString(faultNum), out);
            TerraBot.telemetry.update();
            if(debugging){
                assert false;
            }
        }
    }
    public enum Expectation{
        TRIVIAL,
        OBVIOUS,
        EXPECTED,
        SURPRISING,
        UNEXPECTED,
        INCONCEIVABLE
    }
    public enum Magnitude{
        NEGLIGIBLE,
        MINOR,
        MODERATE,
        MAJOR,
        CRITICAL,
        CATASTROPHIC
    }

}
