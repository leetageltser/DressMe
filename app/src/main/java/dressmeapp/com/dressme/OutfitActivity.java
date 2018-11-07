package dressmeapp.com.dressme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OutfitActivity extends AppCompatActivity {

    private String occasion;
    private TextView occasionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit);
        occasion = getIntent().getExtras().getString("Occasion");
        occasionTextView = (TextView) findViewById(R.id.occasionTextView);
        occasionTextView.setText(occasion);
    }
}
