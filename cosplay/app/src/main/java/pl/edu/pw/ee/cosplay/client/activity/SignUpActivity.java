package pl.edu.pw.ee.cosplay.client.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.edu.pw.ee.cosplay.R;


public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        SignUpLogic signUpLogic = new SignUpLogic(this);
    }
}
