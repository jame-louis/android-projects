package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ProgressBar progressBar;
    private Handler progressHandler = new Handler(Looper.getMainLooper());
    private Runnable progressUpdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        initMedia();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopProgressUpdate();
        destoryMedia();
    }

    public void onClickPlay(View v) {
        if(mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            startProgressUpdate();
        }
    }

    public void onClickStop(View v) {
        if(mediaPlayer!=null) {
            mediaPlayer.stop();
            progressBar.setProgress(0);
            stopProgressUpdate();
        }
    }

    public void onClickPause(View v){
        if(mediaPlayer!=null) {
            mediaPlayer.pause();
            stopProgressUpdate();
        }
    }

    void initMedia() {
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.lukewarm_banjo);
        mediaPlayer.setOnPreparedListener(mp -> {
            // 把进度条最大值设为音频总时长（毫秒）
            progressBar.setMax(mp.getDuration());
        });
        mediaPlayer.setOnCompletionListener(mp -> stopProgressUpdate());
    }

    void destoryMedia() {
        if(mediaPlayer!=null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    /* 开始刷新进度 */
    private void startProgressUpdate() {
        progressUpdater = new Runnable() {
            @Override
            public void run() {
                Log.i("media-player", "update progress : " + progressBar.getProgress());
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    int pos = mediaPlayer.getCurrentPosition();
                    progressBar.setProgress(pos);
                    progressHandler.postDelayed(this, 300); // 300 ms 刷新一次
                }
            }
        };
        progressHandler.post(progressUpdater);
    }

    /* 停止刷新进度 */
    private void stopProgressUpdate() {
        if (progressUpdater != null) {
            Log.i("media-player", "stopProgressUpdate: ");
            progressHandler.removeCallbacks(progressUpdater);
            progressUpdater = null;
        }
    }

}