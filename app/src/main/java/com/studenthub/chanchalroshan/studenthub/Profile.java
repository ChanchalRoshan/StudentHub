package com.studenthub.chanchalroshan.studenthub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {

    FirebaseUser currentUser;
    ImageView profile_picture;
    private Button signout_button;
    private GoogleApiClient mGoogleApiClient;
    private EditText profile_name, profile_email, profile_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Referencing the views
        profile_picture = (ImageView) findViewById(R.id.profile_picture);
        signout_button = (Button) findViewById(R.id.signout_button);
        profile_name = (EditText) findViewById(R.id.profile_name);
        profile_email = (EditText) findViewById(R.id.profile_email);
        profile_phone = (EditText) findViewById(R.id.profile_phone);


        //Setting ActionBar back button
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        Picasso.get().load(currentUser.getPhotoUrl()).into(profile_picture);
        profile_name.setText(currentUser.getDisplayName());
        profile_email.setText(currentUser.getEmail());
        profile_phone.setText(currentUser.getPhoneNumber());

        mGoogleApiClient = new GoogleApiClient.Builder(this).
                addApi(Auth.GOOGLE_SIGN_IN_API).build();

        signout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                mGoogleApiClient.clearDefaultAccountAndReconnect();

                Intent logout_intent = new Intent(getApplicationContext(), Authentication.class);
                logout_intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                logout_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                logout_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(logout_intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
}
