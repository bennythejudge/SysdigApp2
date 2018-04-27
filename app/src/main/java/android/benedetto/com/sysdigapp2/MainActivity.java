package android.benedetto.com.sysdigapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn = findViewById(R.id.submit_login);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView user = findViewById(R.id.user);
                TextView password = findViewById(R.id.password);
                Toast.makeText(
                        MainActivity.this,
                        "Button pressed\nuser: " + user.getText() +
                                "\npwd: " + password.getText(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}