package com.example.serandianz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {

    EditText FoodPrice;
    EditText Quantity;
    EditText Delivery;
    TextView SubTotal;
    TextView Total;
    TextView n2;
    TextView n3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        FoodPrice=findViewById(R.id.FoodPrice);
        Quantity=findViewById(R.id.qty);
        Delivery=findViewById(R.id.delivery_cost);
        SubTotal=findViewById(R.id.subTotal);
        Total=findViewById(R.id.total_amount);



    }

    public void Payment_MethodActivity(View view){
        Intent intent=new Intent(this,Payment_MethodActivity.class);
        startActivity(intent);
    }

}