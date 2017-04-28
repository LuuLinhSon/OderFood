package com.project.luulinhson.oderfood.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.luulinhson.oderfood.Adapter.AdapterGridThanhToan;
import com.project.luulinhson.oderfood.DAO.BanAnDAO;
import com.project.luulinhson.oderfood.DAO.GoiMonDAO;
import com.project.luulinhson.oderfood.DTO.ThanhToanDTO;
import com.project.luulinhson.oderfood.Fragment.BanAnFragment;
import com.project.luulinhson.oderfood.R;

import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener{

    GridView gvhienthithanhtoan;
    TextView tvtongtien;
    Button btnthanhtoan,btnthoatthanhtoan;
    GoiMonDAO goiMonDAO;
    BanAnDAO banAnDAO;
    AdapterGridThanhToan adapterGridThanhToan;
    List<ThanhToanDTO> thanhToanDTOList;
    long tongtien;
    int maban;
    int magoimon;
    FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        goiMonDAO = new GoiMonDAO(this);
        banAnDAO = new BanAnDAO(this);

        fragmentManager = getSupportFragmentManager();

        gvhienthithanhtoan = (GridView) findViewById(R.id.gvhienthithanhtoan);
        tvtongtien = (TextView) findViewById(R.id.tvtongtien);
        btnthanhtoan = (Button) findViewById(R.id.btnthanhtoan);
        btnthoatthanhtoan = (Button) findViewById(R.id.btnthoatthanhtoan);

        btnthanhtoan.setOnClickListener(this);
        btnthoatthanhtoan.setOnClickListener(this);

        Intent iThanhToan = getIntent();
        maban = iThanhToan.getIntExtra("maban",0);
        if(maban != 0){

            HienThiThanhToan();

            for (int i = 0; i < thanhToanDTOList.size();i++){
                int soluong = thanhToanDTOList.get(i).getSoluong();
                int giatien = thanhToanDTOList.get(i).getGiatien();

                tongtien += (soluong*giatien);
            }

            tvtongtien.setText(tongtien + "");
        }
    }

    private void HienThiThanhToan(){
        magoimon = goiMonDAO.LayMaGoiMonTheoMaBan(maban,"false");
        thanhToanDTOList = goiMonDAO.LayDanhSachMonAnTheoMaGoiMon(magoimon);

        adapterGridThanhToan = new AdapterGridThanhToan(this,R.layout.item_gridview_hien_thi_thanh_toan,thanhToanDTOList);
        gvhienthithanhtoan.setAdapter(adapterGridThanhToan);
        adapterGridThanhToan.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnthanhtoan:
                boolean kiemtratinhtrangban = banAnDAO.CapNhatTinhTrangBan(maban,"false");
                boolean kiemtratinhtranggoimon = goiMonDAO.CapNhatLaiTinhTrangGoiMonTheoMaBan(maban,"true");

                if(kiemtratinhtrangban && kiemtratinhtranggoimon){
                    Toast.makeText(this,"Thanh toán thành công",Toast.LENGTH_SHORT).show();
                    HienThiThanhToan();

                }else {
                    Toast.makeText(this,"Lỗi!",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnthoatthanhtoan:
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                BanAnFragment banAnFragment = new BanAnFragment();
//                fragmentTransaction.replace(R.id.content,banAnFragment);
//                fragmentTransaction.commit();
                Intent intent = new Intent(this,TrangChuActivity.class);
                intent.putExtra("tendn","");
                startActivity(intent);
                finish();
                break;
        }
    }


}
