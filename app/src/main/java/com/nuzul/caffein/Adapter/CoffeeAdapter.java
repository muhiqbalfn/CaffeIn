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

import com.nuzul.caffein.Model.Coffee;
import com.nuzul.caffein.R;
import com.nuzul.caffein.Rest.ApiClient;
import com.nuzul.caffein.dbSQLite.DataHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder> {

    int qty=0;

    List<Coffee> listCoffee;
    public CoffeeAdapter(List<Coffee> listCoffee) {
        this.listCoffee = listCoffee;
    }

    @NonNull
    @Override
    public CoffeeAdapter.CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_coffee, parent,false);
        CoffeeAdapter.CoffeeViewHolder mHolder = new CoffeeAdapter.CoffeeViewHolder(view);
        return mHolder;
    }

    public class CoffeeViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCoffee, ivMin, ivPlus;
        TextView tvNameCoffee, tvPriceCoffee, tvQty;
        Button btnPesanCoffee;
        public CoffeeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCoffee = itemView.findViewById(R.id.ivCoffee);
            tvNameCoffee = itemView.findViewById(R.id.tvNameCoffee);
            tvPriceCoffee = itemView.findViewById(R.id.tvPriceCoffee);
            ivMin       = itemView.findViewById(R.id.ivMin);
            ivPlus      = itemView.findViewById(R.id.ivPlus);
            tvQty       = itemView.findViewById(R.id.tvQty );
            btnPesanCoffee   = itemView.findViewById(R.id.btnPesanCoffee);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final CoffeeViewHolder holder, int position) {
        final Coffee list = listCoffee.get(position);

        holder.tvNameCoffee.setText(list.getName_coffee());
        holder.tvPriceCoffee.setText("Rp "+list.getPrice_coffee());
        if (list.getPhoto_id().length()>0){
            Picasso.with(holder.itemView.getContext())
                    .load(ApiClient.BASE_URL+"assets/cafe/"+list.getPhoto_id())
                    .placeholder(R.drawable.noimage)
                    .error(R.drawable.noimage)
                    .into(holder.ivCoffee);
        }else{
            //jika photo_id kosong
            Picasso.with(holder.itemView.getContext())
                    .load(R.drawable.noimage)
                    .into(holder.ivCoffee);
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

        holder.btnPesanCoffee.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                String name  = list.getName_coffee();
                String price = list.getPrice_coffee();
                String qty   = holder.tvQty.getText().toString();

                DataHelper dHelper = new DataHelper(v.getContext());
                SQLiteDatabase db  = dHelper.getWritableDatabase();
                db.execSQL("INSERT INTO tb_pesanan(name, price, qty) VALUES('"+name+"','"+price+"','"+qty+"')");
                Toast.makeText(v.getContext(), qty+" "+name+" disimpan di keranjang !", Toast.LENGTH_SHORT).show();
                holder.btnPesanCoffee.setBackgroundColor(R.color.colorSemuWhite);
                holder.btnPesanCoffee.setEnabled(false);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listCoffee.size();
    }

}