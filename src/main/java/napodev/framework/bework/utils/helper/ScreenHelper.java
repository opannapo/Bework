package napodev.framework.bework.utils.helper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import napodev.framework.bework.utils.Log;

/**
 * Created by opannapo on 12/19/16.
 */
public final class ScreenHelper {
    public static int dpToPx(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px * Resources.getSystem().getDisplayMetrics().densityDpi);
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public static void getDeviceType(Context context) {
        int density = context.getResources().getDisplayMetrics().densityDpi;
        Log.d("getDeviceType() " + density);

        if (density <= DisplayMetrics.DENSITY_LOW) {
            Log.d("DENSITY_LOW : " + String.valueOf(DisplayMetrics.DENSITY_LOW) + " density " + density);
            return;
        } else if (density <= DisplayMetrics.DENSITY_MEDIUM) {
            Log.d("DENSITY_MEDIUM : " + String.valueOf(DisplayMetrics.DENSITY_MEDIUM) + " density " + density);
            return;
        } else if (density <= DisplayMetrics.DENSITY_HIGH) {
            Log.d("DENSITY_HIGH : " + String.valueOf(DisplayMetrics.DENSITY_HIGH) + " density " + density);
            return;
        } else if (density <= DisplayMetrics.DENSITY_XHIGH) {
            Log.d("DENSITY_XHIGH : " + String.valueOf(DisplayMetrics.DENSITY_XHIGH) + " density " + density);
            return;
        } else if (density <= DisplayMetrics.DENSITY_XXHIGH) {
            Log.d("DENSITY_XXHIGH : " + String.valueOf(DisplayMetrics.DENSITY_XXHIGH) + " density " + density);
            return;
        } else if (density <= DisplayMetrics.DENSITY_XXXHIGH) {
            Log.d("DENSITY_XXXHIGH : " + String.valueOf(DisplayMetrics.DENSITY_XXXHIGH) + " density " + density);
            return;
        } else {
            Log.d("UNKNOW DENSITY NJING: " + String.valueOf(density));
        }
    }

}
