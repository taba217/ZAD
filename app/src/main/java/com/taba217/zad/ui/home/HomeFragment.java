package com.taba217.zad.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.taba217.zad.R;
import com.taba217.zad.models.ItemCategory;
import com.taba217.zad.ui.Recycler.HorizontalRecyclerTitle;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    HorizontalRecyclerTitle adapter;
    List<ItemCategory> items;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        items = new ArrayList<>();

        homeViewModel.getdata().observe(getViewLifecycleOwner(), new Observer<List<ItemCategory>>() {
            @Override
            public void onChanged(List<ItemCategory> itemCategories) {
                Toast.makeText(getActivity(), "onChanged " + itemCategories.get(0).getLecs().get(0).getLecName(), Toast.LENGTH_SHORT).show();
                adapter.setItemCategory(itemCategories);
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new HorizontalRecyclerTitle(getActivity(), items);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter);
    }
}