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
        jsonParse2();

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

    private void jsonParse2() {

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

            try {
                JSONArray jsonArr = new JSONArray(finalString);

                for (int i = 0; i < jsonArr.length(); i++)
                {
                    JSONObject jsonObj = jsonArr.getJSONObject(i);


                    String username = jsonObj.getString("username");
                    System.out.println(username);
                    int score = jsonObj.getInt("score");
                    String avatar = jsonObj.getString("avatar");

                    mTextViewResult.append(username + ", " + String.valueOf(score) + ", " + avatar + "\n\n");

                    //System.out.println(jsonObj);
                }



            } catch (Throwable t) {
                Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
            }
        }catch (Exception e){

        }


        //mQueue.add(request);
    }

}
