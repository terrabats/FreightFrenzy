package teleutil.button;

import java.util.ArrayList;

import util.codeseg.CodeSeg;
import util.codeseg.ParameterCodeSeg;

public class ButtonHandler {
    private final Button button;
    private final ArrayList<ButtonEventHandler> eventHandlers = new ArrayList<>();

    public ButtonHandler(Button b) {
        button = b;
    }

    public void addEvent(ButtonEventType type, ParameterCodeSeg codeSeg) {
        if (type == ButtonEventType.NORMAL) {
            eventHandlers.add(new ButtonEventHandler(button, codeSeg));
        } else if (type == ButtonEventType.ON_PRESS) {
            eventHandlers.add(new OnPressEventHandler(button, codeSeg));
        }
    }

    public void run() {
        for (ButtonEventHandler handler : eventHandlers) {
            handler.run();
        }
    }
}
