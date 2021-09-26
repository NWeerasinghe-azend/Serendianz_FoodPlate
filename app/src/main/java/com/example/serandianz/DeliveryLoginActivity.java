package com.example.serandianz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class DeliveryLoginActivity extends AppCompatActivity {

    EditText email,password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_login);

        auth=FirebaseAuth.getInstance();
        email=findViewById(R.id.username_input);
        password=findViewById(R.id.password_input);

    }

    public void signin(View view){

        String deliverEmail=email.getText().toString();
        String deliverPassword=password.getText().toString();

        if(TextUtils.isEmpty(deliverEmail)){

            Toast.makeText(this,"Enter User Name",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(deliverPassword)){

            Toast.makeText(this,"Enter password",Toast.LENGTH_SHORT).show();
            return;
        }

        if(deliverPassword.length()<6){

            Toast.makeText(this,"Password is not Strong,enter minimum 6 characters",Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(deliverEmail,deliverPassword).addOnCompleteListener(DeliveryLoginActivity.this,new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(DeliveryLoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DeliveryLoginActivity.this,MainActivity.class));
                }

                else{

                    Toast.makeText(DeliveryLoginActivity.this,"Login Failed"+task.getException(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    
}