package teleutil;

import java.util.ArrayList;

import teleutil.button.Button;
import util.Timer;
import util.codeseg.BooleanCodeSeg;
import util.codeseg.CodeSeg;
import util.codeseg.TypeParameterCodeSeg;
import util.condition.Status;
import util.store.Item;

public class Selector<T> {
    private ArrayList<Item<T>> items = new ArrayList<>();
    private BooleanCodeSeg up;
    private BooleanCodeSeg down;
    private int currentIndex;
    private final boolean wrapAround;
    private double delay = 0.5; //s
    private final Timer updateTimer = new Timer();
    private CodeSeg next = () -> {};
    private Status status = Status.IDLE;

    public Selector(boolean shouldWrapAround){
        wrapAround = shouldWrapAround;
    }

    public void addItem(Item<T> item){
        items.add(item);
    }

    private void init(){
        items = new ArrayList<>();
        updateTimer.reset();
    }

    public void init(GamepadHandler gph, Button upButton, Button downButton){
        up = gph.pressedMap.get(upButton); down = gph.pressedMap.get(downButton);
        init();
    }

    public void init(BooleanCodeSeg upSeg, BooleanCodeSeg downSeg){
        up = upSeg; down = downSeg;
        init();
    }
    public void init(double timeBetweenUpdates){
        this.delay = timeBetweenUpdates;
        init(() -> true, () -> false);
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

    private void update(){
        if(updateTimer.seconds() > delay) {
            if (up.run()) {
                next.run();
                up();
            } else if (down.run()) {
                next.run();
                down();
            }
            updateTimer.reset();
        }
    }

    public void update(boolean showTelemetry){
        update();
    }

    public int getCurrentIndex(){
        return currentIndex;
    }
    public Item<T> getCurrentItem(){
        return items.get(currentIndex);
    }

    public void runToAll(TypeParameterCodeSeg<T> seg){
        for(Item<T> item: items){
            seg.run(item.getValue());
        }
    }
    public void runToCurrentItem(TypeParameterCodeSeg<T> seg){
        seg.run(getCurrentItem().getValue());
    }
    public void runOnNext(CodeSeg seg){
         next = seg;
    }

    public Status getStatus(){
        return status;
    }
    public boolean isActive(){ return status.equals(Status.ACTIVE); }
    public boolean isInActive(){ return !status.equals(Status.ACTIVE); }
    public void setStatus(Status status){
        this.status = status;
    }

    public ArrayList<String> getItemValuesClassNames(){
        ArrayList<String> out = new ArrayList<>();
        for(Item<T> item: items){
            out.add(item.getValue().getClass().getSimpleName());
        }
        return out;
    }
}
