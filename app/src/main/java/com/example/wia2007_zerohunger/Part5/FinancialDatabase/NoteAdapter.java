package com.example.wia2007_zerohunger.Part5.FinancialDatabase;

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

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.node_item, parent, false);

        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        int imageId = currentNote.getImageID();

        if (imageId == 1) {
            holder.FinancialAidImageP5.setImageResource(R.drawable.workspace1);

        } else if (imageId == 2) {
            holder.FinancialAidImageP5.setImageResource(R.drawable.workspace2);

        } else if (imageId == 3) {
            holder.FinancialAidImageP5.setImageResource(R.drawable.workspace3);

        } else if (imageId == 4) {
            holder.FinancialAidImageP5.setImageResource(R.drawable.workspace4);

        } else if (imageId == 5) {
            holder.FinancialAidImageP5.setImageResource(R.drawable.workspace5);

        }

        holder.FinancialAidNameP5.setText(currentNote.getAidName());
        String aidAmount = "Amount: RM " +String.valueOf(currentNote.getAidAmount());
        holder.FinancialAidAmountP5.setText(aidAmount);
        String availableSlots = "Slots: " + String.valueOf(currentNote.getAidSlots());
        holder.FinancialAidSlotsP5.setText(availableSlots);
        String dateline = "Dateline: " + currentNote.getAidDateLine();
        holder.FinancialAidDatelineP5.setText(dateline);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNote(int position) {
        return notes.get(position);
    }

    public class NoteHolder extends RecyclerView.ViewHolder {

        ImageView FinancialAidImageP5;
        TextView FinancialAidNameP5, FinancialAidAmountP5, FinancialAidSlotsP5, FinancialAidDatelineP5;;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            FinancialAidImageP5 = itemView.findViewById(R.id.FinancialAidImageP5);
            FinancialAidNameP5 = itemView.findViewById(R.id.FinancialAidNameP5);
            FinancialAidAmountP5 = itemView.findViewById(R.id.FinancialAidAmountP5);
            FinancialAidSlotsP5 = itemView.findViewById(R.id.FinancialAidSlotsP5);
            FinancialAidDatelineP5 = itemView.findViewById(R.id.FinancialAidDatelineP5);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(notes.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

}
