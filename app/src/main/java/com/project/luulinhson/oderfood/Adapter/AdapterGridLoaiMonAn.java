package com.project.luulinhson.oderfood.Adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.luulinhson.oderfood.DAO.LoaiMonAnDAO;
import com.project.luulinhson.oderfood.DTO.LoaiMonAnDTO;
import com.project.luulinhson.oderfood.R;

import java.net.URI;
import java.util.List;

/**
 * Created by Admin on 2/2/2017.
 */

public class AdapterGridLoaiMonAn extends BaseAdapter {

    Context context;
    int layout;
    List<LoaiMonAnDTO> loaiMonAnDTOList;
    ViewHolderLoaiMonAnCoHinhAnh viewHolderLoaiMonAnCoHinhAnh;
    LoaiMonAnDAO loaiMonAnDAO;

    public AdapterGridLoaiMonAn(Context context, int layout, List<LoaiMonAnDTO> loaiMonAnDTOList){
        this.context = context;
        this.layout = layout;
        this.loaiMonAnDTOList = loaiMonAnDTOList;
        loaiMonAnDAO = new LoaiMonAnDAO(context);
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

    private class ViewHolderLoaiMonAnCoHinhAnh{
        ImageView imhinhanh;
        TextView tvtenloaimonan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolderLoaiMonAnCoHinhAnh = new ViewHolderLoaiMonAnCoHinhAnh();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolderLoaiMonAnCoHinhAnh.tvtenloaimonan = (TextView) view.findViewById(R.id.tvloaimonan);
            viewHolderLoaiMonAnCoHinhAnh.imhinhanh = (ImageView) view.findViewById(R.id.imloaimonan);

            view.setTag(viewHolderLoaiMonAnCoHinhAnh);
        }else {
            viewHolderLoaiMonAnCoHinhAnh = (ViewHolderLoaiMonAnCoHinhAnh) view.getTag();
        }
        LoaiMonAnDTO loaiMonAnDTO = loaiMonAnDTOList.get(position);

        int maloai = loaiMonAnDTO.getMaloaimonan();
        String hinhanh = loaiMonAnDAO.LayHinhAnhLoaiMonAn(maloai);

        Uri uri = Uri.parse(hinhanh);

        viewHolderLoaiMonAnCoHinhAnh.tvtenloaimonan.setText(loaiMonAnDTO.getTenloaimonan());
        viewHolderLoaiMonAnCoHinhAnh.imhinhanh.setImageURI(uri);

        return view;
    }
}
