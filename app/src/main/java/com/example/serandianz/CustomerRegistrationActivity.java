package com.example.serandianz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
     String  emailPattern= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
             + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

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
    //email validation
    private Boolean validateEmail(){
        String entredEmail = PT_email.getText().toString();
        if(!entredEmail.matches(emailPattern)){

            PT_email.setError("You entered a wrong email");
            return false;

        }else{
            PT_email.setError(null);
            return true;
        }

    }
    //validate phone number
    private Boolean validateEPhone(){
        String entredPhone = PT_phone_no.getText().toString();
        if(entredPhone.length()!=10){

            PT_phone_no.setError("You have entered a wrong phone number");
            return false;

        }else{
            PT_phone_no.setError(null);
            return true;
        }

    }
    //validate nic
    private Boolean validateNic(){
        String entredNic = PT_nic.getText().toString();
        if((entredNic.length()!=10) || (entredNic.length()!=12)) {

            PT_nic.setError("You have entered a wrong number");
            return false;


        }else{
            PT_nic.setError(null);
            return true;
        }

    }

    //validate password
    private Boolean validatePassword(){
        String enteredPasword = Pwd_first.getText().toString();
        String passwordPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$";



        if(!enteredPasword.matches(passwordPattern)){

            Pwd_first.setError("Please provide password with specialcharacers , letters and atleast 6 digits long");
            return false;

        }else{
            Pwd_first.setError(null);
            return true;
        }

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

// validating required inputs
        if (!validateEmail()| !validateEPhone() |!validatePassword()){
            return;
        }

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

            }else if(TextUtils.isEmpty(PT_email.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();


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