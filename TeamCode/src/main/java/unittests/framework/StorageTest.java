package unittests.framework;

import elements.FieldSide;
import unittests.UnitTest;

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
        log.show("St", storage.getItem("St"));
        log.show("In", storage.getItem("In"));
        log.show("Fl", storage.getItem("Fl"));
        log.show("Do", storage.getItem("Do"));
        log.show("Bo", storage.getItem("Bo"));
        fieldSide = FieldSide.create((String) storage.getItem("FieldSide"));
        log.show("FieldSide", fieldSide);
        log.show("FieldColor", fieldSide.name());
    }

    @Override
    public void stop() {
        storage.emptyItems();
    }
}
