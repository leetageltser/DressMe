package dressmeapp.com.dressme;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener{

    protected Context context;
    private Button addPhotoButton, addItemButton;
    private String mCurrentPhotoPath;
    private Uri photoURI;
    private static final int CAMERA_REQUEST_CODE = 1;
    private ImageView uploadImageView;
    private String downloadUrlString;
    private ImageView newItemImage;
    private EditText descriptionEditText;
    private String descriptionString;
    private CheckBox partyCheckBox, businessCheckBox, casualCheckBox, beachCheckBox, schoolCheckBox;
    private Switch rainSwitch, tempSwitch;
    private RadioButton topRad, bottomRad, accessoryRad;
    private String imageLocation;
    private String rainOrShine;
    private String warmOrCold;
    View.OnClickListener top_button_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            bottomRad.setChecked(false);
            accessoryRad.setChecked(false);
            topRad.setChecked(true);
        }
    };
    View.OnClickListener bottom_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            bottomRad.setChecked(true);
            accessoryRad.setChecked(false);
            topRad.setChecked(false);
        }
    };
    View.OnClickListener accessory_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            bottomRad.setChecked(false);
            accessoryRad.setChecked(true);
            topRad.setChecked(false);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        newItemImage = (ImageView) findViewById(R.id.clothingImageView);
        addPhotoButton = (Button) findViewById(R.id.addPhotoButton);
        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        descriptionEditText = (EditText) findViewById(R.id.descriptionEdit);

        partyCheckBox = (CheckBox) findViewById(R.id.partyCheckBox);
        businessCheckBox = (CheckBox) findViewById(R.id.businessCheckBox);
        casualCheckBox = (CheckBox) findViewById(R.id.casualCheckBox);
        beachCheckBox = (CheckBox) findViewById(R.id.beachCheckBox);
        schoolCheckBox = (CheckBox) findViewById(R.id.schoolCheckBox);

        rainSwitch = (Switch) findViewById(R.id.rainSwitch);
        tempSwitch = (Switch) findViewById(R.id.tempSwitch);

        topRad = (RadioButton) findViewById(R.id.topButton);
        topRad.setOnClickListener(top_button_listener);
        bottomRad = (RadioButton) findViewById(R.id.bottomButton);
        bottomRad.setOnClickListener(bottom_listener);
        accessoryRad = (RadioButton) findViewById(R.id.accessoryButton);
        accessoryRad.setOnClickListener(accessory_listener);

        addItemButton = (Button) findViewById(R.id.addToClosetButton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageLocation == null) {
                    Toast.makeText(AddItemActivity.this, "No Image Found, Add a new image",
                            Toast.LENGTH_LONG).show();
                } else {
                    String[] tags = getTags();
                    String[] weather = getWeather();
                    Clothing newItem = new Clothing(imageLocation);
                    descriptionString = descriptionEditText.getText().toString();
                    newItem.setTags(tags);
                    newItem.setWeather(weather);
                    newItem.setClothingType(descriptionString);
                    if(topRad.isChecked()) {
                        newItem.setToTop();
                    } else if(bottomRad.isChecked()) {
                        newItem.setToBottom();
                    } else if(accessoryRad.isChecked()) {
                        newItem.setToAccessory();
                    }
                    StorageManager storage = new StorageManager();
                    storage.saveObjectToFile(AddItemActivity.this, AddItemActivity.this, newItem);
                    Toast.makeText(AddItemActivity.this, "Added Item", Toast.LENGTH_LONG).show();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
    }

    public String[] getTags() {
        ArrayList<String> tagArray = new ArrayList<>();
        if(partyCheckBox.isChecked()) {
            tagArray.add("party");
        }
        if(businessCheckBox.isChecked()) {
            tagArray.add("business");
        }
        if(casualCheckBox.isChecked()) {
            tagArray.add("casual");
        }
        if(beachCheckBox.isChecked()) {
            tagArray.add("beach");
        }
        if(schoolCheckBox.isChecked()) {
            tagArray.add("school");
        }
        String[] stringArray = new String[tagArray.size()];
        for(int i = 0; i<tagArray.size(); i++) {
            stringArray[i] = tagArray.get(i);
        }
        return stringArray;
    }

    public String[] getWeather() {
        String[] weatherArray = new String[2];
        if(rainSwitch.isChecked()) {
            weatherArray[0] = "rainy";
        } else {
            weatherArray[0] = "clear";
        }
        if(tempSwitch.isChecked()) {
            weatherArray[1] = "warm";
        } else {
            weatherArray[1] = "cool";
        }
        return weatherArray;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE &&
                resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                newItemImage.setImageBitmap(imageBitmap);
                String saveFile = saveToInternalStorage(imageBitmap);
                imageLocation = saveFile;
                Toast.makeText(AddItemActivity.this, saveFile, Toast.LENGTH_LONG).show();

            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "BMP_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".bmp",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;

    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void dispatchTakePictureIntent() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE
        );
        if(pictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(pictureIntent,
                    CAMERA_REQUEST_CODE);
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "BMP_" + timeStamp + ".bmp";
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory, imageFileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath() + "/" + imageFileName;
    }

}
