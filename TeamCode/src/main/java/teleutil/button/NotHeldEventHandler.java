package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.CodeSeg;

/**
 * Occurs (multiple times) when the button is not pressed
 */
public class NotHeldEventHandler extends ButtonEventHandler {
    public NotHeldEventHandler(Button button, CodeSeg codeSeg, GamepadHandler gph) {
        super(button, codeSeg, gph);
    }

    @Override
    protected boolean eventOccurred() {
        return !super.eventOccurred();
    }
}
