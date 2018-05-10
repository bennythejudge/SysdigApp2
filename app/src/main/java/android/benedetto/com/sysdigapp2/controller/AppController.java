package android.benedetto.com.sysdigapp2.controller;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;
    private RequestQueue mRequestQueue;
//    private DefaultHttpClient mHttpClient;
    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";
    private static final String SESSION_COOKIE = "sessionid";
    private RequestQueue _requestQueue;
    private SharedPreferences _preferences;




    // devo capire meglio questa
    public static synchronized AppController getInstance() { return mInstance; }
    // a duplicate I know
    public static synchronized AppController get() { return mInstance; }


    @Override
    public void onCreate() {
        super.onCreate();

        // because this was missing you were getting a crash
        // and it makes sense:
        mInstance = this;

        // for the cookies
        _preferences = PreferenceManager.getDefaultSharedPreferences(this);
        _requestQueue = Volley.newRequestQueue(this);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {

            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    // here we overload the method providing two possible signatures
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // if tag is empty use TAG otherwise use tag
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }
    public <T> void addToRequestQueue(Request<T> req) {
        Log.d("addToRequestQueue", "inside here with req: " + req.toString());
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    // for the cookie storing problem





}
