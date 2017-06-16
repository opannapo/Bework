package napodev.framework.bework.utils.napoinject;

import android.view.ViewGroup;

import java.lang.reflect.Field;

import napodev.framework.bework.utils.Log;

/**
 * Created by opannapo on 3/21/17.
 */
public class ViewInjector {

    public static void inject(Object source, ViewGroup mainView) {
        String TAG = source.getClass().getSimpleName();
        Field[] field = source.getClass().getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            Field f = field[i];
            f.setAccessible(true);
            if (f.isAnnotationPresent(Child.class)) {
                Log.d(TAG + " isAnnotationPresent Child " + f.getName() + " type " + f.getType());
                try {
                    f.set(source, mainView.findViewById(f.getAnnotation(Child.class).value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                Log.d(TAG + "NOT Child" + f.getName());
            }
        }
    }
}
