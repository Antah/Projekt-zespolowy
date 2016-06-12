package pl.edu.pw.ee.cosplay.client.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by Michał on 2016-05-24.
 */
public class Utils {

   public static void setImageViewByBytesArray(ImageView imageView, byte[] bytes){
       Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
       imageView.setImageBitmap(bitmap);
   }

    public static byte[] getBytesFromImageView(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        bitmap = getResizedBitmap(bitmap, 720);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 25, stream);
        return stream.toByteArray();
    }

    public static String parseReadableList(HashSet<String> list) {
        StringBuilder s = new StringBuilder();
        int j=0;
        for(String element : list){
            s.append(element);
            if(j != list.size() - 1){
                s.append(", ");
            }
            j++;
        }
        return s.toString();
    }

    public static String formatDate(Date date){
        Calendar now =Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        Calendar then =Calendar.getInstance();
        then.setTimeInMillis(date.getTime());
        SimpleDateFormat simpleDateFormat;
        if(now.get(Calendar.DAY_OF_YEAR) == then.get(Calendar.DAY_OF_YEAR)) {
            simpleDateFormat = new SimpleDateFormat("HH:mm");
        }
        else {
            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        }
        return simpleDateFormat.format(date);
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
