package com.example.serandianz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

public class Card_DetailsActivity extends AppCompatActivity {
    EditText card_no;                       // Declare
    EditText exp_year;
    EditText exp_month;
    EditText ccv;
    EditText cardname;
    Button save, view;
    CardDetails cardObj;
    DatabaseReference dbRef;
    String USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        cardname=findViewById(R.id.Username);                    //Declared variables connected with their xml IDs
        card_no = findViewById(R.id.card_num);
        exp_year = findViewById(R.id.exp_year);
        exp_month = findViewById(R.id.exp_month);
        ccv = findViewById(R.id.ccv_no);
        save = findViewById(R.id.save_button);
        view = findViewById(R.id.view_button);
        cardObj = new CardDetails();
    }
    public void clearControls(){          //When data saved,clear all entered data
        card_no.setText(" ");
        exp_year.setText(" ");
        exp_month.setText(" ");
        ccv.setText(" ");
        cardname.setText(" ");
    }

    public void CreateData(View view) {                    //onclick method


        String CardNumber=card_no.getText().toString();
        String ExpiryYear=exp_year.getText().toString();
        String ExpiryMonth=exp_month.getText().toString();
        String CardCCV=ccv.getText().toString();
        USERNAME=cardname.getText().toString();

        //Test Cases
        if(TextUtils.isEmpty(CardNumber)){
            Toast.makeText(this,"Enter Your Card Number",Toast.LENGTH_SHORT).show();
            return;
        }

        if(CardNumber.length()>16){
            Toast.makeText(this,"You have entered more than 16 digits, please check your card number",Toast.LENGTH_SHORT).show();
            return;
        }
        if(ExpiryYear.length()>2){
            Toast.makeText(this,"You have entered more than 16 digits, please check your card number",Toast.LENGTH_SHORT).show();
            return;
        }
        if(ExpiryMonth.length()>2){
            Toast.makeText(this,"You have entered more than 16 digits, please check your card number",Toast.LENGTH_SHORT).show();
            return;
        }
        if(CardCCV.length()>3){
            Toast.makeText(this,"You have entered more than 16 digits, please check your card number",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(ExpiryYear)){
            Toast.makeText(this,"Enter Your Card Expiry Year",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(ExpiryMonth)){
            Toast.makeText(this,"Enter Your Card Expiry Month",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(CardCCV)){
            Toast.makeText(this,"Enter Your Card CCV Number",Toast.LENGTH_SHORT).show();
            return;
        }

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
            }else if (TextUtils.isEmpty(cardname.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please Enter Your Card Username", Toast.LENGTH_SHORT).show();
            } else {
                cardObj.setCardNo(Integer.parseInt(card_no.getText().toString().trim()));
                cardObj.setExpMonth(Integer.parseInt(exp_year.getText().toString().trim()));
                cardObj.setExpMonth(Integer.parseInt(exp_month.getText().toString().trim()));
                cardObj.setCVC(Integer.parseInt(ccv.getText().toString().trim()));
                cardObj.setCardName(cardname.getText().toString().trim());

                dbRef.child(USERNAME).setValue(cardObj);

                Toast.makeText(getApplicationContext(), "Your Card Details is Being Stored Successfully", Toast.LENGTH_SHORT).show();
                clearControls();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Invalid Number Format", Toast.LENGTH_SHORT).show();

        }

    }
    public void Show(View view) {              //Retrieve data from data


   DatabaseReference  readRef = FirebaseDatabase.getInstance().getReference().child("CardDetails").child(USERNAME);
   readRef.addListenerForSingleValueEvent(new ValueEventListener() {
       @Override
       public void onDataChange(@NonNull DataSnapshot snapshot) {
           if(snapshot.hasChildren()){
               card_no.setText(snapshot.child("cardNo").getValue().toString());
               exp_year.setText(snapshot.child("expYear").getValue().toString());
               exp_month.setText(snapshot.child("expMonth").getValue().toString());
               ccv.setText(snapshot.child("CVC").getValue().toString());
               cardname.setText(snapshot.child("cardName").getValue().toString());
           }else {
               Toast.makeText(getApplicationContext(), "No Data To Display", Toast.LENGTH_SHORT).show();
           }
           }


       @Override
       public void onCancelled(@NonNull DatabaseError error) {

       }
    });


}


}

