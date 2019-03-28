package com.nuzul.caffein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nuzul.caffein.Model.User;
import com.nuzul.caffein.dbSQLite.DataHelper;

public class SignUpActivity extends AppCompatActivity {

    EditText etNama, etEmail, etUser, etPass, etPass2;
    Button btnSignup;
    //-------------------------------------------------
    DataHelper dHelp = new DataHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        etPass2 = findViewById(R.id.etPass2);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama, email, user, pass, pass2;
                nama = etNama.getText().toString();
                email = etEmail.getText().toString();
                user = etUser.getText().toString();
                pass = etPass.getText().toString();
                pass2 = etPass2.getText().toString();
                if (!pass.equals(pass2))
                {
                    Toast.makeText(SignUpActivity.this, "Password don't match !", Toast.LENGTH_LONG).show();
                }
                else
                {
                    User u = new User();
                    u.setNama(nama);
                    u.setEmail(email);
                    u.setUser(user);
                    u.setPass(pass);

                    dHelp.insertUser(u);
                    Toast.makeText(SignUpActivity.this, "Pendaftaran berhasil !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                }
            }
        });
    }
}