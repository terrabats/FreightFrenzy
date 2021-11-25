package debugging;

import java.util.LinkedHashMap;

import static global.General.*;

public class Logger {
    private final LinkedHashMap<String, Log> logs = new LinkedHashMap<>();
    private int logNum = 0;

    public void display(Object val){
        addLog(String.valueOf(val), new Log(getLogName("Display")), String.valueOf(val));
    }
    public void display(String caption, Object val){
        addLog(caption, new Log(getLogName(caption)), String.valueOf(val));
    }

    public void monitor(String name, Object val){
        addLog(name, new Log(getLogName(name), LogType.MONITOR), val);
    }

    public void watch(Object val){
        addLog(String.valueOf(val), new Log(getLogName("Watch"), LogType.WATCH), String.valueOf(val));
    }
    public void watch(String caption, Object val){
        addLog(caption, new Log(getLogName(caption), LogType.WATCH), String.valueOf(val));
    }

    public void save(String name, Object val){
        addLog(name, new Log(getLogName(name), LogType.SAVE), val);
    }

    private String getLogName(String name){
        return "Log #"+logNum+": "+ name;
    }

    private Log getLog(String name) { return logs.get(name); }

    private void addLog(String name, Log log, Object o){
        if(!logs.containsKey(name)){
            logs.put(name, log);
            logNum++;
        }
        switch (log.logType){
            case NORMAL:
                getLog(name).addNewOnly(o);
                break;
            case MONITOR:
                getLog(name).add(o);
                break;
            case WATCH:
                telemetry.addData(log.name, String.valueOf(o));
                getLog(name).noTelemetry = true;
                getLog(name).add(o);
                break;
            case SAVE:
                getLog(name).noTelemetry = true;
                getLog(name).addNewOnly(o);
                break;
        }
    }
    public void showTelemetry(){
        for(String key: logs.keySet()){
            if(!getLog(key).noTelemetry) {
                telemetry.addData(getLog(key).name, getLog(key).getCurrentObject());
            }
        }
        telemetry.update();
    }
    public void clearTelemetry(){
        for(String key: logs.keySet()){
            getLog(key).noTelemetry = true;
        }
    }
    public void showLogs(){
        for(String key: logs.keySet()){
            android.util.Log.println(android.util.Log.ASSERT, getLog(key).name, String.valueOf(getLog(key).getValues()));
        }
        android.util.Log.println(android.util.Log.ASSERT, "# of logs", String.valueOf(logs.keySet().size()));
    }
}
