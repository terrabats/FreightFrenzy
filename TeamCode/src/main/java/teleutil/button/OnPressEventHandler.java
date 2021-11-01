package teleutil.button;

import util.codeseg.CodeSeg;

public class OnPressEventHandler extends ButtonEventHandler {

    boolean wasPressed = false;

    public OnPressEventHandler(Button b, CodeSeg cs) { super(b, cs); }

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
