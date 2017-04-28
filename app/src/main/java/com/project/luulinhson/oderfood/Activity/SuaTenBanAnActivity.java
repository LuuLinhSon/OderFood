package com.project.luulinhson.oderfood.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.luulinhson.oderfood.DAO.BanAnDAO;
import com.project.luulinhson.oderfood.R;

public class SuaTenBanAnActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edsuatenbanan;
    Button btndongysuatenbanan;
    BanAnDAO banAnDAO;
    int maban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_ten_ban_an);

        banAnDAO = new BanAnDAO(this);

        edsuatenbanan = (EditText) findViewById(R.id.edsuatenbanan);
        btndongysuatenbanan = (Button) findViewById(R.id.btndongysuatenbanan);

        btndongysuatenbanan.setOnClickListener(this);

        maban = getIntent().getIntExtra("maban",0);
    }

    @Override
    public void onClick(View v) {

        String tenban = edsuatenbanan.getText().toString();
        if(!tenban.trim().equals("")){
            boolean kiemtra = banAnDAO.CapNhatTenBanAn(maban,tenban);
            Intent intent = new Intent();
            intent.putExtra("kiemtra", kiemtra);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
        else{
            Toast.makeText(this,"Vui lòng nhập dữ liệu",Toast.LENGTH_SHORT).show();
        }

    }
}
