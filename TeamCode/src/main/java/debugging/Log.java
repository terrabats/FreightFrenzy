package debugging;

import java.util.ArrayList;

public class Log {
    public String name = "";

    public ArrayList<Double> doubleValues = new ArrayList<>();
    public ArrayList<Boolean> booleanValues = new ArrayList<>();

    public Log(String name){
        this.name = name;
    }
}
