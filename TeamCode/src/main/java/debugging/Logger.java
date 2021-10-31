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
    private final TreeMap<String, CodeSeg> telemetries = new TreeMap<>();
    private static int logNum = 0;
    private static int displayNum = 0;

    public void display(String text){
        addLog("Display #" + displayNum, new Log(), text, args -> telemetry.addData("display: ", text));
    }

    public void monitor(String name, Object val){
        addLog(name, new Log(LogType.MONITOR), val, args -> telemetry.addData(name + ": ", val));
    }

    private void addLog(String name, Log log, Object o, CodeSeg telemetryCode){
        String logName = "Log #"+logNum+": "+ name + ": ";
        if(!logs.containsKey(logName)){
            logs.put(logName, log);
            logNum++;
            displayNum++;
        }
//        if(!telemetries.containsKey(logName)){
//            telemetries.put(logName, telemetryCode);
//        }
        logs.get(logName).values.add(o);
    }
    public void showTelemetry(){
//        CodeSeg updateCode = args -> {
//            for(Entry<String, CodeSeg> e: telemetries.entrySet()){
//                e.getValue().run();
//            }
//        };
//        telemetryThread.setCode(updateCode);
        telemetry.update();
    }
    public void showLogs(){
        for(Entry<String, Log> e: logs.entrySet()){
            telemetry.addData(e.getKey(), e.getValue().values);
        }
        telemetry.update();
//        while (!gamepad1.x){
//            ExceptionCatcher.catchInterrupted(() -> Thread.sleep(100));
//        }
    }
}
