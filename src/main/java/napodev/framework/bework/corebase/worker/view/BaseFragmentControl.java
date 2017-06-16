package napodev.framework.bework.corebase.worker.view;

import napodev.framework.bework.BaseApp;
import napodev.framework.bework.corebase.model.view.BaseFragmentViewModel;
import napodev.framework.bework.corebase.view.BaseFragment;
import napodev.framework.bework.utils.helper.StringHelper;

/**
 * Created by opannapo on 12/17/16.
 */
public abstract class BaseFragmentControl {
    BaseFragment fragment;
    BaseApp app;
    StringHelper stringHelper;
    private BaseFragmentViewModel viewModel;

    public BaseFragmentControl(BaseFragment fragment) {
        this.fragment = fragment;
        this.viewModel = fragment.getViewModel();
        this.app = BaseApp.getInstance();
        this.stringHelper = new StringHelper();
    }

    public BaseFragment getFragment() {
        return (BaseFragment) fragment;
    }

    public BaseFragmentViewModel getViewModel() {
        return viewModel;
    }
}
