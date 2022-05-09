package com.example.quanlykhachhangadmin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class KhachHangAdapter extends ArrayAdapter<KhachHang> {

    @NonNull private  Activity activity;
    private int resource;
    @NonNull private List<KhachHang> objects;

    public KhachHangAdapter(@NonNull Activity activity, int resource, @NonNull List<KhachHang> objects) {
        super(activity, resource, objects);

        this.activity=activity;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.activity.getLayoutInflater();
        View view= inflater.inflate(this.resource,null);

        TextView txtIdTeamViewcs= view.findViewById(R.id.txtIdTeamViewcs);
        TextView txtNamecs= view.findViewById(R.id.txtNamecs);

        KhachHang khachHang=this.objects.get(position);
        txtIdTeamViewcs.setText("Id team view: "+khachHang.getIdTeamView());
        txtNamecs.setText("Name: "+khachHang.getName());

        ImageView btnMenu =view.findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu= new PopupMenu(activity,view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId()==R.id.item_them_khach_hang){
                            Intent intent=new Intent(activity,ThemKhActivity.class);
                            activity.startActivity(intent);
                        }
                        else if(menuItem.getItemId()==R.id.item_sua_kh){
                            Intent intent=new Intent(activity,SuaKhachHangActivity.class);
                            intent.putExtra("KHACHHANG",khachHang);
                            activity.startActivity(intent);

                        }
                        else if(menuItem.getItemId()==R.id.item_xoa_kh){
                            FirebaseDatabase database=FirebaseDatabase.getInstance();
                            DatabaseReference myRef=database.getReference("DbKhachHang");
                            myRef.child(khachHang.getIdTeamView()).removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                    Toast.makeText(activity,"Xóa thành công.",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        return false;
                    }
                });
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());

                try {
                    Field field = popupMenu.getClass().getDeclaredField("mPopup");
                    field.setAccessible(true);
                    Object popUpMenuHelper = field.get(popupMenu);
                    Class<?> cls = Class.forName("com.android.internal.view.menu.MenuPopupHelper");
                    Method method = cls.getDeclaredMethod("setForceShowIcon",new Class[] {boolean.class});
                    method.setAccessible(true);
                    method.invoke(popUpMenuHelper,new Object[]{true});
                }catch (Exception e){
                    Log.d("MyTag","onClick"+e.toString());
                }

                popupMenu.show();
            }
        });

        return view;
    }
}
