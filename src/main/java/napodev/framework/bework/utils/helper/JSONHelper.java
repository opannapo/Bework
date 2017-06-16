package napodev.framework.bework.utils.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by opannapo on 1/10/17.
 */
public class JSONHelper {
    public static String getString(JSONObject o, String key) {
        String result = "";
        if (!o.has(key)) {
            return result;
        }

        if (!o.isNull(key)) {
            try {
                result = o.getString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static int getInt(JSONObject o, String key) {
        int result = 0;
        if (!o.has(key)) {
            return result;
        }
        if (!o.isNull(key)) {
            try {
                result = o.getInt(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static double getDouble(JSONObject o, String key) {
        double result = 0;
        if (!o.has(key)) {
            return result;
        }
        if (!o.isNull(key)) {
            try {
                result = o.getDouble(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static boolean getBool(JSONObject o, String key) {
        boolean result = false;
        if (!o.has(key)) {
            return result;
        }

        if (!o.isNull(key)) {
            try {
                result = o.getBoolean(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static JSONArray getArray(JSONObject o, String key) {
        JSONArray result = new JSONArray();
        if (!o.has(key)) {
            return result;
        }

        if (!o.isNull(key)) {
            try {
                result = o.getJSONArray(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static JSONObject getObject(JSONObject o, String key) {
        JSONObject result = new JSONObject();
        if (!o.has(key)) {
            return result;
        }

        if (!o.isNull(key)) {
            try {
                result = o.getJSONObject(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
