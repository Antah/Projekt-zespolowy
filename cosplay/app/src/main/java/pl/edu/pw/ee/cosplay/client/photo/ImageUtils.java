package pl.edu.pw.ee.cosplay.client.photo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by Michał on 2016-05-24.
 */
public class ImageUtils {

   public static void setImageViewByBytesArray(ImageView imageView, byte[] bytes){
       Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
       imageView.setImageBitmap(bitmap);
   }

    public static byte[] getBytesFromImageView(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
