package com.example.wia2007_zerohunger.Part3.ResBookingDatabase;

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
public class ResBookingAdapter extends RecyclerView.Adapter<ResBookingAdapter.ResBookingHolder> {
    private List<ResBooking> resBookingList = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ResBookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_booking_items_part3f2, parent, false);

        return new ResBookingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResBookingHolder holder, int position) {
        ResBooking currentResBooking = resBookingList.get(position);

        int imageId = currentResBooking.getResBookingImageId();

        if (imageId == 1) {
            holder.imageViewBookingPart3F2.setImageResource(R.drawable.strawberry_picking);

        } else if (imageId == 2) {
            holder.imageViewBookingPart3F2.setImageResource(R.drawable.farm_volunteer);

        } else if (imageId == 3) {
            holder.imageViewBookingPart3F2.setImageResource(R.drawable.farming_workshop);
        } else {
            holder.imageViewBookingPart3F2.setImageResource(R.drawable.farm_tour);
        }

        holder.nameBookingTextViewP3F2.setText(currentResBooking.getResBookingName());
        holder.locationBookingTextViewP3F2.setText(currentResBooking.getResBookingLocation());
        holder.dateBookingTextViewP3F2.setText(currentResBooking.getResBookingDate());
        holder.timeBookingTextViewP3F2.setText(currentResBooking.getResBookingTimeSlots());
        holder.participantsBookingTextViewP3F2.setText(String.valueOf(currentResBooking.getResBookingNumParticipants()));

    }

    @Override
    public int getItemCount() {
        return resBookingList.size();
    }

    public void setResBookings(List<ResBooking> resBookingList) {
        this.resBookingList = resBookingList;
        notifyDataSetChanged();
    }

    public ResBooking getResBooking(int position) {
        return resBookingList.get(position);
    }

    public class ResBookingHolder extends RecyclerView.ViewHolder {

        ImageView imageViewBookingPart3F2;
        TextView nameBookingTextViewP3F2, locationBookingTextViewP3F2;
        TextView dateBookingTextViewP3F2, timeBookingTextViewP3F2, participantsBookingTextViewP3F2;

        public ResBookingHolder(@NonNull View itemView) {
            super(itemView);

            imageViewBookingPart3F2 = itemView.findViewById(R.id.imageViewBookingPart3F2);
            nameBookingTextViewP3F2 = itemView.findViewById(R.id.nameBookingTextViewP3F2);
            locationBookingTextViewP3F2 = itemView.findViewById(R.id.locationBookingTextViewP3F2);
            dateBookingTextViewP3F2 = itemView.findViewById(R.id.dateBookingTextViewP3F2);
            timeBookingTextViewP3F2 = itemView.findViewById(R.id.timeBookingTextViewP3F2);
            participantsBookingTextViewP3F2 = itemView.findViewById(R.id.participantsBookingTextViewP3F2);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(resBookingList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ResBooking resBooking);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }
}
