package debugging;

import java.util.ArrayList;

import util.ExceptionCatcher;

import static global.General.*;
public class Logger {
    private final ArrayList<Log> logs = new ArrayList<>();
    public void display(String text){
        logs.add(new Log(text));
        telemetry.addData("", text);
        telemetry.update();
    }
    public void monitor(String name, double val){

    }
    public void showLogs(){
        for(Log l: logs){
            telemetry.addData("",l.name);
        }
        telemetry.update();
        while (!gamepad1.x){
            ExceptionCatcher.catchInterrupted(() -> Thread.sleep(100));
        }
    }
}
