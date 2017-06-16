package napodev.framework.bework.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.lang.reflect.Field;

import napodev.framework.bework.utils.local.SPreference;

/**
 * Created by opannapo on 4/01/17.
 */
public final class Device {

    public static void setFcmToken(String val) {
        SPreference.setEncryptPrefVal(C.PreferenceKeys.FCM_TOKEN, val);
        SPreference.setEncryptPrefVal(C.PreferenceKeys.FCM_TOKEN_CERATED_DATE, String.valueOf(System.currentTimeMillis()));
    }

    public static String getFcmToken() {
        String s = SPreference.getDecryptPrefVal(C.PreferenceKeys.FCM_TOKEN);
        if (s == null || s.equals("")) {
            return "UNKNOW";
        }
        Log.d("values " + s);
        return s;
    }

    public static String getDeviceManufacturer() {
        String v = Build.MANUFACTURER;
        Log.d("values " + v);
        return v;
    }

    public static String getDeviceModel() {
        String v = Build.MODEL;
        Log.d("values " + v);
        return v;
    }

    public static String getDeviceProduct() {
        String v = Build.PRODUCT;
        Log.d("values " + v);
        return v;
    }

    public static String getDeviceBrand() {
        String v = Build.BRAND;
        Log.d("values " + v);
        return v;
    }

    public static String getDeviceOsVersion() {
        //String v = String.valueOf(android.os.Build.VERSION.SDK_INT);
        StringBuilder v = new StringBuilder();
        Field[] fields = Build.VERSION_CODES.class.getFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            int fieldValue = -1;

            try {
                fieldValue = field.getInt(new Object());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            if (fieldValue == Build.VERSION.SDK_INT) {
                String name = fieldName;
                String sdk = String.valueOf(fieldValue);
                v.append("Android-").append(name).append(":").append(Build.VERSION.RELEASE).append(" SDK=").append(sdk);
            }
        }


        Log.d("values " + v);
        return v.toString();
    }

    public static String getDeviceAppVersionName(Context context) {
        String v = null;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            v = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            v = "Unknow";
        }

        Log.d("values " + v);
        return v;
    }


    public static void resetFcmToken() {
        SPreference.setEncryptPrefVal(C.PreferenceKeys.FCM_TOKEN, null);
        SPreference.setEncryptPrefVal(C.PreferenceKeys.FCM_TOKEN_CERATED_DATE, null);
    }
}
