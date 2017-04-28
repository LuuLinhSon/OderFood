package com.project.luulinhson.oderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.luulinhson.oderfood.DTO.BanAnDTO;
import com.project.luulinhson.oderfood.DTO.NhanVienDTO;
import com.project.luulinhson.oderfood.Database.DuLieuApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 1/17/2017.
 */

public class BanAnDAO {
    SQLiteDatabase database;

    public BanAnDAO(Context context){
        DuLieuApp duLieuApp = new DuLieuApp(context);
        database = duLieuApp.open();
    }
    public boolean ThemBanAn(String tenbanan){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DuLieuApp.TB_BANAN_TENBAN,tenbanan);
        contentValues.put(DuLieuApp.TB_BANAN_TINHTRANG,"false");

        long trave = database.insert(DuLieuApp.TB_BANAN,null,contentValues);
        if(trave != 0){
            return true;
        }else {
            return false;
        }
    }

    public List<BanAnDTO> LayTatCaBanAn(){
        List<BanAnDTO> banAnDTOList = new ArrayList<>();
        String truyvan = "select * from " + DuLieuApp.TB_BANAN;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            BanAnDTO banAnDTO = new BanAnDTO();
            banAnDTO.setMabanan(cursor.getInt(cursor.getColumnIndex(DuLieuApp.TB_BANAN_MABAN)));
            banAnDTO.setTenbanan(cursor.getString(cursor.getColumnIndex(DuLieuApp.TB_BANAN_TENBAN)));
            banAnDTO.setTrangthaibanan(cursor.getString(cursor.getColumnIndex(DuLieuApp.TB_BANAN_TINHTRANG)));

            banAnDTOList.add(banAnDTO);
            cursor.moveToNext();
        }
        return banAnDTOList;
        }

    public String LayTinhTrangBanAnTheoMa(int maban){
        String tinhtrang = "";
        String truyvan = "select * from " + DuLieuApp.TB_BANAN + " where " + DuLieuApp.TB_BANAN_MABAN + " = '" + maban + "' ";
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            tinhtrang = cursor.getString(cursor.getColumnIndex(DuLieuApp.TB_BANAN_TINHTRANG));

            cursor.moveToNext();
        }
        return tinhtrang;
    }

    public boolean CapNhatTinhTrangBan(int maban,String tinhtrang){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DuLieuApp.TB_BANAN_TINHTRANG,tinhtrang);

        long kiemtra = database.update(DuLieuApp.TB_BANAN,contentValues,DuLieuApp.TB_BANAN_MABAN + " ='" + maban + "'",null);
        if(kiemtra != 0){
            return true;
        }else {
            return false;
        }

    }

    public boolean XoaBanAn(int maban){
        long kiemtra = database.delete(DuLieuApp.TB_BANAN,DuLieuApp.TB_BANAN_MABAN + " = " + maban,null);
        if(kiemtra != 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean CapNhatTenBanAn(int maban,String tenbanan){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DuLieuApp.TB_BANAN_TENBAN,tenbanan);

        long kiemtra = database.update(DuLieuApp.TB_BANAN,contentValues,DuLieuApp.TB_BANAN_MABAN + " = " + maban,null);
        if(kiemtra != 0){
            return true;
        }else {
            return false;
        }
    }
    }


