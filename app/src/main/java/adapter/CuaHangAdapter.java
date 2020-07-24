package adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.polyfood.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import model.CuaHang;

public class CuaHangAdapter extends ArrayAdapter<CuaHang> {
    Activity context;
    int resource;
    List<CuaHang> objects;

    public CuaHangAdapter(Activity context, int resource, List<CuaHang> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public static String distanceBetween2Points(double la1, double lo1, double la2, double lo2) {
        double dLat = (la2 - la1) * (Math.PI / 180);
        double dLon = (lo2 - lo1) * (Math.PI / 180);
        double la1ToRad = la1 * (Math.PI / 180);
        double la2ToRad = la2 * (Math.PI / 180);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(la1ToRad) * Math.cos(la2ToRad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6371 * c;

        //format number
        NumberFormat formatter = new DecimalFormat("#0.0");
        return formatter.format(d);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = this.context.getLayoutInflater();
            convertView = inflater.inflate(this.resource, null);
            viewHolder.img_cuahang = convertView.findViewById(R.id.img_cuahang);
            viewHolder.tv_tenquan = convertView.findViewById(R.id.tv_tenquan);
            viewHolder.tv_diachi = convertView.findViewById(R.id.tv_address);
            viewHolder.tv_rating = convertView.findViewById(R.id.tv_rating);
            viewHolder.tv_khoangcach = convertView.findViewById(R.id.tv_khoangcach);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final CuaHang cuaHang = this.objects.get(position);
        if (cuaHang.getTencuahang().length() > 20) {
            viewHolder.tv_tenquan.setText(cuaHang.getTencuahang().substring(0, 20) + " ...");
        } else {
            viewHolder.tv_tenquan.setText(cuaHang.getTencuahang());
        }
        if (cuaHang.getDiachi().length() > 30) {
            viewHolder.tv_diachi.setText(cuaHang.getDiachi().substring(0, 30) + " ...");
        } else {
            viewHolder.tv_diachi.setText(cuaHang.getDiachi());
        }
        viewHolder.tv_rating.setText(String.valueOf(cuaHang.getRating()));
        try {
            Picasso.with(context).load(cuaHang.getHinhanh()).into(viewHolder.img_cuahang);
        } catch (Exception e) {

        }

        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
        }

        Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            viewHolder.tv_khoangcach.setText(distanceBetween2Points(location.getLatitude(), location.getLongitude(), cuaHang.getLatitude(), cuaHang.getLongitude()) + " km");
        }

        //animation item
        //Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_listview);
//        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
//        animation.setDuration(1000);
//        convertView.startAnimation(animation);

        return convertView;
    }

    static class ViewHolder {
        ImageView img_cuahang;
        TextView tv_tenquan, tv_diachi, tv_rating, tv_khoangcach;
    }
}
