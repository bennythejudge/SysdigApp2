package android.benedetto.com.sysdigapp2.data;

import android.app.DownloadManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Login {
    String user;
    String password;
    String token;

    public void getToken() {
        String url =
                "https://";

        // singleton only use one instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(DownloadManager.Request.Method.GET,
                url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject quoteObj = response.getJSONObject(i);
                        Quote quote = new Quote();
                        quote.setQuote(quoteObj.getString("quote"));
                        quote.setAuthor(quoteObj.getString("name"));

                        Log.d("getQuote", quoteObj.getString("name"));

                        quoteArrayList.add(quote);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getmInstance().addToRequestQueue(jsonArrayRequest);
    }
}
