package autoutil.controllers;

public class BangBang extends Controller{

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
                output = s;
            } else {
                output = -s;
            }
        }else{
            if((errorProfiler.getCurrentTime() - currentTime) > maximumTime){
                isAtTarget = true;
            }else{
                output = 0;
                currentTime = errorProfiler.getCurrentTime();
            }
        }
    }
}
