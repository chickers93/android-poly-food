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

public class Do_anActivity extends AppCompatActivity {
    TextView tv_back;
    ListView lv_do_an;
    CuaHangAdapter cuaHangAdapter;
    List<CuaHang> list_do_an = new ArrayList<>();
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide status bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(Do_anActivity.this,R.color.white));
        setContentView(R.layout.activity_do_an);

        addcontrol();
        addevent();
    }

    private void addcontrol() {
        lv_do_an = findViewById(R.id.lv_do_an);
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
                list_do_an.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    CuaHang cuaHang = data.getValue(CuaHang.class);
                    if (cuaHang.getMatheloai().equals("TL01")) {
                        list_do_an.add(cuaHang);
                    }
                }
                cuaHangAdapter = new CuaHangAdapter(Do_anActivity.this, R.layout.item_cuahang, list_do_an);
                lv_do_an.setAdapter(cuaHangAdapter);
                cuaHangAdapter.notifyDataSetChanged();

                lv_do_an.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(Do_anActivity.this, Chitietquanan_Activity.class);
                        intent.putExtra("macuahang", list_do_an.get(i).getMacuahang());
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
