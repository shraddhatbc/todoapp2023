package com.ace.noteme;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {

    private List<notesEn> notesData = new ArrayList<>();
    private OnItemClickListener listener;

    @Override
    public int getItemCount() {
        return notesData.size();
    }

    public int getPosition() {
        return getPosition();
    }

    public interface OnItemClickListener {
        void onItemClick(notesEn note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View notesRow = layoutInflater.inflate(R.layout.notes_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(notesRow);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final notesEn data = notesData.get(position);
        holder.NoteMeTitle.setText(data.getNotesTitle());
        holder.NoteMeDescription.setText(data.getNotesText());
    }

    public void setNotesData(List<notesEn> notesData) {
        this.notesData.clear();
        this.notesData = notesData;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView NoteMeTitle;
        public TextView NoteMeDescription;
        public RelativeLayout container;
        public ImageView delete;

        public ViewHolder(View view) {
            super(view);
            this.NoteMeTitle = view.findViewById(R.id.NoteMeTitle);
            this.NoteMeDescription = view.findViewById(R.id.NoteMeDescription);
            this.container = view.findViewById(R.id.notes_row);
            this.delete = view.findViewById(R.id.delete);
            boolean undo = false;
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    MainActivity.deleteNote(notesData.get(position));
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(notesData.get(position));
                    }
                }
            });

        }
    }
}



