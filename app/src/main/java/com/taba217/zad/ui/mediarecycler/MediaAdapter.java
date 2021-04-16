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
    LectureItem lectureItem;


    public void setItems(LectureItem lecture) {
        lectureItem = lecture;
        this.lectures = new ArrayList<>(lecture.getLectureSeries());
    }

    public interface onItemClickListener {
        void onItemClick(int i);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public MediaAdapter(Context context) {
        this.context = context;
        lectures=new ArrayList<>();
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
        Log.i("media adapter on ", lecture.getName() + "");

        holder.lec.setText(lectureItem.getName());
        holder.lecturer.setText(lectureItem.getLecturerId()+"");
        holder.series.setText(lecture.getName());
    }

    @Override
    public int getItemCount() {
        if (lectures.size() != 0)
            return lectures.size();
        return 0;
    }


    public static class Holder extends RecyclerView.ViewHolder {
        TextView lecturer, lec, series;

        public Holder(View itemView, final onItemClickListener listener) {
            super(itemView);
            lecturer = itemView.findViewById(R.id.lecturer_name);
            lec = itemView.findViewById(R.id.lec_name);
            series = itemView.findViewById(R.id.series);
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