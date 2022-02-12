package com.bitc.study0901;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    ImageView img;
    TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        img = findViewById(R.id.imgB);
        tv1 = findViewById(R.id.edtBattery);

    }

    BroadcastReceiver broadcastReceiver;
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
            int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            tv1.setText("현재 충전량" + remain + "%");

            if (remain >= 90) {
                img.setImageResource(R.drawable.battery_100);
            } else if (remain >= 70) {
                img.setImageResource(R.drawable.battery_80);
            } else if (remain >= 50) {
                img.setImageResource(R.drawable.battery_60);
            } else if (remain >= 10) {
                img.setImageResource(R.drawable.battery_20);
            } else {
                img.setImageResource(R.drawable.battery_0);
            }

            int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            switch (plug) {
                case 0:
                    tv1.append("전원 연결 : 안됨");
                    break;
                case BatteryManager.BATTERY_PLUGGED_AC:
                    tv1.append("전원 연결 : 어댑터 연결 됨");
                    break;
                case BatteryManager.BATTERY_PLUGGED_USB:
                    tv1.append("전원 연결 : USB 연결 됨");
                    break;
            }
        }


    }

    ;

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }
}