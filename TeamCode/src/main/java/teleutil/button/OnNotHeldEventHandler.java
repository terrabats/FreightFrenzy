package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.DoubleParameterCodeSeg;

public class OnNotHeldEventHandler extends ChangeHoldEventHandler {
    public OnNotHeldEventHandler(Button button, DoubleParameterCodeSeg codeSeg, GamepadHandler gph) {
        super(button, codeSeg, gph);
    }

    @Override
    protected boolean eventOccurred() {
        return super.eventOccurred() && !this.pressed();
    }
}
