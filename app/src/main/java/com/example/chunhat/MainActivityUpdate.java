package com.example.chunhat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivityUpdate extends AppCompatActivity {
    Button buttonOk;
    EditText editTextTen, editTextNgaySinh, editTextDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_update);
        Init();
        // ban đầu, ta cần hiển thị data cũ lên màn hình
        Intent x=getIntent();
        editTextTen.setText(x.getStringExtra("TEN"));
        Double d=x.getDoubleExtra("DIEM", 0);
        editTextDiem.setText(d.toString());
        editTextNgaySinh.setText(x.getStringExtra("NGAYSINH"));

        buttonOk.setOnClickListener(v -> {
            String name=editTextTen.getText().toString();
            String date=editTextNgaySinh.getText().toString();
            Double point = Double.parseDouble(editTextDiem.getText().toString());

            Intent it1=new Intent(MainActivityUpdate.this, MainActivity.class);
            it1.putExtra("TEN", name);
            it1.putExtra("NGAYSINH", date);
            it1.putExtra("DIEM", point);
            setResult(301, it1);
            finish();
        });
    }
    void Init()
    {
        buttonOk=(Button) findViewById(R.id.buttonOk);
        editTextTen=(EditText)findViewById(R.id.editTextTen);
        editTextNgaySinh=(EditText)findViewById(R.id.editTextNgaySinh);
        editTextDiem=(EditText)findViewById(R.id.editTextDiem);


    }
}