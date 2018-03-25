package com.studenthub.chanchalroshan.studenthub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_search){

            return true;
        }
        else if(id == R.id.action_user){

            startActivity(new Intent(getApplicationContext(), Profile.class));
            return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        this.finish();
    }
}
