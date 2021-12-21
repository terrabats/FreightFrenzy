package teleutil.button;

import java.util.ArrayList;

import teleutil.GamepadHandler;
import util.ExceptionCatcher;
import util.codeseg.CodeSeg;

public class ButtonHandler {
    private final Button button;
    private final ArrayList<ButtonEventHandler> eventHandlers = new ArrayList<>();
    private final GamepadHandler gph;

    public ButtonHandler(Button b, GamepadHandler gph) {
        button = b; this.gph = gph;
    }

    public <T> void addEvent(Class<T> type, CodeSeg codeSegs) {
        ExceptionCatcher.catchNewInstance(() -> {
            T obj = type
                .getDeclaredConstructor(Button.class, CodeSeg.class, GamepadHandler.class)
                .newInstance(button, codeSegs, gph);
            eventHandlers.add((ButtonEventHandler) obj);
        });

    }

    public void run() {
        for (ButtonEventHandler handler : eventHandlers) {
            handler.runAndUpdate();
        }
    }
}
