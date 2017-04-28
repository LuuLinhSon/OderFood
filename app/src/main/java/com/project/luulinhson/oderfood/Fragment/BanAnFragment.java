package com.project.luulinhson.oderfood.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.project.luulinhson.oderfood.Activity.SuaTenBanAnActivity;
import com.project.luulinhson.oderfood.Activity.ThemBanAnActivity;
import com.project.luulinhson.oderfood.Activity.TrangChuActivity;
import com.project.luulinhson.oderfood.Adapter.AdapterHienThiBanAn;
import com.project.luulinhson.oderfood.DAO.BanAnDAO;
import com.project.luulinhson.oderfood.DTO.BanAnDTO;
import com.project.luulinhson.oderfood.R;

import org.xml.sax.DTDHandler;

import java.util.List;

/**
 * Created by Admin on 1/17/2017.
 */

public class BanAnFragment extends Fragment {

    public int RESQUES_CODE_THEM = 111;
    public int RESQUES_CODE_SUA = 112;
    GridView gvhienthibanan;
    List<BanAnDTO> banAnDTOList;
    BanAnDAO banAnDAO;
    AdapterHienThiBanAn adapterHienThiBanAn;
    int maban;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hien_thi_ban_an,container,false);
        setHasOptionsMenu(true);
        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.trangchu);

        gvhienthibanan = (GridView) view.findViewById(R.id.gvhienthibanan);

        banAnDAO = new BanAnDAO(getActivity());

        banAnDTOList = banAnDAO.LayTatCaBanAn();
        adapterHienThiBanAn = new AdapterHienThiBanAn(getActivity(),R.layout.item_gridview_hien_thi_ban_an,banAnDTOList);
        gvhienthibanan.setAdapter(adapterHienThiBanAn);
        adapterHienThiBanAn.notifyDataSetChanged();

        registerForContextMenu(gvhienthibanan);

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
        maban = banAnDTOList.get(vitri).getMabanan();
        switch (id){
            case R.id.itSua:
                Intent iSua = new Intent(getActivity(), SuaTenBanAnActivity.class);
                iSua.putExtra("maban",maban);
                startActivityForResult(iSua,RESQUES_CODE_SUA);

                break;

            case R.id.itXoa:
                new AlertDialog.Builder(getActivity())
                        .setMessage(banAnDTOList.get(vitri).getTenbanan() + ".Bạn có muốn xóa bàn ăn này?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                boolean kiemtra = banAnDAO.XoaBanAn(maban);
                                if(kiemtra){
                                    banAnDTOList = banAnDAO.LayTatCaBanAn();
                                    adapterHienThiBanAn = new AdapterHienThiBanAn(getActivity(),R.layout.item_gridview_hien_thi_ban_an,banAnDTOList);
                                    gvhienthibanan.setAdapter(adapterHienThiBanAn);
                                    adapterHienThiBanAn.notifyDataSetChanged();

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
        MenuItem itThemBanAn = menu.add(1,R.id.itthembanan,1,R.string.thembanan);
        itThemBanAn.setIcon(R.drawable.thembanan);
        itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itthembanan:
                Intent iThemBanAn = new Intent(getActivity(), ThemBanAnActivity.class);
                startActivityForResult(iThemBanAn,RESQUES_CODE_THEM);
                break;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESQUES_CODE_THEM){
            if(resultCode == Activity.RESULT_OK){
                Intent intent = data;
                boolean kiemtra = intent.getBooleanExtra("kiemtra",false);
                if(kiemtra){
                    banAnDTOList = banAnDAO.LayTatCaBanAn();
                    adapterHienThiBanAn = new AdapterHienThiBanAn(getActivity(),R.layout.item_gridview_hien_thi_ban_an,banAnDTOList);
                    gvhienthibanan.setAdapter(adapterHienThiBanAn);
                    adapterHienThiBanAn.notifyDataSetChanged();
                    Toast.makeText(getActivity(),getResources().getString(R.string.thembananthanhcong),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.thembananthatbai),Toast.LENGTH_SHORT).show();
                }
            }
        }else if (requestCode == RESQUES_CODE_SUA){
            if(resultCode == Activity.RESULT_OK){
                Intent intent = data;
                boolean kiemtra = intent.getBooleanExtra("kiemtra",false);
                if(kiemtra){
                    banAnDTOList = banAnDAO.LayTatCaBanAn();
                    adapterHienThiBanAn = new AdapterHienThiBanAn(getActivity(),R.layout.item_gridview_hien_thi_ban_an,banAnDTOList);
                    gvhienthibanan.setAdapter(adapterHienThiBanAn);
                    adapterHienThiBanAn.notifyDataSetChanged();

                    Toast.makeText(getActivity(),getResources().getString(R.string.suathanhcong),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.loi),Toast.LENGTH_SHORT).show();
                }

            }
            }
    }
}
