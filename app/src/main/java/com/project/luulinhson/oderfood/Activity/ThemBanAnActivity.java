package com.project.luulinhson.oderfood.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.luulinhson.oderfood.DAO.BanAnDAO;
import com.project.luulinhson.oderfood.R;

/**
 * Created by Admin on 1/17/2017.
 */

public class ThemBanAnActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edthembanan;
    Button btndongythembanan;
    BanAnDAO banAnDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ban_an);

        edthembanan = (EditText) findViewById(R.id.edthembanan);
        btndongythembanan = (Button) findViewById(R.id.btndongythembanan);

        banAnDAO = new BanAnDAO(this);
        btndongythembanan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String stenbanan = edthembanan.getText().toString();
        if(!stenbanan.equals("")) {
            boolean kiemtra = banAnDAO.ThemBanAn(stenbanan);
            Intent intent = new Intent();
            intent.putExtra("kiemtra", kiemtra);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
