package com.example.chunhat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    public SinhVienAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public SinhVienAdapter(@NonNull Context context, int resource, @NonNull List<SinhVien> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null)
        {
            LayoutInflater vi=LayoutInflater.from(getContext());
            v=vi.inflate(R.layout.item_sinhvien, null);
        }
        SinhVien sv=getItem(position);
        if(sv!=null)
        {
            TextView textViewHoTen=(TextView) v.findViewById(R.id.textViewHoTen);
            TextView textViewNgaySinh=(TextView) v.findViewById(R.id.textViewNgaySinh);
            TextView textViewDiem=(TextView) v.findViewById(R.id.textViewDiem);

            textViewHoTen.setText(sv.Ten);
            textViewNgaySinh.setText(sv.NgaySinh);
            textViewDiem.setText(sv.Diem.toString());
        }
        return v;
    }
}
