package com.project.luulinhson.oderfood.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.luulinhson.oderfood.Adapter.AdapterSpinLoaiMonAn;
import com.project.luulinhson.oderfood.DAO.LoaiMonAnDAO;
import com.project.luulinhson.oderfood.DAO.MonAnDAO;
import com.project.luulinhson.oderfood.DTO.LoaiMonAnDTO;
import com.project.luulinhson.oderfood.DTO.MonAnDTO;
import com.project.luulinhson.oderfood.R;

import java.io.IOException;
import java.util.List;

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnchonthemthucdon,btnthoatthemthucdon;
    ImageButton imbthemthucdon,imbadd;
    EditText edtenmonan,edgiatienmonan;
    Spinner spthemloaimonan;
    LoaiMonAnDAO loaiMonAnDAO;
    List<LoaiMonAnDTO> loaiMonAnDTOList;
    AdapterSpinLoaiMonAn adapterSpinLoaiMonAn;
    String sduongdanhinhanh;
    MonAnDAO monAnDAO;


    private final int REQUEST_THEMLOAIMONAN = 113;
    private final int REQUEST_THEMHINHANH = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thuc_don);

        btnchonthemthucdon = (Button) findViewById(R.id.btnchonthemthucdon);
        btnthoatthemthucdon = (Button) findViewById(R.id.btnthoatthemthucdon);
        imbthemthucdon = (ImageButton) findViewById(R.id.imbthemthucdon);
        imbadd = (ImageButton) findViewById(R.id.imbadd);
        edtenmonan = (EditText) findViewById(R.id.edtenmonan);
        edgiatienmonan = (EditText) findViewById(R.id.edgiatienmonan);
        spthemloaimonan = (Spinner) findViewById(R.id.spthemloaimonan);

        loaiMonAnDAO = new LoaiMonAnDAO(this);
        monAnDAO = new MonAnDAO(this);

        HienThiAdapterLoaiMonAn();

        btnchonthemthucdon.setOnClickListener(this);
        btnthoatthemthucdon.setOnClickListener(this);
        imbthemthucdon.setOnClickListener(this);
        imbadd.setOnClickListener(this);

    }

    private void HienThiAdapterLoaiMonAn(){
        loaiMonAnDTOList = loaiMonAnDAO.LayDanhSachLoaiMonAn();
        adapterSpinLoaiMonAn = new AdapterSpinLoaiMonAn(ThemThucDonActivity.this,R.layout.item_spinner_loai_mon_an,loaiMonAnDTOList);
        spthemloaimonan.setAdapter(adapterSpinLoaiMonAn);
        adapterSpinLoaiMonAn.notifyDataSetChanged();
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnchonthemthucdon:
                int vitri = spthemloaimonan.getSelectedItemPosition();
                int maloai = loaiMonAnDTOList.get(vitri).getMaloaimonan();
                String stenmonan = edtenmonan.getText().toString();
                String sgiatien = edgiatienmonan.getText().toString();
                if(!stenmonan.equals("") && !sgiatien.equals("")){
                    MonAnDTO monAnDTO = new MonAnDTO();
                    monAnDTO.setMaloaimonan(maloai);
                    monAnDTO.setTenmonan(stenmonan);
                    monAnDTO.setGiatienmonan(sgiatien);
                    monAnDTO.setHinhanh(sduongdanhinhanh);

                    boolean kiemtra = monAnDAO.ThemMonAn(monAnDTO);
                    if(kiemtra){
                        Toast.makeText(this,getResources().getString(R.string.themmonanthanhcong),Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this,getResources().getString(R.string.themmonanthatbai),Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(this,getResources().getString(R.string.vuilongdiendayduthongtin),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnthoatthemthucdon:
//                Intent intent = new Intent(ThemThucDonActivity.this,TrangChuActivity.class);
//                intent.putExtra("tendn","");
//                startActivity(intent);
//                getFragmentManager().popBackStack("hienthiloaimonan", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                finish();
                break;
            case R.id.imbadd:
                Intent iMoThemLoaiMonAn = new Intent(ThemThucDonActivity.this,ThemLoaiMonAnActivity.class);
                startActivityForResult(iMoThemLoaiMonAn,REQUEST_THEMLOAIMONAN);
                break;
            case R.id.imbthemthucdon:
                Intent iMoHinhAnh = new Intent();
                iMoHinhAnh.setType("image/*");
                iMoHinhAnh.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(iMoHinhAnh,"Chọn hình ảnh món ăn"),REQUEST_THEMHINHANH);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_THEMLOAIMONAN){
            if (resultCode == Activity.RESULT_OK){
                Intent dulieu = data;
                boolean kiemtra = dulieu.getBooleanExtra("kiemtraloaimonan",false);
                if(kiemtra){
                    HienThiAdapterLoaiMonAn();
                    Toast.makeText(this,getResources().getString(R.string.themloaimonanthanhcong),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,getResources().getString(R.string.themloaimonanthatbai),Toast.LENGTH_SHORT).show();
                }
            }
        }else if(requestCode == REQUEST_THEMHINHANH){
            if(resultCode == Activity.RESULT_OK && data != null){
                sduongdanhinhanh = data.getData().toString();
//                imbthemthucdon.setImageURI(data.getData());  // set image trực tiếp cho imagebutton bằng url,tuy nhiên làm thế này hình ảnh sẽ không choáng đầy màn hình
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                    Bitmap bm = Bitmap.createScaledBitmap(bitmap,500,700,false);
                    imbthemthucdon.setImageBitmap(bm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
