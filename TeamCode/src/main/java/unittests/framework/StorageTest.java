package unittests.framework;

import elements.FieldSide;
import unittests.UnitTest;
import util.store.Item;

import static global.General.fieldSide;
import static global.General.log;
import static global.General.storage;

public class StorageTest extends UnitTest {
    @Override
    protected void start() {
        fieldSide = FieldSide.RED;
        storage.addItem("St", "Test");
        storage.addItem("In", 1);
        storage.addItem("Fl", 1.0f);
        storage.addItem("Do", 1.0d);
        storage.addItem("Bo", true);
        storage.addItem("FieldSide", fieldSide);
        storage.saveItems();
    }

    @Override
    protected void loop() {
        log.watch("St", storage.getItem("St"));
        log.watch("In", storage.getItem("In"));
        log.watch("Fl", storage.getItem("Fl"));
        log.watch("Do", storage.getItem("Do"));
        log.watch("Bo", storage.getItem("Bo"));
        fieldSide = FieldSide.create((String) storage.getItem("FieldSide"));
        log.watch("FieldSide", fieldSide);
        log.watch("FieldColor", fieldSide.name());
    }

    @Override
    public void stop() {
        storage.emptyItems();
    }
}
