package nz.ac.otago.telecom.speedmath;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Tavita on 11/05/2015.
 */
public class Message {
    public static void message(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
