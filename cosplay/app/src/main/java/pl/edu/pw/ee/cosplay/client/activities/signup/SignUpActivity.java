package pl.edu.pw.ee.cosplay.client.activities.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.server.model.McUser;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void signUp(View view) {
        EditText loginEditText = (EditText)findViewById(R.id.login_input);
        EditText passwordEditText = (EditText)findViewById(R.id.password_input);
        EditText repasswordEditText = (EditText)findViewById(R.id.repassword_input);
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String repassword = repasswordEditText.getText().toString();
        login = login.replace(" ", ""); //TODO zrobic porzadnego regexa

        if(isInputDataOnClientSideValid(login, password, repassword)){
            SignUpAsyncTask signUpAsyncTask = new SignUpAsyncTask(this);
            McUser newUser = new McUser();
            newUser.setLogin(login);
            newUser.setPassword(password);
            signUpAsyncTask.execute(newUser);
        }
    }

    private boolean isInputDataOnClientSideValid(String login, String password, String repassword) {
        if(login.isEmpty()|| password.isEmpty()|| repassword.isEmpty()){
            SignUpAlerts.emptyInput(this).show();
            return false;
        }
        if (!password.equals(repassword)) {
            SignUpAlerts.wrongRepassword(this).show();
            return false;
        }
        if ((login.length() > 32) || (login.length()  < 3)){
            SignUpAlerts.wrongLoginLenght(this).show();
            return false;
        }
        if ((password.length() > 32) || (password.length()  < 8)) {
            SignUpAlerts.wrongPasswordLenght(this).show();
            return false;
        }
        return true;
    }
}
