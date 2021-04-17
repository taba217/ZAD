package com.taba217.zad.ui.mediaplayer;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;
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
import static com.taba217.zad.ui.mediaplayer.MediaPlayer.playerNotificationManager;

public class MediaFragment extends Fragment {

    private MediaModelView mediaModelView;
    MediaAdapter adapter;
    ArrayList<LectureSeriesItem> items;
    PlayerControlView playerview;
    SimpleExoPlayer player;
    View rootplayer;
    TextView series, lec_name;
    ProgressBar loading;
    LectureItem lectureItem;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
//        MediaPlayer.onDestroy();
        super.onDetach();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mediaModelView = new ViewModelProvider(this).get(MediaModelView.class);
        View root = inflater.inflate(R.layout.fragment_media, container, false);
        items = new ArrayList<>();

        playerview = root.findViewById(R.id.player);
        series = playerview.findViewById(R.id.series);
        lec_name = playerview.findViewById(R.id.lec_name);
        loading = playerview.findViewById(R.id.loading_lec);

        mediaModelView.getdata(getArguments().getInt("lecture_id")).observe(getViewLifecycleOwner(), new Observer<LectureItem>() {
            @Override
            public void onChanged(LectureItem lecture) {
                lectureItem = lecture;
                adapter.setItems(lecture);
                items.addAll(lecture.getLectureSeries());
                adapter.notifyItemRangeInserted(0, lecture.getLectureSeries().size());

            }
        });
        return root;
    }


    RecyclerView recyclerView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MediaAdapter(getActivity());
        adapter.setOnItemClickListener(new MediaAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int i) {
                player = MediaPlayer.getInstance(getActivity(), playerview, items);
                lec_name.setText(lectureItem.getName());
                series.setText(items.get(i).getName());
                player.play();
            }
        });
        recyclerView = view.findViewById(R.id.media_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter);
        if (player != null)
            playerview.setVisibility(VISIBLE);
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

}