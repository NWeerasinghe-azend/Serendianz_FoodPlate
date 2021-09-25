package com.example.serandianz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serandianz.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    EditText txtUserEmail,txtUserPassword;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUserEmail= findViewById(R.id.txtUserEmail);
        txtUserPassword= findViewById(R.id.txtUserPassword);
        auth = FirebaseAuth.getInstance();
    }

    public void SignInCustomer(View view){

        String email= txtUserEmail.getText().toString();
        String password = txtUserPassword.getText().toString();


         if(TextUtils.isEmpty(txtUserEmail.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(txtUserPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();}

         auth.signInWithEmailAndPassword(email,password)

                 .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                         if(task.isSuccessful()){


                             Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();

                             startActivity(new Intent(LoginActivity.this,MainHomeActivity.class));
                         }else{
                             Toast.makeText(getApplicationContext(), "Opps!Error" +task.getException(), Toast.LENGTH_SHORT).show();
                         }
                     }
                 });


    }
}