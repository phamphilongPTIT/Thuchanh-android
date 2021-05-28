package com.example.phamphilong_ktra2_bai2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phamphilong_ktra2_bai2.model.LichThi;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.OrderViewHolder> {

    private List<LichThi> list;

    public RecycleViewAdapter() {
      list = new ArrayList<>();
    }

    public List<LichThi> getList() {
        return list;
    }

    public void setList(List<LichThi> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater  inflater =LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.lichthi, parent, false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        LichThi lichThi = list.get(position);
        holder.idLichThi.setText("Mã Lịch Thi: "+String.valueOf(lichThi.getId()));
        holder.gioBatDau.setText(lichThi.getGioBatDau());
        holder.imgMon.setImageResource(R.drawable.java);
        holder.ngayThi.setText(lichThi.getNgayThi());
        holder.idTenMon.setText(lichThi.getTenMon());
        holder.thiViet.setText(lichThi.getThiViet());

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{

        private TextView idLichThi;
        private TextView idTenMon;
        private ImageView imgMon;
        private TextView ngayThi;
        private TextView gioBatDau;
        private TextView thiViet;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            idTenMon =itemView.findViewById(R.id.idName);
            imgMon = itemView.findViewById(R.id.idImg);
             ngayThi = itemView.findViewById(R.id.ngayThi);
            gioBatDau = itemView.findViewById(R.id.idGioBatDau);
            thiViet = itemView.findViewById(R.id.thiViet);
            idLichThi = itemView.findViewById(R.id.idThi);
        }
    }
}

