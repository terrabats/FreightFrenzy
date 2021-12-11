package elements;

public enum FieldSide {
    BLUE("Left"), RED("Right");

    private final String side;
    FieldSide(String s){
        this.side = s;
    }
    public String getSide(){
        return side;
    }
    public static FieldSide create(String side){
        if(side.equals("Left")){
            return BLUE;
        }else if(side.equals("Right")){
            return RED;
        }else{
            return null;
        }
    }

    @Override
    public String toString() {
        return getSide();
    }
}