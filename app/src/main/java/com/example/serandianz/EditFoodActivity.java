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

import java.util.HashMap;
import java.util.Map;

public class EditFoodActivity extends AppCompatActivity {

    private TextInputEditText foodNameEdt,  foodPriceEdt,  foodImgEdt, foodLinkEdt , foodDescEdt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ProgressBar loadingPB;
    private Button updateFoodBtn, deleteFoodBtn;
    private String foodID;
    private FoodRVModel foodRVModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);

        firebaseDatabase = FirebaseDatabase.getInstance();
        foodNameEdt = findViewById(R.id.idEdtFoodName1);
        foodPriceEdt = findViewById(R.id.idEdtFoodImageLink);
        foodImgEdt = findViewById(R.id.idEdtFoodPrice);
        foodLinkEdt = findViewById(R.id.idEdtFoodLink);
        foodDescEdt = findViewById(R.id.idEdtFoodDescription);
        updateFoodBtn = findViewById(R.id.idBtnUpdateFood);
        deleteFoodBtn = findViewById(R.id.idBtnDeletefood);
        loadingPB = findViewById(R.id.idPBLoading1);

        foodRVModel = getIntent().getParcelableExtra("Foods");
        Button deleteFoodBtn = findViewById(R.id.idBtnDeletefood);
        if (foodRVModel != null) {

            foodNameEdt.setText(foodRVModel.getFoodName());
            foodPriceEdt.setText(foodRVModel.getFoodImg());
            foodImgEdt.setText(foodRVModel.getFoodPrice());
            foodLinkEdt.setText(foodRVModel.getFoodLink());
            foodDescEdt.setText(foodRVModel.getFoodDescription());
            foodID = foodRVModel.getFoodId();


        }

        databaseReference = firebaseDatabase.getReference("Foods").child(foodID);

        updateFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingPB.setVisibility(View.VISIBLE);
                String foodName = foodNameEdt.getText().toString();
                String foodPrice = foodLinkEdt.getText().toString();
                String foodImg = foodLinkEdt.getText().toString();
                String foodLink = foodLinkEdt.getText().toString();
                String foodDesc = foodDescEdt.getText().toString();


                Map<String, Object> map = new HashMap<>();
                map.put("foodName", foodName);
                map.put("foodPrice", foodPrice);
                map.put("foodLink", foodLink);
                map.put("foodImg", foodImg);
                map.put("foodDesc", foodDesc);
                map.put("foodID", foodID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        loadingPB.setVisibility(View.GONE);

                        databaseReference.updateChildren(map);
                        startActivity(new Intent(EditFoodActivity.this, MainActivity2.class));
                        Toast.makeText(EditFoodActivity.this, "Food Updated..", Toast.LENGTH_SHORT).show();
                        // opening a new activity after updating our food.


                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(EditFoodActivity.this, "Fail to update Food..", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        deleteFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFood();

            }
        });
    }
    private void deleteFood(){

        databaseReference.removeValue();
        startActivity(new Intent(EditFoodActivity.this, MainActivity2.class));

        Toast.makeText(this, "food  Deleted..", Toast.LENGTH_SHORT).show();


    }



}
