package com.example.wia2007_zerohunger.Part1.soil_analysis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wia2007_zerohunger.R;

import java.util.ArrayList;
import java.util.List;

public class CropResultAdapter extends RecyclerView.Adapter<CropResultAdapter.ViewHolder> {

    private ArrayList<CropResult> cropResultList;

    private Context context;

    public CropResultAdapter(ArrayList<CropResult> cropResultList, Context context) {
        this.cropResultList = cropResultList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crop_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CropResult currentCropResult = cropResultList.get(position);
        holder.cropItemTextViewP1.setText(currentCropResult.getCropName());
        holder.plantingTimeTextViewP1.setText(currentCropResult.getCropPlantingTime());
        holder.manageTipsTextViewP1.setText(currentCropResult.getCropDescription());

        String cropItem = currentCropResult.getCropName().toLowerCase();

        //maybe is the break that causes your recycler view not working
        if (cropItem.equals("rice")) {
            holder.cropImageViewP1.setImageResource(R.drawable.rice);

        } else if (cropItem.equals("maize")) {
            holder.cropImageViewP1.setImageResource(R.drawable.maize);

        } else if (cropItem.equals("chickpea")) {
            holder.cropImageViewP1.setImageResource(R.drawable.chickpea);

        } else if (cropItem.equals("pigeonpeas")) {
            holder.cropImageViewP1.setImageResource(R.drawable.pigeonpeas);

        } else if (cropItem.equals("mothbeans")) {
            holder.cropImageViewP1.setImageResource(R.drawable.mothbeans);

        } else if (cropItem.equals("mungbean")) {
            holder.cropImageViewP1.setImageResource(R.drawable.mungbean);

        } else if (cropItem.equals("blackgram")) {
            holder.cropImageViewP1.setImageResource(R.drawable.blackgram);

        } else if (cropItem.equals("lentil")) {
            holder.cropImageViewP1.setImageResource(R.drawable.lentil);

        } else if (cropItem.equals("pomegranate")) {
            holder.cropImageViewP1.setImageResource(R.drawable.pomegranate);

        } else if (cropItem.equals("banana")) {
            holder.cropImageViewP1.setImageResource(R.drawable.banana);

        } else if (cropItem.equals("mango")) {
            holder.cropImageViewP1.setImageResource(R.drawable.mango);

        } else if (cropItem.equals("grapes")) {
            holder.cropImageViewP1.setImageResource(R.drawable.grapes);

        } else if (cropItem.equals("watermelon")) {
            holder.cropImageViewP1.setImageResource(R.drawable.watermelon);

        } else if (cropItem.equals("muskmelon")) {
            holder.cropImageViewP1.setImageResource(R.drawable.muskmelon);

        } else if (cropItem.equals("apple")) {
            holder.cropImageViewP1.setImageResource(R.drawable.apple);

        } else if (cropItem.equals("orange")) {
            holder.cropImageViewP1.setImageResource(R.drawable.orange);

        } else if (cropItem.equals("papaya")) {
            holder.cropImageViewP1.setImageResource(R.drawable.papaya);

        } else if (cropItem.equals("coconut")) {
            holder.cropImageViewP1.setImageResource(R.drawable.coconut);

        } else if (cropItem.equals("cotton")) {
            holder.cropImageViewP1.setImageResource(R.drawable.cotton);

        } else if (cropItem.equals("jute")) {
            holder.cropImageViewP1.setImageResource(R.drawable.jute);

        } else if (cropItem.equals("coffee")) {
            holder.cropImageViewP1.setImageResource(R.drawable.coffee);
        }

    }

    @Override
    public int getItemCount() {
        return cropResultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cropItemTextViewP1;
        TextView plantingTimeTextViewP1;
        TextView manageTipsTextViewP1;
        ImageView cropImageViewP1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cropItemTextViewP1 = itemView.findViewById(R.id.cropItemTextViewP1);
            plantingTimeTextViewP1 = itemView.findViewById(R.id.plantingTimeTextViewP1);
            manageTipsTextViewP1 = itemView.findViewById(R.id.manageTipsTextViewP1);
            cropImageViewP1 = itemView.findViewById(R.id.cropImageViewP1);

        }
    }

}
