package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.CodeSeg;

public class ChangeHoldEventHandler extends ButtonEventHandler {

    public ChangeHoldEventHandler(Button button, CodeSeg codeSeg, GamepadHandler gph) { super(button, codeSeg, gph); }

    @Override
    protected boolean eventOccurred() {
        return this.pressed() != wasPressed;
    }

}

