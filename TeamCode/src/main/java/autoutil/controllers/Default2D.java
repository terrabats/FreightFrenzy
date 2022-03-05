package autoutil.controllers;

import geometry.position.Vector2;

public class Default2D extends Controller2D{
    public Default2D(Controller1D xController, Controller1D yController) {
        super(xController, yController);
    }

    @Override
    public void update(double heading) {
        xController.update();
        yController.update();
        Vector2 powerVector = new Vector2(xController.getOutput(), yController.getOutput());
        powerVector.rotate(heading);
        setOutputX(powerVector.getX());
        setOutputY(powerVector.getY());
    }

}
