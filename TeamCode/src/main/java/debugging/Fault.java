package debugging;

import static global.General.*;

public class Fault {
    public static int faultNum = 0;
    public static boolean debugging = true;

    // TODO: Fix this whole class, remove debuging and make it so that an error msg list is constantly refreshed so that
    //  it doesnt create so many logs, use telemety items to add them
    //  also create another class for acually dissplaying telemetry so that we can use that for debugging values

    public Fault() { faultNum = 0; }

    public void check(String msg, Expectation e, Magnitude m, boolean test){
        faultNum++;
        if(!test){
            String out = " Msg: " + msg + " Exp: " + e.toString() + " Mag: " + m.toString();
            telemetry.addData("Fault: " + faultNum, out);
            telemetry.update();
            if(debugging){
                //assertEquals(7,7);
                // using assert does not work
                //int num = 1/0;
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
