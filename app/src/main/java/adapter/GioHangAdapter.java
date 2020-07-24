package adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.polyfood.HomeActivity;
import com.example.polyfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import Fragment.Cart_Fragment;
import model.GioHang;

public class GioHangAdapter extends ArrayAdapter<GioHang> {
    Activity context;
    int resource;
    List<GioHang> objects;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    public GioHangAdapter(Activity context, int resource, List<GioHang> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = this.context.getLayoutInflater();
            convertView = inflater.inflate(this.resource, null);
            viewHolder.img_giohang = convertView.findViewById(R.id.img_giohang);
            viewHolder.tv_tenmonan = convertView.findViewById(R.id.tv_tenmonan);
            viewHolder.tv_soluong = convertView.findViewById(R.id.tv_soluong);
            viewHolder.tv_giatien = convertView.findViewById(R.id.tv_giatien);
            viewHolder.tv_delete = convertView.findViewById(R.id.tv_delete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final GioHang gioHang = this.objects.get(position);
        try {
            Picasso.with(context).load(gioHang.getHinhanh()).into(viewHolder.img_giohang);
        } catch (Exception e) {
        }
        viewHolder.tv_tenmonan.setText(gioHang.getTenmonan());
        viewHolder.tv_soluong.setText("Số lượng: " + gioHang.getSoluong());
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        viewHolder.tv_giatien.setText(formatter.format(gioHang.getGia()));
        try {
            viewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder altdial = new AlertDialog.Builder(context);
                    altdial.setTitle("Thông báo");
                    altdial.setMessage("Xóa món ăn \"" + gioHang.getTenmonan() + "\" khỏi giỏ hàng. Bạn có muốn tiếp tục?")
                            .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    HomeActivity.gioHangList.remove(HomeActivity.gioHangList.get(position));
                                    mData.child("GioHang").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(HomeActivity.gioHangList);
                                    notifyDataSetChanged();
                                    dialog.cancel();

                                    Cart_Fragment.refreshGioHang();
                                }
                            });
                    AlertDialog alert = altdial.create();
                    alert.show();
                }
            });
        } catch (Exception e) {

        }

        return convertView;
    }

    static class ViewHolder {
        ImageView img_giohang;
        TextView tv_tenmonan, tv_soluong, tv_giatien, tv_delete;
    }
}
