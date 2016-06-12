package pl.edu.pw.ee.cosplay.client.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.adapter.OnePhotoAdapter;
import pl.edu.pw.ee.cosplay.client.networking.ServerTask;
import pl.edu.pw.ee.cosplay.client.utils.Utils;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addcomment.AddCommentInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addcomment.AddCommentOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto.GetPhotoOutput;
import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

public class PhotoActivity extends AppCompatActivity {

    GetPhotoOutput getPhotoOutput;
    AuthenticationData authenticationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPhotoOutput = (GetPhotoOutput) getIntent().getSerializableExtra(MenuActivity.GET_PHOTO_OUTPUT);
        authenticationData = (AuthenticationData) getIntent().getSerializableExtra(LoginActivity.AUTHENTICATION_DATA);
        setContentView(R.layout.activity_photo);
        setActivityData(getPhotoOutput);
        OnePhotoAdapter onePhotoAdapter = new OnePhotoAdapter(this, R.layout.comment_item, getPhotoOutput.getComments());
        ListView listView = (ListView) findViewById(R.id.commentListView);
        listView.setAdapter(onePhotoAdapter);
    }

    public void setActivityData(GetPhotoOutput data) {
        TextView oneArrangementTextView = (TextView) findViewById(R.id.oneArrangementTextView);
        TextView oneCharacterTextView = (TextView) findViewById(R.id.oneCharacterTextView);
        TextView oneDateTextView = (TextView) findViewById(R.id.oneDateTextView);
        TextView oneDescriptionTextView = (TextView) findViewById(R.id.oneDescriptionTextView);
        TextView oneFranchiseTextView = (TextView) findViewById(R.id.oneFranchiseTextView);
        TextView oneGeneralTextView = (TextView) findViewById(R.id.oneGeneralTextView);
        TextView oneQualityTextView = (TextView) findViewById(R.id.oneQualityTextView);
        TextView oneSimilarityTextView = (TextView) findViewById(R.id.oneSimilarityTextView);
        TextView oneUserTextView = (TextView) findViewById(R.id.oneUserTextView);
        ImageButton oneAvatarImageButton = (ImageButton) findViewById(R.id.oneAvatarImageButton);
        ImageView onePhotoImageView = (ImageView) findViewById(R.id.onePhotoImageView);

        //Photo
        Utils.setImageViewByBytesArray(onePhotoImageView, data.getPhotoBinaryData());

        //Rating
        oneGeneralTextView.setText(data.getRatingData().getGeneralRate().toString());
        oneQualityTextView.setText(data.getRatingData().getQualityRate().toString());
        oneSimilarityTextView.setText(data.getRatingData().getSimilarityRate().toString());
        oneArrangementTextView.setText(data.getRatingData().getArrangementRate().toString());

        //Lists
        oneCharacterTextView.setText(Utils.parseReadableList(data.getCharactersList()));
        oneFranchiseTextView.setText(Utils.parseReadableList(data.getFranchisesList()));

        //Des
        oneUserTextView.setText(data.getUsername());
        oneDateTextView.setText(Utils.formatDate(data.getUploadDate()));
        oneDescriptionTextView.setText(data.getDescription());

        //Avatar
        if(data.getAvatarBinaryData() != null){
            Utils.setImageViewByBytesArray(oneAvatarImageButton, data.getAvatarBinaryData());
        }
    }

    public void addCommentClick(View view) {
        EditText editText = (EditText) findViewById(R.id.commentEditText);
        AddCommentInput addCommentInput = new AddCommentInput();
        addCommentInput.setAuthenticationData(LoginActivity.authenticationData);
        addCommentInput.setComment(editText.getText().toString());
        addCommentInput.setPhotoId(getPhotoOutput.getPhotoId());
        (new ServerTask<AddCommentInput, AddCommentOutput, PhotoActivity>(this, addCommentInput, UrlData.ADD_COMMENT_CONTROLLER) {
                    @Override
                    protected void doSomethingWithOutput(AddCommentOutput o) {
                        Toast.makeText(activity, "Comment sent", Toast.LENGTH_SHORT).show();
                    }
        }).execute();
    }
}
