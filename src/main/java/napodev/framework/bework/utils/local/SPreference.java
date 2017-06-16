package napodev.framework.bework.utils.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import napodev.framework.bework.utils.Log;
import napodev.framework.bework.utils.helper.security.AESbasic;


/**
 * Created by opannapo on 12/17/16.
 */
public class SPreference {
    private static Config config;

    public static void setEncryptPrefVal(@NonNull String key, @NonNull String value) {
        Log.d("Key:" + String.valueOf(key) + " Value:" + String.valueOf(value));
        SharedPreferences.Editor editor = config.getPreferences().edit();
        try {
            editor.putString(key.toString(), AESbasic.encrypt(value, config.AESkay));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Key:" + String.valueOf(key) + " Value:" + String.valueOf(value));
        }
        editor.commit();
    }

    public static String getPrefVal(@NonNull String key) {
        return config.PREFERENCES.getString(key, "");
    }

    public static String getEncryptPrefVal(@NonNull String key) {
        String result = null;
        try {
            result = config.getPreferences().getString(key.toString(), "");
            Log.d("Key:" + String.valueOf(key) + " result:" + result);
        } catch (NullPointerException e) {
            e.printStackTrace();
            result = "";
            Log.d("Key:" + String.valueOf(key) + " result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
            Log.d("Key:" + String.valueOf(key) + " result:" + result);
        }

        return result;
    }

    public static String getDecryptPrefVal(@NonNull String key) {
        String result = null;
        try {
            result = AESbasic.decrypt(config.getPreferences().getString(key.toString(), ""), config.AESkay);
            Log.d("Key:" + String.valueOf(key) + " result:" + result);
        } catch (NullPointerException e) {
            e.printStackTrace();
            result = "";
            Log.d("Key:" + String.valueOf(key) + " result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
            Log.d("Key:" + String.valueOf(key) + " result:" + result);
        }

        return result;
    }

    public void removeConfig(@NonNull String key) {
        SharedPreferences.Editor editor = config.getPreferences().edit();
        try {
            editor.remove(key);
        } catch (Exception e) {
        }
        editor.commit();
    }

    public static Config getConfig() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public static void clearConfig() {
        config = null;
    }


    public static final class Config {
        private String prefName;
        private Context appContext;
        private SharedPreferences PREFERENCES;
        private String AESkay;

        public SharedPreferences getPreferences() {
            return PREFERENCES;
        }

        public Config setPrefName(String prefName) {
            this.prefName = prefName;
            return this;
        }

        public Config setAppContex(Context appContext) {
            this.appContext = appContext;
            return this;
        }

        public Config setAESKey(String key) {
            this.AESkay = key;
            return this;
        }

        public void build() {
            PREFERENCES = appContext.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        }


    }

}
