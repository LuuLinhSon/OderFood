package com.project.luulinhson.oderfood.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.luulinhson.oderfood.DAO.LoaiMonAnDAO;
import com.project.luulinhson.oderfood.Database.DuLieuApp;
import com.project.luulinhson.oderfood.R;

public class ThemLoaiMonAnActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edthemloaimonan;
    Button btndongythemloaimonan;
    LoaiMonAnDAO loaiMonAnDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_loai_mon_an);

        loaiMonAnDAO = new LoaiMonAnDAO(this);

        edthemloaimonan = (EditText) findViewById(R.id.edthemloaimonan);
        btndongythemloaimonan = (Button) findViewById(R.id.btndongythemloaimonan);

        btndongythemloaimonan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btndongythemloaimonan:
                String sthemloaimonan = edthemloaimonan.getText().toString();
                if(!sthemloaimonan.equals("")){
                    boolean kiemtra = loaiMonAnDAO.ThemLoaiMonAn(sthemloaimonan);
                    Intent iDuLieu = new Intent();
                    iDuLieu.putExtra("kiemtraloaimonan",kiemtra);
                    setResult(Activity.RESULT_OK,iDuLieu);

                }else {
                    Toast.makeText(this,getResources().getString(R.string.vuilongthemloaimonan),Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
        }

    }
}
