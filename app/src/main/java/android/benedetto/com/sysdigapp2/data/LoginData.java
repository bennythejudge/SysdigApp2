package android.benedetto.com.sysdigapp2.data;

import android.app.DownloadManager;
import android.benedetto.com.sysdigapp2.MainActivity;
import android.benedetto.com.sysdigapp2.controller.AppController;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginData {
    String mUser;
    String mPassword;

    public void LoginData() {
    }

    public void doLogin(
            final String user,
            final String password,
            final LoginAsyncResponse callBack) throws RuntimeException {
        String url =
                "https://app.sysdigcloud.com/api/login";

        Log.d("doLogin", "start: user: " + user + " pwd: " + password);


        // singleton only use one instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject quoteObj = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        quoteObj = response.getJSONObject(i);
//                        Quote quote = new Quote();
//                        quote.setQuote(quoteObj.getString("quote"));
//                        quote.setAuthor(quoteObj.getString("name"));
//
//                        Log.d("getQuote", quoteObj.getString("name"));
//
//                        quoteArrayList.add(quote);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                // only when the HTTP call is done, we call the callback method
                // HERE YOU SHOULD PASS THE SESSION DATA OBTAINED FROM THE LOGIN CALL
                // HOW DO YOU DO THAT?
                if (null != callBack) callBack.loginFinished(quoteObj.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onErrorResponse","error: " + error);
//                throw new RuntimeException("OnError response error: " + error);
                // handle errors
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    headers.put("X-Sysdig-Product", "SDC");
                    Log.d("getHeaders", "headers: " + headers);
                    return headers;
            }
            @Override
            protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", user);
                    params.put("password", password);
                    Log.d("getParams", "user: " + user + " pwd: " + password);
                    return params;
            }
        };

        if (jsonArrayRequest == null) {
            throw new RuntimeException("EXCEPTION!!");
        }

        Log.d("QUIQUIQUI", "jsonArrayRequest: " + jsonArrayRequest.toString());

        AppController instance = AppController.getInstance();

        // questa instance e' null - come mai??? <<<<<<<< RICOMINCIA DA QUI!
        // perche' la variabile mInstance non era stata valorizzata nell'AppController
        if (instance == null) {
            throw new RuntimeException("appcontroller instance is null!!");
        }

        Log.d("QUIQUIQUI", "instance: " + instance.toString());
        instance.addToRequestQueue(jsonArrayRequest);
    }
}


