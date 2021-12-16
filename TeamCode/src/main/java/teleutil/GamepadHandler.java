package teleutil;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.Objects;
import java.util.TreeMap;

import teleutil.button.Button;
import teleutil.button.ButtonEventHandler;
import teleutil.button.ButtonHandler;
import util.codeseg.BooleanCodeSeg;
import util.codeseg.DoubleCodeSeg;
import util.codeseg.DoubleParameterCodeSeg;

public class GamepadHandler {
    public Gamepad gamepad;

    public final TreeMap<Button, BooleanCodeSeg> pressedMap = new TreeMap<Button, BooleanCodeSeg>() {{
        put(Button.A, () -> gamepad.a);
        put(Button.B, () -> gamepad.b);
        put(Button.X, () -> gamepad.x);
        put(Button.Y, () -> gamepad.y);
        put(Button.RIGHT_BUMPER, () -> gamepad.right_bumper);
        put(Button.LEFT_BUMPER, () -> gamepad.left_bumper);
        put(Button.DPAD_DOWN, () -> gamepad.dpad_down);
        put(Button.DPAD_UP, () -> gamepad.dpad_up);
        put(Button.DPAD_LEFT, () -> gamepad.dpad_left);
        put(Button.DPAD_RIGHT, () -> gamepad.dpad_right);
        put(Button.LEFT_TRIGGER, () -> gamepad.left_trigger > 0.5);
        put(Button.RIGHT_TRIGGER, () -> gamepad.right_trigger > 0.5);
        put(Button.LEFT_STICK_BUTTON, () -> gamepad.left_stick_button);
        put(Button.RIGHT_STICK_BUTTON, () -> gamepad.right_stick_button);
    }};

    public final TreeMap<Button, DoubleCodeSeg> valueMap = new TreeMap<Button, DoubleCodeSeg>() {{
        put(Button.LEFT_TRIGGER, () -> gamepad.left_trigger);
        put(Button.RIGHT_TRIGGER, () -> gamepad.right_trigger);
        put(Button.LEFT_STICK_Y, () -> gamepad.left_stick_y);
        put(Button.LEFT_STICK_X, () -> gamepad.left_stick_x);
        put(Button.RIGHT_STICK_Y, () -> gamepad.right_stick_y);
        put(Button.RIGHT_STICK_X, () -> gamepad.right_stick_x);
    }};

    public TreeMap<Button, ButtonHandler> handlerMap = new TreeMap<>();

    public GamepadHandler(Gamepad gp) {
        gamepad = gp;
        defineAllButtons();
    }

    public void unlinkAll() {
        handlerMap = new TreeMap<>();
        defineAllButtons();
    }

    public void defineAllButtons() {
        for (Button b : Button.values()) {
            handlerMap.put(b, new ButtonHandler(b, this));
        }
    }

    public void run() {
        for (ButtonHandler handler : handlerMap.values()) {
            handler.run();
        }
    }

    public void link(Button b, Class<? extends ButtonEventHandler> type, DoubleParameterCodeSeg codeSeg) {
        Objects.requireNonNull(handlerMap.get(b)).addEvent(type, codeSeg);
    }
}
