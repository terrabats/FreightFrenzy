package robotparts.sensors;


import robotparts.RobotPart;
import robotparts.electronics.output.OLed;
import robotparts.electronics.output.OLed.*;


/**
 * NOTE: Uncommented
 */

public class Led extends RobotPart {
    private OLed ledfr;
    private OLed ledfl;
    private OLed ledbr;
    private OLed ledbl;

    @Override
    public void init() {
        ledfr = createLED("ledfr");
        ledbr = createLED("ledbr");
        ledfl = createLED("ledfl");
        ledbl = createLED("ledbl");
    }

    public void setColorOfLEDs(LEDColor color){
        ledfr.setColor(color);
        ledbr.setColor(color);
        ledfl.setColor(color);
        ledbl.setColor(color);
    }
}
