package napodev.framework.bework.corebase.model.view;

import android.view.ViewGroup;

import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.utils.napoinject.FontInjector;
import napodev.framework.bework.utils.napoinject.RootInjector;
import napodev.framework.bework.utils.napoinject.ViewInjector;

/**
 * Created by opannapo on 4/23/17.
 */
public abstract class BaseViewModel {
    BaseActivity activity;
    ViewGroup root;
    BaseViewImpl viewImpl;
    BaseWorkerImpl workerImpl;

    public BaseViewModel(BaseActivity activity, BaseViewImpl viewImpl, BaseWorkerImpl workerImpl) {
        this.activity = activity;
        this.root = this.activity.mainView;
        this.viewImpl = viewImpl;
        this.workerImpl = workerImpl;
        RootInjector.inject(this);
        ViewInjector.inject(this, this.root);
        FontInjector.inject(this, this.activity, this.root);
    }

    public BaseActivity getActivity() {
        return activity;
    }

    public BaseViewImpl getViewImpl() {
        return this.viewImpl;
    }

    public BaseWorkerImpl getWorkerImpl() {
        return this.workerImpl;
    }
}
