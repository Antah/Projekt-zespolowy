package pl.edu.pw.ee.cosplay.client.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.networking.ServerTask;
import pl.edu.pw.ee.cosplay.client.utils.SignUpAlerts;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.RegisterInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.RegisterOutput;

/**
 * Created by jereczem on 02.08.15.
 */
public class SignUpLogic {
    private AppCompatActivity a;

    public SignUpLogic(AppCompatActivity activity){
        this.a = activity;

        final ProgressDialog progressDialog = new ProgressDialog(a);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        Button signInButton = (Button) a.findViewById(R.id.signup_button);
        signInButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    progressDialog.show();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    signUpClick();
                    progressDialog.dismiss();
                }
                return false;
            }
        });
    }

    public void signUpClick(){
        EditText loginEditText = (EditText)a.findViewById(R.id.login_input);
        EditText passwordEditText = (EditText)a.findViewById(R.id.password_input);
        EditText repasswordEditText = (EditText)a.findViewById(R.id.repassword_input);
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String repassword = repasswordEditText.getText().toString();
        if(login.length() > 0) {
            if (login.charAt(login.length() - 1) == ' ')
                login = login.substring(0, login.charAt(login.length() - 1));
        }

        if(isInputDataOnClientSideValid(login, password, repassword))
            signUpNewUser(login, password);
    }

    private void signUpNewUser(String login, String password){
        RegisterInput input= new RegisterInput();
        input.setUsername(login);
        input.setPassword(password);
        (new ServerTask<RegisterInput, RegisterOutput, Activity>(a,input, UrlData.REGISTER_PATH){

            @Override
            protected void doSomethingWithOutput(RegisterOutput o) {
                Toast.makeText(activity, "Account created", Toast.LENGTH_SHORT).show();
                activity.finish();
            }
        }).execute();
    }

    private boolean isInputDataOnClientSideValid(String login, String password, String repassword) {
        if(login.isEmpty()|| password.isEmpty()|| repassword.isEmpty()){
            SignUpAlerts.emptyInput(a).show();
            return false;
        }
        if (!password.equals(repassword)) {
            SignUpAlerts.wrongRepassword(a).show();
            return false;
        }
        if ((login.length() > 32) || (login.length()  < 3)){
            SignUpAlerts.wrongLoginLenght(a).show();
            return false;
        }
        if ((password.length() > 32) || (password.length()  < 8)) {
            SignUpAlerts.wrongPasswordLenght(a).show();
            return false;
        }
        return true;
    }
}
