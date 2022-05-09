package com.example.quanlykhachhangadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SuaKhachHangActivity extends AppCompatActivity {

    private EditText edtIdTeamView,edtName,edtAddress,edtPhoneNumber,edtEmail,edtPassTeamView;
    private Button btnCapNhat,btnHuy,btnTroVe;
    private KhachHang khachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_khach_hang);
        addControls();
        addEvents();
    }

    private void addControls() {
        edtIdTeamView=findViewById(R.id.edtSuaIdTeamView);
        edtName=findViewById(R.id.edtSuaName);
        edtAddress=findViewById(R.id.edtSuaAddress);
        edtPhoneNumber=findViewById(R.id.edtSuaPhoneNumber);
        edtEmail=findViewById(R.id.edtSuaEmail);
        edtPassTeamView=findViewById(R.id.edtSuaPassTeamView);

        btnCapNhat=findViewById(R.id.btnSuaCapNhat);
        btnHuy=findViewById(R.id.btnSuaHuy);
        btnTroVe=findViewById(R.id.btnSuaTroVe);

        Intent intent=getIntent();
        khachHang= (KhachHang) intent.getSerializableExtra("KHACHHANG");
        Log.d("MyTag",khachHang.toString());

        if(khachHang!=null){
            edtIdTeamView.setText(khachHang.getIdTeamView()+"");
            edtName.setText(khachHang.getName()+"");
            edtAddress.setText(khachHang.getAddress()+"");
            edtPhoneNumber.setText(khachHang.getPhoneNumber()+"");
            edtEmail.setText(khachHang.getEmail()+"");
            edtPassTeamView.setText(khachHang.getPassTeamView()+"");
        }
        else {
            Toast.makeText(this,"Lỗi khi load dữ liệu khách hàng",Toast.LENGTH_LONG).show();
        }
    }

    private void addEvents() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(khachHang!=null){
                    edtIdTeamView.setText(khachHang.getIdTeamView()+"");
                    edtName.setText(khachHang.getName()+"");
                    edtAddress.setText(khachHang.getAddress()+"");
                    edtPhoneNumber.setText(khachHang.getPhoneNumber()+"");
                    edtEmail.setText(khachHang.getEmail()+"");
                    edtPassTeamView.setText(khachHang.getPassTeamView()+"");
                }
                else {
                    Toast.makeText(getApplicationContext(),"Lỗi khi load dữ liệu khách hàng",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTeamView=edtIdTeamView.getText().toString();
                String name=edtName.getText().toString();
                String address=edtAddress.getText().toString();
                String phoneNumber=edtPhoneNumber.getText().toString();
                String email=edtEmail.getText().toString();
                String passTeamView=edtPassTeamView.getText().toString();

                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myRef=database.getReference("DbKhachHang");
                myRef.child(idTeamView).child("address").setValue(address);
                myRef.child(idTeamView).child("email").setValue(email);
                myRef.child(idTeamView).child("name").setValue(name);
                myRef.child(idTeamView).child("passTeamView").setValue(passTeamView);
                myRef.child(idTeamView).child("phoneNumber").setValue(phoneNumber);
                finish();
                Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_LONG).show();
            }
        });
    }
}