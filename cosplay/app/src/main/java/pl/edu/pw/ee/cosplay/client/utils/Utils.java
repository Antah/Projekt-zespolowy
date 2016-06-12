package pl.edu.pw.ee.cosplay.client.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
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
            simpleDateFormat = new SimpleDateFormat("hh:mm");
        }
        else {
            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        }
        return simpleDateFormat.format(date);
    }
}
