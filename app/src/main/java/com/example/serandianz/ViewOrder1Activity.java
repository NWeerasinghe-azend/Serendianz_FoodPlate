package com.example.serandianz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ViewOrder1Activity extends AppCompatActivity {
    EditText txtFoodid, txtFoodcat, txtFoodName, txtQuantity, txtLocation, helabojun;
    Button placeOrder, updateOrder,deleteOrder;
    DatabaseReference dbRef;
    FirebaseUser user;
    private FirebaseAuth auth;
    HelaBojun order;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order1);


        txtFoodid = (EditText)findViewById(R.id.txtFoodid);
        txtFoodcat =(EditText) findViewById(R.id.txtFoodcat);
        txtFoodName = (EditText)findViewById(R.id.txtFoodName);
        txtQuantity = (EditText)findViewById(R.id.txtQuantity);
        txtLocation = (EditText)findViewById(R.id.txtLocation);
        helabojun =(EditText) findViewById(R.id.helabojun);
        placeOrder= (Button)findViewById(R.id.placeOrder);
        updateOrder= (Button)findViewById(R.id.placeOrder);
        deleteOrder= (Button)findViewById(R.id.deleteOrder);


        user = FirebaseAuth.getInstance().getCurrentUser();
        userID= user.getUid();

    }

    //view the order function
    public void viewOrder(View view){


//connect withdb
        DatabaseReference  viewRef= FirebaseDatabase.getInstance().getReference().child("HelaBojun").child(userID);
        viewRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
//getting values
                    txtFoodid.setText(snapshot.child("foodId").getValue().toString());
                    txtFoodcat.setText(snapshot.child("foodCategory").getValue().toString());
                    txtFoodName.setText(snapshot.child("foodName").getValue().toString());

                    txtQuantity.setText(snapshot.child("foodQuantity").getValue().toString());
                    txtLocation.setText(snapshot.child("location").getValue().toString());
                    helabojun.setText(snapshot.child("time").getValue().toString());



                }else{
                    Toast.makeText(getApplicationContext(), "Opps!cannot retriew order", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public void updateOrder(View view){

//        DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference().child("HelaBojun");
//        updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                if(snapshot.hasChild(userID)){

//                    order.setFoodId(txtFoodid.getText().toString().trim());
//                    order.setFoodCategory(txtFoodcat.getText().toString().trim());
//                    order.setFoodName(txtFoodName.getText().toString().trim());
//                    order.setFoodQuantity(txtQuantity.getText().toString().trim());
//                    order.setLocation(txtLocation.getText().toString().trim());
//                    order.setTime(helabojun.getText().toString().trim());
////
//                    dbRef = FirebaseDatabase.getInstance().getReference().child("HelaBojun").child(userID);
//                    dbRef.setValue(order);
//                     //giving a message
//                    Toast.makeText(getApplicationContext(), "data updated succesfully", Toast.LENGTH_SHORT).show();




//                }
//                else
//                    Toast.makeText(getApplicationContext(), "no source to update", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });
        String foodId =txtFoodid.getText().toString();
        String  foodCat=txtFoodcat.getText().toString();
        String foodname =txtFoodName.getText().toString();
        String quantity =txtQuantity.getText().toString();
        String loaction=txtLocation.getText().toString();
        String time = helabojun.getText().toString();


        Intent intent =new Intent(ViewOrder1Activity.this,OrderUpdatingActivity.class);

        intent.putExtra("keyid",foodId);
        intent.putExtra("keyfoodCat",foodCat);
        intent.putExtra("keyfoodname",foodname);
        intent.putExtra("keyquantity",quantity);
        intent.putExtra("keyloaction",loaction);
        intent.putExtra("keytime",time);


        startActivity(intent);


    }


       public  void deleteOrder(View view){
           DatabaseReference deleteRef= FirebaseDatabase.getInstance().getReference().child("HelaBojun");
           deleteRef.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                   //checking relavant order
                   if(snapshot.hasChild(userID)){

                       dbRef= FirebaseDatabase.getInstance().getReference().child("HelaBojun").child(userID);
                       dbRef.removeValue();
                       Toast.makeText(getApplicationContext(),"Oops!Your order canceled deleted succesfully",Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(ViewOrder1Activity.this,MainHomeActivity.class));

                   }else
                       //giving msg
                       Toast.makeText(getApplicationContext(),"Sorry!Your order  can not deleted ",Toast.LENGTH_SHORT).show();

               }

               @Override
               public void onCancelled(@NonNull @NotNull DatabaseError error) {

               }
           });

       }
    }