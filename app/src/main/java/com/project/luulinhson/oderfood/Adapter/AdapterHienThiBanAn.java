package com.project.luulinhson.oderfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.luulinhson.oderfood.Activity.ThanhToanActivity;
import com.project.luulinhson.oderfood.Activity.TrangChuActivity;
import com.project.luulinhson.oderfood.DAO.BanAnDAO;
import com.project.luulinhson.oderfood.DAO.GoiMonDAO;
import com.project.luulinhson.oderfood.DTO.BanAnDTO;
import com.project.luulinhson.oderfood.DTO.GoiMonDTO;
import com.project.luulinhson.oderfood.Fragment.BanAnFragment;
import com.project.luulinhson.oderfood.Fragment.ThucDonFragment;
import com.project.luulinhson.oderfood.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Admin on 1/20/2017.
 */

public class AdapterHienThiBanAn extends BaseAdapter implements View.OnClickListener{
    Context context;
    int layout;
    List<BanAnDTO> banAnDTOList;
    ViewHolderBanAn viewHolderBanAn;
    BanAnDAO banAnDAO;
    GoiMonDAO goiMonDAO;
    SharedPreferences sfmanhanvien;
    FragmentManager fragmentManager;

    public AdapterHienThiBanAn(Context context,int layout,List<BanAnDTO> banAnDTOList){
        this.context = context;
        this.layout = layout;
        this.banAnDTOList = banAnDTOList;

        banAnDAO = new BanAnDAO(context);
        goiMonDAO = new GoiMonDAO(context);
        fragmentManager = ((TrangChuActivity)context).getSupportFragmentManager();
    }

    @Override
    public int getCount() {
        return banAnDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return banAnDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return banAnDTOList.get(position).getMabanan();
    }


    public class ViewHolderBanAn{
        ImageView imBanAn,imGoiMon,imThanhToan,imAnButton;
        TextView tvBanAn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolderBanAn = new ViewHolderBanAn();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolderBanAn.tvBanAn = (TextView) view.findViewById(R.id.tvbanan);
            viewHolderBanAn.imBanAn = (ImageView) view.findViewById(R.id.imbanan);
            viewHolderBanAn.imThanhToan = (ImageView) view.findViewById(R.id.imthanhtoan);
            viewHolderBanAn.imGoiMon = (ImageView) view.findViewById(R.id.imgoimon);
            viewHolderBanAn.imAnButton = (ImageView) view.findViewById(R.id.imanbutton);

            view.setTag(viewHolderBanAn);

        }else {
            viewHolderBanAn = (ViewHolderBanAn) view.getTag();
        }

        if(banAnDTOList.get(position).isChecked()){
            viewHolderBanAn.imGoiMon.setVisibility(View.VISIBLE);
            viewHolderBanAn.imThanhToan.setVisibility(View.VISIBLE);
            viewHolderBanAn.imAnButton.setVisibility(View.VISIBLE);
        }else {
            viewHolderBanAn.imGoiMon.setVisibility(View.INVISIBLE);
            viewHolderBanAn.imThanhToan.setVisibility(View.INVISIBLE);
            viewHolderBanAn.imAnButton.setVisibility(View.INVISIBLE);
        }

        BanAnDTO banAnDTO = banAnDTOList.get(position);

        String tinhtrang = banAnDAO.LayTinhTrangBanAnTheoMa(banAnDTO.getMabanan());
        if(tinhtrang.equals("true")){
            viewHolderBanAn.imBanAn.setImageResource(R.drawable.banansangtrong);
        }else {
            viewHolderBanAn.imBanAn.setImageResource(R.drawable.banansangtrongfalse);
        }


        viewHolderBanAn.tvBanAn.setText(banAnDTO.getTenbanan());
        viewHolderBanAn.imBanAn.setTag(position);
        viewHolderBanAn.imGoiMon.setTag(position);
        viewHolderBanAn.imThanhToan.setTag(position);
        viewHolderBanAn.imAnButton.setTag(position);

        viewHolderBanAn.imBanAn.setOnClickListener(this);
        viewHolderBanAn.imGoiMon.setOnClickListener(this);
        viewHolderBanAn.imThanhToan.setOnClickListener(this);
        viewHolderBanAn.imAnButton.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        //int id = v.getId();
        //viewHolderBanAn = (ViewHolderBanAn) ((View)v.getParent()).getTag();

        int vitri = Integer.parseInt(v.getTag().toString());
        int maban = banAnDTOList.get(vitri).getMabanan();

        switch (v.getId()){
            case R.id.imbanan:

                /*
                String tenban = viewHolderBanAn.tvBanAn.getText().toString();
                int vitri = (int)  viewHolderBanAn.imBanAn.getTag();
                Toast.makeText(context,vitri + "",Toast.LENGTH_SHORT).show();
                banAnDTOList.get(vitri).setChecked(true);
                viewHolderBanAn.imGoiMon.setVisibility(View.VISIBLE);
                viewHolderBanAn.imThanhToan.setVisibility(View.VISIBLE);
                viewHolderBanAn.imAnButton.setVisibility(View.VISIBLE);
                */

                banAnDTOList.get(vitri).setChecked(true);
                notifyDataSetChanged();
                break;
            case R.id.imgoimon:
                /*
                int vitri1 = (int) viewHolderBanAn.imGoiMon.getTag();
                Toast.makeText(context,vitri1 + "",Toast.LENGTH_SHORT).show();
                */
                String tinhtrang = banAnDAO.LayTinhTrangBanAnTheoMa(maban);
                Intent imnvtrangchu = ((TrangChuActivity)context).getIntent();
                int manhanvien = imnvtrangchu.getIntExtra("manhanvien",0);


                if(tinhtrang.equals("false")){
//                     thêm bảng gọi món và cập nhật lại tình trạng bàn

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat fomat = new SimpleDateFormat("dd/MM/yyyy");
                    String ngaygoimon = fomat.format(calendar.getTime());

                    GoiMonDTO goiMonDTO = new GoiMonDTO();
                    goiMonDTO.setMaban(maban);

                    if(manhanvien != 0){
                        goiMonDTO.setManhanvien(manhanvien);
                        sfmanhanvien = context.getSharedPreferences("manhanvien",MODE_PRIVATE);
                        SharedPreferences.Editor ed = sfmanhanvien.edit();
                        ed.putInt("mnv",manhanvien);
                        ed.commit();
                    }else {
                        sfmanhanvien = context.getSharedPreferences("manhanvien",MODE_PRIVATE);
                        int mnv = sfmanhanvien.getInt("mnv",0);
                        goiMonDTO.setManhanvien(mnv);
                    }

                    goiMonDTO.setNgaygoimon(ngaygoimon);
                    goiMonDTO.setTinhtrang("false");

                    boolean kiemtra = goiMonDAO.ThemGoiMon(goiMonDTO);
                    banAnDAO.CapNhatTinhTrangBan(maban,"true");
                    notifyDataSetChanged();
                    if(!kiemtra){
                        Toast.makeText(context,"thêm thất bại",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,banAnDTOList.get(vitri).getTenbanan() +" có khách và bắt đầu gọi món",Toast.LENGTH_SHORT).show();
                    }
                }else {

                }

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ThucDonFragment thucDonFragment = new ThucDonFragment();
                Bundle bDuLieu = new Bundle();
                bDuLieu.putInt("maban",maban);

                thucDonFragment.setArguments(bDuLieu);
                fragmentTransaction.replace(R.id.content,thucDonFragment).addToBackStack("hienthibanan");
                fragmentTransaction.commit();

                break;
            case R.id.imthanhtoan:
                Intent iThanhToan = new Intent(context, ThanhToanActivity.class);
                iThanhToan.putExtra("maban",maban);
                context.startActivity(iThanhToan);
                ((TrangChuActivity) context).finish();

                break;
            case R.id.imanbutton:
                banAnDTOList.get(vitri).setChecked(false);
                notifyDataSetChanged();
                break;
        }

    }
}
