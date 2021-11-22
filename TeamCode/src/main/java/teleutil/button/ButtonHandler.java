package teleutil.button;

import java.util.ArrayList;

import teleutil.GamepadHandler;
import util.codeseg.ParameterCodeSeg;

public class ButtonHandler {
    private final Button button;
    private final ArrayList<ButtonEventHandler> eventHandlers = new ArrayList<>();
    private final GamepadHandler gph;

    public ButtonHandler(Button b, GamepadHandler gph) {
        button = b; this.gph = gph;
    }

    public void addEvent(ButtonEventType type, ParameterCodeSeg codeSeg) {
        if (type == ButtonEventType.NORMAL) {
            eventHandlers.add(new ButtonEventHandler(button, codeSeg, gph));
        } else if (type == ButtonEventType.ON_PRESS) {
            eventHandlers.add(new OnPressEventHandler(button, codeSeg, gph));
        }
    }

    public void run() {
        for (ButtonEventHandler handler : eventHandlers) {
            handler.run();
        }
    }
}
