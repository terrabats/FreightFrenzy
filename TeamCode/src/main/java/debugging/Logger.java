package debugging;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import teleutil.button.Button;
import teleutil.button.ButtonEventType;
import util.CodeSeg;
import util.ExceptionCatcher;

import static global.General.*;
import static global.General.log;
import static robot.RobotFramework.*;
public class Logger {
    private final TreeMap<String, Log> logs = new TreeMap<>();
    private final TreeMap<String, Object> telemetries = new TreeMap<>();
    private static int logNum = 0;
    private static int displayNum = 0;

    public void display(String text){
        addLog("Display #" + displayNum, new Log(), text);
    }

    public void monitor(String name, Object val){
        addLog(name, new Log(LogType.MONITOR), val);
    }

    private void addLog(String name, Log log, Object o){
        String logName = "Log #"+logNum+": "+ name + ": ";
        if(!logs.containsKey(logName)){
            logs.put(logName, log);
            logNum++;
            displayNum++;
        }
        logs.get(logName).values.add(o);
//        if(!telemetries.containsKey(name)){
//            telemetries.put(name, o);
//        }
    }
    public void showTelemetry(){
        telemetryThread.setCode(args -> {
            for(Entry<String, Object> e: telemetries.entrySet()){
                telemetry.addData(e.getKey(), e.getValue());
            }
            telemetry.update();
        });
    }
    public void showLogs(){
        telemetryThread.setCode(args -> {
            while (!gamepad1.x){
                for(Entry<String, Log> e: logs.entrySet()){
                    telemetry.addData(e.getKey(), e.getValue().values);
                }
                telemetry.update();
            }
            telemetryThread.stopUpdating();
        });
    }
}
