package autoutil.reactors.mecanum;

import autoutil.controllers.PAR;

public class MecanumPARReactor extends MecanumReactor{
    public PAR xPAR = new PAR(0.0,0.0, 0.0);
    public PAR yPAR = new PAR(0.001,0.2, 0.05);
    public PAR hPAR = new PAR(0.0,0.0, 0.0);

    public MecanumPARReactor(){
        xPAR.setAccuracy(1);
        yPAR.setAccuracy(1);
        hPAR.setAccuracy(2);
        addControllers(xPAR,yPAR,hPAR);
    }
}
