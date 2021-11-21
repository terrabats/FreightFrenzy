package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.ParameterCodeSeg;

public class ButtonEventHandler {
    protected ParameterCodeSeg codeSeg;
    protected Button button;

    public ButtonEventHandler(Button b, ParameterCodeSeg cs) { button = b; codeSeg = cs; }

    public void run() { runArgs(pressed() ? 1 : 0); }

    protected boolean eventOccurred() { return pressed(); }

    protected void runArgs(double... args) {
        if (eventOccurred()) {
            codeSeg.run(args);
        }
    }

    protected boolean pressed() { return GamepadHandler.pressedMap.get(button).run(); }

    protected double getValue() { return GamepadHandler.valueMap.get(button).run(); }
}
