package com.nuzul.caffein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nuzul.caffein.dbSQLite.DataHelper;
import com.nuzul.caffein.dbSQLite.SessionManagement;

public class SignInActivity extends AppCompatActivity {
    EditText etUser, etPass;
    Button btnLogin;
    TextView tvSignup;
    //-------------------------------------------------
    DataHelper dHelp = new DataHelper(this);
    SessionManagement sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sm = new SessionManagement(this);

        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignup = findViewById(R.id.tvSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, pass;
                user = etUser.getText().toString();
                pass = etPass.getText().toString();

                if (user.matches("") || user.trim().isEmpty() || pass.matches("") || pass.trim().isEmpty()){
                    Toast.makeText(SignInActivity.this, "Username dan Password tidak boleh kosong atau berisi spasi !", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    String password = dHelp.searchPass(user);
                    if (pass.equals(password))
                    {
                        sm.createLoginSession(user, pass);
                        goToActivity();
                    }else {
                        Toast.makeText(SignInActivity.this, "Username atau password tidak terdaftar !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
    }

    private void goToActivity () {
        Intent mIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(mIntent);
    }
}