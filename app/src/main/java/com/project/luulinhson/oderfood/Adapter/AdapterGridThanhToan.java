package com.project.luulinhson.oderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.luulinhson.oderfood.DTO.ThanhToanDTO;
import com.project.luulinhson.oderfood.R;

import java.util.List;

/**
 * Created by Admin on 2/13/2017.
 */

public class AdapterGridThanhToan extends BaseAdapter {

    Context context;
    int layout;
    List<ThanhToanDTO> thanhToanDTOList;
    ViewHolderThanhToan viewHolderThanhToan;

    public AdapterGridThanhToan(Context context, int layout, List<ThanhToanDTO> thanhToanDTOList){
        this.context = context;
        this.layout = layout;
        this.thanhToanDTOList = thanhToanDTOList;
    }



    @Override
    public int getCount() {
        return thanhToanDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return thanhToanDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolderThanhToan{
        TextView tvtenmonanthanhtoan,tvsoluongthanhtoan,tvgiatienthanhtoan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolderThanhToan = new ViewHolderThanhToan();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolderThanhToan.tvtenmonanthanhtoan = (TextView) view.findViewById(R.id.tvtenmonanthanhtoan);
            viewHolderThanhToan.tvsoluongthanhtoan = (TextView) view.findViewById(R.id.tvsoluongthanhtoan);
            viewHolderThanhToan.tvgiatienthanhtoan = (TextView) view.findViewById(R.id.tvgiatienthanhtoan);

            view.setTag(viewHolderThanhToan);
        }else {
            viewHolderThanhToan = (ViewHolderThanhToan) view.getTag();
        }

        ThanhToanDTO thanhToanDTO = thanhToanDTOList.get(position);
        viewHolderThanhToan.tvtenmonanthanhtoan.setText(thanhToanDTO.getTenmonanthanhtoan());
        viewHolderThanhToan.tvsoluongthanhtoan.setText(String.valueOf(thanhToanDTO.getSoluong()));
        viewHolderThanhToan.tvgiatienthanhtoan.setText(String.valueOf(thanhToanDTO.getGiatien()));

        return view;
    }
}
