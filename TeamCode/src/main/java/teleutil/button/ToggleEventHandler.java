package teleutil.button;

import util.CodeSeg;

public class ToggleEventHandler extends ButtonEventHandler {

    boolean wasPressed = false;

    public ToggleEventHandler(Button b, CodeSeg cs) { super(b, cs); }

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
