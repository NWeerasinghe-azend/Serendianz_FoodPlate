package com.example.serandianz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class CustomerRegistrationActivity extends AppCompatActivity {


    EditText PT_full_name, PT_address, PT_dob, PT_nic, PT_phone_no, PT_email, PT_uname, Pwd_first,pwd_conf;
    Button btn_join;
    DatabaseReference dbRef;
    Customer custOb;
    private FirebaseAuth auth;
//    String  emailPattern= "/[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        PT_full_name = findViewById(R.id.PT_full_name);
        PT_address = findViewById(R.id.PT_address);
        PT_dob = findViewById(R.id.PT_dob);
        PT_nic = findViewById(R.id.PT_nic);
        PT_phone_no = findViewById(R.id.PT_phone_no);
        PT_email = findViewById(R.id.PT_email);
        PT_uname = findViewById(R.id.PT_uname);
        Pwd_first = findViewById(R.id.Pwd_first);
        pwd_conf= findViewById(R.id.pwd_conf);
        btn_join = findViewById(R.id.btn_join);
        auth = FirebaseAuth.getInstance();

        custOb = new Customer();
    }

    private void clearControls() {
        PT_full_name.setText("");
        PT_address.setText("");
        PT_dob.setText("");
        PT_nic.setText("");
        PT_phone_no.setText("");
        PT_email.setText("");
        PT_uname.setText("");
        Pwd_first.setText("");
        pwd_conf.setText("");

    }



    public void createStudent(View view) {

        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");

        String email= PT_email.getText().toString();
        String password = Pwd_first.getText().toString();
        try{


            if(TextUtils.isEmpty(PT_full_name.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();

            }else if (TextUtils.isEmpty(PT_address.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter nic no", Toast.LENGTH_SHORT).show();

            }else if(TextUtils.isEmpty(PT_dob.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();

            }else if(TextUtils.isEmpty(PT_nic.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            }else if((PT_nic.length() < 10) ||(PT_nic.length() <12) ) {
                Toast.makeText(getApplicationContext(), "Please enter a nic with length 10 or 12", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(PT_email.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(PT_uname.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(Pwd_first.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            }else{
                //Take user input assigning them to custOb object

                custOb.setName(PT_full_name.getText().toString().trim());
                custOb.setAddress(PT_address.getText().toString().trim());
                custOb.setDob(PT_dob.getText().toString().trim());
                custOb.setNIC(PT_nic.getText().toString().trim());
                custOb.setPhoneNo(Integer.parseInt(PT_phone_no.getText().toString().trim()));
                custOb.setEmail(PT_email.getText().toString().trim());
                custOb.setUsername(PT_uname.getText().toString().trim());
                custOb.setPassword(Pwd_first.getText().toString().trim());

                //insert into the database

//                dbRef.push().setValue(custOb);
//                dbRef.child("Customer").setValue(custOb);

               auth.createUserWithEmailAndPassword(email,password)
                       .addOnCompleteListener(CustomerRegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                               if(task.isSuccessful()){

                                   FirebaseDatabase.getInstance().getReference("Customer")
                                           .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                           .setValue(custOb).addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull @NotNull Task<Void> task) {

                                           if(task.isSuccessful()){
                                               Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_SHORT).show();
                                               startActivity(new Intent(CustomerRegistrationActivity.this,LoginActivity.class));
                                           }else{
                                               Toast.makeText(getApplicationContext(),"Opps! can not register" +task.getException(),Toast.LENGTH_SHORT).show();

                                           }

                                       }
                                   });





                               }else{
                                   Toast.makeText(getApplicationContext(),"Opps! can not register" +task.getException(),Toast.LENGTH_SHORT).show();

                               }

                           }
                       });



                clearControls();

            }






            }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Invalid contact number",Toast.LENGTH_SHORT).show();


        }

    }


}