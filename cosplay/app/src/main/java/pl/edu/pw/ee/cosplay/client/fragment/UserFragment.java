package pl.edu.pw.ee.cosplay.client.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.activity.LoginActivity;
import pl.edu.pw.ee.cosplay.client.activity.MenuActivity;
import pl.edu.pw.ee.cosplay.client.adapter.SimplePhotoListAdapter;
import pl.edu.pw.ee.cosplay.client.networking.ServerTask;
import pl.edu.pw.ee.cosplay.client.utils.Utils;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.avatar.ChangeAvatarInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.avatar.ChangeAvatarOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.PhotosOrder;
import pl.edu.pw.ee.cosplay.rest.model.controller.user.GetUserInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.user.GetUserOutput;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {


    public UserFragment(){
    }

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_your_profile, container, false);
        GetPhotosListOutput getPhotosListOutput = (GetPhotosListOutput) getArguments().getSerializable(MenuActivity.GET_PHOTOS_LIST_OUTPUT);
        GetPhotosListInput getPhotosListInput = (GetPhotosListInput) getArguments().getSerializable(MenuActivity.GET_PHOTOS_LIST_INPUT);
        GetUserOutput getUserOutput = (GetUserOutput) getArguments().getSerializable(MenuActivity.GET_USER_OUTPUT);
        GetUserInput getUserInput = (GetUserInput) getArguments().getSerializable(MenuActivity.GET_USER_INPUT);

        ListView listView = (ListView) v.findViewById(R.id.userPhotoListView);
        listView.setAdapter(new SimplePhotoListAdapter(v.getContext(), R.layout.simple_photo_item, getPhotosListOutput, getPhotosListInput, (MenuActivity) getActivity()));

        view = v;
        setViewBy(getUserInput, getUserOutput, v);

        return v;
    }

    private void setViewBy(GetUserInput getUserInput, GetUserOutput getUserOutput, View v) {
        TextView userIsObserving = (TextView) v.findViewById(R.id.userIsObserving);
        userIsObserving.setText(Utils.parseReadableList(getUserOutput.getObserverOf()));
        TextView userObservedByTextView = (TextView) v.findViewById(R.id.userObservedByTextView);
        userObservedByTextView.setText(Utils.parseReadableList(getUserOutput.getObservedBy()));
        Button button = (Button) v.findViewById(R.id.userChangeAvatarButton);
        selectedPhotoImageView = (ImageView) v.findViewById(R.id.userAvatarImageView);
        if(getUserOutput.getAvatarBinaryData() != null){
            Utils.setImageViewByBytesArray(selectedPhotoImageView, getUserOutput.getAvatarBinaryData());
        }


        if(getUserInput.getAuthenticationData().getUsername().equals(getUserInput.getUsername())){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeAvatar();
                }
            });
        } else {
            if(getUserOutput.getObservedBy().contains(getUserInput.getAuthenticationData().getUsername())){
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        unObserve();
                    }
                });
            } else {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        observe();
                    }
                });
            }
        }

    }

    private void observe() {

    }

    private void unObserve() {

    }

    private void changeAvatar() {
        selectPhotoButtonAction();
        ChangeAvatarInput changeAvatarInput = new ChangeAvatarInput();
        changeAvatarInput.setAuthenticationData(LoginActivity.authenticationData);
        changeAvatarInput.setAvatarBinaryData(Utils.getBytesFromAvatarImageView(selectedPhotoImageView));
        (new ServerTask<ChangeAvatarInput, ChangeAvatarOutput, MenuActivity>((MenuActivity) getActivity(), changeAvatarInput, UrlData.CHANGE_AVATAR_PATH) {
            @Override
            protected void doSomethingWithOutput(ChangeAvatarOutput o) {
                Toast.makeText(activity, "Avatar changed", Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }

    private ImageView selectedPhotoImageView;

    public void selectPhotoButtonAction() {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

        startActivityForResult(chooserIntent, Integer.valueOf(0));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }
            try {
                InputStream photoInputStream = getActivity().getContentResolver().openInputStream(data.getData());
                selectedPhotoImageView.setImageBitmap(BitmapFactory.decodeStream(photoInputStream));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
