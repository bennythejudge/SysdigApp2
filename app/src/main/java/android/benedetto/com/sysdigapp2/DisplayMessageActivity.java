package android.benedetto.com.sysdigapp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class DisplayMessageActivity extends AppCompatActivity{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_me);

        // get the intent that created this activity
        Intent intent = getIntent();
        String sessionData = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Log.d("DisplayMessageActivity", "sessionData: " + sessionData.toString());

    }
}
