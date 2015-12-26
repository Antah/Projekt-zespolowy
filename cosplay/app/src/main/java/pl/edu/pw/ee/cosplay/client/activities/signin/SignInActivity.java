package pl.edu.pw.ee.cosplay.client.activities.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.activities.signup.SignUpActivity;

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

        //TODO logowanie
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
