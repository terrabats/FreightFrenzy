package util;

//Used to define exit conditions
public abstract class Stage {
    public boolean run(double in) {
        return false;
    } // returns whether it is done or not
    public void runOnStop() { }
    public boolean isPause() { return false; }
}