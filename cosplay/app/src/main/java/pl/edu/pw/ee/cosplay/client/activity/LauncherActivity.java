package pl.edu.pw.ee.cosplay.client.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;

public class LauncherActivity extends Activity {

    //private static final String SERVER_IP = "http://shafear.space";
    private static final String SERVER_IP = "http://192.168.43.129";
    private static final Integer PORT = 8080;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        UrlData.setServerIp(SERVER_IP);
        UrlData.setPORT(PORT);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
