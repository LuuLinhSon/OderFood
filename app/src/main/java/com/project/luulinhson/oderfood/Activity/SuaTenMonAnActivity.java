package com.project.luulinhson.oderfood.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.luulinhson.oderfood.DAO.MonAnDAO;
import com.project.luulinhson.oderfood.R;

public class SuaTenMonAnActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edsuatenmonan,edsuagiamonan;
    Button btndongysuamonan;
    int mamonan;
    MonAnDAO monAnDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_ten_mon_an);

        monAnDAO = new MonAnDAO(this);

        edsuatenmonan = (EditText) findViewById(R.id.edsuatenmonan);
        edsuagiamonan = (EditText) findViewById(R.id.edsuagiamonan);
        btndongysuamonan = (Button) findViewById(R.id.btndongysuamonan);

        btndongysuamonan.setOnClickListener(this);

        mamonan = getIntent().getIntExtra("mamonan",0);
    }

    @Override
    public void onClick(View v) {
        String tenmonan = edsuatenmonan.getText().toString();
        String giamonan = edsuagiamonan.getText().toString();

        if(!tenmonan.trim().equals("") || !giamonan.trim().equals("")){
            boolean kiemtra = monAnDAO.CapNhatTenVaGiaMonAn(mamonan,tenmonan,giamonan);
            Intent intent = new Intent();
            intent.putExtra("kiemtra", kiemtra);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }else {
            Toast.makeText(this,"Vui lòng nhập dữ liệu",Toast.LENGTH_SHORT).show();
        }

    }
}
