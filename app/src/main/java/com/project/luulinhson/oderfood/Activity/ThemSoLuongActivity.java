package com.project.luulinhson.oderfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.luulinhson.oderfood.DAO.GoiMonDAO;
import com.project.luulinhson.oderfood.DTO.ChiTietGoiMonDTO;
import com.project.luulinhson.oderfood.R;

/**
 * Created by Admin on 2/11/2017.
 */

public class ThemSoLuongActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edthemsoluong;
    Button btndongythemsoluong;
    GoiMonDAO goiMonDAO;
    int maban;
    int mamonan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_so_luong);

        edthemsoluong = (EditText) findViewById(R.id.edthemsoluong);
        btndongythemsoluong = (Button) findViewById(R.id.btndongythemsoluong);

        btndongythemsoluong.setOnClickListener(this);

        goiMonDAO = new GoiMonDAO(this);

        Intent intent = getIntent();
        maban = intent.getIntExtra("maban",0);
        mamonan = intent.getIntExtra("mamonan",0);


    }

    @Override
    public void onClick(View v) {
        int magoimon = goiMonDAO.LayMaGoiMonTheoMaBan(maban,"false");
        boolean kiemtra = goiMonDAO.KiemTraMonAnDaTonTai(magoimon,mamonan);
        if(kiemtra){
            //cập nhật lại số lượng mona ăn
            int soluongcu = goiMonDAO.LaySoLuongMonAnTheoMaGoiMon(magoimon,mamonan);
            int soluongmoi = Integer.parseInt(edthemsoluong.getText().toString());
            int tongsoluong = soluongcu + soluongmoi;

            ChiTietGoiMonDTO chiTietGoiMonDTO = new ChiTietGoiMonDTO();
            chiTietGoiMonDTO.setMagoimon(magoimon);
            chiTietGoiMonDTO.setMamonan(mamonan);
            chiTietGoiMonDTO.setSoluong(tongsoluong);

            boolean capnhat = goiMonDAO.CapNhatSoLuong(chiTietGoiMonDTO);
            if(capnhat){
                Toast.makeText(this,"Thêm thành công",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"Thêm thất bại",Toast.LENGTH_SHORT).show();
            }

        }else {
            // Tạo món ăn mới
            int soluong = Integer.parseInt(edthemsoluong.getText().toString());

            ChiTietGoiMonDTO chiTietGoiMonDTO = new ChiTietGoiMonDTO();
            chiTietGoiMonDTO.setMagoimon(magoimon);
            chiTietGoiMonDTO.setMamonan(mamonan);
            chiTietGoiMonDTO.setSoluong(soluong);

            boolean themmoi = goiMonDAO.ThemChiTietMonAn(chiTietGoiMonDTO);
            if(themmoi){
                Toast.makeText(this,"Thêm thành công",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"Thêm thất bại",Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }
}
