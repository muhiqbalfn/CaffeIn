package com.nuzul.caffein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nuzul.caffein.dbSQLite.SessionManagement;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    SessionManagement sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = new SessionManagement(this);
        if (sm.isLoggedIn()) {
            goToActivity();
        }

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });
    }

    private void goToActivity () {
        Intent mIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(mIntent);
    }

}