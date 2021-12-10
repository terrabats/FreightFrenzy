package automodules.stage;

public class Pause {
    private boolean isActive;
    public Pause(){
        isActive = true;
    }
    public Pause(boolean activate){
        isActive = activate;
    }
    public boolean isActive(){
        return isActive;
    }
}
