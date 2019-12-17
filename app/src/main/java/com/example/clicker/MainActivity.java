package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RequestQueue mQueue;
    private Button leaderBackBtn;

    com.github.clans.fab.FloatingActionButton shopBtn;
    com.github.clans.fab.FloatingActionButton leaderBtn;
    com.github.clans.fab.FloatingActionButton gameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        final String loggedUsername = intent.getStringExtra("username");
        final int totalCount = intent.getIntExtra("totalCount", 0);

        Picasso.with(this).load("Picasso");

        userScore logged = new userScore(loggedUsername,totalCount,"https://cdn4.iconfinder.com/data/icons/sports-fitness-line-color-vol-5/52/weight__dumbbell__fitness__gym__lifter__avatar__body-512.png");

        mQueue = Volley.newRequestQueue(this);

        //jsonParse();

        JSONArray jsonArray = jsonParse2();

        ArrayList<userScore> userScores = new ArrayList<>(10);

        userScores.add(logged);

        try {
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject user = jsonArray.getJSONObject(i);

                String username = user.getString("username");
                int score = user.getInt("score");
                String avatar = user.getString("avatar");

                userScore a = new userScore(username,score,avatar);
                userScores.add(a);

                //mTextViewResult.append(username + ", " + String.valueOf(score) + ", " + avatar + "\n\n");*/
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Collections.sort(userScores, new scoreComparator());

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new leaderboardAdapter(userScores);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        shopBtn = findViewById(R.id.floatingActionItem2);
        gameBtn = findViewById(R.id.floatingActionItem3);

        final Session session = new Session(getApplicationContext());

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                intent.putExtra("currCount", session.getCurrCount());
                startActivity(intent);
            }
        });

        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TapActivity.class);
                startActivity(intent);
            }
        });
    }

    private void jsonParse() {

        String url = "https://api.myjson.com/bins/ydd0o";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("userScores");

                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject user = jsonArray.getJSONObject(i);

                                String username = user.getString("username");
                                int score = user.getInt("score");
                                String avatar = user.getString("avatar");

                                mTextViewResult.append(username + ", " + String.valueOf(score) + ", " + avatar + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private JSONArray jsonParse2() {

        try {
            String myUrl = "https://api.myjson.com/bins/nmgs0";
            //String to place our result in
            String result;
            //Instantiate new instance of our class
            HttpGetRequest getRequest = new HttpGetRequest();
            //Perform the doInBackground method, passing in our url
            result = getRequest.execute(myUrl).get();

            int start = result.indexOf("[");

            String finalString = result.substring(start, result.length());

            JSONArray jsonArr = new JSONArray(finalString);

            return jsonArr;
        }catch (Exception e){
            System.out.println("Error = " + e);
        }

        return null;
        //mQueue.add(request);
    }

}
