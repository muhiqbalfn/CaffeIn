package com.nuzul.caffein.Rest;

import com.nuzul.caffein.Model.ResultCoffee;
import com.nuzul.caffein.Model.ResultDessert;
import com.nuzul.caffein.Model.ResultDrink;
import com.nuzul.caffein.Model.ResultFood;
import com.nuzul.caffein.Model.ResultPesanan;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @GET("food/user")
    Call<ResultFood> getFood();

    @GET("drink/user")
    Call<ResultDrink> getDrink();

    @GET("coffee/user")
    Call<ResultCoffee> getCoffee();

    @GET("dessert/user")
    Call<ResultDessert> getDessert();

    @Multipart
    @POST("pesanan/user")
    Call<ResultPesanan> postPesanan(@Part MultipartBody.Part file,
                                    @Part("kode_meja") RequestBody kode_meja,
                                    @Part("name") RequestBody name,
                                    @Part("qty") RequestBody qty,
                                    @Part("price") RequestBody price,
                                    @Part("action") RequestBody action);

    @Multipart
    @POST("pesanan/user")
    Call<ResultPesanan> deletePesanan(@Part("name") RequestBody name,
                                      @Part("action") RequestBody action);
}