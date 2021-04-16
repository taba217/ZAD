package com.taba217.zad.ui.mediaplayer;

import android.content.Context;
import android.text.style.UpdateLayout;
import android.view.View;
import android.widget.Toast;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.common.collect.ImmutableList;
import com.taba217.zad.R;
import com.taba217.zad.models.LectureSeriesItem;

import java.util.ArrayList;

public class MediaPlayer {
    static Context context;
    public static SimpleExoPlayer instance;

    public static synchronized SimpleExoPlayer getInstance(Context cxt, PlayerControlView playerview, ArrayList<LectureSeriesItem> lectures) {
        context = cxt;
        if (instance == null) {
            instance = new SimpleExoPlayer.Builder(context).build();
            instance.addAudioListener(new AudioListener() {
                @Override
                public void onAudioSessionId(int audioSessionId) {
                    Toast.makeText(context, "" + audioSessionId, Toast.LENGTH_SHORT).show();
                    playerview.findViewById(R.id.loading_lec).setVisibility(View.GONE);
                }
            });
        }
//        instance.seekToDefaultPosition(5);

        ArrayList<MediaItem> list = new ArrayList<>();
        for (LectureSeriesItem lecture : lectures) {
            list.add(MediaItem.fromUri(lecture.getUrl()));
        }
        playerview.setPlayer(instance);
        instance.setMediaItems(list);
        playerview.setShowTimeoutMs(0);
        instance.prepare();

        return instance;
    }


    void setList() {
//        list = list = ImmutableList.of(
//                    MediaItem.fromUri(fourthUri),
//                    MediaItem.fromUri(fifthUri));
    }

    void resum() {
//        MediaItem mediaItem = new MediaItem.Builder()
//                .setUri(videoUri)
//                .setClipStartPositionMs(startPositionMs)
//                .setClipEndPositionMs(endPositionMs)
//                .build();
    }

}