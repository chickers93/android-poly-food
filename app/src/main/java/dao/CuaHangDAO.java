package dao;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.polyfood.View.Yeu_thichActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Fragment.Home_Fragment;
import model.CuaHang;
import model.CuaHang_temp;

public class CuaHangDAO implements LocationListener {
    List<CuaHang> cuaHangList = new ArrayList<>();
    List<CuaHang_temp> temp = new ArrayList<>();
    DatabaseReference mData;
    Home_Fragment home_fragment;
    Context context;

    public CuaHangDAO() {
        mData = FirebaseDatabase.getInstance().getReference();
    }

    public CuaHangDAO(Home_Fragment home_fragment) {
        this.home_fragment = home_fragment;
        mData = FirebaseDatabase.getInstance().getReference();
    }

    //đo khoảng cách giữa hai điểm
    public static double distanceBetween2Points(double la1, double lo1, double la2, double lo2) {
        double dLat = (la2 - la1) * (Math.PI / 180);
        double dLon = (lo2 - lo1) * (Math.PI / 180);
        double la1ToRad = la1 * (Math.PI / 180);
        double la2ToRad = la2 * (Math.PI / 180);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(la1ToRad) * Math.cos(la2ToRad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6400 * c;
        return d;
    }

    //put data CuaHang
    public void putData() {
        ArrayList<CuaHang> list = new ArrayList<>();
        //Đồ ăn
        list.add(new CuaHang("CH01", "TL01", "Buzza Pizza", "Tầng 3, 5-7-9 Nguyễn Trung Trực, P. Bến Thành, Quận 1, TP. HCM", 4.3, "https://images.foody.vn/res/g10/90677/prof/s640x400/foody-mobile-buzza-pizza-jpg-194-636123115822885005.jpg", 10.773545, 106.699470));
        list.add(new CuaHang("CH02", "TL01", "San Fu Lou - Ẩm Thực Quảng Đông", "Tầng Trệt, AB Tower, 76A Lê Lai, Quận 1, TP. HCM", 4.2, "https://images.foody.vn/res/g10/90018/prof/s640x400/foody-upload-api-foody-mobile-foody-mobile-56-jpg--190620173721.jpg", 10.770517, 106.694365));
        list.add(new CuaHang("CH03", "TL01", "Sushi Tei", "200A Lý Tự Trọng (Ngã 4 Trương Định), Quận 1, TP. HCM", 4.4, "https://images.foody.vn/res/g14/135147/prof/s640x400/foody-upload-api-foody-mobile-9-190211171436.jpg", 10.772824, 106.695267));
        list.add(new CuaHang("CH04", "TL01", "SaSin - Mì Cay 7 Cấp Độ Hàn Quốc", "756 Sư Vạn Hạnh, Quận 10, TP. HCM", 4.5, "https://images.foody.vn/res/g22/216691/prof/s640x400/foody-mobile-58-jpg.jpg", 10.798701, 106.667914));
        list.add(new CuaHang("CH05", "TL01", "TukTuk Thai Bistro", "17/11 Lê Thánh Tôn (Mặt Tiền), Quận 1, TP. HCM", 4.2, "https://images.foody.vn/res/g9/88048/prof/s640x400/foody-upload-api-foody-mobile-tuktuk-thai-bistro-l-180831165248.jpg", 10.779962, 106.704235));

        //Đồ uống
        list.add(new CuaHang("CH06", "TL02", "Đen Đá Cafe", "96 Hàm Nghi, Quận 1, TP. HCM", 4.5, "https://images.foody.vn/res/g12/110335/prof/s640x400/foody-upload-api-foody-mobile-14-190620154720.jpg", 10.771490, 106.702871));
        list.add(new CuaHang("CH07", "TL02", "The Alley", "232 Nguyễn Thị Minh Khai, P. 6, Quận 3, TP. HCM", 4.5, "https://images.foody.vn/res/g72/714869/prof/s640x400/foody-upload-api-foody-mobile-16-190617174510.jpg", 10.775061, 106.690039));
        list.add(new CuaHang("CH08", "TL02", "Cheese Coffee", "307 Hồng Bàng, P. 11, Quận 5, TP. HCM", 4.5, "https://images.foody.vn/res/g72/710932/s800/foody-cheese-coffee-hong-bang-479-636522400333846567.jpg", 10.754940, 106.660628));
        list.add(new CuaHang("CH09", "TL02", "Say Coffee", "756 Sư Vạn Hạnh, Quận 10, TP. HCM", 4.5, "https://images.foody.vn/res/g19/187688/s800/foody-checkin-say-coffee-451-636110244888022699.jpg", 10.772768, 106.669399));
        list.add(new CuaHang("CH010", "TL02", "Un - T Teh Tarik", "157/3 Nguyễn Ảnh Thủ, Quận 12, TP. HCM", 4.5, "https://images.foody.vn/res/g73/726413/prof/s640x400/foody-upload-api-foody-mobile-3-jpg-181218094640.jpg", 10.856588, 106.607568));


        //Đồ chay
        list.add(new CuaHang("CH011", "TL03", "The Organic", "54 Lý Văn Phức, P. Tân Định, Quận 1, TP. HCM", 4.2, "https://images.foody.vn/res/g1/865/prof/s640x400/foody-upload-api-foody-mobile-12-jpg-180918142854.jpg", 10.791628, 106.693453));
        list.add(new CuaHang("CH012", "TL03", "Bếp Xanh An Duyên", "10 Nguyễn Tri Phương, Quận 5, TP. HCM", 4.5, "https://images.foody.vn/res/g10/94523/prof/s640x400/foody-mobile-2-jpg-519-636432225157696691.jpg", 10.751197, 106.669537));
        list.add(new CuaHang("CH013", "TL03", "KVegetarian - Quán Chay", "20/15 Phan Đăng Lưu, P. 6, Bình Thạnh, TP. HCM", 4.0, "https://images.foody.vn/res/g73/726458/prof/s640x400/foody-upload-api-foody-mobile-7667b983951d72432b0c-190923155905.jpg", 10.803894, 106.690651));
        list.add(new CuaHang("CH014", "TL03", "Bà Xã Vegan", "245/5 Nguyễn Trãi, P. Nguyễn Cư Trinh, Quận 1, TP. HCM", 4.3, "https://images.foody.vn/res/g73/723086/prof/s640x400/foody-upload-api-foody-mobile-100-190513182636.jpg", 10.764763, 106.687733));
        list.add(new CuaHang("CH015", "TL03", "Hủ Tiếu Chay Cây Đề", "1 Diệp Minh Châu, P. Tân Sơn Nhì, Tân Phú, TP. HCM", 4.3, "https://images.foody.vn/res/g17/168893/prof/s640x400/foody-upload-api-foody-mobile-8-190730105221.jpg", 10.798604, 106.631887));


        //Thức ăn nhanh
        list.add(new CuaHang("CH016", "TL04", "Gà Rán KFC - Đinh Tiên Hoàng", "127 - 127N Đinh Tiên Hoàng, P. 3, Bình Thạnh, TP. HCM", 4.0, "https://images.foody.vn/res/g1/4134/prof/s640x400/foody-upload-api-foody-mobile-jhjhjh-190612140201.jpg", 10.799977, 106.696058));
        list.add(new CuaHang("CH017", "TL04", "Pizza Inn - Huỳnh Mẫn Đạt", "77A Huỳnh Mẫn Đạt, P. 7, Quận 5, TP. HCM", 4.5, "https://images.foody.vn/res/g1/4030/prof/s640x400/foody-upload-api-foody-mobile-20-190620104031.jpg", 10.754437, 106.676720));
        list.add(new CuaHang("CH018", "TL04", "Sandwich - Hamburger Chía Núi", "56 Trần Xuân Hòa, P. 7, Quận 5, TP. HCM", 4.6, "https://images.foody.vn/res/g26/258535/prof/s640x400/foody-upload-api-foody-mobile-aaf1865d7a229e7cc733-190626151553.jpg", 10.753845, 106.667353));
        list.add(new CuaHang("CH019", "TL04", "Mama's Kitchen - Pizza & Pasta", "Tầng 4, Tháp V5, 23 Nguyễn Hữu Thọ, Quận 7, TP. HCM", 4.5, "https://images.foody.vn/res/g14/130222/prof/s640x400/foody-upload-api-foody-mobile-5-jpg-180410115637.jpg", 10.739399, 106.700417));
        list.add(new CuaHang("CH020", "TL04", "Pendolasco Thảo Điền", "36 Tống Hữu Định, Quận 2, TP. HCM", 4.8, "https://images.foody.vn/res/g1/5100/prof/s640x400/foody-mobile-hinh-dai-dien-pendo--611-636082313774573155.jpg", 10.806506, 106.734634));

        //Kem
        list.add(new CuaHang("CH021", "TL05", "Kem Mỹ", "23 Đường 41, P. 6, Quận 4, TP. HCM", 4.0, "https://images.foody.vn/res/g1/2107/prof/s640x400/foody-mobile-kem-my-jpg-962-635845868557269117.jpg", 10.758184, 106.700087));
        list.add(new CuaHang("CH022", "TL05", "Kem Bơ & Trái Cây Tô 251", "251 Tô Hiến Thành, P. 13, Quận 10, TP. HCM", 4.0, "https://images.foody.vn/res/g21/203474/prof/s640x400/foody-upload-api-foody-mobile-yuyuyu-190509135504.jpg", 10.778811, 106.666975));
        list.add(new CuaHang("CH023", "TL05", "Kem Xôi Dừa Bu Bu", "205 Đường Số 17, P. Tân Quy, Quận 7, TP. HCM", 4.6, "https://images.foody.vn/res/g9/89627/prof/s640x400/foody-mobile-hmb-h-jpg-707-635785379074086464.jpg", 10.745088, 106.710099));
        list.add(new CuaHang("CH024", "TL05", "Kem Miếng Dừa", "450 Nguyễn Chí Thanh, P. 6, Quận 10, TP. HCM", 4.5, "https://images.foody.vn/res/g4/30253/prof/s640x400/foody-mobile-t-1-jpg-739-635737840004213525.jpg", 10.759113, 106.664590));
        list.add(new CuaHang("CH025", "TL05", "Kem Ken - Gelato Ice Cream", "220/1 Nguyễn Trọng Tuyển, P. 8, Phú Nhuận, TP. HCM", 4.4, "https://images.foody.vn/res/g96/958233/prof/s640x400/foody-upload-api-foody-mobile-ggg-190918171130.jpg", 10.798148, 106.673763));

        //Ăn vặt
        list.add(new CuaHang("CH026", "TL06", "Ăn Vặt - Cá Viên Chiên", "176 Đặng Văn Ngữ, Phú Nhuận, TP. HCM", 4.0, "https://images.foody.vn/res/g8/72852/prof/s640x400/foody-mobile-258-jpg-154-636183601740478461.jpg", 10.791140, 106.666911));
        list.add(new CuaHang("CH027", "TL06", "Ăn Vặt Chị 3", "450 Phan Xích Long, P. 2, Phú Nhuận, TP. HCM", 4.2, "https://images.foody.vn/res/g24/238856/prof/s640x400/foody-mobile-t1-jpg-886-635996219392369273.jpg", 10.801196, 106.684049));
        list.add(new CuaHang("CH028", "TL06", "Phúc Lộc - Ăn Vặt", "128 Vườn Chuối, P. 4, Quận 3, TP. HCM", 4.4, "https://images.foody.vn/res/g25/244522/prof/s640x400/foody-upload-api-foody-mobile-30-jpg-181011113153.jpg", 10.772319, 106.683818));
        list.add(new CuaHang("CH029", "TL06", "Ăn Vặt Cậu Mợ", "28 Hoa Cúc, P. 7, Phú Nhuận, TP. HCM", 4.5, "https://images.foody.vn/res/g93/923209/prof/s640x400/foody-upload-api-foody-mobile-ghgh-190711111353.jpg", 10.798877, 106.688206));
        list.add(new CuaHang("CH030", "TL06", "Bà Trùm Ăn Vặt", "233 Thoại Ngọc Hầu, P. Phú Thạnh, Tân Phú, TP. HCM", 4.5, "https://images.foody.vn/res/g74/736879/prof/s640x400/foody-upload-api-foody-mobile-ds-jpg-180427142933.jpg", 10.778231, 106.630243));

        //Bánh ngọt
        list.add(new CuaHang("CH031", "TL07", "My Flour Fairy", "70 Phú Châu, P. Tam Phú, Thủ Đức, TP. HCM", 4.3, "https://images.foody.vn/res/g77/767168/prof/s640x400/foody-upload-api-foody-mobile-hmkp-jpg-180815151712.jpg", 10.865324, 106.747137));
        list.add(new CuaHang("CH032", "TL07", "Mrs. Cake Ngọc Linh", "761 Nguyễn Trãi, P. 11, Quận 5, TP. HCM", 4.4, "https://images.foody.vn/res/g16/155022/prof/s640x400/foody-mobile-x1-jpg-146-635737811570597215.jpg", 10.752942, 106.659685));
        list.add(new CuaHang("CH033", "TL07", "Dallas Cakes & Coffee", "233 Đường 3 Tháng 2, P. 10, Quận 10, TP. HCM", 4.1, "https://images.foody.vn/res/g13/129958/prof/s640x400/foody-upload-api-foody-mobile-14-190620102021.jpg", 10.770736, 106.672910));
        list.add(new CuaHang("CH034", "TL07", "Cheesecake Ngon", "70 Đường Số 66, P. Thảo Điền, Quận 2, TP. HCM", 4.5, "https://images.foody.vn/res/g5/44454/prof/s640x400/foody-mobile-cheese-cake-jpg-576-636119657716824761.jpg", 10.812571, 106.729511));
        list.add(new CuaHang("CH035", "TL07", "Savouré Bakery", "30 Nguyễn Gia Trí (Đường D2), Bình Thạnh, TP. HCM", 4.5, "https://images.foody.vn/res/g8/70972/prof/s640x400/foody-mobile-ss5ad3ms-jpg-997-635870884090187576.jpg", 10.805968, 106.715849));

        //add to firebase
        for (int i = 0; i < list.size(); i++) {
            mData.child("CuaHang").child(list.get(i).getMacuahang()).setValue(list.get(i));
        }

    }

    //get data CuaHang
    public List<CuaHang> getAllData() {
        mData.child("CuaHang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cuaHangList.clear();
                //vòng lặp để lấy dữ liệu khi có sự thay đổi trên Firebase
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    //convert ra đối tượng HoaDon
                    CuaHang cuaHang = data.getValue(CuaHang.class);
                    // taikhoanAdapter.add(hoaDon);
                    cuaHangList.add(cuaHang);
                }

                //sort quán ăn theo rating
                Collections.sort(cuaHangList, new Comparator<CuaHang>() {
                    @Override
                    public int compare(CuaHang o1, CuaHang o2) {
                        return Double.valueOf(o2.getRating()).compareTo(o1.getRating());
                    }
                });

                ((Yeu_thichActivity) context).capnhatLV();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return cuaHangList;
    }

    //get CuaHang within 10km
    public List<CuaHang_temp> getTemp(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Permission already Granted
            //Do your work here
            //Perform operations here only which requires permission
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) this);
        final Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        mData.child("CuaHang").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                temp.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    //convert ra đối tượng HoaDon
                    CuaHang cuaHang = data.getValue(CuaHang.class);
                    if (location != null) {
                        double khoangcach = distanceBetween2Points(location.getLatitude(), location.getLongitude(), cuaHang.getLatitude(), cuaHang.getLongitude());
                        if (khoangcach < 10) {
                            temp.add(new CuaHang_temp(
                                    cuaHang.getMacuahang(),
                                    cuaHang.getTencuahang(),
                                    cuaHang.getDiachi(),
                                    cuaHang.getRating(),
                                    cuaHang.getHinhanh(),
                                    cuaHang.getLatitude(),
                                    cuaHang.getLongitude(),
                                    khoangcach
                            ));
                        }
                    }
                }

                //sort khoảng cách đến quán ăn theo thứ tự tăng dần
                Collections.sort(temp, new Comparator<CuaHang_temp>() {
                    @Override
                    public int compare(CuaHang_temp o1, CuaHang_temp o2) {
                        return Double.valueOf(o1.getKhoangcach()).compareTo(o2.getKhoangcach());
                    }
                });

                home_fragment.capnhatLV();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return temp;
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


