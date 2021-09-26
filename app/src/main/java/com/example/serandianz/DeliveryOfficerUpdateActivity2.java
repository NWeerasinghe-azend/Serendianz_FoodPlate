package com.example.serandianz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

public class DeliveryOfficerUpdateActivity2 extends AppCompatActivity {

    FirebaseUser user;
    private FirebaseAuth auth;
    DeliveryOfficerActivity updatedeliveryofficer;
    DatabaseReference reference;
    Button Update;
    String userID;
    EditText name,nic,phoneno,empid,email,username,password;
    RadioGroup gender;
    TextView userWelcome;
    String Now;
    //ActivityProfileUpdatingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // binding= ActivityProfileUpdatingBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_delivery_officer_update2);

        //findig edit texts
        name=findViewById(R.id.name);
        nic=findViewById(R.id.nic);
        phoneno=findViewById(R.id.phone_no);
        empid=findViewById(R.id.emp_id);
        gender=(RadioGroup) findViewById(R.id.gender_button);

        email=findViewById(R.id.email);
        username=findViewById(R.id.userName);
        password=findViewById(R.id.password1);

        // creating new object
        updatedeliveryofficer=new DeliveryOfficerActivity();

        //catching view data
        String fullname=getIntent().getStringExtra("keyname");
        String Nic=getIntent().getStringExtra("keyNic");
        String PhoneNo=getIntent().getStringExtra("keyPhoneNo");
        String Email=getIntent().getStringExtra("keyEmail");
        String UserName=getIntent().getStringExtra("keyUserName");

        //setting data
        name.setText(fullname);
        nic.setText(Nic);
        phoneno.setText(PhoneNo);
        email.setText(Email);
        username.setText(UserName);
        userWelcome.setText(UserName);


        //logout function
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID= user.getUid();

    }
    public void update(View view){
        DatabaseReference upRef= FirebaseDatabase.getInstance().getReference().child("Customer");


        upRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.hasChild(userID)){
                    try{

                        updatedeliveryofficer.setName(name.getText().toString().trim());
                        updatedeliveryofficer.setNic(nic.getText().toString().trim());
                        updatedeliveryofficer.setPhoneNo(phoneno.getText().toString().trim());
                        updatedeliveryofficer.setEmail(email.getText().toString().trim());
                        updatedeliveryofficer.setUserName(username.getText().toString().trim());
                        updatedeliveryofficer.setUserName(userWelcome.getText().toString().trim());


                        reference=FirebaseDatabase.getInstance().getReference().child("Customer").child(userID);
                        reference.setValue(updatedeliveryofficer);

                        Toast.makeText(getApplicationContext(),"Your profile Updated succesfuly",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DeliveryOfficerUpdateActivity2.this,DeliveryOfficerViewActivity.class));
                    }catch(NumberFormatException e){

                        Toast.makeText(getApplicationContext(),"Invalid Phone Number",Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(getApplicationContext(),"  can not updated data",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });


    }

}