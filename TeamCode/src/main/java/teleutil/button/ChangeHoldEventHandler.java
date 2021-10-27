package teleutil.button;

import util.CodeSeg;

public class ChangeHoldEventHandler extends ButtonEventHandler {

    boolean wasPressed = false;

    public ChangeHoldEventHandler(Button b, CodeSeg cs) { super(b, cs); }

    @Override
    protected boolean eventOccurred() {
        return this.pressed() != wasPressed;
    }

    @Override
    public void run() {
        super.run();
        wasPressed = this.pressed();
    }
}
