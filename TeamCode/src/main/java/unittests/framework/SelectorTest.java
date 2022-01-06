package unittests.framework;

import teleutil.Selector;
import unittests.UnitTest;

import static global.General.log;

public class SelectorTest extends UnitTest {

    Selector<Integer> selector;

    @Override
    public void init() {
        selector = new Selector<>(false);
        selector.init(10);
    }

    @Override
    protected void loop() {
        // This should be true in the start
        log.show("Selector is on first", selector.isOnFirst());
    }
}
