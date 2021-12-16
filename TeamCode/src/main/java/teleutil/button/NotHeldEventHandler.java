package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.DoubleParameterCodeSeg;

public class NotHeldEventHandler extends ButtonEventHandler {
    public NotHeldEventHandler(Button button, DoubleParameterCodeSeg codeSeg, GamepadHandler gph) {
        super(button, codeSeg, gph);
    }

    @Override
    protected boolean eventOccurred() {
        return !super.eventOccurred();
    }
}
