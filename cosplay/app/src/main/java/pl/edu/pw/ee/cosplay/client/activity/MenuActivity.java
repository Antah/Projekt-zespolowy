package pl.edu.pw.ee.cosplay.client.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.fragment.AddPhotoFragment;
import pl.edu.pw.ee.cosplay.client.fragment.AllPhotosFragment;

public class MenuActivity extends Activity{

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
        Fragment newFragment = new AllPhotosFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.fragmentPlaceHolder, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
