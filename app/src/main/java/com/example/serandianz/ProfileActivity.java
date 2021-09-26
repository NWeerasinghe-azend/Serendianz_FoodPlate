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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;


public class ProfileActivity extends AppCompatActivity {

     FirebaseUser user;
    private FirebaseAuth auth;
    DatabaseReference reference;
    Button ViewButton;
    Button deleteButton;
    Button updateButton;
    EditText txt_name,txt_address,txt_dob,txt_nic,txt_phone,txt_email,txt_user_name;
    TextView welcomeProfile;
    String userID;
     Customer customerProfile;
     Customer customer;
    private ImageButton logout;
   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
         txt_name =(EditText) findViewById(R.id.name);
        txt_address =(EditText) findViewById(R.id.address);
      txt_dob =(EditText) findViewById(R.id.dob);
        txt_nic =(EditText) findViewById(R.id.nic);
        txt_phone =(EditText) findViewById(R.id.phoneNo);
        txt_email =(EditText) findViewById(R.id.email);
        txt_user_name =(EditText) findViewById(R.id.userName);
        deleteButton = (Button)findViewById(R.id.deleteButton);
        ViewButton=(Button) findViewById(R.id.ViewButton);
        updateButton=(Button) findViewById(R.id.updateButton);

    welcomeProfile =(TextView) findViewById(R.id.userWelcome);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID= user.getUid();
        logout =(ImageButton) findViewById(R.id.signOutProfile);

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfileActivity.this, WelcomeActivity.class));
            }
        });






    }
        public void myProfile(View view){


            reference= FirebaseDatabase.getInstance().getReference().child("Customer").child(userID);

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {


                    if(snapshot.hasChildren()){

                        welcomeProfile.setText(snapshot.child("username").getValue().toString());
                    txt_name.setText(snapshot.child("name").getValue().toString());
                    txt_address.setText(snapshot.child("address").getValue().toString());
                    txt_dob.setText((snapshot.child("dob").getValue().toString()));
                    txt_nic.setText(snapshot.child("nic").getValue().toString());
                    txt_phone.setText(snapshot.child("phoneNo").getValue().toString());
                    txt_email.setText(snapshot.child("email").getValue().toString());
                    txt_user_name.setText(snapshot.child("username").getValue().toString());

                    }
                    else
                        Toast.makeText(getApplicationContext(),"There no such a customer",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }

            });

    }

//

    public void update(View view){

        String fullname =txt_name.getText().toString();
        String  address=txt_address.getText().toString();
        String dob =txt_dob.getText().toString();
        String NIC =txt_nic.getText().toString();
        String phone=txt_phone.getText().toString();
        String email = txt_email.getText().toString();
        String userName= txt_user_name.getText().toString();

        Intent intent =new Intent(ProfileActivity.this,ProfileUpdatingActivity.class);

        intent.putExtra("keyname",fullname);
        intent.putExtra("keyaddress",address);
         intent.putExtra("keydob",dob);
        intent.putExtra("keyNic",NIC);
        intent.putExtra("keyPhone",phone);
        intent.putExtra("keyEmail",email);
        intent.putExtra("keyuserName",userName);

        startActivity(intent);




    }
    public  void deleteAccount(View view){


        DatabaseReference deleteRef= FirebaseDatabase.getInstance().getReference().child("Customer");
        deleteRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.hasChild(userID)){

                    reference= FirebaseDatabase.getInstance().getReference().child("Customer").child(userID);
                    reference.removeValue();
                    Toast.makeText(getApplicationContext(),"Oops!Your account deleted succesfuly",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ProfileActivity.this,WelcomeActivity.class));

                }else
                    Toast.makeText(getApplicationContext(),"Sorry!Your account  can not deleted ",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}


