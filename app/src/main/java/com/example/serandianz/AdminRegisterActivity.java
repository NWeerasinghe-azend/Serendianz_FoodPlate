package com.example.serandianz;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminRegisterActivity extends AppCompatActivity {

//admin register
    private TextInputEditText userNameEdt1, passwordEdt1, confirmPwdEdt1;
    private TextView loginTV1;
    private Button registerBtn1;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        userNameEdt1 = findViewById(R.id.idEdtUserName1);
        passwordEdt1 = findViewById(R.id.idEdtPassword1);
        loadingPB1 = findViewById(R.id.idPBLoading1);
        confirmPwdEdt1 = findViewById(R.id.idEdtConfirmPassword1);
        loginTV1 = findViewById(R.id.idTVLoginUser1);
        registerBtn1 = findViewById(R.id.idBtnRegister1);
        mAuth = FirebaseAuth.getInstance();

        loginTV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminRegisterActivity.this, AdminLoginActivity.class);
                startActivity(i);

            }
        });


        registerBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB1.setVisibility(View.VISIBLE);
                String userName = userNameEdt1.getText().toString();
                String pwd = passwordEdt1.getText().toString();
                String cnfPwd = confirmPwdEdt1.getText().toString();

                if (!pwd.equals(cnfPwd)) {
                    Toast.makeText(AdminRegisterActivity.this, "Please check both having same password..", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(cnfPwd)) {


                    Toast.makeText(AdminRegisterActivity.this, "Please enter your credentials..", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(userName,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                loadingPB1.setVisibility(View.GONE);
                                Toast.makeText(AdminRegisterActivity.this, "Admin Registered..", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(AdminRegisterActivity.this, AdminLoginActivity.class);
                                startActivity(i);
                                finish();



                            }else{
                                loadingPB1.setVisibility(View.GONE);
                                Toast.makeText(AdminRegisterActivity.this, "Fail to register Admin..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
