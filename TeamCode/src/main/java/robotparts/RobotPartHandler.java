package robotparts;

import java.util.ArrayList;

public class RobotPartHandler {

    // TODO: Figure out what to do with this class?

    public static ArrayList<RobotPart> allRobotParts = new ArrayList<>();

    public static void init(){
        for (RobotPart part: allRobotParts) {
            part.init();
        }
    }
}
