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
import pl.edu.pw.ee.cosplay.client.networking.ServerTask;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.LoginControllerInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.LoginControllerOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.PhotosOrder;
import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

public class MenuActivity extends Activity{

    public static final String GET_PHOTOS_LIST_OUTPUT = "GET_PHOTOS_LIST_OUTPUT";
    public static final String GET_PHOTOS_LIST_INPUT = "GET_PHOTOS_LIST_INPUT";
    public static final String GET_PHOTO_OUTPUT = "GET_PHOTO_OUTPUT";
    public static final Integer RANGE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void addPhotoFragment(View view) {
        Fragment newFragment = new AddPhotoFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.fragmentPlaceHolder, newFragment);
        transaction.addToBackStack(null);

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
                transaction.addToBackStack(null);

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
        AuthenticationData authenticationData =
                (AuthenticationData) getIntent().getSerializableExtra(LoginActivity.AUTHENTICATION_DATA);
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
                transaction.addToBackStack(null);

                transaction.commit();
            }
        }).execute();
    }
}
