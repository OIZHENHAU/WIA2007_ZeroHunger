package com.example.wia2007_zerohunger.Part3.ReservationDatabase;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wia2007_zerohunger.Part3.ConnectionDatabase.Connection;
import com.example.wia2007_zerohunger.Part3.ConnectionDatabase.ConnectionAdapter;
import com.example.wia2007_zerohunger.R;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationHolder> {
    private List<Reservation> reservationList = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ReservationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item_part3f2, parent, false);

        return new ReservationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationHolder holder, int position) {
        Reservation currentReservation = reservationList.get(position);

        int imageId = currentReservation.getImageId();
        Log.d("Image ID: ", String.valueOf(imageId));

        if (imageId == 1) {
            Log.d("Image ID", String.valueOf("1 here"));
            holder.imageViewPart3F2.setImageResource(R.drawable.strawberry_picking);

        } else if (imageId == 2) {
            Log.d("Image ID", "2 here");
            holder.imageViewPart3F2.setImageResource(R.drawable.farm_volunteer);

        } else if (imageId == 3) {
            Log.d("Image ID", "3 here");
            holder.imageViewPart3F2.setImageResource(R.drawable.farming_workshop);
        } else {
            Log.d("Image ID", "4 here");
            holder.imageViewPart3F2.setImageResource(R.drawable.farm_tour);
        }

        holder.locationTextViewP3F2.setText(currentReservation.getReservationName());
        holder.descriptionTextViewP3F2.setText(currentReservation.getReservationDescription());
        holder.ratingTextViewP3F2.setText(String.valueOf(currentReservation.getRating()));

    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public void setReservations(List<Reservation> reservationList) {
        this.reservationList = reservationList;
        notifyDataSetChanged();
    }

    public Reservation getReservation(int position) {
        return reservationList.get(position);
    }

    public class ReservationHolder extends RecyclerView.ViewHolder {

        ImageView imageViewPart3F2;
        TextView locationTextViewP3F2, descriptionTextViewP3F2, ratingTextViewP3F2;;

        public ReservationHolder(@NonNull View itemView) {
            super(itemView);

            imageViewPart3F2 = itemView.findViewById(R.id.imageViewPart3F2);

            locationTextViewP3F2 = itemView.findViewById(R.id.locationTextViewP3F2);
            descriptionTextViewP3F2 = itemView.findViewById(R.id.descriptionTextViewP3F2);
            ratingTextViewP3F2 = itemView.findViewById(R.id.ratingTextViewP3F2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(reservationList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Reservation reservation);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }
}
