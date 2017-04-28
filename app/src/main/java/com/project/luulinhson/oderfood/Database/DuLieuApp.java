package com.project.luulinhson.oderfood.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 1/10/2017.
 */

public class DuLieuApp extends SQLiteOpenHelper {

    public static final String TEN_DATABASE = "OderFood";

    public static final String TB_NHANVIEN = "NHANVIEN";
    public static final String TB_MONAN    = "MONAN";
    public static final String TB_LOAIMONAN = "LOAIMONAN";
    public static final String TB_BANAN = "BANAN";
    public static final String TB_GOIMON = "GOIMON";
    public static final String TB_CHITIETGOIMON = "CHITIETGOIMON";

    public static final String TB_NHANVIEN_MANV = "MANHANVIEN";
    public static final String TB_NHANVIEN_TENDN = "TENDANGNHAP";
    public static final String TB_NHANVIEN_MATKHAU = "MATKHAU";
    public static final String TB_NHANVIEN_GIOITINH = "GIOITINH";
    public static final String TB_NHANVIEN_NGAYSINH = "NGAYSINH";
    public static final String TB_NHANVIEN_CMND = "CMND";

    public static final String TB_MONAN_MAMONAN    = "MAMONAN";
    public static final String TB_MONAN_TENMONAN    = "TENMONAN";
    public static final String TB_MONAN_GIATIEN    = "GIATIENMONAN";
    public static final String TB_MONAN_MALOAI    = "MALOAIMONAN";
    public static final String TB_MONAN_HINHANH    = "HINHANH";

    public static final String TB_LOAIMONAN_MALOAI = "MALOAIMONAN";
    public static final String TB_LOAIMONAN_TENLOAI = "TENLOAIMONAN";

    public static final String TB_BANAN_MABAN = "MABANAN";
    public static final String TB_BANAN_TENBAN = "TENBANAN";
    public static final String TB_BANAN_TINHTRANG = "TINHTRANGBANAN";

    public static final String TB_GOIMON_MAGOIMON = "MAGOIMON";
    public static final String TB_GOIMON_MANV = "MAVNGOIMON";
    public static final String TB_GOIMON_NGAYGOI = "NGAYGOIMON";
    public static final String TB_GOIMON_TINHTRANG = "TINHTRANGGOIMON";
    public static final String TB_GOIMON_MABAN = "MABANGOIMON";

    public static final String TB_CHITIETGOIMON_MAGOIMON = "MAGOIMON";
    public static final String TB_CHITIETGOIMON__MAMONAN = "MAMONAN";
    public static final String TB_CHITIETGOIMON_SOLUONG = "SOLUONG";


    public DuLieuApp(Context context) {
        super(context, TEN_DATABASE, null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_TB_NHANVIEN = "create table " + TB_NHANVIEN + " ( " + TB_NHANVIEN_MANV + " integer primary key autoincrement, "
                + TB_NHANVIEN_TENDN + " text, " + TB_NHANVIEN_MATKHAU + " text, " + TB_NHANVIEN_GIOITINH + " text, "
                + TB_NHANVIEN_NGAYSINH + " text, " + TB_NHANVIEN_CMND + " text)";

        String query_TB_MONAN = "create table " + TB_MONAN + " ( " + TB_MONAN_MAMONAN + " integer primary key autoincrement, "
                + TB_MONAN_TENMONAN + " text, " + TB_MONAN_GIATIEN + " text, " + TB_MONAN_MALOAI + " integer, " + TB_MONAN_HINHANH + " text)";

        String query_TB_LOAIMONAN = "create table " + TB_LOAIMONAN + " ( " + TB_LOAIMONAN_MALOAI + " integer primary key autoincrement, "
                + TB_LOAIMONAN_TENLOAI + " text)";

        String query_TB_BANAN = "create table " + TB_BANAN + " ( " + TB_BANAN_MABAN + " integer primary key autoincrement, "
                + TB_BANAN_TENBAN + " text, " + TB_BANAN_TINHTRANG + " text)";

        String query_TB_GOIMON = "create table " + TB_GOIMON + " ( " + TB_GOIMON_MAGOIMON + " integer primary key autoincrement, "
                + TB_GOIMON_MANV + " integer, " + TB_GOIMON_NGAYGOI + " text, " + TB_GOIMON_TINHTRANG + " text, "
                + TB_GOIMON_MABAN + " integer)";

        String query_TB_CHITIETGOIMON = "create table " + TB_CHITIETGOIMON + " ( " + TB_CHITIETGOIMON_MAGOIMON + " integer, "
                + TB_CHITIETGOIMON__MAMONAN + " integer, " + TB_CHITIETGOIMON_SOLUONG + " integer, "
                + " primary key ( " + TB_CHITIETGOIMON_MAGOIMON + "," + TB_CHITIETGOIMON__MAMONAN + "))";

        db.execSQL(query_TB_NHANVIEN);
        db.execSQL(query_TB_MONAN);
        db.execSQL(query_TB_LOAIMONAN);
        db.execSQL(query_TB_BANAN);
        db.execSQL(query_TB_GOIMON);
        db.execSQL(query_TB_CHITIETGOIMON);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion){
            db.execSQL("drop table if exists " + TB_NHANVIEN);
            db.execSQL("drop table if exists " + TB_MONAN);
            db.execSQL("drop table if exists " + TB_LOAIMONAN);
            db.execSQL("drop table if exists " + TB_BANAN);
            db.execSQL("drop table if exists " + TB_GOIMON);
            db.execSQL("drop table if exists " + TB_CHITIETGOIMON);
            onCreate(db);
        }

    }

    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }

}
