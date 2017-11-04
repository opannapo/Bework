package napodev.framework.bework.corebase.view.dialogfragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.utils.napoinject.FontInjector;
import napodev.framework.bework.utils.napoinject.ViewInjector;

/**
 * Created by opannapo on 4/25/17.
 */
public abstract class BaseDialogFragment extends DialogFragment {
    private boolean attachToRoot;
    private Bundle arg;
    private DialogClickListener clickListener;
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (arg != null) {
            attachToRoot = arg.getBoolean("attachToRoot", false);
        }

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(layoutId(), container, attachToRoot);
        ViewInjector.inject(this, (ViewGroup) view);
        FontInjector.inject(this, (BaseActivity) getActivity(), (ViewGroup) view);

        onViewCreated(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isAdded()) {
            try {
                ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
                params.width = layoutSize()[0];
                params.height = layoutSize()[1];
                getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
                getDialog().getWindow().setBackgroundDrawable(layoutColorDrawable());
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Bundle getArg() {
        return arg;
    }

    public BaseDialogFragment setArg(Bundle arg) {
        setArguments(arg);
        this.arg = getArguments();
        return this;
    }

    public DialogClickListener getClickListener() {
        return clickListener;
    }

    public BaseDialogFragment setClickListener(DialogClickListener clickListener) {
        this.clickListener = clickListener;
        return this;
    }

    public BaseDialogFragment setSupportFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        return this;
    }

    public void show() {
        show(fragmentManager, this.getClass().getSimpleName());
    }

    protected abstract int layoutId();

    protected abstract int[] layoutSize();

    protected abstract ColorDrawable layoutColorDrawable();

    protected abstract void onViewCreated(View v);


}
