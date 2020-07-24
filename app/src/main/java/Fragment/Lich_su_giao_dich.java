package Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.polyfood.Chi_tiet_Activity.ChitietDonHang_Activity;
import com.example.polyfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapter.DonHangAdapter;
import model.DonHang;

public class Lich_su_giao_dich extends Fragment {
    ListView lv_donhang;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    List<DonHang> donHangList = new ArrayList<>();
    DonHangAdapter donHangAdapter;

    public Lich_su_giao_dich() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lich_su_giao_dich, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //ánh xạ
        lv_donhang = view.findViewById(R.id.lv_donhang);

        mData.child("DonHang").child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donHangList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    DonHang donHang = data.getValue(DonHang.class);
                    if (donHang.getStatus().equals("Hoàn thành") || donHang.getStatus().equals("Đã hủy")) {
                        donHangList.add(donHang);
                    }
                }

                donHangAdapter = new DonHangAdapter(getActivity(), R.layout.item_donhang, donHangList);
                lv_donhang.setAdapter(donHangAdapter);
                donHangAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //chi tiết đơn hàng
        lv_donhang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ChitietDonHang_Activity.class);
                intent.putExtra("madonhang", donHangList.get(position).getId());
                intent.putExtra("macuahang", donHangList.get(position).getMacuahang());
                intent.putExtra("trangthai", donHangList.get(position).getStatus());
                startActivity(intent);
            }
        });

    }
}
