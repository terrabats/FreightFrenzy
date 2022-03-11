package teleutil;

import teleutil.independent.Independent;

import static global.General.automodules;

public class Independents {
    public Independent MoveForAllianceBackward = new Independent() {
        @Override
        public void define() {
            addWaypoint(-5,-50,5);
            addDecision(automodules.SetUpForAllianceShippingHub);
            addWaypoint(25,-60,0);
            addSetpoint(40, 40, -45);
        }
    };
    public Independent MoveForAllianceForward = new Independent() {
        @Override
        public void define() {

        }
    };
    public Independent MoveForSharedForward = new Independent() {
        @Override
        public void define() {

        }
    };
    public Independent MoveForSharedBackward = new Independent() {
        @Override
        public void define() {

        }
    };
}
