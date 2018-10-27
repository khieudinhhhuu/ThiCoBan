package com.khieuthichien.thicoban;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SinhvienAdapter extends BaseAdapter {

    private Context context;
    private int Layout;
    private List<Sinhvien> sinhvienList;
    private DatabaseHelper databaseHelper;

    public SinhvienAdapter(Context context, int layout, List<Sinhvien> sinhvienList, DatabaseHelper databaseHelper) {
        this.context = context;
        Layout = layout;
        this.sinhvienList = sinhvienList;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public int getCount() {
        return sinhvienList.size();
    }

    @Override
    public Object getItem(int position) {
        return sinhvienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sp, parent, false);

            holder = new ViewHolder();

            holder.tvmasv = convertView.findViewById(R.id.tvmasv);
            holder.tvmalop = convertView.findViewById(R.id.tvmalop);
            holder.tvdiemtoan = convertView.findViewById(R.id.tvdiemtoan);
            holder.tvdiemvan = convertView.findViewById(R.id.tvdiemvan);

            convertView.setTag(holder);
        }

        final Sinhvien sinhvien = sinhvienList.get(position);
        holder = (ViewHolder) convertView.getTag();
        holder.tvmasv.setText(sinhvien.getMasv());
        holder.tvmalop.setText(sinhvien.getMalop());
        holder.tvdiemtoan.setText(String.valueOf(sinhvien.getDiemtoan()));
        holder.tvdiemvan.setText(String.valueOf(sinhvien.getDiemvan()));


        return convertView;
    }


    public class ViewHolder{
        TextView tvmasv;
        TextView tvmalop;
        TextView tvdiemtoan;
        TextView tvdiemvan;
    }
}
