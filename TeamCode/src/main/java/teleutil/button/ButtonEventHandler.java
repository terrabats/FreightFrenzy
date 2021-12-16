package teleutil.button;

import java.util.Objects;

import teleutil.GamepadHandler;
import util.codeseg.DoubleParameterCodeSeg;

public class ButtonEventHandler {
    protected DoubleParameterCodeSeg codeSeg;
    public Button button;
    protected GamepadHandler gph;

    boolean wasPressed = false;

    public ButtonEventHandler(Button button, DoubleParameterCodeSeg codeSeg, GamepadHandler gph) {
        this.button = button; this.codeSeg = codeSeg; this.gph = gph;
    }

    public void run() { runArgs(pressed() ? 1 : 0); wasPressed = this.pressed(); }

    protected boolean eventOccurred() { return pressed(); }

    protected void runArgs(double... args) {
        if (eventOccurred()) {
            codeSeg.run(args);
        }
    }

    protected boolean pressed() { return Objects.requireNonNull(gph.pressedMap.get(button)).run(); }

    protected double getValue() { return Objects.requireNonNull(gph.valueMap.get(button)).run(); }
}
