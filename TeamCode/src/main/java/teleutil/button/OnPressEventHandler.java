package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.ParameterCodeSeg;

public class OnPressEventHandler extends ChangeHoldEventHandler {

    public OnPressEventHandler(Button button, ParameterCodeSeg codeSeg, GamepadHandler gph) { super(button, codeSeg, gph); }

    @Override
    protected void runArgs(double... args) {
        if (args[0] == 1) super.runArgs(args);
    }
}
