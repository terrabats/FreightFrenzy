package teleutil.button;

import java.util.Objects;

import teleutil.GamepadHandler;
import util.codeseg.ParameterCodeSeg;

import static global.General.*;

public class ButtonEventHandler {
    protected ParameterCodeSeg codeSeg;
    protected Button button;
    protected GamepadHandler gph;

    public ButtonEventHandler(Button b, ParameterCodeSeg cs, GamepadHandler gph) { button = b; codeSeg = cs; this.gph = gph; }

    public void run() { runArgs(pressed() ? 1 : 0); }

    protected boolean eventOccurred() { return pressed(); }

    protected void runArgs(double... args) {
        if (eventOccurred()) {
            codeSeg.run(args);
        }
    }

    protected boolean pressed() { return Objects.requireNonNull(gph.pressedMap.get(button)).run(); }

    protected double getValue() { return Objects.requireNonNull(gph.valueMap.get(button)).run(); }
}
