package Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.polyfood.Chi_tiet_Activity.Chitietquanan_Activity;
import com.example.polyfood.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapter.CommentAdapter;
import model.Comment;
import model.User;

public class Comment_Fragment extends Fragment {
    ListView lv_comment;
    CommentAdapter commentAdapter;
    List<Comment> commentList = new ArrayList<>();
    RatingBar ratingBar;
    EditText ed_comment;
    Button btn_comment;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    String name;

    public Comment_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ánh xạ
        lv_comment = view.findViewById(R.id.lv_comment);
        ratingBar = view.findViewById(R.id.ratingBar);
        ed_comment = view.findViewById(R.id.ed_comment);
        btn_comment = view.findViewById(R.id.btn_comment);
        ratingBar.setRating(3);
        getInfo();

        mData.child("Comment").child(Chitietquanan_Activity.macuahang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                commentList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Comment comment = data.getValue(Comment.class);
                    commentList.add(0, comment);
                }
                try {
                    commentAdapter = new CommentAdapter(getActivity(), R.layout.item_comment2, commentList);
                    lv_comment.setAdapter(commentAdapter);
                    commentAdapter.notifyDataSetChanged();
                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //sự kiện
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                getInfo();
                DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                if (ed_comment.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Bạn chưa nhập đánh giá!", Toast.LENGTH_SHORT).show();
                } else {
                    if (name != null) {
                        Comment comment = new Comment("", name, ed_comment.getText().toString(), ratingBar.getRating(), formater.format(date));
                        mData.child("Comment").child(Chitietquanan_Activity.macuahang).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(comment)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getActivity(), "Upload thành công!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        ed_comment.setText(null);
                        View view = getActivity().getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }
                }
            }
        });
    }

    //get thông tin user
    private void getInfo() {
        mData.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                name = user.getName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
