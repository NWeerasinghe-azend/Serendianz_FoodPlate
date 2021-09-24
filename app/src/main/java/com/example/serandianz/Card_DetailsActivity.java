package com.example.serandianz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Card_DetailsActivity extends AppCompatActivity {

    EditText card_no;
    EditText exp_year;
    EditText exp_month;
    EditText ccv;
    Button save, view;
    CardDetails cardObj;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        card_no = findViewById(R.id.card_num);
        exp_year = findViewById(R.id.exp_year);
        exp_month = findViewById(R.id.exp_month);
        ccv = findViewById(R.id.ccv_no);
        save = findViewById(R.id.save_button);
        view = findViewById(R.id.view_button);

        cardObj = new CardDetails();

    }

        public void CreateData(View view) {

            dbRef = FirebaseDatabase.getInstance().getReference().child("CardDetails");
            try {
                if (TextUtils.isEmpty(card_no.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Card Number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(exp_year.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Card Expiry Year", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(exp_month.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Card Expiry Month", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(ccv.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Card CCV Number", Toast.LENGTH_SHORT).show();
                } else {
                    cardObj.setCardNo(Integer.parseInt(card_no.getText().toString().trim()));
                    cardObj.setExpMonth(Integer.parseInt(exp_year.getText().toString().trim()));
                    cardObj.setExpMonth(Integer.parseInt(exp_month.getText().toString().trim()));
                    cardObj.setCvv(Integer.parseInt(ccv.getText().toString().trim()));

                    dbRef.push().setValue(cardObj);

                    Toast.makeText(getApplicationContext(), "Your Card Details is Being Stored Successfully", Toast.LENGTH_SHORT).show();

                }
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Invalid Number Format", Toast.LENGTH_SHORT).show();

            }

        }
    public void GetCard_DetailsActivity(View view){
        Intent intent=new Intent(this,GetCard_DetailsActivity.class);
        String num1=card_no.getText().toString();
        String num2=exp_year.getText().toString();
        String num3=exp_month.getText().toString();
        String num4=ccv.getText().toString();



        intent.putExtra("n1",num1);
        intent.putExtra("n2",num2);
        intent.putExtra("n3",num3);
        intent.putExtra("n4",num4);

        startActivity(intent);
    }
    }



