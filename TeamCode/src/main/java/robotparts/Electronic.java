package robotparts;

import org.checkerframework.checker.units.qual.A;

import util.Access;
import util.condition.Status;

public class Electronic {
    protected volatile Status status = Status.IDLE;
    protected Access access = new Access();

    public synchronized Status getStatus(){
        return status;
    }

    public synchronized void setStatus(Status status){
        this.status = status;
    }

}
