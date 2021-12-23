package teleutil;

import java.util.ArrayList;

import teleutil.button.Button;
import util.Timer;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;
import util.codeseg.ParameterCodeSeg;
import util.condition.Status;
import util.store.Item;

public class Selector<T> {
    private ArrayList<Item<T>> items = new ArrayList<>();
    private final ArrayList<String> itemClassNames = new ArrayList<>();
    private ReturnCodeSeg<Boolean> up;
    private ReturnCodeSeg<Boolean> down;
    private int currentIndex;
    private final boolean wrapAround;
    private double delay = 0.2;
    private Timer updateTimer = new Timer();
    private CodeSeg next = () -> {};
    private CodeSeg end = () -> {};
    private Status status = Status.IDLE;

    public Selector(boolean shouldWrapAround){
        wrapAround = shouldWrapAround;
    }

    public void addItem(Item<T> item){
        items.add(item);
        itemClassNames.add(item.getValue().getClass().getSimpleName());
    }

    private void init(){
        items = new ArrayList<>();
        updateTimer.reset();
    }

    public void init(GamepadHandler gph, Button upButton, Button downButton){
        up = gph.pressedMap.get(upButton); down = gph.pressedMap.get(downButton);
        init();
    }

    public void init(ReturnCodeSeg<Boolean> upSeg, ReturnCodeSeg<Boolean> downSeg){
        up = upSeg; down = downSeg;
        init();
    }
    public void init(double timeBetweenUpdates){
        this.delay = timeBetweenUpdates;
        init(() -> true, () -> false);
    }

    public void resetUpdateTimer(){
        updateTimer.reset();
    }

    public boolean isOnFirst(){ return currentIndex == 0; }

    public boolean isOnLast(){ return currentIndex == items.size()-1; }

    public void up(){
        if(isOnLast()) {
            end.run();
            if(wrapAround){
                currentIndex = 0;
            }
        }else {
            currentIndex++;
        }
    }

    public void down(){
        if(isOnFirst()){
            if(wrapAround){
                currentIndex = items.size()-1;
            }
        }else{
            currentIndex--;
        }
    }

    public void update(){
        if(updateTimer.seconds() > delay) {
            if (up.run()) {
                updateTimer.reset();
                next.run();
                up();
            } else if (down.run()) {
                updateTimer.reset();
                next.run();
                down();
            }
        }
    }


    public int getCurrentIndex(){
        return currentIndex;
    }
    public Item<T> getCurrentItem(){
        return items.get(currentIndex);
    }

    public void runToAll(ParameterCodeSeg<T> seg){
        for(Item<T> item: items){
            seg.run(item.getValue());
        }
    }
    public void runToCurrentItem(ParameterCodeSeg<T> seg){
        seg.run(getCurrentItem().getValue());
    }
    public void runOnNext(CodeSeg seg){
         next = seg;
    }
    public void runOnEnd(CodeSeg seg){
        end = seg;
    }

    public Status getStatus(){
        return status;
    }

    public boolean isActive(){ return status.equals(Status.ACTIVE); }
    public boolean isInActive(){ return !status.equals(Status.ACTIVE); }
    public void idle(){setStatus(Status.IDLE);}
    public void active(){setStatus(Status.ACTIVE);}
    public void setStatus(Status status){
        this.status = status;
    }

    public ArrayList<String> getItemClassNames(){ return itemClassNames; }

}
