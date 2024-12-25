package com.example.wia2007_zerohunger.Part4;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wia2007_zerohunger.R;

import java.util.List;

public class ConsultationAdapter extends RecyclerView.Adapter<ConsultationAdapter.ConsultationViewHolder> {
    private final List<Consultation> consultationList;
    private final OnConsultationClickListener listener;

    // 构造方法
    public ConsultationAdapter(List<Consultation> consultationList, OnConsultationClickListener listener) {
        this.consultationList = consultationList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ConsultationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consultation, parent, false);
        return new ConsultationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultationViewHolder holder, int position) {
        if (consultationList == null || consultationList.isEmpty()) {
            Log.e("ConsultationAdapter", "Empty consultation list");
            return;
        }
        Consultation consultation = consultationList.get(position);
        Log.d("ConsultationAdapter", "Binding data at position: " + position +
                ", Expert: " + consultation.getExpertName() + ", Date: " + consultation.getDate());

        holder.tvExpertName.setText("Expert Name: " + consultation.getExpertName());
        holder.tvConsultationDate.setText("Date: " + consultation.getDate());
        holder.tvTopicSummary.setText("Topic: " + consultation.getTopicSummary());
        holder.btnViewDetails.setOnClickListener(v -> listener.onClick(consultation));
        Log.d("ConsultationAdapter", "Binding data at position: " + position);

    }

    @Override
    public int getItemCount() {
        Log.d("ConsultationAdapter", "Total items: " + consultationList.size());
        return consultationList.size();
    }

    // 自定义 ViewHolder
    static class ConsultationViewHolder extends RecyclerView.ViewHolder {
        TextView tvExpertName, tvConsultationDate, tvTopicSummary;
        Button btnViewDetails;

        public ConsultationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvExpertName = itemView.findViewById(R.id.tvExpertName);
            tvConsultationDate = itemView.findViewById(R.id.tvConsultationDate);
            tvTopicSummary = itemView.findViewById(R.id.tvTopicSummary); // 添加初始化
            btnViewDetails = itemView.findViewById(R.id.btnViewDetails);
        }
    }

    // 点击事件接口
    public interface OnConsultationClickListener {
        void onClick(Consultation consultation);
    }
}

