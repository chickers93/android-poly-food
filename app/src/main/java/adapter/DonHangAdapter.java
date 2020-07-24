package adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.polyfood.R;

import java.util.List;

import model.DonHang;


public class DonHangAdapter extends BaseAdapter {
    Activity context;
    int resource;
    List<DonHang> objects;

    public DonHangAdapter(Activity context, int resource, List<DonHang> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = this.context.getLayoutInflater();
            convertView = inflater.inflate(this.resource, null);
            viewHolder.tv_madonhang = convertView.findViewById(R.id.tv_madonhang);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            viewHolder.tv_trangthai = convertView.findViewById(R.id.tv_trangthai);
            viewHolder.img_color = convertView.findViewById(R.id.img_color);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DonHang donHang = this.objects.get(position);
        viewHolder.tv_madonhang.setText("#" + donHang.getId());
        viewHolder.tv_time.setText(donHang.getTime());
        viewHolder.tv_trangthai.setText(donHang.getStatus());
        if (donHang.getStatus().equals("Chờ xử lý") || donHang.getStatus().equals("Đã hủy")) {
            viewHolder.tv_trangthai.setTextColor(context.getResources().getColor(R.color.profilePrimaryDark));
            viewHolder.img_color.setBackgroundColor(context.getResources().getColor(R.color.profilePrimaryDark));
        }
        if (donHang.getStatus().equals("Đã xác nhận") || donHang.getStatus().equals("Hoàn thành")) {
            viewHolder.tv_trangthai.setTextColor(context.getResources().getColor(R.color.settings));
            viewHolder.img_color.setBackgroundColor(context.getResources().getColor(R.color.settings));
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView img_color;
        TextView tv_madonhang, tv_time, tv_trangthai;
    }
}
