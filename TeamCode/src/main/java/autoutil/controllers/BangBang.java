package autoutil.controllers;

public class BangBang extends Controller1D {

    private double s;
    private double maximumTime;

    public BangBang(double s, double maximumTime){
        this.s = s;
        this.maximumTime = maximumTime;
    }

    public void setMaximumTime(double maximumTime){ this.maximumTime = maximumTime; }

    @Override
    public void update() {
        if(!isWithinAccuracyRange()) {
            if (getError() > 0) {
                setOutput(s);
            } else {
                setOutput(-s);
            }
        }else{
            if((errorProfiler.getCurrentTime() - currentTime) > maximumTime){
                isAtTarget = true;
            }else{
                setOutput(0);
                currentTime = errorProfiler.getCurrentTime();
            }
        }
    }
}
