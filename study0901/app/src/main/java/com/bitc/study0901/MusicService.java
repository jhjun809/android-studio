package com.bitc.study0901;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class MusicService extends Service {
    MediaPlayer mediaPlayer;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onCreate(){
        Toast.makeText(getApplicationContext(),"onCreate()" ,Toast.LENGTH_SHORT);
        Log.i("서비스 테스트","onCreate()");
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(),"onStartCommand()" ,Toast.LENGTH_SHORT);
        Log.i("서비스 테스트","onStartCommand()");
        mediaPlayer = MediaPlayer.create(this, R.raw.song1);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy(){
        Toast.makeText(getApplicationContext(),"onDestroy()" ,Toast.LENGTH_SHORT);
        Log.i("서비스 테스트","onDestroy()");
        mediaPlayer.stop();
        super.onDestroy();
    }
}