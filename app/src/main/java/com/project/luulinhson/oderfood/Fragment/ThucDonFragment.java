package com.project.luulinhson.oderfood.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.project.luulinhson.oderfood.Activity.ThemBanAnActivity;
import com.project.luulinhson.oderfood.Activity.ThemThucDonActivity;
import com.project.luulinhson.oderfood.Activity.TrangChuActivity;
import com.project.luulinhson.oderfood.Adapter.AdapterGridLoaiMonAn;
import com.project.luulinhson.oderfood.Adapter.AdapterHienThiBanAn;
import com.project.luulinhson.oderfood.DAO.LoaiMonAnDAO;
import com.project.luulinhson.oderfood.DTO.LoaiMonAnDTO;
import com.project.luulinhson.oderfood.R;

import java.util.List;

/**
 * Created by Admin on 1/25/2017.
 */

public class ThucDonFragment extends Fragment {

    GridView gvhientiloaimonan;
    List<LoaiMonAnDTO> loaiMonAnDTOList;
    LoaiMonAnDAO loaiMonAnDAO;
    FragmentManager fragmentManager;
    int maban;
    int maloaimonan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hien_thi_thuc_don,container,false);
        setHasOptionsMenu(true);

        loaiMonAnDAO = new LoaiMonAnDAO(getContext());

        fragmentManager = getActivity().getSupportFragmentManager();

        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.themthucdon);

        gvhientiloaimonan = (GridView) view.findViewById(R.id.gvhienthiloaimonan);



        loaiMonAnDTOList = loaiMonAnDAO.LayDanhSachLoaiMonAn();

        AdapterGridLoaiMonAn adapterGridLoaiMonAn = new AdapterGridLoaiMonAn(getActivity(),R.layout.item_gridview_hien_thi_loai_mon_an,loaiMonAnDTOList);
        gvhientiloaimonan.setAdapter(adapterGridLoaiMonAn);
        adapterGridLoaiMonAn.notifyDataSetChanged();

        Bundle bDuLieu = getArguments();
        if(bDuLieu != null){
            maban = bDuLieu.getInt("maban");

        }else {
            registerForContextMenu(gvhientiloaimonan);
        }

        gvhientiloaimonan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int maloai = loaiMonAnDTOList.get(position).getMaloaimonan();

                MonAnFragment monAnFragment = new MonAnFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("maloai",maloai);
                bundle.putInt("maban",maban);

                monAnFragment.setArguments(bundle);

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content,monAnFragment).addToBackStack("hienthiloaimonan");
                transaction.commit();

            }
        });

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    getFragmentManager().popBackStack("hienthibanan", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        getActivity().getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int vitri = contextMenuInfo.position;
        maloaimonan = loaiMonAnDTOList.get(vitri).getMaloaimonan();
        switch (id){
            case R.id.itSua:
                Toast.makeText(getActivity(),"Không thể sửa loại món ăn.Vui lòng xóa và thêm món loại món ăn mới",Toast.LENGTH_SHORT).show();
                break;

            case R.id.itXoa:
                new AlertDialog.Builder(getActivity())
                        .setMessage(loaiMonAnDTOList.get(vitri).getTenloaimonan() + ".Bạn có muốn xóa loại món ăn này?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                boolean kiemtra1 = loaiMonAnDAO.XoaLoaiMonAn(maloaimonan);
                                boolean kiemtra2 = loaiMonAnDAO.XoaMonAn(maloaimonan);
                                if(kiemtra1 && kiemtra2){
                                    loaiMonAnDTOList = loaiMonAnDAO.LayDanhSachLoaiMonAn();

                                    AdapterGridLoaiMonAn adapterGridLoaiMonAn = new AdapterGridLoaiMonAn(getActivity(),R.layout.item_gridview_hien_thi_loai_mon_an,loaiMonAnDTOList);
                                    gvhientiloaimonan.setAdapter(adapterGridLoaiMonAn);
                                    adapterGridLoaiMonAn.notifyDataSetChanged();

                                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.xoathanhcong),Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.loi),Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Không,Tôi không muốn", null)
                        .show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemBanAn = menu.add(1,R.id.itthemthucdon,1,R.string.themthucdon);
        itThemBanAn.setIcon(R.drawable.thembanan);
        itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itthemthucdon:
                Intent iThemBanAn = new Intent(getActivity(), ThemThucDonActivity.class);
                startActivity(iThemBanAn);
                break;
        }
        return true;
    }
}
