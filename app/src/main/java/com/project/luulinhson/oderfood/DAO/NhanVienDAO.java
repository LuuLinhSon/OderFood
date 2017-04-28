package com.project.luulinhson.oderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.luulinhson.oderfood.DTO.NhanVienDTO;
import com.project.luulinhson.oderfood.Database.DuLieuApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 1/11/2017.
 */

public class NhanVienDAO {
    SQLiteDatabase database;

    public NhanVienDAO(Context context){
        DuLieuApp duLieuApp = new DuLieuApp(context);
        database = duLieuApp.open();
    }
    public long ThemNhanVien(NhanVienDTO nhanVienDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DuLieuApp.TB_NHANVIEN_TENDN,nhanVienDTO.getTendn());
        contentValues.put(DuLieuApp.TB_NHANVIEN_MATKHAU,nhanVienDTO.getMatkhau());
        contentValues.put(DuLieuApp.TB_NHANVIEN_GIOITINH,nhanVienDTO.getGioitinh());
        contentValues.put(DuLieuApp.TB_NHANVIEN_NGAYSINH,nhanVienDTO.getNgaysinh());
        contentValues.put(DuLieuApp.TB_NHANVIEN_CMND,nhanVienDTO.getCmnd());

        long trave = database.insert(DuLieuApp.TB_NHANVIEN,null,contentValues);
        return trave;
    }

    public int KiemTraDangNhap(String tendangnhap,String matkhau){

        int manhanvien = 0;
        String truyvan = "select * from " + DuLieuApp.TB_NHANVIEN + " where " + DuLieuApp.TB_NHANVIEN_TENDN + " = '" + tendangnhap
                + "' and " + DuLieuApp.TB_NHANVIEN_MATKHAU + " = '" + matkhau + "'";

        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            manhanvien = cursor.getInt(cursor.getColumnIndex(DuLieuApp.TB_NHANVIEN_MANV));

            cursor.moveToNext();
        }
        return manhanvien;
    }

    public List<NhanVienDTO> LayTatCaNhanVien(){
        List<NhanVienDTO> nhanVienDTOList = new ArrayList<>();

        String truyvan = "select * from " + DuLieuApp.TB_NHANVIEN;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            NhanVienDTO nhanVienDTO = new NhanVienDTO();

            nhanVienDTO.setTendn(cursor.getString(cursor.getColumnIndex(DuLieuApp.TB_NHANVIEN_TENDN)));
            nhanVienDTO.setGioitinh(cursor.getString(cursor.getColumnIndex(DuLieuApp.TB_NHANVIEN_GIOITINH)));
            nhanVienDTO.setCmnd(cursor.getString(cursor.getColumnIndex(DuLieuApp.TB_NHANVIEN_CMND)));

            nhanVienDTOList.add(nhanVienDTO);

            cursor.moveToNext();
        }
        return nhanVienDTOList;
    }

}
