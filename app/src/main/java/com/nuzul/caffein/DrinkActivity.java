package com.nuzul.caffein;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nuzul.caffein.Adapter.DrinkAdapter;
import com.nuzul.caffein.Model.Drink;
import com.nuzul.caffein.Model.ResultDrink;
import com.nuzul.caffein.Rest.ApiClient;
import com.nuzul.caffein.Rest.ApiInterface;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class DrinkActivity extends AppCompatActivity {
    RecyclerView rvDrink;
    //--------------------------
    ApiInterface mApiInterface;
    //--------------------------
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        rvDrink = findViewById(R.id.rvDrink);
        //-------------------------------------------------
        mLayoutManager = new GridLayoutManager(this, 2);
        rvDrink.setLayoutManager(mLayoutManager);
        //-------------------------------------------------
        getProduk();
    }

    public void getProduk(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<ResultDrink> mDrinkCall = mApiInterface.getDrink();
        mDrinkCall.enqueue(new Callback<ResultDrink>() {
            @Override
            public void onResponse(retrofit2.Call<ResultDrink> call, Response<ResultDrink> response) {
                Log.d("Get Drink !",response.body().getStatus());
                List<Drink> listDrink = response.body().getResult();
                mAdapter = new DrinkAdapter(listDrink);
                rvDrink.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(retrofit2.Call<ResultDrink> call, Throwable t) {
                Log.d("Get Drink !",t.getMessage());
            }
        });
    }
}