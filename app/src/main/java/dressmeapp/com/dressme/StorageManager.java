package dressmeapp.com.dressme;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Param on 11/4/2018.
 */

public class StorageManager {
    private static final String filename = "ClothingItems.data";

    public StorageManager() {

    }

    public static void saveObjectToFile(Context context, Clothing item) {
        File outFile= new File(Environment.getExternalStorageDirectory(), filename);
        ObjectOutputStream outputStream;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(outFile));
            outputStream.writeObject(item);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Clothing> loadClothingItems(Context context) {
        ArrayList<Clothing> itemArray = new ArrayList<>();
        ObjectInput input;
        Clothing item = null;
        try {
            input = new ObjectInputStream(new FileInputStream(filename));
            item = (Clothing) input.readObject();
            input.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(item != null) {
            itemArray.add(item);
        }
        return itemArray;
    }
}
