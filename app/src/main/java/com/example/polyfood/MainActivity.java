package com.example.polyfood;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextInputEditText ed_phone;
    TextView tv_continue;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        addcontrols();
        addevents();

    }

    private void addcontrols() {
        mAuth = FirebaseAuth.getInstance();
        ed_phone = findViewById(R.id.ed_phone);
        tv_continue = findViewById(R.id.tv_continue);
    }

    private void addevents() {
        tv_continue.setVisibility(View.INVISIBLE);
        ed_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed_phone.length() == 0) {
                    tv_continue.setVisibility(View.INVISIBLE);
                } else if (ed_phone.length() > 0 && ed_phone.length() < 10 || ed_phone.length() > 10) {
                    tv_continue.setVisibility(View.VISIBLE);
                    tv_continue.setTextColor(getResources().getColor(R.color.black));
                } else if (ed_phone.length() == 10) {
                    tv_continue.setTextColor(getResources().getColor(R.color.white));
                    tv_continue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String phoneNumber = "+84" + ed_phone.getText().toString();
                            Intent intent = new Intent(MainActivity.this, VerifyPhoneActivity.class);
                            intent.putExtra("phoneNumber", phoneNumber);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.putExtra("uid", mAuth.getCurrentUser().getUid());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
