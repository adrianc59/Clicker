/*package com.example.clicker;

import android.os.AsyncTask;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Random;

public class ParseTask extends AsyncTask<Void, Void, String> {
    private WeakReference<TextView> mTextViewResult;

    ParseTask(TextView tv) {
        mTextViewResult = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {
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

                                String result = username + "," + String.valueOf(score) + "," + avatar;
                                return result;
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

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
*/