package napodev.framework.bework.utils.helper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by opannapo on 4/18/17.
 */
public class CallHellper {
    public static void openCall(final Context context, String val) {
        final Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + val));
        Toast.makeText(context, "Call " + val, Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        context.startActivity(callIntent);
    }

    public static void openEmail() {

    }

    public static void openMessage() {

    }
}
