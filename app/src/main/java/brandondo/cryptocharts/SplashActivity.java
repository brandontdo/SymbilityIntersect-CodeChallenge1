package brandondo.cryptocharts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start main activity
        startActivity(new Intent(SplashActivity.this, CryptoActivity.class));
        // close splash activity
        finish();
    }
}