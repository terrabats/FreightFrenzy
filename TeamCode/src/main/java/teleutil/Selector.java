package teleutil;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

import teleutil.button.Button;
import util.Timer;
import util.codeseg.BooleanCodeSeg;
import util.codeseg.CodeSeg;
import util.store.Item;

public class Selector {
    private final ArrayList<Item> items = new ArrayList<>();
    private BooleanCodeSeg up;
    private BooleanCodeSeg down;
    private int currentIndex;
    private final boolean wrapAround;
    private final double delay = 0.5; //s
    private final Timer timer = new Timer();

    public Selector(boolean shouldWrapAround){
        wrapAround = shouldWrapAround;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void up(){
        if(currentIndex == items.size()-1) {
            if(wrapAround){
                currentIndex = 0;
            }
        }else {
            currentIndex++;
        }
    }

    public void down(){
        if(currentIndex == 0){
            if(wrapAround){
                currentIndex = items.size()-1;
            }
        }else{
            currentIndex--;
        }
    }

    public void linkButtons(GamepadHandler gph, Button upButton, Button downButton){
        up = gph.pressedMap.get(upButton);
        down = gph.pressedMap.get(downButton);
        timer.reset();
    }

    public void update(){
        if(timer.seconds() > delay) {
            if (up.run()) {
                up();
            } else if (down.run()) {
                down();
            }
            timer.reset();
        }
    }

}
