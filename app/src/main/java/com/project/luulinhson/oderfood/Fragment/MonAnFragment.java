package com.project.luulinhson.oderfood.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.project.luulinhson.oderfood.Activity.SuaTenMonAnActivity;
import com.project.luulinhson.oderfood.Activity.ThemSoLuongActivity;
import com.project.luulinhson.oderfood.Adapter.AdapterGridLoaiMonAn;
import com.project.luulinhson.oderfood.Adapter.AdapterGridMonAn;
import com.project.luulinhson.oderfood.Adapter.AdapterHienThiBanAn;
import com.project.luulinhson.oderfood.DAO.MonAnDAO;
import com.project.luulinhson.oderfood.DTO.MonAnDTO;
import com.project.luulinhson.oderfood.R;

import java.util.List;

/**
 * Created by Admin on 2/5/2017.
 */

public class MonAnFragment extends Fragment {

    GridView gridView;
    MonAnDAO monAnDAO;
    List<MonAnDTO> monAnDTOList;
    AdapterGridMonAn adapterGridMonAn;
    int maban;
    int maloai;
    int mamonan;

    public int RESQUES_CODE_SUA_MON_AN = 116;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.hien_thi_thuc_don,container,false);
        monAnDAO = new MonAnDAO(getActivity());

        gridView = (GridView) view.findViewById(R.id.gvhienthiloaimonan);




        Bundle bundle = getArguments();
        if(bundle != null){
            maloai = bundle.getInt("maloai");
            maban = bundle.getInt("maban",0);

            if(maban == 0){
                registerForContextMenu(gridView);
            }

            monAnDTOList = monAnDAO.LayTatCaMonAn(maloai);

            adapterGridMonAn = new AdapterGridMonAn(getActivity(),R.layout.item_gridview_hien_thi_mon_an,monAnDTOList);
            gridView.setAdapter(adapterGridMonAn);
            adapterGridMonAn.notifyDataSetChanged();

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(maban != 0){
                        Intent intent = new Intent(getActivity(), ThemSoLuongActivity.class);
                        intent.putExtra("maban",maban);
                        intent.putExtra("mamonan",monAnDTOList.get(position).getMamonan());
                        startActivity(intent);
                    }

                }
            });
        }

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    getFragmentManager().popBackStack("hienthiloaimonan", FragmentManager.POP_BACK_STACK_INCLUSIVE);
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
        mamonan = monAnDTOList.get(vitri).getMamonan();
        switch (id){
            case R.id.itSua:
                Intent iSuaMonAn = new Intent(getActivity(), SuaTenMonAnActivity.class);
                iSuaMonAn.putExtra("mamonan",mamonan);
                startActivityForResult(iSuaMonAn,RESQUES_CODE_SUA_MON_AN);
                break;

            case R.id.itXoa:
                new AlertDialog.Builder(getActivity())
                        .setMessage(monAnDTOList.get(vitri).getTenmonan() + ".Bạn có muốn xóa loại món ăn này?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                boolean kiemtra = monAnDAO.XoaMonAn(mamonan);
                                if(kiemtra){
                                    monAnDTOList = monAnDAO.LayTatCaMonAn(maloai);

                                    adapterGridMonAn = new AdapterGridMonAn(getActivity(),R.layout.item_gridview_hien_thi_mon_an,monAnDTOList);
                                    gridView.setAdapter(adapterGridMonAn);
                                    adapterGridMonAn.notifyDataSetChanged();

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESQUES_CODE_SUA_MON_AN){
            if(resultCode == Activity.RESULT_OK){
                Intent intent = data;
                boolean kiemtra = intent.getBooleanExtra("kiemtra",false);
                if(kiemtra){
                    monAnDTOList = monAnDAO.LayTatCaMonAn(maloai);

                    adapterGridMonAn = new AdapterGridMonAn(getActivity(),R.layout.item_gridview_hien_thi_mon_an,monAnDTOList);
                    gridView.setAdapter(adapterGridMonAn);
                    adapterGridMonAn.notifyDataSetChanged();
                    Toast.makeText(getActivity(),getResources().getString(R.string.suathanhcong),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.loi),Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
