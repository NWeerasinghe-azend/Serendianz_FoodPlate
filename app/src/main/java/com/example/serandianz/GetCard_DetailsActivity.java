package com.example.serandianz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GetCard_DetailsActivity extends AppCompatActivity {

    private Button button;
    Dialog dialog;
    CardDetails cardObj;
    DatabaseReference dbRef;
    String USERNAME;

    EditText card_no;
    EditText exp_year;
    EditText exp_month;
    EditText ccv;
    String number1;
    String number2;
    String number3;
    String number4;
    int n1;
    int n2;
    int n3;
    int n4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_card_details);

        dialog=new Dialog(GetCard_DetailsActivity.this);
        dialog.setContentView(R.layout.message);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.ic_launcher_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        Button ok =dialog.findViewById(R.id.buttonOk);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GetCard_DetailsActivity.this,"Thank You",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        button =findViewById(R.id.confirm_d);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


        card_no =findViewById(R.id.card_num);
        exp_year=findViewById(R.id.exp_year);
        exp_month=findViewById(R.id.exp_month);
        ccv=findViewById(R.id.ccv_no);
                                          //Declared variables connected with their xml IDs


        cardObj = new CardDetails();

        Intent intent=getIntent();

        number1 =intent.getStringExtra("n1");
        number2 =intent.getStringExtra("n2");
        number3 =intent.getStringExtra("n3");
        number4 =intent.getStringExtra("n4");

        card_no.setText(number1);
        exp_year.setText(number2);
        exp_month.setText(number3);
        ccv.setText(number4);

        n1=Integer.parseInt(number1);
        n2=Integer.parseInt(number2);
        n3=Integer.parseInt(number3);
        n4=Integer.parseInt(number4);
    }


    public void Card_DetailsActivity(View view){
        Intent intent=new Intent(this,Card_DetailsActivity.class);
        startActivity(intent);
    }

    public void Update(View view){
        DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference().child("CardDetails").child(USERNAME);
        updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(USERNAME)){
                try {
                    cardObj.setCardNo(Integer.parseInt(card_no.getText().toString().trim()));
                    cardObj.setExpMonth(Integer.parseInt(exp_year.getText().toString().trim()));
                    cardObj.setExpMonth(Integer.parseInt(exp_month.getText().toString().trim()));
                    cardObj.setCVC(Integer.parseInt(ccv.getText().toString().trim()));

                    dbRef = FirebaseDatabase.getInstance().getReference().child("CardDetails").child(USERNAME);
                    dbRef.setValue(USERNAME);

                    //Feedback for the user
                    Toast.makeText(getApplicationContext(), "Your Card Details is Being Updated Successfully", Toast.LENGTH_SHORT).show();

                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid data", Toast.LENGTH_SHORT).show();

                }
                }else{
                Toast.makeText(getApplicationContext(), "No Data to Update", Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Delete(View View){          // Deleting saved data
        DatabaseReference deleteRef = FirebaseDatabase.getInstance().getReference().child("CardDetails").child(USERNAME);
        deleteRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(USERNAME)){
                    dbRef=FirebaseDatabase.getInstance().getReference().child("CardDetails").child(USERNAME);
                    dbRef.removeValue();
                    Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "No Data to Delete", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}