package pl.edu.pw.ee.cosplay.client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.utils.Utils;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.CommentData;

/**
 * Created by Michał on 2016-06-12.
 */
public class OnePhotoAdapter extends ArrayAdapter<CommentData> {
    public OnePhotoAdapter(Context context, int resource, List<CommentData> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        CommentData commentData = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_item, parent, false);
        }

        TextView commentUserNameTextView = (TextView) convertView.findViewById(R.id.commentUserNameTextView);
        ImageButton commentAvatarImageButton = (ImageButton) convertView.findViewById(R.id.commentAvatarImageButton);
        TextView commentDateTextView = (TextView) convertView.findViewById(R.id.commentDateTextView);
        TextView commentTextView = (TextView) convertView.findViewById(R.id.commentTextView);

        if(commentData.getAvatarBinaryData() != null){
            Utils.setImageViewByBytesArray(commentAvatarImageButton, commentData.getAvatarBinaryData());
        }
        commentUserNameTextView.setText(commentData.getUsername());
        commentDateTextView.setText(Utils.formatDate(commentData.getCommentDate()));
        commentTextView.setText(commentData.getComment());

        return convertView;
    }
}
