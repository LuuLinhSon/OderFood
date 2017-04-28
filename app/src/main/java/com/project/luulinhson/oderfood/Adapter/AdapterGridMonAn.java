package com.project.luulinhson.oderfood.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.luulinhson.oderfood.DTO.MonAnDTO;
import com.project.luulinhson.oderfood.R;

import java.net.URI;
import java.util.List;

/**
 * Created by Admin on 2/5/2017.
 */

public class AdapterGridMonAn extends BaseAdapter {

    Context context;
    int layout;
    List<MonAnDTO> monAnDTOList;
    ViewHolderMonAn viewHolderMonAn;

    public AdapterGridMonAn(Context context, int layout, List<MonAnDTO> monAnDTOList){
        this.context = context;
        this.layout = layout;
        this.monAnDTOList = monAnDTOList;
    }

    @Override
    public int getCount() {
        return monAnDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return monAnDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return monAnDTOList.get(position).getMamonan();
    }

    private class ViewHolderMonAn{
        ImageView immonan;
        TextView tvtenmonan;
        TextView tvgiamonan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolderMonAn = new ViewHolderMonAn();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolderMonAn.immonan = (ImageView) view.findViewById(R.id.immonan);
            viewHolderMonAn.tvtenmonan = (TextView) view.findViewById(R.id.tvtenmonan);
            viewHolderMonAn.tvgiamonan = (TextView) view.findViewById(R.id.tvgiamonan);

            view.setTag(viewHolderMonAn);
        }else {
            viewHolderMonAn = (ViewHolderMonAn) view.getTag();
        }

        MonAnDTO monAnDTO = monAnDTOList.get(position);

        String hinhanh = monAnDTO.getHinhanh().toString();
        if(hinhanh == null || hinhanh.equals("")){
            viewHolderMonAn.immonan.setImageResource(R.drawable.logorestaurant);
        }else {
            Uri uri = Uri.parse(hinhanh);
            viewHolderMonAn.immonan.setImageURI(uri);
        }

        Log.d("dulieu", "getView: " + monAnDTO.getTenmonan());

        viewHolderMonAn.tvtenmonan.setText(monAnDTO.getTenmonan());
        viewHolderMonAn.tvgiamonan.setText("Giá: " + monAnDTO.getGiatienmonan());

        return view;
    }
}
