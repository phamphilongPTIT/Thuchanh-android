package com.example.phamphilong_ktra2_bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.phamphilong_ktra2_bai2.model.LichThi;

import java.util.ArrayList;
import java.util.List;

public class SQLiteLichThi extends SQLiteOpenHelper {

    private static final String DB_NAME = "LichThi.db";
    private static final int DB_VERSION = 1;

    public SQLiteLichThi(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE lichthi(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenMon TEXT," +
                "ngayThi TEXT," +
                "gioBatDau TEXT," +
                "thiViet TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addMonThi(LichThi lichThi){
        String sql = "INSERT INTO lichthi(tenMon,ngayThi,gioBatDau,thiViet) VALUES(?,?,?,?)";
        String[] args = {lichThi.getTenMon(),lichThi.getNgayThi(),lichThi.getGioBatDau(),lichThi.getThiViet()};
        SQLiteDatabase statement = getWritableDatabase();
        statement.execSQL(sql, args);
    }

    public List<LichThi> getAllLichThi(){
        List<LichThi> lichThis = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        Cursor resultSet = statement.query("lichthi", null, null, null, null, null, null);
        while (resultSet != null && resultSet.moveToNext()){
            int id = resultSet.getInt(0);
            String tenMon = resultSet.getString(1);
            String ngayThi = resultSet.getString(2);
            String gioBatDau = resultSet.getString(3);
            String thiViet = resultSet.getString(4);
            lichThis.add(new LichThi(id, tenMon, ngayThi, gioBatDau, thiViet));
        }
        return lichThis;
    }

    public LichThi getLichThiById(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase statement = getReadableDatabase();
        Cursor resultSet = statement.query("lichthi", null, whereClause, whereArgs, null, null, null);
        if (resultSet.moveToNext()){
            String tenMon = resultSet.getString(1);
            String ngayThi = resultSet.getString(2);
            String gioBatDau = resultSet.getString(3);
            String thiViet = resultSet.getString(4);
            return new LichThi(id, tenMon,ngayThi, gioBatDau,thiViet);
        }
        return null;
    }

    public int update(LichThi lichThi){
        ContentValues values = new ContentValues();
        values.put("tenMon", lichThi.getTenMon());
        values.put("ngayThi", lichThi.getNgayThi());
        values.put("gioBatDau", lichThi.getGioBatDau());
        values.put("thiViet", lichThi.getThiViet());
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(lichThi.getId())};
        SQLiteDatabase statement = getWritableDatabase();
        return statement.update("lichthi", values, whereClause, whereArgs);
    }

    public int deleteById(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase statement = getWritableDatabase();
        return statement.delete("lichthi", whereClause, whereArgs);
    }

    public List<LichThi> getListLichThiByName(String textSearch){
        List<LichThi> lichThis = new ArrayList<>();
        String whereClause = "tenMon LIKE ?";
        String[] whereArgs = {"%" + textSearch + "%"};
        SQLiteDatabase statement = getReadableDatabase();
        Cursor cursor = statement.query("lichthi", null, whereClause, whereArgs, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenMon = cursor.getString(1);
            String ngayThi = cursor.getString(2);
            String gioBatDau = cursor.getString(3);
            String thiViet = cursor.getString(4);

            lichThis.add(new LichThi(id, tenMon, ngayThi, gioBatDau, thiViet));
        }
        cursor.close();
        return lichThis;
    }

}
