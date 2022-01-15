package util.store;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;

//Used to define data that changes over time
public class Data<X, Y>{

    private final String name;
    private final Item<ArrayList<X>> input;
    private final Item<ArrayList<Y>> output;

    public Data(String name, ArrayList<X> input, ArrayList<Y> output){
        this.name = name;
        this.input = new Item<>(getInputName(name), input);
        this.output = new Item<>(getOutputName(name), output);
    }

    public String getName(){
        return name;
    }
    public Item<ArrayList<X>> getInput(){
        return input;
    }
    public Item<ArrayList<Y>> getOutput(){
        return output;
    }

    public static String getInputName(String name){
        return "Input " + name;
    }

    public static String getOutputName(String name){
        return "Output " + name;
    }

    public static Data<?,?> fromString(String name, String input, String output){
        return new Data<>(name, (ArrayList<?>) Item.fromString(getInputName(name), input).getValue(), (ArrayList<?>) Item.fromString(getOutputName(name), output).getValue());
    }

}