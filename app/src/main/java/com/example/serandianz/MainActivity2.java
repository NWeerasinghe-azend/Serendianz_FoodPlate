package com.example.serandianz;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements  FoodRVAdapter.FoodClickInterface {

    private FloatingActionButton addFoodFAB;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private RecyclerView foodRV;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;
    private RelativeLayout idRLBSheet1;
    private ArrayList<FoodRVModel> foodRVModelArrayList;
    private FoodRVAdapter foodRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodRV = findViewById(R.id.idRVFoods);
        loadingPB = findViewById(R.id.idPBLoading1);
        addFoodFAB = findViewById(R.id.idFABAddFood);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Foods");
        foodRVModelArrayList = new ArrayList<>();
        idRLBSheet1 = findViewById(R.id.idRLBSheet1);
        mAuth = FirebaseAuth.getInstance();


        addFoodFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity for adding a course.
                Intent i = new Intent(MainActivity2.this, AddFoodActivity.class);
                startActivity(i);
            }

        });


        foodRVAdapter = new FoodRVAdapter(foodRVModelArrayList, this, this);
        foodRV.setLayoutManager(new LinearLayoutManager(this));
        foodRV.setAdapter(foodRVAdapter);

        getAllFoods();

    }

    private void getAllFoods() {
        // on below line clearing our list.

        foodRVModelArrayList.clear();

        // on below line we are calling add child event listener method to read the data.

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                loadingPB.setVisibility(View.GONE);
                foodRVModelArrayList.add(snapshot.getValue(FoodRVModel.class));
                foodRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                loadingPB.setVisibility(View.GONE);
                foodRVAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                foodRVAdapter.notifyDataSetChanged();
                loadingPB.setVisibility(View.GONE);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                foodRVAdapter.notifyDataSetChanged();
                loadingPB.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

    }

    @Override
    public void onFoodClick(int position) {

        displayBottomSheet(foodRVModelArrayList.get(position));


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.idLogOut:
                Toast.makeText(this, "user logout ", Toast.LENGTH_SHORT).show();

                mAuth.signOut();
                Intent i = new Intent(MainActivity2.this, AdminLoginActivity.class);
                startActivity(i);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;

    }

    private void displayBottomSheet(FoodRVModel foodRVModel) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog_food, idRLBSheet1);

        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();


        TextView foodNameTV = layout.findViewById(R.id.idTVFoodName);
        TextView priceTV = layout.findViewById(R.id.idTVPrice);
        TextView foodDescTV = layout.findViewById(R.id.idTVFoodDesc);
        ImageView foodIV = layout.findViewById(R.id.idIVFood);

        Button Editbtn = layout.findViewById(R.id.idBtnEditFood);
        Button ViewdetsilsBtn = layout.findViewById(R.id.idBtnVIewDetails);


        foodNameTV.setText(foodRVModel.getFoodName());
        priceTV.setText("Rs." + foodRVModel.getFoodPrice());
        foodDescTV.setText(foodRVModel.getFoodDescription());
        Picasso.get().load(foodRVModel.getFoodImg()).into(foodIV);


        Editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity2.this, EditFoodActivity.class);

                i.putExtra("Foods", foodRVModel);
                startActivity(i);

            }
        });

        Editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity2.this, EditFoodActivity.class);

                i.putExtra("Foods", foodRVModel);
                startActivity(i);

            }
        });

    }
}