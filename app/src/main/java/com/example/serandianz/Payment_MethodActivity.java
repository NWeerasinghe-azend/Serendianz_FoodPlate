package com.example.serandianz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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