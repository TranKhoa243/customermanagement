package com.example.quanlykhachhangadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThemKhActivity extends AppCompatActivity {

    private EditText edtIdTeamView,edtName,edtAddress,edtPhoneNumber,edtEmail,edtPassTeamView;
    private Button btnThem,btnHuy,btnTroVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_kh);
        addControls();
        addEvents();
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
                edtIdTeamView.setText("");
                edtName.setText("");
                edtAddress.setText("");
                edtPhoneNumber.setText("");
                edtEmail.setText("");
                edtPassTeamView.setText("");
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTeamView=edtIdTeamView.getText().toString();
                String name=edtName.getText().toString();
                String address=edtAddress.getText().toString();
                String phoneNumber=edtPhoneNumber.getText().toString();
                String email=edtEmail.getText().toString();
                String passTeamView=edtPassTeamView.getText().toString();

                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myReft=database.getReference("DbKhachHang");

                KhachHang khachHang=new KhachHang(name,address,phoneNumber,email,passTeamView);
                myReft.child(idTeamView).setValue(khachHang).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(),"Thêm dữ liệu thành công!",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Thêm dữ liệu thất bại!"+e.toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void addControls() {
        edtIdTeamView=findViewById(R.id.edtIdTeamView);
        edtName=findViewById(R.id.edtName);
        edtAddress=findViewById(R.id.edtAddress);
        edtPhoneNumber=findViewById(R.id.edtPhoneNumber);
        edtEmail=findViewById(R.id.edtEmail);
        edtPassTeamView=findViewById(R.id.edtPassTeamView);

        btnThem=findViewById(R.id.btnThem);
        btnHuy=findViewById(R.id.btnHuy);
        btnTroVe=findViewById(R.id.btnTroVe);
    }
}