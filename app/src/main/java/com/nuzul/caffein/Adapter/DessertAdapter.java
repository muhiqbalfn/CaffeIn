package com.nuzul.caffein.Adapter;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nuzul.caffein.Model.Dessert;
import com.nuzul.caffein.R;
import com.nuzul.caffein.Rest.ApiClient;
import com.nuzul.caffein.dbSQLite.DataHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DessertAdapter extends RecyclerView.Adapter<DessertAdapter.DessertViewHolder> {

    int qty=0;

    List<Dessert> listDessert;
    public DessertAdapter(List<Dessert> listDessert) {
        this.listDessert = listDessert;
    }

    @NonNull
    @Override
    public DessertAdapter.DessertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dessert, parent,false);
        DessertAdapter.DessertViewHolder mHolder = new DessertAdapter.DessertViewHolder(view);
        return mHolder;
    }

    public class DessertViewHolder extends RecyclerView.ViewHolder {
        ImageView ivDessert, ivMin, ivPlus;
        TextView tvNameDessert, tvPriceDessert, tvQty;
        Button btnPesanDessert;
        public DessertViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDessert = itemView.findViewById(R.id.ivDessert);
            tvNameDessert = itemView.findViewById(R.id.tvNameDessert);
            tvPriceDessert = itemView.findViewById(R.id.tvPriceDessert);
            ivMin       = itemView.findViewById(R.id.ivMin);
            ivPlus      = itemView.findViewById(R.id.ivPlus);
            tvQty       = itemView.findViewById(R.id.tvQty );
            btnPesanDessert   = itemView.findViewById(R.id.btnPesanDessert);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final DessertViewHolder holder, int position) {
        final Dessert list = listDessert.get(position);

        holder.tvNameDessert.setText(list.getName_dessert());
        holder.tvPriceDessert.setText("Rp "+list.getPrice_dessert());
        if (list.getPhoto_id().length()>0){
            Picasso.with(holder.itemView.getContext())
                    .load(ApiClient.BASE_URL+"assets/cafe/"+list.getPhoto_id())
                    .placeholder(R.drawable.noimage)
                    .error(R.drawable.noimage)
                    .into(holder.ivDessert);
        }else{
            //jika photo_id kosong
            Picasso.with(holder.itemView.getContext())
                    .load(R.drawable.noimage)
                    .into(holder.ivDessert);
        }

        holder.ivMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty==0){
                    holder.ivMin.setEnabled(false);
                }else {
                    qty = qty-1;
                    holder.tvQty.setText(""+qty);
                }
            }
        });
        holder.ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty==5){
                    holder.ivPlus.setEnabled(false);
                }else {
                    qty += 1;
                    holder.tvQty.setText(""+qty);
                }
            }
        });

        holder.btnPesanDessert.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                String name  = list.getName_dessert();
                String price = list.getPrice_dessert();
                String qty   = holder.tvQty.getText().toString();

                DataHelper dHelper = new DataHelper(v.getContext());
                SQLiteDatabase db  = dHelper.getWritableDatabase();
                db.execSQL("INSERT INTO tb_pesanan(name, price, qty) VALUES('"+name+"','"+price+"','"+qty+"')");
                Toast.makeText(v.getContext(), qty+" "+name+" disimpan di keranjang !", Toast.LENGTH_SHORT).show();
                holder.btnPesanDessert.setBackgroundColor(R.color.colorSemuWhite);
                holder.btnPesanDessert.setEnabled(false);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listDessert.size();
    }

}