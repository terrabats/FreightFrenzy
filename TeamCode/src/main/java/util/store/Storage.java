package util.store;

import android.graphics.Bitmap;
import android.os.Environment;

import org.checkerframework.checker.units.qual.A;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import util.ExceptionCatcher;
import util.codeseg.BooleanCodeSeg;

import static global.General.*;

public class Storage {
    private ArrayList<Item> items = new ArrayList<>();

    public void addItem(String name, Object value) {
        items.add(new Item(name, value));
    }
    public void saveItems(){
        for(Item i: items) {
            saveText("current", i.getName(), i.getValue().toString());
        }
    }
    public void emptyItems(){
        items = new ArrayList<>();
    }

    public Object getItem(String name, ItemType type){
        String rawValue = readText("current", name);
        switch (type){
            case STRING:
                return rawValue;
            case INT:
                return Integer.valueOf(rawValue);
            case FLOAT:
                return Float.valueOf(rawValue);
            case DOUBLE:
                return Double.valueOf(rawValue);
            case BOOLEAN:
                return Boolean.getBoolean(rawValue);
        }
        return null;
    }

    private void saveText(String dirname, String filename, String in)  {
        ExceptionCatcher.catchIO(() -> {
            PrintWriter out = new PrintWriter(makeOutputFolder(dirname).getAbsolutePath()+"/" + filename + ".txt");
            out.println(in);
            out.flush();
            out.close();
        });
    }

    private String readText(String dirname, String filename) {
        final String[] out = {""};
        ExceptionCatcher.catchIO(() -> {
            Scanner scan = new Scanner(new BufferedReader(new FileReader(makeOutputFolder(dirname).getAbsolutePath()+"/" + filename + ".txt")));
            out[0] = scan.nextLine();
        });
        return out[0];
    }

    public void saveBitmap(String dirname, Bitmap in, String name){
        File f = new File(makeOutputFolder(dirname), name + ".png");
        ExceptionCatcher.catchIO(f::createNewFile);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        in.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] bitmapdata = bos.toByteArray();
        ExceptionCatcher.catchIO(() -> {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        });
    }

    private File makeOutputFolder(String dirname){
        File filepath = Environment.getExternalStorageDirectory();
        File ftcDir = new File(filepath.getAbsolutePath()+"/FTC_Files/");
        log.save("was ftcDir made?", ftcDir.mkdir());
        File outDir = new File(ftcDir.getAbsolutePath()+"/"+dirname+"/");
        log.save("was outDir made?", outDir.mkdir());
        return outDir;
    }
}
