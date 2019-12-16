package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RequestQueue mQueue;
    private Button leaderBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);

        mQueue = Volley.newRequestQueue(this);

        //jsonParse();

        JSONArray jsonArray = jsonParse2();
        ArrayList<userScore> userScores = new ArrayList<>(10);

        try {
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject user = jsonArray.getJSONObject(i);

                String username = user.getString("username");
                int score = user.getInt("score");
                String avatar = user.getString("avatar");

                userScore a = new userScore(i,username,score,R.drawable.ic_android);
                System.out.println(a.getPosition());
                System.out.println(a.getScore());
                System.out.println(a.getUsername());
                System.out.println(a.getImageResource());
                userScores.add(a);

                //mTextViewResult.append(username + ", " + String.valueOf(score) + ", " + avatar + "\n\n");*/
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new leaderboardAdapter(userScores);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        leaderBackBtn = findViewById(R.id.leaderBackBtn);

        leaderBackBtn.setOnClickListener(new View.OnClickListener() {
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
            String myUrl = "https://api.myjson.com/bins/ydd0o";
            //String to place our result in
            String result;
            //Instantiate new instance of our class
            HttpGetRequest getRequest = new HttpGetRequest();
            //Perform the doInBackground method, passing in our url
            result = getRequest.execute(myUrl).get();

            int start = result.indexOf("[");

            String finalString = result.substring(start, result.length()-1);

            JSONArray jsonArr = new JSONArray(finalString);

            return jsonArr;
        }catch (Exception e){

        }

        return null;
        //mQueue.add(request);
    }

}
