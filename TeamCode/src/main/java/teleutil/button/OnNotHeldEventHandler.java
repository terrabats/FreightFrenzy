package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.CodeSeg;

/**
 * Occurs (once) when the user releases the button
 */
public class OnNotHeldEventHandler extends ChangeHoldEventHandler {
    public OnNotHeldEventHandler(Button button, CodeSeg codeSeg, GamepadHandler gph) {
        super(button, codeSeg, gph);
    }

    @Override
    protected boolean eventOccurred() {
        return super.eventOccurred() && !this.pressed();
    }
}
