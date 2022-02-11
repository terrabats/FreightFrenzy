package autoutil.reactors.mecanum;

import autoutil.controllers.PAR;

public class MecanumPARReactor extends MecanumReactor{
    public PAR xPAR = new PAR(0.04,0.4, 0.05);
    public PAR yPAR = new PAR(0.04,0.4, 0.05);
    public PAR hPAR = new PAR(0.04,0.4, 0.05);

    public MecanumPARReactor(){
        addControllers(xPAR,yPAR,hPAR);
    }
}
