package com.nuzul.caffein.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nuzul.caffein.MainActivity;
import com.nuzul.caffein.Model.Sugestion;
import com.nuzul.caffein.R;
import com.nuzul.caffein.SugestionActivity;
import com.nuzul.caffein.dbSQLite.DataHelper;

import java.util.List;

public class SugestionAdapter extends RecyclerView.Adapter<SugestionAdapter.MyViewHolder> {

    private Context context;
    private List<Sugestion> listSugestion;

    public SugestionAdapter(Context context, List<Sugestion> listSugestion) {
        this.context = context;
        this.listSugestion = listSugestion;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sugestion, parent, false);
        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama, tvEmail, tvSaran;
        public MyViewHolder(View view) {
            super(view);
            tvNama = view.findViewById(R.id.tvName);
            tvEmail = view.findViewById(R.id.tvEmail);
            tvSaran = view.findViewById(R.id.tvSaran);

        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Sugestion list = listSugestion.get(position);

        holder.tvNama.setText(list.getNama());
        holder.tvEmail.setText(list.getEmail());
        holder.tvSaran.setText(list.getSaran());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Show dialog
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Action");
                alertDialog.setMessage("Hapus pesan ini ? ");

                alertDialog.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // DO SOMETHING HERE
                        DataHelper dHelper = new DataHelper(context);
                        String nama = list.getNama();
                        SQLiteDatabase db = dHelper.getWritableDatabase();
                        db.execSQL("DELETE FROM tb_saran WHERE nama='"+nama+"'");
                        Toast.makeText(context, "Saran dari "+nama+", berhasil dihapus !", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, SugestionActivity.class));
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Update !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSugestion.size();
    }
}