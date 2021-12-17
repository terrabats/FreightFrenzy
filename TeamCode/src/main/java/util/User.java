package util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public enum User {
    TELE,
    AUTO,
    ROFU,
    NONE;

    public static User getUserFromTypeOfOpMode(OpMode opMode){
        if(opMode instanceof LinearOpMode){
            return User.AUTO;
        }else{
            return User.TELE;
        }
    }
}
