package com.project.luulinhson.oderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.luulinhson.oderfood.DTO.NhanVienDTO;
import com.project.luulinhson.oderfood.R;

import java.util.List;

/**
 * Created by Admin on 2/15/2017.
 */

public class AdapterListViewNhanVien extends BaseAdapter {

    Context context;
    int layout;
    List<NhanVienDTO> nhanVienDTOList;
    ViewHolderNhanVien viewHolderNhanVien;

    public AdapterListViewNhanVien(Context context, int layout, List<NhanVienDTO> nhanVienDTOList){
        this.context = context;
        this.layout = layout;
        this.nhanVienDTOList = nhanVienDTOList;

    }

    @Override
    public int getCount() {
        return nhanVienDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return nhanVienDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return nhanVienDTOList.get(position).getManhanvien();
    }

    public class ViewHolderNhanVien{
        TextView tvhienthitennhanvien,tvhienthigioitinh,tvhienthicmnd;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolderNhanVien = new ViewHolderNhanVien();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolderNhanVien.tvhienthitennhanvien = (TextView) view.findViewById(R.id.tvhienthitennhanvien);
            viewHolderNhanVien.tvhienthigioitinh = (TextView) view.findViewById(R.id.tvhienthigiotinh);
            viewHolderNhanVien.tvhienthicmnd = (TextView) view.findViewById(R.id.tvhienthicmnd);

            view.setTag(viewHolderNhanVien);
        }else {
            viewHolderNhanVien = (ViewHolderNhanVien) view.getTag();
        }

        NhanVienDTO nhanVienDTO = nhanVienDTOList.get(position);

        viewHolderNhanVien.tvhienthitennhanvien.setText(nhanVienDTO.getTendn());
        viewHolderNhanVien.tvhienthigioitinh.setText(nhanVienDTO.getGioitinh());
        viewHolderNhanVien.tvhienthicmnd.setText(nhanVienDTO.getCmnd());

        return view;
    }
}
