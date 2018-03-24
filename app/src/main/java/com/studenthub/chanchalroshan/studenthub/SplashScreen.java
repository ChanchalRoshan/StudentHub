package com.studenthub.chanchalroshan.studenthub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        startActivity(new Intent(this, Authentication.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}
