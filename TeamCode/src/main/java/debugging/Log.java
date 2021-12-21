package debugging;

import java.util.ArrayList;

public class Log {
    // TODO
    // Make this have an item inside it
    /**
     * Name of the log
     */
    public String name = "";
    /**
     * Type of log
     */
    public LogType logType = LogType.NORMAL;
    /**
     * If this is true then telemetry will not be outputed
     */
    public boolean noTelemetry = false;

    /**
     * List of values
     */
    private final ArrayList<Object> values = new ArrayList<>();

    /**
     * Constructors for log
     * @param name
     */
    public Log(String name){
        this.name = name;
    }

    public Log(String name, LogType logType){
        this.name = name;
        this.logType = logType;
    }

    /**
     * Add a value to the log
     * @param o
     */
    public void add(Object o){
        values.add(o);
    }

    /**
     * Add a value only if its different then the prevous one
     * @param o
     */
    public void addNewOnly(Object o){
        if(!o.equals(getCurrentObject())){
            values.add(o);
        }
    }

    /**
     * Get the current object, if values has nothing return null
     * @return current object
     */
    public Object getCurrentObject(){
        if(values.size()>0) {
            return values.get(values.size() - 1);
        }else{
            return null;
        }
    }

    /**
     * Get the list of values
     * @return
     */
    public ArrayList<Object> getValues(){
        return values;
    }

}
