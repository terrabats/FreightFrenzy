package util;


import global.Constants;
import util.codeseg.CodeSeg;
import util.condition.Expectation;
import util.condition.Magnitude;
import util.condition.Status;
import static global.General.*;

public class TerraThread extends Thread {

    private volatile Status currentStatus = Status.ACTIVE;
    private volatile CodeSeg updateCode = () -> {};

    public synchronized void setCode(CodeSeg cs){
        updateCode = cs;
    }

    public synchronized void stopUpdating(){currentStatus = Status.DISABLED;}

    @Override
    public void run() {
        while (!currentStatus.equals(Status.DISABLED)){
            updateCode.run();
            ExceptionCatcher.catchInterrupted(()-> sleep(1000/Constants.THREAD_REFRESH_RATE));
        }
    }

    public synchronized void setStatus(Status status){
        currentStatus = status;
    }

    public synchronized Status getStatus(){
        return currentStatus;
    }

}
