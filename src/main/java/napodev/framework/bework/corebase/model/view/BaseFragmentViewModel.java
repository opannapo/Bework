package napodev.framework.bework.corebase.model.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.corebase.view.BaseFragment;
import napodev.framework.bework.utils.napoinject.FontInjector;
import napodev.framework.bework.utils.napoinject.RootInjector;
import napodev.framework.bework.utils.napoinject.ViewInjector;

/**
 * Created by opannapo on 4/23/17.
 */
public abstract class BaseFragmentViewModel {
    BaseFragment fragment;
    BaseFragmentViewImpl viewImpl;
    BaseFragmentWorkerImpl workerImpl;

    public BaseFragmentViewModel(BaseFragment fragment, BaseFragmentViewImpl viewImpl, BaseFragmentWorkerImpl workerImpl, LayoutInflater inflater, ViewGroup container) {
        this.fragment = fragment;
        this.viewImpl = viewImpl;
        this.workerImpl = workerImpl;

        this.fragment.viewRoot = RootInjector.injectFragment(this, inflater, container);
        ViewInjector.inject(this, (ViewGroup) this.fragment.viewRoot);
        FontInjector.inject(this, (BaseActivity) this.fragment.getActivity(), (ViewGroup) this.fragment.viewRoot);
    }

    public BaseFragment getFragment() {
        return fragment;
    }

    public BaseFragmentViewImpl getViewImpl() {
        return this.viewImpl;
    }

    public BaseFragmentWorkerImpl getWorkerImpl() {
        return this.workerImpl;
    }
}
