package pl.edu.pw.ee.cosplay.client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.SimplePhotoData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListOutput;

/**
 * Created by Michał on 2016-06-03.
 */
public class SimplePhotoListAdapter extends ArrayAdapter<SimplePhotoData> {
    private final GetPhotosListOutput getPhotosListOutput;

    public SimplePhotoListAdapter(Context context, int resource, GetPhotosListOutput getPhotosListOutput) {
        super(context, resource, getPhotosListOutput.getSimplePhotoDataList());
        this.getPhotosListOutput = getPhotosListOutput;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SimplePhotoData simplePhotoData = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_photo_item, parent, false);
        }

        TextView usernameTextView = (TextView) convertView.findViewById(R.id.userNameTextView);
        usernameTextView.setText(simplePhotoData.getUsername());

        Button showMoreButton = (Button) convertView.findViewById(R.id.showMoreButton);
        if(getCount() - 1 == position){
            showMoreButton.setVisibility(View.VISIBLE);
        } else {
            showMoreButton.setVisibility(View.GONE);
        }

        return convertView;
    }
}
