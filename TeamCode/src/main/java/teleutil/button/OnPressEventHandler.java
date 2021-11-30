package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.ParameterCodeSeg;

public class OnPressEventHandler extends ChangeHoldEventHandler {

    public OnPressEventHandler(Button button, ParameterCodeSeg codeSeg, GamepadHandler gph) { super(button, codeSeg, gph); }

    @Override
    protected boolean eventOccurred() { return super.eventOccurred() && this.pressed(); }
}
