package debugging;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

import teleutil.Selector;
import util.store.Item;

import static global.General.*;

public class Logger {

    // TODO TEST
    // Is logger being bad? (randomly not showing telementry (maybe updating telemetry multiple times)
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
     * Shows the output on telemetry
     * @param val
     */
    public void show(Object val){
        telemetry.addData("Show", val);
    }
    public void show(String caption, Object val){
        telemetry.addData(caption, val);
    }

    /**
     * Records the value and doesn't display telemetry
     * @param val
     */
    public void record(String name, Object val){
        if(!logs.containsKey(name)){
            logs.put(name, new Log("Log #"+logNum+": "+name));
            logNum++;
        }
        Objects.requireNonNull(logs.get(name)).addNewOnly(val);
    }

    public void showAndRecord(String caption, Object val){
        show(caption, val);
        record(caption, val);
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
     * Updates the telemetry and shows the logs that noTelemetry is false
     */
    public void showTelemetry(){
        telemetry.update();
    }

    /**
     * Sets noTelemetry to true for all the logs (so they effectively arent displayed)
     */
    public void clearTelemetry(){
        telemetry.clear();
    }

    /**
     * Shows the logs at the end of the code
     * To see the logs go to logcat and then the assert tab
     */
    public void showLogs(){
        for(String key: logs.keySet()){
            android.util.Log.println(android.util.Log.ASSERT, Objects.requireNonNull(logs.get(key)).getName(), String.valueOf(Objects.requireNonNull(logs.get(key)).getValues()));
        }
        android.util.Log.println(android.util.Log.ASSERT, "Number of logs", Integer.toString(logNum));
    }
}
