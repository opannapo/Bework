package napodev.framework.bework.corebase.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import napodev.framework.bework.corebase.model.view.BaseFragmentViewModel;
import napodev.framework.bework.corebase.worker.view.BaseFragmentControl;


/**
 * Created by opannapo on 12/17/16.
 */
public interface BaseFragmentListener {
    BaseFragmentViewModel getViewModel();

    BaseFragmentControl getWorker();

    void onViewCreatedBase(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    void onHiddenChangedBase(boolean isHidden);

    void onDestroyViewBase();

    void onAttachBase();
}
