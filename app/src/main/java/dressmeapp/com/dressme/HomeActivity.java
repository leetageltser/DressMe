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

    ImageView myClosetImage;
    ImageView partyImage;
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

        myClosetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, OutfitActivity.class);
                i.putExtra("Occasion", "party");
                startActivity(i);
            }
        } );

        partyImage = (ImageView) findViewById(R.id.imageView11);

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
