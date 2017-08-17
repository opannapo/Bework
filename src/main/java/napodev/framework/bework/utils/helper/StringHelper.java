package napodev.framework.bework.utils.helper;

import android.text.Html;
import android.text.Spanned;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by opannapo on 12/17/16.
 */
public class StringHelper {

    public enum FORMAT_TYPE {
        PRICE {
            @Override
            public String toString() {
                return "###,###.###";
            }
        },
        DATE {
            @Override
            public String toString() {
                return "dd MMM yyyy HH:mm:ss";
            }
        }

    }

    public static boolean isValidString(String value) {
        if (value != null) {
            if (value.length() > 0) {
                return true;
            }
        }

        return false;
    }

    public static String convertFormat(int val, FORMAT_TYPE type) {
        String output = null;
        if (type == FORMAT_TYPE.PRICE) {
            DecimalFormat myFormatter = new DecimalFormat(type.toString());
            output = myFormatter.format(val);
            //Log.d(val + " : " + output);
        }

        //Log.d(output);
        return output;
    }

    public static String convertFormat(long val, FORMAT_TYPE type) {
        String output = null;
        if (type == FORMAT_TYPE.DATE) {
            SimpleDateFormat format = new SimpleDateFormat(type.toString());
            output = format.format(val);
        }

        //Log.d(output);
        return output;
    }

    public static Spanned stringToHtmlJustify(String val) {
        String template = "<body style=\"text-align:justify;color:gray;background-color:black;\">" + val + "</body>";
        Spanned result;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(template, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(template);
        }

        return result;
    }


    public static Spanned stringToHtml(String val) {
        String template = val;
        Spanned result;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(template, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(template);
        }

        return result;
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
