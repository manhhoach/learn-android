package com.example.chunhat;

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
    Button buttonGet, buttonCreate, buttonUpdate, buttonDelete, buttonSearch;
    ListView listViewSinhVien;
    EditText editTextSearch;
    MyDB mydb;
    SinhVienAdapter adapter;
    ArrayList<SinhVien> arrSinhVien;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        mydb=new MyDB(this);
        buttonCreate.setOnClickListener(v -> {
            Intent it=new Intent(MainActivity.this, MainActivityCreate.class);
            startActivityForResult(it, 200); // set request Code với màn hình create
        });

        buttonGet.setOnClickListener(v->{
            LoadData();
        });
        listViewSinhVien.setOnItemClickListener((parent, view, position, id) -> {
            i=position;

        });
        buttonUpdate.setOnClickListener(v->{
            Intent it=new Intent(MainActivity.this, MainActivityUpdate.class);
            // do đặc thù nút update, ta cần phải map data cũ sang màn hình update
            String ten=arrSinhVien.get(i).Ten;
            String ngaysinh=arrSinhVien.get(i).NgaySinh;
            Double diem=arrSinhVien.get(i).Diem;
            it.putExtra("TEN", ten);
            it.putExtra("NGAYSINH", ngaysinh);
            it.putExtra("DIEM", diem);
            startActivityForResult(it, 300);
        });
        buttonDelete.setOnClickListener(v->{
            Integer id= arrSinhVien.get(i).Id;
            long kq=mydb.delete(id);
            if(kq==-1)
            {
                Toast.makeText(MainActivity.this, "Can not delete", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                LoadData();
            }
        });

        buttonSearch.setOnClickListener(v->{
            String name=editTextSearch.getText().toString();
            ArrayList<SinhVien> sv=new ArrayList<SinhVien>();
            for(int i=0;i<arrSinhVien.toArray().length;i++)
            {
                if(arrSinhVien.get(i).Ten.contains(name)==true)
                {
                    sv.add(arrSinhVien.get(i));
                }
            }
            adapter=new SinhVienAdapter(MainActivity.this, R.layout.item_sinhvien, sv);
            listViewSinhVien.setAdapter(adapter);
        });
    }
    // để app mượt hơn, ta làm riêng 1 hàm load Data, và gọi mỗi khi update, create, delete để dữ liệu load lại tự động
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
            Double diem= cursor.getDouble(cursor.getColumnIndex(mydb.getDIEM()));

            arrSinhVien.add(new SinhVien( ten, ngaysinh,diem , id));
            adapter=new SinhVienAdapter(MainActivity.this, R.layout.item_sinhvien, arrSinhVien);
            listViewSinhVien.setAdapter(adapter);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200&&resultCode==201) // check đúng resultCode, requestCode để thực hiện tương ứng chức năng
        {
            // data sẽ là intent mang dữ liệu
            String ten=data.getStringExtra("TEN");
            String ngaysinh=data.getStringExtra("NGAYSINH");
            Double diem=data.getDoubleExtra("DIEM", 0);
            Toast.makeText(MainActivity.this, diem.toString(), Toast.LENGTH_LONG).show();
            long ketqua=mydb.insert(ten, ngaysinh, diem);
            if(ketqua==-1)
            {
                Toast.makeText(MainActivity.this, "Can not create", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Created", Toast.LENGTH_SHORT).show();
                LoadData();
            }
        }
        if(requestCode==300&&resultCode==301)
        {
            String ten=data.getStringExtra("TEN");
            String ngaysinh=data.getStringExtra("NGAYSINH");
            Double diem=data.getDoubleExtra("DIEM", 0);
            Integer id = arrSinhVien.get(i).Id;
            long ketqua=mydb.update(id, ten, ngaysinh, diem);
            if(ketqua==-1)
            {
                Toast.makeText(MainActivity.this, "Can not update", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                LoadData();
            }
        }
    }

    void Init()
    {
        buttonGet=(Button) findViewById(R.id.buttonGet);
        buttonCreate=(Button)findViewById(R.id.buttonCreate);
        buttonUpdate=(Button)findViewById(R.id.buttonUpdate);
        buttonDelete=(Button)findViewById(R.id.buttonDelete);
        listViewSinhVien=(ListView) findViewById(R.id.listViewSinhVien);
        buttonSearch=(Button) findViewById(R.id.buttonSearch);
        editTextSearch=(EditText) findViewById(R.id.editTextSearch);
    }
}