package com.project.luulinhson.oderfood.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.project.luulinhson.oderfood.Adapter.AdapterListViewNhanVien;
import com.project.luulinhson.oderfood.DAO.NhanVienDAO;
import com.project.luulinhson.oderfood.DTO.NhanVienDTO;
import com.project.luulinhson.oderfood.R;

import java.util.List;

/**
 * Created by Admin on 2/15/2017.
 */

public class NhanVienFragment extends Fragment {

    ListView lvhienthinhanvien;
    List<NhanVienDTO> nhanVienDTOList;
    NhanVienDAO nhanVienDAO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hien_thi_nhan_vien,container,false);

        lvhienthinhanvien = (ListView) view.findViewById(R.id.lvnhanvien);

        nhanVienDAO = new NhanVienDAO(getActivity());

        nhanVienDTOList = nhanVienDAO.LayTatCaNhanVien();

        AdapterListViewNhanVien adapterListViewNhanVien = new AdapterListViewNhanVien(getActivity(),R.layout.item_listview_hien_thi_nhan_vien,nhanVienDTOList);
        lvhienthinhanvien.setAdapter(adapterListViewNhanVien);
        adapterListViewNhanVien.notifyDataSetChanged();

        return view;
    }

}
