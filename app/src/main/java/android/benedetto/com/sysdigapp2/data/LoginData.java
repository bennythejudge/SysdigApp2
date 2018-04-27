package android.benedetto.com.sysdigapp2.data;

import android.app.DownloadManager;
import android.benedetto.com.sysdigapp2.MainActivity;
import android.benedetto.com.sysdigapp2.controller.AppController;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

    public void LoginData(String user, String password) {

    }

    public void doLogin() {
        String url =
                "https://app.sysdigcloug.com/api/login";

        // singleton only use one instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // here we have the response to GET
//                Toast.makeText(,"",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handle errors
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("X-Sysdig-Product", "SDC");
                return headers;
            }
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("User", user);
                params.put("Pass", password);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}
