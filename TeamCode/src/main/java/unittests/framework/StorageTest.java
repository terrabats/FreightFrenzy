package unittests.framework;

import util.store.ItemType;

import static global.General.log;
import static global.General.storage;

public class StorageTest extends FrameworkTest{
    @Override
    protected void run() {
        storage.addItem("St", "Test");
        storage.addItem("In", 1);
        storage.addItem("Fl", 1.0f);
        storage.addItem("Do", 1.0d);
        storage.addItem("Bo", true);
        storage.saveItems();
    }

    @Override
    protected void loop() {
        log.watch("St", storage.getItem("St", ItemType.STRING));
        log.watch("In", storage.getItem("In", ItemType.STRING));
        log.watch("Fl", storage.getItem("Fl", ItemType.STRING));
        log.watch("Do", storage.getItem("Do", ItemType.STRING));
        log.watch("Bo", storage.getItem("Bo", ItemType.STRING));
    }

    @Override
    public void stop() {
        storage.emptyItems();
    }
}
