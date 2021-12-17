package util;

public class Access {
    private static final ThreadLocal<Boolean> hasAccess = new ThreadLocal<>();
    public Access(){
        deny();
    }
    public void allow(){
        hasAccess.set(true);
    }
    public void deny(){
        hasAccess.set(false);
    }
    public boolean isAllowed(){
        return hasAccess.get();
    }
    public boolean isDenied(){
        return !hasAccess.get();
    }

}
