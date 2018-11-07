package dressmeapp.com.dressme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private ImageView myClosetImage;
    private ImageView partyImage;
    private ImageView businessImage;
    private ImageView casualImage;
    private ImageView beachImage;
    private ImageView schoolImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myClosetImage = (ImageView) findViewById(R.id.imageView10);
        myClosetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ClosetActivity.class);
                startActivity(i);
            }
        } );

        partyImage = (ImageView) findViewById(R.id.imageView11);
        partyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, OutfitActivity.class);
                i.putExtra("Occasion", "party");
                startActivity(i);
            }
        } );

        businessImage = (ImageView) findViewById(R.id.imageView12);
        businessImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, OutfitActivity.class);
                i.putExtra("Occasion", "business");
                startActivity(i);
            }
        } );

        casualImage = (ImageView) findViewById(R.id.imageView13);
        casualImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, OutfitActivity.class);
                i.putExtra("Occasion", "casual");
                startActivity(i);
            }
        } );

        beachImage = (ImageView) findViewById(R.id.imageView15);
        beachImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, OutfitActivity.class);
                i.putExtra("Occasion", "beach");
                startActivity(i);
            }
        } );

        schoolImage = (ImageView) findViewById(R.id.imageView14);
        schoolImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, OutfitActivity.class);
                i.putExtra("Occasion", "school");
                startActivity(i);
            }
        } );

        Toolbar toolbar =  (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_closet,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings_about:
                Intent about = new Intent(this, AboutActivity.class);
                startActivity(about);
                break;
            case R.id.settings_name:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;
            case R.id.settings_update:
                Toast.makeText(getApplicationContext(),"Latest version installed!",Toast.LENGTH_SHORT).show();
                break;
            default:
                //unknown error
        }
        return super.onOptionsItemSelected(item);

    }
}
