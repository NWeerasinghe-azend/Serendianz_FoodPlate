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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class AddToCartTActivity extends AppCompatActivity {

    EditText txtFoodid, txtFoodcat, txtFoodName, txtQuantity, txtLocation, helabojun;
    Button placeOrder, ViewMkOrder,updateOrderButton;
    DatabaseReference dbRef;
    FirebaseUser user;
    private FirebaseAuth auth;
    String userID;
    HelaBojun order;
    String orderKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart_tactivity);

        txtFoodid = findViewById(R.id.txtFoodid);
        txtFoodcat = findViewById(R.id.txtFoodcat);
        txtFoodName = findViewById(R.id.txtFoodName);
        txtQuantity = findViewById(R.id.txtQuantity);
        txtLocation = findViewById(R.id.txtLocation);
        helabojun = findViewById(R.id.helabojun);
        placeOrder= findViewById(R.id.placeOrder);
        ViewMkOrder =findViewById(R.id.placeOrder);
        updateOrderButton= findViewById(R.id.ViewMyhOrder);

        order = new HelaBojun();


        user = FirebaseAuth.getInstance().getCurrentUser();
        userID= user.getUid();
    }
    private void clearControls() {
        txtFoodid.setText("");
        txtFoodcat.setText("");
        txtFoodName.setText("");
        txtQuantity.setText("");
        txtLocation.setText("");
        helabojun.setText("");

    }

    public void saveOrder(View view) {

        dbRef = FirebaseDatabase.getInstance().getReference().child("HelaBojun");
//validating data
        if (TextUtils.isEmpty(txtFoodid.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter food ID", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(txtFoodcat.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter food category", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(txtFoodName.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter food quantity", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(txtQuantity.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter food quantity", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(txtLocation.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter location", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(helabojun.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter date and time", Toast.LENGTH_SHORT).show();
        } else {

//setting values
            order.setFoodId(txtFoodid.getText().toString().trim());
            order.setFoodCategory(txtFoodcat.getText().toString().trim());
            order.setFoodName(txtFoodName.getText().toString().trim());
            order.setFoodQuantity(txtQuantity.getText().toString().trim());
            order.setLocation(txtLocation.getText().toString().trim());
            order.setTime(helabojun.getText().toString().trim());
            order.setCustomerId(userID);
//pass to db
            dbRef.child(userID).setValue(order);

            Toast.makeText(getApplicationContext(), "Your order placed successfuly", Toast.LENGTH_SHORT).show();

            clearControls();






        }


    }
    public void viewOrder(View view) {
        startActivity(new Intent(AddToCartTActivity.this,ViewOrder1Activity.class));
    }



    //}

}