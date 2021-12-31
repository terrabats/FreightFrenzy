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
        storage.addItem("Do", 2.0d);
        storage.addItem("Bo", true);
        storage.addItem("FieldSide", fieldSide);
        storage.saveItems();
    }

    @Override
    protected void loop() {
        log.showAndRecord("St", storage.getItem("St").getValue());
        log.showAndRecord("In", storage.getItem("In").getValue());
        log.showAndRecord("Fl", storage.getItem("Fl").getValue());
        log.showAndRecord("Do", storage.getItem("Do").getValue());
        log.showAndRecord("Bo", storage.getItem("Bo").getValue());
        fieldSide = FieldSide.create((String) storage.getItem("FieldSide").getValue());
        log.showAndRecord("FieldSide", fieldSide);
        log.showAndRecord("FieldColor", fieldSide.name());
    }

    @Override
    public void stop() {
        storage.emptyItems();
    }
}
