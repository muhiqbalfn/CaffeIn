package com.nuzul.caffein;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nuzul.caffein.Adapter.CoffeeAdapter;
import com.nuzul.caffein.Model.Coffee;
import com.nuzul.caffein.Model.ResultCoffee;
import com.nuzul.caffein.Rest.ApiClient;
import com.nuzul.caffein.Rest.ApiInterface;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class CoffeeActivity extends AppCompatActivity {
    RecyclerView rvCoffee;
    //--------------------------
    ApiInterface mApiInterface;
    //--------------------------
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        rvCoffee = findViewById(R.id.rvCoffee);
        //---------------------------------------
        mLayoutManager = new GridLayoutManager(this, 2);
        rvCoffee.setLayoutManager(mLayoutManager);
        //---------------------------------------
        getProduk();
    }

    public void getProduk(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<ResultCoffee> mCoffeeCall = mApiInterface.getCoffee();
        mCoffeeCall.enqueue(new Callback<ResultCoffee>() {
            @Override
            public void onResponse(retrofit2.Call<ResultCoffee> call, Response<ResultCoffee> response) {
                Log.d("Get Coffee !",response.body().getStatus());
                List<Coffee> listCoffee = response.body().getResult();
                mAdapter = new CoffeeAdapter(listCoffee);
                rvCoffee.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(retrofit2.Call<ResultCoffee> call, Throwable t) {
                Log.d("Get Coffee !",t.getMessage());
            }
        });
    }
}