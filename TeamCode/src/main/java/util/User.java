package util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public enum User {
    /**
     * A user enum which represents what is using something, either teleop, auton, robotfunctions, or none
     */
    TELE,
    AUTO,
    ROFU,
    NONE;

    /**
     * Get the user from the type of opmode, if the opmode is a linear opmode then set it to auton
     * @param opMode
     * @return user
     */
    public static User getUserFromTypeOfOpMode(OpMode opMode){
        if(opMode instanceof LinearOpMode){
            return User.AUTO;
        }else{
            return User.TELE;
        }
    }
}
