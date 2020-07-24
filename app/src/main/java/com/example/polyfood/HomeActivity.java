package com.example.polyfood;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Fragment.Cart_Fragment;
import Fragment.History_Fragment;
import Fragment.Home_Fragment;
import Fragment.User_Fragment;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import model.GioHang;

public class HomeActivity extends AppCompatActivity {
    public static List<GioHang> gioHangList;
    MeowBottomNavigation bottomNavigation;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    String key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        //ánh xạ
        bottomNavigation = findViewById(R.id.bottom_menu_bar_ID);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_shopping_cart));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_history));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_user));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Home_Fragment()).commit();
        }

        try {
            key = getIntent().getStringExtra("from_cart");
            if (key.equals("1")) {
                bottomNavigation.show(2, true);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Cart_Fragment()).commit();
            }
        } catch (Exception e) {

        }

        //set default selected menu
        if (key == null) {
            bottomNavigation.show(1, true);
        }

        //get the select menu
        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                switch (model.getId()) {
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Home_Fragment()).commit();
                        break;
                }
                return null;
            }
        });

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Home_Fragment()).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Cart_Fragment()).commit();
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new History_Fragment()).commit();
                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new User_Fragment()).commit();
                        break;
                }
                return null;
            }
        });

        //xử lý giỏ hàng
        if (gioHangList != null) {

        } else {
            gioHangList = new ArrayList<>();
        }

        //getGioHang from firebase
        mData.child("GioHang").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                gioHangList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    GioHang gioHang = data.getValue(GioHang.class);
                    gioHangList.add(gioHang);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        MenuDAO menuDAO = new MenuDAO();
//        menuDAO.putData();

    }
}



