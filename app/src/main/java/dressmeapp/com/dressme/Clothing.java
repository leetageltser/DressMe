package dressmeapp.com.dressme;

/**
 * Created by Param on 11/3/2018.
 */

public class Clothing {
    private String clothingType;
    private String imageLocation;

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
