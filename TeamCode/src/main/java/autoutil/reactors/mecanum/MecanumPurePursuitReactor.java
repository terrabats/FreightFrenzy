package autoutil.reactors.mecanum;

import autoutil.controllers.PID;
import autoutil.controllers.PurePursuit;

public class MecanumPurePursuitReactor extends MecanumReactor{
    public PurePursuit xPurePursuit = new PurePursuit();
    public PurePursuit yPurePursuit = new PurePursuit();
    public PID hPID =  new PID(PID.PIDParameterType.STANDARD_FORM_EXTRA, 0.4, 6.0, 0.2,
            0.2 , 0.05, 50.0, Math.toRadians(20), 0.08, 4.0);

    public MecanumPurePursuitReactor(){
        addControllers(xPurePursuit, yPurePursuit, hPID);
    }
}
