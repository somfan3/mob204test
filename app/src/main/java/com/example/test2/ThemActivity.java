package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThemActivity extends AppCompatActivity {
    EditText edtMa,edtTen,edtSoLuong,edtDonGia,edtNgayNhap;
    Database database;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        anhxa();
        database = new Database(this);

    }
    public void anhxa(){
        edtMa = findViewById(R.id.edtMa);
        edtTen = findViewById(R.id.edtTen);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        edtDonGia = findViewById(R.id.edtDonGia);
        edtNgayNhap = findViewById(R.id.edtNgayNhap);

    }
    public void chonNgay(View view) {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(ThemActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                edtNgayNhap.setText(sdf.format(calendar.getTime()));
            }
        },2020,9,29);
        datePickerDialog.show();
    }

    public void them(View view) throws ParseException {
        Sach sach = new Sach(edtMa.getText().toString(),
                edtTen.getText().toString(),
                Integer.parseInt(edtSoLuong.getText().toString()),
                Double.parseDouble(edtDonGia.getText().toString()),
                sdf.parse(edtNgayNhap.getText().toString()));
        if (database.insert(sach) < 0){

        }else{
            Toast.makeText(getApplicationContext(),"Them thanh cong",Toast.LENGTH_SHORT).show();
        }
    }
}