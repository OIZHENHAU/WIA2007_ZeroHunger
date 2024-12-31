package com.example.wia2007_zerohunger.Part3.SubBookingDatabase;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wia2007_zerohunger.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SubBookingAdapter extends RecyclerView.Adapter<SubBookingAdapter.SubBookingHolder> {

    private List<SubBooking> subBookingList = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public SubBookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_booking_item_part3f3, parent, false);

        return new SubBookingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubBookingHolder holder, int position) {
        SubBooking currentSubBooking = subBookingList.get(position);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");

        int imageId = currentSubBooking.getSubBookingImageId();

        if (imageId == 1) {
            holder.imageSubBookingItemP3F3.setImageResource(R.drawable.cabbage);

        } else if (imageId == 2) {
            holder.imageSubBookingItemP3F3.setImageResource(R.drawable.kangkung);

        } else if (imageId == 3) {
            holder.imageSubBookingItemP3F3.setImageResource(R.drawable.carrot);

        } else if (imageId == 4) {
            holder.imageSubBookingItemP3F3.setImageResource(R.drawable.sellbanana);

        } else {
            holder.imageSubBookingItemP3F3.setImageResource(R.drawable.milk);

        }

        holder.nameSubBookingItemP3F3.setText(currentSubBooking.getSubBookingName());

        int planSubscriptionCode = currentSubBooking.getSubBookingPlanCode();

        if (planSubscriptionCode == 1) {
            holder.planSubBookingItemP3F3.setText("Weekly");
            LocalDate date = LocalDate.now().plusDays(7);
            String formattedDate = date.format(formatter);

            holder.dateSubBookingItemP3F3.setText(formattedDate);

        } else {
            holder.planSubBookingItemP3F3.setText("Monthly");
            LocalDate date = LocalDate.now().plusDays(30);
            String formattedDate = date.format(formatter);

            holder.dateSubBookingItemP3F3.setText(formattedDate);

        }

        holder.priceSubBookingItemP3F3.setText(String.valueOf(currentSubBooking.getSubBookingPrice()));

    }

    @Override
    public int getItemCount() {
        return subBookingList.size();
    }

    public void setSubBookings(List<SubBooking> subBookingList) {
        this.subBookingList = subBookingList;
        notifyDataSetChanged();
    }

    public SubBooking getSubBooking(int position) {
        return subBookingList.get(position);
    }

    public class SubBookingHolder extends RecyclerView.ViewHolder {

        ImageView imageSubBookingItemP3F3;
        TextView nameSubBookingItemP3F3, planSubBookingItemP3F3, dateSubBookingItemP3F3, priceSubBookingItemP3F3;

        public SubBookingHolder(@NonNull View itemView) {
            super(itemView);

            imageSubBookingItemP3F3 = itemView.findViewById(R.id.imageViewBookingItemPart3F3);
            nameSubBookingItemP3F3 = itemView.findViewById(R.id.nameBookingItemTextViewP3F3);
            planSubBookingItemP3F3 = itemView.findViewById(R.id.planBookingItemTextViewP3F3);
            dateSubBookingItemP3F3 = itemView.findViewById(R.id.dateBookingItemTextViewP3F3);
            priceSubBookingItemP3F3 = itemView.findViewById(R.id.priceBookingItemTextViewP3F3);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(subBookingList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SubBooking subBooking);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }
}
