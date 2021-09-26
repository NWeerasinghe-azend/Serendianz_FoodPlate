package com.example.serandianz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class OrderUpdatingActivity extends AppCompatActivity {
    EditText txtFoodid, txtFoodcat, txtFoodName, txtQuantity, txtLocation, helabojun;
    Button update;
    DatabaseReference dbRef;
    FirebaseUser user;
    private FirebaseAuth auth;
    String userID;
    HelaBojun order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_updating);

        txtFoodid = (EditText)findViewById(R.id.txtFoodid);
        txtFoodcat =(EditText) findViewById(R.id.txtFoodcat);
        txtFoodName = (EditText)findViewById(R.id.txtFoodName);
        txtQuantity = (EditText)findViewById(R.id.txtQuantity);
        txtLocation = (EditText)findViewById(R.id.txtLocation);
        helabojun =(EditText) findViewById(R.id.helabojun);
        update= (Button)findViewById(R.id.updaBtn);

        //creating object
        order = new HelaBojun();

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID= user.getUid();

        //catching view data
        String foodId=getIntent().getStringExtra("keyid");
        String foodCat=getIntent().getStringExtra("keyfoodCat");
        String foodname=getIntent().getStringExtra("keyfoodname");
        String quantity=getIntent().getStringExtra("keyquantity");
        String location=getIntent().getStringExtra("keyloaction");
        String time=getIntent().getStringExtra("keytime");


        //setting data
        txtFoodid.setText(foodId);
        txtFoodcat.setText(foodCat);
        txtFoodName.setText(foodname);
        txtQuantity.setText(quantity);
        txtLocation.setText(location);
        helabojun.setText(time);



    }

    public void updateMyOrder(View view){

        DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference().child("HelaBojun");
        updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.hasChild(userID)){
                order.setFoodId(txtFoodid.getText().toString().trim());
                order.setFoodCategory(txtFoodcat.getText().toString().trim());
                order.setFoodName(txtFoodName.getText().toString().trim());
                order.setFoodQuantity(txtQuantity.getText().toString().trim());
                order.setLocation(txtLocation.getText().toString().trim());
                order.setTime(helabojun.getText().toString().trim());
//coonect with relavnt users data in db
                dbRef = FirebaseDatabase.getInstance().getReference().child("HelaBojun").child(userID);
                dbRef.setValue(order);
                //giving a message

                Toast.makeText(getApplicationContext(), "data updated succesfully", Toast.LENGTH_SHORT).show();

            }else
                    Toast.makeText(getApplicationContext(), "no source to update", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }


        });

        }

}