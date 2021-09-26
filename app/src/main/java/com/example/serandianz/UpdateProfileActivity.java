package com.example.serandianz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UpdateProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
    }
    public void helaBojunOrder(View view) {

        startActivity(new Intent(UpdateProfileActivity.this,AddToCartTActivity.class));
    }
    public void ratings(View view) {

        startActivity(new Intent(UpdateProfileActivity.this,RatingsActivity.class));
    }
}