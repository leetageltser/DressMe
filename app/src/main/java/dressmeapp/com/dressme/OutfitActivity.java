package dressmeapp.com.dressme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class OutfitActivity extends AppCompatActivity {

    private String occasion;
    private TextView occasionTextView;
    private StorageManager storage;
    private ArrayList<Clothing> topItemList;
    private ArrayList<Clothing> bottomItemList;
    private ArrayList<Clothing> accessoryList;
    private static final int IMAGE_HEIGHT = 400;
    private static final int IMAGE_WIDTH = 350;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit);
        occasion = getIntent().getExtras().getString("Occasion");
        occasionTextView = (TextView) findViewById(R.id.occasionTextView);
        occasionTextView.setText(occasion);
        ArrayList<Clothing> allClothingList = StorageManager.loadClothingItems(OutfitActivity.this);
        topItemList = new ArrayList<>();
        bottomItemList = new ArrayList<>();
        accessoryList = new ArrayList<>();

        for(Clothing i : allClothingList) {
            String[] tags = i.getTags();
            if(hasTag(occasion, tags)){
                if(i.isTop()) {
                    topItemList.add(i);
                } else if (i.isBottom()) {
                    bottomItemList.add(i);
                } else if (i.isAccessory()) {
                    accessoryList.add(i);
                }
            }
        }

        LinearLayout layout = (LinearLayout) findViewById(R.id.topLinear);
        for (int i = 0; i < topItemList.size(); i++) {
            String imageLoc = topItemList.get(i).getImageLocation();
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(IMAGE_WIDTH, IMAGE_HEIGHT));
            imageView.setPadding(2, 2, 2, 2);
            Picasso.with(OutfitActivity.this).load(new File(imageLoc)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            layout.addView(imageView);
        }

        LinearLayout bottomLayout = (LinearLayout) findViewById(R.id.bottomLinear);
        for (int i = 0; i < bottomItemList.size(); i++) {
            String imageLoc = bottomItemList.get(i).getImageLocation();
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(IMAGE_WIDTH, IMAGE_HEIGHT));
            imageView.setPadding(2, 2, 2, 2);
            Picasso.with(OutfitActivity.this).load(new File(imageLoc)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            bottomLayout.addView(imageView);
        }

        LinearLayout accessoryLayout = (LinearLayout) findViewById(R.id.accessoryLinear);
        for (int i = 0; i < accessoryList.size(); i++) {
            String imageLoc = accessoryList.get(i).getImageLocation();
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(IMAGE_WIDTH, IMAGE_HEIGHT));
            imageView.setPadding(2, 2, 2, 2);
            Picasso.with(OutfitActivity.this).load(new File(imageLoc)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            accessoryLayout.addView(imageView);
        }
    }

    public static boolean hasTag(String tag, String[] tags) {
        for(String t : tags) {
            if(t.equals(tag)) {
                return true;
            }
        }
        return false;
    }
}
