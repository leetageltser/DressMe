package dressmeapp.com.dressme;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Param on 11/4/2018.
 */

public class StorageManager {
    private static final String foldername = "ClothingItems";

    public StorageManager() {

    }

    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 100;

    public static void saveObjectToFile(Context context, Activity thisActivity, Clothing item) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "ITEM_" + timeStamp + ".data";
        File outFile= new File(Environment.getExternalStorageDirectory().toString() +
                "/" + foldername + "/" + imageFileName);
        ObjectOutputStream outputStream;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(thisActivity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {
            try {
                outputStream = new ObjectOutputStream(new FileOutputStream(outFile));
                outputStream.writeObject(item);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Clothing> loadClothingItems(Context context) {
        File folder = new File(Environment.getExternalStorageDirectory().toString()  +
                "/" + foldername + "/");
        folder.mkdirs();
        File[] allFiles = folder.listFiles();
        File inFile= new File(Environment.getExternalStorageDirectory(), foldername);
        ArrayList<Clothing> itemArray = new ArrayList<>();
        ObjectInput input;
        Clothing item = null;
        if(allFiles == null) {
            return itemArray;
        }
        for(File file : allFiles) {
            try {
                input = new ObjectInputStream(new FileInputStream(file));
                item = (Clothing) input.readObject();
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (item != null) {
                itemArray.add(item);
            }
        }
        return itemArray;
    }
}
