package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.DoubleParameterCodeSeg;

public class ChangeHoldEventHandler extends ButtonEventHandler {

    public ChangeHoldEventHandler(Button button, DoubleParameterCodeSeg codeSeg, GamepadHandler gph) { super(button, codeSeg, gph); }

    @Override
    protected boolean eventOccurred() {
        return this.pressed() != wasPressed;
    }

}

