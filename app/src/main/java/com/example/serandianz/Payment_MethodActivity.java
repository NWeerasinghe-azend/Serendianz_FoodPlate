package com.example.serandianz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Payment_MethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
    }
    public void Card_DetailsActivity(View view){
        Intent intent=new Intent(this,Card_DetailsActivity.class);
        startActivity(intent);
    }
}