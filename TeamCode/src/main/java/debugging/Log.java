package debugging;

import java.util.ArrayList;

public class Log {
    public LogType logType = LogType.NORMAL;

    public ArrayList<Object> values = new ArrayList<>();

    public Log(){}
    public Log(LogType logType){
        this.logType = logType;
    }

}
