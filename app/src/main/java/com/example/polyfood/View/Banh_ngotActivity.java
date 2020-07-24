package com.example.polyfood.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.polyfood.Chi_tiet_Activity.Chitietquanan_Activity;
import com.example.polyfood.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapter.CuaHangAdapter;
import model.CuaHang;

public class Banh_ngotActivity extends AppCompatActivity {
    TextView tv_back;
    ListView lv_banh_ngot;
    CuaHangAdapter cuaHangAdapter;
    List<CuaHang> list_banh_ngot = new ArrayList<>();
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide status bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(Banh_ngotActivity.this, R.color.white));
        setContentView(R.layout.activity_banh_ngot);

        addcontrol();
        addevent();
    }

    private void addcontrol() {
        lv_banh_ngot = findViewById(R.id.lv_banh_ngot);
        tv_back = findViewById(R.id.tv_back);
    }

    private void addevent() {
        getData();
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void getData() {
        mData.child("CuaHang").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_banh_ngot.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    CuaHang cuaHang = data.getValue(CuaHang.class);
                    if (cuaHang.getMatheloai().equals("TL07")) {
                        list_banh_ngot.add(cuaHang);
                    }
                }
                cuaHangAdapter = new CuaHangAdapter(Banh_ngotActivity.this, R.layout.item_cuahang, list_banh_ngot);
                lv_banh_ngot.setAdapter(cuaHangAdapter);
                cuaHangAdapter.notifyDataSetChanged();

                lv_banh_ngot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(Banh_ngotActivity.this, Chitietquanan_Activity.class);
                        intent.putExtra("macuahang", list_banh_ngot.get(i).getMacuahang());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
