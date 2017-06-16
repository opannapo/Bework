package napodev.framework.bework.corebase.worker.view;

import napodev.framework.bework.BaseApp;
import napodev.framework.bework.corebase.model.view.BaseViewModel;
import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.utils.Log;
import napodev.framework.bework.utils.helper.DateHelper;
import napodev.framework.bework.utils.helper.StringHelper;

/**
 * Created by opannapo on 12/17/16.
 */
public abstract class BaseActivityControl {
    private BaseApp app;
    private StringHelper stringHelper;
    private BaseViewModel viewModel;
    private long timeStart;
    private long timeEnd;

    public BaseActivityControl(BaseViewModel viewModel) {
        this.app = BaseApp.getInstance();
        this.stringHelper = new StringHelper();
        this.viewModel = viewModel;
    }

    public BaseActivity getActivity() {
        return viewModel.getActivity();
    }

    public BaseApp getApp() {
        return app;
    }

    public StringHelper getStringHelper() {
        return stringHelper;
    }

    public BaseViewModel getViewModel() {
        return viewModel;
    }

    public void startTimeExecute() {
        timeStart = DateHelper.getMyTimeStampMilis();
        Log.d("Execute Time Start " + DateHelper.TimeMillis.asDateString(timeStart, DateHelper.FORMAT.HH_MM_SS_SSS));
    }

    public void endTimeExecute() {
        timeEnd = DateHelper.getMyTimeStampMilis();
        Log.d("Execute Time End " + DateHelper.TimeMillis.differenceAsString(timeStart, timeEnd));
    }
}
