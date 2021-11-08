package util;


import global.Constants;
import util.codeseg.CodeSeg;
import util.condition.Status;

public class TerraThread extends Thread {

    private volatile Status currentStatus = Status.ACTIVE;
    private volatile CodeSeg updateCode = () -> {};

    public synchronized void setCode(CodeSeg cs){updateCode = cs;}

    public synchronized void stopUpdating(){currentStatus = Status.DISABLED;}

    @Override
    public void run() {
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
