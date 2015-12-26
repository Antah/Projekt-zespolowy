package pl.edu.pw.ee.cosplay.client.activities.signin;

import android.app.Activity;
import android.app.AlertDialog;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.alerts.Alerts;

/**
 * Created by Michał on 2015-12-26.
 */
public class SignInAlerts extends Alerts {
    public static AlertDialog userLogged(Activity activity) {
        String user_logged_title =
                activity.getResources().getString(R.string.user_logged_title);
        String user_logged_message=
                activity.getResources().getString(R.string.user_logged_message);
        return DialogGenerator.generateSimpleOKAlert(activity, user_logged_title, user_logged_message);
    }

    public static AlertDialog wrongLoginAndPassword(Activity activity) {
        String wrong_login_and_password_title =
                activity.getResources().getString(R.string.wrong_login_and_password_title);
        String wrong_login_and_password_message=
                activity.getResources().getString(R.string.wrong_login_and_password_message);
        return DialogGenerator.generateSimpleOKAlert
                (activity, wrong_login_and_password_title, wrong_login_and_password_message);
    }
}
