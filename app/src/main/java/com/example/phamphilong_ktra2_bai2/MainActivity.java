package com.example.phamphilong_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phamphilong_ktra2_bai2.model.LichThi;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btAdd, btAll, btGet, btGetSearch, btUpdate, btDelete;;
    private EditText txtId, txtName, txtGioBatDau, txtNgayThi, txtThiViet;
    private RecyclerView recycleView;
    private RecycleViewAdapter recycleViewAdapter;
    private SQLiteLichThi sqLiteLichThi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        btDelete.setEnabled(false);
        btUpdate.setEnabled(false);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleViewAdapter = new RecycleViewAdapter();
        recycleView.setAdapter(recycleViewAdapter);
        sqLiteLichThi = new SQLiteLichThi(this);

        btAdd.setOnClickListener(v -> {
            String name = txtName.getText().toString();

            try {
                String ngayThi = txtNgayThi.getText().toString();
                String gioBatDau = txtGioBatDau.getText().toString();
                String thiViet = txtThiViet.getText().toString();
                LichThi lichThi = new LichThi(name, ngayThi, gioBatDau, thiViet);
                sqLiteLichThi.addMonThi(lichThi);
            }catch (NumberFormatException e){
                System.out.println(e + "");
            }
        });

        btAll.setOnClickListener(v -> {
            List<LichThi> lichThis = sqLiteLichThi.getAllLichThi();
            recycleViewAdapter.setList(lichThis);
            recycleViewAdapter.notifyDataSetChanged();
            recycleView.setAdapter(recycleViewAdapter);
        });

        btGetSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textName = txtName.getText().toString().trim();
                if (textName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Bạn cần nhập tên cần search", Toast.LENGTH_SHORT).show();
                }else {
                    List<LichThi> lichThis = sqLiteLichThi.getListLichThiByName(textName);
                    if (lichThis.size()==0){
                        Toast.makeText(getApplicationContext(), "Không tìm thấy đối tượng nào", Toast.LENGTH_SHORT).show();
                    }
                    recycleViewAdapter.setList(lichThis);
                    recycleViewAdapter.notifyDataSetChanged();
                    recycleView.setAdapter(recycleViewAdapter);
                }
            }
        });

        btGet.setOnClickListener(v -> {
            try {
                int id = Integer.parseInt(txtId.getText().toString());
                LichThi lichThi = sqLiteLichThi.getLichThiById(id);
                if (lichThi == null){
                    Toast.makeText(getApplicationContext(), "Không Tìm Thấy Đối Tượng Bạn Yêu Cầu", Toast.LENGTH_SHORT).show();
                    txtId.setEnabled(true);
                    txtName.setText("");
                    txtGioBatDau.setText("");
                    txtGioBatDau.setText("");
                    txtThiViet.setText("");
                }else {
                    txtName.setText(lichThi.getTenMon());
                    txtGioBatDau.setText(lichThi.getGioBatDau());
                    txtNgayThi.setText(lichThi.getNgayThi());
                   txtThiViet.setText(lichThi.getThiViet());
                    txtId.setEnabled(false);
                }

                btDelete.setEnabled(true);
                btUpdate.setEnabled(true);
                btAdd.setEnabled(false);
            }catch (NumberFormatException e){
                System.out.println(e + "");
            }
        });

        btUpdate.setOnClickListener(v -> {
            try {
                int id = Integer.parseInt(txtId.getText().toString());
                String name = txtName.getText().toString();
                String ngayThi = txtNgayThi.getText().toString();
                String gioBatDau = txtGioBatDau.getText().toString();
                String thiViet = txtThiViet.getText().toString();
                LichThi lichThi = new LichThi(id,name, ngayThi, gioBatDau, thiViet);
                sqLiteLichThi.update(lichThi);

                btDelete.setEnabled(false);
                btUpdate.setEnabled(false);
                btAdd.setEnabled(true);
                txtId.setEnabled(true);
            }catch (NumberFormatException e){
                System.out.println(e + "");
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(txtId.getText().toString());
                sqLiteLichThi.deleteById(id);

                txtId.setText("");
                txtName.setText("");
                txtGioBatDau.setText("");
                txtNgayThi.setText("");
                txtThiViet.setText("");


                btDelete.setEnabled(false);
                btUpdate.setEnabled(false);
                btAdd.setEnabled(true);
                txtId.setEnabled(true);
            }
        });


        txtNgayThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenDay();
            }
        });
    }

    public void init(){
        btAdd = findViewById(R.id.add);
        btAll = findViewById(R.id.getAll);
        btGet = findViewById(R.id.getById);
        btUpdate = findViewById(R.id.update);
        btDelete = findViewById(R.id.delete);
        btGetSearch = findViewById(R.id.btSearch);

        txtId = findViewById(R.id.idLichThi);
        txtNgayThi = findViewById(R.id.idNgayThi);
        txtGioBatDau = findViewById(R.id.idGio);
        txtName = findViewById(R.id.nameMon);
        txtThiViet = findViewById(R.id.idThiViet);

        recycleView = findViewById(R.id.recycleview);
    }



    private void chosenDay(){

        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtNgayThi.setText(dayOfMonth+"/"+month+"/"+year);

            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
}