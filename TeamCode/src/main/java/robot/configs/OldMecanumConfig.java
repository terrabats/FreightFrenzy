package robot.configs;

import robotparts.unused.MecanumDrive;

public class OldMecanumConfig extends RobotConfig{

    @Override
    public void create() {
        mecanumDrive = new MecanumDrive();
    }
}
