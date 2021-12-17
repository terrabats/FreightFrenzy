package robotparts.electronics;

import util.condition.Status;

public class Electronic {
    protected Status status = Status.ACTIVE;

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public boolean isInUse(){
        return getStatus().equals(Status.ACTIVE);
    }
    public boolean isFreeToUse(){
        return !isInUse();
    }

    public void use(){
        setStatus(Status.ACTIVE);
    }
    public void free(){
        setStatus(Status.INACTIVE);
    }
}
