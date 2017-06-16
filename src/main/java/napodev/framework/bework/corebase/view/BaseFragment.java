package napodev.framework.bework.corebase.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import napodev.framework.bework.utils.Log;


/**
 * Created by opannapo on 12/17/16.
 */
public abstract class BaseFragment extends Fragment implements BaseFragmentListener {
    public View viewRoot;
    private int visibilityCOunt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("");
        onViewCreatedBase(inflater, container, savedInstanceState);
        return viewRoot;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("");
        onHiddenChanged(isHidden());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("");
        onDestroyViewBase();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("");
        visibilityCOunt = 0;
        onAttachBase();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("");
        if (!hidden) {
            visibilityCOunt += 1;
        }

        onHiddenChangedBase(hidden);
    }

    public boolean isFirstVisible() {
        Log.d("visibilityCOunt " + visibilityCOunt + " " + this.getClass().getSimpleName());
        if (visibilityCOunt == 1) {
            return true;
        } else {
            return false;
        }
    }
}
