package com.example.chunhat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivityCreate extends AppCompatActivity {
    Button buttonOk;
    EditText editTextTen, editTextNgaySinh, editTextDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create);
        Init();
        buttonOk.setOnClickListener(v -> {
            String name=editTextTen.getText().toString();
            String date=editTextNgaySinh.getText().toString();
            Double point = Double.parseDouble(editTextDiem.getText().toString());

            Intent it1=new Intent(MainActivityCreate.this, MainActivity.class);
            it1.putExtra("TEN", name);
            it1.putExtra("NGAYSINH", date);
            it1.putExtra("DIEM", point);
            //Toast.makeText(MainActivityCreate.this, name, Toast.LENGTH_LONG).show();
            setResult(201, it1); // set result Code với màn hình create
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