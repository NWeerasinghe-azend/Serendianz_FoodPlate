package com.example.serandianz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button  regBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);





        setContentView(R.layout.activity_welcome);
        regBt  =findViewById(R.id.regBt);


        regBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this,AdminRegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void register(View view) {

        startActivity(new Intent(WelcomeActivity.this,RegistrationActivity.class));
    }

    public void loginMain(View view) {

        startActivity(new Intent(WelcomeActivity.this,MainLoginActivity.class));
    }
}