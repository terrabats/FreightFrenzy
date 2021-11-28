package teleutil.button;

public class ToggleEventHandler extends OnPressEventHandler {
    public boolean on = false;

    public ToggleEventHandler(Button b) {
        super(b);
        codeSeg = args -> {
            on = !on;
            if (on) onTurnOn();
            else onTurnOff();
        };
    }

    @Override
    public void run() {
        super.run();
        if (on) whenOn(); else whenOff();
    }

    public void onTurnOn() { }

    public void onTurnOff() { }

    public void whenOn() { }

    public void whenOff() { }
}
