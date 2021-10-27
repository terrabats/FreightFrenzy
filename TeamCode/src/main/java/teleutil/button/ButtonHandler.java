package teleutil.button;

import java.util.ArrayList;

import util.CodeSeg;

public class ButtonHandler {
    private final Button button;
    private final ArrayList<ButtonEventHandler> eventHandlers = new ArrayList<>();

    public ButtonHandler(Button b) {
        button = b;
    }

    public void addEvent(ButtonEventType type, CodeSeg codeSeg) {
        if (type == ButtonEventType.NORMAL) {
            eventHandlers.add(new ButtonEventHandler(button, codeSeg));
        } else if (type == ButtonEventType.CHANGE_HOLD) {
            eventHandlers.add(new ChangeHoldEventHandler(button, codeSeg));
        } // TODO: ADD ON TO THIS WHEN NEEDED
    }

    public void run() {
        for (ButtonEventHandler handler : eventHandlers) {
            handler.run();
        }
    }
}
