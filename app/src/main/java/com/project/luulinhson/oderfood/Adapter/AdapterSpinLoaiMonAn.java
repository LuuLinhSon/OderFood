package com.project.luulinhson.oderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.luulinhson.oderfood.DAO.LoaiMonAnDAO;
import com.project.luulinhson.oderfood.DTO.LoaiMonAnDTO;
import com.project.luulinhson.oderfood.R;

import java.util.List;

/**
 * Created by Admin on 1/31/2017.
 */

public class AdapterSpinLoaiMonAn extends BaseAdapter {

    Context context;
    int layout;
    List<LoaiMonAnDTO> loaiMonAnDTOList;
    ViewHolderLoaiMonAn viewHolderLoaiMonAn;

    public AdapterSpinLoaiMonAn(Context context, int layout, List<LoaiMonAnDTO> loaiMonAnDTOList){
        this.context = context;
        this.layout = layout;
        this.loaiMonAnDTOList = loaiMonAnDTOList;
    }

    @Override
    public int getCount() {
        return loaiMonAnDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiMonAnDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return loaiMonAnDTOList.get(position).getMaloaimonan();
    }

    public class ViewHolderLoaiMonAn{
        TextView tvtenloaimonan;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolderLoaiMonAn = new ViewHolderLoaiMonAn();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_spinner_loai_mon_an,parent,false);
            viewHolderLoaiMonAn.tvtenloaimonan = (TextView) view.findViewById(R.id.tvtenloaimonan);

            view.setTag(viewHolderLoaiMonAn);
        }else {
            viewHolderLoaiMonAn = (ViewHolderLoaiMonAn) view.getTag();
        }

        LoaiMonAnDTO loaiMonAnDTO = loaiMonAnDTOList.get(position);
        viewHolderLoaiMonAn.tvtenloaimonan.setText(loaiMonAnDTO.getTenloaimonan());
        viewHolderLoaiMonAn.tvtenloaimonan.setTag(loaiMonAnDTO.getMaloaimonan());


        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderLoaiMonAn = new ViewHolderLoaiMonAn();
            view = inflater.inflate(R.layout.item_spinner_loai_mon_an,parent,false);
            viewHolderLoaiMonAn.tvtenloaimonan = (TextView) view.findViewById(R.id.tvtenloaimonan);

            view.setTag(viewHolderLoaiMonAn);
        }else {
            viewHolderLoaiMonAn = (ViewHolderLoaiMonAn) view.getTag();
        }

        LoaiMonAnDTO loaiMonAnDTO = loaiMonAnDTOList.get(position);
        viewHolderLoaiMonAn.tvtenloaimonan.setText(loaiMonAnDTO.getTenloaimonan());
        viewHolderLoaiMonAn.tvtenloaimonan.setTag(loaiMonAnDTO.getMaloaimonan());


        return view;
    }
}
