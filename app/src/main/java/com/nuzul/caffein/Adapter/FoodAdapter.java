package com.nuzul.caffein.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nuzul.caffein.Model.Food;
import com.nuzul.caffein.R;
import com.nuzul.caffein.Rest.ApiClient;
import com.nuzul.caffein.dbSQLite.DataHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    int qty=0;

    List<Food> listFood;
    public FoodAdapter(List<Food> listFood) {
        this.listFood = listFood;
    }

    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_food, parent,false);
        FoodViewHolder mHolder = new FoodViewHolder(view);
        return mHolder;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto, ivMin, ivPlus;
        TextView tvNameFood, tvPriceFood, tvQty;
        Button btwPernod;
        public FoodViewHolder(View itemView) {
            super(itemView);
            ivPhoto     = itemView.findViewById(R.id.ivFood);
            tvNameFood  = itemView.findViewById(R.id.tvNameFood);
            tvPriceFood = itemView.findViewById(R.id.tvPriceFood);
            ivMin       = itemView.findViewById(R.id.ivMin);
            ivPlus      = itemView.findViewById(R.id.ivPlus);
            tvQty       = itemView.findViewById(R.id.tvQty );
            btwPernod   = itemView.findViewById(R.id.btnPesanfood);
        }
    }

    @Override
    public void onBindViewHolder(final FoodAdapter.FoodViewHolder holder, final int position) {
        final Food list = listFood.get(position);

        holder.tvNameFood.setText(list.getName_food());
        holder.tvPriceFood.setText("Rp "+list.getPrice_food());
        if (list.getPhoto_id().length()>0){
            Picasso.with(holder.itemView.getContext())
                    .load(ApiClient.BASE_URL+"assets/cafe/"+list.getPhoto_id())
                    .placeholder(R.drawable.noimage)
                    .error(R.drawable.noimage)
                    .into(holder.ivPhoto);
       }else{
            //jika photo_id kosong
            Picasso.with(holder.itemView.getContext())
                    .load(R.drawable.noimage)
                    .into(holder.ivPhoto);
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

        holder.btwPernod.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                String name  = list.getName_food();
                String price = list.getPrice_food();
                String qty   = holder.tvQty.getText().toString();

                DataHelper dHelper = new DataHelper(v.getContext());
                SQLiteDatabase db  = dHelper.getWritableDatabase();
                db.execSQL("INSERT INTO tb_pesanan(name, price, qty) VALUES('"+name+"','"+price+"','"+qty+"')");
                Toast.makeText(v.getContext(), qty+" "+name+" disimpan di keranjang !", Toast.LENGTH_SHORT).show();
                holder.btwPernod.setBackgroundColor(R.color.colorSemuWhite);
                holder.btwPernod.setEnabled(false);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }
}