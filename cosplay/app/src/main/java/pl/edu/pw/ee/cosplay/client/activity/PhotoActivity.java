package pl.edu.pw.ee.cosplay.client.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.adapter.OnePhotoAdapter;
import pl.edu.pw.ee.cosplay.client.fragment.UserFragment;
import pl.edu.pw.ee.cosplay.client.networking.ServerTask;
import pl.edu.pw.ee.cosplay.client.utils.Utils;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.RatingData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addcomment.AddCommentInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addcomment.AddCommentOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addvote.AddVoteInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addvote.AddVoteOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto.GetPhotoInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto.GetPhotoOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.PhotosOrder;
import pl.edu.pw.ee.cosplay.rest.model.controller.user.GetUserInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.user.GetUserOutput;
import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

public class PhotoActivity extends AppCompatActivity {

    GetPhotoInput getPhotoInput;
    PhotoActivity context;
    OnePhotoAdapter onePhotoAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        getPhotoInput = (GetPhotoInput) getIntent().getSerializableExtra(MenuActivity.GET_PHOTO_ID);
        final RelativeLayout onePhotoLayoutId = (RelativeLayout) findViewById(R.id.onePhotoLayoutId);
        onePhotoLayoutId.setVisibility(View.INVISIBLE);

        (new ServerTask<GetPhotoInput, GetPhotoOutput, PhotoActivity>(this, getPhotoInput, UrlData.GET_PHOTO_PATH){

            @Override
            protected void doSomethingWithOutput(GetPhotoOutput o) {
                setActivityData(o);
                onePhotoAdapter = new OnePhotoAdapter(activity, R.layout.comment_item, o.getComments());
                listView = (ListView) findViewById(R.id.commentListView);
                listView.setAdapter(onePhotoAdapter);
                onePhotoLayoutId.setVisibility(View.VISIBLE);
            }
        }).execute();
        context = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void setActivityData(final GetPhotoOutput data) {
        TextView oneCharacterTextView = (TextView) findViewById(R.id.oneCharacterTextView);
        TextView oneDateTextView = (TextView) findViewById(R.id.oneDateTextView);
        TextView oneDescriptionTextView = (TextView) findViewById(R.id.oneDescriptionTextView);
        TextView oneFranchiseTextView = (TextView) findViewById(R.id.oneFranchiseTextView);

        TextView oneUserTextView = (TextView) findViewById(R.id.oneUserTextView);
        ImageView oneAvatarImageButton = (ImageView) findViewById(R.id.oneAvatarImageButton);
        final ImageView onePhotoImageView = (ImageView) findViewById(R.id.onePhotoImageView);

        //Photo
        Utils.setImageViewByBytesArray(onePhotoImageView, data.getPhotoBinaryData());
        rating(data);


        //Lists
        oneCharacterTextView.setText(Utils.parseReadableList(data.getCharactersList()));
        oneFranchiseTextView.setText(Utils.parseReadableList(data.getFranchisesList()));

        //Des
        oneUserTextView.setText(data.getUsername());
        setTitle(data.getUsername() + "'s photo");
        oneDateTextView.setText(Utils.formatDate(data.getUploadDate()));
        oneDescriptionTextView.setText(data.getDescription());

        //Avatar
        if(data.getAvatarBinaryData() != null){
            Utils.setImageViewByBytesArray(oneAvatarImageButton, data.getAvatarBinaryData());
        } else {
            oneAvatarImageButton.setImageResource(R.mipmap.user);
        }

        oneAvatarImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MenuActivity.class);
                i.putExtra(MenuActivity.USERNAME_ID, data.getUsername());
                startActivity(i);
                MenuActivity.fa.finish();
                finish();
            }
        });

//        onePhotoImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final AlertDialog.Builder popDialog = new AlertDialog.Builder(context);
//                final LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
//                final View photoLayout = inflater.inflate(R.layout.popup_photo, (ViewGroup) findViewById(R.id.layout_photo));
//                popDialog.setView(photoLayout);
//                final ImageView imageView = (ImageView) photoLayout.findViewById(R.id.popupPhotoImageView);
//                Drawable clone = onePhotoImageView.getDrawable().getConstantState().newDrawable();
//                imageView.setImageDrawable(clone);
//                popDialog.show();
//            }
//        });

        View layout = findViewById(R.id.photoRatingLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder popDialog = new AlertDialog.Builder(context);
                final LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                final View voteLayout = inflater.inflate(R.layout.activity_vote, (ViewGroup) findViewById(R.id.layout_vote));
                popDialog.setView(voteLayout);
                voteLogic(voteLayout, popDialog);
                popDialog.show();
            }
        });
    }

    private void rating(GetPhotoOutput data) {
        //Rating
        TextView oneGeneralTextView = (TextView) findViewById(R.id.oneGeneralTextView);
        TextView oneQualityTextView = (TextView) findViewById(R.id.oneQualityTextView);
        TextView oneSimilarityTextView = (TextView) findViewById(R.id.oneSimilarityTextView);
        TextView oneArrangementTextView = (TextView) findViewById(R.id.oneArrangementTextView);
        oneGeneralTextView.setText(data.getRatingData().getGeneralRate().toString());
        oneQualityTextView.setText(data.getRatingData().getQualityRate().toString());
        oneSimilarityTextView.setText(data.getRatingData().getSimilarityRate().toString());
        oneArrangementTextView.setText(data.getRatingData().getArrangementRate().toString());
        oneGeneralTextView.postInvalidate();
        oneQualityTextView.postInvalidate();
        oneSimilarityTextView.postInvalidate();
        oneArrangementTextView.postInvalidate();
    }

    private void voteLogic(final View voteLayout, final AlertDialog.Builder popDialog) {
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
                addVoteInput.setPhotoId(getPhotoInput.getPhotoId());
                addVoteInput.setRatingData(ratingData);
                (new ServerTask<AddVoteInput, AddVoteOutput, PhotoActivity>(context,addVoteInput,UrlData.ADD_VOTE_PATH){

                    @Override
                    protected void doSomethingWithOutput(AddVoteOutput o) {
                        voteLayout.setVisibility(View.INVISIBLE);
                        GetPhotoInput photoInput = new GetPhotoInput();
                        photoInput.setPhotoId(input.getPhotoId());
                        (new ServerTask<GetPhotoInput, GetPhotoOutput, PhotoActivity>(context,photoInput,UrlData.GET_PHOTO_PATH){

                            @Override
                            protected void doSomethingWithOutput(GetPhotoOutput o) {
                                rating(o);
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

    public void addCommentClick(View view) {
        final EditText editText = (EditText) findViewById(R.id.commentEditText);
        AddCommentInput addCommentInput = new AddCommentInput();
        addCommentInput.setAuthenticationData(LoginActivity.authenticationData);
        addCommentInput.setComment(editText.getText().toString());
        addCommentInput.setPhotoId(getPhotoInput.getPhotoId());
        (new ServerTask<AddCommentInput, AddCommentOutput, PhotoActivity>(this, addCommentInput, UrlData.ADD_COMMENT_CONTROLLER) {
                    @Override
                    protected void doSomethingWithOutput(AddCommentOutput o) {
                        Toast.makeText(activity, "Comment sent", Toast.LENGTH_SHORT).show();
                        (new ServerTask<GetPhotoInput, GetPhotoOutput, PhotoActivity>(activity, getPhotoInput, UrlData.GET_PHOTO_PATH){

                            @Override
                            protected void doSomethingWithOutput(GetPhotoOutput o) {
                                onePhotoAdapter = new OnePhotoAdapter(activity, R.layout.comment_item, o.getComments());
                                listView = (ListView) findViewById(R.id.commentListView);
                                listView.setAdapter(onePhotoAdapter);
                                editText.setText("");
                                editText.postInvalidate();
                            }
                        }).execute();
                    }
        }).execute();
    }


}
