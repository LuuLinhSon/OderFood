package com.project.luulinhson.oderfood.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.project.luulinhson.oderfood.Adapter.AdapterHienThiBanAn;
import com.project.luulinhson.oderfood.DTO.NhanVienDTO;
import com.project.luulinhson.oderfood.Fragment.BanAnFragment;
import com.project.luulinhson.oderfood.Fragment.NhanVienFragment;
import com.project.luulinhson.oderfood.Fragment.ThucDonFragment;
import com.project.luulinhson.oderfood.R;

public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView tvtennhanvien;
    SharedPreferences sptendangnhap;
    String tennv;
    String tendn;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        SharedPreferences spdangnhap = getSharedPreferences("trangthaidangnhap",MODE_PRIVATE);
        SharedPreferences.Editor edit = spdangnhap.edit();
        edit.putBoolean("dangnhap",true);
        edit.commit();

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_trang_chu);
        navigationView = (NavigationView) findViewById(R.id.navigationview_trangchu);
        toolbar = (Toolbar) findViewById(R.id.toolbar_trangchu);

        View view = navigationView.inflateHeaderView(R.layout.header_layout);
        tvtennhanvien = (TextView) view.findViewById(R.id.tvtennhanvien);

        navigationView.setNavigationItemSelectedListener(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.mo,R.string.dong){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerToggle.syncState();


        Intent intent = getIntent();
        tennv = intent.getStringExtra("tendn");
        if(!tennv.equals("")){
            tvtennhanvien.setText(tennv);
            sptendangnhap = getSharedPreferences("tendangnhap",MODE_PRIVATE);
            SharedPreferences.Editor ed = sptendangnhap.edit();
            ed.putString("tendn",tennv);
            ed.commit();
        }else {
            try {
                sptendangnhap = getSharedPreferences("tendangnhap",MODE_PRIVATE);
                tendn = sptendangnhap.getString("tendn",null);
            }catch (NullPointerException e){
                tvtennhanvien.setText(tendn);
            }
            tvtennhanvien.setText(tendn);
        }

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction tranHienThiBanAn = fragmentManager.beginTransaction();
        BanAnFragment banAnFragment = new BanAnFragment();
        tranHienThiBanAn.replace(R.id.content,banAnFragment);
        tranHienThiBanAn.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.trangchu:
                FragmentTransaction tranHienThiBanAn = fragmentManager.beginTransaction();
                BanAnFragment banAnFragment = new BanAnFragment();
                tranHienThiBanAn.replace(R.id.content,banAnFragment);
                tranHienThiBanAn.commit();

                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.thucdon:
                FragmentTransaction tranHienThiThucDon = fragmentManager.beginTransaction();
                ThucDonFragment thucDonFragment = new ThucDonFragment();
                tranHienThiThucDon.replace(R.id.content,thucDonFragment);
                tranHienThiThucDon.commit();

                item.setChecked(true);
                drawerLayout.closeDrawers();

                break;
            case R.id.nhanvien:

                FragmentTransaction tranHienThiNhanVien = fragmentManager.beginTransaction();
                NhanVienFragment nhanVienFragment = new NhanVienFragment();
                tranHienThiNhanVien.replace(R.id.content,nhanVienFragment);
                tranHienThiNhanVien.commit();

                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.thongke:
                break;
            case R.id.caidat:
                break;
            case R.id.dangxuat:
                new AlertDialog.Builder(TrangChuActivity.this)
                        .setMessage("Bạn có muốn đăng xuất?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(TrangChuActivity.this,DangNhapActivity.class);
                                startActivity(intent);
                                Toast.makeText(TrangChuActivity.this,"Đăng xuất",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setNegativeButton("Không,Tôi không muốn", null)
                        .show();
                break;
        }
        return false;
    }

}
