package com.taba217.zad.ui.maincategories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.taba217.zad.R;
import com.taba217.zad.models.Lecturer;
import com.taba217.zad.models.Lecturer;
import com.taba217.zad.ui.mainrecycler.HorizontalRecyclerTitle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainCategories extends Fragment {

    private MainCategoriesViewModel mainCategoriesViewModel;
    RecyclerView recyclerView;
    HorizontalRecyclerTitle adapter;
    List<Lecturer> Lecturers;
    Spinner categoryspinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainCategoriesViewModel =
                new ViewModelProvider(this).get(MainCategoriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_maincategories, container, false);
        recyclerView = root.findViewById(R.id.recycler);
        categoryspinner = root.findViewById(R.id.category_spinner);
        Lecturers = new ArrayList<>();
        mainCategoriesViewModel.getdata().observe(getViewLifecycleOwner(), new Observer<List<Lecturer>>() {
            @Override
            public void onChanged(List<Lecturer> itemCategories) {
                adapter.setLecturer(itemCategories);
                Lecturers.addAll(itemCategories);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new HorizontalRecyclerTitle(getActivity(), Lecturers);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter);

        ArrayList<String> categories = new ArrayList<>(Arrays.asList("محاضرات ", "دروس", "سلسلة دروس", "خطب"));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, categories);
        categoryspinner.setAdapter(adapter);
        categoryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainCategoriesViewModel viewModel = new MainCategoriesViewModel();
                viewModel.getData();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}