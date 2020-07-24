package com.example.polyfood.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.polyfood.Chi_tiet_Activity.Chitietquanan_Activity;
import com.example.polyfood.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapter.CuaHangAdapter;
import dao.CuaHangDAO;
import model.CuaHang;

public class Yeu_thichActivity extends AppCompatActivity {
    static CuaHangAdapter cuaHangAdapter;
    TextView tv_back;
    TextInputEditText ed_search;
    ListView lv_yeu_thich;
    List<CuaHang> list_yeu_thich = new ArrayList<>();
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    public static void capnhatLV() {
        cuaHangAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide status bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(Yeu_thichActivity.this, R.color.white));
        setContentView(R.layout.activity_yeu_thich);

        addcontrol();
        addevent();
    }

    private void addcontrol() {
        lv_yeu_thich = findViewById(R.id.lv_yeu_thich);
        tv_back = findViewById(R.id.tv_back);
        ed_search = findViewById(R.id.ed_search);
    }

    private void addevent() {
        addtoListview();

        lv_yeu_thich.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Yeu_thichActivity.this, Chitietquanan_Activity.class);
                intent.putExtra("macuahang", list_yeu_thich.get(i).getMacuahang());
                startActivity(intent);
            }
        });

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //tim kiem
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    mData.child("CuaHang").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            list_yeu_thich.clear();
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                CuaHang cuaHang = data.getValue(CuaHang.class);
                                if (cuaHang.getTencuahang().contains(s)
                                        || cuaHang.getTencuahang().toLowerCase().contains(s)
                                        || cuaHang.getTencuahang().toUpperCase().contains(s)) {
                                    list_yeu_thich.add(cuaHang);
                                }
                            }

                            cuaHangAdapter = new CuaHangAdapter(Yeu_thichActivity.this, R.layout.item_cuahang, list_yeu_thich);
                            lv_yeu_thich.setAdapter(cuaHangAdapter);
                            cuaHangAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else {
                    addtoListview();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void addtoListview() {
        CuaHangDAO cuaHangDAO = new CuaHangDAO();
        list_yeu_thich = cuaHangDAO.getAllData();

        cuaHangAdapter = new CuaHangAdapter(Yeu_thichActivity.this, R.layout.item_cuahang, list_yeu_thich);
        lv_yeu_thich.setAdapter(cuaHangAdapter);
    }

}
