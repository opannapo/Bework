package napodev.framework.bework.utils;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import napodev.framework.bework.utils.helper.Broadcast;
import napodev.framework.bework.utils.helper.JSONHelper;
import napodev.framework.bework.utils.local.SPreference;


/**
 * Created by opannapo on 1/22/17.
 */
public class User {
    public static String id;
    public static String role_id;
    public static String username;
    public static String name;
    public static String gender;
    public static String job_position;
    public static String company;
    public static String address;
    public static String city;
    public static String province;
    public static String email;
    public static String mobile;
    public static String website;
    public static String token;
    public static String image;
    public static String bio;
    public static String status;
    public static String updated;
    public static String last_login;
    public static String updated_by;
    public static String created;
    public static String timezone;
    public static String created_by;
    public static String point;
    public static String notification_count;
    public static String store_id;


    private static User INSTANCE;

    public User() {

    }

    public static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new User();
        }
    }

    public static void updateUserData(Context context, JSONObject jdata) {
        createInstance();
        SPreference.setEncryptPrefVal(C.PreferenceKeys.USER, jdata.toString());
        Broadcast.send(context, C.Broadcast.USER_UPDATED, null);
    }

    public static User getCurrentUser() {
        try {
            JSONObject jdata = new JSONObject(SPreference.getDecryptPrefVal(C.PreferenceKeys.USER));
            /*id = JSONHelper.getString(jdata, "id");
            role_id = JSONHelper.getString(jdata, "role_id");
            username = JSONHelper.getString(jdata, "username");
            name = JSONHelper.getString(jdata, "name");
            email = JSONHelper.getString(jdata, "email");
            website = JSONHelper.getString(jdata, "website");
            image = JSONHelper.getString(jdata, "image");
            bio = JSONHelper.getString(jdata, "bio");
            status = JSONHelper.getString(jdata, "status");
            updated = JSONHelper.getString(jdata, "updated");
            updated_by = JSONHelper.getString(jdata, "updated_by");
            created = JSONHelper.getString(jdata, "created");
            timezone = JSONHelper.getString(jdata, "timezone");
            created_by = JSONHelper.getString(jdata, "created_by");
            point = JSONHelper.getString(jdata, "point");
            store_id = JSONHelper.getString(jdata, "store_id");*/

            id = JSONHelper.getString(jdata, "id");
            role_id = JSONHelper.getString(jdata, "role_id");
            username = JSONHelper.getString(jdata, "username");
            name = JSONHelper.getString(jdata, "name");
            gender = JSONHelper.getString(jdata, "gender");
            job_position = JSONHelper.getString(jdata, "job_position");
            company = JSONHelper.getString(jdata, "company");
            address = JSONHelper.getString(jdata, "address");
            city = JSONHelper.getString(jdata, "city");
            province = JSONHelper.getString(jdata, "province");
            email = JSONHelper.getString(jdata, "email");
            mobile = JSONHelper.getString(jdata, "mobile");
            website = JSONHelper.getString(jdata, "website");
            token = JSONHelper.getString(jdata, "token");
            image = JSONHelper.getString(jdata, "image");
            bio = JSONHelper.getString(jdata, "bio");
            status = JSONHelper.getString(jdata, "status");
            updated = JSONHelper.getString(jdata, "updated");
            last_login = JSONHelper.getString(jdata, "last_login");
            updated_by = JSONHelper.getString(jdata, "updated_by");
            created = JSONHelper.getString(jdata, "created");
            timezone = JSONHelper.getString(jdata, "timezone");
            created_by = JSONHelper.getString(jdata, "created_by");
            point = JSONHelper.getString(jdata, "point");
            notification_count = JSONHelper.getString(jdata, "notification_count");
            store_id = JSONHelper.getString(jdata, "store_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return INSTANCE;
    }

}
