package com.taba217.zad.ui.mediarecycler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.taba217.zad.R;
import com.taba217.zad.models.LectureItem;
import com.taba217.zad.models.LectureSeriesItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.Holder> {

    Context context;
    ArrayList<LectureSeriesItem> lectures;
    onItemClickListener listener;
//    private ArrayList<LectureItem> lectures;

    public void setItems(ArrayList<LectureSeriesItem> lectures) {
        this.lectures=new ArrayList<>();
        this.lectures=lectures;
    }

    public interface onItemClickListener {
        void onItemClick(int i);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public MediaAdapter(Context context, ArrayList<LectureSeriesItem> lectures) {
        this.context = context;
        this.lectures = lectures;
        // mealitems = new ArrayList<>();
    }

    @NotNull
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_item, parent, false);
        return new Holder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        LectureSeriesItem lecture = lectures.get(position);
        Log.i("meals", lecture.getName());

        holder.lec.setText(lecture.getName());
//        holder.shak.setText(lecture.getLecturerId());
//        holder.episode.setText(lecture.getTypeId());

    }

    @Override
    public int getItemCount() {
        return lectures.size();
    }


    public static class Holder extends RecyclerView.ViewHolder {
        TextView shak, lec, episode;

        public Holder(View itemView, final onItemClickListener listener) {
            super(itemView);
            shak = itemView.findViewById(R.id.shak_name);
            lec = itemView.findViewById(R.id.lec_name);
            episode = itemView.findViewById(R.id.episode);
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int i = getAdapterPosition();
                    if (i != RecyclerView.NO_POSITION)
                        listener.onItemClick(i);
                }
            });

        }
    }
}