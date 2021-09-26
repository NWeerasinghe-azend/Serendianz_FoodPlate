package com.example.serandianz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ProfileUpdatingActivity extends AppCompatActivity {
    FirebaseUser user;
    private FirebaseAuth auth;
    Customer updateCustomer;
    DatabaseReference reference;
    Button MyUpdateBtn;
    String userID;
    private ImageButton logout;
    EditText editTextPersonName,editTextaddress,editDob,editNic,editPhone,editEmail,editUsername;
    TextView userWelcome;
     String Now;
    //ActivityProfileUpdatingBinding binding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // binding= ActivityProfileUpdatingBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_profile_updating);
        //findig edit texts
        editTextPersonName= (EditText)findViewById(R.id.editTextPersonName) ;
        editTextaddress = (EditText)findViewById(R.id.editTextaddress) ;
        editDob = (EditText)findViewById(R.id.editDob) ;
        editNic= (EditText)findViewById(R.id.editNic) ;
        editPhone= (EditText)findViewById(R.id.editPhone) ;
        editEmail= (EditText)findViewById(R.id.editEmail) ;
        editUsername= (EditText)findViewById(R.id.editUsername) ;
        userWelcome =(TextView)findViewById(R.id.userWelcome);
        MyUpdateBtn =(Button) findViewById(R.id.MyUpdateBtn);
        // creating new object
        updateCustomer = new Customer();
          //catching view data
        String fullname=getIntent().getStringExtra("keyname");
        String address=getIntent().getStringExtra("keyaddress");
        String dob=getIntent().getStringExtra("keydob");
        String NIC=getIntent().getStringExtra("keyNic");
        String phone=getIntent().getStringExtra("keyPhone");
        String email=getIntent().getStringExtra("keyEmail");
        String userName=getIntent().getStringExtra("keyuserName");

       //setting data
        editTextPersonName.setText(fullname);
        editTextaddress.setText(address);
        editDob.setText(dob);
        editNic.setText(NIC);
        editPhone.setText(phone);
        editEmail.setText(email);
        editUsername.setText(userName);
        userWelcome.setText(userName);


           //logout function
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID= user.getUid();
        logout =(ImageButton) findViewById(R.id.signOutProfile);



        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileUpdatingActivity.this, WelcomeActivity.class));
            }
        });
          DatabaseReference refNow=FirebaseDatabase.getInstance().getReference("Customer");
        Now= FirebaseAuth.getInstance().getCurrentUser().getUid();

    }
    public void update(View view){
        DatabaseReference upRef= FirebaseDatabase.getInstance().getReference().child("Customer");



         upRef.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                 if(snapshot.hasChild(userID)){
                    try{

                        updateCustomer.setName(editTextPersonName.getText().toString().trim());
                        updateCustomer.setAddress(editTextaddress.getText().toString().trim());
                        updateCustomer.setNIC(editNic.getText().toString().trim());
                        updateCustomer.setDob(editDob.getText().toString().trim());
                        updateCustomer.setPhoneNo(Integer.parseInt(editPhone.getText().toString().trim()));
                        updateCustomer.setUsername(editUsername.getText().toString().trim());
                        updateCustomer.setEmail(editEmail.getText().toString().trim());


                       reference=FirebaseDatabase.getInstance().getReference().child("Customer").child(userID);
                        reference.setValue(updateCustomer);

                        Toast.makeText(getApplicationContext(),"Your profile Updated succesfuly",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProfileUpdatingActivity.this,MainHomeActivity.class));
                    }catch(NumberFormatException e){

                        Toast.makeText(getApplicationContext(),"Invalid Phone Number",Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(getApplicationContext(),"  can not updated data",Toast.LENGTH_SHORT).show();
            }


             @Override
             public void onCancelled(@NonNull @NotNull DatabaseError error) {

             }
         });


    }

}