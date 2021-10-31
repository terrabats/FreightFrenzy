package debugging;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import util.condition.Status;

import static global.General.*;

public class Logger {
    private final LinkedHashMap<String, Log> logs = new LinkedHashMap<>();
    private int logNum = 0;

    public void display(Object val){
        addLog(String.valueOf(val), new Log(getLogName("Display")), String.valueOf(val));
    }

    public void monitor(String name, Object val){
        addLog(name, new Log(getLogName(name), LogType.MONITOR), val);
    }

    public void status(String name, Status status){
        addLog(name, new Log(getLogName(name), LogType.STATUS), status);
    }

    private String getLogName(String name){
        return "Log #"+logNum+": "+ name;
    }

    private void addLog(String name, Log log, Object o){
        if(!logs.containsKey(name)){
            logs.put(name, log);
            logNum++;
        }
        switch (log.logType){
            case NORMAL:
                logs.get(name).addNewOnly(o);
                break;
            case MONITOR:
                logs.get(name).add(o);
                break;
            case STATUS:
                logs.get(name).noTelemetry = true;
                logs.get(name).addNewOnly(o);
                break;
        }
    }
    public void showTelemetry(){
        for(String key: logs.keySet()){
            if(!logs.get(key).noTelemetry) {
                telemetry.addData(logs.get(key).name, logs.get(key).getCurrentObject());
            }
        }
        telemetry.update();
    }
    public void showLogs(){
        for(String key: logs.keySet()){
            android.util.Log.println(7,logs.get(key).name, String.valueOf(logs.get(key).getValues()));
        }
    }
}
