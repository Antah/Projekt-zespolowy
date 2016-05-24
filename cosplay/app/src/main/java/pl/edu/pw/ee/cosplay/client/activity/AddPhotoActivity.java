package pl.edu.pw.ee.cosplay.client.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.commons.lang.SerializationUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;

import pl.edu.pw.ee.cosplay.R;
import pl.edu.pw.ee.cosplay.client.networking.ServerTask;
import pl.edu.pw.ee.cosplay.client.photo.ImageUtils;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addphoto.AddPhotoInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addphoto.AddPhotoOutput;
import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

public class AddPhotoActivity extends AppCompatActivity {

    private Activity activity = this;
    private ImageView selectedPhotoImageView;
    private AuthenticationData authenticationData;
    private EditText charactersEditText;
    private EditText descriptionEditText;
    private EditText franchisesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        selectedPhotoImageView = (ImageView) findViewById(R.id.selectedPhotoImageView);
        charactersEditText = (EditText) findViewById(R.id.charactersEditText);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        franchisesEditText = (EditText) findViewById(R.id.franchisesEditText);

        authenticationData =
                (AuthenticationData) getIntent().getSerializableExtra(LoginActivity.AUTHENTICATION_DATA);
    }

    public void selectPhoto(View view) {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

        startActivityForResult(chooserIntent, Integer.valueOf(0));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }
            try {
                InputStream photoInputStream = activity.getContentResolver().openInputStream(data.getData());
                selectedPhotoImageView.setImageBitmap(BitmapFactory.decodeStream(photoInputStream));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void addPhotoButtonAction(View view) {
        AddPhotoInput input = getAddPhotoInput();

        (new ServerTask<AddPhotoInput, AddPhotoOutput, AddPhotoActivity>(this, input, UrlData.ADD_PHOTO_PATH) {
            @Override protected void doSomethingWithOutput(AddPhotoOutput o) {
                Toast.makeText(activity, o.toString(), Toast.LENGTH_LONG).show();
            }
        }).execute();
    }

    private AddPhotoInput getAddPhotoInput(){
        AddPhotoInput input = new AddPhotoInput();

        input.setAuthenticationData(authenticationData);
        input.setPhotoDescription(descriptionEditText.getText().toString());

        HashSet<String> franchisesList = parseToList(franchisesEditText.getText().toString());
        HashSet<String> charactersList = parseToList(charactersEditText.getText().toString());

        input.setCharactersList(charactersList);
        input.setFranchisesList(franchisesList);

        byte[] bytes = ImageUtils.getBytesFromImageView(selectedPhotoImageView);
        input.setPhotoBinaryData(bytes);
        ImageUtils.setImageViewByBytesArray(selectedPhotoImageView, bytes);

        return input;
    }

    private HashSet<String> parseToList(String s) {
        HashSet<String> result = new HashSet<>();
        String[] resultList = s.split(", ");
        result.addAll(Arrays.asList(resultList));
        return result;
    }
}
