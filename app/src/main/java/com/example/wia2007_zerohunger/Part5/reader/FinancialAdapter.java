package com.example.wia2007_zerohunger.Part5.reader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wia2007_zerohunger.Part1.soil_analysis.CropResult;
import com.example.wia2007_zerohunger.R;

import java.util.ArrayList;
import java.util.List;

public class FinancialAdapter extends RecyclerView.Adapter<FinancialAdapter.FinancialHolder> {

    private List<FinancialData> financialList = new ArrayList<>();
    private OnItemClickListener listener;

    private Context context;

    public FinancialAdapter(List<FinancialData> financialList, Context context) {
        this.financialList = financialList;
        this.context = context;
    }

    @NonNull
    @Override
    public FinancialHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.financial_item, parent, false);

        return new FinancialHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FinancialHolder holder, int position) {
        FinancialData currentFinancialData = financialList.get(position);
        /*holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
         */

        holder.textViewFinancialTitleP5.setText(currentFinancialData.getLocality());
        holder.textViewAmountP5.setText(currentFinancialData.getFips());
        holder.textViewAvailableSlotP5.setText(currentFinancialData.getHouseholdsServed());
        String date = currentFinancialData.getMonth() + "/" + currentFinancialData.getYear();
        holder.textViewDateLineP5.setText(date);

    }

    @Override
    public int getItemCount() {
        return financialList.size();
    }

    public void setFinancialData(List<FinancialData> financialList) {
        this.financialList = financialList;
        notifyDataSetChanged();
    }

    public FinancialData getFinancialData(int position) {
        return financialList.get(position);
    }

    public class FinancialHolder extends RecyclerView.ViewHolder {

        TextView textViewFinancialTitleP5;
        TextView textViewAmountP5;
        TextView textViewAvailableSlotP5;
        TextView textViewDateLineP5;


        public FinancialHolder(@NonNull View itemView) {
            super(itemView);

            textViewFinancialTitleP5 = itemView.findViewById(R.id.textViewFinancialTitleP5);
            textViewAmountP5 = itemView.findViewById(R.id.textViewAmountP5);
            textViewAvailableSlotP5 = itemView.findViewById(R.id.textViewAvailableSlotP5);
            textViewDateLineP5 = itemView.findViewById(R.id.textViewDateLineP5);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(financialList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(FinancialData financialData);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

}