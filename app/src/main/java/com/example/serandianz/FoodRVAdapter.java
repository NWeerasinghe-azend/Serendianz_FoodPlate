package com.example.serandianz;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class FoodRVAdapter extends RecyclerView.Adapter<FoodRVAdapter.ViewHolder> {

    int lastPos = -1;
    private ArrayList<FoodRVModel> foodRVModelArrayList;
    private Context context;
    private FoodClickInterface foodClickInterface;

    public FoodRVAdapter(ArrayList<FoodRVModel> foodRVModelArrayList, Context context, FoodClickInterface foodClickInterface) {
        this.foodRVModelArrayList = foodRVModelArrayList;
        this.context = context;
        this.foodClickInterface = foodClickInterface;
    }

    @NonNull
    @Override
    public FoodRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.food_rv_item,parent,false);

        return  new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FoodRVModel foodRVModel = foodRVModelArrayList.get(position);
        holder.foodNameTV.setText(foodRVModel.getFoodName());
        holder.foodPriceTV.setText("Rs"+foodRVModel.getFoodPrice());

        Picasso.get().load(foodRVModel.getFoodImg()).into(holder.foodIV);

        setAnimation(holder.itemView,position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                foodClickInterface.onFoodClick(position);
            }
        });



    }



    private void setAnimation(View itemView, int position){

        if(position>lastPos){

            Animation animation = AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
            itemView.setAnimation(animation);

            lastPos = position;


        }
    }
    @Override
    public int getItemCount() {

        return foodRVModelArrayList.size();

    }

    public interface FoodClickInterface{
        void onFoodClick(int position);

    }


    public class ViewHolder  extends RecyclerView.ViewHolder {

        private TextView foodNameTV , foodPriceTV;
        private ImageView foodIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foodNameTV= itemView.findViewById(R.id.idTVFoodName);
            foodPriceTV= itemView.findViewById(R.id.idTVFoodPrice);
            foodIV=itemView.findViewById(R.id.idIVFood);


        }
    }




}
