package com.taba217.zad.ui.mainrecycler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.taba217.zad.R;
import com.taba217.zad.models.LectureItem;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.StackFrom;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.Holder> {

    Context context;
    ArrayList<LectureItem> Lecturer;
    onItemClickListener listener;
    private ArrayList<LectureItem> lectures;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    Bundle bundle = new Bundle();

    public interface onItemClickListener {
        void onItemClick(int i);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public CardStackAdapter(Context context, ArrayList<LectureItem> lectures) {
        this.context = context;
        this.lectures = lectures;
        // mealitems = new ArrayList<>();
    }

    @NotNull
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontallecitem, parent, false);
        return new Holder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        LectureItem lecture = lectures.get(position);
        Log.i("meals", lecture.getName());
        Log.i("lectures size", lectures.size() + "");
        for (LectureItem lectureItem : lectures
        ) {
            Log.i("all data in CSA", lectureItem.getName());
        }
        ArrayList<LectureItem> mealitemsfull = new ArrayList<>();
        mealitemsfull.add(lecture);
        if (lecture.getLectureSeries() != null)
            for (int i = 1; i <= lecture.getLectureSeries().size(); i++)
                mealitemsfull.add(lecture);
        cardstack(mealitemsfull);
    }

    @Override
    public int getItemCount() {
        return lectures.size();
    }

    private void cardstack(ArrayList<LectureItem> items) {
        CardStackLayoutManager cardStackLayoutManager = new CardStackLayoutManager(context);
        cardStackLayoutManager.setStackFrom(StackFrom.Bottom);
        HorizotalRecyclerChild adapter = new HorizotalRecyclerChild(context, items);
        card.setAdapter(adapter);

        for (LectureItem item : items) {
//            if (item.getTypeId() == 1)//string of lectures as lecture_series
            cardStackLayoutManager.setVisibleCount(3);
            adapter.setOnItemClickListener(i -> {
                bundle.putInt("lecture_id", item.getId());
                navigate(bundle);
            });
//                    Toast.makeText(context, item.getName() , Toast.LENGTH_SHORT).show());
            cardStackLayoutManager.setTranslationInterval(5.0f);//margin between cards
            card.setLayoutManager(cardStackLayoutManager);
        }
        // }
        // }
    }

    private void navigate(Bundle bundle) {
        NavController navController = Navigation.findNavController((AppCompatActivity) context, R.id.categories_nav_view);
        NavigationUI.setupActionBarWithNavController((AppCompatActivity) context, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                navController.navigate(R.id.mediaFragment, bundle);
            }
        });
    }

    CardStackView card;

    public class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView, final onItemClickListener listener) {
            super(itemView);
            card = itemView.findViewById(R.id.card_stack_view);
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