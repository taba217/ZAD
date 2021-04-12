package com.taba217.zad.ui.mainrecycler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.taba217.zad.R;
import com.taba217.zad.connction.BaseUrl;
import com.taba217.zad.models.LectureItem;


import java.util.ArrayList;
import java.util.List;


public class HorizotalRecyclerChild extends RecyclerView.Adapter<HorizotalRecyclerChild.CustumViewHolder> implements Filterable {
    private Context context;
    private ArrayList<LectureItem> lectures;
    private ArrayList<LectureItem> alllectures;
    int donor;

    public HorizotalRecyclerChild(Context context, ArrayList<LectureItem> items) {
        this.context = context;
        this.lectures = items;
        alllectures = new ArrayList<>(lectures);
    }

    private onItemClickListener listener;


    public interface onItemClickListener {
        void onItemClick(int i);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lec_item, parent, false);
        return new CustumViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustumViewHolder holder, int position) {
        LectureItem lecture = lectures.get(position);
        // Drawable drawable = icons.getDrawable(position);
        //int resID = getResId("icon", R.drawable.class); // or other resource class
        //   cardstack(mealitem.getPrices());
        Log.i("meals in h Recyc chid ", lecture.getName());
        holder.lecName.setText(lecture.getName());
        Picasso.get()
                .load(BaseUrl.BASE_URL + "images/" + lecture.getTypeId() + "/" + lecture.getId())
                .fit()
                .into(holder.lecImage);
    }


    static class CustumViewHolder extends RecyclerView.ViewHolder {
        TextView lecName;
        ImageView lecImage;


        CustumViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            lecName = itemView.findViewById(R.id.lec_name);
            lecImage = itemView.findViewById(R.id.lec_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int i = getAdapterPosition();
                        if (i != RecyclerView.NO_POSITION)
                            listener.onItemClick(i);
                    }
                }
            });


        }


    }


    @Override
    public int getItemCount() {
        if (lectures.size() != 0)
            return lectures.size();
        return 0;
    }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }


    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<LectureItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(alllectures);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (LectureItem item : alllectures) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            for (int j = getItemCount(); j >= 0; j--)
                notifyItemRemoved(j);
            lectures.clear();
            lectures.addAll((List) results.values);
            notifyItemInserted(0);
        }
    };

}



