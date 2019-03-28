package com.nuzul.caffein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.nuzul.caffein.Adapter.SugestionAdapter;
import com.nuzul.caffein.Model.Sugestion;
import com.nuzul.caffein.dbSQLite.DataHelper;

import java.util.ArrayList;
import java.util.List;

public class SugestionActivity extends AppCompatActivity {
    Button btnSaran;
    //--------------------------------------------------------------
    private DataHelper dHelper;
    private List<Sugestion> listSaran = new ArrayList<Sugestion>();
    private RecyclerView rv;
    private SugestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestion);

        dHelper  = new DataHelper(this);
        btnSaran = findViewById(R.id.btnSaran);
        rv       = findViewById(R.id.rvSugestion);
        getSaran();

        btnSaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SugestionInActivity.class));
            }
        });

    }

    public void getSaran(){
        listSaran.addAll(dHelper.allSaran());
        adapter = new SugestionAdapter(this, listSaran);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.setAdapter(adapter);
    }
}