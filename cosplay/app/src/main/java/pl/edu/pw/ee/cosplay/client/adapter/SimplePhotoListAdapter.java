package pl.edu.pw.ee.cosplay.client.adapter;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.activity.LoginActivity;
import pl.edu.pw.ee.cosplay.client.activity.MenuActivity;
import pl.edu.pw.ee.cosplay.client.activity.PhotoActivity;
import pl.edu.pw.ee.cosplay.client.fragment.AllPhotosFragment;
import pl.edu.pw.ee.cosplay.client.networking.ServerTask;
import pl.edu.pw.ee.cosplay.client.utils.Utils;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.RatingData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.SimplePhotoData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addvote.AddVoteInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addvote.AddVoteOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto.GetPhotoInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto.GetPhotoOutput;
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

        final SimplePhotoData simplePhotoData = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_photo_item, parent, false);
        }

        setViewBySimplePhotoData(convertView, simplePhotoData);

        Button showMoreButton = (Button) convertView.findViewById(R.id.showMoreButton);

        ImageView simplePhotoImageView = (ImageView) convertView.findViewById(R.id.simplePhotoImageView);
        ImageView avatarImageButton = (ImageView) convertView.findViewById(R.id.avatarImageButton);


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

        avatarImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openProfileFragment(simplePhotoData.getUsername());
            }
        });

        if((getCount() - 1 == position)&&getPhotosListOutput.getAreThereNextPhotos()){
            showMoreButton.setVisibility(View.VISIBLE);
            showMoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getPhotosListInput.setRangeFirst(getPhotosListInput.getRangeLast() + 1);
                    getPhotosListInput.setRangeLast(getPhotosListInput.getRangeFirst() + MenuActivity.RANGE - 1);
                    (new ServerTask<GetPhotosListInput, GetPhotosListOutput, MenuActivity>(activity, getPhotosListInput, UrlData.GET_PHOTOS_LIST_PATH) {
                        @Override protected void doSomethingWithOutput(GetPhotosListOutput o) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(MenuActivity.GET_PHOTOS_LIST_OUTPUT, o);
                            bundle.putSerializable(MenuActivity.GET_PHOTOS_LIST_INPUT, this.input);

                            Fragment newFragment = new AllPhotosFragment();
                            newFragment.setArguments(bundle);
                            FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();

                            transaction.replace(R.id.fragmentPlaceHolder, newFragment);

                            transaction.commit();
                        }
                    }).execute();
                }
            });
        } else {
            showMoreButton.setVisibility(View.GONE);
        }

        final View finalConvertView = convertView;
        View layout = finalConvertView.findViewById(R.id.itemRatingLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder popDialog = new AlertDialog.Builder(activity);
                final LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View voteLayout = inflater.inflate(R.layout.activity_vote, (ViewGroup) finalConvertView.findViewById(R.id.layout_vote));
                popDialog.setView(voteLayout);
                voteLogic(voteLayout, popDialog, simplePhotoData.getPhotoId(), finalConvertView);
                popDialog.show();
            }
        });

        return convertView;
    }

    private void voteLogic(final View voteLayout, final AlertDialog.Builder popDialog, final Integer Id, final View convertView) {
        ArrayList<ImageView> similarity = new ArrayList<>();
        similarity.add((ImageView) voteLayout.findViewById(R.id.s1));
        similarity.add((ImageView) voteLayout.findViewById(R.id.s2));
        similarity.add((ImageView) voteLayout.findViewById(R.id.s3));
        similarity.add((ImageView) voteLayout.findViewById(R.id.s4));
        similarity.add((ImageView) voteLayout.findViewById(R.id.s5));
        ArrayList<ImageView> quality = new ArrayList<>();
        quality.add((ImageView) voteLayout.findViewById(R.id.q1));
        quality.add((ImageView) voteLayout.findViewById(R.id.q2));
        quality.add((ImageView) voteLayout.findViewById(R.id.q3));
        quality.add((ImageView) voteLayout.findViewById(R.id.q4));
        quality.add((ImageView) voteLayout.findViewById(R.id.q5));
        ArrayList<ImageView> arrangement = new ArrayList<>();
        arrangement.add((ImageView) voteLayout.findViewById(R.id.a1));
        arrangement.add((ImageView) voteLayout.findViewById(R.id.a2));
        arrangement.add((ImageView) voteLayout.findViewById(R.id.a3));
        arrangement.add((ImageView) voteLayout.findViewById(R.id.a4));
        arrangement.add((ImageView) voteLayout.findViewById(R.id.a5));
        final RatingData ratingData;
        ratingData = new RatingData();
        ratingData.setArrangementRate(3);
        ratingData.setQualityRate(3);
        ratingData.setSimilarityRate(3);
        makeListeners(similarity, ratingData, Rating.SIMILARITY);
        makeListeners(quality, ratingData, Rating.QUALITY);
        makeListeners(arrangement, ratingData, Rating.ARRANGEMENT);
        popDialog.setPositiveButton("RATE", (new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                AddVoteInput addVoteInput = new AddVoteInput();
                addVoteInput.setAuthenticationData(LoginActivity.authenticationData);
                addVoteInput.setPhotoId(Id);
                addVoteInput.setRatingData(ratingData);
                (new ServerTask<AddVoteInput, AddVoteOutput, MenuActivity>(activity,addVoteInput,UrlData.ADD_VOTE_PATH){

                    @Override
                    protected void doSomethingWithOutput(AddVoteOutput o) {
                        voteLayout.setVisibility(View.INVISIBLE);
                        GetPhotoInput photoInput = new GetPhotoInput();
                        photoInput.setPhotoId(input.getPhotoId());
                        (new ServerTask<GetPhotoInput, GetPhotoOutput, MenuActivity>(activity,photoInput,UrlData.GET_PHOTO_PATH){

                            @Override
                            protected void doSomethingWithOutput(GetPhotoOutput o) {
                                rating(convertView, o.getRatingData());
                            }
                        }).execute();
                    }
                }).execute();
            }
        }));
    }

    enum Rating{
        SIMILARITY, QUALITY, ARRANGEMENT
    }

    private void makeListeners(final ArrayList<ImageView> rateList, final RatingData ratingData, final Rating ratingType) {
        for(int j = 0; j< rateList.size(); j++){
            final ImageView rate = rateList.get(j);
            final int finalJ = j;
            rate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer actualRate = 3;
                    switch (ratingType){
                        case SIMILARITY:
                            actualRate = ratingData.getSimilarityRate();
                            break;
                        case QUALITY:
                            actualRate = ratingData.getQualityRate();
                            break;
                        case ARRANGEMENT:
                            actualRate = ratingData.getArrangementRate();
                            break;
                    }
                    if (actualRate == finalJ + 1){
                        for(int i= 0; i < finalJ; i++){
                            rateList.get(i).setImageResource(R.mipmap.star);
                            rateList.get(i).postInvalidate();
                        }
                        for(int i= finalJ; i < rateList.size(); i++){
                            rateList.get(i).setImageResource(R.mipmap.blackstar);
                            rateList.get(i).postInvalidate();
                        }
                        switch (ratingType){
                            case SIMILARITY:
                                ratingData.setSimilarityRate(finalJ);
                                break;
                            case QUALITY:
                                ratingData.setQualityRate(finalJ);
                                break;
                            case ARRANGEMENT:
                                ratingData.setArrangementRate(finalJ);
                                break;
                        }
                    } else {
                        for(int i= 0; i < finalJ + 1; i++){
                            rateList.get(i).setImageResource(R.mipmap.star);
                            rateList.get(i).postInvalidate();
                        }
                        for(int i= finalJ + 1; i < rateList.size(); i++){
                            rateList.get(i).setImageResource(R.mipmap.blackstar);
                            rateList.get(i).postInvalidate();
                        }
                        switch (ratingType){
                            case SIMILARITY:
                                ratingData.setSimilarityRate(finalJ + 1);
                                break;
                            case QUALITY:
                                ratingData.setQualityRate(finalJ + 1);
                                break;
                            case ARRANGEMENT:
                                ratingData.setArrangementRate(finalJ + 1);
                                break;
                        }
                    }
                }
            });
        }
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

        rating(convertView, simplePhotoData.getRatingData());

        ImageView avatarImageButton = (ImageView) convertView.findViewById(R.id.avatarImageButton);
        if(simplePhotoData.getAvatarBinaryData() != null) {
            Utils.setImageViewByBytesArray(avatarImageButton, simplePhotoData.getAvatarBinaryData());
        } else {
            avatarImageButton.setImageResource(R.mipmap.user);
        }

        ImageView simplePhotoImageView = (ImageView) convertView.findViewById(R.id.simplePhotoImageView);
        Utils.setImageViewByBytesArray(simplePhotoImageView, simplePhotoData.getPhotoBinaryData());

    }

    private void rating(View convertView, RatingData ratingData) {
        TextView generalTextView = (TextView) convertView.findViewById(R.id.generalTextView);
        generalTextView.setText(ratingData.getGeneralRate().toString());

        TextView similarityTextView = (TextView) convertView.findViewById(R.id.similarityTextView);
        similarityTextView.setText(ratingData.getSimilarityRate().toString());

        TextView arrangementTextView = (TextView) convertView.findViewById(R.id.arrangementTextView);
        arrangementTextView.setText(ratingData.getArrangementRate().toString());

        TextView qualityTextView = (TextView) convertView.findViewById(R.id.qualityTextView);
        qualityTextView.setText(ratingData.getQualityRate().toString());
    }
}
