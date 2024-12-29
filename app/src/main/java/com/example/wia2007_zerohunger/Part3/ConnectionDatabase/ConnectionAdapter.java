package com.example.wia2007_zerohunger.Part3.ConnectionDatabase;

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

public class ConnectionAdapter extends RecyclerView.Adapter<ConnectionAdapter.ConnectionHolder> {

    private List<Connection> connectionList = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ConnectionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.connection_item_part3f1, parent, false);

        return new ConnectionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConnectionHolder holder, int position) {
        Connection currentConnection = connectionList.get(position);

        int connectionImageId = currentConnection.getConnectionImageId();

        if (connectionImageId == 1) {
            holder.connectionImageViewP3F1.setImageResource(R.drawable.farmp3);
            holder.productImageView1Part3F1.setImageResource(R.drawable.kangkung);
            holder.productImageView2Part3F1.setImageResource(R.drawable.cabbage);

        } else if (connectionImageId == 2) {
            holder.connectionImageViewP3F1.setImageResource(R.drawable.fresh_supermarket);
            holder.productImageView1Part3F1.setImageResource(R.drawable.sellapple);
            holder.productImageView2Part3F1.setImageResource(R.drawable.sellorange);

        } else if (connectionImageId == 3) {
            holder.connectionImageViewP3F1.setImageResource(R.drawable.durian_forest);
            holder.productImageView1Part3F1.setImageResource(R.drawable.durian);
            holder.productImageView2Part3F1.setImageResource(R.drawable.musang_king);

        } else {
            holder.connectionImageViewP3F1.setImageResource(R.drawable.aeon_industry);
            holder.productImageView1Part3F1.setImageResource(R.drawable.carrot_seeds);
            holder.productImageView2Part3F1.setImageResource(R.drawable.brocoli_seeds);
        }

        holder.connectionNameTextViewP3F1.setText(currentConnection.getConnectionName());
        String distance = String.valueOf(currentConnection.getConnectionDistance()) + " km";
        holder.distanceTextViewP3F1.setText(distance);
        holder.productTextViewP3F1.setText(currentConnection.getConnectionProduct());

        double product1Price = currentConnection.getProduct1Price();
        holder.product1PriceTextViewPart3F1.setText(String.valueOf(product1Price));

        double product2Price = currentConnection.getProduct2Price();
        holder.product2PriceTextViewPart3F1.setText(String.valueOf(product2Price));

    }

    @Override
    public int getItemCount() {
        return connectionList.size();
    }

    public void setConnections(List<Connection> connectionList) {
        this.connectionList = connectionList;
        notifyDataSetChanged();
    }

    public Connection getConnection(int position) {
        return connectionList.get(position);
    }

    public class ConnectionHolder extends RecyclerView.ViewHolder {

        ImageView connectionImageViewP3F1, productImageView1Part3F1, productImageView2Part3F1;
        TextView connectionNameTextViewP3F1, distanceTextViewP3F1, productTextViewP3F1;

        TextView product1PriceTextViewPart3F1, product2PriceTextViewPart3F1;

        public ConnectionHolder(@NonNull View itemView) {
            super(itemView);

            connectionImageViewP3F1 = itemView.findViewById(R.id.connectionImageViewP3F1);
            connectionNameTextViewP3F1 = itemView.findViewById(R.id.connectionNameTextViewP3F1);
            distanceTextViewP3F1 = itemView.findViewById(R.id.distanceTextViewP3F1);
            productTextViewP3F1 = itemView.findViewById(R.id.productTextViewP3F1);

            productImageView1Part3F1 = itemView.findViewById(R.id.productImageView1Part3F1);
            product1PriceTextViewPart3F1 = itemView.findViewById(R.id.product1PriceTextViewPart3F1);

            productImageView2Part3F1 = itemView.findViewById(R.id.productImageView2Part3F1);
            product2PriceTextViewPart3F1 = itemView.findViewById(R.id.product2PriceTextViewPart3F1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(connectionList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Connection connection);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

}
