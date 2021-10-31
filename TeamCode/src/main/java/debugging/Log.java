package debugging;

import java.util.ArrayList;

public class Log {
    public String name = "";
    public LogType logType = LogType.NORMAL;

    public ArrayList<Object> values = new ArrayList<>();

    public Log(String name){
        this.name = name;
    }
    public Log(String name, LogType logType){
        this.name = name;
        this.logType = logType;
    }

    public Object getRecentObject(){
        return values.get(values.size()-1);
    }

}
