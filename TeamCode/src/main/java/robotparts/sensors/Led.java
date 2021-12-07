package robotparts.sensors;

import com.qualcomm.robotcore.hardware.DigitalChannel;


import robotparts.RobotPart;
import robotparts.custom.LED;
import robotparts.custom.LED.*;


public class Led extends RobotPart {
    private LED ledfr;
    private LED ledfl;
    private LED ledbr;
    private LED ledbl;

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
