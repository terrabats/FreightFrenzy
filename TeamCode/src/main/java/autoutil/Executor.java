package autoutil;

import autoutil.reactors.TankReactor;

public class Executor {

    // Uses the desired motion predictor and reactor to move the robot along the desired path
    // There should be one static executor

    private Generator generator = new Generator();
    private Decision decisionMaker = new Decision();
    private TankReactor reactor = new TankReactor();

}
