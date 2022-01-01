package util.store;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import util.ExceptionCatcher;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

public class Storage {
    /**
     * List of items to store
     */
    private ArrayList<Item<?>> items = new ArrayList<>();

    /**
     * Add an item given a name and a value
     * @param name
     * @param value
     */
    public <T> void addItem(String name, T value) { items.add(new Item<>(name, value));
    }

    /**
     * Save the items currently in the arraylist of items
     * NOTE: This generates a bunch of text files in the current directory with the name of the item
     * as the name of the text file and the value in the file
     */
    public void saveItems(){
        log.showAndRecord("Num", items.size());
        log.showAndRecord("Num2", items.get(0) == null);
        fault.warn("No items to save", Expectation.SURPRISING, Magnitude.MINOR, items.isEmpty(), false);
        for(Item<?> i: items) {
            saveText("current", i.getName(), i.toString());
        }
    }

    /**
     * Removes all of the items in the arraylist of items
     */
    public void emptyItems(){
        items = new ArrayList<>();
    }

    /**
     * Get the item using the name
     * NOTE: This gets it from the storage not the arraylist so the arraylist could be empty when this is called
     * @param name
     * @return itemValue
     */
    public Item<?> getItem(String name){
        return Item.fromString(name, readText("current", name));
    }

    /**
     * Save the text using the directory name, the filename, and the string to save
     * @param dirname
     * @param filename
     * @param in
     */
    private void saveText(String dirname, String filename, String in)  {
        ExceptionCatcher.catchIO(() -> {
            PrintWriter out = new PrintWriter(makeOutputFolder(dirname).getAbsolutePath()+"/" + filename + ".txt");
            out.println(in);
            out.flush();
            out.close();
        });
    }

    /**
     * Read the text from the directory name, and the filename
     * @param dirname
     * @param filename
     * @return text
     */
    private String readText(String dirname, String filename) {
        final String[] out = {""};
        ExceptionCatcher.catchIO(() -> {
            Scanner scan = new Scanner(new BufferedReader(new FileReader(makeOutputFolder(dirname).getAbsolutePath()+"/" + filename + ".txt")));
            out[0] = scan.nextLine();
        });
        return out[0];
    }

    /**
     * Make the output folder from the specified directory name
     * NOTE: All files and folders will be under the FTC_Files folder
     * @param dirname
     * @return output directory
     */
    private File makeOutputFolder(String dirname){
        File filepath = Environment.getExternalStorageDirectory();
        File ftcDir = new File(filepath.getAbsolutePath()+"/FTC_Files/");
        //log.record("was ftcDir made?", ftcDir.mkdir());
        File outDir = new File(ftcDir.getAbsolutePath()+"/"+dirname+"/");
        //log.record("was outDir made?", outDir.mkdir());
        return outDir;
    }
}
