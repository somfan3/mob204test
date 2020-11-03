package com.example.test2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public Database(@Nullable Context context) {
        super(context, "mydatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table Sach(ma text primary key, ten text, soluong integer, dongia real, ngaynhap date)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists Sach");
    }
    public int insert(Sach sach){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ma",sach.getMa());
        contentValues.put("ten",sach.getTen());
        contentValues.put("soluong",sach.getSoLuong());
        contentValues.put("dongia",sach.getDonGia());
        contentValues.put("ngaynhap",sdf.format(sach.getNgayNhap()));

        if (db.insert("Sach",null,contentValues ) < 0){
            return -1;
        }
        return 1;
    }
    public int update(Sach sach){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ten",sach.getTen());
        contentValues.put("soluong",sach.getSoLuong());
        contentValues.put("dongia",sach.getDonGia());
        contentValues.put("ngaynhap",sdf.format(sach.getNgayNhap()));

        if (db.update("Sach",contentValues,"ma = ?",new String[]{sach.getMa()}) < 0){
            return -1;
        }
        return 1;
    }
    public int delete(String id){
        SQLiteDatabase db = getWritableDatabase();

        if (db.delete("Sach","ma = ?",new String[]{id}) < 0){
            return -1;
        }
        return 1;
    }
    public List<Sach> getAll()  {
        SQLiteDatabase db = getReadableDatabase();

        List<Sach> sachList = new ArrayList<>();

        Cursor cursor = db.rawQuery("Select * from Sach",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            try {
                Sach sach = new Sach(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getDouble(3),
                        sdf.parse(cursor.getString(4)));
                sachList.add(sach);
                cursor.moveToNext();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cursor.close();

        return sachList;

    }
}
