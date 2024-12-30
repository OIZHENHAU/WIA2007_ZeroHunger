package com.example.wia2007_zerohunger.Part3.SubscriptionDatabase;

import android.util.Log;
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

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.SubscriptionHolder> {

    private List<Subscription> subscriptionList = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public SubscriptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscription_item_part3f3, parent, false);

        return new SubscriptionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubscriptionHolder holder, int position) {
        Subscription currentSubscription = subscriptionList.get(position);

        int imageId = currentSubscription.getSubscriptionImageId();

        if (imageId == 1) {
            holder.imageSubscriptionItemP3F3.setImageResource(R.drawable.cabbage);

        } else if (imageId == 2) {
            holder.imageSubscriptionItemP3F3.setImageResource(R.drawable.kangkung);

        } else if (imageId == 3) {
            holder.imageSubscriptionItemP3F3.setImageResource(R.drawable.carrot);

        } else if (imageId == 4) {
            holder.imageSubscriptionItemP3F3.setImageResource(R.drawable.sellbanana);

        } else {
            holder.imageSubscriptionItemP3F3.setImageResource(R.drawable.milk);

        }

        holder.nameSubscriptionItemP3F3.setText(currentSubscription.getSubscriptionName());
        holder.weeklyPriceSubscriptionItemP3F3.setText(String.valueOf(currentSubscription.getWeeklyPrice()));
        holder.monthlyPriceSubscriptionItemP3F3.setText(String.valueOf(currentSubscription.getMonthlyPrice()));

    }

    @Override
    public int getItemCount() {
        return subscriptionList.size();
    }

    public void setSubscriptions(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
        notifyDataSetChanged();
    }

    public Subscription getSubscription(int position) {
        return subscriptionList.get(position);
    }

    public class SubscriptionHolder extends RecyclerView.ViewHolder {

        ImageView imageSubscriptionItemP3F3;
        TextView nameSubscriptionItemP3F3, weeklyPriceSubscriptionItemP3F3, monthlyPriceSubscriptionItemP3F3;

        public SubscriptionHolder(@NonNull View itemView) {
            super(itemView);

            imageSubscriptionItemP3F3 = itemView.findViewById(R.id.imageSubscriptionItemP3F3);
            nameSubscriptionItemP3F3 = itemView.findViewById(R.id.nameSubscriptionItemP3F3);
            weeklyPriceSubscriptionItemP3F3 = itemView.findViewById(R.id.weeklyPriceSubscriptionItemP3F3);
            monthlyPriceSubscriptionItemP3F3 = itemView.findViewById(R.id.monthlyPriceSubscriptionItemP3F3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(subscriptionList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Subscription subscription);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

}
