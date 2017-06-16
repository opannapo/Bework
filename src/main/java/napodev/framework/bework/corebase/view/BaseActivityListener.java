package napodev.framework.bework.corebase.view;

import android.os.Bundle;

import napodev.framework.bework.corebase.model.view.BaseViewModel;
import napodev.framework.bework.corebase.worker.view.BaseActivityControl;


/**
 * Created by opannapo on 12/17/16.
 */
public interface BaseActivityListener {
    BaseViewModel getViewModel();

    BaseActivityControl getWorker();

    void onActivityCreate(Bundle savedInstanceState);
}
