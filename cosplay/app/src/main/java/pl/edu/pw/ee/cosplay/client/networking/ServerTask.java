package pl.edu.pw.ee.cosplay.client.networking;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;

import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;

/**
 * Created by Michał on 2016-05-24.
 */
public abstract class ServerTask<Input extends Serializable,Output extends Serializable, UsedActivity extends Activity> extends AsyncTask<Void, Void, Object> {

    protected UsedActivity activity;
    protected Input input;
    private String servicePath;
    private ProgressDialog dialog;
    private Boolean hasErrors = false;
    private final static String PROGRESS_TITLE = "";
    private final static String PROGRESS_MESSAGE = "Loading. Please wait...";

    public ServerTask(UsedActivity activity, Input input, String servicePath){
        this.activity = activity;
        this.input = input;
        this.servicePath = servicePath;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(activity, PROGRESS_TITLE, PROGRESS_MESSAGE, true);
    }

    @Override
    protected Object doInBackground(Void[] params) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            URI uri = UriComponentsBuilder
                    .fromHttpUrl(UrlData.getServerIp()).port(UrlData.getPORT()).path(servicePath).build().toUri();
            byte[] binaryInput = SerializationUtils.serialize(input);
            ResponseEntity<byte[]> binaryOutput = restTemplate.postForEntity(uri, binaryInput, byte[].class);
            return SerializationUtils.deserialize(binaryOutput.getBody());
        } catch (Exception e){
            hasErrors = true;
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        dialog.dismiss();
        if(!hasErrors) {
            Output output = (Output)o;
            doSomethingWithOutput(output);
        } else {
            if (o instanceof HttpClientErrorException){
                Toast.makeText(activity, ((HttpClientErrorException) o).getResponseBodyAsString(), Toast.LENGTH_LONG).show();
                ((Exception)o).printStackTrace();
            }
            else {
                Toast.makeText(activity, ((Exception)o).getMessage(), Toast.LENGTH_LONG).show();
                ((Exception)o).printStackTrace();
            }
        }
    }

    protected abstract void doSomethingWithOutput(Output o);
}
