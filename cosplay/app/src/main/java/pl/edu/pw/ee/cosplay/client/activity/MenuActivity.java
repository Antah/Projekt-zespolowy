package pl.edu.pw.ee.cosplay.client.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.HashSet;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.fragment.AddPhotoFragment;
import pl.edu.pw.ee.cosplay.client.fragment.AllPhotosFragment;
import pl.edu.pw.ee.cosplay.client.fragment.UserFragment;
import pl.edu.pw.ee.cosplay.client.networking.ServerTask;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.LoginControllerInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.LoginControllerOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto.GetPhotoInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.PhotosOrder;
import pl.edu.pw.ee.cosplay.rest.model.controller.user.GetUserInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.user.GetUserOutput;
import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

public class MenuActivity extends Activity{

    public static final String GET_PHOTOS_LIST_OUTPUT = "GET_PHOTOS_LIST_OUTPUT";
    public static final String GET_PHOTOS_LIST_INPUT = "GET_PHOTOS_LIST_INPUT";
    public static final String GET_USER_INPUT = "GET_USER_INPUT";
    public static final String GET_USER_OUTPUT = "GET_USER_OUTPUT";
    public static final String GET_PHOTO_ID = "GET_PHOTO_ID";
    public static final String USERNAME_ID = "USERNAME_ID";
    public static final Integer RANGE = 4;

    public static Activity fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        String username = getIntent().getStringExtra(USERNAME_ID);
        if(username != null){
            openProfileFragment(username);
        }
        fa = this;
    }

    public void addPhotoFragment(View view) {
        Fragment newFragment = new AddPhotoFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.fragmentPlaceHolder, newFragment);

        transaction.commit();
    }

    public void allPhotosFragment(View view) {

        GetPhotosListInput input = new GetPhotosListInput();
        input.setFiltrByCharactersList(new HashSet<String>());
        input.setFiltrByFranchiseList(new HashSet<String>());
        input.setObserver(null);
        input.setOrder(PhotosOrder.UPLOAD_DATE_DESC);
        input.setRangeFirst(1);
        input.setRangeLast(RANGE);

        (new ServerTask<GetPhotosListInput, GetPhotosListOutput, MenuActivity>(this, input, UrlData.GET_PHOTOS_LIST_PATH) {
            @Override protected void doSomethingWithOutput(GetPhotosListOutput o) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(GET_PHOTOS_LIST_OUTPUT, o);
                bundle.putSerializable(GET_PHOTOS_LIST_INPUT, this.input);

                Fragment newFragment = new AllPhotosFragment();
                newFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentPlaceHolder, newFragment);

                transaction.commit();
            }
        }).execute();
    }

    public void observedPhotosFragment(View view) {

        GetPhotosListInput input = new GetPhotosListInput();
        input.setFiltrByCharactersList(new HashSet<String>());
        input.setFiltrByFranchiseList(new HashSet<String>());
        input.setObserver(null);
        input.setOrder(PhotosOrder.UPLOAD_DATE_DESC);
        input.setRangeFirst(1);
        input.setRangeLast(RANGE);
        AuthenticationData authenticationData = LoginActivity.authenticationData;
        input.setObserver(authenticationData.getUsername());

        (new ServerTask<GetPhotosListInput, GetPhotosListOutput, MenuActivity>(this, input, UrlData.GET_PHOTOS_LIST_PATH) {
            @Override protected void doSomethingWithOutput(GetPhotosListOutput o) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(GET_PHOTOS_LIST_OUTPUT, o);
                bundle.putSerializable(GET_PHOTOS_LIST_INPUT, this.input);

                Fragment newFragment = new AllPhotosFragment();
                newFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragmentPlaceHolder, newFragment);

                transaction.commit();
            }
        }).execute();
    }

    public void yourProfileFragment(View view) {
        openProfileFragment(LoginActivity.authenticationData.getUsername());
    }

    public void openProfileFragment(String username) {
        final GetUserInput userInput = new GetUserInput();
        userInput.setAuthenticationData(LoginActivity.authenticationData);
        userInput.setUsername(LoginActivity.authenticationData.getUsername());

        final GetPhotosListOutput photoOutput = new GetPhotosListOutput();
        photoOutput.setAreThereNextPhotos(false);
        final GetPhotosListInput photoInput = new GetPhotosListInput();
        photoInput.setFiltrByCharactersList(new HashSet<String>());
        photoInput.setFiltrByFranchiseList(new HashSet<String>());
        photoInput.setObserver(null);
        photoInput.setOrder(PhotosOrder.UPLOAD_DATE_DESC);
        photoInput.setRangeFirst(1);
        photoInput.setRangeLast(1);
        photoInput.setAuthor(username);

        (new ServerTask<GetPhotosListInput, GetPhotosListOutput, MenuActivity>(this, photoInput, UrlData.GET_PHOTOS_LIST_PATH) {
            @Override
            protected void doSomethingWithOutput(GetPhotosListOutput o) {
                photoOutput.setAreThereNextPhotos(o.getAreThereNextPhotos());
                photoOutput.setSimplePhotoDataList(o.getSimplePhotoDataList());
            }
        }).execute();

        (new ServerTask<GetUserInput, GetUserOutput, MenuActivity>(this, userInput, UrlData.GET_USER_PATH) {
            @Override
            protected void doSomethingWithOutput(GetUserOutput o) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(GET_PHOTOS_LIST_OUTPUT, photoOutput);
                bundle.putSerializable(GET_PHOTOS_LIST_INPUT, photoInput);
                bundle.putSerializable(GET_USER_INPUT, userInput);
                bundle.putSerializable(GET_USER_OUTPUT, o);

                Fragment newFragment = new UserFragment();
                newFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentPlaceHolder, newFragment);

                transaction.commit();
            }
        }).execute();
    }
}
