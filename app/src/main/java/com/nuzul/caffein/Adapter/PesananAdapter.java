package com.nuzul.caffein.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nuzul.caffein.CartActivity;
import com.nuzul.caffein.Model.Pesanan;
import com.nuzul.caffein.Model.ResultPesanan;
import com.nuzul.caffein.R;
import com.nuzul.caffein.Rest.ApiClient;
import com.nuzul.caffein.Rest.ApiInterface;
import com.nuzul.caffein.dbSQLite.DataHelper;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.MyViewHolder> {

    final ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    private Context context;
    private List<Pesanan> listPesanan;

    public PesananAdapter(Context context, List<Pesanan> listPesanan) {
        this.context = context;
        this.listPesanan = listPesanan;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cart, parent, false);
        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNameCart, tvPriceCart, tvQtyCart;
        ImageView ivBatalCart, ivSendCart, ivNotSend;
        public MyViewHolder(View view) {
            super(view);
            tvNameCart = view.findViewById(R.id.tvNameCart);
            tvPriceCart = view.findViewById(R.id.tvPriceCart);
            tvQtyCart = view.findViewById(R.id.tvQtyCart);
            ivBatalCart = view.findViewById(R.id.ivBatalCart);
            ivSendCart = view.findViewById(R.id.ivSendCart);
            ivNotSend = view.findViewById(R.id.ivNotSend);
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Pesanan list = listPesanan.get(position);

        holder.tvNameCart.setText(list.getName());
        holder.tvQtyCart.setText("("+list.getQty()+"x)");

        int price = Integer.parseInt(list.getPrice());
        int qty   = Integer.parseInt(list.getQty());
        int count = price*qty;

        holder.tvPriceCart.setText("Rp "+count);
        final String kode_meja = "meja 1";

        holder.ivBatalCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHelper dHelper = new DataHelper(context);
                String name = list.getName();
                SQLiteDatabase db = dHelper.getWritableDatabase();
                db.execSQL("DELETE FROM tb_pesanan WHERE name='"+name+"'");
                Toast.makeText(context, name+" berhasil dihapus !", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, CartActivity.class));
            }
        });

        holder.ivSendCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                MultipartBody.Part body = null;

                RequestBody reqKodeMeja = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (kode_meja.isEmpty()==true)?"": kode_meja);
                RequestBody reqName = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (holder.tvNameCart.getText().toString().isEmpty())?"": holder.tvNameCart.getText().toString());
                RequestBody reqQty = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (holder.tvQtyCart.getText().toString().isEmpty())?"": holder.tvQtyCart.getText().toString());
                RequestBody reqPrice = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (holder.tvPriceCart.getText().toString().isEmpty())?"": holder.tvPriceCart.getText().toString());
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"), "post");

                Call<ResultPesanan> mPesananCall = mApiInterface.postPesanan(body, reqKodeMeja, reqName, reqQty, reqPrice, reqAction );
                mPesananCall.enqueue(new Callback<ResultPesanan>() {
                    @Override
                    public void onResponse(Call<ResultPesanan> call, Response<ResultPesanan> response) {
                        Toast.makeText(context, "Pesanan berhasil dikirim !", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<ResultPesanan> call, Throwable t) {
                        Toast.makeText(context, "Pesanan gagal dikirim !", Toast.LENGTH_SHORT).show();
                    }
                });
                holder.ivSendCart.setImageDrawable(null);
                holder.ivNotSend.setImageResource(R.drawable.not_send);
            }
        });

        holder.ivNotSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody reqName = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (holder.tvNameCart.getText().toString().isEmpty()==true)?"": holder.tvNameCart.getText().toString());
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),"delete");
                Call<ResultPesanan> callDelete = mApiInterface.deletePesanan(reqName, reqAction);
                callDelete.enqueue(new Callback<ResultPesanan>() {
                    @Override
                    public void onResponse(Call<ResultPesanan> call, Response<ResultPesanan> response) {
                        Toast.makeText(context, "Pesanan dibatalkan !", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<ResultPesanan> call, Throwable t) {
                        Toast.makeText(context, "Gagal batalkan pesan !", Toast.LENGTH_SHORT).show();
                    }
                });
                holder.ivNotSend.setImageDrawable(null);
                holder.ivSendCart.setImageResource(R.drawable.send);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPesanan.size();
    }
}