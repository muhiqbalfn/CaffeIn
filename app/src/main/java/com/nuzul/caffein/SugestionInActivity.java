package com.nuzul.caffein;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nuzul.caffein.dbSQLite.DataHelper;

public class SugestionInActivity extends AppCompatActivity {
    EditText etNama, etEmail, etSaran;
    Button btnKirim, btnReset;
    DataHelper dHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestion_in);

        dHelper = new DataHelper(this);
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etSaran = findViewById(R.id.etSaran);
        btnKirim = findViewById(R.id.btnKirim);
        btnReset = findViewById(R.id.btnReset);

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama  = etNama.getText().toString();
                String email = etEmail.getText().toString();
                String saran = etSaran.getText().toString();
                SQLiteDatabase db = dHelper.getWritableDatabase();
                db.execSQL("INSERT INTO tb_saran(nama, email, saran) VALUES ('"+nama+"','"+email+"','"+saran+"')");
                Toast.makeText(SugestionInActivity.this, "Pesan dari "+nama+", berhasil disimpan !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SugestionInActivity.this, SugestionActivity.class));
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNama.setText("");
                etEmail.setText("");
                etSaran.setText("");
            }
        });
    }
}