package com.nuzul.caffein;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nuzul.caffein.Adapter.PesananAdapter;
import com.nuzul.caffein.Model.Pesanan;
import com.nuzul.caffein.Model.ResultPesanan;
import com.nuzul.caffein.Rest.ApiClient;
import com.nuzul.caffein.Rest.ApiInterface;
import com.nuzul.caffein.dbSQLite.DataHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    Button btnQR;
    TextView tvQR;
    //-----------------------------------------------------
    private DataHelper dHelper;
    private List<Pesanan> listPesanan = new ArrayList<Pesanan>();
    private RecyclerView rv;
    private PesananAdapter adapter;
    RecyclerView.LayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        dHelper = new DataHelper(this);
        rv = findViewById(R.id.rvCart);
        btnQR = findViewById(R.id.btnQR);
        tvQR = findViewById(R.id.tvQR);
        getPesanan();
        //--------------------------------------------------------------------------
        final Activity activity = this;
        btnQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                /*integrator.setBarcodeImageEnabled(false);*/
                integrator.initiateScan();
            }
        });
    }

    public void getPesanan(){
        listPesanan.addAll(dHelper.allPesanan());
        adapter = new PesananAdapter(this, listPesanan);
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        //underline
        /*DividerItemDecoration itemDec = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDec.setDrawable(ContextCompat.getDrawable(this, R.drawable.line));
        rv.addItemDecoration(itemDec);*/
    }

    //QR Code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning !", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                tvQR.setText(result.getContents());
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}