package Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.polyfood.R;
import com.example.polyfood.View.An_vatActivity;
import com.example.polyfood.View.Banh_ngotActivity;
import com.example.polyfood.View.Do_anActivity;
import com.example.polyfood.View.Do_chayActivity;
import com.example.polyfood.View.Do_uongActivity;
import com.example.polyfood.View.KemActivity;
import com.example.polyfood.View.Thuc_an_nhanhActivity;
import com.example.polyfood.View.Yeu_thichActivity;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import adapter.CuaHangAdapter_temp;
import adapter.CustomGridViewActivity;
import adapter.SliderAdapter;
import dao.CuaHangDAO;
import model.CuaHang_temp;

public class Home_Fragment extends Fragment implements LocationListener {
    public static double Latitude, Longitude;
    SliderView sliderView;
    List<CuaHang_temp> temp = new ArrayList<>();
    ImageView btn_reaload;
    TextView tv_reload;
    GridView gridView;
    String[] gridViewString = {"Đồ ăn", "Đồ uống", "Đồ chay", "Thức ăn nhanh", "Kem", "Ăn vặt", "Bánh ngọt", "Yêu thích"};
    int[] gridViewImageId = {
            R.drawable.do_an,
            R.drawable.do_uong,
            R.drawable.do_chay,
            R.drawable.do_an_nhanh,
            R.drawable.kem,
            R.drawable.do_an_vat,
            R.drawable.banh_ngot,
            R.drawable.yeu_thich,
    };
    ListView lv_cuahang;
    CuaHangAdapter_temp cuaHangAdapter_temp;
    LocationManager locationManager;
    boolean GpsStatus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(getContext(), gridViewString, gridViewImageId);
        gridView = view.findViewById(R.id.gridView);
        gridView.setAdapter(adapterViewAndroid);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent i = new Intent(getActivity(), Do_anActivity.class);
                    startActivity(i);
                } else if (position == 1) {
                    Intent i = new Intent(getActivity(), Do_uongActivity.class);
                    startActivity(i);
                } else if (position == 2) {
                    Intent i = new Intent(getActivity(), Do_chayActivity.class);
                    startActivity(i);
                } else if (position == 3) {
                    Intent i = new Intent(getActivity(), Thuc_an_nhanhActivity.class);
                    startActivity(i);
                } else if (position == 4) {
                    Intent i = new Intent(getActivity(), KemActivity.class);
                    startActivity(i);
                } else if (position == 5) {
                    Intent i = new Intent(getActivity(), An_vatActivity.class);
                    startActivity(i);
                } else if (position == 6) {
                    Intent i = new Intent(getActivity(), Banh_ngotActivity.class);
                    startActivity(i);
                } else if (position == 7) {
                    Intent i = new Intent(getActivity(), Yeu_thichActivity.class);
                    startActivity(i);
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

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

//        if (location != null) {
//            Latitude = location.getLatitude();
//            Longitude = location.getLongitude();
//        }

        //ánh xạ
        lv_cuahang = view.findViewById(R.id.lv_cuahang);
        btn_reaload = view.findViewById(R.id.btn_reload);
        tv_reload = view.findViewById(R.id.tv_reload);
        sliderView = view.findViewById(R.id.imgSlider);

        //custom slider
        SliderAdapter adapter = new SliderAdapter(getActivity());
        sliderView.setSliderAdapter(adapter);
        //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setIndicatorAnimation(IndicatorAnimations.FILL);
//        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEOUTROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        //set scroll delay in seconds
        sliderView.setScrollTimeInSec(3);
        sliderView.startAutoCycle();

        //set INVISIBLE
        btn_reaload.setVisibility(View.INVISIBLE);
        tv_reload.setVisibility(View.INVISIBLE);

        getTemp();

        //reaload listview
        if (location == null) {
            btn_reaload.setVisibility(View.VISIBLE);
            tv_reload.setVisibility(View.VISIBLE);
        }

        btn_reaload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                if (GpsStatus == true) {
                    getTemp();
                    btn_reaload.setVisibility(View.INVISIBLE);
                    tv_reload.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(getActivity(), "Bạn chưa bật vị trí của thiết bị!", Toast.LENGTH_SHORT).show();
                }
            }
        });


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

    public void getTemp() {
        CuaHangDAO cuaHangDAO = new CuaHangDAO(Home_Fragment.this);
        //cuaHangDAO.putData();
        temp = cuaHangDAO.getTemp(getActivity());

        cuaHangAdapter_temp = new CuaHangAdapter_temp(getActivity(), R.layout.item_cuahang, temp);
        lv_cuahang.setAdapter(cuaHangAdapter_temp);
    }

    public void capnhatLV() {
        cuaHangAdapter_temp.notifyDataSetChanged();
    }

}
