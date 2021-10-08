package global;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class General {
    public static HardwareMap hardwareMap;
    public static TerraBot bot;

    public static void init(HardwareMap hwMap) {
        bot = new TerraBot();
        hardwareMap = hwMap;
    }
}
