package teleutil.button;

import util.codeseg.CodeSeg;
import util.codeseg.ParameterCodeSeg;

public class OnPressEventHandler extends ButtonEventHandler {

    boolean wasPressed = false;

    public OnPressEventHandler(Button b, ParameterCodeSeg cs) { super(b, cs); }

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
