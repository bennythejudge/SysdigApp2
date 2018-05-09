package android.benedetto.com.sysdigapp2.controller;

import android.app.Application;
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


    // devo capire meglio questa
    public static synchronized AppController getInstance() { return mInstance; }

    @Override
    public void onCreate() {
        super.onCreate();

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
