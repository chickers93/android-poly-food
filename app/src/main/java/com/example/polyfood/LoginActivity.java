package com.example.polyfood;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.User;

public class LoginActivity extends AppCompatActivity {
    String uid;
    TextView tv_continue, tv_change;
    TextInputEditText ed_password;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    ProgressBar progressBar;

    LocationManager locationManager;
    boolean GpsStatus;
    int PERMISSION_ALL = 1;

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this, R.color.white));
        setContentView(R.layout.activity_login);

        addcontrols();
        addevents();

        //cấp quyền
        String[] PERMISSIONS = {
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
        };

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        //FirebaseAuth.getInstance().signOut();

    }

    private void addcontrols() {
        uid = getIntent().getStringExtra("uid");
        tv_continue = findViewById(R.id.tv_continue);
        tv_change = findViewById(R.id.tv_change);
        ed_password = findViewById(R.id.ed_password);
        progressBar = findViewById(R.id.spin_kit);
    }

    private void addevents() {
        tv_continue.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        ed_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed_password.length() > 0) {
                    tv_continue.setVisibility(View.VISIBLE);
                }

                tv_continue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressBar.setVisibility(View.VISIBLE);
                        mData.child("User").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                User user = dataSnapshot.getValue(User.class);
                                if (user.getPassword().equals(ed_password.getText().toString().trim())) {

                                    CheckGpsStatus();

                                } else {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(LoginActivity.this, "Mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //đổi tài khoản đăng nhập
        tv_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //check GPS
    public void CheckGpsStatus() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (GpsStatus == true) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            ed_password.setText(null);
        } else {
//            AlertDialog.Builder altdial = new AlertDialog.Builder(LoginActivity.this);
//            altdial.setTitle("Thông báo");
//            altdial.setMessage("Vui lòng bật vị trí thiết bị để tiếp tục ứng dụng!")
//                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            progressBar.setVisibility(View.INVISIBLE);
//                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                            startActivity(intent);
//                        }
//                    });
//            AlertDialog alert = altdial.create();
//            alert.show();

            Toast.makeText(this, "Vui lòng bật vị trí thiết bị để tiếp tục ứng dụng!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.INVISIBLE);
        ed_password.setText(null);
        tv_continue.setVisibility(View.INVISIBLE);
    }
}
