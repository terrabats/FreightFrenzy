package teleop.independent;

import auton.MecanumAuto;

import static global.General.bot;

public abstract class Independent extends MecanumAuto {
    @Override
    public void initAuto() {}

    public void returnToMain(){
        bot.independentRunner.returnToMain();
    }
}
