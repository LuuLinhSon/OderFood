package com.project.luulinhson.oderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.luulinhson.oderfood.DTO.MonAnDTO;
import com.project.luulinhson.oderfood.Database.DuLieuApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2/2/2017.
 */

public class MonAnDAO {

    SQLiteDatabase database;

    public MonAnDAO(Context context){
        DuLieuApp duLieuApp = new DuLieuApp(context);
        database = duLieuApp.open();
    }

    public boolean ThemMonAn(MonAnDTO monAnDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DuLieuApp.TB_MONAN_MALOAI,monAnDTO.getMaloaimonan());
        contentValues.put(DuLieuApp.TB_MONAN_TENMONAN,monAnDTO.getTenmonan());
        contentValues.put(DuLieuApp.TB_MONAN_GIATIEN,monAnDTO.getGiatienmonan());
        contentValues.put(DuLieuApp.TB_MONAN_HINHANH,monAnDTO.getHinhanh());

        long trave = database.insert(DuLieuApp.TB_MONAN,null,contentValues);
        if(trave != 0){
            return true;
        }else {
            return false;
        }
    }

    public List<MonAnDTO> LayTatCaMonAn(int maloai){
        List<MonAnDTO> monAnDTOList = new ArrayList<MonAnDTO>();
        String truyvan = "select * from " + DuLieuApp.TB_MONAN + " where " + DuLieuApp.TB_MONAN_MALOAI + " = '" + maloai + "' ";
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            MonAnDTO monAnDTO = new MonAnDTO();
            monAnDTO.setHinhanh(cursor.getString(cursor.getColumnIndex(DuLieuApp.TB_MONAN_HINHANH)) + "");
            monAnDTO.setTenmonan(cursor.getString(cursor.getColumnIndex(DuLieuApp.TB_MONAN_TENMONAN)));
            monAnDTO.setGiatienmonan(cursor.getString(cursor.getColumnIndex(DuLieuApp.TB_MONAN_GIATIEN)));
            monAnDTO.setMamonan(cursor.getInt(cursor.getColumnIndex(DuLieuApp.TB_MONAN_MAMONAN)));
            monAnDTO.setMaloaimonan(cursor.getInt(cursor.getColumnIndex(DuLieuApp.TB_MONAN_MALOAI)));

            monAnDTOList.add(monAnDTO);

            cursor.moveToNext();
        }
        return monAnDTOList;
    }

    public boolean XoaMonAn(int mamonan){
        long kiemtra = database.delete(DuLieuApp.TB_MONAN,DuLieuApp.TB_MONAN_MAMONAN + " = " + mamonan,null);
        if(kiemtra != 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean CapNhatTenVaGiaMonAn(int mamonan,String tenmonan,String giamonan){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DuLieuApp.TB_MONAN_TENMONAN,tenmonan);
        contentValues.put(DuLieuApp.TB_MONAN_GIATIEN,giamonan);

        long kiemtra = database.update(DuLieuApp.TB_MONAN,contentValues,DuLieuApp.TB_MONAN_MAMONAN + " = " + mamonan,null);
        if(kiemtra != 0){
            return true;
        }else {
            return false;
        }
    }
}
