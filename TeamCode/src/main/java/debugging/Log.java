package debugging;

import java.util.ArrayList;

public class Log {
    public String name = "";
    public LogType logType = LogType.NORMAL;
    public boolean noTelemetry = false;

    private final ArrayList<Object> values = new ArrayList<>();

    public Log(String name){
        this.name = name;
    }
    public Log(String name, LogType logType){
        this.name = name;
        this.logType = logType;
    }

    public void add(Object o){
        values.add(o);
    }
    public void addNewOnly(Object o){
        if(!o.equals(getCurrentObject())){
            values.add(o);
        }
    }
    public Object getCurrentObject(){
        if(values.size()>0) {
            return values.get(values.size() - 1);
        }else{
            return null;
        }
    }
    public ArrayList<Object> getValues(){
        return values;
    }

}
