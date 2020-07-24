package adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.polyfood.R;

import java.util.List;

import model.Comment;

public class CommentAdapter extends ArrayAdapter<Comment> {
    Activity context;
    int resource;
    List<Comment> objects;

    public CommentAdapter(Activity context, int resource, List<Comment> objects) {
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
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            viewHolder.tv_content = convertView.findViewById(R.id.tv_content);
            viewHolder.ratingBar = convertView.findViewById(R.id.ratingBar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Comment comment = this.objects.get(position);
        viewHolder.tv_name.setText(comment.getName());
        viewHolder.tv_time.setText(comment.getTime());
        viewHolder.tv_content.setText(comment.getContent());
        viewHolder.ratingBar.setRating(comment.getRating());

        return convertView;
    }

    static class ViewHolder {
        TextView tv_name, tv_time, tv_content;
        RatingBar ratingBar;
    }
}
