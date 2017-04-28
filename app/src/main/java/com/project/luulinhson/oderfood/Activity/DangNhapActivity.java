package com.project.luulinhson.oderfood.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.luulinhson.oderfood.DAO.NhanVienDAO;
import com.project.luulinhson.oderfood.Database.DuLieuApp;
import com.project.luulinhson.oderfood.R;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.id;
import static android.R.attr.switchMinWidth;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtendangnhapDN,edmatkhauDN;
    Button btndangnhap;
    TextView tvnutdangky;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        edtendangnhapDN = (EditText) findViewById(R.id.edtendangnhapDN);
        edmatkhauDN = (EditText) findViewById(R.id.edmatkhauDN);
        btndangnhap = (Button) findViewById(R.id.btndangnhap);
        tvnutdangky = (TextView) findViewById(R.id.tvnutdangky);

        btndangnhap.setOnClickListener(this);
        tvnutdangky.setOnClickListener(this);
        nhanVienDAO = new NhanVienDAO(this);

        SharedPreferences spdangnhap = getSharedPreferences("trangthaidangnhap",MODE_PRIVATE);
        SharedPreferences.Editor edit = spdangnhap.edit();
        edit.putBoolean("dangnhap",false);
        edit.commit();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btndangnhap:
                String stendangnhap = edtendangnhapDN.getText().toString();
                String smatkhau = edmatkhauDN.getText().toString();

                int kiemtra = nhanVienDAO.KiemTraDangNhap(stendangnhap,smatkhau);
                if(kiemtra != 0){
                    Intent intent = new Intent(DangNhapActivity.this,TrangChuActivity.class);
                    intent.putExtra("tendn",stendangnhap);
                    intent.putExtra("manhanvien",kiemtra);
                    startActivity(intent);
                    Toast.makeText(DangNhapActivity.this,getResources().getString(R.string.dangnhapthanhcong) + " " + stendangnhap, Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(DangNhapActivity.this,getResources().getString(R.string.dangnhapthatbai),Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tvnutdangky:
                Intent intent = new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }
}
