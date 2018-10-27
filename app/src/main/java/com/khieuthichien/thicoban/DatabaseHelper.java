package com.khieuthichien.thicoban;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "sv_db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_SINHVIEN = "sinhvien";

    public static final String COLUMN_MA_SV = "masv";
    public static final String COLUMN_MA_LOP = "malop";
    public static final String COLUMN_DIEM_TOAN = "diemtoan";
    public static final String COLUMN_DIEM_VAN = "diemvan";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = " CREATE TABLE " + TABLE_SINHVIEN + "(" +
                "" + COLUMN_MA_SV + " NVARCHAR PRIMARY KEY, " +
                "" + COLUMN_MA_LOP + " NVARCHAR, " +
                "" + COLUMN_DIEM_TOAN + " NVARCHAR, " +
                "" + COLUMN_DIEM_VAN + " NVARCHAR " +
                ")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SINHVIEN);
        onCreate(db);
    }

    public void insertSinhvien(Sinhvien sinhvien) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_MA_SV, sinhvien.getMasv());
        values.put(COLUMN_MA_LOP, sinhvien.getMalop());
        values.put(COLUMN_DIEM_TOAN, sinhvien.getDiemtoan());
        values.put(COLUMN_DIEM_VAN, sinhvien.getDiemvan());

        db.insert(TABLE_SINHVIEN, null, values);
        db.close();
    }


    public Sinhvien getSinhvien(String idsinhvien) {

        Sinhvien sinhvien = null;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SINHVIEN,
                new String[]{ COLUMN_MA_SV, COLUMN_MA_LOP, COLUMN_DIEM_TOAN, COLUMN_DIEM_VAN},
                COLUMN_MA_SV + "=?",
                new String[]{String.valueOf(idsinhvien)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            String masv = cursor.getString(cursor.getColumnIndex(COLUMN_MA_SV));

            String malop = cursor.getString(cursor.getColumnIndex(COLUMN_MA_LOP));

            int diemtoan = cursor.getInt(cursor.getColumnIndex(COLUMN_DIEM_TOAN));

            int diemvan = cursor.getInt(cursor.getColumnIndex(COLUMN_DIEM_VAN));

            sinhvien = new Sinhvien(masv, malop, diemtoan, diemvan);
        }
        cursor.close();
        return sinhvien;

    }


    public List<Sinhvien> getAllSanpham() {

        List<Sinhvien> sinhvienList = new ArrayList<>();

        String SELECT_ALL_SANPHAM = "SELECT * FROM " + TABLE_SINHVIEN;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(SELECT_ALL_SANPHAM, null);


        if (cursor.moveToFirst()) {
            do {

                String masv = cursor.getString(cursor.getColumnIndex(COLUMN_MA_SV));

                String malop = cursor.getString(cursor.getColumnIndex(COLUMN_MA_LOP));

                int diemtoan = cursor.getInt(cursor.getColumnIndex(COLUMN_DIEM_TOAN));

                int diemvan = cursor.getInt(cursor.getColumnIndex(COLUMN_DIEM_VAN));

                Sinhvien sinhvien = new Sinhvien();
                sinhvien.setMasv(masv);
                sinhvien.setMalop(malop);
                sinhvien.setDiemtoan(diemtoan);
                sinhvien.setDiemvan(diemvan);

                sinhvienList.add(sinhvien);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return sinhvienList;

    }

    public int updateSinhvien(Sinhvien sinhvien) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_MA_LOP, sinhvien.getMalop());
        values.put(COLUMN_DIEM_TOAN, sinhvien.getDiemtoan());
        values.put(COLUMN_DIEM_VAN, sinhvien.getDiemvan());

        return db.update(TABLE_SINHVIEN, values, COLUMN_MA_SV + " = ?", new String[]{String.valueOf(sinhvien.getMalop())});

    }

    public void deleteSanpham(Sinhvien idsanpham) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SINHVIEN, COLUMN_MA_SV + " = ?", new String[]{String.valueOf(idsanpham.getMasv())});
        db.close();

    }


}
