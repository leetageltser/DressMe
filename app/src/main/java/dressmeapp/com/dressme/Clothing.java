package dressmeapp.com.dressme;

import java.io.Serializable;

/**
 * Created by Param on 11/3/2018.
 */

public class Clothing implements Serializable {
    private String clothingType;
    private String imageLocation;
    private String[] tags;

    public Clothing () {
        clothingType = "";
    }

    public Clothing(String filePath) {
        imageLocation = filePath;
        clothingType = "";
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setClothingType(String type) {
        clothingType = type;
    }

    public String getClothingType() {
        return clothingType;
    }

}
