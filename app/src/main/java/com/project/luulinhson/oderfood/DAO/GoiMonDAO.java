package com.project.luulinhson.oderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.luulinhson.oderfood.DTO.ChiTietGoiMonDTO;
import com.project.luulinhson.oderfood.DTO.GoiMonDTO;
import com.project.luulinhson.oderfood.DTO.ThanhToanDTO;
import com.project.luulinhson.oderfood.Database.DuLieuApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2/10/2017.
 */

public class GoiMonDAO {

    SQLiteDatabase database;

    public GoiMonDAO(Context context){
        DuLieuApp duLieuApp = new DuLieuApp(context);
        database = duLieuApp.open();
    }

    public boolean ThemGoiMon(GoiMonDTO goiMonDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DuLieuApp.TB_GOIMON_MABAN,goiMonDTO.getMaban());
        contentValues.put(DuLieuApp.TB_GOIMON_MANV,goiMonDTO.getManhanvien());
        contentValues.put(DuLieuApp.TB_GOIMON_NGAYGOI,goiMonDTO.getNgaygoimon());
        contentValues.put(DuLieuApp.TB_GOIMON_TINHTRANG,goiMonDTO.getTinhtrang());

        long trave = database.insert(DuLieuApp.TB_GOIMON,null,contentValues);
        if(trave != 0){
            return true;
        }else {
            return false;
        }
    }

    public int LayMaGoiMonTheoMaBan(int maban,String tinhtrang){
        String truyvan = "select * from " + DuLieuApp.TB_GOIMON + " where " + DuLieuApp.TB_GOIMON_MABAN + " = '" + maban + "' AND "
                + DuLieuApp.TB_GOIMON_TINHTRANG + " = '" + tinhtrang + "'";

        int magoimon = 0;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            magoimon = cursor.getInt(cursor.getColumnIndex(DuLieuApp.TB_GOIMON_MAGOIMON));

            cursor.moveToNext();
        }
        return magoimon;
    }

    public boolean KiemTraMonAnDaTonTai(int magoimon,int mamonan){
        String truyvan = "select * from " + DuLieuApp.TB_CHITIETGOIMON + " where " + DuLieuApp.TB_CHITIETGOIMON_MAGOIMON + " = " + magoimon + " and "
               + DuLieuApp.TB_CHITIETGOIMON__MAMONAN + " = " + mamonan;

        Cursor cursor = database.rawQuery(truyvan,null);
        if(cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }

    }

    public int LaySoLuongMonAnTheoMaGoiMon(int magoimon,int mamonan){
        int soluong = 0;
        String truyvan = "select * from " + DuLieuApp.TB_CHITIETGOIMON + " where " + DuLieuApp.TB_CHITIETGOIMON_MAGOIMON + " = " + magoimon + " and "
                + DuLieuApp.TB_CHITIETGOIMON__MAMONAN + " = " + mamonan;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            soluong = cursor.getInt(cursor.getColumnIndex(DuLieuApp.TB_CHITIETGOIMON_SOLUONG));
            cursor.moveToNext();
        }
        return soluong;
    }

    public boolean CapNhatSoLuong(ChiTietGoiMonDTO chiTietGoiMonDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DuLieuApp.TB_CHITIETGOIMON_SOLUONG,chiTietGoiMonDTO.getSoluong());

        long trave = database.update(DuLieuApp.TB_CHITIETGOIMON,contentValues,DuLieuApp.TB_CHITIETGOIMON_MAGOIMON + " = " + chiTietGoiMonDTO.getMagoimon()
        + " and " + DuLieuApp.TB_CHITIETGOIMON__MAMONAN + " = " + chiTietGoiMonDTO.getMamonan(),null);

        if(trave != 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean ThemChiTietMonAn(ChiTietGoiMonDTO chiTietGoiMonDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DuLieuApp.TB_CHITIETGOIMON_SOLUONG,chiTietGoiMonDTO.getSoluong());
        contentValues.put(DuLieuApp.TB_CHITIETGOIMON_MAGOIMON,chiTietGoiMonDTO.getMagoimon());
        contentValues.put(DuLieuApp.TB_CHITIETGOIMON__MAMONAN,chiTietGoiMonDTO.getMamonan());

        long trave = database.insert(DuLieuApp.TB_CHITIETGOIMON,null,contentValues);
        if(trave != 0){
            return true;
        }else {
            return false;
        }
    }

    public List<ThanhToanDTO> LayDanhSachMonAnTheoMaGoiMon(int magoimon){
        String truyvan = "select * from " + DuLieuApp.TB_CHITIETGOIMON + "," + DuLieuApp.TB_MONAN + " where "
                + DuLieuApp.TB_CHITIETGOIMON + "." + DuLieuApp.TB_CHITIETGOIMON__MAMONAN + " = "
                + DuLieuApp.TB_MONAN + "." + DuLieuApp.TB_MONAN_MAMONAN + " and " + DuLieuApp.TB_CHITIETGOIMON_MAGOIMON + " = " + magoimon;

        List<ThanhToanDTO> thanhToanDTOList = new ArrayList<>();
        Cursor cursor =  database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ThanhToanDTO thanhToanDTO = new ThanhToanDTO();
            thanhToanDTO.setTenmonanthanhtoan(cursor.getString(cursor.getColumnIndex(DuLieuApp.TB_MONAN_TENMONAN)));
            thanhToanDTO.setSoluong(cursor.getInt(cursor.getColumnIndex(DuLieuApp.TB_CHITIETGOIMON_SOLUONG)));
            thanhToanDTO.setGiatien(cursor.getInt(cursor.getColumnIndex(DuLieuApp.TB_MONAN_GIATIEN)));

            thanhToanDTOList.add(thanhToanDTO);

            cursor.moveToNext();
        }
        return thanhToanDTOList;

    }

    public boolean CapNhatLaiTinhTrangGoiMonTheoMaBan(int maban,String tinhtrang){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DuLieuApp.TB_GOIMON_TINHTRANG,tinhtrang);

        long trave = database.update(DuLieuApp.TB_GOIMON,contentValues,DuLieuApp.TB_GOIMON_MABAN + " = " + maban,null);
        if(trave != 0){
            return true;
        }else {
            return false;
        }
    }

}
