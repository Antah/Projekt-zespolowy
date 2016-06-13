package pl.edu.pw.ee.cosplay.client.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.HashSet;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.activity.MenuActivity;
import pl.edu.pw.ee.cosplay.client.adapter.SimplePhotoListAdapter;
import pl.edu.pw.ee.cosplay.client.networking.ServerTask;
import pl.edu.pw.ee.cosplay.client.utils.Utils;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.PhotosOrder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllPhotosFragment extends Fragment {

    private EditText charactersEditText;
    private EditText franchiseEditText;


    public AllPhotosFragment(){
    }

    String observer = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_all_photos, container, false);
        GetPhotosListOutput getPhotosListOutput = (GetPhotosListOutput) getArguments().getSerializable(MenuActivity.GET_PHOTOS_LIST_OUTPUT);
        GetPhotosListInput getPhotosListInput = (GetPhotosListInput) getArguments().getSerializable(MenuActivity.GET_PHOTOS_LIST_INPUT);
        ListView listView = (ListView) v.findViewById(R.id.simplePhotosListView);
        listView.setAdapter(new SimplePhotoListAdapter(v.getContext(), R.layout.simple_photo_item, getPhotosListOutput, getPhotosListInput, (MenuActivity) getActivity()));

        charactersEditText = (EditText) v.findViewById(R.id.characterEditText);
        franchiseEditText = (EditText) v.findViewById(R.id.franchiseEditText);

        observer = getPhotosListInput.getObserver();

        final Spinner orderSpinner = ((Spinner)v.findViewById(R.id.sortSpinner));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(), R.array.photoOrder, R.layout.support_simple_spinner_dropdown_item);
        orderSpinner.setAdapter(adapter);

        v.findViewById(R.id.showAndFilterButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {

                (new ServerTask<GetPhotosListInput, GetPhotosListOutput, MenuActivity>((MenuActivity) getActivity(), getInput(orderSpinner, v), UrlData.GET_PHOTOS_LIST_PATH) {
                    @Override
                    protected void doSomethingWithOutput(GetPhotosListOutput o) {
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
        return v;
    }


    public GetPhotosListInput getInput(Spinner orderSpinner, View v) {
        final GetPhotosListInput input = new GetPhotosListInput();
        input.setRangeFirst(1);
        input.setRangeLast(MenuActivity.RANGE);
        input.setFiltrByCharactersList(Utils.parseToList((charactersEditText.getText().toString())));
        input.setFiltrByFranchiseList(Utils.parseToList((franchiseEditText.getText().toString())));

        switch ((int) orderSpinner.getSelectedItemId()){
            case 1:{
                input.setOrder(PhotosOrder.COMMENTS_NO);
                break;
            }
            case 2:{
                input.setOrder(PhotosOrder.GENERAL_RATE);
                break;
            }
            case 3:{
                input.setOrder(PhotosOrder.SIMILARITY_RATE);
                break;
            }
            case 4:{
                input.setOrder(PhotosOrder.QUALITY_RATE);
                break;
            }
            case 5:{
                input.setOrder(PhotosOrder.ARRANGEMENT_RATE);
                break;
            }
            case 6:{
                input.setOrder(PhotosOrder.UPLOAD_DATE);
                break;
            }
            case 7:{
                input.setOrder(PhotosOrder.COMMENTS_NO_DESC);
                break;
            }
            case 8:{
                input.setOrder(PhotosOrder.GENERAL_RATE_DESC);
                break;
            }
            case 9:{
                input.setOrder(PhotosOrder.SIMILARITY_RATE_DESC);
                break;
            }
            case 10:{
                input.setOrder(PhotosOrder.QUALITY_RATE_DESC);
                break;
            }
            case 11:{
                input.setOrder(PhotosOrder.ARRANGEMENT_RATE_DESC);
                break;
            }
            default:{
                input.setOrder(PhotosOrder.UPLOAD_DATE_DESC);
            }
        }
        return input;
    }
}
