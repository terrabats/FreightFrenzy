package teleutil.button;

import util.codeseg.CodeSeg;

public class ChangeHoldEventHandler extends ButtonEventHandler {

    boolean wasPressed = false;

    public ChangeHoldEventHandler(Button b, CodeSeg cs) { super(b, cs); }

    @Override
    protected boolean eventOccurred() {
        return this.pressed() != wasPressed;
    }

    @Override
    public void run() {
        runArgs(this.pressed() ? 1 : 0);
        wasPressed = this.pressed();
    }
}
