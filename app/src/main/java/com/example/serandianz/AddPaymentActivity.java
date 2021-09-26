package com.example.serandianz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPaymentActivity extends AppCompatActivity {


    EditText FoodPrice;
    EditText Quantity;
    EditText Delivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        FoodPrice=findViewById(R.id.FoodPrice);
        Quantity=findViewById(R.id.qty);
        Delivery=findViewById(R.id.delivery_cost);

    }

    public void  PaymentActivity(View view){
        Intent intent=new Intent(this,PaymentActivity.class);

        String num1=FoodPrice.getText().toString();
        String num2=Quantity.getText().toString();
        String num3=Delivery.getText().toString();

        intent.putExtra("n1",num1);
        intent.putExtra("n2",num2);
        intent.putExtra("n2",num3);


        startActivity(intent);
    }


}