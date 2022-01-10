package teleutil.button;

import java.util.Objects;

import teleutil.GamepadHandler;
import util.codeseg.CodeSeg;

/**
 * Occurs (multiple times) when the button is currently pressed
 */
public class ButtonEventHandler {
    protected CodeSeg codeSeg;
    public Button button;
    protected GamepadHandler gph;

    boolean wasPressed = false;

    public ButtonEventHandler(Button button, CodeSeg codeSeg, GamepadHandler gph) {
        this.button = button; this.codeSeg = codeSeg; this.gph = gph;
    }

    public void runAndUpdate() { run(); wasPressed = this.pressed(); }

    protected boolean eventOccurred() { return pressed(); }

    protected void run() {
        if (eventOccurred()) {
            codeSeg.run();
        }
    }

    protected boolean pressed() { return Objects.requireNonNull(gph.pressedMap.get(button)).run(); }

    protected double getValue() { return Objects.requireNonNull(gph.valueMap.get(button)).run(); }
}
