package adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.polyfood.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import model.Menu;

public class MenuAdapter extends ArrayAdapter<Menu> {
    Activity context;
    int resource;
    List<Menu> objects;

    public MenuAdapter(Activity context, int resource, List<Menu> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = this.context.getLayoutInflater();
            convertView = inflater.inflate(this.resource, null);
            viewHolder.img_menu = convertView.findViewById(R.id.img_menu);
            viewHolder.tv_tenmonan = convertView.findViewById(R.id.tv_tenmonan);
            viewHolder.tv_gia = convertView.findViewById(R.id.tv_gia);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Menu menu = this.objects.get(position);
        if (menu.getTenmonan().length() < 20) {
            viewHolder.tv_tenmonan.setText(menu.getTenmonan());
        } else {
            viewHolder.tv_tenmonan.setText(menu.getTenmonan().substring(0, 20) + " ...");
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        viewHolder.tv_gia.setText(formatter.format(menu.getGia()) + " VND");
        try {
            Picasso.with(context).load(menu.getHinhanh()).into(viewHolder.img_menu);
        } catch (Exception e) {
            Picasso.with(context).load("https://firebasestorage.googleapis.com/v0/b/polyfood-7fcd7.appspot.com/o/no_image.jpg?alt=media&token=fa11b05a-5e3e-4f0b-a172-f10dad5208f6").into(viewHolder.img_menu);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView img_menu;
        TextView tv_tenmonan, tv_gia;
    }
}
