package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.ParameterCodeSeg;

public class NotHeldEventHandler extends ButtonEventHandler {
    public NotHeldEventHandler(Button button, ParameterCodeSeg codeSeg, GamepadHandler gph) {
        super(button, codeSeg, gph);
    }

    @Override
    protected boolean eventOccurred() {
        return !super.eventOccurred();
    }
}
