package util;

public class Access {
    /**
     * Used to store the access that something has to something else?
     */

    /**
     * ThreadLocal stores a variable in different thread as seperate objects, For example
     * Main Thread -> hasAccess is false
     * Robot Functions Thread -> hasAccess is true at the same time
     *
     * If isAllowed is called from main thread it will return false
     * If isAllowed is called from the robotfunctions thread it will return true
     */
    private final ThreadLocal<Boolean> hasAccess = new ThreadLocal<>();

    /**
     * Constructor, denys by default
     * NOTE: If you want to give access you must explicit call allow
     */
    public Access(){
        deny();
    }

    /**
     * Allow, sets hasAccess to true in the thread from which it is called
     */
    public void allow(){
        hasAccess.set(true);
    }

    /**
     * Opposite of allow
     * @link allow
     */
    public void deny(){
        hasAccess.set(false);
    }
    /**
     * Is access allowed?
     */
    public boolean isAllowed(){
        return hasAccess.get();
    }
    /**
     * Is access denied?
     */
    public boolean isDenied(){
        return !hasAccess.get();
    }

}
