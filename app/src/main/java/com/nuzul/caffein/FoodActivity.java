package com.nuzul.caffein;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.nuzul.caffein.Adapter.FoodAdapter;
import com.nuzul.caffein.Model.Food;
import com.nuzul.caffein.Model.ResultFood;
import com.nuzul.caffein.Rest.ApiClient;
import com.nuzul.caffein.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodActivity extends AppCompatActivity {
    RecyclerView rvFood;
    //--------------------------
    ApiInterface mApiInterface;
    //--------------------------
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        rvFood = findViewById(R.id.rvFood);
        //-------------------------------------------------
        mLayoutManager = new LinearLayoutManager(this);
        rvFood.setLayoutManager(mLayoutManager);
        //-------------------------------------------------
        getProduk();
    }

    public void getProduk(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResultFood> mFoodCall = mApiInterface.getFood();
        mFoodCall.enqueue(new Callback<ResultFood>() {
            @Override
            public void onResponse(Call<ResultFood> call, Response<ResultFood> response) {
                Log.d("Get Food",response.body().getStatus());
                List<Food> listFood = response.body().getResult();
                mAdapter = new FoodAdapter(listFood);
                rvFood.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(Call<ResultFood> call, Throwable t) {
                Log.d("Get Pembeli",t.getMessage());
            }
        });
    }

}