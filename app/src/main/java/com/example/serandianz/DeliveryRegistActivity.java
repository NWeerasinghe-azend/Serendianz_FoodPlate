package com.example.serandianz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class DeliveryRegistActivity extends AppCompatActivity {
    
    EditText name,nic,phoneno,empid,email,username,password;
    RadioGroup gender;
    CheckBox confirmation;
    private FirebaseAuth auth;
    private View view;
    DatabaseReference dbRef;
    String  emailPattern= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    DeliveryOfficerActivity delivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_regist);

        auth=FirebaseAuth.getInstance();
        name=findViewById(R.id.name);
        nic=findViewById(R.id.nic);
        phoneno=findViewById(R.id.phone_no);
        empid=findViewById(R.id.emp_id);
        gender=(RadioGroup) findViewById(R.id.gender_button);

        email=findViewById(R.id.email);
        username=findViewById(R.id.userName);
        password=findViewById(R.id.password1);
        confirmation=(CheckBox) findViewById(R.id.confirmation);

        delivery=new DeliveryOfficerActivity();

    }

    private Boolean validateEmail()
    {
        String Email=email.getText().toString();
        if(Email.matches(emailPattern)){
            email.setError("Your email is not valid");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }
    }

    private Boolean validateEPhone(){
        String Phoneno = phoneno.getText().toString();
        if(Phoneno.length()!=10){

            phoneno.setError("You have entered a wrong phone number");
            return false;

        }else{
            phoneno.setError(null);
            return true;
        }

    }
    //validate nic
    private Boolean validateNic(){
        String Nic = nic.getText().toString();
        if((nic.length()!=10) || (Nic.length()!=12)) {

            nic.setError("You have entered a wrong number");
            return false;


        }else{
            nic.setError(null);
            return true;
        }

    }

    //validate password
    private Boolean validatePassword(){
        String Password = password.getText().toString();
        String passwordPattern = "^(?=.?[A-Z])(?=.?[a-z])(?=.?[0-9])(?=.?[#?!@$%^&*-]).{6,}$";



        if(!Password.matches(passwordPattern)){

            password.setError("Please provide password with specialcharacers , letters and atleast 6 digits long");
            return false;

        }else{
            password.setError(null);
            return true;
        }

    }


    public void login(View view) {

        startActivity(new Intent(DeliveryRegistActivity.this,DeliveryLoginActivity.class));
    }

    private void clearControls() {
        name.setText("");
        nic.setText("");
        phoneno.setText("");
        empid.setText("");
        email.setText("");
        username.setText("");
        password.setText("");

    }

    public void signup(View view)
    {
        if (!validateEmail()| !validateEPhone() |!validatePassword()){
            return;
        }
        dbRef = FirebaseDatabase.getInstance().getReference().child("DeliveryOfficerActivity");
        String deliverName=name.getText().toString();
        String deliverNic=nic.getText().toString();
        String deliverPno=phoneno.getText().toString();
        String deliverEmpid=empid.getText().toString();
        String deliverEmail=email.getText().toString();
        String deliverUname=username.getText().toString();
        String deliverPassword=password.getText().toString();
        int isSelected=gender.getCheckedRadioButtonId();

        try{
        if(TextUtils.isEmpty(deliverName)){

            Toast.makeText(this,"Enter name",Toast.LENGTH_SHORT).show();
            return;
        }

        else if(TextUtils.isEmpty(deliverNic)){

            Toast.makeText(this,"Enter Nic",Toast.LENGTH_SHORT).show();
            return;
        }

        else if(TextUtils.isEmpty(deliverPno)){

            Toast.makeText(this,"Enter Phone no",Toast.LENGTH_SHORT).show();
            return;
        }

        else if(TextUtils.isEmpty(deliverEmpid)){

            Toast.makeText(this,"Enter Emp Id",Toast.LENGTH_SHORT).show();
            return;
        }

        else if(TextUtils.isEmpty(deliverEmail)){

            Toast.makeText(this,"Enter email",Toast.LENGTH_SHORT).show();
            return;
        }

        else if(TextUtils.isEmpty(deliverUname)){

            Toast.makeText(this,"Enter User Name",Toast.LENGTH_SHORT).show();
            return;
        }

        else if(TextUtils.isEmpty(deliverPassword)){

            Toast.makeText(this,"Enter password",Toast.LENGTH_SHORT).show();
            return;
        }

        else if(isSelected==1){

            Toast.makeText(this,"Enter your gender",Toast.LENGTH_SHORT).show();
            return;
        }

        else if(!confirmation.isChecked()){

            Toast.makeText(this,"Enter your gender",Toast.LENGTH_SHORT).show();
            return;
        }

        else if(deliverPassword.length()<6){

            Toast.makeText(this,"Password is not Strong,enter minimum 6 characters",Toast.LENGTH_SHORT).show();
            return;
        }

        else{

                delivery.setName(name.getText().toString().trim());
                delivery.setNic(nic.getText().toString().trim());
                delivery.setPhoneNo(phoneno.getText().toString().trim());
                delivery.setEmplId(empid.getText().toString().trim());
                delivery.setGender(Boolean.parseBoolean(String.valueOf(gender.getCheckedRadioButtonId())));
                delivery.setEmail(email.getText().toString().trim());
                delivery.setUserName(username.getText().toString().trim());
                delivery.setPassword(password.getText().toString().trim());

                //insert into the database

//                dbRef.push().setValue(custOb);
//                dbRef.child("Customer").setValue(custOb);

        auth.createUserWithEmailAndPassword(deliverEmail,deliverPassword).addOnCompleteListener(DeliveryRegistActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    FirebaseDatabase.getInstance().getReference("DeliveryOfficerActivity")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(delivery).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(DeliveryRegistActivity.this,DeliveryLoginActivity.class));
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


