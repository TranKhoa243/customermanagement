package com.example.quanlykhachhangadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvKhachHang;
    private ArrayList<KhachHang> khachHangArrayList;
    private KhachHangAdapter adapter;
    private Button btnMainSearch;
    private EditText edtMainSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();

        khachHangArrayList=new ArrayList<>();

        GetData();
        addEvents();
        adapter=new KhachHangAdapter(this,R.layout.custom_listview_item,khachHangArrayList);
        lvKhachHang.setAdapter(adapter);


    }

    private void addEvents() {
        btnMainSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keySearch=edtMainSearch.getText().toString().trim();
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myRef=database.getReference("DbKhachHang");
                if(TextUtils.isEmpty(keySearch)){
                    GetData();
                    return;
                }
                adapter.clear();
                Query query=myRef.orderByKey().equalTo(keySearch);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for (DataSnapshot data:dataSnapshot.getChildren()){
                                KhachHang khachHang = data.getValue(KhachHang.class);
                                khachHang.setIdTeamView(data.getKey());
                                adapter.add(khachHang);
                                Toast.makeText(getApplicationContext(),"Tìm dữ liệu thành công.",Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Nhập sai id team view , vui lòng nhập lại.",Toast.LENGTH_LONG).show();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("My tag","Erro search: "+databaseError.toString());
                    }
                });
            }
        });

    }

    private void addControls() {
        lvKhachHang=findViewById(R.id.lvKhachHang);
        btnMainSearch=findViewById(R.id.btnMainSearch);
        edtMainSearch=findViewById(R.id.edtMainSeatch);
    }

    private void GetData(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("DbKhachHang");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapter.clear();

                for (DataSnapshot data:dataSnapshot.getChildren()){
                    KhachHang khachHang=data.getValue(KhachHang.class);
                    khachHang.setIdTeamView(data.getKey());
                    adapter.add(khachHang);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Tải dữ liệu thất bại"+databaseError.toString(),Toast.LENGTH_LONG).show();
                Log.d("My tag","onCancelled: "+databaseError.toString());
            }
        });
    }

}