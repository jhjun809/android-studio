package com.bitc.myapp1001;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AuthenticationRequiredException;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    TextView tvMovieList;
    EditText edtUrl;
    Button btnRequest;

    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtUrl = findViewById(R.id.edtUrl);
        tvMovieList = findViewById(R.id.tvMovieList);
        btnRequest = findViewById(R.id.btnRequest);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void prints(String data) {

        tvMovieList.append(data + "\n");
    }

    public void processResponse(String response) {
        Gson gson = new Gson();
        MovieList movieList = gson.fromJson(response, MovieList.class);
        prints("영화 정보의 수: " + movieList.boxOfficeResult.dailyBoxOfficeList.size());
    }

    public void makeRequest() {
        String url = edtUrl.getText().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        prints("응답 -> " + response);
                        processResponse(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        prints("에러 -> " + error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
        prints("요청 보냄");
    }
}