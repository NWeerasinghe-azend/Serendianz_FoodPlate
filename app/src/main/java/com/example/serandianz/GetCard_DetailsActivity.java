package com.example.serandianz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GetCard_DetailsActivity extends AppCompatActivity {

    private Button button;
    Dialog dialog;


    EditText card_no;
    EditText exp_year;
    EditText exp_month;
    EditText ccv;
    String number1;
    String number2;
    String number3;
    String number4;
    int n1;
    int n2;
    int n3;
    int n4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_card_details);

        dialog=new Dialog(GetCard_DetailsActivity.this);
        dialog.setContentView(R.layout.message);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.ic_launcher_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        Button ok =dialog.findViewById(R.id.buttonOk);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GetCard_DetailsActivity.this,"Thank You",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        button =findViewById(R.id.confirm_d);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


        card_no =findViewById(R.id.card_num);
        exp_year=findViewById(R.id.exp_year);
        exp_month=findViewById(R.id.exp_month);
        ccv=findViewById(R.id.ccv_no);

        Intent intent=getIntent();

        number1 =intent.getStringExtra("n1");
        number2 =intent.getStringExtra("n2");
        number3 =intent.getStringExtra("n3");
        number4 =intent.getStringExtra("n4");

        card_no.setText(number1);
        exp_year.setText(number2);
        exp_month.setText(number3);
        ccv.setText(number4);

        n1=Integer.parseInt(number1);
        n2=Integer.parseInt(number2);
        n3=Integer.parseInt(number3);
        n4=Integer.parseInt(number4);
    }


    public void Card_DetailsActivity(View view){
        Intent intent=new Intent(this,Card_DetailsActivity.class);
        startActivity(intent);
    }
}