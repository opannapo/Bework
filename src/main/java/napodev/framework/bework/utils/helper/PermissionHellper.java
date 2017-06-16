package napodev.framework.bework.utils.helper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by opannapo on 4/19/17.
 */
public class PermissionHellper {
    public static final int PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED;
    public static final int PERMISSION_DENIED = PackageManager.PERMISSION_DENIED;

    public enum PERMISSION {
        CALL_PHONE(1, Manifest.permission.CALL_PHONE),
        READ_EXTERNAL_STORAGE(2, Manifest.permission.READ_EXTERNAL_STORAGE);

        private final int code;
        private final String name;

        PERMISSION(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    public static void check(Context context, PERMISSION permission, Callback callback) {
        if (ActivityCompat.checkSelfPermission(context, permission.getName()) == PERMISSION_GRANTED) {
            callback.onResult(true);
        } else {
            callback.onResult(false);
        }
    }

    public static void request(Activity activity, PERMISSION permission) {
        ActivityCompat.requestPermissions(activity, new String[]{permission.getName()}, permission.getCode());
    }


    public interface Callback {
        void onResult(boolean isGranted);
    }
}
