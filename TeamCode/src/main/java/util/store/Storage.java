package util.store;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import util.ExceptionCatcher;

import static global.General.*;

public class Storage {

    // TODO TEST
    // Test this class agian using unittester and storage test afte the to do in item is fixed
    /**
     * List of items to store
     */
    private ArrayList<Item<?>> items = new ArrayList<>();

    /**
     * Add and item given a name and a value
     * @param name
     * @param value
     * @param <T>
     */
    public <T> void addItem(String name, T value) { items.add(new Item<>(name, value));
    }

    /**
     * Save the items currently in the arraylist
     */
    public void saveItems(){
        for(Item<?> i: items) {
            saveText("current", i.getName(), i.toString());
        }
    }

    /**
     * Empty all of the items in the list to store
     */
    public void emptyItems(){
        items = new ArrayList<>();
    }

    /**
     * Get the item using the ItemType
     * @param name
     * @return
     */
    public Object getItem(String name){
        return Item.getObjectFromType(readText("current", name));
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
     * @return
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
     * @param dirname
     * @return
     */
    private File makeOutputFolder(String dirname){
        File filepath = Environment.getExternalStorageDirectory();
        File ftcDir = new File(filepath.getAbsolutePath()+"/FTC_Files/");
        log.record("was ftcDir made?", ftcDir.mkdir());
        File outDir = new File(ftcDir.getAbsolutePath()+"/"+dirname+"/");
        log.record("was outDir made?", outDir.mkdir());
        return outDir;
    }
}
