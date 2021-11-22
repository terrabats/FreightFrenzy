package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.ParameterCodeSeg;

public class OnPressEventHandler extends ButtonEventHandler {

    boolean wasPressed = false;

    public OnPressEventHandler(Button b, ParameterCodeSeg cs, GamepadHandler gph) { super(b, cs, gph); }

    @Override
    protected boolean eventOccurred() {
        return this.pressed() != wasPressed;
    }

    @Override
    public void run() {
        if (this.pressed()) {
            super.run();
        }
        wasPressed = this.pressed();
    }
}
