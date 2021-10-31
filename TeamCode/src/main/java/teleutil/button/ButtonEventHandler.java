package teleutil.button;

import teleutil.GamepadHandler;
import util.CodeSegs.CodeSeg;

public class ButtonEventHandler {
    protected CodeSeg codeSeg;
    protected Button button;

    public ButtonEventHandler(Button b, CodeSeg cs) { button = b; codeSeg = cs; }

    public void run() { runArgs(); }

    protected boolean eventOccurred() { return true; }

    protected void runArgs(double... args) {
        if (eventOccurred()) {
            codeSeg.run(args);
        }
    }

    protected boolean pressed() { return GamepadHandler.pressedMap.get(button).run(); }

    protected double getValue() { return GamepadHandler.valueMap.get(button).run(); }
}
