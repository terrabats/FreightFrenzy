package unittests.tele.hardware;

import automodules.StageList;
import teleutil.button.Button;
import teleutil.button.OnPressEventHandler;
import unittests.tele.TeleUnitTest;
import static global.General.*;

public class LiftTest extends TeleUnitTest {
    /**
     * Tests the lift
     */
    public StageList test = new StageList(bot.lift).define(
            bot.lift.liftEncoder(0.5, 30)
    );
    public StageList test2 = new StageList(bot.lift).define(
            bot.lift.liftTime(0.6, 0.5)
    );


    @Override
    protected void start() {
        /**
         * Link the gamepad handlers
         */
        gph1.link(Button.A, OnPressEventHandler.class, () -> bot.addAutoModule(test));
        gph1.link(Button.B, OnPressEventHandler.class, () -> bot.addAutoModule(test2));
    }

    @Override
    public void loop() {
        showConfig(bot.lift);
        /**
         * Lift should move
         */
        log.show("Use right stick y");
        bot.lift.move(-gamepad1.right_stick_y);
        /**
         * Should change when lift moves
         */
        log.show("Lift pos", bot.lift.getLiftPos());
        /**
         * Should not change when lift moves
         */
        log.show("Lift target pos", bot.lift.getTarget());
    }
}
