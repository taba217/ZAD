package com.taba217.zad.ui.mainrecycler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.taba217.zad.R;
import com.taba217.zad.models.LectureItem;
import com.taba217.zad.models.Lecturer;


import java.util.ArrayList;
import java.util.List;


public class HorizontalRecyclerTitle extends RecyclerView.Adapter<HorizontalRecyclerTitle.CustumViewHolder> {

    private Context context;
    private List<Lecturer> itemCategories;
    private ArrayList<ArrayList<LectureItem>> mealitems;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public HorizontalRecyclerTitle(Context context, List<Lecturer> items) {
        this.context = context;
        this.itemCategories = items;
        mealitems = new ArrayList<>();
        Log.i("taba title adapter ", items.size() + "");
    }

    private onItemClickListener listener;

    public interface onItemClickListener {
        void onItemClick(int i);
    }

    public void setLecturer(List<Lecturer> items) {
        itemCategories = items;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.horizontallectitle, parent, false);
        Log.i("title adapter ", "onCreateViewHolder");
        return new CustumViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustumViewHolder holder, int position) {
        final Lecturer Lecturer = itemCategories.get(position);
        Log.i("taba title adapter ", Lecturer.getLecture().get(0).getName());
        if (Lecturer.getLecture() != null) {
            mealitems.add((ArrayList<LectureItem>) Lecturer.getLecture());
            // Log.i("meals in h r t ",Lecturer.getMeals().get(position).getMealname());
            holder.Lecturer_title.setText(Lecturer.getName());

            // Create background manager with initial prefetch item count
            LinearLayoutManager layoutManager = new LinearLayoutManager(
                    holder.recyclerView.getContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
            );
            layoutManager.setInitialPrefetchItemCount(mealitems.get(position).size());
//            ArrayList<mealitem> mealitems = new ArrayList<>();
//            mealitems.add(new mealitem("meal", "food", "120", "", new ArrayList<>(Arrays.asList("120", "145", "66"))));
//            mealitems.add(new mealitem("meal", "food", "120", "", new ArrayList<>(Arrays.asList("50", "145", "66"))));
//            mealitems.add(new mealitem("meal", "food", "120", "", new ArrayList<>(Arrays.asList("6", "66"))));
//            mealitems.add(new mealitem("meal", "food", "120", "", new ArrayList<>(Arrays.asList("2", "312"))));
//            mealitems.add(new mealitem("meal", "food", "120", "", new ArrayList<>(Arrays.asList("00", "145", "145", "66"))));
            // Create sub item view adapter
            //  holder.recyclerView.setItemAnimator(new FadeInDownAnimator(new OvershootInterpolator(0f)));
            CardStackAdapter cardStackAdapter = new CardStackAdapter(context, mealitems.get(position));
            //  LinearLayoutManager layoutManager new LinearLayoutManager(context)
            holder.recyclerView.setLayoutManager(layoutManager);

            cardStackAdapter.setOnItemClickListener(new CardStackAdapter.onItemClickListener() {
                @Override
                public void onItemClick(int i) {
                    Toast.makeText(context, i+"", Toast.LENGTH_SHORT).show();
                }
            });
            holder.recyclerView.setAdapter(cardStackAdapter);
        }
    }


    static class CustumViewHolder extends RecyclerView.ViewHolder {

        TextView Lecturer_title;

        RecyclerView recyclerView;

        CustumViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);

            Lecturer_title = itemView.findViewById(R.id.categories_title);
            recyclerView = itemView.findViewById(R.id.horizntal_recycler);
            // status.setEnabled(false);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (itemCategories.size() != 0)
            return itemCategories.size();
        return 0;
    }


}
