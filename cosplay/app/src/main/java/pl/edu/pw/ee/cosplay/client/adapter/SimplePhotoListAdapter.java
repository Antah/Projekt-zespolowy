package pl.edu.pw.ee.cosplay.client.adapter;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.activity.MenuActivity;
import pl.edu.pw.ee.cosplay.client.activity.PhotoActivity;
import pl.edu.pw.ee.cosplay.client.fragment.AllPhotosFragment;
import pl.edu.pw.ee.cosplay.client.networking.ServerTask;
import pl.edu.pw.ee.cosplay.client.utils.Utils;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.SimplePhotoData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto.GetPhotoInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListOutput;

/**
 * Created by Michał on 2016-06-03.
 */
public class SimplePhotoListAdapter extends ArrayAdapter<SimplePhotoData> {
    private final GetPhotosListOutput getPhotosListOutput;
    private GetPhotosListInput getPhotosListInput;
    private MenuActivity activity;

    public SimplePhotoListAdapter(Context context, int resource, GetPhotosListOutput getPhotosListOutput, GetPhotosListInput getPhotosListInput, MenuActivity activity) {
        super(context, resource, getPhotosListOutput.getSimplePhotoDataList());
        this.getPhotosListOutput = getPhotosListOutput;
        this.getPhotosListInput = getPhotosListInput;
        this.activity = activity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        SimplePhotoData simplePhotoData = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_photo_item, parent, false);
        }

        setViewBySimplePhotoData(convertView, simplePhotoData);

        Button showMoreButton = (Button) convertView.findViewById(R.id.showMoreButton);

        ImageView simplePhotoImageView = (ImageView) convertView.findViewById(R.id.simplePhotoImageView);

        final GetPhotoInput getPhotoInput = new GetPhotoInput();
        getPhotoInput.setPhotoId(getPhotosListOutput.getSimplePhotoDataList().get(position).getPhotoId());

        simplePhotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(activity, PhotoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(MenuActivity.GET_PHOTO_ID, getPhotoInput);
                        intent.putExtras(bundle);
                        activity.startActivity(intent);
            }
        });

        if((getCount() - 1 == position)&&getPhotosListOutput.getAreThereNextPhotos()){
            showMoreButton.setVisibility(View.VISIBLE);
            showMoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getPhotosListInput.setRangeFirst(getPhotosListInput.getRangeFirst() + 1);
                    getPhotosListInput.setRangeLast(getPhotosListInput.getRangeFirst() + MenuActivity.RANGE);
                    (new ServerTask<GetPhotosListInput, GetPhotosListOutput, MenuActivity>(activity, getPhotosListInput, UrlData.GET_PHOTOS_LIST_PATH) {
                        @Override protected void doSomethingWithOutput(GetPhotosListOutput o) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(MenuActivity.GET_PHOTOS_LIST_OUTPUT, o);
                            bundle.putSerializable(MenuActivity.GET_PHOTOS_LIST_INPUT, this.input);

                            Fragment newFragment = new AllPhotosFragment();
                            newFragment.setArguments(bundle);
                            FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();

                            transaction.replace(R.id.fragmentPlaceHolder, newFragment);
                            transaction.addToBackStack(null);

                            transaction.commit();
                        }
                    }).execute();
                }
            });
        } else {
            showMoreButton.setVisibility(View.GONE);
        }

        return convertView;
    }

    private void setViewBySimplePhotoData(View convertView, SimplePhotoData simplePhotoData) {
        TextView usernameTextView = (TextView) convertView.findViewById(R.id.userNameTextView);
        usernameTextView.setText(simplePhotoData.getUsername());

        TextView dateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
        dateTextView.setText(Utils.formatDate(simplePhotoData.getUploadDate()));

        TextView franchiseTextView = (TextView) convertView.findViewById(R.id.franchiseTextView);
        franchiseTextView.setText(Utils.parseReadableList(simplePhotoData.getFranchisesList()));

        TextView characterTextView = (TextView) convertView.findViewById(R.id.characterTextView);
        characterTextView.setText(Utils.parseReadableList(simplePhotoData.getCharactersList()));

        TextView commentsTextView = (TextView) convertView.findViewById(R.id.commentsTextView);
        commentsTextView.setText(simplePhotoData.getCommentsNumber().toString());

        TextView generalTextView = (TextView) convertView.findViewById(R.id.generalTextView);
        generalTextView.setText(simplePhotoData.getRatingData().getGeneralRate().toString());

        TextView similarityTextView = (TextView) convertView.findViewById(R.id.similarityTextView);
        similarityTextView.setText(simplePhotoData.getRatingData().getSimilarityRate().toString());

        TextView arrangementTextView = (TextView) convertView.findViewById(R.id.arrangementTextView);
        arrangementTextView.setText(simplePhotoData.getRatingData().getArrangementRate().toString());

        TextView qualityTextView = (TextView) convertView.findViewById(R.id.qualityTextView);
        qualityTextView.setText(simplePhotoData.getRatingData().getQualityRate().toString());

        ImageButton avatarImageButton = (ImageButton) convertView.findViewById(R.id.avatarImageButton);
        if(simplePhotoData.getAvatarBinaryData() != null) {
            Utils.setImageViewByBytesArray(avatarImageButton, simplePhotoData.getAvatarBinaryData());
        }

        ImageView simplePhotoImageView = (ImageView) convertView.findViewById(R.id.simplePhotoImageView);
        Utils.setImageViewByBytesArray(simplePhotoImageView, simplePhotoData.getPhotoBinaryData());

    }
}
