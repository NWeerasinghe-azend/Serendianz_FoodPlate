package com.example.serandianz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DeliveryOfficerViewActivity extends AppCompatActivity {

    ListView li;
    ArrayAdapter<String> adapter;
    String[] default_items=new String[]{"name","nic","phoneno","empid","email","gender"};
    EditText name,nic,phoneno,empid,email;
    RadioGroup gender;
    DatabaseReference dbRef;
   FirebaseUser user;
    UserInfo info;

    List<String> itemlist;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_officer_view);

        user=FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        itemlist=new ArrayList<>();

       dbRef=FirebaseDatabase.getInstance().getReference();
       dbRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
               itemlist.clear();
               String name=dataSnapshot.child(uid).child("name").getValue(String.class);
               String nic=dataSnapshot.child(uid).child("nic").getValue(String.class);
               String phoneno=dataSnapshot.child(uid).child("phoneno").getValue(String.class);
               String empid=dataSnapshot.child(uid).child("empid").getValue(String.class);
               String email=dataSnapshot.child(uid).child("email").getValue(String.class);
               String gender=dataSnapshot.child(uid).child("gender").getValue(String.class);

               itemlist.add(name);
               itemlist.add(nic);
               itemlist.add(phoneno);
               itemlist.add(empid);
               itemlist.add(email);
               itemlist.add(gender);

               adapter=new ArrayAdapter<>(DeliveryOfficerViewActivity.this,android.R.layout.simple_list_item_1,itemlist);
               li.setAdapter(adapter);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

               Toast.makeText(getApplicationContext(),"Network error.Check your connection",Toast.LENGTH_SHORT).show();
           }
       });


    }


    }

