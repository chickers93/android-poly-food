package Fragment;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.polyfood.HomeActivity;
import com.example.polyfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import adapter.GioHangAdapter;
import model.DonHang;
import model.GioHang;
import model.MonAnYeuThich;
import model.User;

public class Cart_Fragment extends Fragment implements LocationListener {
    static LinearLayout view_giohangtrong;
    ListView lv_giohang;
    GioHangAdapter gioHangAdapter;
    TextView tv_tongtien, tv_diachi;
    int tongtien = 0;
    ImageView btn_more;
    LocationManager locationManager;
    boolean GpsStatus;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    Button btn_thanhtoan;
    String uid, name, phonenumber;
    String timeStamp;

    public Cart_Fragment() {
        // Required empty public constructor
    }

    public static void refreshGioHang() {
        if (HomeActivity.gioHangList.size() > 0) {
            view_giohangtrong.setVisibility(View.INVISIBLE);
        } else {
            view_giohangtrong.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.cart_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Permission already Granted
            //Do your work here
            //Perform operations here only which requires permission
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) this);
        final Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        //ánh xạ
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        lv_giohang = view.findViewById(R.id.lv_giohang);
        gioHangAdapter = new GioHangAdapter(getActivity(), R.layout.item_giohang, HomeActivity.gioHangList);
        lv_giohang.setAdapter(gioHangAdapter);

        tv_diachi = view.findViewById(R.id.tv_diachi);
        tv_tongtien = view.findViewById(R.id.tv_tongtien);
        btn_thanhtoan = view.findViewById(R.id.btn_thanhtoan);
        btn_more = view.findViewById(R.id.btn_more);
        view_giohangtrong = view.findViewById(R.id.view_giohangtrong);
        if (HomeActivity.gioHangList.size() > 0) {
            view_giohangtrong.setVisibility(View.INVISIBLE);
        } else {
            view_giohangtrong.setVisibility(View.VISIBLE);
        }

        //sự kiện
        for (int i = 0; i < HomeActivity.gioHangList.size(); i++) {
            tongtien += HomeActivity.gioHangList.get(i).getGia();
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        tv_tongtien.setText(formatter.format(tongtien));

        //get địa chỉ giao hàng
        try {
            tv_diachi.setText(getCompleteAddressString(location.getLatitude(), location.getLongitude()));
        } catch (Exception e) {
            tv_diachi.setText("Hãy nhập địa chỉ giao hàng");
        }

        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(getActivity(), btn_more);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.poupup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.one:
                                locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                                GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                                if (GpsStatus == true) {
                                    try {
                                        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                                                ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                                        } else {
                                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                                        }
                                        final Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                        tv_diachi.setText(getCompleteAddressString(location.getLatitude(), location.getLongitude()));
                                    } catch (Exception e) {

                                    }

                                } else {
                                    Toast.makeText(getActivity(), "Bạn chưa bật vị trí của thiết bị!", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case R.id.two:
                                mData.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        User user = dataSnapshot.getValue(User.class);
                                        tv_diachi.setText(user.getAddress());
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                                break;
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });

        //thanh toán gio hàng
        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
                if (HomeActivity.gioHangList.size() == 0) {
                    Toast.makeText(getActivity(), "Giỏ hàng trống!", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder altdial = new AlertDialog.Builder(getActivity());
                    altdial.setTitle("Xác nhận đơn hàng");
                    altdial.setMessage("Các món ăn bạn đã gọi sẽ được gửi đến cửa hàng.Bạn có muốn tiếp tục?")
                            .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Date date = new Date();
                                    DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                    DateFormat formater2 = new SimpleDateFormat("yyyyMMddHHmmssSSSS");

                                    timeStamp = formater2.format(date);

                                    DonHang donHang = new DonHang(timeStamp, uid, name, tv_diachi.getText().toString(), phonenumber, HomeActivity.gioHangList, tongtien, "Chờ xử lý", formater.format(date.getTime()), HomeActivity.gioHangList.get(0).getMacuahang());
                                    mData.child("DonHang").child("CuaHang").child(HomeActivity.gioHangList.get(0).getMacuahang()).child(timeStamp).setValue(donHang);
                                    mData.child("DonHang").child("User").child(uid).child(timeStamp).setValue(donHang);
                                    mData.child("GioHang").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
                                    setFavourite();

                                    //thanh toán xong xóa giỏ hàng
                                    List<GioHang> list = new ArrayList<>();
                                    lv_giohang.setAdapter(new GioHangAdapter(getActivity(), R.layout.item_giohang, list));
                                    view_giohangtrong.setVisibility(View.VISIBLE);
                                    tv_tongtien.setText("0");

                                }
                            });
                    AlertDialog alert = altdial.create();
                    alert.show();

                }
            }
        });

    }

    //get thông tin user
    private void getInfo() {
        mData.child("User").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                name = user.getName();
                phonenumber = user.getPhonenumber();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //tạo danh sách món ăn yêu thích
    private void setFavourite() {
        for (int i = 0; i < HomeActivity.gioHangList.size(); i++) {
            mData.child("Favourite").child(HomeActivity.gioHangList.get(0).getMacuahang()).child(HomeActivity.gioHangList.get(i).getMamonan()).push().setValue(new MonAnYeuThich(HomeActivity.gioHangList.get(i).getSoluong()));
        }
    }

    //function get address from coordinates
    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder();

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i));
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
