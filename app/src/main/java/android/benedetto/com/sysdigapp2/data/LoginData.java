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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
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
        VolleyLog.DEBUG = true;
        String url =
                "https://app-staging2.sysdigcloud.com/api/login";

        Log.d("doLogin", "start: user: " + user + " pwd: " + password);


        // singleton only use one instance

        // we try to send the login data here
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("username", user);
        params.put("password", password);

        Log.d("doLogin", "params");
        Log.d("doLogin", "params" + params.toString());


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("onResponse", "response: " + response.toString());


                // CookieStore is just an interface, you can implement it and do things like
// save the cookies to disk or what ever.
                CookieManager manager = new CookieManager();
                CookieHandler.setDefault( manager  );

                Log.d("onResponse", "cookiemanager: " + manager.toString());



//                // only when the HTTP call is done, we call the callback method
//                // HERE YOU SHOULD PASS THE SESSION DATA OBTAINED FROM THE LOGIN CALL
//                // HOW DO YOU DO THAT?
                if (null != callBack) callBack.loginFinished(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("onErrorResponse", "Error: " + error.toString());
                Log.d("onErrorResponse","error: " + error);
                Log.d("onErrorResponse", error.networkResponse.allHeaders.toString());
            }
        }) {
            // why isn't this working? Lord, help me!
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("username", user);
//                params.put("password", password);
//                Log.d("getParams", "user: " + user + " pwd: " + password);
//                return params;
//            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    headers.put("X-Sysdig-Product", "SDC");
                    Log.d("getHeaders", "headers: " + headers);
                    return headers;
            }
        };

        if (req == null) {
            throw new RuntimeException("EXCEPTION!!");
        }

        Log.d("QUIQUIQUI", "jsonArrayRequest: " + req.toString());

        AppController instance = AppController.getInstance();

        // questa instance e' null - come mai??? <<<<<<<< RICOMINCIA DA QUI!
        // perche' la variabile mInstance non era stata valorizzata nell'AppController
        if (instance == null) {
            throw new RuntimeException("appcontroller instance is null!!");
        }

        Log.d("QUIQUIQUI", "instance: " + instance.toString());
        instance.addToRequestQueue(req);
    }
}


