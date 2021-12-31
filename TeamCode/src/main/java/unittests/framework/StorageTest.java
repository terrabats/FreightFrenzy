package unittests.framework;

import elements.FieldSide;
import unittests.UnitTest;

import static global.General.fieldSide;
import static global.General.log;
import static global.General.storage;

public class StorageTest extends UnitTest {
    /**
     * Tests storage by adding a set of items
     */
    @Override
    protected void start() {
        /**
         * Items to be stored
         */
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
        /**
         * Show the values of the items to be stored
         */
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
        /**
         * Empty the storage list
         */
        storage.emptyItems();
    }
}
