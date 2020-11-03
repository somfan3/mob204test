package com.example.test2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<Sach> sachList;
    Database database;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public Adapter(Context context, List<Sach> sachList) {
        this.context = context;
        this.sachList = sachList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        database = new Database(context);
        Sach sach = sachList.get(position);

        holder.tvMa.setText("Ma : "+sach.getMa());
        holder.tvTen.setText("Ten : "+sach.getTen());
        holder.tvSoLuong.setText("So Luong : "+sach.getSoLuong());
        holder.tvGia.setText("Don Gia : "+sach.getDonGia());
        holder.tvThanhTien.setText("Thanh Tien : " + (sach.getDonGia() * sach.getSoLuong()));
        holder.tvNgayNhap.setText("Ngay Nhap Hang : "+sdf.format(sach.getNgayNhap()));

    }

    @Override
    public int getItemCount() {
        return sachList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMa,tvTen,tvSoLuong,tvGia,tvThanhTien,tvNgayNhap;
        Button btnSua,btnXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMa = itemView.findViewById(R.id.tvMa);
            tvTen = itemView.findViewById(R.id.tvTen);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            tvGia = itemView.findViewById(R.id.tvDonGia);
            tvThanhTien = itemView.findViewById(R.id.tvThanhTien);
            tvNgayNhap = itemView.findViewById(R.id.tvNgayNhap);
            btnSua = itemView.findViewById(R.id.btnSua);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }
    public void setDataChange(List<Sach> items){
        sachList = items;
        notifyDataSetChanged();
    }
}
