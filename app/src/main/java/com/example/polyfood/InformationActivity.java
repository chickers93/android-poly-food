package com.example.polyfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.User;

public class InformationActivity extends AppCompatActivity {
    String phoneNumber, uid;
    EditText ed_name, ed_address, ed_password, ed_repassword;
    Button btn_xacnhan;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_information);

        addcontrols();
        addevents();
    }

    private void addcontrols() {
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        uid = getIntent().getStringExtra("uid");
        ed_name = findViewById(R.id.ed_name);
        ed_address = findViewById(R.id.ed_address);
        ed_password = findViewById(R.id.ed_password);
        ed_repassword = findViewById(R.id.ed_repassword);
        btn_xacnhan = findViewById(R.id.btn_xacnhan);
    }

    private void addevents() {
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = ed_name.getText().toString();
                final String address = ed_address.getText().toString();
                final String password = ed_password.getText().toString();
                String re_password = ed_repassword.getText().toString();

                if (name.isEmpty() || address.isEmpty() || password.isEmpty()) {
                    Toast.makeText(InformationActivity.this, "Thông tin không được để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    if (!password.equals(re_password)) {
                        Toast.makeText(InformationActivity.this, "Mật khẩu xác nhận không trùng khớp!", Toast.LENGTH_SHORT).show();
                    } else {
                        mData.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                User user = new User(uid, name, address, password, phoneNumber, "", "");
                                mData.child("User").child(uid).setValue(user);

                                Intent intent = new Intent(InformationActivity.this, LoginActivity.class);
                                intent.putExtra("uid", uid);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }
        });
    }
}
