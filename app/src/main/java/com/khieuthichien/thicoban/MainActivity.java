package com.khieuthichien.thicoban;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtmasinhvien;
    private EditText edtmalop;
    private EditText edtdiemtoan;
    private EditText edtdiemvan;
    private Button btndiemtheolop;
    private TextView tvdiempt;
    private TextView tvdiemph;
    private Button btndiemtheomon;
    private TextView tvdiemtoan;
    private TextView tvdiemvan;
    private ListView lvsp;

    DatabaseHelper databaseHelper;
    List<Sinhvien> sinhvienList;
    SinhvienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        sinhvienList = new ArrayList<>();

        edtmasinhvien = findViewById(R.id.edtmasinhvien);
        edtmalop = findViewById(R.id.edtmalop);
        edtdiemtoan = findViewById(R.id.edtdiemtoan);
        edtdiemvan = findViewById(R.id.edtdiemvan);
        btndiemtheolop = findViewById(R.id.btndiemtheolop);
        tvdiempt = findViewById(R.id.tvdiempt);
        tvdiemph = findViewById(R.id.tvdiemph);
        btndiemtheomon = findViewById(R.id.btndiemtheomon);
        tvdiemtoan = findViewById(R.id.tvdiemtoan);
        tvdiemvan = findViewById(R.id.tvdiemvan);
        lvsp = findViewById(R.id.lvsp);

        sinhvienList = databaseHelper.getAllSanpham();

        adapter = new SinhvienAdapter(this, R.layout.item_sp, sinhvienList, databaseHelper);
        lvsp.setAdapter(adapter);

        btndiemtheolop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String masv = edtmasinhvien.getText().toString().trim();
                String malop = edtmalop.getText().toString().trim();
                int diemtoan = Integer.parseInt(edtdiemtoan.getText().toString().trim());
                int diemvan = Integer.parseInt(edtdiemvan.getText().toString().trim());

                Sinhvien sinhvien = new Sinhvien();
                sinhvien.setMasv(masv);
                sinhvien.setMalop(malop);
                sinhvien.setDiemtoan(diemtoan);
                sinhvien.setDiemvan(diemvan);

                databaseHelper.insertSinhvien(sinhvien);
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                databaseHelper.getAllSanpham();
                Toast.makeText(MainActivity.this, "da thêm", Toast.LENGTH_SHORT).show();

            }
        });

        btndiemtheomon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
