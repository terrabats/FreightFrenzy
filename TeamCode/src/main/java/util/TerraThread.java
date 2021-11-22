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
    private volatile boolean setUpdateCode = false;

    public synchronized void setCode(CodeSeg cs){
        updateCode = cs;
        setUpdateCode = true;
    }

    public synchronized void stopUpdating(){currentStatus = Status.DISABLED;}

    @Override
    public void run() {
        fault.warn("Starting thread with empty CodeSeg", Expectation.EXPECTED, Magnitude.MINOR, setUpdateCode);
        while (!currentStatus.equals(Status.DISABLED)){
            if(currentStatus.equals(Status.IDLE)){
                synchronized (this) {
                    ExceptionCatcher.catchInterrupted(this::wait);
                }
            }else if(currentStatus.equals(Status.ACTIVE)){
                updateCode.run();
                ExceptionCatcher.catchInterrupted(()-> sleep(1000/Constants.THREAD_REFRESH_RATE));
            }
        }
    }

    public synchronized void setStatus(Status status){
        if(currentStatus.equals(Status.IDLE) && status.equals(Status.ACTIVE)){
            ExceptionCatcher.catchInterrupted(this::notify);
        }
        currentStatus = status;
    }

    public synchronized Status getStatus(){
        return currentStatus;
    }

}
