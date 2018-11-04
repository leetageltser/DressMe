package dressmeapp.com.dressme;

import java.io.Serializable;

/**
 * Created by Param on 11/3/2018.
 */

public class Clothing implements Serializable {
    private String clothingType;
    private String imageLocation;
    private String[] tags;
    private String[] weather;
    private boolean isTop = false;
    private boolean isBottom = false;
    private boolean isAccessory = false;

    public Clothing () {
        clothingType = "";
    }

    public Clothing(String filePath) {
        imageLocation = filePath;
        clothingType = "";
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String[] getTags() {
        return tags;
    }

    public void setWeather(String[] weather) {
        this.weather = weather;
    }

    public String[] getWeather() {
        return weather;
    }

    public void setToTop() {
        isTop = true;
        isBottom = false;
        isAccessory = false;
    }

    public void setToBottom() {
        isTop = false;
        isBottom = true;
        isAccessory = false;
    }
    public void setToAccessory() {
        isTop = false;
        isBottom = false;
        isAccessory = true;
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
