package pl.edu.pw.ee.cosplay.client.activities.signin;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import org.springframework.web.client.HttpClientErrorException;

import java.util.concurrent.ExecutionException;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.activities.signup.SignUpActivity;
import pl.edu.pw.ee.cosplay.client.rest.client.LoginClient;
import pl.edu.pw.ee.cosplay.rest.model.entity.McUser;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void signIn(View view) {
        EditText loginEditText = (EditText) findViewById(R.id.login_input);
        EditText passwordEditText = (EditText) findViewById(R.id.password_input);
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        login = login.replace(" ", "");

        LoginClient loginClient = new LoginClient();
        loginClient.execute(login,password);
        try {
            Object s = loginClient.get();
            Toast.makeText(this,s.toString(),Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


}
