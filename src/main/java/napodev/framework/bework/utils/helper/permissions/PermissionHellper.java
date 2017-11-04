package napodev.framework.bework.utils.helper.permissions;

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
        READ_EXTERNAL_STORAGE(2, Manifest.permission.READ_EXTERNAL_STORAGE),
        WRITE_EXTERNAL_STORAGE(3, Manifest.permission.WRITE_EXTERNAL_STORAGE),
        ACCESS_NETWORK_STATE(4, Manifest.permission.ACCESS_NETWORK_STATE),
        GET_ACCOUNTS(5, Manifest.permission.GET_ACCOUNTS),
        READ_PHONE_STATE(6, Manifest.permission.READ_PHONE_STATE),
        RECEIVE_BOOT_COMPLETED(7, Manifest.permission.RECEIVE_BOOT_COMPLETED),
        ACCESS_WIFI_STATE(8, Manifest.permission.ACCESS_WIFI_STATE),
        CAMERA(9, Manifest.permission.CAMERA),
        ACCESS_FINE_LOCATION(10, Manifest.permission.ACCESS_FINE_LOCATION),
        ACCESS_COARSE_LOCATION(11, Manifest.permission.ACCESS_COARSE_LOCATION),
        WAKE_LOCK(12, Manifest.permission.WAKE_LOCK),
        RECEIVE_SMS(13, Manifest.permission.RECEIVE_SMS);

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

    public static void check(Context context, PermissionEntity permissionEntity, Callback callback) {
        if (ActivityCompat.checkSelfPermission(context, permissionEntity.getName()) == PERMISSION_GRANTED) {
            callback.onResult(true);
        } else {
            callback.onResult(false);
        }
    }

    public static void request(Activity activity, PermissionEntity permissionEntity) {
        ActivityCompat.requestPermissions(activity, new String[]{permissionEntity.getName()}, permissionEntity.getRequestCode());
    }

    public interface Callback {
        void onResult(boolean isGranted);
    }
}
