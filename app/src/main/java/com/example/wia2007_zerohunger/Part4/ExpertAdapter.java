package com.example.wia2007_zerohunger.Part4;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wia2007_zerohunger.R;

import java.util.List;
public class ExpertAdapter extends RecyclerView.Adapter<ExpertAdapter.ExpertViewHolder> {
    private List<Expert> expertList; // 专家数据列表
    private OnExpertClickListener listener; // 点击事件监听器

    // 构造方法
    public ExpertAdapter(List<Expert> expertList, OnExpertClickListener listener) {
        this.expertList = expertList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExpertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 加载 item_expert.xml 布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expert, parent, false);
        return new ExpertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpertViewHolder holder, int position) {
        // 绑定数据
        Expert expert = expertList.get(position);
        Log.d("ExpertAdapter", "Binding data for expert: " + expert.getName() + ", specialization: " + expert.getSpecialization());

        holder.ivAvatar.setImageResource(expert.getAvatarResId()); // 设置头像
        holder.tvName.setText("Name: " + expert.getName()); // 设置名字
        holder.tvSpecialization.setText("Good At: " + expert.getSpecialization()); // 设置擅长领域
        holder.ratingBar.setRating((float) expert.getRating()); // 设置评分

        // 设置点击事件
        holder.btnViewDetails.setOnClickListener(v -> {
            if (listener != null) {
                listener.onExpertClick(expert);
            }
        });
    }

    @Override
    public int getItemCount() {
        return expertList.size(); // 返回列表大小
    }

    // 定义 ViewHolder 内部类
    public static class ExpertViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar; // 头像
        TextView tvName, tvSpecialization; // 名字和擅长领域
        RatingBar ratingBar; // 评分
        View btnViewDetails; // 查看详情按钮

        public ExpertViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivExpertAvatar);
            tvName = itemView.findViewById(R.id.tvExpertName);
            tvSpecialization = itemView.findViewById(R.id.tvExpertSpecialization);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            btnViewDetails = itemView.findViewById(R.id.btnViewDetails);
        }
    }

    // 定义点击事件监听器接口
    public interface OnExpertClickListener {
        void onExpertClick(Expert expert);
    }
}

