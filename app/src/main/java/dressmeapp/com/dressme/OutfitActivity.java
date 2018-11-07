package dressmeapp.com.dressme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OutfitActivity extends AppCompatActivity {

    private String occasion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit);
        occasion = getIntent().getExtras().getParcelable("Occasion");

    }
}
