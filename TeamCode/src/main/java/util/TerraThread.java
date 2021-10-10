package util;


import robot.Constants;
import robotparts.Status;

public class TerraThread extends Thread {

    private Status currentStatus = Status.ACTIVE;
    private CodeSeg updateCode;
    private boolean active = true;
    private boolean askedForWait = false;

    public void setCode(CodeSeg cs){updateCode = cs;}

    public synchronized void stopUpdating(){currentStatus = Status.DISABLED;}

    public synchronized boolean isActive(){return active;}

    @Override
    public void run() {
        while (active){
            switch (currentStatus){
                case IDLE:
                    if(!askedForWait){
                        Sleep.trySleep(this::wait);
                        askedForWait = true;
                    }
                    break;
                case ACTIVE:
                    if(askedForWait){
                        Sleep.trySleep(this::notify);
                        askedForWait = false;
                    }
                    updateCode.run();
                    Sleep.trySleep(()-> wait(1000/Constants.THREAD_REFRESH_RATE));
                    break;
                case DISABLED:
                    active = false;
                    break;
            }
        }
    }

    public synchronized void setStatus(Status status){
        currentStatus = status;
    }

    public Status getStatus(){
        return currentStatus;
    }

}
