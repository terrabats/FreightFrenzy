package util.store;

public class Item<T> {
    /**
     * Item represents a item, should be created as follows
     * For example if creating an item with a int value then
     * Item<Integer> item = new Item<>("yes", 1)
     */
    /**
     * Name of the item
     */
    private final String name;
    /**
     * Value of the item as a type T
     */
    private final T value;

    /**
     * Constructor, pass in the name and the value
     * @param name
     * @param value
     */
    public Item(String name, T value){
        this.name = name;
        this.value = value;
    }

    /**
     * Get the name
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Get the value
     * @return value
     */
    public T getValue(){
        return value;
    }
}

