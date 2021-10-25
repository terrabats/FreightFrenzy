package teleutil;

import util.BooleanCodeSeg;
import util.CodeSeg;
import util.DoubleCodeSeg;
import util.Timer;

public class ButtonHandler {
    private final Timer t = new Timer();

    private final BooleanCodeSeg pressed;
    private final DoubleCodeSeg value;

    private CodeSeg code = (double... args) -> {};

    public ButtonHandler(BooleanCodeSeg pressed, DoubleCodeSeg value) {
        this.pressed = pressed;
        this.value = value;
        t.reset();
    }

    private boolean isPressed() {
        return pressed.run();
    }

    private double getValue() {
        return value.run();
    }

    public void setCode(CodeSeg codeSeg) { code = codeSeg; }

    public void run() {
        code.run(t.seconds(), isPressed() ? 1 : 0, getValue());
    }
}
