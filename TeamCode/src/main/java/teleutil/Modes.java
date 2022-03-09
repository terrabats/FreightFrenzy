package teleutil;

import util.condition.DecisionList.Decision;

public class Modes {
    public enum OuttakeMode implements Decision {
        SHARED,
        ALLIANCE
    }

    public enum DriveMode implements Decision {
        FAST,
        MEDIUM,
        SLOW
    }
}
