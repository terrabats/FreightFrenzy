package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.CodeSeg;

/**
 * Occurs (once) when the user presses the button
 */
public class OnPressEventHandler extends ChangeHoldEventHandler {

    public OnPressEventHandler(Button button, CodeSeg codeSeg, GamepadHandler gph) { super(button, codeSeg, gph); }

    @Override
    protected boolean eventOccurred() { return super.eventOccurred() && this.pressed(); }
}
