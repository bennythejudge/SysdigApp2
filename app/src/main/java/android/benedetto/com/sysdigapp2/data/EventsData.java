package android.benedetto.com.sysdigapp2.data;

import android.benedetto.com.sysdigapp2.controller.AppController;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EventsData {
    String mPassword;
    String mUser;

    public void EventsData() {
    }

    public void fetchEvents(
            final EventsAsyncResponse callBack) throws RuntimeException {
        VolleyLog.DEBUG = true;
        String url =
                "https://app-staging2.sysdigcloud.com/api/events";

        Log.d("fetchEvents", "starting");


        // singleton only use one instance

        // what about the cookie? what about the session token?

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("eventsonresponse", "response: " + response.toString());


                if (null != callBack) callBack.fetchEventsFinished(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("onErrorResponse", "Error: " + error.toString());
                Log.d("onErrorResponse","error: " + error);
                Log.d("onErrorResponse", error.networkResponse.allHeaders.toString());
            }
        }) {
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
