package pl.edu.pw.ee.cosplay.client.activities.signup;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.IOException;

import pl.edu.pw.ee.cosplay.client.alerts.Alerts;
import pl.edu.pw.ee.cosplay.server.controller.UserController;
import pl.edu.pw.ee.cosplay.server.model.McUser;

/**
 * Created by Michał on 2015-12-26.
 */
public class SignUpAsyncTask extends AsyncTask<McUser, Boolean, Boolean>{

    private Activity activity;
    private ProgressDialog progressDialog;

    protected SignUpAsyncTask(Activity activity){
        this.activity=activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(activity, "Loading", "Please wait...", true);
    }

    @Override
    protected Boolean doInBackground(McUser... params) {
        UserController userController = new UserController();
        try {
            userController.postUser(params[0]);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        progressDialog.dismiss();
        if(result){
            activity.finish();
        } else {
            Alerts.connectionError(activity, "some problem").show();
        }
    }
}
