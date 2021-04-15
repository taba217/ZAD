package com.taba217.zad.ui.mediaplayer;

import android.net.Uri;
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

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.taba217.zad.R;
import com.taba217.zad.connction.Connect;
import com.taba217.zad.models.LectureItem;
import com.taba217.zad.models.LectureSeriesItem;
import com.taba217.zad.models.Lecturer;
import com.taba217.zad.ui.mainrecycler.HorizontalRecyclerTitle;
import com.taba217.zad.ui.mediarecycler.MediaAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MediaFragment extends Fragment {

    private MediaModelView mediaModelView;
    MediaAdapter adapter;
    List<LectureSeriesItem> items;
    PlayerControlView playerview;
    SimpleExoPlayer player;
    View rootplayer;

    Bundle bundle = new Bundle();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mediaModelView = new ViewModelProvider(this).get(MediaModelView.class);
        View root = inflater.inflate(R.layout.fragment_media, container, false);
        items = new ArrayList<>();

        playerview = root.findViewById(R.id.player);
        playerview.setOnFocusChangeListener((v, hasFocus) -> {
            Toast.makeText(getActivity(), "hasFocus changed to " + hasFocus, Toast.LENGTH_SHORT).show();
            //   changePlayerSize(hasFocus, v, inflater, container);
        });
        mediaModelView.getdata().observe(getViewLifecycleOwner(), new Observer<LectureItem>() {
            @Override
            public void onChanged(LectureItem lecture) {
                adapter.setItems((ArrayList<LectureSeriesItem>) lecture.getLectureSeries());
                items.addAll(lecture.getLectureSeries());
            }
        });

        return root;
    }




    private void changePlayerSize(boolean hasFocus, View v, LayoutInflater inflater, ViewGroup container) {
        if (hasFocus) {
            rootplayer = playerview.getChildAt(0);
            playerview.removeView(rootplayer);
            playerview.addView(inflater.inflate(R.layout.exo_player_control_view_mini, container, false));
            playerview.setShowTimeoutMs(0);
            playerview.show();
        } else
            playerview.addView(rootplayer);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MediaAdapter(getActivity(), (ArrayList<LectureSeriesItem>) items);
        adapter.setOnItemClickListener(new MediaAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int i) {
                player = MediaPlayer.getInstance(getActivity(), playerview, (ArrayList<LectureSeriesItem>) items);
                player.play();
                playerview.setVisibility(VISIBLE);

            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.media_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter);
    }
}