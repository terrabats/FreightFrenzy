package util.store;

import java.util.Objects;

import static global.General.log;

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

    private final ItemType type;

    /**
     * Constructor, pass in the name and the value
     * @param name
     * @param value
     */
    public Item(String name, T value){
        this.name = name;
        this.value = value;
        this.type = ItemType.STRING;
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

    /**
     * Get the itemtype from the object given
     * @return itemtype
     */
    private ItemType getTypeFromObject(){
        if(value instanceof String){
            return ItemType.STRING;
        }else if(value instanceof Integer){
            return ItemType.INT;
        }else if(value instanceof Float){
            return ItemType.FLOAT;
        }else if(value instanceof Double){
            return ItemType.DOUBLE;
        }else if(value instanceof Boolean){
            return ItemType.BOOLEAN;
        }
        return null;
    }

    /**
     * Get the object from the rawString
     * @param rawString
     * @return
     */
    public static Object getObjectFromType(String rawString){
        String rawType = rawString.split(":")[0];
        String rawValue = rawString.split(":")[1];
        switch (Objects.requireNonNull(ItemType.fromString(rawType))){
            case STRING:
                return rawValue;
            case INT:
                return Integer.valueOf(rawValue);
            case FLOAT:
                return Float.valueOf(rawValue);
            case DOUBLE:
                return Double.valueOf(rawValue);
            case BOOLEAN:
                return Boolean.valueOf(rawValue);
        }
        return null;
    }

    /**
     * String represntation of the object, has both the type and the value
     * @return string of type and value
     */
    @Override
    public String toString(){
        return type.toString() + ":" + value.toString();
    }

    /**
     * The type of item
     */

    public enum ItemType {
        STRING,
        INT,
        FLOAT,
        DOUBLE,
        BOOLEAN;

        public static ItemType fromString(String s){
            for (ItemType t: ItemType.values()){
                if(s.equals(t.toString())){ return t; }
            }
            return null;
        }
    }
}

