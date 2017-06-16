package napodev.framework.bework.utils.napoinject;

import android.view.ViewGroup;

import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.utils.Log;
import napodev.framework.bework.utils.helper.FontHelper;

/**
 * Created by opannapo on 3/21/17.
 */
public class FontInjector {
    public static void inject(Object source, BaseActivity activity, ViewGroup mainView) {
        String TAG = source.getClass().getSimpleName();
        Log.d(TAG + " FontInjector");

        boolean hasFontBold = false;
        boolean hasFontItalic = false;

        String fontDefault = null;
        String fontBold = null;
        String fontItalic = null;

        if (source.getClass().isAnnotationPresent(FontDefault.class)) {
            fontDefault = source.getClass().getAnnotation(FontDefault.class).value();
        }

        if (source.getClass().isAnnotationPresent(FontBold.class)) {
            fontBold = source.getClass().getAnnotation(FontBold.class).value();
            hasFontBold = true;
        }

        if (source.getClass().isAnnotationPresent(FontItalic.class)) {
            fontItalic = source.getClass().getAnnotation(FontItalic.class).value();
            hasFontItalic = true;
        }

        if ((!hasFontBold) && (!hasFontItalic)) {//only default
            new FontHelper().with(activity, fontDefault).setInto(mainView);
        } else {
            new FontHelper().with(activity, fontDefault, fontBold, fontItalic).setInto(mainView);
        }
    }

}