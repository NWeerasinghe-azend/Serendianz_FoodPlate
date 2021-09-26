package com.example.serandianz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingsActivity extends AppCompatActivity {

    TextView rateCountFood,showRate,rateCountTasty,rateCountStaff,rateCountDelivery;
    Ratings newrate;
    Button submit;
    RatingBar FoodRatingBar,staffRatingBar,paymentRatingBar,deliveryRatingBar;
    float ratevaluefood,ratevaluedelivery,ratevaluestaff,ratevaluepayment,total;
     String temp;
    String msg1="bad";
    String msg2;
    String msg3;
    String msg4;
    String msg5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);
        //catching texts
        rateCountFood.findViewById(R.id.rateCountFood);
        rateCountTasty.findViewById(R.id.rateCountTasty);
        rateCountStaff.findViewById(R.id.rateCountStaff);
        rateCountDelivery.findViewById(R.id.rateCountDelivery);
        FoodRatingBar.findViewById(R.id.FoodRatingBar);
        deliveryRatingBar.findViewById(R.id.deliveryRatingBar);
        staffRatingBar.findViewById(R.id.staffRatingBar);
        paymentRatingBar.findViewById(R.id.paymentRatingBar);
        submit.findViewById(R.id.submitRate);
        showRate.findViewById(R.id.showRate);
        newrate= new Ratings();



        //rating bar of food
        FoodRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                ratevaluefood=FoodRatingBar.getRating();

                //setting values according to count of rating stars
                if(ratevaluefood <=1 && ratevaluefood<0)
                    rateCountFood.setText("bad" + ratevaluefood + "/5");
                else if(ratevaluefood <=2 && ratevaluefood < 1)
                    rateCountFood.setText("Ok" + ratevaluefood + "/5");
                if(ratevaluefood <=3 && ratevaluefood < 2)
                    rateCountFood.setText("Good" + ratevaluefood + "/5");
                if(ratevaluefood <=4 && ratevaluefood < 3)
                    rateCountFood.setText("Better" + ratevaluefood + "/5");
                if(ratevaluefood <=5 && ratevaluefood < 4)
                    rateCountFood.setText("Excellent" + ratevaluefood + "/5");
            }
        });
        //rating bar of delivery
        deliveryRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                ratevaluefood=FoodRatingBar.getRating();

                //setting values according to count of rating stars
                if(ratevaluedelivery<=1 && ratevaluedelivery < 0) {
                    rateCountDelivery.setText("Bad" + ratevaluedelivery );
                } else if(ratevaluedelivery <=2 && ratevaluefood < 1)
                    rateCountDelivery.setText("Ok" + ratevaluedelivery + "/5");
                if(ratevaluedelivery <=3 && ratevaluefood < 2)
                    rateCountDelivery.setText("Good" + ratevaluedelivery+ "/5");
                if(ratevaluedelivery <=4 && ratevaluedelivery< 3)
                    rateCountDelivery.setText("Better" + ratevaluedelivery+ "/5");
                if(ratevaluedelivery <=5 && ratevaluedelivery< 4)
                    rateCountDelivery.setText("Excellent" + ratevaluedelivery + "/5");
            }
        });
        //rating bar of paymentRatingBar
        paymentRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                ratevaluefood=FoodRatingBar.getRating();

                //setting values according to count of rating stars
                if(ratevaluepayment <=1 && ratevaluepayment < 0) {
                    rateCountTasty.setText("Excellent" + ratevaluepayment + "/5");
                } else if(ratevaluefood <=2 && ratevaluepayment < 1)
                    rateCountTasty.setText("Ok" + ratevaluepayment + "/5");
                if(ratevaluepayment <=3 && ratevaluepayment < 2)
                    rateCountTasty.setText("Good" + ratevaluepayment+ "/5");
                if(ratevaluepayment <=4 && ratevaluepayment < 3)
                    rateCountTasty.setText("Better" + ratevaluepayment+ "/5");
                if(ratevaluepayment <=5 && ratevaluepayment < 4)
                    rateCountTasty.setText("Excellent" + ratevaluepayment + "/5");
            }
        });
        //rating bar of staff service
        staffRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                ratevaluefood=FoodRatingBar.getRating();

                //setting values according to count of rating stars
                if(ratevaluestaff<=1 && ratevaluestaff <0)
                    rateCountStaff.setText("Bad" + ratevaluestaff + "/5");
                else if(ratevaluestaff<=2 && ratevaluestaff < 1)
                    rateCountStaff.setText("Ok" + ratevaluestaff+ "/5");
                if(ratevaluestaff <=3 && ratevaluestaff < 2)
                    rateCountStaff.setText("Good" + ratevaluestaff + "/5");
                if(ratevaluestaff <=4 && ratevaluestaff < 3)
                    rateCountStaff.setText("Better" + ratevaluefood + "/5");
                if(ratevaluestaff <=5 && ratevaluestaff < 4)
                    rateCountStaff.setText("Excellent" + ratevaluestaff+ "/5");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting average ratings
                float sum=ratevaluefood+ratevaluedelivery+ratevaluepayment+ratevaluestaff;
                total= sum/4;
                temp =rateCountFood.getText().toString();
                showRate.setText("Your total ratings  :" +total);
                FoodRatingBar.setRating(0);
                paymentRatingBar.setRating(0);
                staffRatingBar.setRating(0);
                deliveryRatingBar.setRating(0);
                rateCountFood.setText("");
                rateCountDelivery.setText("");
                rateCountTasty.setText("");
                rateCountStaff.setText("");


            }
        });


    }
}