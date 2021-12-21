package debugging;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import teleutil.Selector;
import util.store.Item;

import static global.General.*;

public class Logger {
    // TODO FIX
    // Logger needs a lot of fixing and testing

    /**
     * Hashmap of logs
     * NOTE: Hashmaps preserve order unlike treemaps
     */
    private final LinkedHashMap<String, Log> logs = new LinkedHashMap<>();
    /**
     * The log number since the start
     */
    private int logNum = 0;

    /**
     * Displays the output to the phone and stores it as a log
     * @param val
     */
    public void display(Object val){
        addLog(String.valueOf(val), new Log(getLogName("Display")), String.valueOf(val));
    }
    public void display(String caption, Object val){
        addLog(caption, new Log(getLogName(caption)), String.valueOf(val));
    }

    /**
     * Moniters every state of the value given
     * @param name
     * @param val
     */
    public void monitor(String name, Object val){
        addLog(name, new Log(getLogName(name), LogType.MONITOR), val);
    }

    /**
     * Watches the value and only displays telemetry
     * @param val
     */
    public void watch(Object val){
        addLog(String.valueOf(val), new Log(getLogName("Watch"), LogType.WATCH), String.valueOf(val));
    }
    public void watch(String caption, Object val){
        addLog(caption, new Log(getLogName(caption), LogType.WATCH), String.valueOf(val));
    }

    /**
     * Saves the value and doesn't display telemetry
     * @param name
     * @param val
     */
    public void save(String name, Object val){
        addLog(name, new Log(getLogName(name), LogType.SAVE), val);
    }

    /**
     * Create a list and show what is the current value being selected
     * @param values
     * @param currentIndex
     */
    public void list(ArrayList<String> values, int currentIndex){
        for(int i = 0; i < values.size(); i++){
            String num = Integer.toString(i);
            /**
             * Lol formatting issues be like
             */
            if(i < 10){
                num = "0" + num;
            }
            if(i != currentIndex) {
                telemetry.addData("Item" + num, "    " + values.get(i));
            }else{
                telemetry.addData("Item" + num, "--> " + values.get(i));
            }
        }
    }

    /**
     * Get the name of the log, with the lognumber and the name
     * @param name
     * @return logname
     */
    private String getLogName(String name){
        return "Log #"+logNum+": "+ name;
    }

    /**
     * Get the log with the given name
     * @param name
     * @return name
     */
    private Log getLog(String name) { return logs.get(name); }

    /**
     * Add a log
     * @param name
     * @param log
     * @param o
     */
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

    /**
     * Updates the telemetry and shows the logs that noTelemetry is false
     */
    public void showTelemetry(){
        for(String key: logs.keySet()){
            if(!getLog(key).noTelemetry) {
                telemetry.addData(getLog(key).name, getLog(key).getCurrentObject());
            }
        }
        telemetry.update();
    }

    /**
     * Sets noTelemetry to true for all the logs (so they effectively arent displayed)
     */
    public void clearTelemetry(){
        for(String key: logs.keySet()){
            getLog(key).noTelemetry = true;
        }
    }

    /**
     * Shows the logs at the end of the code
     * To see the logs go to logcat and then the assert tab
     */
    public void showLogs(){
        for(String key: logs.keySet()){
            android.util.Log.println(android.util.Log.ASSERT, getLog(key).name, String.valueOf(getLog(key).getValues()));
        }
        android.util.Log.println(android.util.Log.ASSERT, "# of logs", String.valueOf(logs.keySet().size()));
    }
}
