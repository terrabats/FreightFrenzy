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

//    public <T extends ButtonEventHandler> void addEvent (ParameterCodeSeg codeSegs) {
//        eventHandlers.add(type.newInstance());
////        if (type == ButtonEventType.NORMAL) {
////            eventHandlers.add(new ButtonEventHandler(button, codeSegs, gph));
////        } else if (type == ButtonEventType.ON_PRESS) {
////            eventHandlers.add(new OnPressEventHandler(button, codeSegs, gph));
////        } else if (type == ButtonEventType.CHANGE_HOLD) {
////            eventHandlers.add(new ChangeHoldEventHandler(button, codeSegs, gph));
////        } else if (type == ButtonEventType.ON_TURN_ON) {
////            eventHandlers.add(new OnTurnOnEventHandler(button, codeSegs, gph));
////        } else if (type == ButtonEventType.ON_TURN_OFF) {
////            eventHandlers.add(new OnTurnOffEventHandler(button, codeSegs, gph));
////        } else if (type == ButtonEventType.WHEN_ON) {
////            eventHandlers.add(new WhenOnEventHandler(button, codeSegs, gph));
////        } else if (type == ButtonEventType.WHEN_OFF) {
////            eventHandlers.add(new WhenOffEventHandler(button, codeSegs, gph));
////        }
//    }

    public void run() {
        for (ButtonEventHandler handler : eventHandlers) {
            handler.run();
        }
    }
}
