package android.benedetto.com.sysdigapp2.controller;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;


public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;
    private RequestQueue mRequestQueue;
    private Context objContext;

    // devo capire meglio questa
    public static synchronized AppController getInstance() { return mInstance; }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("onCreate", "before I call for cookies");

        if (getApplicationContext().getApplicationContext() == null) {
            Log.d("onCreate", "I found that this.objContext wss null!");
            throw new RuntimeException("EXCEPTION when creating cookiemanager - this.objContext is null !!");
        }

        CookieManager cmrCookieMan = new CookieManager(new MyCookieStore(getApplicationContext().getApplicationContext()), CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cmrCookieMan);

        Log.d("onCreate", "after the damn cookie calls");


        // because this was missing you were getting a crash
        // and it makes sense:
        mInstance = this;
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
}
