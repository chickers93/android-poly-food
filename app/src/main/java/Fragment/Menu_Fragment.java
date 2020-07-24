package Fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.polyfood.Chi_tiet_Activity.Chitietquanan_Activity;
import com.example.polyfood.HomeActivity;
import com.example.polyfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import adapter.MenuAdapter;
import model.GioHang;
import model.Menu;

public class Menu_Fragment extends Fragment {
    ListView lv_menu;
    MenuAdapter menuAdapter;
    List<Menu> listMenu = new ArrayList<>();
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    TextView tv_tenquanan, tv_diachi;
    ImageView hinhanh, btn_back, btn_home;
    String macuahang;
    Integer soluong, tonggia;
    Button btn_xemgiohang;
    boolean exists;
    int count;

    public Menu_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ánh xạ
        lv_menu = view.findViewById(R.id.lv_menu);
        macuahang = Chitietquanan_Activity.macuahang;
        tv_tenquanan = view.findViewById(R.id.tv_tenquanan);
        tv_diachi = view.findViewById(R.id.tv_address);
        hinhanh = view.findViewById(R.id.hinhanh);
        btn_back = view.findViewById(R.id.btn_back);
        btn_home = view.findViewById(R.id.btn_home);
        btn_xemgiohang = view.findViewById(R.id.btn_xemgiohang);

        //sự kiện
        mData.child("Menu").child(macuahang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                listMenu.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Menu menu = data.getValue(Menu.class);
                    listMenu.add(menu);
                }

                try {
                    menuAdapter = new MenuAdapter(getActivity(), R.layout.item_menu, listMenu);
                    lv_menu.setAdapter(menuAdapter);
                    menuAdapter.notifyDataSetChanged();
                } catch (Exception e) {

                }

                //dialog
                lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, final View view, final int i, long l) {
                        lv_menu.setEnabled(false);
                        final Dialog dialog = new Dialog(getActivity(), R.style.theme_dialog);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.add_to_cart);

                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                        lp.copyFrom(dialog.getWindow().getAttributes());
                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                        //lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        lp.gravity = Gravity.BOTTOM;
                        lp.windowAnimations = R.style.DialogAnimation;

                        dialog.getWindow().setAttributes(lp);
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.setCancelable(true);
                        dialog.setCanceledOnTouchOutside(true);

                        dialog.show();

                        //ánh xạ
                        final DecimalFormat formatter = new DecimalFormat("###,###,###");
                        ImageView img_monan = dialog.findViewById(R.id.img_monan);
                        TextView tv_tenmonan = dialog.findViewById(R.id.tv_tenmonan);
                        TextView tv_gia = dialog.findViewById(R.id.tv_gia);
                        final Button btn_add_to_cart = dialog.findViewById(R.id.btn_add_to_cart);
                        ImageView btn_cancel = dialog.findViewById(R.id.btn_cancel);
                        final ElegantNumberButton btn_soluong = dialog.findViewById(R.id.btn_soluong);
                        //set default so luong
                        btn_soluong.setRange(1, 10);
                        btn_soluong.setNumber("1");
                        soluong = Integer.parseInt(btn_soluong.getNumber());
                        tonggia = soluong * listMenu.get(i).getGia();
                        btn_add_to_cart.setText("Thêm vào giỏ hàng - " + formatter.format(tonggia) + " VND");

                        //sự kiện
                        try {
                            Picasso.with(getActivity()).load(listMenu.get(i).getHinhanh()).into(img_monan);
                        } catch (Exception e) {
                            Picasso.with(getActivity()).load("https://firebasestorage.googleapis.com/v0/b/polyfood-7fcd7.appspot.com/o/no_image.jpg?alt=media&token=fa11b05a-5e3e-4f0b-a172-f10dad5208f6").into(img_monan);
                        }

                        tv_tenmonan.setText(listMenu.get(i).getTenmonan());
                        tv_gia.setText(formatter.format(listMenu.get(i).getGia()) + " VND");
                        btn_soluong.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                            @Override
                            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                                soluong = Integer.parseInt(btn_soluong.getNumber());
                                tonggia = soluong * listMenu.get(i).getGia();
                                btn_add_to_cart.setText("Thêm vào giỏ hàng - " + formatter.format(tonggia) + " VND");
                            }
                        });

                        //add to cart
                        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (HomeActivity.gioHangList.size() > 0) {
                                    exists = false;
                                    if (HomeActivity.gioHangList.get(0).getMacuahang().equals(macuahang)) {
                                        for (int j = 0; j < HomeActivity.gioHangList.size(); j++) {
                                            if (HomeActivity.gioHangList.get(j).getMamonan().equals(listMenu.get(i).getMamonan())) {
                                                soluong = Integer.parseInt(btn_soluong.getNumber());
                                                tonggia = soluong * listMenu.get(i).getGia();
                                                HomeActivity.gioHangList.get(j).setSoluong(HomeActivity.gioHangList.get(j).getSoluong() + soluong);
                                                HomeActivity.gioHangList.get(j).setGia(listMenu.get(i).getGia() * HomeActivity.gioHangList.get(j).getSoluong());
                                                exists = true;
                                            }
                                        }

                                        if (exists == false) {
                                            soluong = Integer.parseInt(btn_soluong.getNumber());
                                            tonggia = soluong * listMenu.get(i).getGia();
                                            HomeActivity.gioHangList.add(new GioHang(macuahang, listMenu.get(i).getMamonan(), listMenu.get(i).getTenmonan(), soluong, tonggia, listMenu.get(i).getHinhanh()));
                                        }

                                    } else {
                                        AlertDialog.Builder altdial = new AlertDialog.Builder(getActivity());
                                        altdial.setTitle("Tạo giỏ hàng mới?");
                                        altdial.setMessage("Thêm các món này sẽ khiến các món hiện tại trong giỏ hàng của bạn bị xóa. Bạn có muốn tiếp tục?")
                                                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.cancel();
                                                    }
                                                })
                                                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        HomeActivity.gioHangList.clear();
                                                        mData.child("GioHang").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
                                                        soluong = Integer.parseInt(btn_soluong.getNumber());
                                                        tonggia = soluong * listMenu.get(i).getGia();
                                                        HomeActivity.gioHangList.add(new GioHang(macuahang, listMenu.get(i).getMamonan(), listMenu.get(i).getTenmonan(), soluong, tonggia, listMenu.get(i).getHinhanh()));
                                                        reload();
                                                    }
                                                });
                                        AlertDialog alert = altdial.create();
                                        alert.show();
                                    }

                                } else {
                                    soluong = Integer.parseInt(btn_soluong.getNumber());
                                    tonggia = soluong * listMenu.get(i).getGia();
                                    HomeActivity.gioHangList.add(new GioHang(macuahang, listMenu.get(i).getMamonan(), listMenu.get(i).getTenmonan(), soluong, tonggia, listMenu.get(i).getHinhanh()));

                                }

                                dialog.cancel();
                                reload();

                            }
                        });

                        //cancel dialog
                        btn_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                                //lv_menu.setEnabled(true);
                            }
                        });

                        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                lv_menu.setEnabled(true);
                            }
                        });
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_xemgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("from_cart", "1");
                startActivity(intent);
            }
        });
    }

    public void reload() {
        count = 0;
        for (int i = 0; i < HomeActivity.gioHangList.size(); i++) {
            count += HomeActivity.gioHangList.get(i).getSoluong();
        }

        if (count > 0) {
            btn_xemgiohang.setText("Xem giỏ hàng - " + count + " món");
        } else {
            btn_xemgiohang.setText("Xem giở hàng");
        }

        if (HomeActivity.gioHangList.size() > 0) {
            mData.child("GioHang").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(HomeActivity.gioHangList);
        }
    }
}
