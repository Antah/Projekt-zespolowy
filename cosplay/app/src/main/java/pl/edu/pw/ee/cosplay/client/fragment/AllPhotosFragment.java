package pl.edu.pw.ee.cosplay.client.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.edu.pw.ee.cosplay.R;

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
        return v;
    }
}
