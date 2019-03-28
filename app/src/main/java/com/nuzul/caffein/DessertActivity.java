package com.nuzul.caffein;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nuzul.caffein.Adapter.DessertAdapter;
import com.nuzul.caffein.Model.Dessert;
import com.nuzul.caffein.Model.ResultDessert;
import com.nuzul.caffein.Rest.ApiClient;
import com.nuzul.caffein.Rest.ApiInterface;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class DessertActivity extends AppCompatActivity {
    RecyclerView rvDessert;
    //--------------------------
    ApiInterface mApiInterface;
    //--------------------------
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert);

        rvDessert = findViewById(R.id.rvDessert);
        //---------------------------------------
        mLayoutManager = new LinearLayoutManager(this);
        rvDessert.setLayoutManager(mLayoutManager);
        //---------------------------------------
        getProduk();
    }

    public void getProduk(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<ResultDessert> mDessertCall = mApiInterface.getDessert();
        mDessertCall.enqueue(new Callback<ResultDessert>() {
            @Override
            public void onResponse(retrofit2.Call<ResultDessert> call, Response<ResultDessert> response) {
                Log.d("Get Dessert !",response.body().getStatus());
                List<Dessert> listDessert = response.body().getResult();
                mAdapter = new DessertAdapter(listDessert);
                rvDessert.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(retrofit2.Call<ResultDessert> call, Throwable t) {
                Log.d("Get Dessert !",t.getMessage());
            }
        });
    }
}