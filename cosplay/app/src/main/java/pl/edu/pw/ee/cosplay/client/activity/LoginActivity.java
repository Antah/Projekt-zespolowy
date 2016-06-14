package pl.edu.pw.ee.cosplay.client.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.fragment.AddPhotoFragment;
import pl.edu.pw.ee.cosplay.client.networking.ServerTask;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.LoginControllerInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.LoginControllerOutput;
import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

public class LoginActivity extends AppCompatActivity {

    public static final String AUTHENTICATION_DATA = "AUTHENTICATION_DATA";
    public static AuthenticationData authenticationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signInButtonAction(View view) {

        LoginControllerInput input = getLoginControllerInput();

        (new ServerTask<LoginControllerInput, LoginControllerOutput, LoginActivity>(this, input, UrlData.LOGIN_PATH) {
            @Override protected void doSomethingWithOutput(LoginControllerOutput o) {
                Intent intent = new Intent(activity, MenuActivity.class);
                Bundle bundle = new Bundle();
                authenticationData = o.getAuthenticationData();
                bundle.putSerializable(AUTHENTICATION_DATA, o.getAuthenticationData());
                intent.putExtras(bundle);
                activity.startActivity(intent);
            }
        }).execute();
    }

    private LoginControllerInput getLoginControllerInput(){
        LoginControllerInput input = new LoginControllerInput();
        EditText loginInputEditText = (EditText) findViewById(R.id.loginInputEditText);
        EditText passwordInputEditText = (EditText) findViewById(R.id.passwordInputEditText);
        input.setUserName(loginInputEditText.getText().toString());
        input.setPassword(passwordInputEditText.getText().toString());
        return input;
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
