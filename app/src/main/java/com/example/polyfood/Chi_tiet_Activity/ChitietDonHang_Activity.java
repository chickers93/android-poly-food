package com.example.polyfood.Chi_tiet_Activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.polyfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import adapter.GioHangAdapter;
import model.CuaHang;
import model.DonHang;
import model.GioHang;

public class ChitietDonHang_Activity extends AppCompatActivity {
    String madonhang, macuahang, trangthai;
    TextView tv_back, tv_theloai, tv_diachi, tv_madonhang, tv_trangthai, tv_name, tv_address, tv_phonenumber, tv_tongtien;
    ListView lv_donhang;
    List<GioHang> gioHangList = new ArrayList<>();
    GioHangAdapter gioHangAdapter;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    DecimalFormat formatter = new DecimalFormat("###,###,###");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chitiet_don_hang);

        addcontrols();
        addevents();
    }

    private void addcontrols() {
        madonhang = getIntent().getStringExtra("madonhang");
        macuahang = getIntent().getStringExtra("macuahang");
        trangthai = getIntent().getStringExtra("trangthai");
        //Toast.makeText(this, madonhang, Toast.LENGTH_SHORT).show();
        tv_back = findViewById(R.id.tv_back);
        tv_theloai = findViewById(R.id.tv_theloai);
        tv_diachi = findViewById(R.id.tv_diachi);
        tv_madonhang = findViewById(R.id.tv_madonhang);
        tv_trangthai = findViewById(R.id.tv_trangthai);
        tv_name = findViewById(R.id.tv_name);
        tv_address = findViewById(R.id.tv_address);
        tv_phonenumber = findViewById(R.id.tv_phonenumber);
        lv_donhang = findViewById(R.id.lv_donhang);
        tv_tongtien = findViewById(R.id.tv_tongtien);

        if (macuahang != null && madonhang != null && trangthai != null) {
            mData.child("CuaHang").child(macuahang).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    CuaHang cuaHang = dataSnapshot.getValue(CuaHang.class);
                    tv_theloai.setText(convertString(cuaHang.getMatheloai()));
                    tv_diachi.setText(cuaHang.getDiachi());
                    tv_madonhang.setText("Mã đơn hàng: " + "#" + madonhang);
                    tv_trangthai.setText(trangthai);
                    if (trangthai.equals("Chờ xử lý") || trangthai.equals("Đã hủy")) {
                        tv_trangthai.setTextColor(getResources().getColor(R.color.profilePrimaryDark));
                    } else if (trangthai.equals("Đã xác nhận") || trangthai.equals("Hoàn thành")) {
                        tv_trangthai.setTextColor(getResources().getColor(R.color.settings));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        mData.child("DonHang").child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(madonhang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DonHang donHang = dataSnapshot.getValue(DonHang.class);
                tv_name.setText(donHang.getName());
                tv_address.setText(donHang.getAddress());
                tv_phonenumber.setText(donHang.getPhonenumber().substring(3));
                tv_tongtien.setText(formatter.format(donHang.getTotal()) + " VND");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void addevents() {
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mData.child("DonHang").child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(madonhang).child("gioHangList").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                gioHangList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    GioHang gioHang = data.getValue(GioHang.class);
                    gioHangList.add(gioHang);
                }

                gioHangAdapter = new GioHangAdapter(ChitietDonHang_Activity.this, R.layout.item_chitietgiohang, gioHangList);
                lv_donhang.setAdapter(gioHangAdapter);
                gioHangAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private String convertString(String matheloai) {
        String theloai = null;
        if (matheloai.equals("TL01")) {
            theloai = "Đồ ăn";
        } else if (matheloai.equals("TL02")) {
            theloai = "Đồ uống";
        } else if (matheloai.equals("TL03")) {
            theloai = "Đồ chay";
        } else if (matheloai.equals("TL04")) {
            theloai = "Thức ăn nhanh";
        } else if (matheloai.equals("TL05")) {
            theloai = "Kem";
        } else if (matheloai.equals("TL06")) {
            theloai = "Ăn vặt";
        } else if (matheloai.equals("TL07")) {
            theloai = "Bánh ngọt";
        }

        return theloai;
    }
}
