package com.example.kiemtracuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivityCreate extends AppCompatActivity {
    Button buttonOK;
    EditText editTextTen, editTextNgaySinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create);
        buttonOK=(Button) findViewById(R.id.buttonOK);
        editTextTen=(EditText) findViewById(R.id.editTextTen);
        editTextNgaySinh=(EditText) findViewById(R.id.editTextNgaySinh);

        buttonOK.setOnClickListener(v->{
            String ten=editTextTen.getText().toString();
            String ngaysinh =editTextNgaySinh.getText().toString();
            Intent it1=new Intent(MainActivityCreate.this, MainActivity.class);
            it1.putExtra("TEN", ten);
            it1.putExtra("NGAYSINH", ngaysinh);
            setResult(201, it1);
            finish();
        });

    }
}