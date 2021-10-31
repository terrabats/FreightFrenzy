package debugging;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import teleutil.button.Button;
import teleutil.button.ButtonEventType;
import util.CodeSegs.CodeSeg;
import util.ExceptionCatcher;

import static global.General.*;
import static global.General.log;
import static robot.RobotFramework.*;
public class Logger {
    private final TreeMap<String, Log> logs = new TreeMap<>();
    private static int logNum = 0;

    public void display(String text){
        addLog(text, new Log(getLogName("Display")), text);
    }

    public void monitor(String name, Object val){
        addLog(name, new Log(getLogName(name), LogType.MONITOR), val);
    }

    private String getLogName(String name){
        return "Log #"+logNum+": "+ name;
    }

    private void addLog(String name, Log log, Object o){
        if(!logs.containsKey(name)){
            logs.put(name, log);
            logNum++;
        }
        logs.get(name).values.add(o);
    }
    public void showTelemetry(){
        for(String key: logs.descendingKeySet()){
            telemetry.addData(logs.get(key).name, logs.get(key).getRecentObject());
        }
        telemetry.update();
    }
    public void showLogs(){
        for(Entry<String, Log> e: logs.entrySet()){
            android.util.Log.d(e.getValue().name, String.valueOf(e.getValue().values));
        }
    }
}
