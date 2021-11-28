package teleutil.button;

import java.util.ArrayList;

import teleutil.GamepadHandler;
import util.codeseg.ParameterCodeSeg;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

public class ButtonHandler {
    private final Button button;
    private final ArrayList<ButtonEventHandler> eventHandlers = new ArrayList<>();
    private final GamepadHandler gph;

    public ButtonHandler(Button b, GamepadHandler gph) {
        button = b; this.gph = gph;
    }

    public void addEvent(ButtonEventHandler event) {
        event.set(gph);
        eventHandlers.add(event);
    }

    public void addEvent(ButtonEventType type, ParameterCodeSeg codeSegs) {
        fault.check("Wrong Usage of ToggleEventHandler", Expectation.SURPRISING, Magnitude.MINOR, type != ButtonEventType.TOGGLE);
        if (type == ButtonEventType.NORMAL) {
            eventHandlers.add(new ButtonEventHandler(button, codeSegs, gph));
        } else if (type == ButtonEventType.ON_PRESS) {
            eventHandlers.add(new OnPressEventHandler(button, codeSegs, gph));
        } else if (type == ButtonEventType.CHANGE_HOLD) {
            eventHandlers.add(new ChangeHoldEventHandler(button, codeSegs, gph));
        }
    }

    public void run() {
        for (ButtonEventHandler handler : eventHandlers) {
            handler.run();
        }
    }
}
