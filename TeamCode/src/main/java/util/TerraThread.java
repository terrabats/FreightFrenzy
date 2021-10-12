package util;


import robot.Constants;
import robotparts.Status;

public class TerraThread extends Thread {

    private Status currentStatus = Status.ACTIVE;
    private CodeSeg updateCode = () -> {};

    public void setCode(CodeSeg cs){updateCode = cs;}

    public synchronized void stopUpdating(){currentStatus = Status.DISABLED;}

    @Override
    public void run() {
//        while (!currentStatus.equals(Status.DISABLED)){
//            if(currentStatus.equals(Status.IDLE)){
//                synchronized (this) {
//                    Sleep.trySleep(this::wait);
//                }
//            }else if(currentStatus.equals(Status.ACTIVE)){
//                updateCode.run();
//                Sleep.trySleep(()-> sleep(1000/Constants.THREAD_REFRESH_RATE));
//            }
//        }
    }

    public synchronized void setStatus(Status status){
        if(currentStatus.equals(Status.IDLE) && status.equals(Status.ACTIVE)){
            Sleep.trySleep(this::notify);
        }
        currentStatus = status;
    }

    public synchronized Status getStatus(){
        return currentStatus;
    }

}
