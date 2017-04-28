package com.project.luulinhson.oderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.luulinhson.oderfood.DTO.LoaiMonAnDTO;
import com.project.luulinhson.oderfood.Database.DuLieuApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 1/31/2017.
 */

public class LoaiMonAnDAO {
    SQLiteDatabase database;

    public LoaiMonAnDAO(Context context){
        DuLieuApp duLieuApp = new DuLieuApp(context);
        database = duLieuApp.open();
    }
    public boolean ThemLoaiMonAn(String tenloaimonan){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DuLieuApp.TB_LOAIMONAN_TENLOAI,tenloaimonan);

        long trave = database.insert(DuLieuApp.TB_LOAIMONAN,null,contentValues);
        if(trave != 0){
            return true;
        }else {
            return false;
        }
    }

    public List<LoaiMonAnDTO> LayDanhSachLoaiMonAn(){
        List<LoaiMonAnDTO> loaiMonAnDTOList = new ArrayList<>();
        String truyvan = "select * from " + DuLieuApp.TB_LOAIMONAN;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            LoaiMonAnDTO loaiMonAnDTO = new LoaiMonAnDTO();
            loaiMonAnDTO.setMaloaimonan(cursor.getInt(cursor.getColumnIndex(DuLieuApp.TB_LOAIMONAN_MALOAI)));
            loaiMonAnDTO.setTenloaimonan(cursor.getString(cursor.getColumnIndex(DuLieuApp.TB_LOAIMONAN_TENLOAI)));

            loaiMonAnDTOList.add(loaiMonAnDTO);

            cursor.moveToNext();
        }
        return loaiMonAnDTOList;
    }

    public String LayHinhAnhLoaiMonAn(int maloai){
        String hinhanh = "";
        String truyvan = "select * from " + DuLieuApp.TB_MONAN + " where " + DuLieuApp.TB_MONAN_MALOAI + " = '" + maloai + "' "
                + " and " + DuLieuApp.TB_MONAN_HINHANH + " != '' order by " + DuLieuApp.TB_MONAN_MAMONAN + " limit 1";
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            hinhanh = cursor.getString(cursor.getColumnIndex(DuLieuApp.TB_MONAN_HINHANH));
            cursor.moveToNext();
        }
        return hinhanh;
    }

    public boolean XoaLoaiMonAn(int maloaimonan){
        long kiemtra = database.delete(DuLieuApp.TB_LOAIMONAN,DuLieuApp.TB_LOAIMONAN_MALOAI + " = " + maloaimonan,null);
        if(kiemtra != 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean XoaMonAn(int maloaimonan){
        long kiemtra = database.delete(DuLieuApp.TB_MONAN,DuLieuApp.TB_MONAN_MALOAI + " = " + maloaimonan,null);

        if(kiemtra != 0){
            return true;
        }else {
            return false;
        }
    }
}
