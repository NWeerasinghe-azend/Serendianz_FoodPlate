package com.example.serandianz;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddFoodActivity extends AppCompatActivity {

    private TextInputEditText foodNameEdt,  foodPriceEdt,  foodImgEdt, foodLinkEdt , foodDescEdt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ProgressBar loadingPB;
    private Button addFoodBtn;
    private String foodID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        foodNameEdt = findViewById(R.id.idEdtFoodName1);
        foodPriceEdt = findViewById(R.id.idEdtFoodPrice);
        foodImgEdt = findViewById(R.id.idEdtFoodImageLink);
        foodLinkEdt = findViewById(R.id.idEdtFoodLink);
        foodDescEdt = findViewById(R.id.idEdtFoodDescription);
        addFoodBtn = findViewById(R.id.idBtnAddFood);
        loadingPB = findViewById(R.id.idPBLoading1);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("Foods");

        addFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingPB.setVisibility(View.VISIBLE);
                String foodName = foodNameEdt.getText().toString();
                String foodPrice = foodPriceEdt.getText().toString();
                String foodImg = foodImgEdt.getText().toString();
                String foodLink = foodLinkEdt.getText().toString();
                String foodDesc = foodDescEdt.getText().toString();

                foodID = foodName;


                FoodRVModel foodRVModel = new FoodRVModel(foodID,foodName,foodPrice,foodImg,foodLink,foodDesc);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        loadingPB.setVisibility(View.GONE);

                        databaseReference.child(foodID).setValue(foodRVModel);
                        // displaying a toast message.

                        Toast.makeText(AddFoodActivity.this, "Food Added..", Toast.LENGTH_SHORT).show();
                        // starting a main activity

                        startActivity(new Intent(AddFoodActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(AddFoodActivity.this, "Fail to add Food ..", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });


    }



}