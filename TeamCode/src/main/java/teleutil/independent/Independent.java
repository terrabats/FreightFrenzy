package teleutil.independent;

import auton.MecanumAuto;
import util.codeseg.CodeSeg;
import util.codeseg.ParameterCodeSeg;

import static global.General.bot;

public class Independent extends MecanumAuto {
    private final ParameterCodeSeg<Independent> define;
    private final boolean resetOdometry;

    public Independent(ParameterCodeSeg<Independent> define){
        this.define = define;
        this.resetOdometry = false;
    }
    public Independent(boolean resetOdometry, ParameterCodeSeg<Independent> define){
        this.resetOdometry = resetOdometry;
        this.define = define;
    }
    @Override
    public void initAuto() {
        if(resetOdometry){
            bot.odometry.reset();
        }
        makeIndependent();
    }

    @Override
    public void define() {
        define.run(this);
    }
}
