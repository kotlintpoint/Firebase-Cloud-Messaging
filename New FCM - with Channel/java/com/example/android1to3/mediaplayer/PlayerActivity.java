package com.example.android1to3.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.android1to3.R;

public class PlayerActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    ImageButton btnPlay;
    MediaPlayer mediaPlayer;
    private SeekBar songProgress;
    TextView tvTotalDuration, tvCurrentPosition;
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        songProgress=findViewById(R.id.songProgress);
        tvTotalDuration=findViewById(R.id.tvTotalDuration);
        tvCurrentPosition=findViewById(R.id.tvCurrentPosition);

        btnPlay=findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMySong();
            }
        });
        songProgress.setOnSeekBarChangeListener(this);
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            int totalDuration=mediaPlayer.getDuration();
            tvTotalDuration.setText(milliToTimer(totalDuration));
            int currentPosition=mediaPlayer.getCurrentPosition();
            tvCurrentPosition.setText(milliToTimer(currentPosition));
            int progress=(currentPosition*100)/totalDuration;
            songProgress.setProgress(progress);
            handler.postDelayed(this, 1000);
        }
    };

    String milliToTimer(int milli){
        int hour=0, minute=0, second=0;
        hour=milli/(60*60*1000);
        minute=(milli%(60*60*1000))/(60*1000);
        second=((milli%(60*60*1000))%(60*1000))/1000;
        return hour+":"+minute+":"+second;
    }

    private void playMySong() {
        if(mediaPlayer==null) {
            //First time only
            mediaPlayer = MediaPlayer.create(this, R.raw.song);
            mediaPlayer.start();
            btnPlay.setImageResource(android.R.drawable.ic_media_pause);
            handler.postDelayed(runnable, 1000);
        }else{
            if(mediaPlayer.isPlaying()){
                //Pause
                mediaPlayer.pause();
                btnPlay.setImageResource(android.R.drawable.ic_media_play);
            }else{
                //play
                mediaPlayer.start();
                btnPlay.setImageResource(android.R.drawable.ic_media_pause);
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        int total=mediaPlayer.getDuration();
        int current=(progress*total)/100;
        mediaPlayer.seekTo(current);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
