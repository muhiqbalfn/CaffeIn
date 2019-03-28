package com.nuzul.caffein.dbSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nuzul.caffein.Model.Pesanan;
import com.nuzul.caffein.Model.Sugestion;
import com.nuzul.caffein.Model.User;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db.cafein";
    SQLiteDatabase db;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tb_saran (nama text, email text, saran text)";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        String sql1 = "INSERT INTO tb_saran (nama, email, saran) VALUES ('Firdaus', 'fnfirdaus45@gmail.com', 'Lumayan lah ! daripada lu manyun !');";
        db.execSQL(sql1);
        //------------------------------------------------------------------------------------------
        String createUser = "CREATE TABLE tb_user (id int, nama text, email text, user text, pass text)";
        Log.d("Data", "onCreate: " + createUser);
        db.execSQL(createUser);
        String insertUser = "INSERT INTO tb_user (id, nama, email, user, pass) VALUES (1, 'nuzul', 'nuzul@gmail.com','nuzul','nuzul123');";
        db.execSQL(insertUser);
        //------------------------------------------------------------------------------------------
        String createPesanan = "CREATE TABLE tb_pesanan (name text, price text, qty text)";
        Log.d("Data", "onCreate: " + createPesanan);
        db.execSQL(createPesanan);
        //String insertPesanan = "INSERT INTO tb_pesanan (name, price, qty) VALUES ('Ayamcrispy', '25000', '2');";
        //db.execSQL(insertPesanan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS tb_saran");
        this.onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tb_user");
        this.onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tb_pesanan");
        this.onCreate(db);
    }

    //==============================================================================================
    public List<Sugestion> allSaran() {
        List<Sugestion> saran = new ArrayList<>();
        String selectQuery = "SELECT  * FROM tb_saran";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                Sugestion listSaran = new Sugestion();
                listSaran.setNama(cursor.getString(0));
                listSaran.setEmail(cursor.getString(1));
                listSaran.setSaran(cursor.getString(2));
                saran.add(listSaran);
            } while (cursor.moveToNext());
        }
        db.close();
        return saran;
    }

    public List<Pesanan> allPesanan() {
        List<Pesanan> pesanan = new ArrayList<>();
        String selectQuery = "SELECT  * FROM tb_pesanan";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                Pesanan listPesanan = new Pesanan();
                listPesanan.setName(cursor.getString(0));
                listPesanan.setPrice(cursor.getString(1));
                listPesanan.setQty(cursor.getString(2));
                pesanan.add(listPesanan);
            } while (cursor.moveToNext());
        }
        db.close();
        return pesanan;
    }

    //==============================================================================================
    public void insertUser(User u ){
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama", u.getNama());
        values.put("email", u.getEmail());
        values.put("user", u.getUser());
        values.put("pass", u.getPass());

        db.insert("tb_user", null, values);
    }

    //==============================================================================================
    public String searchPass(String user){
        db = getReadableDatabase();
        String qry = "SELECT user, pass FROM tb_user";
        Cursor cursor = db.rawQuery(qry, null);
        String a,b;
        b = "Not Found !";
        if (cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);
                if (a.equals(user))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

}