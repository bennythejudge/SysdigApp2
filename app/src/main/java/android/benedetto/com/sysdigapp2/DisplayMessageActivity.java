package android.benedetto.com.sysdigapp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class DisplayMessageActivity extends AppCompatActivity{

    private JSONObject obj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_me);

        // get the intent that created this activity
        Intent intent = getIntent();
        String sessionData = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Log.d("DisplayMessageActivity", "sessionData: " + sessionData.toString());

        try {
            obj = new JSONObject(sessionData);
            Log.d("DisplayMessageActivity", "id: " + obj.get("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView id = findViewById(R.id.userid);
        try {
            id.setText(obj.getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
