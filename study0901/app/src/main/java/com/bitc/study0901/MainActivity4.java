package com.bitc.study0901;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class MainActivity4 extends AppCompatActivity {
    Button btnCall;
    EditText edtCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CALL_LOG}, MODE_PRIVATE);
        btnCall = findViewById(R.id.btnCall);
        edtCall = findViewById(R.id.edtCall);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtCall.setText(getCallHistory());
            }
        });
    }

    public String getCallHistory() {
        String[] callSet = new String[]{CallLog.Calls.DATE, CallLog.Calls.TYPE, CallLog.Calls.NUMBER, CallLog.Calls.DURATION};//

        Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, callSet, null, null, null);

        if (cursor == null) {
            return "통화기록 없음";
        }

        StringBuffer callBuff = new StringBuffer();
        callBuff.append("\n날짜 : 구분 : 전화번호 : 통화시간\n\n");
        cursor.moveToFirst();

        do {
            long callDate = cursor.getLong(0);
            SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
            String date_str = datePattern.format(new Date(callDate));
            callBuff.append(date_str + ":");

            if (cursor.getInt(1) == CallLog.Calls.INCOMING_TYPE) {
                callBuff.append("착신 : ");
            } else {
                callBuff.append("발신 : ");
            }


            String tel = cursor.getString(2);
            tel = tel.substring(0, 3)+"-"+ tel.substring(3, 7) +"-" +tel.substring(7, 11);
            callBuff.append(tel + " : ");

            callBuff.append(cursor.getString(3) + "초\n");
        } while (cursor.moveToNext());

        cursor.close();
        return callBuff.toString();
    }
}