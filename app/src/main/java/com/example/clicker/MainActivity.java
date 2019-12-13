package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);

        OkHttpClient client = new OkHttpClient();

        String url = "https://tf7mh3yt6f.execute-api.eu-west-1.amazonaws.com/live/score";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful())
                {
                    final String myResponse = response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewResult.setText(myResponse);
                        }
                    });
                }
            }
        });

        ArrayList<userScore> userScores = new ArrayList<>();
        userScores.add(new userScore("1", R.drawable.ic_android, "180940", "Nick"));
        userScores.add(new userScore("2", R.drawable.ic_android, "130040", "Nick 2"));
        userScores.add(new userScore("3", R.drawable.ic_android, "110937", "Nick 3"));
        userScores.add(new userScore("4", R.drawable.ic_android, "0", "Line 2"));
        userScores.add(new userScore("5", R.drawable.ic_android, "0", "Line 2"));
        userScores.add(new userScore("6", R.drawable.ic_android, "0", "Line 2"));
        userScores.add(new userScore("7", R.drawable.ic_android, "0", "Line 2"));
        userScores.add(new userScore("8", R.drawable.ic_android, "0", "Line 2"));
        userScores.add(new userScore("9", R.drawable.ic_android, "0", "Line 2"));
        userScores.add(new userScore("10", R.drawable.ic_android, "0", "Line 2"));
        userScores.add(new userScore("11", R.drawable.ic_android, "0", "Line 2"));




        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new leaderboardAdapter(userScores);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);



    }


}
