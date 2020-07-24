package com.example.polyfood.Chi_tiet_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.polyfood.DirectionHelpers.TaskLoadedCallback;
import com.example.polyfood.HomeActivity;
import com.example.polyfood.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import Fragment.Comment_Fragment;
import Fragment.Map_Fragment;
import Fragment.Menu_Fragment;
import adapter.ViewPagerAdapter;
import model.CuaHang;

public class Chitietquanan_Activity extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {
    public static String macuahang;
    ListView lv_menu;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    TextView tv_tenquanan, tv_diachi;
    ImageView hinhanh, btn_back, btn_home;
    Button btn_xemgiohang;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chitietquanan);

        addcontrol();
        addevent();
    }

    private void addcontrol() {
        lv_menu = findViewById(R.id.lv_menu);
        macuahang = getIntent().getStringExtra("macuahang");
        tv_tenquanan = findViewById(R.id.tv_tenquanan);
        tv_diachi = findViewById(R.id.tv_address);
        hinhanh = findViewById(R.id.hinhanh);
        btn_back = findViewById(R.id.btn_back);
        btn_home = findViewById(R.id.btn_home);
        btn_xemgiohang = findViewById(R.id.btn_xemgiohang);

        //ánh xạ
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);

        // attach tablayout with viewpager
        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        // add your fragments
        adapter.addFrag(new Menu_Fragment(), "Thực đơn");
        adapter.addFrag(new Map_Fragment(), "Bản đồ");
        adapter.addFrag(new Comment_Fragment(), "Đánh giá");
        // set adapter on viewpager
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                viewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        tabLayout.setTabsFromPagerAdapter(adapter);//deprecated
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

    }

    private void addevent() {
        mData.child("CuaHang").child(macuahang).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CuaHang cuaHang = dataSnapshot.getValue(CuaHang.class);
                try {
                    Picasso.with(Chitietquanan_Activity.this).load(cuaHang.getHinhanh()).into(hinhanh);
                } catch (Exception e) {

                }
                tv_tenquanan.setText(cuaHang.getTencuahang());
                tv_diachi.setText(cuaHang.getDiachi());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //back
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //home
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chitietquanan_Activity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onTaskDone(Object... values) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

}
