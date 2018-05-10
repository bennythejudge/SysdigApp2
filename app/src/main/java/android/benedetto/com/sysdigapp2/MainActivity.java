package android.benedetto.com.sysdigapp2;

import android.benedetto.com.sysdigapp2.data.EventsAsyncResponse;
import android.benedetto.com.sysdigapp2.data.EventsData;
import android.benedetto.com.sysdigapp2.data.LoginAsyncResponse;
import android.benedetto.com.sysdigapp2.data.LoginData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyLog;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        VolleyLog.DEBUG = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn = findViewById(R.id.submit_login);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView user = findViewById(R.id.user);
                TextView password = findViewById(R.id.password);
                new LoginData().doLogin(
                        user.getText().toString(),
                        password.getText().toString(),
                        new LoginAsyncResponse() {
                    @Override
                    public void loginFinished(String sessionData) {
                        Log.d("loginFinished", "inside main activity with sessionData: " + sessionData);
                        Toast.makeText(MainActivity.this, "login completed",
                                Toast.LENGTH_SHORT).show();

                        // here make the second call
                        new EventsData().fetchEvents(
                                new EventsAsyncResponse() {
                                    @Override
                                    public void fetchEventsFinished(String sessionData) {
                                        Log.d("EventsAsyncResponse", "here is am with: " + sessionData.toString());
                                    }
                                }
                        );


                        Intent intent = new Intent(getApplicationContext(), DisplayMessageActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, sessionData);
                        startActivity(intent);

                    }
                });
            }
        });
    }
}
