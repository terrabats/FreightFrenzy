package util.store;

public class Item<T> {
    private final String name;
    private final T value;

    public Item(String name, T value){
        this.name = name;
        this.value = value;
    }
    public String getName(){
        return name;
    }
    public T getValue(){
        return value;
    }
}

