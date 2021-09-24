package com.example.serandianz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }
    public void Payment_MethodActivity(View view){
        Intent intent=new Intent(this,Payment_MethodActivity.class);
        startActivity(intent);
    }
}