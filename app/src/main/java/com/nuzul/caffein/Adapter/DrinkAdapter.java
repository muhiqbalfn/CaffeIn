package com.nuzul.caffein.Adapter;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nuzul.caffein.Model.Drink;
import com.nuzul.caffein.R;
import com.nuzul.caffein.Rest.ApiClient;
import com.nuzul.caffein.dbSQLite.DataHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {

    int qty=0;

    List<Drink> listDrink;
    public DrinkAdapter(List<Drink> listDrink) {
        this.listDrink = listDrink;
    }

    @Override
    public DrinkAdapter.DrinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_drink, parent,false);
        DrinkViewHolder mHolder = new DrinkViewHolder(view);
        return mHolder;
    }

    public class DrinkViewHolder extends RecyclerView.ViewHolder {
        ImageView ivDrink, ivMin, ivPlus;
        TextView tvNameDrink, tvPriceDrink, tvQty;
        Button btnPesanDrink;
        public DrinkViewHolder(View itemView) {
            super(itemView);
            ivDrink = itemView.findViewById(R.id.ivDrink);
            tvNameDrink  = itemView.findViewById(R.id.tvNameDrink);
            tvPriceDrink = itemView.findViewById(R.id.tvPriceDrink);
            ivMin       = itemView.findViewById(R.id.ivMin);
            ivPlus      = itemView.findViewById(R.id.ivPlus);
            tvQty       = itemView.findViewById(R.id.tvQty );
            btnPesanDrink   = itemView.findViewById(R.id.btnPesanDrink);
        }
    }

    @Override
    public void onBindViewHolder(final DrinkAdapter.DrinkViewHolder holder, final int position) {
        final Drink list = listDrink.get(position);

        holder.tvNameDrink.setText(list.getName_drink());
        holder.tvPriceDrink.setText("Rp "+list.getPrice_drink());
        if (list.getPhoto_id().length()>0){
            Picasso.with(holder.itemView.getContext())
                    .load(ApiClient.BASE_URL+"assets/cafe/"+list.getPhoto_id())
                    .placeholder(R.drawable.noimage)
                    .error(R.drawable.noimage)
                    .into(holder.ivDrink);
        }else{
            //jika photo_id kosong
            Picasso.with(holder.itemView.getContext())
                    .load(R.drawable.noimage)
                    .into(holder.ivDrink);
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

        holder.btnPesanDrink.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                String name  = list.getName_drink();
                String price = list.getPrice_drink();
                String qty   = holder.tvQty.getText().toString();

                DataHelper dHelper = new DataHelper(v.getContext());
                SQLiteDatabase db  = dHelper.getWritableDatabase();
                db.execSQL("INSERT INTO tb_pesanan(name, price, qty) VALUES('"+name+"','"+price+"','"+qty+"')");
                Toast.makeText(v.getContext(), qty+" "+name+" disimpan di keranjang !", Toast.LENGTH_SHORT).show();
                holder.btnPesanDrink.setBackgroundColor(R.color.colorSemuWhite);
                holder.btnPesanDrink.setEnabled(false);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listDrink.size();
    }
}