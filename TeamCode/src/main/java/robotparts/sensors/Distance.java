package robotparts.sensors;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import robotparts.RobotPart;

public class Distance extends RobotPart {
    private DistanceSensor dsl,dsr,dsfl,dsfr,dso;

    @Override
    public void init() {
        dsl = createDistanceSensor("dsl");
        dsr = createDistanceSensor("dsr");
        dsfl = createDistanceSensor("dsfl");
        dsfr = createDistanceSensor("dsfr");
        dso = createDistanceSensor("cso");
    }
}
