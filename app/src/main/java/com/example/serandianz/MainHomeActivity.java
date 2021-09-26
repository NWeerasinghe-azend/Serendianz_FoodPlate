package com.example.serandianz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class MainHomeActivity extends AppCompatActivity {
    private ImageButton logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);


        logout =(ImageButton) findViewById(R.id.signOutProfile);

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainHomeActivity.this, WelcomeActivity.class));
            }
        });



    }
    public void starting(View view) {

        startActivity(new Intent(MainHomeActivity.this,UpdateProfileActivity.class));
    }
    public void viewProfile(View view){

        startActivity(new Intent(MainHomeActivity.this,ProfileActivity.class));
    }

}