package util.stage;

public class Stage {
    private Initial initial = new Initial(() -> {});
    private Main main = new Main(() -> {});
    private Exit exit = new Exit(() -> true);
    private Stop stop = new Stop(() -> {});
    private Pause pause = new Pause(false);


    public Stage(Main m){
        main = m;
    }
    public Stage(Initial i, Main m){
        initial = i; main = m;
    }
    public Stage(Main m, Exit e){
        main = m; exit = e;
    }
    public Stage(Initial i, Main m, Exit e){
        initial = i; main = m; exit = e;
    }

    public Stage(Main m, Exit e, Stop s){
        main = m; exit = e; stop = s;
    }
    public Stage(Initial i, Main m, Exit e, Stop s){
        initial = i; main = m; exit = e; stop = s;
    }
    public Stage(Pause p){
        pause = p;
    }

    public void start(){
        initial.start();
    }
    public void run(){
        main.loop();
    }
    public boolean shouldStop(){
        return exit.shouldEnd();
    }
    public void runOnStop(){
        stop.runOnStop();
    }
    public boolean isPause(){
        return pause.isActive();
    }

}
