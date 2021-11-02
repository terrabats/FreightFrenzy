package util.codeseg;

//Used to define and pass code
public interface CodeSeg {
    default void run(){}
    default void run(double... args){}
    default double runDouble(){return 0;}
    default double runDouble(double... args){return 0;}
    default boolean runBoolean(){return true;}
    default boolean runBoolean(double... args){return true;}
}