package teleutil.button;

import java.lang.reflect.InvocationTargetException;
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

    public <T> void addEvent(Class<T> type, ParameterCodeSeg codeSegs) {
        try {
            T obj = type
                    .getDeclaredConstructor(Button.class, ParameterCodeSeg.class, GamepadHandler.class)
                    .newInstance(button, codeSegs, gph);
            eventHandlers.add((ButtonEventHandler) obj);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        for (ButtonEventHandler handler : eventHandlers) {
            handler.run();
        }
    }
}
