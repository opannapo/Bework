package napodev.framework.bework.utils.napoinject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import napodev.framework.bework.corebase.model.view.BaseFragmentViewModel;
import napodev.framework.bework.corebase.model.view.BaseViewModel;
import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.utils.Log;

/**
 * Created by opannapo on 3/21/17.
 */
public class RootInjector {
    public static void inject(Object source) {
        String TAG = source.getClass().getSimpleName();
        Log.d(TAG + " RootInjector");

        if (source.getClass().isAnnotationPresent(Root.class)) {
            Log.d(TAG + " RootInjector YES Root ");
            //((BaseActivity) source).setContentView(source.getClass().getAnnotation(Root.class).value());

            if (source instanceof BaseActivity) {
                ((BaseActivity) source).setContentView(source.getClass().getAnnotation(Root.class).value());
            } else if (source instanceof BaseViewModel) {
                ((BaseViewModel) source).getActivity().setContentView(source.getClass().getAnnotation(Root.class).value());
            }
        } else {
            Log.d(TAG + " RootInjector NO Root ");
        }
    }

    public static View injectFragment(Object source, LayoutInflater inflater, ViewGroup container) {
        ViewGroup mainView = null;
        String TAG = source.getClass().getSimpleName();
        Log.d(TAG + " RootInjector");
        if (source.getClass().isAnnotationPresent(Root.class)) {
            Log.d(TAG + " RootInjector YES Root ");
            if (source instanceof BaseFragmentViewModel) {
                mainView = (ViewGroup) inflater.inflate(source.getClass().getAnnotation(Root.class).value(), container, false);
            }
        } else {
            Log.d(TAG + " RootInjector NO Root ");
        }

        return mainView;
    }
}
