package com.example.kiemtracuoiky;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewSinhVien;
    Button buttonAdd, buttonSearch;
    EditText editTextSearchName;
    SinhVienAdapter adapter;
    MyDB mydb;

    ArrayList<SinhVien> arrSinhVien;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new MyDB(this);
        Init();
        LoadData();
        buttonAdd.setOnClickListener(v -> {
            Intent it=new Intent(MainActivity.this, MainActivityCreate.class);
            startActivityForResult(it, 200);
        });
        buttonSearch.setOnClickListener(v->{
            String nameSearch=editTextSearchName.getText().toString();
            ArrayList<SinhVien> res=new ArrayList<SinhVien>();
            for (int i = 0;i<arrSinhVien.toArray().length;i++)
            {
                if(arrSinhVien.get(i).Ten.contains(nameSearch))
                    res.add(arrSinhVien.get(i));
            }
            adapter=new SinhVienAdapter(MainActivity.this, R.layout.item_sinhvien, res);
            listViewSinhVien.setAdapter(adapter);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200&&resultCode==201)
        {
            String ten=data.getStringExtra("TEN");
            String ngaysinh=data.getStringExtra("NGAYSINH");
            long result=mydb.insert(ten, ngaysinh);
            if(result==-1)
            {
                Toast.makeText(MainActivity.this, "Can not create", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Created", Toast.LENGTH_SHORT).show();
                LoadData();
            }
        }
    }

    void Init()
    {
        listViewSinhVien=(ListView) findViewById(R.id.listViewSinhVien);
        buttonAdd=(Button)findViewById(R.id.buttonAdd);
        buttonSearch=(Button) findViewById(R.id.buttonSearch);
        editTextSearchName=(EditText) findViewById(R.id.editTextSearchName);
    }
    void LoadData()
    {
        arrSinhVien=new ArrayList<SinhVien>();
        mydb.openDb();
        Cursor cursor=mydb.get();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            Integer id=cursor.getInt(cursor.getColumnIndex(mydb.getID()));
            String ten=cursor.getString(cursor.getColumnIndex(mydb.getTEN()));
            String ngaysinh=cursor.getString(cursor.getColumnIndex(mydb.getNGAYSINH()));

            arrSinhVien.add(new SinhVien(id, ten, ngaysinh));
            adapter=new SinhVienAdapter(MainActivity.this, R.layout.item_sinhvien, arrSinhVien);
            listViewSinhVien.setAdapter(adapter);
        }
    }
}