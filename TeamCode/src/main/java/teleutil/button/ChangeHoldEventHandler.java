package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.ParameterCodeSeg;

public class ChangeHoldEventHandler extends ButtonEventHandler {

    boolean wasPressed = false;

    public ChangeHoldEventHandler(Button button, ParameterCodeSeg codeSeg, GamepadHandler gph) { super(button, codeSeg, gph); }

    @Override
    protected boolean eventOccurred() {
        return this.pressed() != wasPressed;
    }

}

