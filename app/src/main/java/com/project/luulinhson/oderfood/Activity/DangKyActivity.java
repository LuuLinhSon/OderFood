package com.project.luulinhson.oderfood.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.project.luulinhson.oderfood.DAO.NhanVienDAO;
import com.project.luulinhson.oderfood.DTO.NhanVienDTO;
import com.project.luulinhson.oderfood.Database.DuLieuApp;
import com.project.luulinhson.oderfood.Fragment.DatePickerFragment;
import com.project.luulinhson.oderfood.R;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener,View.OnFocusChangeListener{

    EditText edtendangnhap,edmatkhau,ednhaplaimatkhau,edngaysinh,edcmnd;
    Button btndangky,btnhuybo;
    RadioGroup rggioitinh;
    String sGioiTinh;

    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        edtendangnhap = (EditText) findViewById(R.id.edtendangnhapDK);
        edmatkhau = (EditText) findViewById(R.id.edmatkhauDK);
        ednhaplaimatkhau = (EditText) findViewById(R.id.ednhaplaimatkhau);
        edngaysinh = (EditText) findViewById(R.id.edngaysinh);
        edcmnd = (EditText) findViewById(R.id.edcmnd);

        btndangky = (Button) findViewById(R.id.btndangkyDK);
        btnhuybo = (Button) findViewById(R.id.btnhuybo);

        rggioitinh = (RadioGroup) findViewById(R.id.rggioitinh);

        edngaysinh.setOnFocusChangeListener(this);
        btndangky.setOnClickListener(this);
        btnhuybo.setOnClickListener(this);

        nhanVienDAO = new NhanVienDAO(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btndangkyDK:
                String sTenDangNhap = edtendangnhap.getText().toString();
                String sMatKhau = edmatkhau.getText().toString();
                String sNhapLaiMatKhau = ednhaplaimatkhau.getText().toString();
                String sNgaySinh = edngaysinh.getText().toString();
                String scmnd = edcmnd.getText().toString();

                switch (rggioitinh.getCheckedRadioButtonId()){
                    case R.id.rbNam:
                        sGioiTinh = "Nam";
                        break;
                    case R.id.rbNu:
                        sGioiTinh = "Nữ";
                        break;
                }

                if(sTenDangNhap == null || sTenDangNhap.equals("")){
                    Toast.makeText(DangKyActivity.this,getResources().getString(R.string.loitendangnhap),Toast.LENGTH_LONG).show();
                }else if(sMatKhau == null || sMatKhau.equals("")){
                    Toast.makeText(DangKyActivity.this,getResources().getString(R.string.loimatkhau),Toast.LENGTH_LONG).show();
                }else if(!sMatKhau.equals(sNhapLaiMatKhau)){
                    Toast.makeText(DangKyActivity.this,getResources().getString(R.string.loimatkhaukhongtrungnhau),Toast.LENGTH_LONG).show();
                }else if(sNgaySinh == null || sNgaySinh.equals("")) {
                    Toast.makeText(DangKyActivity.this,getResources().getString(R.string.loingaysinh),Toast.LENGTH_LONG).show();
                }else if(scmnd == null || scmnd.equals("")) {
                    Toast.makeText(DangKyActivity.this,getResources().getString(R.string.loicmnd),Toast.LENGTH_LONG).show();
                }else {
                    NhanVienDTO nhanVienDTO = new NhanVienDTO();
                    nhanVienDTO.setTendn(sTenDangNhap);
                    nhanVienDTO.setMatkhau(sMatKhau);
                    nhanVienDTO.setNgaysinh(sNgaySinh);
                    nhanVienDTO.setCmnd(scmnd);
                    nhanVienDTO.setGioitinh(sGioiTinh);

                    nhanVienDAO.ThemNhanVien(nhanVienDTO);

                    Toast.makeText(DangKyActivity.this,getResources().getString(R.string.dangkythanhcong),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DangKyActivity.this,DangNhapActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.btnhuybo:
                Intent intent = new Intent(DangKyActivity.this,DangNhapActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id){
            case R.id.edngaysinh:
                if(hasFocus){
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getFragmentManager(),"ngaysinh");
                }
                break;
        }
    }
}
