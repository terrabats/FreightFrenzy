package util;

import util.codeseg.CodeSeg;

// TODO
// FIX this please and make it super easy to use somehow

public class Stage {

    private CodeSeg start, stop, run, exit;
    private boolean isPause = false;
    private final Timer timer = new Timer();
    public Stage(CodeSeg... code){
        switch (code.length){
            case 1:
                run = code[0];
                break;
            case 2:
                run = code[0];
                exit = code[1];
                break;
            case 3:
                run = code[0];
                exit = code[1];
                stop = code[2];
                break;
            case 4:
                start = code[0];
                run = code[1];
                exit = code[2];
                stop = code[3];

        }
    }
    public Stage(boolean isPause){
        isPause = true;
    }

    public void start(){
        timer.reset();
        start.run();
    }
    public void run(){
//        run.run(timer.seconds());
    }
    public void stop(){
        stop.run();
    }
    public boolean isDone(){
//        return exit.runBoolean(timer.seconds());
        return false;
    }
    public boolean isPause(){ return isPause; }
    public void setPause(){
        isPause = true;
    }
}
