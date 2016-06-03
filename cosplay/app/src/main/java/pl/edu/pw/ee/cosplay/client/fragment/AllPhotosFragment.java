package pl.edu.pw.ee.cosplay.client.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.adapter.SimplePhotoListAdapter;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.SimplePhotoData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListOutput;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllPhotosFragment extends Fragment {

    public AllPhotosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_photos, container, false);

        GetPhotosListOutput getPhotosListOutput = new GetPhotosListOutput();

        ListView listView = (ListView) v.findViewById(R.id.simplePhotosListView);
        ArrayList<SimplePhotoData> simplePhotoList = new ArrayList<>();
        SimplePhotoData d1 = new SimplePhotoData();
        d1.setUsername("1");
        SimplePhotoData d2 = new SimplePhotoData();
        d2.setUsername("2");
        SimplePhotoData d3 = new SimplePhotoData();
        d3.setUsername("3");
        SimplePhotoData d4 = new SimplePhotoData();
        d4.setUsername("4");
        simplePhotoList.add(d1);
        simplePhotoList.add(d2);
        simplePhotoList.add(d3);
        simplePhotoList.add(d4);
        getPhotosListOutput.setAreThereNextPhotos(true);
        getPhotosListOutput.setSimplePhotoDataList(simplePhotoList);

        listView.setAdapter(new SimplePhotoListAdapter(v.getContext(), R.layout.simple_photo_item, getPhotosListOutput));

        return v;
    }
}
