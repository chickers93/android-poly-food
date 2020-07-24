package Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.polyfood.MainActivity;
import com.example.polyfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.User;

public class User_Fragment extends Fragment {
    TextView tv_name, tv_phone, tv_email, tv_address, tv_birthday, tv_signout, tv_update;
    ImageView img_alert1, img_alert2;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ánh xạ
        tv_name = view.findViewById(R.id.tv_name);
        tv_phone = view.findViewById(R.id.tv_phone);
        tv_email = view.findViewById(R.id.tv_email);
        tv_address = view.findViewById(R.id.tv_address);
        tv_birthday = view.findViewById(R.id.tv_birthday);
        tv_update = view.findViewById(R.id.tv_update);
        tv_signout = view.findViewById(R.id.tv_signout);
        img_alert1 = view.findViewById(R.id.img_alert1);
        img_alert2 = view.findViewById(R.id.img_alert2);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //sự kiện
        mData.child("User").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                tv_name.setText(user.getName());
                tv_phone.setText(user.getPhonenumber().substring(3));
                tv_email.setText(user.getEmail());
                tv_address.setText(user.getAddress());
                tv_birthday.setText(user.getBirthday());

                if (tv_email.getText().toString().length() > 0) {
                    img_alert1.setVisibility(View.INVISIBLE);
                } else {
                    img_alert1.setVisibility(View.VISIBLE);
                }
                if (tv_birthday.getText().toString().length() > 0) {
                    img_alert2.setVisibility(View.INVISIBLE);
                } else {
                    img_alert2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //đăng xuất
        tv_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder altdial = new AlertDialog.Builder(getActivity());
                altdial.setMessage("Bạn có muốn đăng xuất tài khoản này hay không?")
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        });
                AlertDialog alert = altdial.create();
                alert.show();

            }
        });

        //cập nhật thông tin
        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder altdial = new AlertDialog.Builder(getActivity());
                final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_update, null);
                altdial.setView(dialogView);

                final AlertDialog dialog = altdial.create();
                dialog.show();

                //ánh xạ
                final TextView tv_name_dialog = dialogView.findViewById(R.id.tv_name);
                final TextView tv_email_dialog = dialogView.findViewById(R.id.tv_email);
                final TextView tv_address_dialog = dialogView.findViewById(R.id.tv_address);
                final TextView tv_birthday_dialog = dialogView.findViewById(R.id.tv_birthday);
                Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
                Button btn_xacnhan = dialogView.findViewById(R.id.btn_xacnhan);

                //get information
                tv_name_dialog.setText(tv_name.getText().toString());
                tv_email_dialog.setText(tv_email.getText().toString());
                tv_address_dialog.setText(tv_address.getText().toString());
                tv_birthday_dialog.setText(tv_birthday.getText().toString());

                //sự kiên
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btn_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = tv_name_dialog.getText().toString();
                        String email = tv_email_dialog.getText().toString();
                        String address = tv_address_dialog.getText().toString();
                        String birthday = tv_birthday_dialog.getText().toString();

                        if (name.length() > 0) {
                            if (address.length() > 0) {
                                mData.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").setValue(name);
                                mData.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email").setValue(email);
                                mData.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("address").setValue(address);
                                mData.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("birthday").setValue(birthday);
                                Toast.makeText(getActivity(), "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                                dialog.cancel();

                            } else {
                                Toast.makeText(getActivity(), "Vui lòng cung cấp địa chỉ liên hệ!", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getActivity(), "Tên người dùng không được để trống!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    }
}
