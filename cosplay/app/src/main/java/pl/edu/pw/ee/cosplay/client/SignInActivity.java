package pl.edu.pw.ee.cosplay.client;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.server.controller.StatusController;
import pl.edu.pw.ee.cosplay.server.controller.UserController;
import pl.edu.pw.ee.cosplay.server.model.McUser;

/**
 * Tu narazie jest tylko prosty test komunikacji z serwerem.
 */
public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        TestAsyncClass testAsyncClass = new TestAsyncClass();
        try {
            testAsyncClass.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private class TestAsyncClass extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            McUser user = new McUser();
            user.setLogin("Test");
            user.setPassword("test");
            try {
                StatusController statusController = new StatusController();
                UserController userController = new UserController();
                Log.d("CS_TEST", statusController.getStatus().toString());
                Log.d("CS_TEST", statusController.getStatus(0L).toString());
                Log.d("CS_TEST", statusController.getStatus(1L).toString());
                Log.d("CS_TEST", userController.getUsers().toString());
                Log.d("CS_TEST", userController.getUser(1L).toString());
                userController.postUser(user);
                user.setUserId(1L);
                user.setLogin("NewShafear");
                userController.postUser(user);
            } catch (Exception e) {
                Log.d("CS_TEST", "some problem");
                e.printStackTrace();
            }
            return null;
        }
    }
}
