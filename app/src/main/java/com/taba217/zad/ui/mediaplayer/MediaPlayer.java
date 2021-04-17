package com.taba217.zad.ui.mediaplayer;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.text.style.UpdateLayout;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.common.collect.ImmutableList;
import com.taba217.zad.R;
import com.taba217.zad.models.LectureSeriesItem;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MediaPlayer {
    static Context context;
    public static SimpleExoPlayer instance;
    public static PlayerNotificationManager playerNotificationManager;
    static ProgressBar loading;
    static PlayerControlView playerView;
    private static PlayerNotificationManager.MediaDescriptionAdapter mediaDescriptionAdapter = new PlayerNotificationManager
            .MediaDescriptionAdapter() {
        @Override
        public String getCurrentSubText(Player player) {
            return "Sub text";
        }

        @Override
        public String getCurrentContentTitle(Player player) {
            return "Title";
        }

        @Override
        public PendingIntent createCurrentContentIntent(Player player) {
            return null;
        }

        @Override
        public String getCurrentContentText(Player player) {
            return "ContentText";
        }

        @Override
        public Bitmap getCurrentLargeIcon(Player player, PlayerNotificationManager.BitmapCallback callback) {
            return null;
        }
    };

    public static synchronized SimpleExoPlayer getInstance(Context cxt, PlayerControlView playerview, ArrayList<LectureSeriesItem> lectures) {
        context = cxt;
        playerView = playerview;
        loading = playerView.findViewById(R.id.loading_lec);
        if (instance == null) {
            instance = new SimpleExoPlayer.Builder(context).build();
            instance.addAudioListener(new AudioListener() {
                @Override
                public void onAudioSessionId(int audioSessionId) {
                    Toast.makeText(context, "" + audioSessionId, Toast.LENGTH_SHORT).show();
                }
            });
            playerView.setVisibility(VISIBLE);
        }
        playerView.setVisibility(VISIBLE);
        ArrayList<MediaItem> list = new ArrayList<>();
        for (LectureSeriesItem lecture : lectures) {
            list.add(MediaItem.fromUri(lecture.getUrl()));
        }
        playerview.setPlayer(instance);
        instance.setMediaItems(list);
        playerview.setShowTimeoutMs(0);
        instance.prepare();
        playerNotificationManager = PlayerNotificationManager.
                createWithNotificationChannel(context, "1234", R.string.channel_name, R.string.app_name, 123, mediaDescriptionAdapter);
        playerNotificationManager.setSmallIcon(R.drawable.search_background);
        playerNotificationManager.setPlayer(instance);

        return instance;
    }

    public static void onDestroy() {
        if (playerNotificationManager != null) {
            playerNotificationManager.setPlayer(null);
        }
        if (instance != null) {
            instance.release();
            instance = null;
        }
    }


    public static void playerListener() {
        instance.addListener(new Player.EventListener() {
            @Override
            public void onIsLoadingChanged(boolean isLoading) {
                if (isLoading) {
                    loading.setVisibility(VISIBLE);
                    playerView.setVisibility(VISIBLE);
                }
            }

            @Override
            public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {
                Toast.makeText(context, playWhenReady + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onIsPlayingChanged(boolean isPlaying) {
                Toast.makeText(context, "isPlaying " + isPlaying, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                Toast.makeText(context, error + " error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                Toast.makeText(context, playbackParameters + " playbackParameters", Toast.LENGTH_SHORT).show();
            }
        });
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