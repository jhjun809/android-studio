package com.bitc.study0901;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    Button btnStart, btnStop;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        intent = new Intent(getApplicationContext(), MusicService.class);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent); //전에 재생중이는 곡 중지(중복 재생 막기)

                startService(intent);
                Toast.makeText(getApplicationContext(),"startService()" ,Toast.LENGTH_SHORT);
                Log.i("서비스 테스트", "startService()");
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
                Toast.makeText(getApplicationContext(),"stopService()" ,Toast.LENGTH_SHORT);
                Log.i("서비스 테스트", "stopService()");
            }
        });
    }
}