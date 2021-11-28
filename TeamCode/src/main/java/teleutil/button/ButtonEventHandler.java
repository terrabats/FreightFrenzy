package teleutil.button;

import java.util.Objects;

import teleutil.GamepadHandler;
import util.codeseg.ParameterCodeSeg;

import static global.General.*;

public class ButtonEventHandler {
    protected ParameterCodeSeg codeSeg;
    public Button button;
    protected GamepadHandler gph;

    boolean wasPressed = false;

    public ButtonEventHandler(Button b) { button = b; }

    public ButtonEventHandler(Button button, ParameterCodeSeg codeSeg, GamepadHandler gph) {
        this.button = button; this.codeSeg = codeSeg; this.gph = gph;
    }

    public void set(GamepadHandler gamepadHandler) { gph = gamepadHandler; }

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
